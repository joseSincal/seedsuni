package uni.seed.practica2.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6849620175672527106L;
	
	private Integer idUser;
	private String email;
	private String username;
	private String password;

}
