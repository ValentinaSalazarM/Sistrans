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

import uniandes.isis2304.parranderos.negocio.VisitanCentroComercial;


/**
 * @author Usuario
 *
 */
public class SQLVisitanCentroComercial 

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
	public SQLVisitanCentroComercial(PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VISITANCENTROCOMERCIAL a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idCC - El identificador del centro comercial
	 * @param idvisitante - El identificador del visitante
	 * @return Las tuplas insertadas
	 */
	public long adicionarVisitanCC (PersistenceManager pm, String idCC, String idvisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitanCentroComercial()+ "(idcentrocomercial, idvisitante) values (?, ?)");
        q.setParameters(idCC, idvisitante);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de VISITANCENTROCOMERCIAL de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VISITANCENTROCOMERCIAL
	 */
	public List<VisitanCentroComercial> darVisitanCC(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitanCentroComercial());
		q.setResultClass(VisitanCentroComercial.class);
		return (List<VisitanCentroComercial>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VISITANCENTROCOMERCIAL de la base de datos, por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param idCC- El identificador del centro comercial
	 * @param idVisitante - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVisitanCC (PersistenceManager pm, String idCC, String idVisitante) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitanCentroComercial () + " WHERE idcentrocomercial = ? AND idvisitante = ?");
        q.setParameters(idCC, idVisitante);
        return (long) q.executeUnique();
	}
}
