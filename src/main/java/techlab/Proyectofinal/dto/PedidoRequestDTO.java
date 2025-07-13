package techlab.Proyectofinal.dto;

import java.util.List;

public class PedidoRequestDTO {
    private Long usuarioId;
    private List<LineaPedidoDTO> lineas;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<LineaPedidoDTO> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedidoDTO> lineas) {
        this.lineas = lineas;
    }
}
