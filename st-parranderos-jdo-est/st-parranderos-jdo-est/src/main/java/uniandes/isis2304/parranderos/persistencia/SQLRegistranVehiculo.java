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

import uniandes.isis2304.aforoandes.negocio.RegistranCarnet;
import uniandes.isis2304.aforoandes.negocio.RegistranVehiculo;



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
	 * @param fecha - Fecha de registro del vehículo
	 * @param horaentrada - Hora de entrada del vehículo al centro comercial
	 * @param horasalida - Hora de salida del vehículo del centro comercial 
	 * @return El número de tuplas insertadas
	 */
	public long adicionarRegistranVehiculo (PersistenceManager pm, String idlector, String vehiculo, Timestamp fecha, long horaentrada, long horasalida ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRegistranVehiculo() + "(idlector, vehiculo, fecha, horaentrada, horasalida) values (?, ?, ?, ?, ?)");
        q.setParameters(idlector, vehiculo, fecha, horaentrada, horasalida);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN REGISTRANVEHICULO de la base de datos por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param idLector - El identificador del lector por el cual ingresa el vehículo
	 * @param  vehiculo - La placa del vehiculo registrado 
	 * @param fecha - Fecha de registro del vehículo
	 * @param horaEntrada - Hora de entrada del vehículo al centro comercial
	 * @param horaSalida - Hora de salida del vehículo del centro comercial 
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarRegistranVehiculo (PersistenceManager pm, String idLector, String vehiculo, Timestamp fecha, long horaEntrada, long horaSalida) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRegistranVehiculo() + " WHERE idlector = ? AND vehiculo = ? AND fecha = ? AND horaentrada = ? AND horasalida = ?");
        q.setParameters(idLector, vehiculo, fecha, horaEntrada, horaSalida);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes, por su lector
	 * @param pm - El manejador de persistencia
	 * @param lectorid - El id del lector que registró el vehículo 
	 * @return Una lista de objetos REGISTRANVEHICULO
	 */
	public List<RegistranVehiculo> darRegistranVehiculosPorLector (PersistenceManager pm, String idLector) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo () + " WHERE idlector = ?");
		q.setResultClass(RegistranVehiculo.class);
		q.setParameters(idLector);
		return (List<RegistranVehiculo>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes, por su vehiculo
	 * @param pm - El manejador de persistencia
	 * @param vehiculo - La placa del carro registrado 
	 * @return Una lista de objetos REGISTRANVEHICULO con la placa dada
	 */
	public List<RegistranVehiculo> darRegistranVehiculoPorPlaca (PersistenceManager pm, String vehiculo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo () + " WHERE vehiculo = ?");
		q.setResultClass(RegistranVehiculo.class);
		q.setParameters(vehiculo);
		return (List<RegistranVehiculo>) q.executeUnique();
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes, por su fecha
	 * @param pm - El manejador de persistencia
	 * @param fecha - La fecha del registro
	 * @return Una lista de objetos REGISTRANVEHICULO
	 */
	public List<RegistranVehiculo> darRegistranVehiculoPorFecha(PersistenceManager pm, Timestamp fecha) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo () + " WHERE fecha = ?");
		q.setResultClass(RegistranVehiculo.class);
		q.setParameters(fecha);
		return (List<RegistranVehiculo>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN REGISTRANVEHICULO de la 
	 * base de datos de AforoAndes, por su vehiculo
	 * @param pm - El manejador de persistencia
	 * @param vehiculo - La placa del carro registrado 
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta o null si solo se requiere una fecha
	 * @return Una lista de objetos REGISTRANVEHICULO con la placa del vehículo dado y en la fecha o rango dados
	 */
	public List<RegistranVehiculo> darRegistranVehiculoPorPlacaFecha (PersistenceManager pm, String vehiculo, Timestamp fechaInicio, Timestamp fechaFin) 
	{
		Query q;
		if ( fechaFin != null )
		{
			q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo() + " WHERE vehiculo = ? AND fecha BETWEEN ? AND ? ORDER BY fecha");
			q.setParameters(vehiculo, fechaInicio, fechaFin );
		}
		else
		{
			q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranVehiculo () + " WHERE vehiculo = ? AND fecha = ?");
			q.setParameters(vehiculo, fechaInicio );
		}
		q.setResultClass(RegistranCarnet.class);
		return (List<RegistranVehiculo>) q.executeUnique();	}
	
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
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la hora de salida de un registro de vehículo en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idLector - Lector donde se realizó el registro de la visita
	 * @param vehiculo - La placa del vehículo registrado 
	 * @param fecha - La fecha en la que se realizó el registro
	 * @param horaEntrada - La hora de entrada de la visita
	 * @param horaSalida - La hora de salida de la visita
	 * @return El número de tuplas modificadas
	 */
	public long cambiarHoraSalidaRegistranVehiculo (PersistenceManager pm, String idLector, String vehiculo, Timestamp fecha, long horaEntrada, long horaSalida) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaRegistranVehiculo () + " SET horaSalida = ? WHERE idLector = ? AND vehiculo = ? AND fecha = ? AND horaEntrada = ?");
		q.setParameters(horaSalida, idLector, vehiculo, fecha, horaEntrada);
		return (long) q.executeUnique();            
	}
}
