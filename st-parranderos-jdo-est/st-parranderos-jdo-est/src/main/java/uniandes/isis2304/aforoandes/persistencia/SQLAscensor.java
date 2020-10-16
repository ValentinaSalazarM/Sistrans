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

import uniandes.isis2304.aforoandes.negocio.Ascensor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ASCENSOR de AforoAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLAscensor 
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
	public SQLAscensor (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ASCENSOR a la base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idAscensor - El identificador del ascensor
	 * @param capacidadNormal - El identificador de la capacidad normal del ascensor
	 * @param area - El identificador del área del ascensor
	 * @param pesoMaximo - El peso máximo del ascensor
	 * @param idCentroComercial - El identificador del centro comercial del ascensor
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarAscensor (PersistenceManager pm, String idAscensor, long capacidadNormal, long area, double pesoMaximo, String idCentroComercial) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAscensor () + "(identificador, capacidadNormal, area, pesoMaximo, idCentroComercial) values (?, ?, ?, ?, ?)");
        q.setParameters(idAscensor, capacidadNormal, area, pesoMaximo, idCentroComercial);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN ASCENSOR de la base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idAscensor - El identificador del ascensor
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAscensorPorId (PersistenceManager pm, String idAscensor)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAscensor () + " WHERE identificador = ?");
        q.setParameters(idAscensor);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un ASCENSOR de la base de datos de AforoAndes, por su peso máximo
	 * @param pm - El manejador de persistencia
	 * @param pesoMaximo - El peso máximo del ascensor
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAscensorPorPesoMaximo (PersistenceManager pm, double pesoMaximo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAscensor () + " WHERE pesoMaximo = ?");
        q.setParameters(pesoMaximo);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN ASCENSOR de la 
	 * base de datos de AforoAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idAscensor - El identificador del ascensor
	 * @return El objeto ASCENSOR que tiene el identificador dado
	 */
	public Ascensor darAscensorPorId (PersistenceManager pm, String idAscensor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAscensor () + " WHERE identificador = ?");
		q.setResultClass(Ascensor.class);
		q.setParameters(idAscensor);
		return (Ascensor) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ASCENSORES de la 
	 * base de datos de AforoAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param pesoMaximo - El peso máximo de ascensor buscado
	 * @return Una lista de objetos ASCENSOR que tienen el peso máximo dado
	 */
	public List<Ascensor> darAscensoresPorPesoMaximo (PersistenceManager pm, double pesoMaximo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAscensor () + " WHERE pesoMaximo = ?");
		q.setResultClass(Ascensor.class);
		q.setParameters(pesoMaximo);
		return (List<Ascensor>) q.executeList();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ASCENSORES de la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ASCENSOR
	 */
	public List<Ascensor> darAscensores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAscensor ());
		q.setResultClass(Ascensor.class);
		return (List<Ascensor>) q.executeList();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el peso máximo de un ascensor en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idAscensor - El identificador del ascensor
	 * @param pesoMaximo - El nuevo peso máximo del ascensor
	 * @return El número de tuplas modificadas
	 */
	public long cambiarPesoMaximoAscensor (PersistenceManager pm, String idAscensor, double pesoMaximo) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAscensor () + " SET pesoMaximo = ? WHERE identificador = ?");
	     q.setParameters(pesoMaximo, idAscensor);
	     return (long) q.executeUnique();            
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el área de un ascensor en la 
	 * base de datos de AforoAndes
	 * @param pm - El manejador de persistencia
	 * @param idAscensor- identificador del ascensor
	 * @param area - Identificador de la nueva área de un ascensor
	 * @return El número de tuplas modificadas
	 */
	public long cambiarAreaAscensor (PersistenceManager pm, String idAscensor, long area) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAscensor () + " SET area = ? WHERE identificador = ?");
		q.setParameters(area, idAscensor);
		return (long) q.executeUnique();            
	}

}
