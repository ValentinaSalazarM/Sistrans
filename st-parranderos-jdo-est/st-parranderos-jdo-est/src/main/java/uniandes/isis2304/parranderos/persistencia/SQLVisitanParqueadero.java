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

import uniandes.isis2304.parranderos.negocio.VisitanParqueadero;

/**
 * @author Usuario
 *
 */
public class SQLVisitanParqueadero
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
	public SQLVisitanParqueadero(PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VISITANPARQUEADERO a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idParqueadero - El identificador del parqueadero
	 * @param idvisitante - El identificador del visitante
	 * @return Las tuplas insertadas
	 */
	public long adicionarVisitanParqueadero (PersistenceManager pm, String idParqueadero, String idvisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitanParqueadero()+ "(idparqueadero, idvisitante) values (?, ?)");
        q.setParameters(idParqueadero, idvisitante);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de VISITANPARQUEADERO de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VISITANPARQUEADERO
	 */
	public List<VisitanParqueadero> darVisitanParqueadero(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitanParqueadero());
		q.setResultClass(VisitanParqueadero.class);
		return (List<VisitanParqueadero>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VISITANPARQUEADERO de la base de datos, por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param idParqueadero- El identificador del parqueadero
	 * @param idVisitante - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVisitanParqueadero (PersistenceManager pm, String idParqueadero, String idVisitante) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitanParqueadero () + " WHERE idparqueadero = ? AND idvisitante = ?");
        q.setParameters(idParqueadero, idVisitante);
        return (long) q.executeUnique();
	}


}
