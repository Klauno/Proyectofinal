package techlab.Proyectofinal.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techlab.Proyectofinal.entity.Pedido;
import techlab.Proyectofinal.entity.Usuario;
import techlab.Proyectofinal.servicio.GestionProductosPedidos;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final GestionProductosPedidos gestion;

    public UsuarioController(GestionProductosPedidos gestion) {
        this.gestion = gestion;
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = gestion.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidosPorUsuario(@PathVariable Long id) {
        List<Pedido> pedidos = gestion.listarPedidosPorUsuario(id);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }
}
