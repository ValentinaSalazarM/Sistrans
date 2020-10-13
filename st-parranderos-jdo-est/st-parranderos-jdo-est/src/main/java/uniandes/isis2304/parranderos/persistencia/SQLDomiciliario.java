/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
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
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto VISITAN de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
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
	 * @param idvisitante - El identificador del visitante
	 * @param compañia - La compañía del domiciliario
	 * @return Las tuplas insertadas
	 */
	public long adicionarDomiciliario (PersistenceManager pm, long idvisitante, String compañia) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaDomiciliario() + "(idvisitante, compañia) values (?, ?)");
        q.setParameters(idvisitante, compañia);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar los DOMICILIARIOS de la base de datos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idDomiciliario - El identificador del domiciliario
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarDomiciliarioPorID(PersistenceManager pm, long idDomiciliario) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDomiciliario() + " WHERE idvisitante = ?");
        q.setParameters(idDomiciliario);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un DOMICILIARIO de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del Domiciliario
	 * @return El objeto DOMICILIARIO 
	 */
	public Domiciliario darDomiciliarioPorID(PersistenceManager pm,long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaDomiciliario() + " WHERE idvisitante = ?");
		q.setResultClass(Domiciliario.class);
		q.setParameters(id);
		return (Domiciliario) q.executeUnique();
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
	 * @param  compañia - La compañia del domiciliario
	 * @return El número de tuplas modificadas
	 */
	public long cambiarCompañia(PersistenceManager pm, long id , String compañia) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaDomiciliario() + " SET compañia = ? WHERE idvisitante = ?");
	     q.setParameters(compañia, id);
	     return (long) q.executeUnique();            
	}


}
