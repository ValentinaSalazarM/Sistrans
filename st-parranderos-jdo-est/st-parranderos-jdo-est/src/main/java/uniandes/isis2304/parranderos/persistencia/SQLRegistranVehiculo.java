/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.RegistranVehiculo;



/**
 * @author Usuario
 *
 */
public class SQLRegistranVehiculo 
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
	public SQLRegistranVehiculo (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}


	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un RegistranVehiculo a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idlector - El identificador del lector por el cual ingresa el vehículo
	 * @param vehiculo - El vehículo registrado
	 * @param horaentrada - Hora de entrada del vehículo al centro comercial
	 * @param horasalida - Hora de salida del vehículo del centro comercial 
	 * @return El número de tuplas insertadas
	 */
	public long adicionarRegistranVehiculo (PersistenceManager pm, long idlector, String vehiculo, Timestamp horaentrada, Timestamp horasalida ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRegistranVehiculo() + "(idlector, vehiculo, horaentrada, horasalida) values (?, ?, ?, ?)");
        q.setParameters(idlector, vehiculo, horaentrada, horasalida);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN REGISTRANVEHICULO de la base de datos por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param idlector - El identificador del lector por el cual ingresa el vehículo
	 * @param  vehiculo - La placa del vehiculo registrado 
	 * @param horaentrada - Hora de entrada del vehículo al centro comercial
	 * @param horasalida - Hora de salida del vehículo del centro comercial 
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarRegistranVehiculo (PersistenceManager pm, long idlector, String vehiculo, Timestamp horaentrada, Timestamp horasalida) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRegistranVehiculo() + " WHERE idlector = ? AND vehiculo = ? AND horaentrada = ? AND horasalida = ?");
        q.setParameters(idlector, vehiculo, horaentrada, horasalida);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes, por su vehiculo
	 * @param pm - El manejador de persistencia
	 * @param vehiculo -La placa del carro registrado 
	 * @return El objeto REGISTRANVEHICULO que tiene la placa dada 
	 */
	public RegistranVehiculo darResgitranPorPlaca (PersistenceManager pm, String vehiculo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo () + " WHERE vehiculo = ?");
		q.setResultClass(RegistranVehiculo.class);
		q.setParameters(vehiculo);
		return (RegistranVehiculo) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes, por su lector
	 * @param pm - El manejador de persistencia
	 * @param lectorid - El id del lector que registró el vehículo 
	 * @return Una lista de objetos REGISTRANVEHICULO
	 */
	public List<RegistranVehiculo> darRegistranVehiculosPorLector (PersistenceManager pm, long idLector) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo () + " WHERE idlector = ?");
		q.setResultClass(RegistranVehiculo.class);
		q.setParameters(idLector);
		return (List<RegistranVehiculo>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes, por su hora de ingreso
	 * @param pm - El manejador de persistencia
	 * @param horaentrada - La hora de ingreso del vehículo al centro comercial
	 * @return Una lista de objetos REGISTRANVEHICULO
	 */
	public List<RegistranVehiculo> darRegistranVehiculosPorHoraEntrada (PersistenceManager pm, Timestamp horaentrada) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo () + " WHERE horaentrada = ?");
		q.setResultClass(RegistranVehiculo.class);
		q.setParameters(horaentrada);
		return (List<RegistranVehiculo>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes, por su hora de salida
	 * @param pm - El manejador de persistencia
	 * @param horasalida - La hora de salida del centro comercial
	 * @return Una lista de objetos REGISTRANVEHICULO
	 */
	public List<RegistranVehiculo> darRegistranVehiculosPorHoraSalida (PersistenceManager pm, Timestamp horasalida) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo () + " WHERE horasalida = ?");
		q.setResultClass(RegistranVehiculo.class);
		q.setParameters(horasalida);
		return (List<RegistranVehiculo>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos REGISTRANVEHICULO
	 */
	public List<RegistranVehiculo> darRegistranVehiculo(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo());
		q.setResultClass(RegistranVehiculo.class);
		return (List<RegistranVehiculo>) q.executeList();
	}
}
