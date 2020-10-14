/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: AforoAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 */
class SQLUtil
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
	public SQLUtil (PersistenciaAforoAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalHorario (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqHorario () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalcapacidadNormal (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqCapacidadNormal() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalArea (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqArea () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalTipoLocal (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqTipoLocal() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalTipoCarnet (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqTipoCarnet() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalTipoLector (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqTipoLector() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalTipoVisitante (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqTipoVisitante() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con números que indican el número de tuplas borradas en las tablas de AforoAndes
	 */
	public long [] limpiarAforoAndes (PersistenceManager pm)
	{
        Query qRegistranVehiculo = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRegistranVehiculo());          
        Query qRegistranCarnet = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRegistranCarnet ());
        Query qVehiculo = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVehiculo());
        Query qEmpleado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleado());
        Query qDomiciliario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDomiciliario());
        Query qCarnet = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarnet());
        Query qTipoCarnet = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoCarnet());
        Query qLector = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLector());
        Query qTipoLector = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoLector());
        Query qZonaCirculacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaZonaCirculacion());
        Query qLocalComercial = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLocalComercial());
        Query qTipoLocal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoLocal());
        Query qParqueadero = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaParqueadero());
        Query qBaño = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBaño());
        Query qAscensor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAscensor());
        Query qCapacidadNormal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCapacidadNormal ());
        Query qArea = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaArea());
        Query qCentroComercial = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCentroComercial());
        Query qVisitante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitante ());
        Query qTipoVisitante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoVisitante());
        Query qHorario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHorario ());

        long registranVehiculoEliminados = (long) qRegistranVehiculo.executeUnique ();
        long  registranCarnetEliminados = (long) qRegistranCarnet.executeUnique ();
        long  vehiculoEliminados = (long) qVehiculo.executeUnique ();
        long  empleadoEliminados = (long) qEmpleado.executeUnique ();
        long  domiciliarioEliminados = (long) qDomiciliario.executeUnique ();
        long  carnetEliminados = (long) qCarnet.executeUnique ();
        long  tipoCarnetEliminados = (long) qTipoCarnet.executeUnique ();
        long  lectorEliminados = (long) qLector.executeUnique ();
        long  tipoLectorEliminados = (long) qTipoLector.executeUnique ();
        long  zonaCirculacionEliminados = (long) qZonaCirculacion.executeUnique ();
        long  localComercialEliminados = (long) qLocalComercial.executeUnique ();
        long  tipoLocalEliminados = (long) qTipoLocal.executeUnique ();
        long  parqueaderoEliminados = (long) qParqueadero.executeUnique ();
        long  bañoEliminados = (long) qBaño.executeUnique ();
        long  ascensorEliminados = (long) qAscensor.executeUnique ();
        long  capacidadNormalEliminados = (long) qCapacidadNormal.executeUnique ();
        long  areaEliminados = (long) qArea.executeUnique ();
        long  centroComercialEliminados = (long) qCentroComercial.executeUnique ();
        long  visitanteEliminados = (long) qVisitante.executeUnique ();
        long  tipoVisitanteEliminados = (long) qTipoVisitante.executeUnique ();
        long  horarioEliminados = (long) qHorario.executeUnique ();


        return new long[] {registranVehiculoEliminados, registranCarnetEliminados, vehiculoEliminados, empleadoEliminados, domiciliarioEliminados, carnetEliminados, tipoCarnetEliminados, lectorEliminados, tipoLectorEliminados,
        		zonaCirculacionEliminados, localComercialEliminados, tipoLocalEliminados, parqueaderoEliminados, bañoEliminados, ascensorEliminados, capacidadNormalEliminados, areaEliminados,
        		centroComercialEliminados, visitanteEliminados, tipoVisitanteEliminados, horarioEliminados};
	}

}
