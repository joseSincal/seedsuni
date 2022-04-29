package uni.seed.practica2.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import uni.seed.practica2.common.ConversionDto;
import uni.seed.practica2.dto.SiniestroDto;
import uni.seed.practica2.entity.Perito;
import uni.seed.practica2.entity.Seguro;
import uni.seed.practica2.entity.Siniestro;
import uni.seed.practica2.repository.PeritoRepository;
import uni.seed.practica2.repository.SeguroRepository;
import uni.seed.practica2.repository.SiniestroRepository;
import uni.seed.practica2.ws.SiniestroServicioInt;

@Component
public class SiniestroServicio implements SiniestroServicioInt{

	@Autowired
	SiniestroRepository siniestroRepository;
	
	@Autowired
	SeguroRepository seguroRepository;
	
	@Autowired
	PeritoRepository peritoRepository;
	
	@Autowired
	ConversionDto conversionDto;
	
	@Override
	public List<Siniestro> buscar(){
		return siniestroRepository.findAll();
	}
	
	@Override
	public ResponseEntity<Siniestro> guardar(SiniestroDto  siniestroDto, int numeroPoliza , int dniPerito) {
		
		try {
			Siniestro siniestro = conversionDto.convertirSiniestroToSiniestroDto(siniestroDto);
			List<Perito> peritoList = peritoRepository.findAll();
			List<Seguro> seguroList = seguroRepository.findAll();
			
			for(Perito  per : peritoList) {
				if(per.getDniPerito() == dniPerito) {
					siniestro.setPerito(per);
				}
			}
			
			for(Seguro seg : seguroList) {
				if(seg.getNumeroPoliza() == numeroPoliza) {
					siniestro.setSeguro(seg);
				}
			}
			
				return new ResponseEntity<>(siniestroRepository.save(siniestro),null,HttpStatus.OK);
		}catch(Exception exp) {
				return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

			
	}
	
	@Override
	public ResponseEntity<Siniestro> guardarSeguroPerito( SiniestroDto siniestroDto) {
		try {
			Siniestro siniestro = conversionDto.convertirSiniestroToSiniestroDto(siniestroDto);
			Seguro seguro = siniestro.getSeguro();
			siniestro.setSeguro(null);
			seguroRepository.save(seguro);
			Perito perito = siniestro.getPerito();
			siniestro.setPerito(null);
			seguroRepository.save(seguro);
			siniestro.setSeguro(seguro);
			siniestro.setPerito(perito);
			return  new ResponseEntity<>(siniestroRepository.save(siniestro),null,HttpStatus.OK);
		}catch(Exception exp) {
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}
	
	

	
	@Override
	public void eliminar(int idSiniestro) {
		Optional<Siniestro> siniestro = siniestroRepository.findById(idSiniestro);
		if(siniestro.isPresent()) {
			siniestroRepository.delete(siniestro.get());
		}
	}
	
	@Override
	public List<Siniestro> buscarPorDniPerito( int dniPerito){
		return siniestroRepository.findByPeritoDniPerito(dniPerito);
	}
	
	@Override
	public List<Siniestro> buscarAceptados( char aceptado){
		return siniestroRepository.queryByAceptadoLike(aceptado);
	}

	@Override
	public Page<Siniestro> buscar(int pagina, int cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		return siniestroRepository.findAll(pageable);
	}
}
