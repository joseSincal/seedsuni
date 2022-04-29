package uni.seed.practica2.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ClienteDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer dniCl;
	private String nombreCl;
	private String  apellido1;
	private String apellido2;
	private String claseVia;
	private String nombreVia;
	private Integer numeroVia;
	private Integer codPostal;
	private String ciudad;
	private Integer telefono;
	private String observaciones;
	private List<SeguroDto> seguro;
	
	
}