package uni.seed.practica2.impl;

import java.util.ArrayList;
import java.util.Date;
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
import uni.seed.practica2.dto.SeguroDto;
import uni.seed.practica2.entity.Seguro;
import uni.seed.practica2.repository.SeguroRepository;
import uni.seed.practica2.ws.SeguroServicioInt;

@Component
public class SeguroServicio implements SeguroServicioInt {

	@Autowired
	SeguroRepository seguroRepository;
	
	@Autowired
	ConversionDto conversionDto;
	
	
	@Override
	public List<Seguro> buscar(){
		return seguroRepository.findAll();
	}
	
	@Override
	public ResponseEntity<Seguro> guardar(SeguroDto seguroDto) {
		
		try {
			Seguro seguro = conversionDto.converitSeguroToSeguroDto(seguroDto);
			return  new ResponseEntity<>(seguroRepository.save(seguro),null,HttpStatus.OK);
		}catch(Exception exp) {
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@Override
	public void eliminar(int numeroPoliza) {
		Optional<Seguro>  seguro = seguroRepository.findById(numeroPoliza);
		if(seguro.isPresent()) {
			seguroRepository.delete(seguro.get());
		}
	}
	
	@Override
	public List<Seguro> buscarPorFechaDespues(Date fechaInicio){
		return seguroRepository.findByFechaInicioAfter(fechaInicio);
	}
	
	@Override
	public List<Seguro> buscarFechaiInicio(Date fechaInicio){
		return seguroRepository.findByFechaInicio(fechaInicio);
	}
	
	
	@Override
	public List<Seguro> buscarPorCompaniaAsc(int numeroPoliza){
		return seguroRepository.findByNumeroPoliza(numeroPoliza);
	}

	@Override
	public Page<Seguro> buscar(int pagina, int cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		return seguroRepository.findAll(pageable);
	}

	/**
	 *
	 */
	@Override
	public List<Seguro> buscar(int dniCliente) {
		List<Seguro> seguros = new ArrayList<>();
		for(Seguro seguro : seguroRepository.findAll()) {
			if(seguro.getDniCl() == dniCliente) {
				seguros.add(seguro);
			}
		}
		return seguros;
	}

	
}
