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

import uniandes.isis2304.parranderos.negocio.Baño;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAÑO de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLBaño 
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
	public SQLBaño (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAÑO a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idBaño - El identificador del baño
	 * @param capacidadNormal - El identificador de la capacidad normal del baño
	 * @param area - El identificador del área del baño
	 * @param numeroSanitarios - El número de sanitarios del baño
	 * @param idCentroComercial - El identificador del centro comercial del baño
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarBaño (PersistenceManager pm, String idBaño, long capacidadNormal, long area, int numeroSanitarios, String idCentroComercial) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBaño () + "(identificador, capacidadNormal, area, sanitarios, idcentrocomercial) values (?, ?, ?, ?, ?)");
        q.setParameters(idBaño, capacidadNormal, area, numeroSanitarios, idCentroComercial);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAÑO de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBaño - El identificador del baño
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarBañoPorId (PersistenceManager pm, String idBaño)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBaño () + " WHERE identificador = ?");
        q.setParameters(idBaño);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAÑO de la base de datos de AforoAndes, por su número de sanitarios
	 * @param pm - El manejador de persistencia
	 * @param sanitarios - El número de sanitarios del baño
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarBañoPorSanitarios (PersistenceManager pm, int sanitarios)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBaño () + " WHERE sanitarios = ?");
        q.setParameters(sanitarios);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAÑO de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBaño - El identificador del baño
	 * @return El objeto BAÑO que tiene el identificador dado
	 */
	public Baño darBañoPorId (PersistenceManager pm, String idBaño) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBaño () + " WHERE identificador = ?");
		q.setResultClass(Baño.class);
		q.setParameters(idBaño);
		return (Baño) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BAÑOS de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroSanitarios - El número de sanitarios de baño buscado
	 * @return Una lista de objetos BAÑO que tienen el número de sanitarios dado
	 */
	public List<Baño> darBañosPorSanitarios (PersistenceManager pm, int numeroSanitarios) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBaño () + " WHERE sanitarios = ?");
		q.setResultClass(Baño.class);
		q.setParameters(numeroSanitarios);
		return (List<Baño>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BAÑOS de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAÑO
	 */
	public List<Baño> darBaños (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBaño ());
		q.setResultClass(Baño.class);
		return (List<Baño>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el número de sanitarios de un baño en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idBaño - El identificador del baño
	 * @param numeroSanitarios - El nuevo número de sanitarios del baño
	 * @return El número de tuplas modificadas
	 */
	public long cambiarNumeroSanitariosBaño (PersistenceManager pm, String idBaño, int numeroSanitarios) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaBaño () + " SET sanitarios = ? WHERE identificador = ?");
	     q.setParameters(numeroSanitarios, idBaño);
	     return (long) q.executeUnique();            
	}


}
