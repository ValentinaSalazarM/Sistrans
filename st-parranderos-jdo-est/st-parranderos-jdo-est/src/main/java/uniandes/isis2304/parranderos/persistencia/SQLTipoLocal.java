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

import uniandes.isis2304.parranderos.negocio.TipoLocal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TIPO DE LOCAL de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
public class SQLTipoLocal 
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
	public SQLTipoLocal (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un TIPOLOCAL a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idTipoLocal - El identificador del tipo de local
	 * @param tipo - El nombre del tipo de local
	 * @param horaApertura - Hora de apertura del tipo de local
	 * @param horaCierre - Hora de cierre del tipo de local
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarTipoLocal (PersistenceManager pm, long idTipoLocal,Timestamp horaApertura, Timestamp horaCierre, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoLocal() + "(id, tipo, horaApertura, horaCierre) values (?, ?, ?, ?)");
        q.setParameters(idTipoLocal, tipo, horaApertura,horaCierre);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE LOCAL de la base de datos de AforoAndes, por su tipo
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoLocal - El nombre del tipo de local
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoLocalPorNombre (PersistenceManager pm, String nombreTipoLocal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoLocal () + " WHERE tipo = ?");
        q.setParameters(nombreTipoLocal);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TIPOS DE LOCAL de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoLocal - El identificador del tipo de local
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTipoLocalPorId (PersistenceManager pm, long idTipoLocal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoLocal () + " WHERE id = ?");
        q.setParameters(idTipoLocal);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE LOCAL de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTipoLocal - El identificador del tipo de local
	 * @return El objeto TIPOLOCAL que tiene el identificador dado
	 */
	public TipoLocal darTipoLocalPorId (PersistenceManager pm, long idTipoLocal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoLocal  () + " WHERE id = ?");
		q.setResultClass(TipoLocal.class);
		q.setParameters(idTipoLocal);
		return (TipoLocal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE LOCAL de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreTipoLocal - El nombre del tipo de local
	 * @return El objeto TIPOLOCAL que tiene el tipo dado
	 */
	public List<TipoLocal> darTiposLocalPorNombre (PersistenceManager pm, String nombreTipoLocal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoLocal  () + " WHERE tipo = ?");
		q.setResultClass(TipoLocal.class);
		q.setParameters(nombreTipoLocal);
		return (List<TipoLocal>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE LOCAL de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TIPOLOCAL
	 */
	public List<TipoLocal> darTiposLocal (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoLocal  ());
		q.setResultClass(TipoLocal.class);
		return (List<TipoLocal>) q.executeList();
	}
	

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el tipo de un TIPOLOCAL en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del tipo
	 * @param  tipo - El nuevo tipo del tipo local
	 * @return El número de tuplas modificadas
	 */
	public long cambiarTipoLocal (PersistenceManager pm, long identificador, String tipo) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaTipoLocal() + " SET tipo = ? WHERE id = ?");
	     q.setParameters(tipo, identificador);
	     return (long) q.executeUnique();            
	}



	
}
