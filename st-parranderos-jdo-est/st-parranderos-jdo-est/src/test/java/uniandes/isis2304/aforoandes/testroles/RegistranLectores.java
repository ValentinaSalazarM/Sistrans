/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.testroles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.FileReader;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.aforoandes.interfazApp.InterfazAforoAndesApp;
import uniandes.isis2304.aforoandes.negocio.AforoAndes;
import uniandes.isis2304.aforoandes.negocio.Lector;
import uniandes.isis2304.aforoandes.negocio.VOLector;
/**
 * @author Usuario
 *
 */
public class RegistranLectores 
{

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/

	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazAforoAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 


	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private AforoAndes aforoAndes;


	InterfazAforoAndesApp interfaz = new InterfazAforoAndesApp( );


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------


	@Test
	public void RegistroEspacios()
	{
		try
		{
			log.info ("Probando las operaciones sobre Administrador");
			aforoAndes = new AforoAndes (openConfig (CONFIG_TABLAS));
		}
		catch ( Exception e )
		{
			log.info ("Prueba de conexión de Administrador incompleta. No se pudo conectar a la base de datos. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de conexión de Administrador incompleta. No se pudo conectar a la base de datos.\n";
			msg += "Revise el log de AforoAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		try
		{
			/* ****************************************************************
			 * 			Lectores
			 *****************************************************************/

			interfaz.setAdministrador();
			interfaz.adicionarLector();
			/**
			 * Datos a ingresar en el diálogo de adicionar un lector de centro comercial
			 * Identificador: 500
			 * TipoLector: 3 - Visitante 
			 * IdCentroComercial = 6
			 */
			Thread.sleep(15000);

			VOLector lectorAñadido = (VOLector) new Lector (500, 3, "6", null, null, null, null);
			VOLector lectorRecuperado = aforoAndes.darLectorPorId(500);

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", lectorAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", lectorRecuperado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", lectorAñadido.toString(), lectorRecuperado.toString());
			
			aforoAndes.eliminarLectorPorId(500);
			lectorRecuperado = aforoAndes.darLectorPorId(500);
			assertNull ("No debería existir el lector con la identificación '500'", lectorRecuperado);

			lectorAñadido = null;
			lectorRecuperado = null;
			
			interfaz.adicionarLector();
			/**
			 * Datos a ingresar en el diálogo de adicionar un lector de local comercial
			 * Identificador: 500
			 * TipoLector: 3 - Visitante 
			 * IdLocalComercial = LC101
			 */
			Thread.sleep(15000);

			lectorAñadido = (VOLector) new Lector (500, 3, null, "LC101", null, null, null);
			lectorRecuperado = aforoAndes.darLectorPorId(500);

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", lectorAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", lectorRecuperado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", lectorAñadido.toString(), lectorRecuperado.toString());
			
			aforoAndes.eliminarLectorPorId(500);
			lectorRecuperado = aforoAndes.darLectorPorId(500);
			assertNull ("No debería existir el lector con la identificación '500'", lectorRecuperado);

			
			interfaz.adicionarLector();
			/**
			 * Datos a ingresar en el diálogo de adicionar un lector de ascensor
			 * Identificador: 500
			 * TipoLector: 3 - Visitante 
			 * IdAscensor = ASC123
			 */
			Thread.sleep(15000);

			lectorAñadido = (VOLector) new Lector (500, 3, null, null, null, "ASC123", null);
			lectorRecuperado = aforoAndes.darLectorPorId(500);

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", lectorAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", lectorRecuperado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", lectorAñadido.toString(), lectorRecuperado.toString());
			
			aforoAndes.eliminarLectorPorId(500);
			lectorRecuperado = aforoAndes.darLectorPorId(500);
			assertNull ("No debería existir el lector con la identificación '500'", lectorRecuperado);
			

			interfaz.adicionarLector();
			/**
			 * Datos a ingresar en el diálogo de adicionar un lector de parqueadero
			 * Identificador: 500
			 * TipoLector: 2 - Vehículo 
			 * Id parqueadero = PAR15
			 */
			Thread.sleep(15000);

			lectorAñadido = (VOLector) new Lector (500, 2, null, null, null, null, "PAR15" );
			lectorRecuperado = aforoAndes.darLectorPorId(500);

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", lectorAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", lectorRecuperado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", lectorAñadido.toString(), lectorRecuperado.toString());
			
			aforoAndes.eliminarLectorPorId(500);
			lectorRecuperado = aforoAndes.darLectorPorId(500);
			assertNull ("No debería existir el lector con la identificación '500'", lectorRecuperado);
			

			interfaz.adicionarLector();
			/**
			 * Datos a ingresar en el diálogo de adicionar un lector de baño
			 * Identificador: 500
			 * TipoLector: 3 - Visitante 
			 * Id espacio = BA121
			 */
			Thread.sleep(15000);

			lectorAñadido = (VOLector) new Lector (500, 3, null, null, "BA121", null, null);
			lectorRecuperado = aforoAndes.darLectorPorId(500);

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", lectorAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", lectorRecuperado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", lectorAñadido.toString(), lectorRecuperado.toString());
			
			aforoAndes.eliminarLectorPorId(500);
			lectorRecuperado = aforoAndes.darLectorPorId(500);
			assertNull ("No debería existir el lector con la identificación '500'", lectorRecuperado);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Administrador.\n";
			msg += "Revise el log de administrador y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas sobre la tabla Administrador");
		}
		finally
		{
			aforoAndes.limpiarAforoAndes();
			aforoAndes.cerrarUnidadPersistencia();
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
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "AdministradorTest", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}
}
