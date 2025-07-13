package techlab.Proyectofinal.dto;

import techlab.Proyectofinal.entity.Pedido;

public class PedidoResponseDTO {
    private String message;
    private int pedidoId;
    private double total;

    public PedidoResponseDTO() {}

    public PedidoResponseDTO(String message, Pedido pedido) {
        this.message = message;
        this.pedidoId = pedido.getIdPedido();
        this.total = pedido.calcularTotal();
    }

    public String getMessage() { return message; }
    public int getPedidoId() { return pedidoId; }
    public double getTotal() { return total; }

    public void setMessage(String message) { this.message = message; }
    public void setPedidoId(int pedidoId) { this.pedidoId = pedidoId; }
    public void setTotal(double total) { this.total = total; }
}
