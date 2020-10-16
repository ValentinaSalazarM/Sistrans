/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: AforoAndes Uniandes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.Area;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Area de AFORO-CCANDES
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLArea 
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
	public SQLArea (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un AREA a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idArea - El identificador del area
	 * @param valor - El valor del área
	 * @param aforo - El aforo del área
	 * @return El número de tuplas insertadas
	 */
	public long adicionarArea (PersistenceManager pm, long idArea, double valor, int aforo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaArea () + "(id, valor, aforo) values (?, ?, ?)");
        q.setParameters(idArea, valor, aforo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar AREAS de la base de datos de AforoAndes, por su valor
	 * @param pm - El manejador de persistencia
	 * @param valorArea - El valor del area
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAreasPorValor (PersistenceManager pm, double valorArea)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaArea () + " WHERE valor = ?");
        q.setParameters(valorArea);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN AREA de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idArea - El identificador del area
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAreaPorId (PersistenceManager pm, long idArea)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaArea () + " WHERE id = ?");
        q.setParameters(idArea);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN AREA de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idArea - El identificador del area
	 * @return El objeto AREA que tiene el identificador dado
	 */
	public Area darAreaPorId (PersistenceManager pm, long idArea) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaArea () + " WHERE id = ?");
		q.setResultClass(Area.class);
		q.setParameters(idArea);
		return (Area) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS AREAS de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param valorArea - El valor de area buscado
	 * @return El objeto AREA que tiene el valor dado
	 */
	public Area darAreaPorValor (PersistenceManager pm, double valorArea) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaArea () + " WHERE valor = ?");
		q.setResultClass(Area.class);
		q.setParameters(valorArea);
		return (Area) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS AREAS de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos AREA
	 */
	public List<Area> darAreas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaArea ());
		q.setResultClass(Area.class);
		return (List<Area>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el valor del área en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El id del área
	 * @param valor - El nuevo valor del área
	 * @return El número de tuplas modificadas
	 */
	public long cambiarValorArea(PersistenceManager pm, long id, double valor) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaArea () + " SET valor = ? WHERE id = ?");
	     q.setParameters(valor, id);
	     return (long) q.executeUnique();            
	}
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el aforo del área en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El id del área
	 * @param valor - El nuevo aforo dependiendo del área
	 * @return El número de tuplas modificadas
	 */
	public long cambiarAforoArea (PersistenceManager pm, long id, int aforo) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaArea () + " SET aforo = ? WHERE id = ?");
	     q.setParameters(aforo, id);
	     return (long) q.executeUnique();            
	}

}
