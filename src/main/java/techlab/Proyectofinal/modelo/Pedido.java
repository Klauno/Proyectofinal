package techlab.Proyectofinal.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1;
    private int idPedido;
    private List<LineaPedido> lineas;
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
