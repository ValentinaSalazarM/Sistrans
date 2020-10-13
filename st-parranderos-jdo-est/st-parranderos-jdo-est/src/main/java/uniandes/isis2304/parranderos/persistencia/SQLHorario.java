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
import uniandes.isis2304.parranderos.negocio.Horario;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Area de AFORO-CCANDES
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLHorario 
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
	public SQLHorario (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
/**
 * Crea y ejecuta la sentencia SQL para adicionar un HORARIO a la base de datos de AforoAndes
 * @param pm - El manejador de persistencia
 * @param hora - La hora del horario
 * @param minuto - El minuto del horario
 * @param id - El identificador
 * @return - Las tuplas insertadas
 */
	public long adicionarHorario (PersistenceManager pm, int hora, int minuto, long id ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHorario() + "(id, hora, minuto) values (?, ?, ?)");
        q.setParameters(id, hora, minuto);
        return (long) q.executeUnique();
	}

/**
 * Crea y ejecuta la sentencia SQL para eliminar un HORARIO a la base de datos de AforoAndes
 * @param pm - El manejador de persistencia
 * @param id - El identificador del horaro
 * @return Las tuplas eliminadas
 */
	public long eliminarAreasPorID (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHorario () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN HORARIO de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del area
	 * @return El objeto HORARIO que tiene el identificador dado
	 */
	public Horario darHorarioPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHorario () + " WHERE id = ?");
		q.setResultClass(Horario.class);
		q.setParameters(id);
		return (Horario) q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS HORARIOS de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HORARIO
	 */
	public List<Horario> darHorarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHorario ());
		q.setResultClass(Horario.class);
		return (List<Horario>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la hora en la
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El id del área
	 * @param hora - La nueva hora del horario
	 * @return El número de tuplas modificadas
	 */
	public long cambiarHora(PersistenceManager pm, long id, int hora) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHorario () + " SET hora = ? WHERE id = ?");
	     q.setParameters(hora, id);
	     return (long) q.executeUnique();            
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el minuto en la
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El id del área
	 * @param minuto - El minuto modificado
	 * @return El número de tuplas modificadas
	 */
	public long cambiarMinuto(PersistenceManager pm, long id, int minuto) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHorario () + " SET minuto = ? WHERE id = ?");
	     q.setParameters(minuto, id);
	     return (long) q.executeUnique();            
	}
	

}
