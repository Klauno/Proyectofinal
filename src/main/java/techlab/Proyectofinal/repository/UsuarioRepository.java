package techlab.Proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techlab.Proyectofinal.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
