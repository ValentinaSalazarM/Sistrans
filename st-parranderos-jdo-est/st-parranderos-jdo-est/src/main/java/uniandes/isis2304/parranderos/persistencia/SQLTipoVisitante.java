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

import uniandes.isis2304.parranderos.negocio.TipoVisitante;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TIPO DE VISITANTE de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
public class SQLTipoVisitante 
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
	public SQLTipoVisitante (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un TipoVisitante a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idTipoVisitante - El identificador del tipo de visitante
	 * @param tipo - El nombre del tipo de visitante
	 * @param horalimite - Hora máxima de visita en el centro comercial
	 * @param horaInicio - Hora inicio del visitante en el centro comercial de acuerdo a su tipo
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarTipoVisitante (PersistenceManager pm, long idTipoVisitante, String tipo, long horaLimite, long horainicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoVisitante() + "(id, tipo, horainicio, horalimite) values (?, ?, ?, ?)");
        q.setParameters(idTipoVisitante, tipo, horainicio, horaLimite);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE VISITANTE de la base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoVisitante - El nombre del tipo de visitante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoVisitantePorNombre (PersistenceManager pm, String nombreTipoVisitante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoVisitante () + " WHERE tipo = ?");
        q.setParameters(nombreTipoVisitante);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE VISITANTE de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoVisitante - El identificador del tipo de visitante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoVisitantePorId (PersistenceManager pm, long idTipoVisitante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoVisitante () + " WHERE id = ?");
        q.setParameters(idTipoVisitante);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE VISITANTE de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoVisitante - El identificador del tipo de visitante
	 * @return El objeto TipoVisitante que tiene el identificador dado
	 */
	public TipoVisitante darTipoVisitantePorId (PersistenceManager pm, long idTipoVisitante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoVisitante  () + " WHERE id = ?");
		q.setResultClass(TipoVisitante.class);
		q.setParameters(idTipoVisitante);
		return (TipoVisitante) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE VISITANTE de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoVisitante - El nombre del tipo de visitante
	 * @return El objeto TipoVisitante que tiene el tipo dado
	 */
	public List<TipoVisitante> darTiposVisitantePorNombre (PersistenceManager pm, String nombreTipoVisitante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoVisitante  () + " WHERE tipo = ?");
		q.setResultClass(TipoVisitante.class);
		q.setParameters(nombreTipoVisitante);
		return (List<TipoVisitante>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE VISITANTE de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TipoVisitante
	 */
	public List<TipoVisitante> darTiposVisitante (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoVisitante  ());
		q.setResultClass(TipoVisitante.class);
		return (List<TipoVisitante>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la hora de inicio de un TIPOVISITANTE en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del tipo
	 * @param  horainicio - La nueva hora habilitada para el ingreso al centro comercial 
	 * @return El número de tuplas modificadas
	 */
	public long cambiarHoraInicio (PersistenceManager pm, long identificador, long horainicio) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaTipoVisitante() + " SET horainicio = ? WHERE id = ?");
	     q.setParameters(horainicio, identificador);
	     return (long) q.executeUnique();            
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la hora limite de un TIPOVISITANTE en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del tipo
	 * @param  horalimite - La nueva hora habilitada para la salida del centro comercial
	 * @return El número de tuplas modificadas
	 */
	public long cambiarHoraLimite (PersistenceManager pm, long identificador, long horalimite) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaTipoVisitante() + " SET horalimite = ? WHERE id = ?");
	     q.setParameters(horalimite, identificador);
	     return (long) q.executeUnique();            
	}
}
