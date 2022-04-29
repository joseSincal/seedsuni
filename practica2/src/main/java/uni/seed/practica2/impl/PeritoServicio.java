package uni.seed.practica2.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import uni.seed.practica2.common.ConversionDto;
import uni.seed.practica2.dto.PeritoDto;
import uni.seed.practica2.entity.Perito;
import uni.seed.practica2.entity.Siniestro;
import uni.seed.practica2.repository.PeritoRepository;
import uni.seed.practica2.repository.SiniestroRepository;
import uni.seed.practica2.service.CatalogoServicio;
import uni.seed.practica2.ws.PeritoServicioInt;

@Component
public class PeritoServicio implements PeritoServicioInt{

	@Autowired
	PeritoRepository peritoRepository;
	
	@Autowired
	SiniestroRepository siniestroRepository;
	
	@Autowired
	CatalogoServicio catalogoServicio;
	
	@Autowired
	ConversionDto conversionDto;
	
	@Override
	public List<Perito> buscar(){
		return peritoRepository.findAll();
	}
	
	@Override
	public ResponseEntity<Perito> guardar(PeritoDto peritoDto){
		try {
			Perito perito =  conversionDto.convertirPeritoDtoToPerito(peritoDto);
			return new ResponseEntity<>(peritoRepository.save(perito),null,HttpStatus.OK);
		}catch(Exception exp) {
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@Override
	public void eliminar(Integer dniPerito){
		Optional<Perito> perito = peritoRepository.findById(dniPerito);
		if(perito.isPresent()) {
			peritoRepository.delete(perito.get());
		}
	}
	
	@Override
	public List<Perito> buscarCiudadYNumeroVia(String  ciudad, String numeroVia){
		return peritoRepository.findByCiudadAndNumeroVia(ciudad, numeroVia);
	}
	
	@Override
	public List<Siniestro> buscarSiniestroPerito(int dniPerito){
		List<Siniestro> siniestro = new ArrayList<>();
		for(Siniestro sin : siniestroRepository.findAll()) {
			if(sin.getPerito() != null) {
				if(sin.getPerito().getDniPerito() == dniPerito) {
					siniestro.add(sin);
				}
			}
		}
		return siniestro;
	}

	@Override
	public ResponseEntity<Integer> nuevoPerito(PeritoDto peritoDto) {
		try {
			return new ResponseEntity<>(catalogoServicio.nuevoPerito(peritoDto), null, HttpStatus.OK);
		}catch(Exception exp) {
			return new ResponseEntity<>(null,null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public Page<Perito> buscarPeritoOrdenadoConPaginado(int pagina, int cantidad) {
		Pageable paginador = PageRequest.of(pagina,cantidad);
		return peritoRepository.buscarTodosEnOrden(paginador);
	}


}
