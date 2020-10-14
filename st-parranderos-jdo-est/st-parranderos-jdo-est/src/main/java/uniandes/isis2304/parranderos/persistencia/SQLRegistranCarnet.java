/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.RegistranCarnet;


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
	public long adicionarRegistranCarnet (PersistenceManager pm, String idlector, long tipoCarnet, String idvisitante, Date fecha, long horaentrada, long horasalida ) 
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
	public long eliminarRegistranCarnet (PersistenceManager pm, String idlector, long tipoCarnet, String idvisitante, Date fecha, long horaentrada, long horasalida ) 
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
	 * base de datos de AforoAndes, por su idvisitante en una fecha o rango de fechas
	 * @param pm - El manejador de persistencia
	 * @param idvisitante - El id del visitante al que pertenece el carnet
	 * @return El objeto REGISTRANVISITANTE con el id del visitante dado
	 */
	public RegistranCarnet darResistranCarnetPorIdVisitanteFecha (PersistenceManager pm,String idvisitante, Date fechaInicio, Date fechaFin) 
	{
		Query q;
		if ( fechaFin != null )
		{
			q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE idvisitante = ? AND fecha BETWEEN ? AND ? ORDER BY fecha");
			q.setParameters(idvisitante, fechaInicio, fechaFin );
		}
		else
		{
			q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE idvisitante = ? AND fecha = fechaInicio ");
			q.setParameters(idvisitante, fechaInicio, fechaFin );
		}
		q.setResultClass(RegistranCarnet.class);
		return (RegistranCarnet) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN REGISTRANCARNET de la 
	 * base de datos de AforoAndes, por su idvisitante en una hora o rango de horas
	 * @param pm - El manejador de persistencia
	 * @param idvisitante - El id del visitante al que pertenece el carnet
	 * @return El objeto REGISTRANVISITANTE con el id del visitante dado
	 */
	public RegistranCarnet darResistranCarnetPorIdVisitanteHora (PersistenceManager pm, String idvisitante, Date fecha, long horaEntradaInicio, long horaEntradaFin) 
	{
//		Query q;
//		if ( fechaFin != null )
//		{
//			q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE idvisitante = ? AND fecha BETWEEN ? AND ? ORDER BY fecha");
//			q.setParameters(idvisitante, fechaInicio, fechaFin );
//		}
//		else
//		{
//			q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE idvisitante = ? AND fecha = fechaInicio ");
//			q.setParameters(idvisitante, fechaInicio, fechaFin );
//		}
//		q.setResultClass(RegistranCarnet.class);
//		return (RegistranCarnet) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANCARNET de la 
	 * base de datos de AforoAndes, por su hora de ingreso
	 * @param pm - El manejador de persistencia
	 * @param horaentrada - La hora de ingreso del visitante
	 * @return Una lista de objetos REGISTRANCARNET
	 */
	public List<RegistranCarnet> darRegistranCarnetPorHoraEntrada (PersistenceManager pm, long horaentrada) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE horaentrada = ?");
		q.setResultClass(RegistranCarnet.class);
		q.setParameters(horaentrada);
		return (List<RegistranCarnet>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANCARNET de la 
	 * base de datos de AforoAndes, por su hora de salida
	 * @param pm - El manejador de persistencia
	 * @param horasalida - La hora de salida 
	 * @return Una lista de objetos REGISTRANCARNET
	 */
	public List<RegistranCarnet> darRegistranCarnetPorHoraSalida (PersistenceManager pm,long horasalida) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRegistranCarnet () + " WHERE horasalida = ?");
		q.setResultClass(RegistranCarnet.class);
		q.setParameters(horasalida);
		return (List<RegistranCarnet>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS REGISTRANCARNET de la 
	 * base de datos de AforoAndes, por su fecha
	 * @param pm - El manejador de persistencia
	 * @param fecha - La fecha del registro
	 * @return Una lista de objetos REGISTRANCARNET
	 */
	public List<RegistranCarnet> darRegistranCarnetPorFecha(PersistenceManager pm, Date fecha) 
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
	
	

}
