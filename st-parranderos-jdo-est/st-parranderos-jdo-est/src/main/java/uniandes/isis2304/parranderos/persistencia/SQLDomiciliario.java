/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: AforoAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Domiciliario;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto DOMICILIARIO de AFORO-CCANDES
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLDomiciliario 
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
	public SQLDomiciliario (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un DOMICILIARIO a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idVisitante - El identificador del visitante
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return Las tuplas insertadas
	 */
	public long adicionarDomiciliario (PersistenceManager pm, String idVisitante, String empresaDomicilios) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaDomiciliario() + "(idvisitante, empresaDomicilios) values (?, ?)");
        q.setParameters(idVisitante, empresaDomicilios);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar los DOMICILIARIOS de la base de datos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idVisitante - El identificador del domiciliario
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarDomiciliarioPorId(PersistenceManager pm, String idVisitante) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDomiciliario() + " WHERE idvisitante = ?");
        q.setParameters(idVisitante);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar los DOMICILIARIOS de la base de datos, por su empresa
	 * @param pm - El manejador de persistencia
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarDomiciliarioPorEmpresa(PersistenceManager pm, String empresaDomicilios) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDomiciliario() + " WHERE empresaDomicilios = ?");
        q.setParameters(empresaDomicilios);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un DOMICILIARIO de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idDomiciliario - El identificador del Domiciliario
	 * @return El objeto DOMICILIARIO 
	 */
	public Domiciliario darDomiciliarioPorId(PersistenceManager pm, String idDomiciliario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaDomiciliario() + " WHERE idvisitante = ?");
		q.setResultClass(Domiciliario.class);
		q.setParameters(idDomiciliario);
		return (Domiciliario) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un DOMICILIARIO de la 
	 * base de datos de AforoAndes, por su empresa
	 * @param pm - El manejador de persistencia
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return Una lista de objetos DOMICILIARIOS
	 */
	public List<Domiciliario> darDomiciliariosPorEmpresa(PersistenceManager pm, String empresaDomicilios) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaDomiciliario() + " WHERE empresaDomicilios = ?");
		q.setResultClass(Domiciliario.class);
		q.setParameters(empresaDomicilios);
		return (List<Domiciliario>) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS DOMICILIARIOS de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos DOMICILIARIOS
	 */
	public List<Domiciliario> darDomiciliarios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaDomiciliario());
		q.setResultClass(Domiciliario.class);
		return (List<Domiciliario>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la compañia de un domiciliario en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del domiciliario 
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return El número de tuplas modificadas
	 */
	public long cambiarEmpresaDomiciliario(PersistenceManager pm, String idDomiciliario, String empresaDomicilios) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaDomiciliario() + " SET empresaDomicilios = ? WHERE idvisitante = ?");
	     q.setParameters(empresaDomicilios, idDomiciliario);
	     return (long) q.executeUnique();            
	}


}
