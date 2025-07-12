package techlab.Proyectofinal.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Producto producto;

    private int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Calcula el costo total de esta línea de pedido,
     * multiplicando el precio unitario del producto por la cantidad.
     *
     * @return costo total de la línea de pedido
     */
    public double getCostoLinea() {
        if (producto == null) {
            return 0;
        }
        return producto.getPrecio() * cantidad;
    }
}
