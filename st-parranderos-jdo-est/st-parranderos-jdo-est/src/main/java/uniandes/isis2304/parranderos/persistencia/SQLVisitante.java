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

import uniandes.isis2304.parranderos.negocio.Visitante;

/**
 * @author Usuario
 *
 */
public class SQLVisitante 

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
	public SQLVisitante (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VISITANTE a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param identificacion - La identificación de cada visitante del centro comercial
	 * @param nombre - Nombre del visitante
	 * @param tipo - Tipo de visitante
	 * @param correo - Correo del visitante
	 * @param telefonopropio - Telefono del visitante
	 * @param nombreEmergencia - Contacto de emergencia del visitante
	 * @param telefonoEmergencia - Telefono del contacto de emergencia del visitante
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarVisitante(PersistenceManager pm, long identificacion, String nombre, String tipo, String correo,String telefonopropio, String nombreEmergencia, String telefonoEmergencia) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitante() + "(identificador, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia ) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(identificacion, nombre, tipo, correo, telefonopropio, nombreEmergencia, telefonoEmergencia);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VISITANTE de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del visitante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVisitantePorID (PersistenceManager pm, long identificador)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitante() + " WHERE identificador = ?");
        q.setParameters(identificador);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VISITANTE de la base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del visitante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVisitantePorNombre(PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitante() + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de VISITANTE de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del visitante
	 * @return El objeto VISITANTE que tiene el identificador dado
	 */
	public Visitante darVisitantePorId (PersistenceManager pm, long identificador) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitante() + " WHERE identificador = ?");
		q.setResultClass(Visitante.class);
		q.setParameters(identificador);
		return (Visitante) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de VISITANTE de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del visitante
	 * @return El objeto VISITANTE que tiene el nombre ingresado 
	 */
	public Visitante darVisitantePorNombre(PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitante() + " WHERE nombre = ?");
		q.setResultClass(Visitante.class);
		q.setParameters(nombre);
		return (Visitante) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS VISITANTES de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VISITANTE
	 */
	public List<Visitante> darVisitantes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitante ());
		q.setResultClass(Visitante.class);
		return (List<Visitante>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el telefono de emergencia de un visitante en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del visitante
	 * @param  telefono emergencia - El nuevo telefono de emergencia del visitante
	 * @return El número de tuplas modificadas
	 */
	public long cambiarTelefonoEmergencia (PersistenceManager pm, long identificador, String telefonoEmergencia) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaVisitante() + " SET telefonoemergencia = ? WHERE identificador = ?");
	     q.setParameters(telefonoEmergencia, identificador);
	     return (long) q.executeUnique();            
	}
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el contacto de emergencia de un visitante en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del visitante
	 * @param  contactoEmergencia - El nuevo contacto de emergencia del visitante
	 * @return El número de tuplas modificadas
	 */
	public long cambiarContactoEmergencia (PersistenceManager pm, long identificador, String contactoEmergencia) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaVisitante() + " SET nombreemergencia = ? WHERE identificador = ?");
	     q.setParameters(contactoEmergencia, identificador);
	     return (long) q.executeUnique();            
	}


}
