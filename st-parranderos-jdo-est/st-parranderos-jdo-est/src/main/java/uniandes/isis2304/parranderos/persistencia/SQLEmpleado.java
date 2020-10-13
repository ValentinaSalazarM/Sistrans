package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.Empleado;

public class SQLEmpleado 

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
	public SQLEmpleado(PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un EMPLEADO a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idvisitante - El identificador del visitante
	 * @param lugarTrabajo - El lugar de trabajo del empleado 
	 * @return Las tuplas insertadas
	 */
	public long adicionarEmpleado (PersistenceManager pm, String idvisitante, String lugarTrabajo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpleado() + "(idvisitante, lugartrabajo) values (?, ?)");
        q.setParameters(idvisitante, lugarTrabajo);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar los EMPLEADOS de la base de datos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEmpleado - El identificador del empleado
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEmpleadoPorID(PersistenceManager pm, String idEmpleado) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleado() + " WHERE idvisitante = ?");
        q.setParameters(idEmpleado);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de un EMPLEADO de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del empleado
	 * @return El objeto EMPLEADO
	 */
	public Empleado darEmpleadoPorID(PersistenceManager pm, String id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleado() + " WHERE idvisitante = ?");
		q.setResultClass(Empleado.class);
		q.setParameters(id);
		return (Empleado) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS EMPLEADOS de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos EMPLEADOS
	 */
	public List<Empleado> darEmpleados(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleado());
		q.setResultClass(Empleado.class);
		return (List<Empleado>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el lugar de trabajo de un empleado en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del empleado
	 * @param  lugarTrabajo - El lugar de trabajo del empleado
	 * @return El número de tuplas modificadas
	 */
	public long cambiarCompañia(PersistenceManager pm, String id , String lugarTrabajo) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaDomiciliario() + " SET lugartrabajo = ? WHERE idvisitante = ?");
	     q.setParameters(lugarTrabajo, id);
	     return (long) q.executeUnique();            
	}


}
