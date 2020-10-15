/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: AforoAndes Uniandes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.CapacidadNormal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CapacidadNormal de AFORO-CCANDES
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLCapacidadNormal 
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
	public SQLCapacidadNormal (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una CAPACIDADNORMAL a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idCapacidadNormal - El identificador de la capacidad normal
	 * @param valor - El valor de la capacidad normal
	 * @param aforo - El aforo de la capacidad normal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarCapacidadNormal (PersistenceManager pm, long idCapacidadNormal, double valor, int aforo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCapacidadNormal () + "(id, valor, aforo) values (?, ?, ?)");
        q.setParameters(idCapacidadNormal, valor, aforo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CAPACIDADNORMALES de la base de datos de AforoAndes, por su valor
	 * @param pm - El manejador de persistencia
	 * @param valorCapacidadNormal - El valor de la capacidad normal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCapacidadNormalesPorValor (PersistenceManager pm, double valorCapacidadNormal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCapacidadNormal () + " WHERE valor = ?");
        q.setParameters(valorCapacidadNormal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA CAPACIDAD NORMAL de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCapacidadNormal - El identificador de la capacidad normal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCapacidadNormalPorId (PersistenceManager pm, long idCapacidadNormal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCapacidadNormal () + " WHERE id = ?");
        q.setParameters(idCapacidadNormal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA CAPACIDAD NORMAL de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCapacidadNormal - El identificador de la capacidad normal
	 * @return El objeto CAPACIDADNORMAL que tiene el identificador dado
	 */
	public CapacidadNormal darCapacidadNormalPorId (PersistenceManager pm, long idCapacidadNormal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCapacidadNormal () + " WHERE id = ?");
		q.setResultClass(CapacidadNormal.class);
		q.setParameters(idCapacidadNormal);
		return (CapacidadNormal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS CAPACIDADNORMALES de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param valorCapacidadNormal - El valor de area buscado
	 * @return Una lista de objetos CAPACIDADNORMAL que tienen el nombre dado
	 */
	public List<CapacidadNormal> darCapacidadesNormalesPorValor (PersistenceManager pm, double valorCapacidadNormal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCapacidadNormal () + " WHERE valor = ?");
		q.setResultClass(CapacidadNormal.class);
		q.setParameters(valorCapacidadNormal);
		return (List<CapacidadNormal>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS CAPACIDADNORMALES de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CAPACIDADNORMAL
	 */
	public List<CapacidadNormal> darCapacidadesNormales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCapacidadNormal ());
		q.setResultClass(CapacidadNormal.class);
		return (List<CapacidadNormal>) q.executeList();
	}
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el valor de la capacidad normal en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El id Capacidad Normal
	 * @param valor - El nuevo valor de la capacidad
	 * @return El número de tuplas modificadas
	 */
	public long cambiarValorCapacidad (PersistenceManager pm, long id, double valor) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCapacidadNormal () + " SET valor = ? WHERE id = ?");
	     q.setParameters(valor, id);
	     return (long) q.executeUnique();            
	}
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el aforo de la capacidad normal en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El id Capacidad Normal
	 * @param valor - El nuevo aforo de la capacidad
	 * @return El número de tuplas modificadas
	 */
	public long cambiarAforoCapacidad (PersistenceManager pm, long id, int aforo) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCapacidadNormal () + " SET aforo = ? WHERE id = ?");
	     q.setParameters(aforo, id);
	     return (long) q.executeUnique();            
	}

}
