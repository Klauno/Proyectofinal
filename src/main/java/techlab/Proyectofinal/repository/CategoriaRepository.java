package techlab.Proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techlab.Proyectofinal.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
