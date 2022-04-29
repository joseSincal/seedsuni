package uni.seed.practica2.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import uni.seed.practica2.repository.ClienteRepository;
import uni.seed.practica2.repository.SeguroRepository;
import uni.seed.practica2.service.CatalogoServicio;
import uni.seed.practica2.ws.ClienteServicioInt;
import uni.seed.practica2.common.ConversionDto;
import uni.seed.practica2.dto.ClienteDto;
import uni.seed.practica2.entity.Cliente;
import uni.seed.practica2.entity.Seguro;

@Component
public class ClienteServicio implements ClienteServicioInt {
	
	@Autowired 
	ClienteRepository clienteRepository;
	
	@Autowired
	SeguroRepository seguroRepository;
	
	@Autowired
	CatalogoServicio catalogoServicio;
	
	@Autowired
	ConversionDto conversionDto;
	
	@Override
	public List<Cliente> buscar(){
		return clienteRepository.findAll();
	}
	
	@Override
	public ResponseEntity<Cliente>  guardar(ClienteDto clienteDto) {
		try {
			Cliente cliente = conversionDto.convertirClienteToClienteDto(clienteDto);
			return new ResponseEntity<>(clienteRepository.save(cliente),null, HttpStatus.ACCEPTED);
			
		}catch(Exception exp) {
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	


	@Override
	public ResponseEntity<Cliente> guardarSeguro(ClienteDto clienteDto) {
		try {
			Cliente cliente = conversionDto.convertirClienteToClienteDto(clienteDto);
			List<Seguro> seguro = cliente.getSeguro();
			cliente.setSeguro(null);
			clienteRepository.save(cliente);
			for(Seguro seg : seguro ) 
				seg.setDniCl(cliente.getDniCl());  
			
			
			seguroRepository.saveAll(seguro);
			cliente.setSeguro(seguro);
			return new ResponseEntity<>(cliente, null, HttpStatus.OK);
		}catch(Exception exp) {
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Override
	public void eliminar( int dniCl) {
		Optional<Cliente> cliente = clienteRepository.findById(dniCl);
		if(cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
		}
	}
	
	@Override
	public List<Cliente> buscarNombreYApellido1( String nombreCl, String apellido1){
		return clienteRepository.findByNombreClAndApellido1(nombreCl, apellido1);
	}
	
	@Override
	public List<Cliente> buscarApellido2OCiudad( String apellido2, String  ciudad){
		return clienteRepository.findByApellido2OrCiudad(apellido2, ciudad);
	}

	@Override
	public Cliente buscarNombreLike(String nombreIniciales) {
		return clienteRepository.findByNombreClStartingWith(nombreIniciales);
	}

	@Override
	public List<Map<String, Object>> buscarClienteSeguro() {
		return catalogoServicio.buscarClienteSeguro();
	}

	@Override
	public ResponseEntity<Integer> updateClienteCodigoPostal(int codPostal, int dniCl) {
		try {
			return new ResponseEntity<>( catalogoServicio.updateClienteCodigoPostal(dniCl,codPostal), null, HttpStatus.OK);
		}catch(Exception exp) {
			return new ResponseEntity<>( null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Cliente> buscarApellido(String apellido) {
		return clienteRepository.findByApellido1OrApellido2(apellido, apellido);
	}

	@Override
	public Page<Cliente> buscar(int pagina, int cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		return clienteRepository.findAll(pageable);
	}

	@Override
	public List<Cliente> buscarCantidadSeguros(int numeroPoliza) {
		return clienteRepository.buscarNumeroPolizasPorCliente(numeroPoliza);
	}

	@Override
	public Long buscarTotalSeguros(int dniCl) {
		return clienteRepository.buscarTotalPolizasCliente(dniCl);
	}

	
}
