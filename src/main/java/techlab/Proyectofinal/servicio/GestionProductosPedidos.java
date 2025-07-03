package techlab.Proyectofinal.servicio;

import org.springframework.stereotype.Service;
import techlab.Proyectofinal.dto.LineaPedidoDTO;
import techlab.Proyectofinal.dto.ProductResponseDTO;
import techlab.Proyectofinal.exception.ProductNotFoundException;
import techlab.Proyectofinal.exception.StockInsuficienteException;
import techlab.Proyectofinal.modelo.LineaPedido;
import techlab.Proyectofinal.modelo.Pedido;
import techlab.Proyectofinal.modelo.Producto;
import techlab.Proyectofinal.repository.PedidoRepository;
import techlab.Proyectofinal.repository.ProductRepository;

import java.util.List;

@Service
public class GestionProductosPedidos {

    private final ProductRepository productRepository;
    private final PedidoRepository pedidoRepository;

    public GestionProductosPedidos(ProductRepository productRepository, PedidoRepository pedidoRepository) {
        this.productRepository = productRepository;
        this.pedidoRepository = pedidoRepository;
    }

    // Productos
    public ProductResponseDTO agregarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        String mensaje = productRepository.agregarProducto(producto);
        return new ProductResponseDTO(mensaje);
    }

    public List<Producto> listarProductos() {
        return productRepository.listarProductos();
    }

    public List<Producto> buscarProducto(String busqueda) {
        List<Producto> encontrados = productRepository.buscarProducto(busqueda);
        if (encontrados.isEmpty()) {
            throw new ProductNotFoundException(busqueda);
        }
        return encontrados;
    }

    // Metodo corregido para manejar Optional correctamente
    public Producto buscarProductoPorId(Long id) {
        return productRepository.buscarPorId(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto con ID: " + id + " no encontrado"));
    }

    public Producto editarProducto(Long id, Double nuevoPrecio) {
        Producto p = buscarProductoPorId(id);
        if (nuevoPrecio != null && nuevoPrecio >= 0) {
            p.setPrecio(nuevoPrecio);
        }
        return p;
    }

    public Producto eliminarProducto(Long id) {
        Producto p = buscarProductoPorId(id);
        return productRepository.eliminarProducto(p);
    }

    // Pedidos
    public Pedido crearPedido(List<LineaPedidoDTO> lineas) {
        Pedido pedido = new Pedido();
        for (LineaPedidoDTO dto : lineas) {
            Producto producto = buscarProductoPorId(dto.getProductoId());
            if (dto.getCantidad() <= 0 || dto.getCantidad() > producto.getStock()) {
                throw new StockInsuficienteException(producto.getNombre());
            }
            pedido.agregarLinea(new LineaPedido(producto, dto.getCantidad()));
        }
        return pedido;
    }

    public Pedido confirmarPedido(Pedido pedido) {
        for (LineaPedido linea : pedido.getLineas()) {
            Producto producto = linea.getProducto();
            if (linea.getCantidad() > producto.getStock()) {
                throw new StockInsuficienteException(producto.getNombre());
            }
        }
        for (LineaPedido linea : pedido.getLineas()) {
            linea.getProducto().disminuirStock(linea.getCantidad());
        }
        pedidoRepository.guardar(pedido);
        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.listar();
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new ProductNotFoundException("Pedido con ID: " + id + " no encontrado"));
    }
}
