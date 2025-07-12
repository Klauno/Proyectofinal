package techlab.Proyectofinal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import techlab.Proyectofinal.modelo.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}