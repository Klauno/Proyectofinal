package techlab.Proyectofinal.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<LineaPedido> lineas = new ArrayList<>();

    private LocalDateTime fecha = LocalDateTime.now();

    private String estado = "pendiente"; // "pendiente", "confirmado", "enviado", "entregado", "cancelado"

    @ManyToOne
    private Usuario usuario; // Relación con usuario

    // Método para agregar una línea al pedido
    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    // Método para calcular el total del pedido
    public double calcularTotal() {
        return lineas.stream()
                .mapToDouble(LineaPedido::getCostoLinea)
                .sum();
    }
}
