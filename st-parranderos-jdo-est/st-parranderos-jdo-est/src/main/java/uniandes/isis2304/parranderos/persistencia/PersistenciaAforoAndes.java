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


import java.math.BigDecimal;
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
import uniandes.isis2304.parranderos.negocio.Visitante;
import uniandes.isis2304.parranderos.negocio.CapacidadNormal;
import uniandes.isis2304.parranderos.negocio.Ascensor;
import uniandes.isis2304.parranderos.negocio.TipoLocal;
import uniandes.isis2304.parranderos.negocio.Area;
import uniandes.isis2304.parranderos.negocio.LocalComercial;
import uniandes.isis2304.parranderos.negocio.TipoCarnet;
import uniandes.isis2304.parranderos.negocio.TipoVisitante;

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
	 * 			Métodos para manejar los TIPOS DE CARNET
	 *****************************************************************/
	
	
	
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
 
	
 }
