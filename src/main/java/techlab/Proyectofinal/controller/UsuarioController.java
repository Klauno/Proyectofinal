package techlab.Proyectofinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techlab.Proyectofinal.modelo.Usuario;
import techlab.Proyectofinal.modelo.Pedido;
import techlab.Proyectofinal.servicio.GestionProductosPedidos;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final GestionProductosPedidos gestion;

    public UsuarioController(GestionProductosPedidos gestion) {
        this.gestion = gestion;
    }

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = gestion.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    // Endpoint para listar pedidos de un usuario por su ID
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidosPorUsuario(@PathVariable Long id) {
        List<Pedido> pedidos = gestion.listarPedidosPorUsuario(id);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }
}
