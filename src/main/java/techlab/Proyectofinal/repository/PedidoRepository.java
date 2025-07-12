package techlab.Proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techlab.Proyectofinal.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Métodos CRUD listos para usar
}
