package uni.seed.practica2.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import uni.seed.practica2.dto.UsuarioDto;
import uni.seed.practica2.entity.Usuario;
import uni.seed.practica2.repository.UsuarioRepository;
import uni.seed.practica2.ws.UsuarioService;

@Component
public class UsuarioImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public Usuario logear(UsuarioDto usuarioDto) {
		return usuarioRepository.findByEmailAndPassword(usuarioDto.getEmail(), usuarioDto.getPassword());
	}

	@Override
	public List<Usuario> buscar() {
		return usuarioRepository.findAll();
	}

	@Override
	public ResponseEntity<Usuario> buscarEmail(UsuarioDto usuarioDto) {
		try {
			return new ResponseEntity<>(usuarioRepository.findByEmail(usuarioDto.getEmail()), null, HttpStatus.OK);
		}catch(Exception exp) {
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> error() {
		// TODO Auto-generated method stub
		return new ResponseEntity<>("ERROR", null, HttpStatus.FORBIDDEN);
	}
	
	

}
