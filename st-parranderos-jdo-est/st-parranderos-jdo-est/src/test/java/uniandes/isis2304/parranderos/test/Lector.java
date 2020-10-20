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
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.aforoandes.negocio.AforoAndes;
import uniandes.isis2304.aforoandes.negocio.VOArea;

import uniandes.isis2304.aforoandes.negocio.VOCapacidadNormal;
import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;

import uniandes.isis2304.aforoandes.negocio.VOLector;
import uniandes.isis2304.aforoandes.negocio.VOTipoLector;




/**
 * @author 
 *
 */
public class Lector 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(Lector.class.getName());

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
	 * 			Métodos de prueba para la tabla Lector - Creación 
	 *****************************************************************/

	@Test
	public void primeraVez() 
	{
		// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Lector");
			aforoAndes = new AforoAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
			//   			e.printStackTrace();
			log.info ("Prueba de CRD de Lector incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Lector incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}

		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOLector> lista = aforoAndes.darVOLectores();
			assertEquals("No deben haber ascensores creados!!", 0, lista.size ());

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



			//TipoLocal 
			String tipo = "Fisico";
			VOTipoLector tipoLector = aforoAndes.adicionarTipoLector(tipo);


			Long identificador = (long) 123;
			VOLector lector1 = aforoAndes.adicionarLector(identificador, tipoLector.getId(), cc.getIdentificador(), null, null, null, null);
			lista = aforoAndes.darVOLectores();
			assertEquals ("Debe haber un lector creado !!", 1, lista.size ());





		}
		catch (Exception e)
		{
			//   			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Lector.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas sobre la tabla Lector");
		}
		finally
		{
			aforoAndes.limpiarAforoAndes ();
			aforoAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/**
	 * Método de prueba de la restricción de unicidad sobre el nombre del Lector
	 */
	@Test
	public void unicidadIDLectorTest() 
	{

		// Probar primero la conexión a la base de datos
		try
		{

			log.info ("Probando la restricción de UNICIDAD del id del lector");
			aforoAndes = new AforoAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de  lector incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de lector incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}

		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOLector> lista = aforoAndes.darVOLectores();
			assertEquals("No deben haber ascensores creados!!", 0, lista.size ());

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



			//TipoLocal 
			String tipo = "Fisico";
			VOTipoLector tipoLector = aforoAndes.adicionarTipoLector(tipo);


			Long identificador = (long) 123;
			VOLector lector1 = aforoAndes.adicionarLector(identificador, tipoLector.getId(), cc.getIdentificador(), null, null, null, null);
			lista = aforoAndes.darVOLectores();
			assertEquals ("Debe haber un lector creado !!", 1, lista.size ());



			VOLector lector2 = aforoAndes.adicionarLector(identificador, tipoLector.getId(), cc.getIdentificador(),null, null, null, null);
			assertNull ("No puede adicionar dos lectores con el mismo id !!", lector2);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Lector.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD sobre la tabla Lector");
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



