/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.ZonaCirculacion;

/**
 * @author Usuario
 *
 */
public class SQLZonaCirculacion 

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
	public SQLZonaCirculacion (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
/**
 * Crea y ejecuta la sentencia SQL para adicionar un BAÑO a la base de datos de AforoAndes
 * @param pm - El manejador de persistencia
 * @param identificador - Identificador de la zona de circulación
 * @param capacidadNormal - Capacidad normal de la zona de circulación
 * @param idCentroComercial - El identificador del centro comercial al que pertenece la zona de circulación 
 * @return
 */
	public long adicionarZonaCirculacion (PersistenceManager pm, String identificador, int capacidadNormal, String idCentroComercial) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaZonaCirculacion() + "(identificador, capacidadNormal, idCentroComercial) values (?, ?, ?)");
        q.setParameters(identificador, capacidadNormal, idCentroComercial);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA ZONA DE CIRCULACIÓN de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la zona
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarZonaCirculacionPorId (PersistenceManager pm, String id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaZonaCirculacion() + " WHERE identificador = ?");
        q.setParameters(id);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA ZONA DE CIRCULACION de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador de la zona
	 * @return El objeto ZONA DE CIRCULACION que tiene el identificador dado
	 */
	public ZonaCirculacion darZonaPorId (PersistenceManager pm, String identificador) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaZonaCirculacion()+ " WHERE identificador = ?");
		q.setResultClass(ZonaCirculacion.class);
		q.setParameters(identificador);
		return (ZonaCirculacion) q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de las ZONAS DE CIRCULACION de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ZONA CIRCULACION
	 */
	public List<ZonaCirculacion> darZonas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaZonaCirculacion ());
		q.setResultClass(ZonaCirculacion.class);
		return (List<ZonaCirculacion>) q.executeList();
	}
	
}
