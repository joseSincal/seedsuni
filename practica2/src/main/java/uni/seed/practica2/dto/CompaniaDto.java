package uni.seed.practica2.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CompaniaDto  implements Serializable{

	private static final long serialVersionUID = 3086923943921838017L;
	
	private String nombreCompania;
	private String claseVia;
	private String nombreVia;
	private Integer numeroVia;
	private Integer codPostal;
	private Integer telefonoContratacion;
	private Integer telefonoSiniestros;
	private String notas;
	private List<CompaniaSeguroDto> companiaSeguro;


}
