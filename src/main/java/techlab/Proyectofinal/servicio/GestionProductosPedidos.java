package techlab.Proyectofinal.servicio;

import org.springframework.stereotype.Service;
import techlab.Proyectofinal.dto.LineaPedidoDTO;
import techlab.Proyectofinal.dto.ProductResponseDTO;
import techlab.Proyectofinal.exception.ProductNotFoundException;
import techlab.Proyectofinal.exception.StockInsuficienteException;
import techlab.Proyectofinal.exception.UsuarioNotFoundException;
import techlab.Proyectofinal.modelo.*;
import techlab.Proyectofinal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import techlab.Proyectofinal.repository.PedidoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionProductosPedidos {

    private final ProductoRepository productoRepository;
    private final ProductoMemoriaRepository productoMemoriaRepository;
    private final PedidoRepository pedidoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public GestionProductosPedidos(ProductoRepository productoRepository,
                                   ProductoMemoriaRepository productoMemoriaRepository,
                                   PedidoRepository pedidoRepository,
                                   CategoriaRepository categoriaRepository,
                                   UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.productoMemoriaRepository = productoMemoriaRepository;
        this.pedidoRepository = pedidoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
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
        productoRepository.save(producto);
        return new ProductResponseDTO("Producto agregado exitosamente! ID: " + producto.getId());
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto con ID " + id + " no encontrado"));
    }

    public List<Producto> buscarProductoPorNombre(String nombre) {
        List<Producto> encontrados = productoRepository.findByNombreContainingIgnoreCase(nombre);
        if (encontrados.isEmpty()) {
            throw new ProductNotFoundException("No se encontraron productos con nombre: " + nombre);
        }
        return encontrados;
    }

    public Producto editarProducto(Long id, Double nuevoPrecio, Integer nuevoStock) {
        Producto producto = buscarProductoPorId(id);
        if (nuevoPrecio != null) {
            if (nuevoPrecio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
            producto.setPrecio(nuevoPrecio);
        }
        if (nuevoStock != null) {
            if (nuevoStock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
            producto.setStock(nuevoStock);
        }
        return productoRepository.save(producto);
    }

    public Producto eliminarProducto(Long id) {
        Producto producto = buscarProductoPorId(id);
        productoRepository.delete(producto);
        return producto;
    }

    // Pedidos

    public Pedido crearPedido(List<LineaPedidoDTO> lineas) {
        Pedido pedido = new Pedido();
        for (LineaPedidoDTO dto : lineas) {
            Producto producto = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new ProductNotFoundException("Producto con ID " + dto.getProductoId() + " no encontrado"));
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
            Producto producto = linea.getProducto();
            producto.disminuirStock(linea.getCantidad());
            productoRepository.save(producto);
        }
        pedidoRepository.save(pedido);
        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Pedido con ID " + id + " no encontrado"));
    }

    // Categorías

    public Categoria agregarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Usuarios y pedidos por usuario

    public List<Pedido> listarPedidosPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con ID " + usuarioId + " no encontrado"));
        return usuario.getPedidos();
    }

    // Productos con stock bajo

    public List<Producto> productosConStockBajo(int min) {
        return productoRepository.findAll()
                .stream()
                .filter(p -> p.getStock() <= min)
                .collect(Collectors.toList());
    }

    public Usuario guardarUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
        // Podés agregar validación de formato de email aquí
        return usuarioRepository.save(usuario);
    }
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

}
