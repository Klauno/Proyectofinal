package techlab.Proyectofinal.repository;

import org.springframework.stereotype.Repository;
import techlab.Proyectofinal.modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductoMemoriaRepository {

    private final ArrayList<Producto> productos;

    public ProductoMemoriaRepository() {
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
        return productos.stream()
                .filter(p -> p.contieneNombre(busqueda))
                .collect(Collectors.toList());
    }

    // Cambiado para retornar Optional en lugar de null
    public Optional<Producto> buscarPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Producto eliminarProducto(Producto producto) {
        productos.remove(producto);
        return producto;
    }

    private void agregarProductosDeEjemplo() {
        productos.add(new Producto("Monitor", "Monitor LED 24 pulgadas", 1000, "Electrónica", "https://ejemplo.com/monitor.jpg", 10));
        productos.add(new Producto("Micrófono", "Micrófono condensador profesional", 2000, "Audio", "https://ejemplo.com/microfono.jpg", 10));
        productos.add(new Producto("Teclado mecánico", "Teclado mecánico RGB", 1500, "Periféricos", "https://ejemplo.com/teclado.jpg", 15));
        productos.add(new Producto("Mouse gamer", "Mouse para juegos con alta precisión", 1200, "Periféricos", "https://ejemplo.com/mouse.jpg", 20));
        productos.add(new Producto("Laptop", "Laptop ultraligera con procesador i7", 15000, "Computadoras", "https://ejemplo.com/laptop.jpg", 5));
        productos.add(new Producto("Tablet", "Tablet con pantalla retina", 8000, "Computadoras", "https://ejemplo.com/tablet.jpg", 7));
        productos.add(new Producto("Disco duro externo", "Disco duro portátil 1TB", 2500, "Almacenamiento", "https://ejemplo.com/disco.jpg", 12));
        productos.add(new Producto("Memoria USB 64GB", "Pendrive USB 3.0", 500, "Almacenamiento", "https://ejemplo.com/usb.jpg", 25));
        productos.add(new Producto("Router Wi-Fi", "Router inalámbrico dual band", 1800, "Redes", "https://ejemplo.com/router.jpg", 10));
        productos.add(new Producto("Smartphone", "Smartphone con cámara 48MP", 12000, "Telefonía", "https://ejemplo.com/smartphone.jpg", 8));
        productos.add(new Producto("Audífonos Bluetooth", "Auriculares inalámbricos", 2200, "Audio", "https://ejemplo.com/audifonos.jpg", 18));
        productos.add(new Producto("Cámara Web HD", "Cámara web 1080p", 1300, "Accesorios", "https://ejemplo.com/camara.jpg", 10));
        productos.add(new Producto("Impresora", "Impresora multifunción", 4000, "Oficina", "https://ejemplo.com/impresora.jpg", 6));
        productos.add(new Producto("Proyector", "Proyector portátil HD", 9000, "Audio y Video", "https://ejemplo.com/proyector.jpg", 4));
        productos.add(new Producto("Reproductor multimedia", "Reproductor 4K", 3000, "Audio y Video", "https://ejemplo.com/reproductor.jpg", 9));
        productos.add(new Producto("Smartwatch", "Reloj inteligente con GPS", 3500, "Wearables", "https://ejemplo.com/smartwatch.jpg", 11));
        productos.add(new Producto("Lector de tarjetas", "Lector USB para tarjetas SD", 800, "Accesorios", "https://ejemplo.com/lector.jpg", 14));
        productos.add(new Producto("Estabilizador de voltaje", "Protector eléctrico para equipos", 1100, "Accesorios", "https://ejemplo.com/estabilizador.jpg", 10));
        productos.add(new Producto("Cable HDMI", "Cable HDMI 2 metros", 300, "Accesorios", "https://ejemplo.com/hdmi.jpg", 30));
        productos.add(new Producto("Panel táctil USB", "Panel táctil externo USB", 2000, "Periféricos", "https://ejemplo.com/panel.jpg", 5));
    }


}
