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

import uniandes.isis2304.parranderos.negocio.VisitanAscensor;


/**
 * @author Usuario
 *
 */
public class SQLVisitanAscensor 
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
	public SQLVisitanAscensor(PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VISITANASCENSOR a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idAscensor - El identificador del ascesnor 
	 * @param idvisitante - El identificador del visitante
	 * @return Las tuplas insertadas
	 */
	public long adicionarVisitanAscensor (PersistenceManager pm, long idAscensor, long idvisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitanAscensor()+ "(idascensor, idvisitante) values (?, ?)");
        q.setParameters(idAscensor, idvisitante);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de VISITANASCENSOR de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VISITANASCESNOR
	 */
	public List<VisitanAscensor> darVisitanAscensor(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitanAscensor());
		q.setResultClass(VisitanAscensor.class);
		return (List<VisitanAscensor>) q.executeList();
	}

}
