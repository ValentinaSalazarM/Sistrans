package uniandes.isis2304.parranderos.test;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.aforoandes.negocio.AforoAndes;
import uniandes.isis2304.aforoandes.negocio.Ascensor;
import uniandes.isis2304.aforoandes.negocio.VOArea;
import uniandes.isis2304.aforoandes.negocio.VOAscensor;
import uniandes.isis2304.aforoandes.negocio.VOCapacidadNormal;
import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;
import uniandes.isis2304.aforoandes.negocio.VOHorario;
import uniandes.isis2304.aforoandes.negocio.VOLector;
import uniandes.isis2304.aforoandes.negocio.VORegistranCarnet;
import uniandes.isis2304.aforoandes.negocio.VOTipoCarnet;
import uniandes.isis2304.aforoandes.negocio.VOTipoLector;
import uniandes.isis2304.aforoandes.negocio.VOTipoVisitante;
import uniandes.isis2304.aforoandes.negocio.VOVisitante;



/**
 * @author 
 *
 */
public class RegistranCarnet 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(RegistranCarnet.class.getName());

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * La clase que se quiere probar
	 */
	private AforoAndes aforoAndes;

	/* ****************************************************************
	 * 			Métodos de prueba para la tabla RegistranCarnet - Creación 
	 *****************************************************************/

	@Test
	public void primeraVez() 
	{
		// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre RegistranCarnet");
			aforoAndes = new AforoAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
			//   			e.printStackTrace();
			log.info ("Prueba de CRD de RegistranCarnet incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de RegistranCarnet incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}

		// Ahora si se pueden probar las operaciones
		try
		{
			aforoAndes.limpiarAforoAndes (); 
			// Lectura de los tipos de bebida con la tabla vacía
			List <VORegistranCarnet> lista = aforoAndes.darVORegistranCarnet();
			assertEquals("No deben haber registros añadidos!!", 0, lista.size ());

			//Area
			double valor = 200;
			int aforo = 13;
			VOArea area1 = aforoAndes.adicionarArea(valor, aforo);
			
			//Capacidad normal 
			int value = 100; 
			int af = 50;

			VOCapacidadNormal cn = aforoAndes.adicionarCapacidadNormal(value, af);

			//Centro comercial 
			String identificacion = "22";
			String nombre = "Titan plaza";

			VOCentroComercial cc = aforoAndes.adicionarCentroComercial(identificacion, nombre);
			
			//TipoLector
			long idTipoLector = 1000;
			String tipoLector = "Visitante";;
			
			VOTipoLector tl = aforoAndes.adicionarTipoLector(tipoLector);
			
			//Lector
			
			long idLector = 1604;
			VOLector lector1= aforoAndes.adicionarLector(idLector, tl.getId(), cc.getIdentificador(), null, null,null,null);
			
			//TipoCarnet
			long idTipoCarnet = 1607;
			String tipoCarnet = "Fisico";
			VOTipoCarnet tipoCarnett = aforoAndes.adicionarTipoCarnet(tipoCarnet);
			
			//TipoVisitante 
			long idTipoVisitante = 1032;
			String tipoVisita ="Domiciliario";
			 
			VOTipoVisitante tv = aforoAndes.adicionarTipoVisitante(tipoVisita, 9, 10,17, 30);
			
			
			
			
			//Visitante 
			String cedula = "39716627";
			String nombreVisitante = "Juan Pérez";
			long tipoVisitante = tv.getId();
			String correo = "snwo@gmail.com";
			String telefonoPropio = "123456789";
			String nombrEmergencia = "Ana Sofia Pérez";
			String telefonoEmergencia = "987654321";
			
			VOVisitante visitante1 = aforoAndes.adicionarVisitante(cedula, nombreVisitante, tipoVisitante, correo, telefonoPropio, nombrEmergencia, telefonoEmergencia);
			
			//Horario entrada 
			int minuto1 = 30;
			int hora1 = 7;
			
			VOHorario horario1 = aforoAndes.adicionarHorario(hora1, minuto1);
			Long id1 = horario1.getId();
			
			//Horario salida
			int minuto2 = 30;
			int hora2 = 20;
			
			VOHorario horario2 = aforoAndes.adicionarHorario(hora2, minuto2);
			Long id2 = horario2.getId();
			

			String pattern = "MMM dd, yyyy";
			String timestampAsString = "Nov 12, 2019";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timestampAsString));

			Timestamp timestamp = Timestamp.valueOf(localDateTime);
			 
			 
			//VORegistranCarnet registro1 = aforoAndes.adicionarRegistranCarnet(lector1.getId(), tipoCarnett.getId(), visitante1.getIdentificacion(), timestamp, id1,id2);
			//lista = aforoAndes.darVORegistranCarnet();
			//assertEquals ("Debe haber un registro creado !!", 1, lista.size ());





		}
		catch (Exception e)
		{
			//   			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Centro Comercial.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas sobre la tabla Centro Comercial");
		}
		finally
		{
			aforoAndes.limpiarAforoAndes ();
			aforoAndes.cerrarUnidadPersistencia ();    		
		}
	}



	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
			//   			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "AreaTest", JOptionPane.ERROR_MESSAGE);
		}	
		return config;


	}	
}



