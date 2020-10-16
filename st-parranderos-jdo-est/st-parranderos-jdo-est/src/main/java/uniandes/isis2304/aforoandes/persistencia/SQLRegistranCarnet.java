/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.RegistranCarnet;


/**
 * @author Usuario
 *
 */
public class SQLRegistranCarnet 

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
	public SQLRegistranCarnet (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}


	/**
	 * Crea y ejecuta la sentencia SQL para añadir UN REGISTRANCARNET a la base de datos 
	 * @param pm - El manejejador de persistencia
	 * @param idlector - El id del lector 
	 * @param tipoCarnet - El tipo del carnet del visitante
	 * @param idvisitante - El identificador del visitante
	 * @param fecha - La fecha de ingreso
	 * @param horaentrada - La hora de ingreso
	 * @param horasalida - La hora de salida 
	 * @return Las tuplas insertadas 
	 */
	public long adicionarRegistranCarnet (PersistenceManager pm, String idlector, long tipoCarnet, String idvisitante, Timestamp fecha, long horaentrada, long horasalida ) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRegistranCarnet() + "(idlector, tipocarnet, idvisitante, fecha, horaentrada, horasalida) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idlector, tipoCarnet, idvisitante, fecha, horaentrada, horasalida);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN REGISTRANCARNET de la base de datos por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param idlector - El identificador del lector por el cual ingresa el visitante
	 * @param tipoCarnet - El tipo del carnet del visitante
	 * @param idvisitante - El identificador del visitante
	 * @param fecha - La fecha de ingreso
	 * @param horaentrada - Hora de entrada 
	 * @param horasalida - Hora de salida 
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarRegistranCarnet (PersistenceManager pm, String idlector, long tipoCarnet, String idvisitante, Timestamp fecha, long horaentrada, long horasalida ) 
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRegistranCarnet() + " WHERE idlector = ? AND tipocarnet = ? AND idvisitante = ? AND fecha =  AND horaentrada = ? AND horasalida = ?");
		q.setParameters(idlector, tipoCarnet, idvisitante, fecha, horaentrada, horasalida);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANCARNET de la 
	 * base de datos de AforoAndes, por su lector
	 * @param pm - El manejador de persistencia
	 * @param lectorid - El id del lector que registró el visitante
	 * @return Una lista de objetos REGISTRANCARNET
	 */
	public List<RegistranCarnet> darRegistranCarnetPorLector (PersistenceManager pm, String idLector) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE idlector LIKE '%" + idLector + "'");
		q.setResultClass(RegistranCarnet.class);
		return (List<RegistranCarnet>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN REGISTRANCARNET de la 
	 * base de datos de AforoAndes, por su idvisitante
	 * @param pm - El manejador de persistencia
	 * @param idvisitante - El id del visitante al que pertenece el carnet
	 * @return Una lista de objetos REGISTRANCARNET con el id del visitante dado
	 */
	public List<RegistranCarnet> darResistranCarnetPorIdVisitante (PersistenceManager pm, String idvisitante) 
	{
		Query q;
		q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE idvisitante = ? AND fecha = fechaInicio ");
		q.setParameters(idvisitante );
		q.setResultClass(RegistranCarnet.class);
		return (List<RegistranCarnet>) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN REGISTRANCARNET de la 
	 * base de datos de AforoAndes, por su idvisitante en una fecha o rango de fechas
	 * @param pm - El manejador de persistencia
	 * @param idVisitante - El id del visitante al que pertenece el carnet
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta o null si solo se requiere una fecha
	 * @return Una lista de objetos REGISTRANCARNET con el id del visitante dado y en la fecha o rango dados
	 */
	public List<RegistranCarnet> darResistranCarnetPorIdVisitanteFecha (PersistenceManager pm,String idVisitante, Timestamp fechaInicio, Timestamp fechaFin) 
	{
		Query q;
		if ( fechaFin != null )
		{
			q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE idvisitante = ? AND fecha BETWEEN ? AND ? ORDER BY fecha");
			q.setParameters(idVisitante, fechaInicio, fechaFin );
		}
		else
		{
			q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE idvisitante = ? AND fecha = ?");
			q.setParameters(idVisitante, fechaInicio );
		}
		q.setResultClass(RegistranCarnet.class);
		return (List<RegistranCarnet>) q.executeUnique();
	}
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANCARNET de la 
	 * base de datos de AforoAndes, por su fecha
	 * @param pm - El manejador de persistencia
	 * @param fecha - La fecha del registro
	 * @return Una lista de objetos REGISTRANCARNET
	 */
	public List<RegistranCarnet> darRegistranCarnetPorFecha(PersistenceManager pm, Timestamp fecha) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE fecha = ?");
		q.setResultClass(RegistranCarnet.class);
		q.setParameters(fecha);
		return (List<RegistranCarnet>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de REGISTRANCARNET de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos REGISTRANCARNET
	 */
	public List<RegistranCarnet> darRegistranCarnet (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet());
		q.setResultClass(RegistranCarnet.class);
		return (List<RegistranCarnet>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la hora de salida de un registro de carnet en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idLector - Lector donde se realizó el registro de la visita
	 * @param idVisitante - Identificador del visitante 
	 * @param fecha - La fecha en la que se realizó el registro
	 * @param horaEntrada - La hora de entrada de la visita
	 * @param horaSalida - La hora de salida de la visita
	 * @return El número de tuplas modificadas
	 */
	public long cambiarHoraSalidaRegistranCarnet (PersistenceManager pm, String idLector, String idVisitante, Timestamp fecha, long horaEntrada, long horaSalida) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaRegistranCarnet () + " SET horaSalida = ? WHERE idLector = ? AND idVisitante = ? AND fecha = ? AND horaEntrada = ?");
		q.setParameters(horaSalida, idLector, idVisitante, fecha, horaEntrada);
		return (long) q.executeUnique();            
	}
}
