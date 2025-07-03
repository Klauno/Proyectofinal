package techlab.Proyectofinal.repository;

import org.springframework.stereotype.Repository;
import techlab.Proyectofinal.modelo.Pedido;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void guardar(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> listar() {
        return pedidos;
    }

    public Pedido buscarPorId(int id) {
        return pedidos.stream().filter(p -> p.getIdPedido() == id).findFirst().orElse(null);
    }
}
