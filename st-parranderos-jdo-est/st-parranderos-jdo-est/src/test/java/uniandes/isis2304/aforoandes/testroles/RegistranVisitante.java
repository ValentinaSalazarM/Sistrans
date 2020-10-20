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
import uniandes.isis2304.aforoandes.negocio.Domiciliario;
import uniandes.isis2304.aforoandes.negocio.Empleado;
import uniandes.isis2304.aforoandes.negocio.VODomiciliario;
import uniandes.isis2304.aforoandes.negocio.VOEmpleado;
import uniandes.isis2304.aforoandes.negocio.VOTipoVisitante;
import uniandes.isis2304.aforoandes.negocio.VOVisitante;
import uniandes.isis2304.aforoandes.negocio.Visitante;

/**
 * @author Usuario
 *
 */
public class RegistranVisitante 
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
			 * 			TIPOVISITANTE
			 *****************************************************************/

			interfaz.adicionarTipoVisitante();
			/**
			 * Datos a ingresar en el diálogo de adicionar ascensor
			 * Tipo: TipoTest
			 * Horario Inicio: 11:11
			 * Horario Limite: 11:44
			 */
			Thread.sleep(25000);

			VOTipoVisitante tipoVisitanteRecuperado = aforoAndes.darTipoVisitantePorTipo("TipoTest");

			assertNotNull ("Debería existir un objeto con el identificador dado", tipoVisitanteRecuperado);
			assertEquals ("El tipo de visitante no coincide con el especificado.", tipoVisitanteRecuperado.getTipo(), "TipoTest");
			assertEquals ("La hora inicial de ciculación no coincide con la especificada.", tipoVisitanteRecuperado.getHoraInicio(), 13);
			assertEquals ("La hora límite de ciculación no coincide con la especificada.", tipoVisitanteRecuperado.getHoraLimite(), 16);

	
			aforoAndes.eliminarTipoVisitantePorTipo("TipoTest");
			tipoVisitanteRecuperado = aforoAndes.darTipoVisitantePorTipo("TipoTest");
			assertNull ("No debería existir el tipo de visitante con la identificación 'TipoTest'", tipoVisitanteRecuperado);

			/* ****************************************************************
			 * 			VISITANTE
			 *****************************************************************/

			interfaz.adicionarVisitante();
			/**
			 * Datos a ingresar en el diálogo de adicionar visitante
			 * Identificador: 3227
			 * Nombre: Sari Regis
			 * Tipo: 1 - Domiciliario
			 * Correo: sregis8@slashdot.org
			 * Telefono propio: 8002673
			 * NombreEmergencia: Edna Pettis
			 * TelefonoEmergencia: 7195118 
			 */
			Thread.sleep(35000);

			VOVisitante visitanteAñadido1 = ( VOVisitante ) new Visitante("3227", "Sari Regis", 1, "sregis8@slashdot.org", "8002673", "Edna Pettis", "7195118");
			VOVisitante visitanteEncontrado1 = aforoAndes.darVisitantePorId("3227");

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", visitanteAñadido1);
			assertNotNull ("Debería existir un objeto con el identificador dado", visitanteEncontrado1);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", visitanteAñadido1.toString(), visitanteEncontrado1.toString());

			interfaz.adicionarVisitante();
			/**
			 * Datos a ingresar en el diálogo de adicionar visitante
			 * Identificador: 2345
			 * Nombre: Morris Lowater
			 * Tipo: 6 - Empleado
			 * Correo: mlowater7@w3.org
			 * Telefono propio: 7197171
			 * NombreEmergencia: Sari Regis
			 * TelefonoEmergencia: 8198282 
			 */
			Thread.sleep(35000);

			VOVisitante visitanteAñadido2 = ( VOVisitante ) new Visitante("2345", "Morris Lowater", 6, "mlowater7@w3.org", "7197171", "Sari Regis", "8198282");
			VOVisitante visitanteEncontrado2 = aforoAndes.darVisitantePorId("2345");
			assertNotNull ("Debería haberse creado un objeto con el identificador dado", visitanteAñadido2);
			assertNotNull ("Debería existir un objeto con el identificador dado", visitanteEncontrado2);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", visitanteAñadido2.toString(), visitanteEncontrado2.toString());

			/* ****************************************************************
			 * 			DOMICILIARIO
			 *****************************************************************/

			interfaz.adicionarDomiciliario();
			/**
			 * Datos a ingresar en el diálogo de adicionar domiciliario
			 * Identificador: 110
			 * Empresa domicilios: Rappi
			 * HorarioInicioTurno: 11:11
			 * HorarioFinalTurno: 11:44
			 */
			Thread.sleep(20000);
			
			VODomiciliario domiciliarioAñadido = (VODomiciliario) new Domiciliario("110", "Rappi", 13, 16);
			VODomiciliario domiciliarioRecuperado = aforoAndes.darDomiciliarioPorId("110");
			assertNotNull ("Debería haberse creado un objeto con el identificador dado", domiciliarioAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", domiciliarioRecuperado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", domiciliarioAñadido.toString(), domiciliarioRecuperado.toString());

			aforoAndes.eliminarVisitantePorId("3227");
			visitanteEncontrado1 = aforoAndes.darVisitantePorId("3227");
			domiciliarioRecuperado = aforoAndes.darDomiciliarioPorId("3227");
			assertNull ("No debería existir el domiciliario con la identificación '3227'", domiciliarioRecuperado);
			assertNull ("No debería existir el visitante con la identificación '3227'", visitanteEncontrado1);
			
			/* ****************************************************************
			 * 			Empleado
			 *****************************************************************/

			interfaz.adicionarEmpleado();
			/**
			 * Datos a ingresar en el diálogo de adicionar empleado
			 * Identificador: 110
			 * Lugar de trabajo: LC101
			 * HorarioInicioTurno: 11:11
			 * HorarioFinalTurno: 11:44
			 */
			Thread.sleep(20000);
			
			VOEmpleado empleadoAñadido = (VOEmpleado) new Empleado("2345", "LC101", 13, 16);
			VOEmpleado empleadoRecuperado = aforoAndes.darEmpleadoPorId("2345");
			assertNotNull ("Debería haberse creado un objeto con el identificador dado", empleadoAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", empleadoRecuperado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", empleadoAñadido.toString(), empleadoRecuperado.toString());

			aforoAndes.eliminarVisitantePorId("2345");
			visitanteEncontrado1 = aforoAndes.darVisitantePorId("2345");
			empleadoRecuperado = aforoAndes.darEmpleadoPorId("2345");
			assertNull ("No debería existir el empleado con la identificación '3227'", domiciliarioRecuperado);
			assertNull ("No debería existir el visitante con la identificación '3227'", visitanteEncontrado1);
						
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
