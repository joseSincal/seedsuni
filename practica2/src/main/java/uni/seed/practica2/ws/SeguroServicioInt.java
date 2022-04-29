package uni.seed.practica2.ws;

import java.util.Date;
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

import uni.seed.practica2.dto.SeguroDto;
import uni.seed.practica2.entity.Seguro;

@RestController
@RequestMapping("seguro")
@CrossOrigin
public interface SeguroServicioInt {

	@GetMapping(path="/buscar")
	public List<Seguro> buscar();
	
	@PostMapping(path="/guardar")
	public ResponseEntity<Seguro> guardar(@RequestBody SeguroDto seguroDto);
	
	@DeleteMapping(path="/eliminar/{numeroPoliza}")
	public void eliminar(@PathVariable int numeroPoliza);
	
	@GetMapping(path="/buscar/fechaInicio/despuesde/{fechaInicio}")
	public List<Seguro> buscarPorFechaDespues(@PathVariable Date fechaInicio);
	
	@GetMapping(path="/buscar/fechaInicio/{fechaInicio}")
	public List<Seguro> buscarFechaiInicio(@PathVariable Date fechaInicio);
	
	@GetMapping(path="/buscar/poliza/{numeroPoliza}")
	public List<Seguro> buscarPorCompaniaAsc(@PathVariable int numeroPoliza);
	
	@GetMapping(path="/buscar/paginacion/{pagina}/{cantidad}")
	public Page<Seguro> buscar(@PathVariable int pagina, @PathVariable int cantidad);
	
	@GetMapping(path = "/buscar/cliente/{dniCliente}")
	public List<Seguro> buscar(@PathVariable int dniCliente);
	

}
