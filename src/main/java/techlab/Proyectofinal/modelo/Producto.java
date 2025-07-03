package techlab.Proyectofinal.modelo;

public class Producto {
    // ID único autoincremental para identificar cada producto
    private static long contadorId = 1;
    private Long id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto() {
        this.id = contadorId++;
    }

    public Producto(String nombre, double precio, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // Metodo para verificar si el nombre contiene un texto de búsqueda (para búsquedas)
    public boolean contieneNombre(String busqueda) {
        return nombre != null && nombre.toLowerCase().contains(busqueda.toLowerCase());
    }
    // Reduce el stock al confirmar una venta o pedido
    public void disminuirStock(int cantidad) {
        if (cantidad > stock) throw new IllegalArgumentException("Stock insuficiente");
        stock -= cantidad;
    }
}
