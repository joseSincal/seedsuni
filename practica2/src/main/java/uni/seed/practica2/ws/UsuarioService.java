package uni.seed.practica2.ws;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uni.seed.practica2.dto.UsuarioDto;
import uni.seed.practica2.entity.Usuario;

@RestController
@RequestMapping("/usuario") 
@CrossOrigin
public interface UsuarioService {
	
	@GetMapping(path = "/buscar/email/password")
	public Usuario logear(@RequestBody UsuarioDto usuarioDto);
	
	@GetMapping(path = "buscar")
	public List<Usuario> buscar();
	
	@PostMapping(path = "/buscar/email")
	public ResponseEntity<Usuario> buscarEmail(@RequestBody UsuarioDto usuarioDto);
	
	@GetMapping(path = "/error")
	public ResponseEntity<String> error();

}
