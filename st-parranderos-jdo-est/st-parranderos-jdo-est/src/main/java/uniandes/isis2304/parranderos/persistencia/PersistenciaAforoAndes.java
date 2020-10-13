/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;


import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import uniandes.isis2304.parranderos.negocio.CapacidadNormal;
import uniandes.isis2304.parranderos.negocio.Carnet;
import uniandes.isis2304.parranderos.negocio.CentroComercial;
import uniandes.isis2304.parranderos.negocio.Domiciliario;
import uniandes.isis2304.parranderos.negocio.Empleado;
import uniandes.isis2304.parranderos.negocio.Horario;
import uniandes.isis2304.parranderos.negocio.Ascensor;
import uniandes.isis2304.parranderos.negocio.Baño;
import uniandes.isis2304.parranderos.negocio.Area;
import uniandes.isis2304.parranderos.negocio.LocalComercial;
import uniandes.isis2304.parranderos.negocio.Parqueadero;
import uniandes.isis2304.parranderos.negocio.TipoCarnet;


/**
 * Clase para el manejador de persistencia del proyecto Aforo-CCAndes
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLArea, SQLAscensor, SQLBaño, SQLCapacidadNormal, SQLCarnet, SQLCentroComercial, SQLDomiciliario,
 * SQLEmpleado, SQLLector, SQLLocalComercial, SQLParqueadero, SQLRegistranCarnet, SQLRegistranVehiculo, SQLTipoCarnet, SQLTipoLector,
 * SQLTipoLocal, SQLTipoVisitante, SQLVehiculo, SQLVisitanAscensor, SQLVisitanBaño, SQLVisitanCentroComercial, SQLVisitanParqueadero,
 * SQLVisitante y SQLZonaCirculacion que son las que realizan el acceso a la base de datos
 * 
 */
