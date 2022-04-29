package uni.seed.practica2.common;



import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import uni.seed.practica2.dto.ClienteDto;
import uni.seed.practica2.dto.CompaniaDto;
import uni.seed.practica2.dto.CompaniaSeguroDto;
import uni.seed.practica2.dto.PeritoDto;
import uni.seed.practica2.dto.SeguroDto;
import uni.seed.practica2.dto.SiniestroDto;
import uni.seed.practica2.entity.Cliente;
import uni.seed.practica2.entity.Compania;
import uni.seed.practica2.entity.CompaniaSeguro;
import uni.seed.practica2.entity.Perito;
import uni.seed.practica2.entity.Seguro;
import uni.seed.practica2.entity.Siniestro;

@Component
public class ConversionDto {
	
	
	private static final ModelMapper MP= new ModelMapper();

	public  Cliente convertirClienteToClienteDto(ClienteDto clienteDto) {
		return MP.map(clienteDto, Cliente.class);
	}
	

	
	public Seguro converitSeguroToSeguroDto(SeguroDto seguroDto) {
		return MP.map(seguroDto, Seguro.class);

	}
	
	public CompaniaSeguro convertirCompaniaSeguroToDtoVersion(CompaniaSeguroDto companiaSeguroDto) {
		return MP.map(companiaSeguroDto, CompaniaSeguro.class);
	}
	
	public Compania convertirCompaniaToCompaniaDto(CompaniaDto companiaDto) {
		return MP.map(companiaDto,Compania.class);
	}
	
	

	
	public Siniestro convertirSiniestroToSiniestroDto(SiniestroDto siniestroDto) {
		
		return MP.map(siniestroDto, Siniestro.class);
	}
	
	public Perito convertirPeritoDtoToPerito(PeritoDto peritoDto) {
		return MP.map(peritoDto, Perito.class);
	}

	
}
