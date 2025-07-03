package techlab.Proyectofinal.modelo;

public class Producto {
    // ID único autoincremental para identificar cada producto
    private static long contadorId = 1;
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private String imagenUrl;
    private int stock;

    public Producto() {
        this.id = contadorId++;
    }

    public Producto(String nombre, String descripcion, double precio, String categoria, String imagenUrl, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        setPrecio(precio);
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        setStock(stock);
    }

    // Getters y setters

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
        this.stock = stock;
    }

    // Metodo para verificar si el nombre contiene un texto de búsqueda (para búsquedas)
    public boolean contieneNombre(String busqueda) {
        return nombre != null && nombre.toLowerCase().contains(busqueda.toLowerCase());
    }

    // Reduce el stock al confirmar una venta o pedido
    public void disminuirStock(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad a disminuir no puede ser negativa.");
        if (cantidad > stock) throw new IllegalArgumentException("Stock insuficiente");
        stock -= cantidad;
    }
}
