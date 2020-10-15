/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: AforoAndes Uniandes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.Carnet;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Carnet de AFORO-CCANDES
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLCarnet 
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
	public SQLCarnet (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una CARNET a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @param idVisitante - El identificador del visitante del carnet
	 * @return El número de tuplas insertadas
	 */
	public long adicionarCarnet (PersistenceManager pm, long tipoCarnet, String idVisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarnet () + "(tipoCarnet, idVisitante) values (?, ?)");
        q.setParameters(tipoCarnet, idVisitante);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CARNETS de la base de datos de AforoAndes, por su tipo
	 * @param pm - El manejador de persistencia
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCarnetsPorTipo (PersistenceManager pm, long tipoCarnet)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarnet () + " WHERE tipoCarnet = ?");
        q.setParameters(tipoCarnet);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN CARNET de la base de datos de AforoAndes, por su tipo e identificador
	 * @param pm - El manejador de persistencia
	 * @param idVisitante - El identificador del visitante del carnet
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCarnetPorIdVisitante (PersistenceManager pm, String idVisitante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarnet () + " WHERE idVisitante = ?");
        q.setParameters(idVisitante);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CARNETS de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @return Una lista de objetos CARNET que tienen el tipo dado
	 */
	public List<Carnet> darCarnetsPorTipo (PersistenceManager pm, long tipoCarnet) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarnet () + " WHERE tipoCarnet = ?");
		q.setResultClass(Carnet.class);
		q.setParameters(tipoCarnet);
		return (List<Carnet>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN CARNET de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idVisitante - El identificador del dueño del carnet
	 * @return El objeto CARNET que tiene el identificador de visitante dado
	 */
	public Carnet darCarnetPorIdVisitante (PersistenceManager pm, String idVisitante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarnet () + " WHERE idVisitante = ?");
		q.setResultClass(Carnet.class);
		q.setParameters(idVisitante);
		return (Carnet) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CARNETS de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CARNET
	 */
	public List<Carnet> darCarnets (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarnet ());
		q.setResultClass(Carnet.class);
		return (List<Carnet>) q.executeList();
	}

}
