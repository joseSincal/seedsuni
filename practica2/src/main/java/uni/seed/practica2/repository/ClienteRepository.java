package uni.seed.practica2.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.seed.practica2.entity.Cliente;

@Repository("clienteRepository")
public interface ClienteRepository  extends JpaRepository<Cliente, Serializable>{
	
	public Cliente findByDniCl(int dniCl);

	public List<Cliente> findByNombreClAndApellido1(String nombreCl, String apellido1);
	
	public List<Cliente> findByApellido2OrCiudad(String apellido2, String ciudad);
	
	public Cliente findByNombreClStartingWith(String nombreIniciales);
	
	public List<Cliente> findByApellido1OrApellido2(String apellido1, String apellido2);
	

	@Query(value="SELECT *  FROM CLIENTE c  JOIN SEGURO s "
			+ "ON c.DNI_CL = s.DNI_CL "
			+ "WHERE c.APELLIDO_2  LIKE 'M%' "
			+ "AND s.NUMERO_POLIZA = :numeroPoliza "
			+ "ORDER BY c.NOMBRE_CL  ASC", nativeQuery=true)
	public List<Cliente> buscarNumeroPolizasPorCliente(int numeroPoliza);
	
	@Query("SELECT COUNT(s.numeroPoliza) FROM  Cliente c JOIN Seguro s " 
			+"ON s.dniCl = c.dniCl "
			+"WHERE c.dniCl = :dniCl "
			+ "GROUP BY c.nombreCl , c.apellido1")
	public Long buscarTotalPolizasCliente(int dniCl);
	
}
