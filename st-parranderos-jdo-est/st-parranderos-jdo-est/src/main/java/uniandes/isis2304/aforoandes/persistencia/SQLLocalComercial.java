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

import uniandes.isis2304.aforoandes.negocio.LocalComercial;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto LOCAL COMERCIAL de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLLocalComercial 
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
	public SQLLocalComercial (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un LOCALCOMERCIAL la base de datos de AforoAndes
	 * @param pm  - El manejador de persistencia
	 * @param idLocalComercial - El identificador del local
	 * @param capacidadNormal - La capacidad común del local comercial
	 * @param area - El área del local comercial
	 * @param tipoLocal - El tipo del local comercial
	 * @param activo - Si está en funcionamiento o no (es un booleano representado con 0 o 1)
	 * @param idCentroComercial - El identificador del centro comercial al que pertenece el local comercial
	 * @return El número de tuplas insertadas
	 */
	public long adicionarLocalComercial (PersistenceManager pm, String idLocalComercial, long capacidadNormal, long area, long tipoLocal, int activo, String idCentroComercial) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLocalComercial () + "(identificador, capacidadNormal, area, tipoLocal, activo, idcentrocomercial) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idLocalComercial, capacidadNormal, area, tipoLocal, activo, idCentroComercial);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar LOCAL COMERCIAL de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del local
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarLocalComercialPorId (PersistenceManager pm, String identificador ) 
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLocalComercial() + " WHERE identificador = ?");
		q.setParameters(identificador);
		return (long) q.executeUnique();  
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar LOCAL COMERCIAL de la base de datos de AforoAndes, por su actividad
	 * @param pm - El manejador de persistencia
	 * @param activo - El estado del local
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarLocalComercialPorActividad (PersistenceManager pm, int activo ) 
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLocalComercial() + " WHERE activo = ?");
		q.setParameters(activo);
		return (long) q.executeUnique();  
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN LOCAL COMERCIAL de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del local comercial
	 * @return El objeto LOCAL COMERCIAL que tiene el identificador dado
	 */
	public LocalComercial darLocalComercialPorId (PersistenceManager pm, String identificador) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLocalComercial () + " WHERE identificador = ?");
		q.setResultClass(LocalComercial.class);
		q.setParameters(identificador);
		return (LocalComercial) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS LOCALES COMERCIALES de la 
	 * base de datos de AforoAndes, por su idCentroComercial
	 * @param pm - El manejador de persistencia
	 * @param idCentroComercial - El identificador del centro comercial en el que se encuentra el local
	 * @return Una lista de objetos LOCAL COMERCIAL que pertenecen a un centro comercial
	 */
	public List<LocalComercial> darLocalesComercialesPorIDCC (PersistenceManager pm, String idCentroComercial) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLocalComercial() + " WHERE idcentrocomercial = ?");
		q.setResultClass(LocalComercial.class);
		q.setParameters(idCentroComercial);
		return (List<LocalComercial>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS LOCAL COMERCIALES de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos LOCAL COMERCIAL
	 */
	public List<LocalComercial> darLocalesComerciales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLocalComercial ());
		q.setResultClass(LocalComercial.class);
		return (List<LocalComercial>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el estado de actividad de un local comercial en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idLocal- identificador del local
	 * @param activo - Nuevo estado de actividad del local comercial(1 si está activo o 0 de lo contrario)
	 * @return El número de tuplas modificadas
	 */
	public long cambiarActividadLocalComercial (PersistenceManager pm, String idLocal, int activo) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaLocalComercial () + " SET activo = ? WHERE identificador = ?");
		q.setParameters(activo, idLocal);
		return (long) q.executeUnique();            
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el área de un local comercial en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idLocal- identificador del local
	 * @param area - Identificador de la nueva área de un local comercial
	 * @return El número de tuplas modificadas
	 */
	public long cambiarAreaLocalComercial (PersistenceManager pm, String idLocal, long area) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaLocalComercial () + " SET area = ? WHERE identificador = ?");
		q.setParameters(area, idLocal);
		return (long) q.executeUnique();            
	}



}
