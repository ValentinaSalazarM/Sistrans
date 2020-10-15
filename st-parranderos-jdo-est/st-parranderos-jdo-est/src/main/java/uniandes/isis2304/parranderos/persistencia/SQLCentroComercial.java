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

import uniandes.isis2304.aforoandes.negocio.CentroComercial;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CENTRO COMERCIAL de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLCentroComercial 
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
	public SQLCentroComercial (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un CENTRO COMERCIAL a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idCentroComercial - El identificador del centro comercial
	 * @param nombre - El nombre del centro comercial
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarCentroComercial (PersistenceManager pm, String idCentroComercial, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCentroComercial () + "(id, nombre) values (?, ?)");
        q.setParameters(idCentroComercial, nombre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN CENTRO COMERCIAL de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCentroComercial - El identificador del centro comercial
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCentroComercialPorId (PersistenceManager pm, String idCentroComercial)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCentroComercial () + " WHERE id = ?");
        q.setParameters(idCentroComercial);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un CENTRO COMERCIAL de la base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del centro comercial
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCentroComercialPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCentroComercial () + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN CENTRO COMERCIAL de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCentroComercial - El identificador del centro comercial
	 * @return El objeto CENTRO COMERCIAL que tiene el identificador dado
	 */
	public CentroComercial darCentroComercialPorId (PersistenceManager pm, String idCentroComercial) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCentroComercial () + " WHERE id = ?");
		q.setResultClass(CentroComercial.class);
		q.setParameters(idCentroComercial);
		return (CentroComercial) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CENTRO COMERCIALES de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del centro comercial buscado
	 * @return Una lista de objetos CENTRO COMERCIAL que tienen el nombre dado
	 */
	public List<CentroComercial> darCentrosComercialesPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCentroComercial () + " WHERE nombre = ?");
		q.setResultClass(CentroComercial.class);
		q.setParameters(nombre);
		return (List<CentroComercial>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CENTRO COMERCIALES de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CENTRO COMERCIAL
	 */
	public List<CentroComercial> darCentrosComerciales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCentroComercial ());
		q.setResultClass(CentroComercial.class);
		return (List<CentroComercial>) q.executeList();
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el nombre de un centro comercial en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idCentroComercial - El identificador del centro comercial 
	 * @param nombre - El nuevo nombre del Centro Comercial
	 * @return El número de tuplas modificadas
	 */
	public long cambiarNombreCentroComercial (PersistenceManager pm, String idCentroComercial, String nombre) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCentroComercial () + " SET nombre = ? WHERE idCentroComercial = ?");
	     q.setParameters(nombre, idCentroComercial);
	     return (long) q.executeUnique();            
	}
	

}
