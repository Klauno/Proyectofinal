package techlab.Proyectofinal.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private String imagenUrl;

    @JsonProperty("stock")
    private int stock;
    private int cantidadAComprar;

    public Producto(String nombre, String descripcion, double precio, String categoria, String imagenUrl, int stock, int cantidadAComprar) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        setPrecio(precio);
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        setStock(stock);
        this.cantidadAComprar = cantidadAComprar;
    }

    public Producto(String nombre, String descripcion, double precio, String categoria, String imagenUrl, int stock) {
        this(nombre, descripcion, precio, categoria, imagenUrl, stock, 0);
    }

    // Validación personalizada en setters
    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
        this.stock = stock;
    }

    // Metodo para búsquedas por nombre
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
