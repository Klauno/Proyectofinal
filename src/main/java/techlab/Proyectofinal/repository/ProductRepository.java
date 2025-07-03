package techlab.Proyectofinal.repository;

import org.springframework.stereotype.Repository;
import techlab.Proyectofinal.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final ArrayList<Producto> productos;

    public ProductRepository() {
        this.productos = new ArrayList<>();
        this.agregarProductosDeEjemplo();
    }

    public String agregarProducto(Producto producto){
        productos.add(producto);
        return "Producto agregado exitosamente! ID: " + producto.getId();
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public List<Producto> buscarProducto(String busqueda) {
        ArrayList<Producto> encontrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.contieneNombre(busqueda)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public Producto buscarPorId(Long id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Producto eliminarProducto(Producto producto) {
        productos.remove(producto);
        return producto;
    }

    private void agregarProductosDeEjemplo() {
        productos.add(new Producto("Monitor", 1000, 10));
        productos.add(new Producto("Micrófono", 2000, 10));
        productos.add(new Producto("Teclado mecánico", 1500, 15));
        productos.add(new Producto("Mouse gamer", 1200, 20));
        productos.add(new Producto("Laptop", 15000, 5));
        productos.add(new Producto("Tablet", 8000, 7));
        productos.add(new Producto("Disco duro externo", 2500, 12));
        productos.add(new Producto("Memoria USB 64GB", 500, 25));
        productos.add(new Producto("Router Wi-Fi", 1800, 10));
        productos.add(new Producto("Smartphone", 12000, 8));
        productos.add(new Producto("Audífonos Bluetooth", 2200, 18));
        productos.add(new Producto("Cámara Web HD", 1300, 10));
        productos.add(new Producto("Impresora", 4000, 6));
        productos.add(new Producto("Proyector", 9000, 4));
        productos.add(new Producto("Reproductor multimedia", 3000, 9));
        productos.add(new Producto("Smartwatch", 3500, 11));
        productos.add(new Producto("Lector de tarjetas", 800, 14));
        productos.add(new Producto("Estabilizador de voltaje", 1100, 10));
        productos.add(new Producto("Cable HDMI", 300, 30));
        productos.add(new Producto("Panel táctil USB", 2000, 5));
    }
}
