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
	public long adicionarParqueadero (PersistenceManager pm, long idParqueadero, long capacidadNormal, long area, long idCentroComercial) 
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
	public long eliminarParqueaderoPorId (PersistenceManager pm, long idParqueadero)
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
	public Parqueadero darParqueaderoPorId (PersistenceManager pm, long idParqueadero) 
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
	 * @return Una lista de objetos PARQUEADERO
	 */
	public List<Parqueadero> darParqueaderos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaParqueadero ());
		q.setResultClass(Parqueadero.class);
		return (List<Parqueadero>) q.executeList();
	}


}
