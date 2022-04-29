package uni.seed.practica2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USUARIO")
@Data
public class Usuario implements Serializable {

	private static final long serialVersionUID = 7981750739136972923L;
	
	@Id
	@Column(name = "ID_USER")
	private Integer idUser;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	


}
