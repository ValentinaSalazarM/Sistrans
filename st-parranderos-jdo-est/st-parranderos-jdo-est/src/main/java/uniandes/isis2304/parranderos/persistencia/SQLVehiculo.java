/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Vehiculo;


/**
 * @author Usuario
 *
 */
public class SQLVehiculo 

{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAforoAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAforoAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLVehiculo(PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	

/**
 * Crea y ejecuta la sentencia SQL para adicionar un VEHICULO a la base de datos de AforoAndes
 * @param pm -  El manejador de persistencia
 * @param placa - La placa con la que se identifica el vehículo
 * @param caracteristicas - Las característias del vehículo
 * @param dueño - El dueño del vehículo
 * @return Las tuplas insertadas
 */
	public long adicionarVehiculo(PersistenceManager pm, String placa, String caracteristicas, String dueño) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVehiculo() + "(placa, caracteristicas, propietario) values (?, ?, ?)");
        q.setParameters(placa, caracteristicas, dueño);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VEHICULO de la base de datos de AforoAndes, por su placa
	 * @param pm - El manejador de persistencia
	 * @param placa- La placa del vehículo a eliminar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVehiculoPorPlaca(PersistenceManager pm, String placa)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVehiculo() + " WHERE placa = ?");
        q.setParameters(placa);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VEHICULO de la base de datos de AforoAndes, por su dueño
	 * @param pm - El manejador de persistencia
	 * @param dueno- El dueño del vehículo a eliminar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVehiculoPorDueño(PersistenceManager pm, String dueño)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVehiculo() + " WHERE propietario = ?");
        q.setParameters(dueño);
        return (long) q.executeUnique();            
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un VEHICULO de la 
	 * base de datos de AforoAndes, por su placa
	 * @param pm - El manejador de persistencia
	 * @param placa - La placa del vehiculo
	 * @return El objeto VEHICULO que tiene la placa dada 
	 */
	public Vehiculo darVehiculoPorPlaca (PersistenceManager pm, String placa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVehiculo() + " WHERE placa = ?");
		q.setResultClass(Vehiculo.class);
		q.setParameters(placa);
		return (Vehiculo) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un VEHICULO de la 
	 * base de datos de AforoAndes, por su dueño
	 * @param pm - El manejador de persistencia
	 * @param propietario -El dueño del vehiculo
	 * @return Una lista de objetos VEHICULOS que pertenecen al dueño ingresado
	 */
	public List<Vehiculo> darVehiculosPorDueño (PersistenceManager pm, String propietario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVehiculo() + " WHERE propietario = ?");
		q.setResultClass(Vehiculo.class);
		q.setParameters(propietario);
		return (List<Vehiculo>) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS VEHICULOS de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VEHICULOS
	 */
	public List<Vehiculo> darVehiculos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVehiculo ());
		q.setResultClass(Vehiculo.class);
		return (List<Vehiculo>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar las características de un vehículo en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param placa - La placa del vehículo
	 * @param  caracteristicas - Las nuevas caracteristicas del vehículo
	 * @return El número de tuplas modificadas
	 */
	public long cambiarCaracteristicas (PersistenceManager pm, String placa , String caracteristicas) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaVehiculo() + " SET caracteristicas = ? WHERE placa = ?");
	     q.setParameters(caracteristicas, placa);
	     return (long) q.executeUnique();            
	}


	

}
