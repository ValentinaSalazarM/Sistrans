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

import uniandes.isis2304.parranderos.negocio.Parqueadero;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PARQUEADERO de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLParqueadero
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
	public SQLParqueadero (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PARQUEADERO a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idParqueadero - El identificador del parqueadero
	 * @param capacidadNormal - El identificador de la capacidad normal del parqueadero
	 * @param area - El identificador del área del parqueadero
	 * @param idCentroComercial - El identificador del centro comercial del parqueadero
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarParqueadero (PersistenceManager pm, String idParqueadero, long capacidadNormal, long area, String idCentroComercial) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaParqueadero () + "(identificador, capacidadNormal, area, idcentrocomercial) values (?, ?, ?, ?)");
        q.setParameters(idParqueadero, capacidadNormal, area, idCentroComercial);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN PARQUEADERO de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idParqueadero - El identificador del parqueadero
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarParqueaderoPorId (PersistenceManager pm, String idParqueadero)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaParqueadero () + " WHERE identificador = ?");
        q.setParameters(idParqueadero);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PARQUEADERO de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idParqueadero - El identificador del parqueadero
	 * @return El objeto PARQUEADERO que tiene el identificador dado
	 */
	public Parqueadero darParqueaderoPorId (PersistenceManager pm, String idParqueadero) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaParqueadero () + " WHERE identificador = ?");
		q.setResultClass(Parqueadero.class);
		q.setParameters(idParqueadero);
		return (Parqueadero) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PARQUEADEROS de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PARQUEADEROS
	 */
	public List<Parqueadero> darParqueaderos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaParqueadero ());
		q.setResultClass(Parqueadero.class);
		return (List<Parqueadero>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el área de un parqueadero en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idLocal- identificador del parqueadero
	 * @param area - Identificador de la nueva área de un parqueadero
	 * @return El número de tuplas modificadas
	 */
	public long cambiarAreaParqueadero (PersistenceManager pm, String idParqueadero, long area) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaLocalComercial () + " SET area = ? WHERE identificador = ?");
		q.setParameters(area, idParqueadero);
		return (long) q.executeUnique();            
	}

}
