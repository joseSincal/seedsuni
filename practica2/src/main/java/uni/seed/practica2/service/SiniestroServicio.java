package uni.seed.practica2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uni.seed.practica2.entity.Compania;
import uni.seed.practica2.entity.Perito;
import uni.seed.practica2.entity.Seguro;
import uni.seed.practica2.entity.Siniestro;
import uni.seed.practica2.repository.CompaniaRepository;
import uni.seed.practica2.repository.PeritoRepository;
import uni.seed.practica2.repository.SeguroRepository;
import uni.seed.practica2.repository.SiniestroRepository;

@RestController
@RequestMapping("/siniestro")
@CrossOrigin
public class SiniestroServicio {

	@Autowired
	SiniestroRepository siniestroRepository;
	
	@Autowired
	SeguroRepository seguroRepository;
	
	@Autowired
	PeritoRepository peritoRepository;
	
	@GetMapping(path="/buscar")
	public List<Siniestro> buscar(){
		return siniestroRepository.findAll();
	}
	
	@PostMapping(path="/guardar")
	public Siniestro guardar(@RequestBody Siniestro  siniestro) {
		return siniestroRepository.save(siniestro);
	}
	
	@PostMapping(path="/guardar/seguro/perito")
	public Siniestro guardarSeguroPerito(@RequestBody Siniestro siniestro) {
		Seguro seguro = siniestro.getSeguro();
		siniestro.setSeguro(null);
		seguroRepository.save(seguro);
		Perito perito = siniestro.getPerito();
		siniestro.setPerito(null);
		seguroRepository.save(seguro);
		siniestro.setSeguro(seguro);
		siniestro.setPerito(perito);
		return siniestroRepository.save(siniestro);
		
	}
	
	
	@DeleteMapping(path="/eliminar/{idSiniestro}")
	public void eliminar(@PathVariable int idSiniestro) {
		Optional<Siniestro> siniestro = siniestroRepository.findById(idSiniestro);
		if(siniestro.isPresent()) {
			siniestroRepository.delete(siniestro.get());
		}
	}
}
