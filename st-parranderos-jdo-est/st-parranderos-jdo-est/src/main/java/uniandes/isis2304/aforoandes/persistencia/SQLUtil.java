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

package uniandes.isis2304.aforoandes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforoandes.negocio.LocalComercial;
import uniandes.isis2304.aforoandes.negocio.RFC3;
import uniandes.isis2304.aforoandes.negocio.RFC4;
import uniandes.isis2304.aforoandes.negocio.Visitante;

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

	/* ****************************************************************
	 * 			Requerimientos de consulta
	 *****************************************************************/

	/**
	 * Creación y ejecución de la sentencia SQL para buscar todos los visitantes atendidos por un establecimiento en una fecha o rango de fechas
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param idLocalComercial - El id del local comercial a consultar
	 * @return Una lista de objetos Visitante, construida con base en la consulta realizada	 
	 */
	public List<Visitante> RFC1Fecha ( PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, String idLocalComercial ) 
	{
		Query q;
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);

		q = pm.newQuery (SQL, "SELECT DISTINCT VISITANTE.*" + 
				"	FROM LECTOR" + 
				"	JOIN" + 
				"    (" + 
				"        SELECT IDLECTOR, IDVISITANTE" + 
				"        FROM REGISTRANCARNET" + 
				"        WHERE FECHA BETWEEN ? AND ?" + 
				"    ) INF_VISITAS" + 
				"	ON INF_VISITAS.IDLECTOR = LECTOR.ID" + 
				"	JOIN VISITANTE" + 
				"	ON INF_VISITAS.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"	WHERE LECTOR.IDLOCALCOMERCIAL = ?");
		q.setParameters(dt1, dt2, idLocalComercial);		
		q.setResultClass(Visitante.class);
		return (List<Visitante>) q.executeList();
	}

	/**
	 * Creación y ejecución de la sentencia SQL para buscar todos los visitantes atendidos por un establecimiento en un rango de horas
	 * @param fecha - La fecha de consulta
	 * @param horaInicio - La hora de inicio del rango de consulta
	 * @param minutoFin - El minuto de inicio del rango de consulta
	 * @param horaFin - La hora de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param idLocalComercial - El id del local comercial a consultar
	 * @return Una lista de objetos Visitante, construida con base en la consulta realizada
	 */
	public List<Visitante> RFC1Horas (PersistenceManager pm, Timestamp fecha, int horaInicio, int minutoInicio, int horaFin, int minutoFin, String idLocalComercial) 
	{
		Query q;
		LocalDateTime dt1 = fecha.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);

		q = pm.newQuery (SQL,"SELECT DISTINCT VISITANTE.*" + 
				"	FROM LECTOR" + 
				"	JOIN" + 
				"    (" + 
				"        SELECT IDLECTOR, IDVISITANTE" + 
				"        FROM HORARIO" + 
				"        JOIN REGISTRANCARNET" + 
				"        ON HORARIO.ID = REGISTRANCARNET.HORAENTRADA" + 
				"        WHERE FECHA = ?" + 
				"        AND (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?)" + 
				"    ) INF_VISITAS" + 
				"	ON INF_VISITAS.IDLECTOR = LECTOR.ID" + 
				"	JOIN VISITANTE" + 
				"	ON INF_VISITAS.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"	WHERE LECTOR.IDLOCALCOMERCIAL = ?");
		q.setParameters(dt1, horaInicio, horaFin, horaInicio, minutoInicio, horaFin, minutoFin, idLocalComercial);	
		q.setResultClass(Visitante.class);
		List<Visitante> list = q.executeList();
		return list;
	}

	/**
	 * Creación y ejecución de la sentencia SQL para buscar los 20 establecimientos más populares en una fecha o rango de fechas
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param idLocalComercial - El id del local comercial a consultar
	 * @return Una lista de objetos LocalComercial, construida con base en la consulta realizada
	 */
	public List<LocalComercial> RFC2Fecha ( PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin ) 
	{
		Query q;
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		q = pm.newQuery (SQL, "SELECT LOCALCOMERCIAL.*" + 
				"	FROM LECTOR" + 
				"	JOIN " + 
				"    (SELECT IDLECTOR, NUM_VISITAS, DENSE_RANK() OVER (ORDER BY NUM_VISITAS DESC) RANK" + 
				"    FROM" + 
				"        (SELECT IDLECTOR, COUNT(*) AS NUM_VISITAS" + 
				"        FROM REGISTRANCARNET" + 
				"        WHERE FECHA BETWEEN ? AND ?" + 
				"        GROUP BY IDLECTOR)" + 
				"    ) AUX" + 
				"	ON AUX.IDLECTOR = LECTOR.ID" + 
				"	JOIN LOCALCOMERCIAL" + 
				"	ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
				"	WHERE RANK <= 20" + 
				"	ORDER BY NUM_VISITAS DESC" + 
				"");
		q.setParameters(dt1, dt2);				
		q.setResultClass(LocalComercial.class);
		return (List<LocalComercial>) q.executeList();
	}

	/**
	 * Creación y ejecución de la sentencia SQL para buscar los 20 establecimientos más populares en un rango de horas. Los locales con el mismo número de visitas se incluyen
	 * @param fecha - La fecha de consulta
	 * @param horaInicio - La hora de inicio del rango de consulta
	 * @param minutoFin - El minuto de inicio del rango de consulta
	 * @param horaFin - La hora de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @return Una lista de objetos LocalComercial, construida con base en la consulta realizada
	 */
	public List<LocalComercial> RFC2Horas (PersistenceManager pm, Timestamp fecha, int horaInicio, int minutoInicio, int horaFin, int minutoFin) 
	{
		LocalDateTime dt1 = fecha.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		Query q;
		q = pm.newQuery (SQL,"SELECT LOCALCOMERCIAL.*" + 
				"	FROM LECTOR" + 
				"	JOIN" + 
				"    (SELECT DISTINCT REGISTRANCARNET.IDLECTOR, NUM_VISITAS, DENSE_RANK() OVER (ORDER BY INF_VISITAS.NUM_VISITAS DESC) AS RANK" + 
				"    FROM" + 
				"        (" + 
				"            SELECT IDLECTOR, COUNT(*) AS NUM_VISITAS" + 
				"            FROM REGISTRANCARNET" + 
				"            WHERE FECHA = ?" + 
				"            GROUP BY IDLECTOR" + 
				"        ) INF_VISITAS" + 
				"    JOIN REGISTRANCARNET" + 
				"    ON INF_VISITAS.IDLECTOR = REGISTRANCARNET.IDLECTOR" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?)" + 
				"    ) AUX" + 
				"	ON AUX.IDLECTOR = LECTOR.ID" + 
				"	JOIN LOCALCOMERCIAL" + 
				"	ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
				"	WHERE RANK <= 20" + 
				"");
		q.setParameters(dt1, horaInicio, horaFin, horaInicio, minutoInicio, horaFin, minutoFin);		
		q.setResultClass(LocalComercial.class);
		List<LocalComercial> list = q.executeList();
		return list;
	}

	/**
	 * Creación y ejecución de la sentencia SQL para calcular el índice de aforo de un centro comercial en una fecha o rango de fechas
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param idCentroComercial - Identificador del centro comercial
	 * @return El objeto RFC3, construido con base en la consulta realizada	 
	 */
	public RFC3 RFC3FechaCentroComercial (PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, String idCentroComercial)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);

		Query q;
		q = pm.newQuery (SQL, "SELECT IDCENTROCOMERCIAL AS IDENTIFICADOR, ROUND(AVG(NUM_VISITAS/AFOROTOTAL),4) AS INDICE" + 
				"	FROM" + 
				"	(" + 
				"    SELECT LECTOR.IDCENTROCOMERCIAL, HORA, MINUTO, COUNT(*) AS NUM_VISITAS" + 
				"    FROM REGISTRANCARNET JOIN LECTOR" + 
				"    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE FECHA BETWEEN ? AND ? AND LECTOR.IDCENTROCOMERCIAL IS NOT NULL AND LECTOR.IDCENTROCOMERCIAL = ?" +
				"    GROUP BY LECTOR.IDCENTROCOMERCIAL, HORA, MINUTO" + 
				"	)," + 
				"	(" + 
				"    SELECT SUM(AFORO) AS AFOROTOTAL" + 
				"    FROM" + 
				"    (" + 
				"        (SELECT AFORO" + 
				"        FROM" + 
				"        ASCENSOR JOIN CAPACIDADNORMAL" + 
				"        ON ASCENSOR.CAPACIDADNORMAL = CAPACIDADNORMAL.ID" + 
				"        WHERE ASCENSOR.IDCENTROCOMERCIAL = ?)" + 
				"        UNION" + 
				"        (SELECT AFORO" + 
				"        FROM" + 
				"        BANO JOIN CAPACIDADNORMAL " + 
				"        ON BANO.CAPACIDADNORMAL = CAPACIDADNORMAL.ID" + 
				"        WHERE BANO.IDCENTROCOMERCIAL = ?)" + 
				"        UNION" + 
				"        (SELECT AFORO" + 
				"        FROM" + 
				"        LOCALCOMERCIAL JOIN AREA " + 
				"        ON LOCALCOMERCIAL.AREA = AREA.ID" + 
				"        WHERE LOCALCOMERCIAL.IDCENTROCOMERCIAL = ?)" + 
				"        UNION" + 
				"        (SELECT AFORO" + 
				"        FROM" + 
				"        PARQUEADERO JOIN CAPACIDADNORMAL " + 
				"        ON PARQUEADERO.CAPACIDADNORMAL = CAPACIDADNORMAL.ID" + 
				"        WHERE PARQUEADERO.IDCENTROCOMERCIAL = ?)" + 
				"    )" + 
				"	)" + 
				"	WHERE IDCENTROCOMERCIAL IS NOT NULL" + 
				"	GROUP BY IDCENTROCOMERCIAL");
		q.setParameters(dt1, dt2, idCentroComercial, idCentroComercial, idCentroComercial, idCentroComercial, idCentroComercial);		
		q.setResultClass(RFC3.class);
		List<RFC3> list = q.executeList();
		return list.get(0);
	}

	/**
	 * Creación y ejecución de la sentencia SQL para calcular el índice de aforo de un centro comercial en una fecha o rango de fechas
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param id - Identificador del local o centro comercial
	 * @return El objeto RFC3, construido con base en la consulta realizada
	 */
	public RFC3 RFC3HoraCentroComercial (PersistenceManager pm, Timestamp fecha, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal, String id)
	{
		Query q;
		LocalDateTime dt1 = fecha.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		q = pm.newQuery (SQL, "	SELECT IDCENTROCOMERCIAL AS IDENTIFICADOR, ROUND(AVG(NUM_VISITAS/AFOROTOTAL),4) AS INDICE" + 
				"	FROM" + 
				"	(" + 
				"    SELECT LECTOR.IDCENTROCOMERCIAL, COUNT(*) AS NUM_VISITAS" + 
				"    FROM REGISTRANCARNET JOIN LECTOR" + 
				"    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE FECHA = ? AND LECTOR.IDCENTROCOMERCIAL IS NOT NULL AND LECTOR.IDCENTROCOMERCIAL = ?" + 
				"    AND (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?)" + 
				"    GROUP BY LECTOR.IDCENTROCOMERCIAL" + 
				"	)," + 
				"	(" + 
				"    SELECT SUM(AFORO) AS AFOROTOTAL" + 
				"    FROM" + 
				"    (" + 
				"        (SELECT AFORO" + 
				"        FROM" + 
				"        ASCENSOR JOIN CAPACIDADNORMAL " + 
				"        ON ASCENSOR.CAPACIDADNORMAL = CAPACIDADNORMAL.ID" + 
				"        WHERE ASCENSOR.IDCENTROCOMERCIAL = ?)" + 
				"        UNION" + 
				"        (SELECT AFORO" + 
				"        FROM" + 
				"        BANO JOIN CAPACIDADNORMAL " + 
				"        ON BANO.CAPACIDADNORMAL = CAPACIDADNORMAL.ID" + 
				"        WHERE BANO.IDCENTROCOMERCIAL = ?)" + 
				"        UNION" + 
				"        (SELECT AFORO" + 
				"        FROM" + 
				"        LOCALCOMERCIAL JOIN AREA" + 
				"        ON LOCALCOMERCIAL.AREA = AREA.ID" + 
				"        WHERE LOCALCOMERCIAL.IDCENTROCOMERCIAL = ?)" + 
				"        UNION" + 
				"        (SELECT AFORO" + 
				"        FROM" + 
				"        PARQUEADERO JOIN CAPACIDADNORMAL" + 
				"        ON PARQUEADERO.CAPACIDADNORMAL = CAPACIDADNORMAL.ID" + 
				"        WHERE PARQUEADERO.IDCENTROCOMERCIAL = ?)" + 
				"    )" + 
				"	)" + 
				"	WHERE IDCENTROCOMERCIAL IS NOT NULL" + 
				"	GROUP BY IDCENTROCOMERCIAL");
		q.setParameters(dt1, id, horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, id, id, id, id);		
		q.setResultClass(RFC3.class);
		List<RFC3> list = q.executeList();
		return list.get(0);
	}

	/**
	 * Creación y ejecución de la sentencia SQL para calcular el índice de aforo de un local comercial en una fecha o rango de fechas
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @return El objeto RFC3, construido con base en la consulta realizada
	 * @param id - Identificador del local comercial
	 */
	public RFC3 RFC3FechaEstablecimiento (PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, String id)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);

		Query q;
		q = pm.newQuery (SQL, "SELECT LOCALCOMERCIAL.IDENTIFICADOR AS IDENTIFICADOR, ROUND(AVG(NUM_VISITAS/AREA.AFORO), 4) AS INDICE" + 
				"	FROM LOCALCOMERCIAL" + 
				"	JOIN" + 
				"    (" + 
				"        SELECT LECTOR.IDLOCALCOMERCIAL, HORARIO.HORA, HORARIO.MINUTO, COUNT(*) AS NUM_VISITAS" + 
				"        FROM REGISTRANCARNET " + 
				"        JOIN HORARIO" + 
				"        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"        JOIN LECTOR" + 
				"        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"        WHERE FECHA BETWEEN ? AND ? AND LECTOR.IDLOCALCOMERCIAL IS NOT NULL" + 
				"        GROUP BY LECTOR.IDLOCALCOMERCIAL, HORARIO.HORA, HORARIO.MINUTO" + 
				"    ) AUX" + 
				"	ON LOCALCOMERCIAL.IDENTIFICADOR = AUX.IDLOCALCOMERCIAL" + 
				"	JOIN AREA" + 
				"	ON LOCALCOMERCIAL.AREA = AREA.ID" + 
				"	WHERE LOCALCOMERCIAL.IDENTIFICADOR = ?" + 
				"	GROUP BY LOCALCOMERCIAL.IDENTIFICADOR");

		q.setParameters(dt1, dt2, id);				
		q.setResultClass(RFC3.class);
		return (RFC3)q.executeUnique();
	}

	/**
	 * Creación y ejecución de la sentencia SQL para calcular el índice de aforo de un local comercial en una fecha o rango de fechas
	 * @param fecha - La fecha del rango de consulta
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param id - Identificador del local comercial
	 * @return El objeto RFC3, construido con base en la consulta realizada
	 */
	public RFC3 RFC3HoraEstablecimiento (PersistenceManager pm, Timestamp fecha, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal, String id)
	{

		LocalDateTime dt1 = fecha.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		Query q;
		q = pm.newQuery (SQL, "SELECT LOCALCOMERCIAL.IDENTIFICADOR AS IDENTIFICADOR, ROUND(AVG(NUM_VISITAS/AREA.AFORO), 4) AS INDICE" + 
				"	FROM LOCALCOMERCIAL" + 
				"	JOIN" + 
				"    (" + 
				"        SELECT LECTOR.IDLOCALCOMERCIAL, COUNT(*) AS NUM_VISITAS" + 
				"        FROM REGISTRANCARNET " + 
				"        JOIN HORARIO" + 
				"        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"        JOIN LECTOR" + 
				"        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"        WHERE FECHA = ? AND " + 
				"        HORA BETWEEN ? AND ? OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) AND LECTOR.IDLOCALCOMERCIAL IS NOT NULL" + 
				"        GROUP BY LECTOR.IDLOCALCOMERCIAL" + 
				"    ) AUX" + 
				"	ON LOCALCOMERCIAL.IDENTIFICADOR = AUX.IDLOCALCOMERCIAL" + 
				"	JOIN AREA" + 
				"	ON LOCALCOMERCIAL.AREA = AREA.ID" + 
				"	WHERE LOCALCOMERCIAL.IDENTIFICADOR = ?" + 
				"	GROUP BY LOCALCOMERCIAL.IDENTIFICADOR" + 
				"");
		q.setParameters(dt1, horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, id);		
		q.setResultClass(RFC3.class);
		return (RFC3)q.executeUnique();
	}

	/**
	 * Creación y ejecución de la sentencia SQL para calcular el índice de aforo de un local comercial en una fecha o rango de fechas
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param tipoLocal - El tipo del local comercial de interés
	 * @return El objeto RFC3, construido con base en la consulta realizada
	 */
	public RFC3 RFC3FechaTipoLocal (PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, String tipoLocal)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);

		Query q;
		q = pm.newQuery (SQL, "	SELECT INF_AFORO.TIPO AS IDENTIFICADOR, ROUND(AVG(INF_VISITAS.NUM_VISITAS/INF_AFORO.AFORO_TOTAL_TIPO),4) AS INDICE" + 
				"	FROM" + 
				"	(" + 
				"    SELECT TIPOLOCAL.TIPO, SUM(AFORO) AS AFORO_TOTAL_TIPO" + 
				"    FROM LOCALCOMERCIAL JOIN TIPOLOCAL" + 
				"    ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
				"    JOIN AREA" + 
				"    ON LOCALCOMERCIAL.AREA = AREA.ID" + 
				"    WHERE UPPER(TIPOLOCAL.TIPO) = UPPER(?)" + 
				"    GROUP BY TIPOLOCAL.TIPO" + 
				"	) INF_AFORO" + 
				"	JOIN" + 
				"	(" + 
				"    SELECT TIPOLOCAL.TIPO, HORA, MINUTO, COUNT(*) AS NUM_VISITAS" + 
				"    FROM REGISTRANCARNET JOIN LECTOR" + 
				"    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"    JOIN LOCALCOMERCIAL" + 
				"    ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
				"    JOIN TIPOLOCAL" + 
				"    ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE FECHA BETWEEN ? AND ?" + 
				"    AND UPPER(TIPOLOCAL.TIPO) = UPPER(?)" + 
				"    GROUP BY TIPOLOCAL.TIPO, HORA, MINUTO" + 
				"	)INF_VISITAS" + 
				"	ON INF_AFORO.TIPO = INF_VISITAS.TIPO" + 
				"	GROUP BY INF_AFORO.TIPO");

		q.setParameters(tipoLocal, dt1, dt2, tipoLocal);				
		q.setResultClass(RFC3.class);
		List<RFC3> list = q.executeList();
		return list.get(0);
	}

	/**
	 * Creación y ejecución de la sentencia SQL para calcular el índice de aforo de un local comercial en una fecha o rango de fechas
	 * @param fecha - La fecha del rango de consulta	
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param tipoLocal - El tipo del local comercial de interés
	 * @return El objeto RFC3, construido con base en la consulta realizada
	 */
	public RFC3 RFC3HoraTipoLocal (PersistenceManager pm, Timestamp fecha, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal, String tipoLocal)
	{
		LocalDateTime dt1 = fecha.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		Query q;
		q = pm.newQuery (SQL, "SELECT INF_AFORO.TIPO AS IDENTIFICADOR, ROUND(AVG(INF_VISITAS.NUM_VISITAS/INF_AFORO.AFORO_TOTAL_TIPO),4) AS INDICE" + 
				"	FROM" + 
				"	(" + 
				"    SELECT TIPOLOCAL.TIPO, SUM(AFORO) AS AFORO_TOTAL_TIPO" + 
				"    FROM LOCALCOMERCIAL JOIN TIPOLOCAL" + 
				"    ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
				"    JOIN AREA" + 
				"    ON LOCALCOMERCIAL.AREA = AREA.ID" + 
				"    AND UPPER(TIPOLOCAL.TIPO) = UPPER(?)" + 
				"    GROUP BY TIPOLOCAL.TIPO" + 
				"	) INF_AFORO" + 
				"	JOIN" + 
				"	(" + 
				"    SELECT TIPOLOCAL.TIPO, HORA, MINUTO, COUNT(*) AS NUM_VISITAS" + 
				"    FROM REGISTRANCARNET JOIN LECTOR" + 
				"    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"    JOIN LOCALCOMERCIAL" + 
				"    ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
				"    JOIN TIPOLOCAL" + 
				"    ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE FECHA = ? AND HORA BETWEEN ? AND ?" + 
				"    OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?)" + 
				"    AND TIPOLOCAL.TIPO = UPPER (?)" + 
				"    GROUP BY TIPOLOCAL.TIPO, HORA, MINUTO" + 
				"	)INF_VISITAS" + 
				"	ON INF_AFORO.TIPO = INF_VISITAS.TIPO" + 
				"	GROUP BY INF_AFORO.TIPO" + 
				"");
		q.setParameters(tipoLocal, dt1, horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, tipoLocal);		
		q.setResultClass(RFC3.class);
		List<RFC3> list = q.executeList();
		return list.get(0);
	}

	/* ****************************************************************
	 * 			BONO: Requerimientos de consulta
	 *****************************************************************/

	/**
	 * Creación y ejecución de la sentencia SQL para consultar los establecimientos con aforo disponible en una fecha o rango de fechas y una hora
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @return El objeto RFC3, construido con base en la consulta realizada
	 */
	public List<RFC4> RFC4FechaHora (PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, int horaInicio, int minutoInicio)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);

		Query q;
		q = pm.newQuery (SQL, "SELECT LOCALCOMERCIAL.*, AFORO-NUM_VISITAS AS CUPOS_DISPONIBLES" + 
				"	FROM" + 
				"	(" + 
				"    SELECT LECTOR.IDLOCALCOMERCIAL, COUNT(*) AS NUM_VISITAS" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN LECTOR" + 
				"    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE FECHA BETWEEN ? AND ?" + 
				"    AND HORA = ? AND MINUTO = ?" + 
				"    GROUP BY LECTOR.IDLOCALCOMERCIAL" + 
				"	) AUX" + 
				"	JOIN LOCALCOMERCIAL" + 
				"	ON LOCALCOMERCIAL.IDENTIFICADOR = AUX.IDLOCALCOMERCIAL" + 
				"	JOIN AREA" + 
				"	ON LOCALCOMERCIAL.AREA = AREA.ID" + 
				"	WHERE AFORO-NUM_VISITAS > 0" + 
				"");
		q.setParameters(dt1, dt2, horaInicio, minutoInicio);		
		q.setResultClass(RFC4.class);
		return (List<RFC4>)q.executeList();
	}

	/**
	 * Creación y ejecución de la sentencia SQL para consultar los establecimientos con aforo disponible en una fecha y rango de horas
	 * @param fecha - La fecha del rango de consulta
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @return La lista de objetos RFC4, construida con base en la consulta realizada
	 */
	public List<RFC4> RFC4FechaRangoHoras (PersistenceManager pm, Timestamp fecha, int horaInicio, int minutoInicio, int horaFin, int minutoFin)
	{
		LocalDateTime dt1 = fecha.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		Query q;
		q = pm.newQuery (SQL, "SELECT LOCALCOMERCIAL.*, AFORO - NUM_VISITAS AS CUPOS_DISPONIBLES" + 
				"	FROM" + 
				"	(" + 
				"    SELECT LECTOR.IDLOCALCOMERCIAL, COUNT(*) AS NUM_VISITAS" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN LECTOR" + 
				"    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE FECHA = ?" + 
				"    AND (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?)" + 
				"    GROUP BY LECTOR.IDLOCALCOMERCIAL" + 
				"	) AUX" + 
				"	JOIN LOCALCOMERCIAL" + 
				"	ON LOCALCOMERCIAL.IDENTIFICADOR = AUX.IDLOCALCOMERCIAL" + 
				"	JOIN AREA" + 
				"	ON LOCALCOMERCIAL.AREA = AREA.ID" + 
				"	WHERE AFORO - NUM_VISITAS > 0");
		q.setParameters(dt1, horaInicio, horaFin, horaInicio, minutoInicio, horaFin, minutoFin);		
		q.setResultClass(RFC4.class);
		return (List<RFC4>)q.executeList();
	}

	/**
	 * Creación y ejecución de la sentencia SQL para consultar las visitas de un tipo de visitante
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param tipoVisitante - Tipo de visitante de interés
	 * @return Arreglo de objetos construido con base en la consulta realizada
	 */
	public double[] RFC5TiemposVisitaTipoVisitanteMetricas(PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal, String tipoVisitante)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);

		Query q;
		q = pm.newQuery (SQL, "SELECT INF_ENTRADA.TIPO, ROUND(AVG((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA), 3) AS DURACION_VISITA" + 
				" FROM" + 
				" (" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN VISITANTE" + 
				"    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"    JOIN TIPOVISITANTE" + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE (HORAENTRADA BETWEEN ? AND ?) OR (HORAENTRADA = ? AND MINUTO >= ?) OR (HORAENTRADA = ? AND MINUTO <= ?)" + 
				" ) INF_ENTRADA" + 
				" JOIN" + 
				" (" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN VISITANTE" + 
				"    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"    JOIN TIPOVISITANTE" + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID" + 
				"    WHERE FECHA BETWEEN ? AND ?" + 
				"    AND TIPOVISITANTE.TIPO = ?" + 
				" ) INF_SALIDA" + 
				" ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE" + 
				" GROUP BY INF_ENTRADA.TIPO" + 
				"");
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, tipoVisitante);	
		Object[] promedio = (Object[]) q.executeUnique();

		q = pm.newQuery (SQL, "SELECT INF_ENTRADA.TIPO, MIN((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA) AS DURACION_VISITA" + 
				" FROM" + 
				" (" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN VISITANTE" + 
				"    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"    JOIN TIPOVISITANTE" + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE (HORAENTRADA BETWEEN ? AND ?) OR (HORAENTRADA = ? AND MINUTO >= ?) OR (HORAENTRADA = ? AND MINUTO <= ?)" + 
				" ) INF_ENTRADA" + 
				" JOIN" + 
				" (" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN VISITANTE" + 
				"    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"    JOIN TIPOVISITANTE" + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID" + 
				"    WHERE FECHA BETWEEN ? AND ?" + 
				"    AND TIPOVISITANTE.TIPO = ?" + 
				" ) INF_SALIDA" + 
				" ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE" + 
				" GROUP BY INF_ENTRADA.TIPO" + 
				"");
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, tipoVisitante);	
		Object[] minimo = (Object[]) q.executeUnique();

		q = pm.newQuery (SQL, "SELECT INF_ENTRADA.TIPO, MAX((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA) AS DURACION_VISITA" + 
				" FROM" + 
				" (" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN VISITANTE" + 
				"    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"    JOIN TIPOVISITANTE" + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE (HORAENTRADA BETWEEN ? AND ?) OR (HORAENTRADA = ? AND MINUTO >= ?) OR (HORAENTRADA = ? AND MINUTO <= ?)" + 
				" ) INF_ENTRADA" + 
				" JOIN" + 
				" (" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN VISITANTE" + 
				"    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"    JOIN TIPOVISITANTE" + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID" + 
				"    WHERE FECHA BETWEEN ? AND ?" + 
				"    AND TIPOVISITANTE.TIPO = ?" + 
				" ) INF_SALIDA" + 
				" ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE" + 
				" GROUP BY INF_ENTRADA.TIPO" + 
				"");
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, tipoVisitante);	
		Object[] maximo = (Object[]) q.executeUnique();

		double[] metricas = new double[3];
		metricas[0] = ((BigDecimal)promedio[1]).doubleValue();
		metricas[1] = ((BigDecimal)minimo[1]).doubleValue();
		metricas[2] = ((BigDecimal)maximo[1]).doubleValue();

		return metricas;
	}

	/**
	 * Creación y ejecución de la sentencia SQL para consultar los establecimientos con aforo disponible en una fecha y rango de horas
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param tipoVisitante - Tipo de visitante de interés
	 * @return Arreglo de objetos construido con base en la consulta realizada
	 */
	public List<Object> RFC5TiemposVisitaTipoVisitante(PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal, String tipoVisitante)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		Query q = pm.newQuery (SQL, "SELECT INF_ENTRADA.IDVISITANTE, (HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA AS DURACION_VISITA" + 
				"	FROM" + 
				"	(" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN VISITANTE" + 
				"    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"    JOIN TIPOVISITANTE" + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    WHERE (HORAENTRADA BETWEEN ? AND ?) OR (HORAENTRADA = ? AND MINUTO >= ?) OR (HORAENTRADA = ? AND MINUTO <= ?)" + 
				"	) INF_ENTRADA" + 
				"	JOIN" + 
				"	(" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN VISITANTE" + 
				"    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION" + 
				"    JOIN TIPOVISITANTE" + 
				"    ON VISITANTE.TIPO = TIPOVISITANTE.ID" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID" + 
				"    WHERE FECHA BETWEEN ? AND ?" + 
				"    AND TIPOVISITANTE.TIPO = 'Domiciliario'" + 
				"	) INF_SALIDA" + 
				"	ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE");
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, tipoVisitante);	
		return (List<Object>) q.executeList();
	}

	/**
	 * Creación y ejecución de la sentencia SQL para consultar las visitas de un tipo de local
	 * @param distintos - Indica si se cuentan visitas de visitantes distintos
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param tipoLocal - Tipo de local de interés
	 * @return Arreglo de objetos construido con base en la consulta realizada
	 */
	@SuppressWarnings("resource")
	public double[] RFC5VisitantesTipoLocalMetricas(PersistenceManager pm, boolean distintos, Timestamp fechaInicio, Timestamp fechaFin, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal, String tipoLocal)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);

		Query q;
		if (distintos)
		{
			q = pm.newQuery (SQL, "" + 
					"    SELECT TIPO, ROUND(AVG(CANTIDAD_VISITANTES),4) AS PROMEDIO_CANTIDAD" + 
					"    FROM" + 
					"    (" + 
					"        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES" + 
					"        FROM REGISTRANCARNET" + 
					"        JOIN LECTOR" + 
					"        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
					"        JOIN LOCALCOMERCIAL" + 
					"        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
					"        JOIN TIPOLOCAL" + 
					"        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
					"        JOIN HORARIO" + 
					"        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
					"        WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) " + 
					"        AND FECHA BETWEEN ? AND ?" + 
					"        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL" + 
					"    )" + 
					"    WHERE UPPER(TIPO) = UPPER(?)" + 
					"    GROUP BY TIPO" + 
					"");
		}
		else
		{
			q = pm.newQuery (SQL, "" + 
					"    SELECT TIPO, ROUND(AVG(CANTIDAD_VISITANTES),4) AS PROMEDIO_CANTIDAD" + 
					"    FROM" + 
					"    (" + 
					"        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(IDVISITANTE) AS CANTIDAD_VISITANTES" + 
					"        FROM REGISTRANCARNET" + 
					"        JOIN LECTOR" + 
					"        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
					"        JOIN LOCALCOMERCIAL" + 
					"        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
					"        JOIN TIPOLOCAL" + 
					"        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
					"        JOIN HORARIO" + 
					"        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
					"        WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) " + 
					"        AND FECHA BETWEEN ? AND ?" + 
					"        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL" + 
					"    )" + 
					"    WHERE UPPER(TIPO) = UPPER(?)" + 
					"    GROUP BY TIPO" + 
					"");
		}
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, tipoLocal);	
		Object[] promedio = (Object[]) q.executeUnique();

		if ( distintos )
		{
			q = pm.newQuery (SQL, "" + 
					"    SELECT TIPO, ROUND(MIN(CANTIDAD_VISITANTES),4) AS MINIMO_CANTIDAD" + 
					"    FROM" + 
					"    (" + 
					"        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES" + 
					"        FROM REGISTRANCARNET" + 
					"        JOIN LECTOR" + 
					"        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
					"        JOIN LOCALCOMERCIAL" + 
					"        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
					"        JOIN TIPOLOCAL" + 
					"        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
					"        JOIN HORARIO" + 
					"        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
					"        WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) " + 
					"        AND FECHA BETWEEN ? AND ?" + 
					"        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL" + 
					"    )" + 
					"    WHERE UPPER(TIPO) = UPPER(?)" + 
					"    GROUP BY TIPO" + 
					"");
		}
		else
		{
			q = pm.newQuery (SQL, "" + 
					"    SELECT TIPO, ROUND(MIN(CANTIDAD_VISITANTES),4) AS MINIMO_CANTIDAD" + 
					"    FROM" + 
					"    (" + 
					"        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(IDVISITANTE) AS CANTIDAD_VISITANTES" + 
					"        FROM REGISTRANCARNET" + 
					"        JOIN LECTOR" + 
					"        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
					"        JOIN LOCALCOMERCIAL" + 
					"        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
					"        JOIN TIPOLOCAL" + 
					"        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
					"        JOIN HORARIO" + 
					"        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
					"        WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) " + 
					"        AND FECHA BETWEEN ? AND ?" + 
					"        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL" + 
					"    )" + 
					"    WHERE UPPER(TIPO) = UPPER(?)" + 
					"    GROUP BY TIPO" + 
					"");

		}
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, tipoLocal);	
		Object[] minimo = (Object[]) q.executeUnique();

		if ( distintos )
		{
			q = pm.newQuery (SQL, "" + 
					"    SELECT TIPO, ROUND(MAX(CANTIDAD_VISITANTES),4) AS MAX_CANTIDAD" + 
					"    FROM" + 
					"    (" + 
					"        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES" + 
					"        FROM REGISTRANCARNET" + 
					"        JOIN LECTOR" + 
					"        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
					"        JOIN LOCALCOMERCIAL" + 
					"        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
					"        JOIN TIPOLOCAL" + 
					"        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
					"        JOIN HORARIO" + 
					"        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
					"        WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) " + 
					"        AND FECHA BETWEEN ? AND ?" + 
					"        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL" + 
					"    )" + 
					"    WHERE UPPER(TIPO) = UPPER(?)" + 
					"    GROUP BY TIPO" + 
					"");
		}
		else
		{
			q = pm.newQuery (SQL, "" + 
					"    SELECT TIPO, ROUND(MAX(CANTIDAD_VISITANTES),4) AS MAX_CANTIDAD" + 
					"    FROM" + 
					"    (" + 
					"        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(IDVISITANTE) AS CANTIDAD_VISITANTES" + 
					"        FROM REGISTRANCARNET" + 
					"        JOIN LECTOR" + 
					"        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
					"        JOIN LOCALCOMERCIAL" + 
					"        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
					"        JOIN TIPOLOCAL" + 
					"        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
					"        JOIN HORARIO" + 
					"        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
					"        WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) " + 
					"        AND FECHA BETWEEN ? AND ?" + 
					"        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL" + 
					"    )" + 
					"    WHERE UPPER(TIPO) = UPPER(?)" + 
					"    GROUP BY TIPO" + 
					"");
		}
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, tipoLocal);	
		Object[] maximo = (Object[]) q.executeUnique();
		double[] metricas = new double[3];
		metricas[0] = ((BigDecimal)promedio[1]).doubleValue();
		metricas[1] = ((BigDecimal)minimo[1]).doubleValue();
		metricas[2] = ((BigDecimal)maximo[1]).doubleValue();
		return metricas;
	}
	/**
	 * Creación y ejecución de la sentencia SQL para las visitas a un tipo de local
	 * @param distintos - Indica si se cuentan visitas de visitantes distintos
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param tipoLocal - Tipo de local de interés
	 * @return Arreglo de objetos construido con base en la consulta realizada
	 */
	public List<Object> RFC5VisitantesTipoLocal(PersistenceManager pm, boolean distintos, Timestamp fechaInicio, Timestamp fechaFin, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal, String tipoLocal)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		Query q;
		if ( distintos )
		{
		q = pm.newQuery (SQL, "" + 
				"SELECT *" + 
				"FROM" + 
				"        (" + 
				"            SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES" + 
				"            FROM REGISTRANCARNET" + 
				"            JOIN LECTOR" + 
				"            ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"            JOIN LOCALCOMERCIAL" + 
				"            ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
				"            JOIN TIPOLOCAL" + 
				"            ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
				"            JOIN HORARIO" + 
				"            ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"            WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) " + 
				"            AND FECHA BETWEEN ? AND ?" + 
				"            GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL" + 
				"        )" + 
				"WHERE TIPO = ?");
		}
		else
		{
			q = pm.newQuery (SQL, "" + 
					"SELECT *" + 
					"FROM" + 
					"        (" + 
					"            SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(IDVISITANTE) AS CANTIDAD_VISITANTES" + 
					"            FROM REGISTRANCARNET" + 
					"            JOIN LECTOR" + 
					"            ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
					"            JOIN LOCALCOMERCIAL" + 
					"            ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR" + 
					"            JOIN TIPOLOCAL" + 
					"            ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID" + 
					"            JOIN HORARIO" + 
					"            ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
					"            WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) " + 
					"            AND FECHA BETWEEN ? AND ?" + 
					"            GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL" + 
					"        )" + 
					"WHERE TIPO = ?");
		}
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, tipoLocal);	
		return (List<Object>) q.executeList();
	}
	
	/**
	 * Creación y ejecución de la sentencia SQL para las visitas a un tipo de local
	 * @param distintos - Indica si se cuentan visitas de visitantes distintos
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La fecha de inicio del rango de consulta
	 * @param minutoInicio - El minuto de inicio del rango de consulta
	 * @param horaFin - La fecha de fin del rango de consulta
	 * @param minutoFin - El minuto de fin del rango de consulta
	 * @param idVisitante - Visitante de interés
	 * @return Arreglo de objetos construido con base en la consulta realizada
	 */
	public List<Object> RFC5DuracionVisitaLocal(PersistenceManager pm, boolean distintos, Timestamp fechaInicio, Timestamp fechaFin, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal, String idVisitante)
	{
		LocalDateTime dt1 = fechaInicio.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		LocalDateTime dt2 = fechaFin.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
		Query q;
		q = pm.newQuery (SQL, "SELECT INF_ENTRADA.IDVISITANTE, INF_ENTRADA.IDLOCALCOMERCIAL,((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA) AS DURACION_VISITA" + 
				"FROM" + 
				"(" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, LECTOR.IDLOCALCOMERCIAL, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID" + 
				"    JOIN LECTOR" + 
				"    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"    WHERE (HORA BETWEEN ? AND ?) OR (HORA = ? AND MINUTO >= ?) OR (HORA = ? AND MINUTO <= ?) AND LECTOR.IDLOCALCOMERCIAL IS NOT NULL" + 
				") INF_ENTRADA" + 
				"JOIN" + 
				"(" + 
				"    SELECT REGISTRANCARNET.IDVISITANTE, LECTOR.IDLOCALCOMERCIAL, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA" + 
				"    FROM REGISTRANCARNET" + 
				"    JOIN HORARIO" + 
				"    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID" + 
				"    JOIN LECTOR" + 
				"    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID" + 
				"    WHERE FECHA BETWEEN ? AND ?" + 
				"    AND REGISTRANCARNET.IDVISITANTE = ?" + 
				") INF_SALIDA" + 
				"ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE;");
		q.setParameters(horaInicio, horaFinal, horaInicio, minutoInicio, horaFinal, minutoFinal, dt1, dt2, idVisitante);	
		return (List<Object>) q.executeList();
	}
	

	
}