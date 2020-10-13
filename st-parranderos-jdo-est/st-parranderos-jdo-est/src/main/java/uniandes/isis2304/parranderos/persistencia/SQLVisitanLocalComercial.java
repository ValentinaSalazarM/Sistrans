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

import uniandes.isis2304.parranderos.negocio.VisitanLocalComercial;


/**
 * @author Usuario
 *
 */
public class SQLVisitanLocalComercial 
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
	public SQLVisitanLocalComercial(PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VISITANLOCALCOMERCIAL a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idLocal - El identificador del local
	 * @param idvisitante - El identificador del visitante
	 * @return Las tuplas insertadas
	 */
	public long adicionarVisitanLocal (PersistenceManager pm, long idLocal, long idvisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitanLocalComercial()+ "(idlocalcomercial, idvisitante) values (?, ?)");
        q.setParameters(idLocal, idvisitante);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de VISITANLOCALCOMERCIAL de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VISITANLOCALCOMERCIAL
	 */
	public List<VisitanLocalComercial> darVisitanLocal(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitanLocalComercial());
		q.setResultClass(VisitanLocalComercial.class);
		return (List<VisitanLocalComercial>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VISITANLOCALCOMERCIAL de la base de datos, por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param idLocal- El identificador del parqueadero
	 * @param idVisitante - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVisitanLocal (PersistenceManager pm, long idLocal, long idVisitante) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitanParqueadero () + " WHERE idlocalcomercial = ? AND idvisitante = ?");
        q.setParameters(idLocal, idVisitante);
        return (long) q.executeUnique();
	}

}
