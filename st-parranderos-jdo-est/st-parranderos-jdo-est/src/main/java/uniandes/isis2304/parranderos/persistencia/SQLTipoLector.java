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

import uniandes.isis2304.parranderos.negocio.TipoLector;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TIPO DE LECTOR de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
public class SQLTipoLector 
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
	public SQLTipoLector (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un TipoLector a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idTipoLector - El identificador del tipo de lector
	 * @param tipo - El nombre del tipo de lector
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarTipoLector (PersistenceManager pm, long idTipoLector, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoLector() + "(id, tipo) values (?, ?)");
        q.setParameters(idTipoLector, tipo);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE LECTOR de la base de datos de AforoAndes, por su tipo
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoLector - El nombre del tipo de lector
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoLectorPorTipo (PersistenceManager pm, String nombreTipoLector)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoLector () + " WHERE tipo = ?");
        q.setParameters(nombreTipoLector);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE LECTOR de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoLector - El identificador del tipo de lector
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoLectorPorId (PersistenceManager pm, long idTipoLector)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoLector () + " WHERE id = ?");
        q.setParameters(idTipoLector);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE LECTOR de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoLector - El identificador del tipo de lector
	 * @return El objeto TipoLector que tiene el identificador dado
	 */
	public TipoLector darTipoLectorPorId (PersistenceManager pm, long idTipoLector) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoLector  () + " WHERE id = ?");
		q.setResultClass(TipoLector.class);
		q.setParameters(idTipoLector);
		return (TipoLector) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE LECTOR de la 
	 * base de datos de AforoAndes, por su tipo
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoLector - El nombre del tipo de lector
	 * @return El objeto TipoLector que tiene el tipo dado
	 */
	public List<TipoLector> darTiposLectorPorTipo (PersistenceManager pm, String nombreTipoLector) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoLector  () + " WHERE tipo = ?");
		q.setResultClass(TipoLector.class);
		q.setParameters(nombreTipoLector);
		return (List<TipoLector>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE LECTOR de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TipoLector
	 */
	public List<TipoLector> darTiposLector (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoLector  ());
		q.setResultClass(TipoLector.class);
		return (List<TipoLector>) q.executeList();
	}

}
