package techlab.Proyectofinal.controller;
import org.springframework.web.bind.annotation.*;
import techlab.Proyectofinal.modelo.Categoria;
import techlab.Proyectofinal.servicio.GestionProductosPedidos;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final GestionProductosPedidos gestion;
    public CategoriaController(GestionProductosPedidos gestion) { this.gestion = gestion; }

    @PostMapping("/")
    public Categoria agregarCategoria(@RequestBody Categoria categoria) {
        return gestion.agregarCategoria(categoria);
    }
    @GetMapping("/list")
    public List<Categoria> listarCategorias() {
        return gestion.listarCategorias();
    }
}
