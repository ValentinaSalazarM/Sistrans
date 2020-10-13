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

import uniandes.isis2304.parranderos.negocio.VisitanBaño;

/**
 * @author Usuario
 *
 */
public class SQLVisitanBaño 

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
	public SQLVisitanBaño(PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VISITANBAÑO a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idBaño - El identificador del baño
	 * @param idvisitante - El identificador del visitante
	 * @return Las tuplas insertadas
	 */
	public long adicionarVisitanBaño (PersistenceManager pm, String idBaño, String idvisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitanBaño()+ "(idbaño, idvisitante) values (?, ?)");
        q.setParameters(idBaño, idvisitante);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de VISITANBAÑOL de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VISITANBAÑO
	 */
	public List<VisitanBaño> darVisitanBaño(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitanBaño());
		q.setResultClass(VisitanBaño.class);
		return (List<VisitanBaño>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VISITANBAÑO de la base de datos, por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param idBaño- El identificador del baño
	 * @param idVisitante - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVisitanLocal (PersistenceManager pm, String idBaño, String idVisitante) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitanBaño() + " WHERE idbaño = ? AND idvisitante = ?");
        q.setParameters(idBaño, idVisitante);
        return (long) q.executeUnique();
	}

}
