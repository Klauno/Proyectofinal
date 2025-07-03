package techlab.Proyectofinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techlab.Proyectofinal.dto.LineaPedidoDTO;
import techlab.Proyectofinal.dto.PedidoResponseDTO;
import techlab.Proyectofinal.modelo.Pedido;
import techlab.Proyectofinal.servicio.GestionProductosPedidos;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final GestionProductosPedidos gestion;

    public PedidoController(GestionProductosPedidos gestion) {
        this.gestion = gestion;
    }

    @PostMapping("/")
    public ResponseEntity<PedidoResponseDTO> crearPedido(@RequestBody List<LineaPedidoDTO> lineas) {
        Pedido pedido = gestion.crearPedido(lineas);
        Pedido confirmado = gestion.confirmarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PedidoResponseDTO("Pedido creado y confirmado correctamente.", confirmado));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = gestion.listarPedidos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable int id) {
        Pedido pedido = gestion.buscarPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }
}
