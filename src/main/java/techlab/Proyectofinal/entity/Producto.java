package techlab.Proyectofinal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int stock;

    public Producto(String nombre, String descripcion, double precio, String categoria, String imagenUrl, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        setPrecio(precio);
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        setStock(stock);
    }

    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
        this.stock = stock;
    }

    public boolean contieneNombre(String busqueda) {
        return nombre != null && nombre.toLowerCase().contains(busqueda.toLowerCase());
    }

    public void disminuirStock(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad a disminuir no puede ser negativa.");
        if (cantidad > stock) throw new IllegalArgumentException("Stock insuficiente");
        stock -= cantidad;
    }
}
