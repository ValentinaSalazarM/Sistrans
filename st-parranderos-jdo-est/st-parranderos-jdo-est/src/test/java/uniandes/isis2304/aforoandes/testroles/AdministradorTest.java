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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.aforoandes.interfazApp.InterfazAforoAndesApp;
import uniandes.isis2304.aforoandes.negocio.Administrador;
import uniandes.isis2304.aforoandes.negocio.AforoAndes;

/**
 * @author Usuario
 *
 */
public class AdministradorTest 
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

	/**
	 * Indica que el manejador indicó que el usuario está conectado
	 */
	private static boolean administradorConectado;	

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Indica si el administrador está conectado
	 * @return adminisradorConectado
	 */
	public static boolean usuarioConectado( )
	{
		return administradorConectado;
	}

	@Test
	public void ConexionBaseDatos()
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
			
			interfaz.mostrarDialogoRegistro();
			/**
			 * Datos a ingresar en el diálogo de registro
			 * Identificación: 34540
			 * Nombre: Otha Ebbers
			 * Contraseña: #e99
			 * Rol: Administrador
			 * Local administrado: ------
			 */
			Thread.sleep(15000);
			
			Administrador añadido = new Administrador("34540", "Otha Ebbers", "#e99");
			Administrador encontrado = aforoAndes.darAdministradorPorId("34540");
			
			assertNotNull ("Debería haberse creado un objeto con el identificador dado", añadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", encontrado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", añadido.toString(), encontrado.toString());
			
			aforoAndes.eliminarAdministradorPorId("34540");
			encontrado = aforoAndes.darAdministradorPorId("34540");
			assertNull ("No debería existir el administrador con la identificación '34540'", encontrado);
			
			Thread.sleep(100);
						
			interfaz.mostrarDialogoInicioSesion();
			/**
			 * Datos a ingresar en el diálogo inicio de sesión
			 * Identificación: 3543016364021851
			 * Contraseña: #618
			 * Rol: Administrador
			 * Local administrado: ------
			 */
			Thread.sleep(15000);
			assertTrue("Tiempo excedido o el administrador no se inició sesión exitosamente", interfaz.getAdministrador());  


			interfaz.mostrarDialogoInicioSesion();
			/**
			 * Datos a ingresar en el diálogo inicio de sesión
			 * Identificación: 3550856365084741
			 * Contraseña: #b8c
			 * Rol: Administrador
			 * Local administrado: ------
			 */
			Thread.sleep(15000);
			assertTrue("Tiempo excedido o el administrador no se inició sesión exitosamente", interfaz.getAdministrador());  

			interfaz.mostrarDialogoInicioSesion();
			/**
			 * Datos a ingresar en el diálogo inicio de sesión
			 * Identificación: 4913646598441655
			 * Contraseña: #085
			 * Rol: Administrador
			 * Local administrado: ------
			 */
			Thread.sleep(15000);
			assertTrue("Tiempo excedido o el administrador no se inició sesión exitosamente", interfaz.getAdministrador());  
			administradorConectado = true;
			
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
