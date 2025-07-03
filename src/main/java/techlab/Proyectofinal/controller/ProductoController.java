package techlab.Proyectofinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techlab.Proyectofinal.dto.ProductResponseDTO;
import techlab.Proyectofinal.modelo.Producto;
import techlab.Proyectofinal.servicio.GestionProductosPedidos;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final GestionProductosPedidos gestion;

    public ProductoController(GestionProductosPedidos gestion) {
        this.gestion = gestion;
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponseDTO> crearProducto(@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.gestion.agregarProducto(producto));
    }

    @GetMapping("/list")
    public List<Producto> listarProductos(){
        return this.gestion.listarProductos();
    }

    @GetMapping("/find")
    public ResponseEntity<List<Producto>> buscarProductos(@RequestParam String nombreBusqueda){
        // Ya no se maneja la excepción aquí, se delega al manejo global
        List<Producto> productosEncontrados = this.gestion.buscarProducto(nombreBusqueda);
        return ResponseEntity.ok(productosEncontrados);
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        // Lanza ProductNotFoundException si no encuentra el producto
        return this.gestion.buscarProductoPorId(id);
    }

    @PutMapping("/{id}")
    public Producto editarPrecioProducto(@PathVariable Long id, @RequestParam Double nuevoPrecio){
        return this.gestion.editarProducto(id, nuevoPrecio);
    }

    @DeleteMapping("/{id}")
    public Producto eliminarProducto(@PathVariable Long id){
        return this.gestion.eliminarProducto(id);
    }
}
