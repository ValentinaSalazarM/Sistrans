/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.testinterfaz;

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
import uniandes.isis2304.aforoandes.negocio.Bano;
import uniandes.isis2304.aforoandes.negocio.CentroComercial;
import uniandes.isis2304.aforoandes.negocio.LocalComercial;
import uniandes.isis2304.aforoandes.negocio.Parqueadero;
import uniandes.isis2304.aforoandes.negocio.VOAscensor;
import uniandes.isis2304.aforoandes.negocio.VOBano;
import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;
import uniandes.isis2304.aforoandes.negocio.VOLocalComercial;
import uniandes.isis2304.aforoandes.negocio.VOParqueadero;
import uniandes.isis2304.aforoandes.negocio.VOZonaCirculacion;
import uniandes.isis2304.aforoandes.negocio.ZonaCirculacion;

/**
 * @author Usuario
 *
 */
public class RegistranEspacios 
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
			 * 			ASCENSOR
			 *****************************************************************/

			interfaz.setAdministrador();
			interfaz.adicionarAscensor();
			
			/**
			 * Datos a ingresar en el diálogo de adicionar ascensor
			 * Identificador: ASCTest
			 * Área: 21
			 * Peso máximo: 1218.2
			 * IDCentroComercial: 3
			 */
			Thread.sleep(25000);
			VOAscensor ascensorEncontrado = aforoAndes.darAscensorPorId("ASCTest");

			assertNotNull ("Debería existir un objeto con el identificador dado", ascensorEncontrado);
			
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", "ASCTest", ascensorEncontrado.getIdentificador());
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", 1218.2, ascensorEncontrado.getPesoMaximo(), 0);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", 11, ascensorEncontrado.getArea(), 0);

			
			aforoAndes.eliminarAscensorPorId("ASCTest");
			ascensorEncontrado = aforoAndes.darAscensorPorId("ASCTest");
			assertNull ("No debería existir el ascensor con la identificación 'ASCTest'", ascensorEncontrado);

			/* ****************************************************************
			 * 			BAÑO
			 *****************************************************************/

			interfaz.adicionarBano();
			/**
			 * Datos a ingresar en el diálogo de adicionar baño
			 * Identificador: BATest
			 * Capacidad Normal: 14
			 * Área: 21
			 * Número de sanitarios: 14
			 * IDCentroComercial: 10
			 */
			Thread.sleep(25000);

			VOBano banoAñadido = (VOBano) new Bano ("BATest", new Long(11), new Long(11), 14, "10");
			VOBano banoEncontrado = aforoAndes.darBañoPorId("BATest");

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", banoAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", banoEncontrado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", banoAñadido.toString(), banoEncontrado.toString());

			aforoAndes.eliminarBañoPorId("BATest");
			banoEncontrado = aforoAndes.darBañoPorId("BATest");
			assertNull ("No debería existir el baño con la identificación 'BATest'", banoEncontrado);

			/* ****************************************************************
			 * 			CENTRO COMERCIAL
			 *****************************************************************/

			interfaz.adicionarCentroComercial();
			/**
			 * Datos a ingresar en el diálogo de adicionar centro comercial
			 * Identificador: 110
			 * Nombre: CCTest
			 */
			Thread.sleep(10000);
			VOCentroComercial ccAñadido = (VOCentroComercial) new CentroComercial ("110", "CCTest");
			VOCentroComercial ccEncontrado = aforoAndes.darCentroComercialPorId("110");
			assertNotNull ("Debería haberse creado un objeto con el identificador dado", ccAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", ccEncontrado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", ccAñadido.toString(), ccEncontrado.toString());

			aforoAndes.eliminarCentroComercialPorId("110");
			ccEncontrado = aforoAndes.darCentroComercialPorId("110");
			assertNull ("No debería existir el centro comercial con la identificación 'CCTest'", ccEncontrado);

			/* ****************************************************************
			 * 			LOCALCOMERCIAL
			 *****************************************************************/

			interfaz.adicionarLocalComercial();
			/**
			 * Datos a ingresar en el diálogo de adicionar baño
			 * Identificador: LCTest
			 * Capacidad Normal: 14
			 * Área: 21			 
			 * Tipo local: 1-Restaurante
			 * IDCentroComercial: 10
			 * Activo: Check
			 */
			Thread.sleep(25000);

			VOLocalComercial localAñadido = (VOLocalComercial) new LocalComercial ("LCTest", new Long(11), new Long(11), new Long (1), 1, "10");
			VOLocalComercial localEncontrado = aforoAndes.darLocalComercialPorId("LCTest");

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", localAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", localEncontrado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", localAñadido.toString(), localEncontrado.toString());

			/**
			 * Cambiar la actividad del local LCTest a 0
			 */
			interfaz.cambiarActividadLocalComercial();
			localEncontrado = aforoAndes.darLocalComercialPorId("LCTest");
			assertEquals ("La actividad del local comercial debió haber cambiado. ", localEncontrado.getActivo(), 0);
			
			
			aforoAndes.eliminarLocalComercialPorId("LCTest");
			localEncontrado = aforoAndes.darLocalComercialPorId("LCTest");
			assertNull ("No debería existir el local con la identificación 'LCTest'", localEncontrado);
			

			/* ****************************************************************
			 * 			PARQUEADERO
			 *****************************************************************/

			interfaz.adicionarParqueadero();
			/**
			 * Datos a ingresar en el diálogo de adicionar parqueadero
			 * Identificador: PARTest
			 * Capacidad Normal: 200
			 * Área: 200
			 * IDCentroComercial: 10
			 */
			Thread.sleep(25000);

			VOParqueadero parqueaderoAñadido = (VOParqueadero) new Parqueadero ("PARTest", new Long(22), new Long(5), "10");
			VOParqueadero parqueaderoEncontrado = aforoAndes.darParqueaderoPorId("PARTest");

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", parqueaderoAñadido);
			assertNotNull ("Debería existir un objeto con el identificador dado", parqueaderoEncontrado);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", parqueaderoAñadido.toString(), parqueaderoEncontrado.toString());

			aforoAndes.eliminarParqueaderoPorId("PARTest");
			parqueaderoEncontrado = aforoAndes.darParqueaderoPorId("PARTest");
			assertNull ("No debería existir el parqueadero con la identificación 'PARTest'", parqueaderoEncontrado);

			/* ****************************************************************
			 * 			ZONA CIRCULACIÓN
			 *****************************************************************/

			interfaz.adicionarZonaCirculacion();
			/**
			 * Datos a ingresar en el diálogo de adicionar parqueadero
			 * Identificador: ZCTest
			 * Capacidad Normal: 14
			 * IDCentroComercial: 10
			 */
			Thread.sleep(10000);
			VOZonaCirculacion zonaAñadida = (VOZonaCirculacion) new ZonaCirculacion ("ZCTest", 14, "10");
			VOZonaCirculacion zonaEncontrada = aforoAndes.darZonaCirculacionPorId("ZCTest");

			assertNotNull ("Debería haberse creado un objeto con el identificador dado", zonaAñadida);
			assertNotNull ("Debería existir un objeto con el identificador dado", zonaEncontrada);
			assertEquals ("El objeto creado y el recuperado de la base de datos deben ser iguales", zonaAñadida.toString(), zonaEncontrada.toString());

			aforoAndes.eliminarZonaCirculacionPorId("ZCTest");
			zonaEncontrada = aforoAndes.darZonaCirculacionPorId("ZCTest");
			assertNull ("No debería existir la zona de circulación con la identificación 'ZCTest'", zonaEncontrada);

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
