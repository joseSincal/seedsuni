package uni.seed.practica2.ws;

import java.util.List;
import java.util.Map;

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

import uni.seed.practica2.dto.ClienteDto;
import uni.seed.practica2.entity.Cliente;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public interface ClienteServicioInt {

	
	@GetMapping(path = "/buscar")
	public List<Cliente> buscar();
	
	@PostMapping(path="/guardar")
	public ResponseEntity<Cliente>  guardar(@RequestBody ClienteDto clienteDto);
	
	@PostMapping(path="/guardar/seguro")
	public ResponseEntity<Cliente> guardarSeguro(@RequestBody ClienteDto clienteDto);
	
	@DeleteMapping(path="/eliminar/{dniCl}")
	public void eliminar(@PathVariable("dniCl") int dniCl);
	
	@GetMapping(path="/buscar/nombre/{nombreCl}/and/{apellido1}")
	public List<Cliente> buscarNombreYApellido1(@PathVariable String nombreCl, @PathVariable String apellido1);
	
	@GetMapping(path="/buscar/apellido2/{apellido2}/or/ciudad/{ciudad}")
	public List<Cliente> buscarApellido2OCiudad(@PathVariable String apellido2, @PathVariable String  ciudad);
	
	@GetMapping(path="/buscar/nombre/like/{nombreIniciales}")
	public Cliente buscarNombreLike(@PathVariable String nombreIniciales);
	
	@GetMapping(path="/buscar/seguro")
	public List<Map<String, Object>> buscarClienteSeguro();
	
	@PostMapping(path="/update/{dniCl}/codigo/{codPostal}")
	public ResponseEntity<Integer> updateClienteCodigoPostal(@PathVariable int dniCl, @PathVariable int codPostal);
	
	@GetMapping(path = "/buscar/apellido/{apellido}")
	public List<Cliente> buscarApellido(@PathVariable String apellido);
	
	@GetMapping(path = "/buscar/paginacion/{pagina}/{cantidad}")
	public Page<Cliente> buscar(@PathVariable int pagina, @PathVariable int cantidad);
	
	@GetMapping(path = "/buscar/numero/cuentas/{numeroPoliza}")
	public List<Cliente> buscarCantidadSeguros(@PathVariable int numeroPoliza);
	
	@GetMapping(path = "/buscar/total/seguros/{dniCl}")
	public Long  buscarTotalSeguros(@PathVariable int dniCl);
	
}
