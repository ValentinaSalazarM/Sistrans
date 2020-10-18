package uniandes.isis2304.aforoandes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.Lector;

public class SQLLector 
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
	public SQLLector (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un LECTOR a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del lector
	 * @param tipolector - El tipo del lector
	 * @param idcentrocomercial - El identificador del centro comercial 
	 * @param idlocalcomercial - El identificador del local comercial en el caso de que pertenezca a un local
	 * @param idascensor - El identificador del ascensor en el caso de que pertenezca a un ascensor
	 * @param idbaño - El identificador del baño en el caso de que pertenezca a un baño
	 * @return El número de tuplas insertadas
	 */
	public long adicionarLector (PersistenceManager pm, long id, long tipolector, String idcentrocomercial, String idlocalcomercial, String idascensor, String idparqueadero, String idbaño ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLector () + "(id, tipolector, idcentrocomercial, idlocalcomercial, idascensor, idparqueadero, idbaño) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipolector, idcentrocomercial,idlocalcomercial, idascensor,idparqueadero, idbaño);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un LECTOR de la base de datos de AforoAndes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del lector
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarLectorPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLector() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un LECTOR de la base de datos de AforoAndes, por su tipo
	 * @param pm - El manejador de persistencia
	 * @param tipoLector - El tipo del lector
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarLectorPorTipo (PersistenceManager pm, long tipoLector)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLector() + " WHERE tipoLector = ?");
        q.setParameters(tipoLector);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN LECTOR de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id del lector
	 * @return El objeto LECTOR que tiene el identificador dado
	 */
	public Lector darLectorPorId(PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLector () + " WHERE id = " + id);
		q.setResultClass(Lector.class);
		return (Lector) q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los LECTORES de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos LECTOR
	 */
	public List<Lector> darLectores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLector ());
		q.setResultClass(Lector.class);
		return (List<Lector>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN LECTOR de la 
	 * base de datos de AforoAndes, por su tipo
	 * @param pm - El manejador de persistencia
	 * @param tipo - El tipo del lector
	 * @return El objeto LECTOR que tiene el tipo dado
	 */
	public List<Lector> darLectoresPorTipo (PersistenceManager pm, long tipolector) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLector () + " WHERE tipolector = ?");
		q.setResultClass(Lector.class);
		q.setParameters(tipolector);
		return (List<Lector>) q.executeList();
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el tipo de un lector en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param id- El identificador del lector
	 * @param tipo- El nuevo tipo del lector con el id ingresado
	 * @return El número de tuplas modificadas
	 */
	public long cambiarTipoLector (PersistenceManager pm, long id, long tipolector) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaLector() + " SET tipolector = ? WHERE id = ?");
	     q.setParameters(tipolector, id);
	     return (long) q.executeUnique();            
	}
	
}
