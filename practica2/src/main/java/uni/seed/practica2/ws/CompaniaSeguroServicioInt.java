package uni.seed.practica2.ws;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.beans.CompaniaSeguroDto;

import uni.seed.practica2.entity.CompaniaSeguro;

@RestController
@RequestMapping("/companiaseguro")
@CrossOrigin
public interface CompaniaSeguroServicioInt {
	
	@GetMapping(path="/buscar")
	public List<CompaniaSeguro>  buscar();
	
	@PostMapping(path="/guardar/compania/{nombreCompania}/seguro/{numeroPoliza}")
	public CompaniaSeguro guardar(@RequestBody CompaniaSeguroDto companiaSeguroDto, @PathVariable String nombreCompania, @PathVariable int numeroPoliza);
	
	@PostMapping(path="/guardar/compania/y/seguro")
	public CompaniaSeguro guardarCompaniaYSeguro(@RequestBody CompaniaSeguroDto companiaSeguroDto);
	
	@DeleteMapping(path="/eliminar/{id}")
	public void eliminar(@PathVariable Integer id);
	
	@GetMapping(path="/buscar/{id}")
	public List<CompaniaSeguro> buscarPorId(@PathVariable int id);
	
	@DeleteMapping(path="/eliminar/sql/{id}")
	public int eliminarCompaniaSeguro(Integer id);

}
