package techlab.Proyectofinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techlab.Proyectofinal.dto.ProductResponseDTO;
import techlab.Proyectofinal.entity.Producto;
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
    public ResponseEntity<List<Producto>> buscarProductosPorNombre(@RequestParam String nombre){
        return ResponseEntity.ok(this.gestion.buscarProductoPorNombre(nombre));
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return this.gestion.buscarProductoPorId(id);
    }

    @PutMapping("/{id}")
    public Producto editarProducto(@PathVariable Long id,
                                   @RequestParam(required = false) Double nuevoPrecio,
                                   @RequestParam(required = false) Integer nuevoStock){
        return this.gestion.editarProducto(id, nuevoPrecio, nuevoStock);
    }

    @DeleteMapping("/{id}")
    public Producto eliminarProducto(@PathVariable Long id){
        return this.gestion.eliminarProducto(id);
    }

    @GetMapping("/stock-bajo")
    public List<Producto> productosConStockBajo(@RequestParam(defaultValue = "5") int min) {
        return gestion.productosConStockBajo(min);
    }
}
