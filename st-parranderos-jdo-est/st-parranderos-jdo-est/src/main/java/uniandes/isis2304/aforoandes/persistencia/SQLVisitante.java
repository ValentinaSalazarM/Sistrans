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

import uniandes.isis2304.aforoandes.negocio.Visitante;

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
	public long adicionarVisitante(PersistenceManager pm, String identificacion, String nombre, long tipo, String correo,String telefonopropio, String nombreEmergencia, String telefonoEmergencia) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitante() + "(identificacion, nombre, tipo, correo, telefonopropio, nombreemergencia, telefonoemergencia ) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(identificacion, nombre, tipo, correo, telefonopropio, nombreEmergencia, telefonoEmergencia);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VISITANTE de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param identificador - El identificador del visitante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVisitantePorID (PersistenceManager pm, String identificador)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitante() + " WHERE identificacion = ?");
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
	public Visitante darVisitantePorId (PersistenceManager pm, String identificador) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitante() + " WHERE identificacion = ?");
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
	public long cambiarTelefonoEmergencia (PersistenceManager pm, String identificador, String telefonoEmergencia) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaVisitante() + " SET telefonoemergencia = ? WHERE identificacion = ?");
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
	public long cambiarContactoEmergencia (PersistenceManager pm, String identificador, String contactoEmergencia) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaVisitante() + " SET nombreemergencia = ? WHERE identificacion = ?");
	     q.setParameters(contactoEmergencia, identificador);
	     return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar el horario de circulación de un visitante
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo de objetos cuyos elementos corresponden a los datos del visitante, las horas y minutos de habilitados para circulación.
	 */
	public Object[] darHorariosVisitante ( PersistenceManager pm, String idVisitante )
	{
		Query q = pm.newQuery(SQL, "SELECT AUX1.*, AUX2.HORALIMITE, AUX2.MINUTOLIMITE " + 
				"	FROM " + 
				"(" + 
				"    SELECT VISITANTE.IDENTIFICACION, HORARIO.HORA AS HORAINICIO, HORARIO.MINUTO AS MINUTOINICIO " + 
				"    FROM VISITANTE " + 
				"    JOIN TIPOVISITANTE " + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID " + 
				"    JOIN HORARIO " + 
				"    ON TIPOVISITANTE.HORAINICIO = HORARIO.ID " + 
				"    WHERE VISITANTE.IDENTIFICACION = ?" + 
				") AUX1 " + 
				"	JOIN " + 
				"(" + 
				"    SELECT VISITANTE.IDENTIFICACION, HORARIO.HORA AS HORALIMITE, HORARIO.MINUTO AS MINUTOLIMITE " + 
				"    FROM VISITANTE " + 
				"    JOIN TIPOVISITANTE " + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID " + 
				"    JOIN HORARIO " + 
				"    ON TIPOVISITANTE.HORALIMITE = HORARIO.ID " + 
				") AUX2 " + 
				" ON AUX1.IDENTIFICACION = AUX2.IDENTIFICACION " + 
				"");
		q.setParameters(idVisitante);
		return (Object[]) q.executeUnique();            
	}
}
