package uni.seed.practica2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.seed.practica2.entity.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {
	
	public Usuario findByEmail(String email);
	public Usuario findByEmailAndPassword(String email, String password);

}
