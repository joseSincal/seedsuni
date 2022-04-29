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
import uni.seed.practica2.dto.CompaniaDto;
import uni.seed.practica2.entity.Compania;
import uni.seed.practica2.repository.CompaniaRepository;
import uni.seed.practica2.repository.SeguroRepository;
import uni.seed.practica2.ws.CompaniaServicioInt;

@Component
public class CompaniaServicio implements CompaniaServicioInt {

	@Autowired
	CompaniaRepository companiaRepository;
	
	@Autowired
	SeguroRepository seguroRepository;
	
	@Autowired
	ConversionDto conversionDto;
	
	@Override
	public List<Compania> buscar(){
		return companiaRepository.findAll();
	}
	
	@Override
	public ResponseEntity<Compania> guardar(CompaniaDto companiaDto) {
		try {
			Compania compania = conversionDto.convertirCompaniaToCompaniaDto(companiaDto);
			return new ResponseEntity<>(companiaRepository.save(compania),null, HttpStatus.OK);
		}catch(Exception exp) {
			
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

	@Override
	public void eliminar(String nombreCompania) {
		Optional<Compania> compania = companiaRepository.findById(nombreCompania);
		if(compania.isPresent()) {
			companiaRepository.delete(compania.get());
		}
	}
	
	@Override
	public List<Compania> buscarPorId(String nombreVia){
		return companiaRepository.findByNombreViaOrderByNombreCompaniaDesc(nombreVia);
	}
	
	
	@Override
	public List<Compania> buscarContieneNombre(String referencia){
		return companiaRepository.queryByNombreCompaniaContaining(referencia);
	}

	@Override
	public Page<Compania> buscar(int pagina, int cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		return companiaRepository.findAll(pageable);
	}
}
