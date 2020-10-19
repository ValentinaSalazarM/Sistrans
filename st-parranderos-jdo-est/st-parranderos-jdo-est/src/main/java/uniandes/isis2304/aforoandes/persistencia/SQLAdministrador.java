/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.Administrador;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Administrador de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
public class SQLAdministrador 
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
	public SQLAdministrador (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Administrador a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param identificacion - El identificador del administrador
	 * @param nombre - El nombre del administrador
	 * @param contrasenia - La contraseña del administrador
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarAdministrador (PersistenceManager pm, String identificacion, String nombre, String contrasenia) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAdministrador() + "(identificacion, nombre, contrasena) values (?, ?, ?)");
        q.setParameters(identificacion, nombre, contrasenia);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de ADMINISTRADOR de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param identificacion - El identificador del administrador
	 * @return El objeto Administrador que tiene el identificador dado
	 */
	public Administrador darAdministradorPorId (PersistenceManager pm, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministrador() + " WHERE identificacion = ?");
		q.setResultClass(Administrador.class);
		q.setParameters(identificacion);
		return (Administrador) q.executeUnique();
	}

}
