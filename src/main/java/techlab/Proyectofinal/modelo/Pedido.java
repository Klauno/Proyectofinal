package techlab.Proyectofinal.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1;
    // ID único autoincremental para identificar el pedido
    private int idPedido;
    // Lista de líneas de pedido (cada línea con producto y cantidad)
    private List<LineaPedido> lineas;
    // Fecha y hora en que se creó el pedido
    private LocalDateTime fecha;

    public Pedido() {
        this.idPedido = contadorPedidos++;
        this.lineas = new ArrayList<>();
        this.fecha = LocalDateTime.now();
    }

    public int getIdPedido() { return idPedido; }
    public List<LineaPedido> getLineas() { return lineas; }
    public void agregarLinea(LineaPedido linea) { lineas.add(linea); }
    public LocalDateTime getFecha() { return fecha; }

    public double calcularTotal() {
        return lineas.stream()
                .mapToDouble(LineaPedido::getCostoLinea)
                .sum();
    }
}
