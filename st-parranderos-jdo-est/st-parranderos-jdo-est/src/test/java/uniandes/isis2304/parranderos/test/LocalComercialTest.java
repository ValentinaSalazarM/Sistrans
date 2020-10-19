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
import uniandes.isis2304.aforoandes.negocio.VOAscensor;
import uniandes.isis2304.aforoandes.negocio.VOCapacidadNormal;
import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;
import uniandes.isis2304.aforoandes.negocio.VOLocalComercial;
import uniandes.isis2304.aforoandes.negocio.VOParqueadero;
import uniandes.isis2304.aforoandes.negocio.VOTipoLocal;



/**
 * @author 
 *
 */
public class LocalComercialTest 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(LocalComercialTest.class.getName());

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
	 * 			Métodos de prueba para la tabla Local Comercial - Creación 
	 *****************************************************************/

	@Test
	public void primeraVez() 
	{
		// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Local comercial");
			aforoAndes = new AforoAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
			//   			e.printStackTrace();
			log.info ("Prueba de CRD de LocalComercial incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Local Comercial incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}

		// Ahora si se pueden probar las operaciones
		try
		{

			List <VOLocalComercial> lista = aforoAndes.darVOLocalesComerciales();
			assertEquals ("No deben haber locales creados creados!!", 0, lista.size ());

			//Area
			double valor = 200;
			int aforo = 13;
			VOArea area1 = aforoAndes.adicionarArea(valor, aforo);

			//Capacidad normal 
			int value = 1800; 
			int af = 900;

			VOCapacidadNormal cn = aforoAndes.adicionarCapacidadNormal(value, af);

			//Centro comercial 
			String identificacion = "21";
			String nombre = "Parque la Colina";

			VOCentroComercial cc = aforoAndes.adicionarCentroComercial(identificacion, nombre);
			
			
			//Tipo Local
			
			int hora1 = 9;
			int minuto1 = 10;
			int hora2 = 23;
			int minuto2 = 59;
			String tipo = "Farmacia";
			VOTipoLocal tc = aforoAndes.adicionarTipoLocal(tipo, hora1, minuto1, hora2, minuto2);


			String identificador = "LC203";
			VOLocalComercial local1 = aforoAndes.adicionarLocalComercial(identificador, cn.getId(), area1.getId(), tc.getId(), 1, cc.getIdentificador());
			lista = aforoAndes.darVOLocalesComerciales();
			assertEquals ("Debe haber un local comercial creado !!", 1, lista.size ());

		}
		catch (Exception e)
		{
			//   			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Parqueadero.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas sobre la tabla Parqueadero");
		}
		finally
		{
			aforoAndes.limpiarAforoAndes ();
			aforoAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/**
	 * Método de prueba de la restricción de unicidad sobre el nombre de parqueadero
	 */
	@Test
	public void unicidadIDLocalTest() 
	{
		// Probar primero la conexión a la base de datos
		try
		{

			log.info ("Probando la restricción de UNICIDAD del id del parqueadero");
			aforoAndes = new AforoAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de parqueadero incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de parqueadero incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}

		// Ahora si se pueden probar las operaciones
		try
		{
			List <VOLocalComercial> lista = aforoAndes.darVOLocalesComerciales();
			assertEquals ("No deben haber locales creados creados!!", 0, lista.size ());

			//Area
			double valor = 200;
			int aforo = 13;
			VOArea area1 = aforoAndes.adicionarArea(valor, aforo);

			//Capacidad normal 
			int value = 1800; 
			int af = 900;

			VOCapacidadNormal cn = aforoAndes.adicionarCapacidadNormal(value, af);

			//Centro comercial 
			String identificacion = "21";
			String nombre = "Parque la Colina";

			VOCentroComercial cc = aforoAndes.adicionarCentroComercial(identificacion, nombre);
			
			
			//Tipo Local
			
			int hora1 = 9;
			int minuto1 = 10;
			int hora2 = 23;
			int minuto2 = 59;
			String tipo = "Farmacia";
			VOTipoLocal tc = aforoAndes.adicionarTipoLocal(tipo, hora1, minuto1, hora2, minuto2);


			String identificador = "LC203";
			VOLocalComercial local1 = aforoAndes.adicionarLocalComercial(identificador, cn.getId(), area1.getId(), tc.getId(), 1, cc.getIdentificador());
			lista = aforoAndes.darVOLocalesComerciales();
			assertEquals ("Debe haber un local comercial creado !!", 1, lista.size ());


			

			VOLocalComercial lc2 = aforoAndes.adicionarLocalComercial(identificador, cn.getId(),area1.getId(), tc.getId(), 1, cc.getIdentificador());
			assertNull ("No puede adicionar dos locales con el mismo id !!", lc2);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Parqueadero.\n";
			msg += "Revise el log de aforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD sobre la tabla Parqueadero");
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



