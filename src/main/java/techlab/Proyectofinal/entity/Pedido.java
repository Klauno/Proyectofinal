package techlab.Proyectofinal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private String estado = "pendiente";

    @ManyToOne
    private Usuario usuario;

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public double calcularTotal() {
        return lineas.stream()
                .mapToDouble(LineaPedido::getCostoLinea)
                .sum();
    }
}
