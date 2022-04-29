package uni.seed.practica2.ws;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uni.seed.practica2.dto.CompaniaDto;
import uni.seed.practica2.entity.Compania;

@RestController
@RequestMapping("/compania")
@CrossOrigin
public interface CompaniaServicioInt {

	@GetMapping(path="/buscar")
	public List<Compania> buscar();
	
	@PostMapping(path="/guardar")
	public ResponseEntity<Compania> guardar(@RequestBody CompaniaDto companiaDto);
	
	@DeleteMapping(path="/eliminar/{nombreCompania}")
	public void eliminar(@PathVariable String nombreCompania);
	
	@GetMapping(path="/buscar/{nombreVia}")
	public List<Compania> buscarPorId(@PathVariable String nombreVia);
	
	@GetMapping(path="/buscar/nombre/contiene/{referencia}")
	public List<Compania> buscarContieneNombre(@PathVariable String referencia);
	
	@GetMapping(path="/buscar/paginacion/{pagina}/{cantidad}")
	public Page<Compania> buscar(@PathVariable int pagina, @PathVariable int cantidad);
}