public class PersistenciaAforoAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAforoAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAforoAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en orden alfabético:
	 */
	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaAforoAndes
	 */
	private SQLUtil sqlUtil;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLTipoVisitante sqlTipoVisitante;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLVisitante sqlVisitante;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLCentroComercial sqlCentroComercial;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLCapacidadNormal sqlCapacidadNormal;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLArea sqlArea;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLAscensor sqlAscensor;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLBaño sqlBaño;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLParqueadero sqlParqueadero;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLTipoLocal sqlTipoLocal;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLLocalComercial sqlLocalComercial;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLZonaCirculacion sqlZonaCirculacion;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLTipoLector sqlTipoLector;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLLector sqlLector;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLTipoCarnet sqlTipoCarnet;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLCarnet sqlCarnet;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLDomiciliario sqlDomiciliario;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLEmpleado sqlEmpleado;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLVehiculo sqlVehiculo;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLVisitanCentroComercial sqlVisitanCentroComercial;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLVisitanAscensor sqlVisitanAscensor;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLVisitanBaño sqlVisitanBaño;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLVisitanLocalComercial sqlVisitanLocalComercial;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLVisitanParqueadero sqlVisitanParqueadero;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLRegistranCarnet sqlRegistranCarnet;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLRegistranVehiculo sqlRegistranVehiculo;

	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */
	private SQLHorario sqlHorario;

	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAforoAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("AforoAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("HORARIO");
		tablas.add ("TIPOVISITANTE");
		tablas.add ("VISITANTE");
		tablas.add ("CENTROCOMERCIAL");
		tablas.add ("AREA");
		tablas.add ("CAPACIDADNORMAL");
		tablas.add ("ASCENSOR");
		tablas.add ("BAÑO");
		tablas.add ("PARQUEADERO");
		tablas.add ("TIPOLOCAL");
		tablas.add ("LOCALCOMERCIAL");
		tablas.add ("ZONACIRCULACION");
		tablas.add ("TIPOLECTOR");
		tablas.add ("LECTOR");
		tablas.add ("TIPOCARNET");
		tablas.add ("CARNET");
		tablas.add ("DOMICILIARIO");
		tablas.add ("EMPLEADO");
		tablas.add ("VEHICULO");
		tablas.add ("VISITANCENTROCOMERCIAL");
		tablas.add ("VISITANASCENSOR");
		tablas.add ("VISITANBAÑO");
		tablas.add ("VISITANLOCALCOMERCIAL");
		tablas.add ("VISITANPARQUEADERO");
		tablas.add ("REGISTRANCARNET");
		tablas.add ("REGISTRANVEHICULO");
		tablas.add("horario_sequence");
		tablas.add ("capacidadNormal_sequence");
		tablas.add ("area_sequence");
		tablas.add ("tipoLocal_sequence");
		tablas.add ("tipoCarnet_sequence");
		tablas.add ("tipoLector_sequence");
		tablas.add ("tipoVisitante_sequence");

	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAforoAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaAforoAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaAforoAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAforoAndes ();
		}
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaAforoAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaAforoAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAforoAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlHorario = new SQLHorario(this);
		sqlTipoVisitante = new SQLTipoVisitante(this);
		sqlVisitante = new SQLVisitante(this);
		sqlCentroComercial = new SQLCentroComercial(this);
		sqlArea = new SQLArea(this);
		sqlCapacidadNormal = new SQLCapacidadNormal(this);
		sqlAscensor = new SQLAscensor(this);
		sqlBaño = new SQLBaño(this);
		sqlParqueadero = new SQLParqueadero(this);
		sqlTipoLocal = new SQLTipoLocal(this);
		sqlLocalComercial = new SQLLocalComercial(this);
		sqlZonaCirculacion = new SQLZonaCirculacion(this);
		sqlTipoLector = new SQLTipoLector(this);
		sqlLector = new SQLLector(this);
		sqlTipoCarnet = new SQLTipoCarnet(this);
		sqlCarnet = new SQLCarnet (this);
		sqlDomiciliario = new SQLDomiciliario(this);
		sqlEmpleado = new SQLEmpleado(this);
		sqlVehiculo = new SQLVehiculo(this);
		sqlVisitanCentroComercial = new SQLVisitanCentroComercial(this);
		sqlVisitanAscensor = new SQLVisitanAscensor(this);
		sqlVisitanBaño = new SQLVisitanBaño(this);
		sqlVisitanLocalComercial = new SQLVisitanLocalComercial(this);
		sqlVisitanParqueadero = new SQLVisitanParqueadero(this);
		sqlRegistranCarnet = new SQLRegistranCarnet(this);
		sqlRegistranVehiculo = new SQLRegistranVehiculo(this);
		sqlUtil = new SQLUtil(this);
	}


	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Horario de AforoAndes
	 */
	public String darTablaHorario ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoVisitante de AforoAndes
	 */
	public String darTablaTipoVisitante ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitante de AforoAndes
	 */
	public String darTablaVisitante ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de CentroComercial de AforoAndes
	 */
	public String darTablaCentroComercial ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Area de AforoAndes
	 */
	public String darTablaArea ()
	{
		return tablas.get (4);
	}


	/**
	 * @return La cadena de caracteres con el nombre de la tabla de CapacidadNormal de AforoAndes
	 */
	public String darTablaCapacidadNormal ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Ascensor de AforoAndes
	 */
	public String darTablaAscensor ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Baño de AforoAndes
	 */
	public String darTablaBaño ()
	{
		return tablas.get (7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Parqueadero de AforoAndes
	 */
	public String darTablaParqueadero ()
	{
		return tablas.get (8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoLocal de AforoAndes
	 */
	public String darTablaTipoLocal ()
	{
		return tablas.get (9);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de LocalComercial de AforoAndes
	 */
	public String darTablaLocalComercial ()
	{
		return tablas.get (10);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ZonaCirculacion de AforoAndes
	 */
	public String darTablaZonaCirculacion ()
	{
		return tablas.get (11);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoLector de AforoAndes
	 */
	public String darTablaTipoLector ()
	{
		return tablas.get (12);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Lector de AforoAndes
	 */
	public String darTablaLector ()
	{
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoCarnet de AforoAndes
	 */
	public String darTablaTipoCarnet ()
	{
		return tablas.get (14);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Carnet de AforoAndes
	 */
	public String darTablaCarnet ()
	{
		return tablas.get (15);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Domiciliario de AforoAndes
	 */
	public String darTablaDomiciliario ()
	{
		return tablas.get (16);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Empleado de AforoAndes
	 */
	public String darTablaEmpleado ()
	{
		return tablas.get (17);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Vehiculo de AforoAndes
	 */
	public String darTablaVehiculo ()
	{
		return tablas.get (18);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de VisitanCentroComercial de AforoAndes
	 */
	public String darTablaVisitanCentroComercial ()
	{
		return tablas.get (19);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de VisitanAscensor de AforoAndes
	 */
	public String darTablaVisitanAscensor ()
	{
		return tablas.get (20);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de VisitanBaño de AforoAndes
	 */
	public String darTablaVisitanBaño ()
	{
		return tablas.get (21);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de VisitanLocalComercial de AforoAndes
	 */
	public String darTablaVisitanLocalComercial ()
	{
		return tablas.get (22);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de VisitanParqueadero de AforoAndes
	 */
	public String darTablaVisitanParqueadero ()
	{
		return tablas.get (23);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de RegistranCarnet de AforoAndes
	 */
	public String darTablaRegistranCarnet ()
	{
		return tablas.get (24);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de RegistranVehiculo de AforoAndes
	 */
	public String darTablaRegistranVehiculo ()
	{
		return tablas.get (25);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador
	 */
	public String darSeqHorario()
	{
		return tablas.get (26);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador
	 */
	public String darSeqCapacidadNormal ()
	{
		return tablas.get (27);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador
	 */
	public String darSeqArea ()
	{
		return tablas.get (28);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador
	 */
	public String darSeqTipoLocal ()
	{
		return tablas.get (29);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador
	 */
	public String darSeqTipoCarnet ()
	{
		return tablas.get (30);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador
	 */
	public String darSeqTipoLector ()
	{
		return tablas.get (31);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador
	 */
	public String darSeqTipoVisitante ()
	{
		return tablas.get (32);
	}

	/**
	 * Transacción para el generador de secuencia de Horario
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Horario
	 */
	private long nextvalHorario ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia para Horario: " + resp);
		return resp;
	}


	/**
	 * Transacción para el generador de secuencia de CapacidadNormal
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de CapacidadNormal
	 */
	private long nextvalCapacidadNormal ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia para CapacidadNormal: " + resp);
		return resp;
	}

	/**
	 * Transacción para el generador de secuencia de Area
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Area
	 */
	private long nextvalArea ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia para Area: " + resp);
		return resp;
	}

	/**
	 * Transacción para el generador de secuencia de TipoLocal
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de TipoLocal
	 */
	private long nextvalTipoLocal ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia para TipoLocal: " + resp);
		return resp;
	}

	/**
	 * Transacción para el generador de secuencia de TipoCarnet
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de TipoCarnet
	 */
	private long nextvalTipoCarnet ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia para TipoCarnet: " + resp);
		return resp;
	}

	/**
	 * Transacción para el generador de secuencia de TipoLector
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de TipoLector
	 */
	private long nextvalTipoLector ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia para TipoLector: " + resp);
		return resp;
	}

	/**
	 * Transacción para el generador de secuencia de TipoVisitante
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de TipoVisitante
	 */
	private long nextvalTipoVisitante ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia para TipoVisitante: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las ÁREAS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ÁREA
	 * Adiciona entradas al log de la aplicación
	 * @param valor - El valor del área
	 * @param aforo - El aforo del área
	 * @return El objeto Área adicionado. null si ocurre alguna Excepción
	 */
	public Area adicionarArea(double valor, int aforo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long idArea = nextvalArea();
			long tuplasInsertadas = sqlArea.adicionarArea(pm, idArea, valor, aforo);
			tx.commit();

			log.trace ("Inserción del área con valor: " + valor + " y aforo: " + aforo + "| " + tuplasInsertadas + " tuplas insertadas");

			return new Area (idArea, valor, aforo);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Area, dado el valor del área
	 * Adiciona entradas al log de la aplicación
	 * @param valorArea - El valor del area
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAreaPorValor (double valor) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlArea.eliminarAreasPorValor(pm, valor);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Area, dado el identificador del área
	 * Adiciona entradas al log de la aplicación
	 * @param idArea - El identificador del área
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAreaPorId (long idArea) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlArea.eliminarAreaPorId(pm, idArea);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Area con un identificador dado
	 * @param idArea - El identificador del área
	 * @return El objeto Area, construido con base en las tuplas de la tabla AREA con el identificador dado
	 */
	public Area darAreaPorId (long idArea)
	{
		return sqlArea.darAreaPorId (pmf.getPersistenceManager(), idArea);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Area que tienen el valor dado
	 * @param valor - El valor del área
	 * @return La lista de objetos Area, construidos con base en las tuplas de la tabla AREA
	 */
	public List<Area> darAreaPorValor (double valor)
	{
		return sqlArea.darAreasPorValor(pmf.getPersistenceManager(), valor);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Area
	 * @return La lista de objetos Area, construidos con base en las tuplas de la tabla AREA
	 */
	public List<Area> darAreas ()
	{
		return sqlArea.darAreas (pmf.getPersistenceManager());
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de un AREA
	 * @param idArea - El identificador del área que se quiere modificar
	 * @param valor - El nuevo valor del área
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarValorArea (long idArea, double valor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlArea.cambiarValorArea(pm, idArea, valor);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de un AREA
	 * @param idArea - El identificador del área que se quiere modificar
	 * @param aforo - El nuevo aforo del área
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarAforoArea (long idArea, int aforo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlArea.cambiarAforoArea(pm, idArea, aforo);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ASCENSORES
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ASCENSOR
	 * Adiciona entradas al log de la aplicación
	 * @param idAscensor - El identificador del ascensor
	 * @param capacidadNormal - El identificador de la capacidad normal del ascensor
	 * @param area - El identificador del área del ascensor
	 * @param pesoMaximo - El peso máximo del ascensor
	 * @param idCentroComercial - El identificador del centro comercial del ascensor
	 * @return El objeto Ascensor adicionado. null si ocurre alguna Excepción
	 */
	public Ascensor adicionarAscensor(String idAscensor, long capacidadNormal, long area, double pesoMaximo, String idCentroComercial) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlAscensor.adicionarAscensor(pm, idAscensor, capacidadNormal, area, pesoMaximo, idCentroComercial);
			tx.commit();

			log.trace ("Inserción de Ascensor: " + idAscensor + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Ascensor(idAscensor, area, capacidadNormal, pesoMaximo, idCentroComercial);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ASCENSOR, dado el identificador del ascensor
	 * Adiciona entradas al log de la aplicación
	 * @param idAscensor - El identificador del ascensor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAscensorPorId (String idAscensor) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlAscensor.eliminarAscensorPorId (pm, idAscensor);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ASCENSOR, dado el peso máximo del ascensor
	 * Adiciona entradas al log de la aplicación
	 * @param pesoMaximo - El peso máximo del ascensor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAscensorPorPesoMaximo (double pesoMaximo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlAscensor.eliminarAscensorPorPesoMaximo (pm, pesoMaximo);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que consulta todas las tuplas en la tabla ASCENSOR que tienen el identificador dado
	 * @param idAscensor - El identificador del ascensor
	 * @return El objeto ASCENSOR, construido con base en la tuplas de la tabla ASCENSOR, que tiene el identificador dado
	 */
	public Ascensor darAscensorPorId (String idAscensor)
	{
		return sqlAscensor.darAscensorPorId (pmf.getPersistenceManager(), idAscensor);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ASCENSOR que tienen el peso máximo dado
	 * @param pesoMaximo - El peso máximo del ascensor
	 * @return La lista de objetos ASCENSOR, construidos con base en las tuplas de la tabla ASCENSOR
	 */
	public List<Ascensor> darAscensoresPorPesoMaximo (double pesoMaximo)
	{
		return sqlAscensor.darAscensoresPorPesoMaximo (pmf.getPersistenceManager(), pesoMaximo);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ASCENSOR
	 * @return La lista de objetos ASCENSOR, construidos con base en las tuplas de la tabla ASCENSOR
	 */
	public List<Ascensor> darAscensores ()
	{
		return sqlAscensor.darAscensores (pmf.getPersistenceManager());
	}


	/**
	 * Método que actualiza, de manera transaccional, el valor de un ASCENSOR
	 * @param idAscensor - El identificador del ascensor que se quiere modificar
	 * @param pesoMaximo - El nuevo peso máximo del ascensor
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarPesoMaximoAscensor (String idAscensor, double pesoMaximo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlAscensor.cambiarPesoMaximoAscensor(pm, idAscensor, pesoMaximo);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * Método que actualiza, de manera transaccional, el valor de un ASCENSOR
	 * @param idAscensor - El identificador del parqueadero que se quiere modificar
	 * @param area - Identificador de la nueva área de un ascensor
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarAreaAscensor (String idAscensor, long area)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlAscensor.cambiarAreaAscensor (pm, idAscensor, area);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	

	/* ****************************************************************
	 * 			Métodos para manejar los BAÑOS
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BAÑO
	 * Adiciona entradas al log de la aplicación
	 * @param idBaño - El identificador del baño
	 * @param capacidadNormal - El identificador de la capacidad normal del baño
	 * @param area - El identificador del área del baño
	 * @param numeroSanitarios - El número de sanitarios del baño
	 * @param idCentroComercial - El identificador del centro comercial del baño
	 * @return El objeto Baño adicionado. null si ocurre alguna Excepción
	 */
	public Baño adicionarBaño (String idBaño, int cupoActual, long capacidadNormal, long area, int numeroSanitarios, String idCentroComercial) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlBaño.adicionarBaño (pm, idBaño, capacidadNormal, area, numeroSanitarios, idCentroComercial);
			tx.commit();

			log.trace ("Inserción de Baño: " + idBaño + "| " + tuplasInsertadas + " tuplas insertadas");

			return new Baño(idBaño, area, capacidadNormal, numeroSanitarios, idCentroComercial);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAÑO, dado el identificador del baño
	 * Adiciona entradas al log de la aplicación
	 * @param idBaño - El identificador del baño
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarBañoPorId (String idBaño) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlBaño.eliminarBañoPorId (pm, idBaño);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAÑO, dado el número de sanitarios del baño
	 * Adiciona entradas al log de la aplicación
	 * @param sanitarios - El número de sanitarios del baño
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarBañoPorSanitarios (int sanitarios) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlBaño.eliminarBañoPorSanitarios(pm, sanitarios);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BAÑO que tienen el identificador dado
	 * @param idBaño - El identificador del baño
	 * @return El objeto BAÑO, construido con base en la tuplas de la tabla BAÑO, que tiene el identificador dado
	 */
	public Baño darBañoPorId (String idBaño)
	{
		return sqlBaño.darBañoPorId (pmf.getPersistenceManager(), idBaño);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BAÑO que tienen el número de sanitarios dado
	 * @param numeroSanitarios - El número de sanitarios del baño
	 * @return La lista de objetos BAÑO, construidos con base en las tuplas de la tabla BAÑO
	 */
	public List<Baño> darBañosPorSanitarios (int numeroSanitarios)
	{
		return sqlBaño.darBañosPorSanitarios (pmf.getPersistenceManager(), numeroSanitarios);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BAÑO
	 * @return La lista de objetos BAÑO, construidos con base en las tuplas de la tabla BAÑO
	 */
	public List<Baño> darBaños ()
	{
		return sqlBaño.darBaños (pmf.getPersistenceManager());
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de un BAÑO
	 * @param idBaño - El identificador del baño que se quiere modificar
	 * @param numeroSanitarios - El nuevo número de sanitarios del baño
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarNumeroSanitariosBaño (String idBaño, int numeroSanitarios)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlBaño.cambiarNumeroSanitariosBaño(pm, idBaño, numeroSanitarios);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * Método que actualiza, de manera transaccional, el valor de un BAÑO
	 * @param idBaño - El identificador del parqueadero que se quiere modificar
	 * @param area - Identificador de la nueva área de un baño
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarAreaBaño (String idBaño, long area)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlBaño.cambiarAreaBaño (pm, idBaño, area);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las CAPACIDADESNORMALES
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla CAPACIDADNORMAL
	 * Adiciona entradas al log de la aplicación
	 * @param valor - El valor de la capacidad normal
	 * @param aforo - El aforo de la capacidad normal
	 * @return El objeto CapacidadNormal adicionado. null si ocurre alguna Excepción
	 */
	public CapacidadNormal adicionarCapacidadNormal (double valor, int aforo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long idCapacidadNormal = nextvalCapacidadNormal();
			long tuplasInsertadas = sqlCapacidadNormal.adicionarCapacidadNormal(pm, idCapacidadNormal, valor, aforo);
			tx.commit();

			log.trace ("Inserción de la capacidad normal con valor: " + valor + " y aforo: " + aforo + "| " + tuplasInsertadas + " tuplas insertadas");

			return new CapacidadNormal (idCapacidadNormal, valor, aforo);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CapacidadNormal, dado el valor de la capacidad normal
	 * Adiciona entradas al log de la aplicación
	 * @param valorArea - El valor de la capacidad normal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCapacidadNormalPorValor (double valor) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCapacidadNormal.eliminarCapacidadNormalesPorValor(pm, valor);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CapacidadNormal, dado el identificador de la capacidad normal
	 * Adiciona entradas al log de la aplicación
	 * @param idCapacidadNormal - El identificador de la capacidad normal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCapacidadNormalPorId (long idCapacidadNormal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCapacidadNormal.eliminarCapacidadNormalPorId(pm, idCapacidadNormal);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CapacidadNormal con un identificador dado
	 * @param idCapacidadNormal - El identificador de la capacidad normal
	 * @return El objeto CapacidadNormal, construido con base en las tuplas de la tabla CAPACIDADNORMAL con el identificador dado
	 */
	public CapacidadNormal darCapacidadNormalPorId (long idCapacidadNormal)
	{
		return sqlCapacidadNormal.darCapacidadNormalPorId (pmf.getPersistenceManager(), idCapacidadNormal);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CapacidadNormal que tienen el valor dado
	 * @param valor - El valor de la capacidad normal
	 * @return La lista de objetos CapacidadNormal, construidos con base en las tuplas de la tabla CAPACIDADNORMAL
	 */
	public List<CapacidadNormal> darCapacidadesNormalesPorValor (double valor)
	{
		return sqlCapacidadNormal.darCapacidadesNormalesPorValor(pmf.getPersistenceManager(), valor);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CapacidadNormal
	 * @return La lista de objetos CapacidadNormal, construidos con base en las tuplas de la tabla CAPACIDADNORMAL
	 */
	public List<CapacidadNormal> darCapacidadesNormales ()
	{
		return sqlCapacidadNormal.darCapacidadesNormales (pmf.getPersistenceManager());
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de una CAPACIDADNORMAL
	 * @param idCapacidadNormal - El identificador de la capacidad normal que se quiere modificar
	 * @param valor - El nuevo valor de la capacidad normal
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarValorCapacidad (long idCapacidadNormal, double valor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCapacidadNormal.cambiarValorCapacidad(pm, idCapacidadNormal, valor);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de una CAPACIDADNORMAL
	 * @param idCapacidadNormal - El identificador de la capacidad normal que se quiere modificar
	 * @param aforo - El nuevo aforo de la capacidad normal
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarAforoCapacidad (long idCapacidadNormal, int aforo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCapacidadNormal.cambiarAforoCapacidad(pm, idCapacidadNormal, aforo);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar los CARNETS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla CARNET
	 * Adiciona entradas al log de la aplicación
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @param idVisitante - El identificador del visitante del carnet
	 * @return El objeto Carnet adicionado. null si ocurre alguna Excepción
	 */
	public Carnet adicionarCarnet (long tipoCarnet, String idVisitante)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCarnet.adicionarCarnet(pm, tipoCarnet, idVisitante);
			tx.commit();

			log.trace ("Inserción del carnet con tipoCarnet: " + tipoCarnet + " e idVisitante: " + idVisitante + "| " + tuplasInsertadas + " tuplas insertadas");

			return new Carnet(tipoCarnet, idVisitante);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Carnet, dado el tipo del carnet
	 * Adiciona entradas al log de la aplicación
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCarnetsPorTipo (long tipoCarnet) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCarnet.eliminarCarnetsPorTipo (pm, tipoCarnet);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Carnet, dado identificador del visitante
	 * Adiciona entradas al log de la aplicación
	 * @param idVisitante - El identificador del visitante del carnet
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCarnetPorIdVisitante (String idVisitante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCarnet.eliminarCarnetPorIdVisitante(pm, idVisitante);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Carnet con un tipo dado
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @return La lista de objetos Carnet que tienen el tipo buscado, construidos con base en las tuplas de la tabla CARNET
	 */
	public List<Carnet> darCarnetsPorTipo (long tipoCarnet)
	{
		return sqlCarnet.darCarnetsPorTipo (pmf.getPersistenceManager(), tipoCarnet);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla Carnet con un identificador de visitante dado
	 * @param idVisitante - El identificador del dueño del carnet
	 * @return El objeto Carnet, construido con base en las tuplas de la tabla CARNET con el idVisitante dado
	 */
	public Carnet darCarnetPorIdVisitante (String idVisitante)
	{
		return sqlCarnet.darCarnetPorIdVisitante (pmf.getPersistenceManager(), idVisitante);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Carnet
	 * @return La lista de objetos Carnet, construidos con base en las tuplas de la tabla CARNET
	 */
	public List<Carnet> darCarnets ()
	{
		return sqlCarnet.darCarnets (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar lOS CENTROCOMERCIALES
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla CENTROCOMERCIAL
	 * Adiciona entradas al log de la aplicación
	 * @param idCentroComercial - El identificador del centro comercial
	 * @param nombre - El nombre del centro comercial
	 * @return El objeto Área adicionado. null si ocurre alguna Excepción
	 */
	public CentroComercial adicionarCentroComercial (String id, String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCentroComercial.adicionarCentroComercial (pm, id, nombre);
			tx.commit();

			log.trace ("Inserción de Centro Comercial:  " + id + "| " + tuplasInsertadas + " tuplas insertadas");

			return new CentroComercial (id, nombre);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CentroComercial, dado el identificador del centro comercial
	 * Adiciona entradas al log de la aplicación
	 * @param idCentroComercial - El identificador del centro comercial
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCentroComercialPorId (String idCentroComercial) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCentroComercial.eliminarCentroComercialPorId (pm, idCentroComercial);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CentroComercial, dado el identificador del centro comercial
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del centro comercial
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCentroComercialPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCentroComercial.eliminarCentroComercialPorNombre(pm, nombre);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CentroComercial con un identificador dado
	 * @param idCentroComercial - El identificador del centro comercial
	 * @return El objeto CentroComercial, construido con base en las tuplas de la tabla CENTROCOMERCIAL con el identificador dado
	 */
	public CentroComercial darCentroComercialPorId (String idCentroComercial)
	{
		return sqlCentroComercial.darCentroComercialPorId(pmf.getPersistenceManager(), idCentroComercial);
	}

	
	/**
	 * Método que consulta todas las tuplas en la tabla CentroComercial
	 * @return La lista de objetos CentroComercial, construidos con base en las tuplas de la tabla CENTROCOMERCIAL
	 */
	public List<CentroComercial> darCentrosComerciales ()
	{
		return sqlCentroComercial.darCentrosComerciales (pmf.getPersistenceManager());
	}

	/**
	 * Método que actualiza, de manera transaccional, el nombre de un CENTROCOMERCIAL
	 * @param idCentroComercial - El identificador del centro comercial 
	 * @param nombre - El nuevo nombre del Centro Comercial
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarValorCapacidad (String idCentroComercial, String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCentroComercial.cambiarNombreCentroComercial(pm, idCentroComercial, nombre);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar los DOMICILIARIOS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla DOMICILIARIO
	 * Adiciona entradas al log de la aplicación
	 * @param idVisitante - El identificador del visitante
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return El objeto Domiciliario adicionado. null si ocurre alguna Excepción
	 */
	public Domiciliario adicionarDomiciliario (String idVisitante, String empresaDomicilios)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlDomiciliario.adicionarDomiciliario(pm, idVisitante, empresaDomicilios);
			tx.commit();

			log.trace ("Inserción del Domiciliario: " + idVisitante + "| " + tuplasInsertadas + " tuplas insertadas");

			return new Domiciliario(idVisitante, empresaDomicilios);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Domiciliario, dado el identificador del domiciliario
	 * Adiciona entradas al log de la aplicación
	 * @param idVisitante - El identificador del domiciliario
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarDomiciliarioPorId (String idVisitante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlDomiciliario.eliminarDomiciliarioPorId(pm, idVisitante);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Domiciliario, dado la empresa donde trabaja el domiciliario
	 * Adiciona entradas al log de la aplicación
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarDomiciliarioPorEmpresa (String empresaDomicilios) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlDomiciliario.eliminarDomiciliarioPorEmpresa(pm, empresaDomicilios);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Domiciliario con un identificador dado
	 * @param idDomiciliario - El identificador de la capacidad normal
	 * @return El objeto Domiciliario, construido con base en las tuplas de la tabla DOMICILIARIO con el identificador dado
	 */
	public Domiciliario darDomiciliarioPorId (String idDomiciliario)
	{
		return sqlDomiciliario.darDomiciliarioPorId (pmf.getPersistenceManager(), idDomiciliario);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Domiciliario que tienen el valor dado
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return La lista de objetos Domiciliario, construidos con base en las tuplas de la tabla DOMICILIARIO
	 */
	public List<Domiciliario> darDomiciliariosPorEmpresa (String empresaDomicilios)
	{
		return sqlDomiciliario.darDomiciliariosPorEmpresa(pmf.getPersistenceManager(), empresaDomicilios);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Domiciliario
	 * @return La lista de objetos Domiciliario, construidos con base en las tuplas de la tabla DOMICILIARIO
	 */
	public List<Domiciliario> darDomiciliarios ()
	{
		return sqlDomiciliario.darDomiciliarios (pmf.getPersistenceManager());
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de una DOMICILIARIO
	 * @param id - El identificador del domiciliario que se quiere modificar
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarEmpresaDomiciliario (String idDomiciliario, String empresaDomicilios)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlDomiciliario.cambiarEmpresaDomiciliario(pm, idDomiciliario, empresaDomicilios);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los LOCALESCOMERCIALES
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla LOCALCOMERCIAL
	 * Adiciona entradas al log de la aplicación
	 * @param idLocalComercial - El identificador del local
	 * @param capacidadNormal - La capacidad común del local comercial
	 * @param area - El área del local comercial
	 * @param tipoLocal - El tipo del local comercial
	 * @param activoBooleano - Si está en funcionamiento o no 
	 * @param idCentroComercial - El identificador del centro comercial al que pertenece el local comercial
	 * @return El objeto LocalComercial adicionado. null si ocurre alguna Excepción
	 */
	public LocalComercial adicionarLocalComercial (String idLocalComercial, long capacidadNormal, long area, long tipoLocal, boolean activoBooleano, String idCentroComercial) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			int activo = 0;
			if ( activoBooleano )
				activo = 1;
			long tuplasInsertadas = sqlLocalComercial.adicionarLocalComercial(pm, idLocalComercial, capacidadNormal, area, tipoLocal, activo, idCentroComercial);
			tx.commit();

			log.trace ("Inserción de LocalComercial: " + idLocalComercial + ": " + tuplasInsertadas + " tuplas insertadas");

			return new LocalComercial(idLocalComercial, area, capacidadNormal, tipoLocal, activoBooleano, idCentroComercial);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla LOCALCOMERCIAL, dado el identificador del local comercial
	 * Adiciona entradas al log de la aplicación
	 * @param idLocalComercial - El identificador del local comercial
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarLocalComercialPorId (String idLocalComercial) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlLocalComercial.eliminarLocalComercialPorId(pm, idLocalComercial);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla LOCALCOMERCIAL, dada la actividad del local comercial
	 * Adiciona entradas al log de la aplicación
	 * @param activo - La actividad del local comercial
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarLocalComercialPorActividad (int activo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlLocalComercial.eliminarLocalComercialPorActividad(pm, activo);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla LOCALCOMERCIAL que tienen el identificador dado
	 * @param idLocalComercial - El identificador del local comercial
	 * @return El objeto LOCALCOMERCIAL, construido con base en la tuplas de la tabla LOCALCOMERCIAL, que tiene el identificador dado
	 */
	public LocalComercial darLocalComercialPorId (String idLocalComercial)
	{
		return sqlLocalComercial.darLocalComercialPorId (pmf.getPersistenceManager(), idLocalComercial);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla LOCALCOMERCIAL que tienen el id del centro comercial dado
	 * @param idCentroComercial - El identificador del centro comercial en el que se encuentra el local
	 * @return La lista de objetos LOCALCOMERCIAL, construidos con base en las tuplas de la tabla LOCALCOMERCIAL
	 */
	public List<LocalComercial> darLocalesComercialesPorIDCC (String idCentroComercial)
	{
		return sqlLocalComercial.darLocalesComercialesPorIDCC (pmf.getPersistenceManager(), idCentroComercial);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla LOCALCOMERCIAL
	 * @return La lista de objetos LOCALCOMERCIAL, construidos con base en las tuplas de la tabla LOCALCOMERCIAL
	 */
	public List<LocalComercial> darLocalesComerciales ()
	{
		return sqlLocalComercial.darLocalesComerciales (pmf.getPersistenceManager());
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de un LOCALCOMERCIAL
	 * @param idLocal - El identificador del local que se quiere modificar
	 * @param area - Identificador de la nueva área de un local comercial
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarAreaLocalComercial (String idLocal, long area)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlLocalComercial.cambiarAreaLocalComercial(pm, idLocal, area);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar los PARQUEADEROS
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla PARQUEADERO
	 * Adiciona entradas al log de la aplicación
	 * @param idParqueadero - El identificador del parqueadero
	 * @param capacidadNormal - El identificador de la capacidad normal del parqueadero
	 * @param area - El identificador del área del parqueadero
	 * @param idCentroComercial - El identificador del centro comercial del parqueadero
	 * @return El objeto Parqueadero adicionado. null si ocurre alguna Excepción
	 */
	public Parqueadero adicionarParqueadero (String idParqueadero, long capacidadNormal, long area, String idCentroComercial) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlParqueadero.adicionarParqueadero(pm, idParqueadero, capacidadNormal, area, idCentroComercial);
			tx.commit();

			log.trace ("Inserción de Parqueadero: " + idParqueadero + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Parqueadero(idParqueadero, area, capacidadNormal, idCentroComercial);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla PARQUEADERO, dado el identificador del parqueadero
	 * Adiciona entradas al log de la aplicación
	 * @param idParqueadero - El identificador del parqueadero
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarParqueaderoPorId (String idParqueadero) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlParqueadero.eliminarParqueaderoPorId(pm, idParqueadero);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	
	/**
	 * Método que consulta todas las tuplas en la tabla PARQUEADERO que tienen el identificador dado
	 * @param idParqueadero - El identificador del parqueadero
	 * @return El objeto LOCALCOMERCIAL, construido con base en la tuplas de la tabla PARQUEADERO, que tiene el identificador dado
	 */
	public Parqueadero darParqueaderoPorId (String idParqueadero)
	{
		return sqlParqueadero.darParqueaderoPorId (pmf.getPersistenceManager(), idParqueadero);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PARQUEADERO
	 * @return La lista de objetos PARQUEADERO, construidos con base en las tuplas de la tabla PARQUEADERO
	 */
	public List<Parqueadero> darParqueaderos ()
	{
		return sqlParqueadero.darParqueaderos (pmf.getPersistenceManager());
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de un PARQUEADERO
	 * @param idParqueadero - El identificador del parqueadero que se quiere modificar
	 * @param area - Identificador de la nueva área de un parqueadero
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarAreaParqueadero (String idParqueadero, long area)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlParqueadero.cambiarAreaParqueadero (pm, idParqueadero, area);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE CARNET
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoCarnet
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo de carnet
	 * @return El objeto TipoCarnet adicionado. null si ocurre alguna Excepción
	 */
	public TipoCarnet adicionarTipoCarnet(String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long idTipoCarnet = nextvalTipoCarnet ();
			long tuplasInsertadas = sqlTipoCarnet.adicionarTipoCarnet(pm, idTipoCarnet, tipo);
			tx.commit();

			log.trace ("Inserción de tipo de carnet: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");

			return new TipoCarnet(idTipoCarnet, tipo);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoCarnet, dado el nombre del tipo de carnet
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo de carnet
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoCarnetPorNombre (String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlTipoCarnet.eliminarTipoCarnetPorTipo(pm, tipo);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoCarnet, dado el identificador del tipo de carnet
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoCarnet - El identificador del tipo de carnet
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoCarnetPorId (long idTipoCarnet) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlTipoCarnet.eliminarTipoCarnetPorId(pm, idTipoCarnet);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoCarnet
	 * @return La lista de objetos TipoCarnet, construidos con base en las tuplas de la tabla TipoCarnet
	 */
	public List<TipoCarnet> darTiposCarnet ()
	{
		return sqlTipoCarnet.darTiposCarnet (pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoCarnet que tienen el nombre dado
	 * @param tipo - El nombre del tipo de carnet
	 * @return La lista de objetos TipoCarnet, construidos con base en las tuplas de la tabla TipoCarnet
	 */
	public List<TipoCarnet> darTiposCarnetPorNombre (String tipo)
	{
		return sqlTipoCarnet.darTiposCarnetPorTipo (pmf.getPersistenceManager(), tipo);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoCarnet con un identificador dado
	 * @param idTipoCarnet - El identificador del tipo de carnet
	 * @return El objeto TipoCarnet, construido con base en las tuplas de la tabla TipoCarnet con el identificador dado
	 */
	public TipoCarnet darTipoCarnetPorId (long idTipoCarnet)
	{
		return sqlTipoCarnet.darTipoCarnetPorId (pmf.getPersistenceManager(), idTipoCarnet);
	}

	/* ****************************************************************
	 * 			Métodos para manejar los EMPLEADOS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Empleado
	 * Adiciona entradas al log de la aplicación
	 * @param idvisitante - El identificador del visitante
	 * @param lugartrabajo - El lugar de trabajo del empleado 
	 * @return El objeto Empleado adicionado. null si ocurre alguna Excepción
	 */
	public Empleado adicionarEmpleado(String idvisitante, String lugartrabajo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlEmpleado.adicionarEmpleado(pm, idvisitante, lugartrabajo);
			tx.commit();

			log.trace ("Inserción del Empleado " + idvisitante + "| " + tuplasInsertadas + " tuplas insertadas");

			return new Empleado(idvisitante, lugartrabajo);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Empleado, dado el identificador de este 
	 * Adiciona entradas al log de la aplicación
	 * @param idvisitante - El identificador del empleado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarEmpleadoPorID(String idvisitante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEmpleado.eliminarEmpleadoPorID(pm, idvisitante);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Empleado, dado el lugar de trabajo del empleado
	 * Adiciona entradas al log de la aplicación
	 * @param lugartrabajo - El lugar de trabajo del empleado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarEmpleadoPorLugar (String lugartrabajo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEmpleado.eliminarEmpleadoPorLugarDeTrabajo(pm, lugartrabajo);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Empleado con un identificador dado
	 * @param idEmpleado - El identificador del empleado 
	 * @return El objeto Empleado, construido con base en las tuplas de la tabla EMPLEADO con el identificador dado
	 */
	public Empleado darEmpleadoPorId (String idEmpleado)
	{
		return sqlEmpleado.darEmpleadoPorID(pmf.getPersistenceManager(), idEmpleado);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla EMPLEADO que tienen el valor dado
	 * @param lugartrabajo - El lugar de trabajo del empleado 
	 * @return La lista de objetos Empleado, construidos con base en las tuplas de la tabla EMPLEADO
	 */
	public List<Empleado> darEmpleadosPorLugar (String lugar)
	{
		return sqlEmpleado.darEmpleadoPorLugar(pmf.getPersistenceManager(),lugar);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Empleado
	 * @return La lista de objetos Empleado, construidos con base en las tuplas de la tabla EMPLEADO
	 */
	public List<Empleado> darEmpleados ()
	{
		return sqlEmpleado.darEmpleados(pmf.getPersistenceManager());
	}
	
	/**
	 * Método que actualiza, de manera transaccional, el valor de un EMPLEADO
	 * @param id - El identificador del empleado que se quiere modificar
	 * @param lugartrabajo - El lugar de trabajo del empleado
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarLugarEmpleado(String id, String lugartrabajo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEmpleado.cambiarCompañia(pm, id, lugartrabajo);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar LOS HORARIOS 
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla HORARIO
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del horario
     * @param hora - La hora del horario
     * @param minuto - El minuto del horario
	 * @return El objeto HORARIO adicionado. null si ocurre alguna Excepción
	 */
	public Horario adicionarHorario(long id, int hora, int minuto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlHorario.adicionarHorario(pm, id, hora, minuto);
			tx.commit();

			log.trace ("Inserción de un Horario:  " + id + "| " + tuplasInsertadas + " tuplas insertadas");

			return new Horario (id, hora, minuto);
		}
		catch (Exception e)
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


}