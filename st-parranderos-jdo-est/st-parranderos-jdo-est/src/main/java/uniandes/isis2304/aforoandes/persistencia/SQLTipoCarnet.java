/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.TipoCarnet;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TIPO DE CARNET de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
public class SQLTipoCarnet 
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
	public SQLTipoCarnet (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un TIPOCARNET a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idTipoCarnet - El identificador del tipo de carnet
	 * @param tipo - El nombre del tipo de carnet
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarTipoCarnet (PersistenceManager pm, long idTipoCarnet, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoCarnet() + "(id, tipo) values (?, ?)");
        q.setParameters(idTipoCarnet, tipo);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE CARNET de la base de datos de AforoAndes, por su tipo
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoCarnet - El nombre del tipo de carnet
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoCarnetPorTipo (PersistenceManager pm, String nombreTipoCarnet)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoCarnet () + " WHERE tipo = ?");
        q.setParameters(nombreTipoCarnet);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE CARNET de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoCarnet - El identificador del tipo de carnet
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoCarnetPorId (PersistenceManager pm, long idTipoCarnet)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoCarnet () + " WHERE id = ?");
        q.setParameters(idTipoCarnet);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE CARNET de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoCarnet - El identificador del tipo de carnet
	 * @return El objeto TIPOCARNET que tiene el identificador dado
	 */
	public TipoCarnet darTipoCarnetPorId (PersistenceManager pm, long idTipoCarnet) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoCarnet  () + " WHERE id = ?");
		q.setResultClass(TipoCarnet.class);
		q.setParameters(idTipoCarnet);
		return (TipoCarnet) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE CARNET de la 
	 * base de datos de AforoAndes, por su tipo
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoCarnet - El nombre del tipo de carnet
	 * @return El objeto TIPOCARNET que tiene el tipo dado
	 */
	public TipoCarnet darTipoCarnetPorTipo (PersistenceManager pm, String nombreTipoCarnet) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoCarnet  () + " WHERE tipo = ?");
		q.setResultClass(TipoCarnet.class);
		q.setParameters(nombreTipoCarnet);
		return (TipoCarnet) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE CARNET de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TIPOCARNET
	 */
	public List<TipoCarnet> darTiposCarnet (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoCarnet  ());
		q.setResultClass(TipoCarnet.class);
		return (List<TipoCarnet>) q.executeList();
	}

}
