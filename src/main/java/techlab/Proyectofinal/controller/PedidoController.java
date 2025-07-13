package techlab.Proyectofinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techlab.Proyectofinal.dto.LineaPedidoDTO;
import techlab.Proyectofinal.dto.PedidoRequestDTO;
import techlab.Proyectofinal.dto.PedidoResponseDTO;
import techlab.Proyectofinal.entity.*;
import techlab.Proyectofinal.exception.ProductNotFoundException;
import techlab.Proyectofinal.exception.StockInsuficienteException;
import techlab.Proyectofinal.exception.UsuarioNotFoundException;
import techlab.Proyectofinal.servicio.GestionProductosPedidos;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final GestionProductosPedidos gestion;

    public PedidoController(GestionProductosPedidos gestion) {
        this.gestion = gestion;
    }

    // Crear pedido con usuario y líneas
    @PostMapping("/")
    public ResponseEntity<PedidoResponseDTO> crearPedido(@RequestBody PedidoRequestDTO pedidoRequest) {
        // Buscar usuario por ID
        Usuario usuario = gestion.getUsuarioRepository().findById(pedidoRequest.getUsuarioId())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con ID " + pedidoRequest.getUsuarioId() + " no encontrado"));

        Pedido pedido = new Pedido();

        // Validar y agregar líneas de pedido
        for (LineaPedidoDTO dto : pedidoRequest.getLineas()) {
            Producto producto = gestion.buscarProductoPorId(dto.getProductoId());
            if (dto.getCantidad() <= 0 || dto.getCantidad() > producto.getStock()) {
                throw new StockInsuficienteException(producto.getNombre());
            }
            pedido.agregarLinea(new LineaPedido(producto, dto.getCantidad()));
        }

        pedido.setUsuario(usuario);

        // Confirmar pedido (actualiza stock y guarda)
        Pedido confirmado = gestion.confirmarPedido(pedido);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PedidoResponseDTO("Pedido creado y confirmado correctamente.", confirmado));
    }

    // Listar todos los pedidos
    @GetMapping("/list")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = gestion.listarPedidos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable int id) {
        Pedido pedido = gestion.buscarPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    // Actualizar estado de pedido
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable int id, @RequestParam String estado) {
        if (!EstadoPedido.esValido(estado)) {
            return ResponseEntity.badRequest()
                    .body(null);
        }
        Pedido pedido = gestion.buscarPedidoPorId(id);
        pedido.setEstado(estado.toLowerCase());
        Pedido pedidoActualizado = gestion.guardarPedido(pedido);
        return ResponseEntity.ok(pedidoActualizado);
    }

    // Eliminar pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable int id) {
        Pedido pedido = gestion.buscarPedidoPorId(id);
        gestion.eliminarPedido(pedido.getIdPedido());
        return ResponseEntity.noContent().build();
    }

    // Listar pedidos por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pedido>> listarPedidosPorUsuario(@PathVariable Long usuarioId) {
        List<Pedido> pedidos = gestion.listarPedidosPorUsuario(usuarioId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }
}
