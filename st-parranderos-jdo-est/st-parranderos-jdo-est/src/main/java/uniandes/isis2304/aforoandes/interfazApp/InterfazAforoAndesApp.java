/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.aforoandes.negocio.Administrador;
import uniandes.isis2304.aforoandes.negocio.AdministradorLocal;
import uniandes.isis2304.aforoandes.negocio.AforoAndes;
import uniandes.isis2304.aforoandes.negocio.Bano;
import uniandes.isis2304.aforoandes.negocio.Carnet;
import uniandes.isis2304.aforoandes.negocio.CentroComercial;
import uniandes.isis2304.aforoandes.negocio.Lector;
import uniandes.isis2304.aforoandes.negocio.LocalComercial;
import uniandes.isis2304.aforoandes.negocio.RFC3;
import uniandes.isis2304.aforoandes.negocio.RFC4;
import uniandes.isis2304.aforoandes.negocio.RegistranCarnet;
import uniandes.isis2304.aforoandes.negocio.VOArea;
import uniandes.isis2304.aforoandes.negocio.VOAscensor;
import uniandes.isis2304.aforoandes.negocio.VOBano;
import uniandes.isis2304.aforoandes.negocio.VOCapacidadNormal;
import uniandes.isis2304.aforoandes.negocio.VOCarnet;
import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;
import uniandes.isis2304.aforoandes.negocio.VODomiciliario;
import uniandes.isis2304.aforoandes.negocio.VOEmpleado;
import uniandes.isis2304.aforoandes.negocio.VOLector;
import uniandes.isis2304.aforoandes.negocio.VOLocalComercial;
import uniandes.isis2304.aforoandes.negocio.VOParqueadero;
import uniandes.isis2304.aforoandes.negocio.VORegistranCarnet;
import uniandes.isis2304.aforoandes.negocio.VOTipoCarnet;
import uniandes.isis2304.aforoandes.negocio.VOTipoLector;
import uniandes.isis2304.aforoandes.negocio.VOTipoLocal;
import uniandes.isis2304.aforoandes.negocio.VOTipoVisitante;
import uniandes.isis2304.aforoandes.negocio.VOVisitante;
import uniandes.isis2304.aforoandes.negocio.VOZonaCirculacion;
import uniandes.isis2304.aforoandes.negocio.Visitante;


/**
 * Clase principal de la interfaz
 */
@SuppressWarnings("serial")

public class InterfazAforoAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazAforoAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

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

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/**
	 * Indica si el usuario es un administrador 
	 */
	private boolean administrador;

	/**
	 * Identificador del local administrado 
	 */
	private String idLocalAdministrado;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazAforoAndesApp( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		aforoAndes = new AforoAndes (tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );      
		administrador = false;
		idLocalAdministrado = null;
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "AforoAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "AforoAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús

		menuBar = new JMenuBar();       
		menuBar.setLayout(new GridLayout(3,6));
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			CRUD de Area
	 *****************************************************************/

	/**
	 * Adiciona un área con la información dada por el usuario
	 * Se crea una nueva tupla de Area en la base de datos, si un área con ese valor no existía
	 */
	public void adicionarArea()
	{
		try 
		{
			String valorStr = JOptionPane.showInputDialog (this, "Valor del área: ", "Adicionar área", JOptionPane.QUESTION_MESSAGE);
			String aforoStr = JOptionPane.showInputDialog (this, "Aforo del área: ", "Adicionar área", JOptionPane.QUESTION_MESSAGE);

			double valor = Double.parseDouble(valorStr);
			int aforo = Integer.parseInt(aforoStr);

			if (valorStr != null && aforoStr != null)
			{
				VOArea area = aforoAndes.adicionarArea(valor, aforo);
				if (area == null)
				{
					throw new Exception ("No se pudo crear un área con valor: " + valor);
				}
				String resultado = "En adicionar Área\n\n";
				resultado += "Área adicionado exitosamente: " + area;
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El valor (double) y el aforo (int) deben ser números.", "Agregar Área", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las áreas existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarAreas( )
	{
		try 
		{
			List <VOArea> lista = aforoAndes.darVOAreas();

			String resultado = "En listar Area";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el área con el valor indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarAreaPorValor( )
	{
		try 
		{
			String valorStr = JOptionPane.showInputDialog (this, "Valor del área: ", "Buscar área por valor", JOptionPane.QUESTION_MESSAGE);
			if (valorStr != null)
			{
				double valor = Double.parseDouble(valorStr);
				VOArea area = aforoAndes.darAreaPorValor(valor);
				String resultado = "En buscar Área por valor\n\n";
				if (area != null)
				{
					resultado += "El area es: " + area;
				}
				else
				{
					resultado += "Un area con valor: " + valorStr + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	} 

	/**
	 * Cambiar el valor de un área
	 */
	public void cambiarValorArea( )
	{
		try 
		{
			String idStr = JOptionPane.showInputDialog (this, "Identificador del área: ", "Actualizar valor del área", JOptionPane.QUESTION_MESSAGE);
			String valor = JOptionPane.showInputDialog (this, "Nuevo valor: ", "Actualizar valor del área", JOptionPane.QUESTION_MESSAGE);
			if (idStr != null && valor != null)
			{	
				long id = Long.valueOf (idStr);
				double valorS = Double.valueOf (valor);

				long modificados = aforoAndes.cambiarValorArea(id, valorS);
				String resultado = "En actualizar Área: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e2 )
		{
			JOptionPane.showMessageDialog( this, "El identificador y el valor del área deben ser números.", "Actualizar valor del área", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el aforo de un área
	 */
	public void cambiarAforoArea( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificador del área: ", "Actualizar aforo del área", JOptionPane.QUESTION_MESSAGE);
			String aforo = JOptionPane.showInputDialog (this, "Nuevo aforo: ", "Actualizar aforo del área", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null && aforo != null)
			{	
				long id = Long.valueOf (identificacion);
				int aforoS = Integer.valueOf (aforo);

				long modificados = aforoAndes.cambiarAforoArea(id, aforoS);
				String resultado = "En actualizar Área: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}

			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e2 )
		{
			JOptionPane.showMessageDialog( this, "El identificador y el aforo del área deben ser números.", "Actualizar valor del área", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos el área con el identificador dado por el usuario
	 * Cuando dicho área no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarAreaPorId( )
	{
		try 
		{
			String id = JOptionPane.showInputDialog (this, "Identificador del área: ", "Borrar área por identificador", JOptionPane.QUESTION_MESSAGE);
			if (id != null)
			{
				long idArea = Long.valueOf (id);
				long areasEliminadas = aforoAndes.eliminarAreaPorId (idArea);

				String resultado = "En eliminar Área\n\n";
				resultado += areasEliminadas + " Áreas eliminadas\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		}
		catch( NumberFormatException e2 )
		{
			JOptionPane.showMessageDialog( this, "El identificador del área debe ser un número.", "Actualizar valor del área", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Ascensor
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un ascensor
	 */
	public void adicionarAscensor( )
	{
		if ( administrador )
		{
			DialogoAdicionarAscensor dialogo = new DialogoAdicionarAscensor( this );
			dialogo.setVisible( true );
			panelDatos.actualizarInterfaz("En proceso de adición");
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Adiciona un ascensor con la información dada por el usuario
	 * Se crea una nueva tupla de Ascensor en la base de datos, si un ascensor con ese identificador no existía
	 */
	public void adicionarAscensor( String idAscensor, double area, double pesoMaximo, String idCentroComercial, DialogoAdicionarAscensor pDialogo)
	{
		try 
		{
			Long idArea = null;
			if ( area != -1)
			{
				idArea = buscarAreaPorValor(area).getId();
			}
			VOAscensor ascensor = aforoAndes.adicionarAscensor(idAscensor, idArea, pesoMaximo, idCentroComercial);

			if (ascensor == null)
			{
				throw new Exception ("No se pudo crear un ascensor con nombre: " + idAscensor);
			}
			String resultado = "En adicionar Ascensor\n\n";
			resultado += "Ascensor adicionado exitosamente: " + ascensor;
			resultado += "\n\n Operación terminada";
			pDialogo.dispose();
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los ascensores existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarAscensores( )
	{
		try 
		{
			List <VOAscensor> lista = aforoAndes.darVOAscensores();

			String resultado = "En listar Ascensor";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el ascensor con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarAscensorPorIdentificador( )
	{
		try 
		{
			String idAscensor = JOptionPane.showInputDialog (this, "Identificador del ascensor: ", "Buscar ascensor por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idAscensor != null)
			{
				VOAscensor Ascensor = aforoAndes.darAscensorPorId(idAscensor);
				String resultado = "En buscar Ascensor por identificador\n\n";
				if (Ascensor != null)
				{
					resultado += "El ascensor es: " + Ascensor;
				}
				else
				{
					resultado += "Un ascensor con identificador: " + idAscensor + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el peso máximo de un ascensor
	 */
	public void cambiarPesoMaximoAscensor( )
	{
		try 
		{
			String idAscensor = JOptionPane.showInputDialog (this, "Identificador del ascensor: ", "Actualizar peso máximo del ascensor", JOptionPane.QUESTION_MESSAGE);
			String pesoMaximoStr = JOptionPane.showInputDialog (this, "Nuevo peso máximo: ", "Actualizar peso máximo del ascensor", JOptionPane.QUESTION_MESSAGE);
			if (idAscensor != null && pesoMaximoStr != null)
			{	
				double pesoMaximo = Double.valueOf (pesoMaximoStr);
				long modificados = aforoAndes.cambiarPesoMaximoAscensor(idAscensor, pesoMaximo);
				String resultado = "En actualizar Ascensor: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "El peso máximo se un número.", "Actualizar peso máximo del ascensor", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el ascensor con el identificador dado por el usuario
	 * Cuando dicho ascensor no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarAscensorPorIdentificador( )
	{
		try 
		{
			String idAscensor = JOptionPane.showInputDialog (this, "Identificador del ascensor:", "Borrar ascensor por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idAscensor != null)
			{
				String idTipo = idAscensor;
				long ascensoresEliminados = aforoAndes.eliminarAscensorPorId (idTipo);

				String resultado = "En eliminar Ascensor\n\n";
				resultado += ascensoresEliminados + " Ascensores eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			CRUD de BANO
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un baño
	 */
	public void adicionarBano( )
	{
		if ( administrador )
		{
			DialogoAdicionarBano dialogo = new DialogoAdicionarBano( this );
			dialogo.setVisible( true );

		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Adiciona un baño con la información dada por el usuario
	 * Se crea una nueva tupla de Bano en la base de datos, si un baño con ese identificador no existía
	 */
	public void adicionarBano( String idBano, int capacidadNormal, double area, int numSanitarios, String idCentroComercial, DialogoAdicionarBano pDialogo)
	{
		try 
		{

			Long idArea = null;
			if ( area != -1)
			{
				idArea = buscarAreaPorValor(area).getId();
			}
			long idCapacidadNormal = buscarCapacidadNormalPorValor(capacidadNormal).getId();
			VOBano baño = aforoAndes.adicionarBaño(idBano, idCapacidadNormal, idArea, numSanitarios, idCentroComercial);

			if (baño == null)
			{
				throw new Exception ("No se pudo crear un baño con identificador: " + idBano);
			}
			String resultado = "En adicionar Baño\n\n";
			resultado += "Baño adicionado exitosamente: " + baño;
			resultado += "\n\n Operación terminada";
			pDialogo.dispose();
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los baños existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarBanos( )
	{
		try 
		{
			List <VOBano> lista = aforoAndes.darVOBaños();

			String resultado = "En listar Baños";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca  el baño por identificador
	 */
	public void buscarBanoPorIdentificador( )
	{
		try 
		{
			String identificador = JOptionPane.showInputDialog (this, "Identificador del baño: ", "Buscar baño por identificador", JOptionPane.QUESTION_MESSAGE);
			if (identificador != null)
			{
				VOBano baño= aforoAndes.darBañoPorId(identificador);
				String resultado = "En buscar Baño por id\n\n";
				if (baño != null)
				{
					resultado += "El baño es: " + baño;
				}
				else
				{
					resultado += "El baño con identificación: " + identificador + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca los baños por sanitarios.
	 */
	public void buscarBanoPorSanitarios( )
	{
		try 
		{
			String sanitarios = JOptionPane.showInputDialog (this, "Número de sanitarios: ", "Buscar por número de sanitarios", JOptionPane.QUESTION_MESSAGE);
			if (sanitarios != null)
			{
				int value = Integer.valueOf (sanitarios);
				List <Bano> lista = aforoAndes.darBañosPorSanitarios(value);
				String resultado = "En listar Baños";
				resultado +=  "\n" + listarObjetos (lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n\n Operación terminada";
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "La cantidad de sanitarios debe ser un número.", "Buscar número de sanitarios", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el número de sanitarios de un baño
	 */
	public void cambiarNumeroSanitariosBano( )
	{
		try 
		{
			String idBano = JOptionPane.showInputDialog (this, "Identificador del baño: ", "Actualizar número de sanitarios del baño", JOptionPane.QUESTION_MESSAGE);
			String numeroSanitariosStr = JOptionPane.showInputDialog (this, "Nuevo número de sanitarios: ", "Actualizar número de sanitarios del baño", JOptionPane.QUESTION_MESSAGE);
			if (idBano != null && numeroSanitariosStr != null)
			{	
				int numeroSanitarios = Integer.parseInt(numeroSanitariosStr);
				long modificados = aforoAndes.cambiarNumeroSanitariosBaño(idBano, numeroSanitarios);
				String resultado = "En actualizar Baño: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		}
		catch( NumberFormatException e2 )
		{
			JOptionPane.showMessageDialog( this, "La cantidad de sanitarios debe ser un número.", "Actualizar número de sanitarios del baño", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos el baño con el identificador dado por el usuario
	 * Cuando dicho baño no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarBanoPorIdentificador( )
	{
		try 
		{
			String idBano = JOptionPane.showInputDialog (this, "Identificador del baño: ", "Borrar baño por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idBano != null)
			{
				String bano = idBano;
				long banosEliminados = aforoAndes.eliminarBañoPorId(bano);
				String resultado = "En eliminar Baño\n\n";
				resultado += banosEliminados + " Baños eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el baño con los sanitarios dados
	 * Cuando dicho baño no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarBanoPorSanitarios( )
	{
		try 
		{
			String sanitariosStr = JOptionPane.showInputDialog (this, "Número de sanitarios del baño a eliminar: ", "Borrar baño por número de sanitarios", JOptionPane.QUESTION_MESSAGE);
			if (sanitariosStr != null)
			{
				int sanitarios = Integer.valueOf (sanitariosStr);
				long banosEliminados = aforoAndes.eliminarBañoPorSanitarios(sanitarios);
				String resultado = "En eliminar Baño\n\n";
				resultado += banosEliminados + " Baños eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		}
		catch( NumberFormatException e2 )
		{
			JOptionPane.showMessageDialog( this, "La cantidad de sanitarios debe ser un número.", "Borrar baño por número de sanitarios", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			CRUD de CAPACIDAD NORMAL
	 *****************************************************************/

	/**
	 * Adiciona una capacidad normal con la información dada por el usuario
	 * Se crea una nueva tupla de Capacidad Normal en la base de datos, si una capacidad normal con ese valor no existía
	 */
	public void adicionarCapacidadNormal()
	{
		try 
		{
			String valorStr = JOptionPane.showInputDialog (this, "Valor: ", "Adicionar capacidad normal", JOptionPane.QUESTION_MESSAGE);
			String aforoStr = JOptionPane.showInputDialog (this, "Aforo: ", "Adicionar capacidad normal", JOptionPane.QUESTION_MESSAGE);

			Integer valor = Integer.parseInt(valorStr);
			int aforo = Integer.parseInt(aforoStr);

			if (valorStr != null && aforoStr != null)
			{
				VOCapacidadNormal capacidad = aforoAndes.adicionarCapacidadNormal(valor, aforo);
				if (capacidad == null)
				{
					throw new Exception ("No se pudo crear una capacidad con el valor: " + valor);
				}
				String resultado = "En adicionar Capacidad normal\n\n";
				resultado += "Capacidad adicionada exitosamente: " + capacidad;
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El valor (double) y el aforo (int) deben ser números enteros.", "Adicionar capacidad normal", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las capacidades normales existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarCapacidades( )
	{
		try 
		{
			List <VOCapacidadNormal> lista = aforoAndes.darVOCapacidadesNormales();

			String resultado = "En listar Capacidad Normal";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca la capacidad normal por el ID.
	 */
	public void buscarCapacidadPorId( )
	{
		try 
		{
			String identificador = JOptionPane.showInputDialog (this, "Identificador de la capacidad: ", "Buscar capacidad normal por identificador", JOptionPane.QUESTION_MESSAGE);
			if (identificador != null)
			{
				long id = Long.valueOf (identificador);
				VOCapacidadNormal capacidad = aforoAndes.darCapacidadNormalPorId(id);
				String resultado = "En buscar Capacidad normal por id\n\n";
				if (capacidad != null)
				{
					resultado += "La capacidad es: " + capacidad;
				}
				else
				{
					resultado += "La capacidad con identificación: " + identificador + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El identificador debe ser un número", "Buscar capacidad normal por identificador", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca la capacidad normal por el valor.
	 */
	public void buscarCapacidadNormalPorValor( )
	{
		try 
		{
			String valorStr = JOptionPane.showInputDialog (this, "Valor de la capacidad: ", "Buscar capacidades normales por valor", JOptionPane.QUESTION_MESSAGE);
			if (valorStr != null)
			{
				Integer valor = Integer.parseInt(valorStr);
				VOCapacidadNormal capacidadNormal = aforoAndes.darCapacidadNormalPorValor(valor);
				String resultado = "En buscar Capacidad normal por valor\n\n";
				if (capacidadNormal != null)
				{
					resultado += "La capacidad es: " + capacidadNormal;
				}
				else
				{
					resultado += "La capacidad con valor: " + valorStr + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El valor debe ser un número entero", "Buscar capacidades normales por valor", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el valor de una capacidad normal.
	 */
	public void cambiarValorCapacidad( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificador de la capacidad: ", "Actualizar valor de capacidad normal", JOptionPane.QUESTION_MESSAGE);
			String valorStr = JOptionPane.showInputDialog (this, "Nuevo valor: ", "Actualizar valor de capacidad normal", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null && valorStr != null)
			{	
				long id = Long.valueOf (identificacion);
				int valor = Integer.parseInt(valorStr);
				long modificados = aforoAndes.cambiarValorCapacidad(id, valor);
				String resultado = "En actualizar Capacidad Normal: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El identificador y el valor deben ser números", "Actualizar valor de capacidad normal", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el aforo de una capacidad normal.
	 */
	public void cambiarAforoCapacidad( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificador de la capacidad: ", "Actualizar aforo de capacidad normal", JOptionPane.QUESTION_MESSAGE);
			String aforoStr = JOptionPane.showInputDialog (this, "Nuevo aforo: ", "Actualizar aforo de capacidad normal", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null && aforoStr != null)
			{	
				long id = Long.valueOf (identificacion);
				int aforo = Integer.valueOf (aforoStr);

				long modificados = aforoAndes.cambiarAforoCapacidad(id, aforo);
				String resultado = "En actualizar Capacidad Normal: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El identificador y el valor deben ser números", "Actualizar aforo de capacidad normal", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos la capacidad  con el identificador dado por el usuario
	 * Cuando dicha capacidad no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarCapacidadPorId( )
	{
		try 
		{
			String id = JOptionPane.showInputDialog (this, "Identificador de la capacidad: ", "Borrar capacidad normal por identificador", JOptionPane.QUESTION_MESSAGE);
			if (id != null)
			{
				long idCapacidad = Long.valueOf (id);
				long capacidadesEliminadas = aforoAndes.eliminarCapacidadNormalPorId(idCapacidad);

				String resultado = "En eliminar Capacidad\n\n";
				resultado += capacidadesEliminadas  + "Capacidades eliminadas\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El identificador debe ser un número", "Borrar capacidad normal por identificador", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos la capacidad  con el identificador dado por el usuario
	 * Cuando dicha capacidad no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarCapacidadPorValor( )
	{
		try 
		{
			String valorStr = JOptionPane.showInputDialog (this, "Valor de la capacidad: ", "Borrar capacidad normal por valor", JOptionPane.QUESTION_MESSAGE);
			if (valorStr != null)
			{
				int valor = Integer.parseInt(valorStr);
				long eliminadas = aforoAndes.eliminarCapacidadNormalPorValor(valor);

				String resultado = "En eliminar Capacidad\n\n";
				resultado += eliminadas  + "Capacidades eliminadas\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El valor debe ser un número entero.", "Borrar capacidad normal por valor", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			CRUD de CentroComercial
	 *****************************************************************/

	/**
	 * Adiciona un centro comercial con la información dada por el usuario
	 * Se crea una nueva tupla de CentroComercial en la base de datos, si un centro comercial con ese identificador no existía
	 */
	public void adicionarCentroComercial()
	{
		try
		{
			if( administrador )
			{
				String identificador = JOptionPane.showInputDialog (this, "Identificador del centro comercial: ", "Adicionar centro comercial", JOptionPane.QUESTION_MESSAGE);
				String nombre = JOptionPane.showInputDialog (this, "Nombre del centro comercial: ", "Adicionar centro comercial", JOptionPane.QUESTION_MESSAGE);
				if (identificador != null)
				{

					VOCentroComercial centroComercial = aforoAndes.adicionarCentroComercial(identificador, nombre);
					if (centroComercial == null)
					{
						throw new Exception ("No se pudo crear un centro comercial con identificador: " + identificador);
					}
					String resultado = "En adicionar CentroComercial\n\n";
					resultado += "CentroComercial adicionado exitosamente: " + centroComercial;
					resultado += "\n\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else
			{
				throw new Exception ("\n No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores");
			}
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los centros comerciales existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarCentrosComerciales( )
	{
		try 
		{
			List <VOCentroComercial> lista = aforoAndes.darVOCentrosComerciales();

			String resultado = "En listar CentroComercial";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Busca el centro comercial con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarCentroComercialPorIdentificador( )
	{
		try 
		{
			String idCentroComercial = JOptionPane.showInputDialog (this, "Identificacion del centro comercial: ", "Buscar centro comercial por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idCentroComercial != null)
			{
				VOCentroComercial centroComercial = aforoAndes.darCentroComercialPorId(idCentroComercial);
				String resultado = "En buscar CentroComercial por identificacion\n\n";
				if (centroComercial != null)
				{
					resultado += "El centro comercial es: " + centroComercial;
				}
				else
				{
					resultado += "Un centro comercial con identificacion: " + idCentroComercial + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el centro comercial con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarCentroComercialPorNombre( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del centro comercial: ", "Buscar centro comercial por nombre", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				List<CentroComercial> lista = aforoAndes.darCentroComercialPorNombre(nombre);
				String resultado = "En buscar CentroComercial por nombre: \n";
				resultado +=  "\n" + listarObjetos (lista);
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el nombre al centro comercial con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarNombreCentroComercial( )
	{
		try 
		{
			String idCentroComercial = JOptionPane.showInputDialog (this, "Identificador del centro comercial: ", "Actualizar nombre del centro comercial", JOptionPane.QUESTION_MESSAGE);
			String nombre = JOptionPane.showInputDialog (this, "Nuevo nombre del centro comercial: ", "Actualizar nombre del centro comercial", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				long modificados = aforoAndes.cambiarNombreCentroComercial(idCentroComercial, nombre);
				String resultado = "En actualizar CentroComercial por nombre: \n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el centro comercial con la identificación dada por el usuario
	 * Cuando dicho centro comercial no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarCentroComercialPorIdentificador( )
	{
		try 
		{
			String idCentroComercial = JOptionPane.showInputDialog (this, "Identificación del centro comercial: ", "Borrar centro comercial por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idCentroComercial != null)
			{
				long ccEliminados = aforoAndes.eliminarCentroComercialPorId(idCentroComercial);		
				String resultado = "En eliminar CentroComercial\n\n";
				resultado += ccEliminados + " Centros comerciales eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Carnet
	 *****************************************************************/

	/**
	 * Adiciona un carnet con la información dada por el usuario
	 * Se crea una nueva tupla de Carnet en la base de datos, si un Carnet con ese identificador no existía
	 */
	public void adicionarCarnet()
	{
		try
		{
			String idVisitante = JOptionPane.showInputDialog (this, "Identificador del visitante dueño del carnet: ", "Adicionar carnet", JOptionPane.QUESTION_MESSAGE);
			String tipoCarnetStr = JOptionPane.showInputDialog (this, "Tipo de carnet (QR o Fisico): ", "Adicionar carnet", JOptionPane.QUESTION_MESSAGE);
			if (idVisitante != null)
			{
				if ( !tipoCarnetStr.equals("QR") && !tipoCarnetStr.equals("Fisico"))
				{
					JOptionPane.showInputDialog (this, "El tipo de carnet ingresado es inválido ", "Adicionar carnet", JOptionPane.ERROR_MESSAGE);
				}
				VOCarnet carnet = aforoAndes.adicionarCarnet(tipoCarnetStr, idVisitante);
				if (carnet == null)
				{
					throw new Exception ("No se pudo crear un carnet del visitante: " + idVisitante);
				}

				String resultado = "En adicionar Carnet\n\n";
				resultado += "Carnet adicionado exitosamente: " + carnet;
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los carnets existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarCarnets( )
	{
		try 
		{
			List <VOCarnet> lista = aforoAndes.darVOCarnets();

			String resultado = "En listar Carnet";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos el carnet con la identificación dada por el usuario
	 * Cuando dicho carnet no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarCarnetPorId( )
	{
		try 
		{
			String idVisitante = JOptionPane.showInputDialog (this, "Identificación del visitante dueño del carnet: ", "Borrar carnet por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idVisitante != null)
			{
				long carnetsEliminados = aforoAndes.eliminarCarnetPorIdVisitante(idVisitante);

				String resultado = "En eliminar Carnet\n\n";
				resultado += carnetsEliminados + " Carnets eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el carnet con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarCarnetPorIdentificacion( )
	{
		try 
		{
			String idVisitante = JOptionPane.showInputDialog (this, "Identificacion del visitante dueño del carnet: ", "Buscar carnet por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idVisitante != null)
			{
				VOCarnet carnet = aforoAndes.darCarnetPorIdVisitante(idVisitante);
				String resultado = "En buscar Carnet por identificacion\n\n";
				if (carnet != null)
				{
					resultado += "El carnet es: " + carnet;
				}
				else
				{
					resultado += "Un carnet perteneciente al visitante: " + idVisitante + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el carnet con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarCarnetsPorTipo( )
	{
		try 
		{
			String tipoCarnet = JOptionPane.showInputDialog (this, "Tipo del carnet: ", "Buscar carnets por tipo", JOptionPane.QUESTION_MESSAGE);
			if (tipoCarnet != null)
			{
				List<Carnet> lista = aforoAndes.darCarnetsPorTipo(tipoCarnet);
				String resultado = "En buscar Carnet por nombre: \n\n";
				resultado +=  "\n" + listarObjetos (lista);
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Domiciliario
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un domiciliario.
	 */
	public void adicionarDomiciliario( )
	{
		DialogoAdicionarDomiciliario dialogo = new DialogoAdicionarDomiciliario( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("En proceso de adición");
	}

	/**
	 * Adiciona un domiciliario con la información dada por el usuario
	 * Se crea una nueva tupla de Domiciliario en la base de datos, si un domiciliario con esa identificación no existía
	 * @param idVisitante - El identificador del visitante
	 * @param empresaDomicilios - La empresa de domicilios donde trabaja el domiciliario
	 * @param horaInicioTurno - Hora de inicio del turno de trabajo
	 * @param minutoInicioTurno - Minuto de inicio del turno de trabajo
	 * @param horaFinalTurno - Hora final del turno de trabajo
	 * @param minutoFinalTurno - Minuto final del turno de trabajo
	 */
	public void adicionarDomiciliario (String idVisitante, String empresaDomicilios, int horaInicioTurno, int minutoInicioTurno, int horaFinalTurno, int minutoFinalTurno, DialogoAdicionarDomiciliario pDialogo)
	{
		try
		{
			VODomiciliario domiciliario = aforoAndes.adicionarDomiciliario(idVisitante, empresaDomicilios, horaInicioTurno, minutoInicioTurno, horaFinalTurno, minutoFinalTurno);
			if (domiciliario == null)
			{
				throw new Exception ("No se pudo crear un domiciliario con identificación: " + idVisitante);
			}
			String resultado = "En adicionar Domiciliario\n\n";
			resultado += "Domiciliario adicionado exitosamente: " + domiciliario;
			resultado += "\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
			pDialogo.dispose();

		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los domiciliarios existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarDomiciliarios( )
	{
		try 
		{
			List <VODomiciliario> lista = aforoAndes.darVODomiciliario();
			String resultado = "En listar Domiciliario";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos el domiciliario con la identificación dada por el usuario
	 * Cuando dicho domiciliario no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarDomiciliarioPorIdentificacion( )
	{
		try 
		{
			String idEmpleado = JOptionPane.showInputDialog (this, "Identificación del domiciliario: ", "Borrar domiciliario por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idEmpleado != null)
			{
				long domiciliariosEliminados = aforoAndes.eliminarDomiciliarioPorId(idEmpleado);

				String resultado = "En eliminar Domiciliario\n\n";
				resultado += domiciliariosEliminados + " Domiciliarios eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el domiciliario con la identificación indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarDomiciliarioPorIdentificacion( )
	{
		try 
		{
			String idEmpleado = JOptionPane.showInputDialog (this, "Identificacion del domiciliario: ", "Buscar domiciliario por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idEmpleado != null)
			{
				VODomiciliario visitante = aforoAndes.darDomiciliarioPorId(idEmpleado);
				String resultado = "En buscar Domiciliario por identificacion\n\n";
				if (visitante != null)
				{
					resultado += "El domiciliario es: " + visitante;
				}
				else
				{
					resultado += "Un domiciliario con identificacion: " + idEmpleado + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar la empresa de domicilios del domiciliario con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarEmpresaDomiciliosDomiciliario( )
	{
		try 
		{
			String idDomiciliario = JOptionPane.showInputDialog (this, "Identificador del domiciliario: ", "Actualizar empresa del domiciliario", JOptionPane.QUESTION_MESSAGE);
			String empresaDomicilios = JOptionPane.showInputDialog (this, "Nueva empresa de domicilios: ", "Actualizar empresa del domiciliario", JOptionPane.QUESTION_MESSAGE);

			if (idDomiciliario != null && empresaDomicilios != null )
			{	
				long modificados = aforoAndes.cambiarEmpresaDomiciliario(idDomiciliario, empresaDomicilios);
				String resultado = "En actualizar Domiciliario por empresa: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 

		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el horario de turno del domiciliario con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarHorarioTurnoDomiciliario( )
	{
		try 
		{
			String idEmpleado = JOptionPane.showInputDialog (this, "Identificador del domiciliario: ", "Actualizar horario de turno del domiciliario", JOptionPane.QUESTION_MESSAGE);
			String horaInicioTurnoStr = JOptionPane.showInputDialog (this, "Nueva hora de inicio del turno (hh:mm): ", "Actualizar horario de turno del domiciliario", JOptionPane.QUESTION_MESSAGE);
			String horaFinalTurnoStr = JOptionPane.showInputDialog (this, "Nueva hora final del turno (hh:mm): ", "Actualizar horario de turno del domiciliario", JOptionPane.QUESTION_MESSAGE);

			if (idEmpleado != null && horaInicioTurnoStr != null && horaFinalTurnoStr != null)
			{	
				int horaInicioTurno;
				int minutoInicioTurno;
				int horaFinalTurno;
				int minutoFinalTurno;

				horaInicioTurno = Integer.parseInt(horaInicioTurnoStr.split(":")[0]);
				minutoInicioTurno = Integer.parseInt(horaInicioTurnoStr.split(":")[1]);
				horaFinalTurno = Integer.parseInt(horaFinalTurnoStr.split(":")[0]);
				minutoFinalTurno = Integer.parseInt(horaFinalTurnoStr.split(":")[1]);

				if ( horaInicioTurno > horaFinalTurno || (horaFinalTurno == horaInicioTurno && minutoFinalTurno <= minutoInicioTurno))
				{
					JOptionPane.showMessageDialog( this, "La hora de final de turno debe ser posterior a la hora de inicio de turno", "Actualizar horario de turno del domiciliario", JOptionPane.ERROR_MESSAGE );
				}
				long modificados = aforoAndes.cambiarHorarioTurnoDomiciliario(idEmpleado, horaInicioTurno, minutoInicioTurno, horaFinalTurno, minutoFinalTurno);
				String resultado = "En actualizar Domiciliario por horario de turno: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "Las horas deben ser números separados por ':'", "Actualizar horario de turno del domiciliario", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Empleado
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un empleado.
	 */
	public void adicionarEmpleado( )
	{
		DialogoAdicionarEmpleado dialogo = new DialogoAdicionarEmpleado( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("En proceso de adición");
	}

	/**
	 * Adiciona un empleado con la información dada por el usuario
	 * Se crea una nueva tupla de Empleado en la base de datos, si un empleado con esa identificación no existía
	 * @param idVisitante - El identificador del visitante
	 * @param lugarTrabajo - El lugar de trabajo del empleado 
	 * @param horaInicioTurno - Hora de inicio del turno de trabajo
	 * @param minutoInicioTurno - Minuto de inicio del turno de trabajo
	 * @param horaFinalTurno - Hora final del turno de trabajo
	 * @param minutoFinalTurno - Minuto final del turno de trabajo
	 */
	public void adicionarEmpleado (String idVisitante, String lugarTrabajo, int horaInicioTurno, int minutoInicioTurno, int horaFinalTurno, int minutoFinalTurno, DialogoAdicionarEmpleado pDialogo)
	{
		try
		{
			VOEmpleado empleado = aforoAndes.adicionarEmpleado(idVisitante, lugarTrabajo, horaInicioTurno, minutoInicioTurno, horaFinalTurno, minutoFinalTurno);
			if (empleado == null)
			{
				throw new Exception ("No se pudo crear un empleado con identificación: " + idVisitante);
			}
			String resultado = "En adicionar Empleado\n\n";
			resultado += "Empleado adicionado exitosamente: " + empleado;
			resultado += "\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
			pDialogo.dispose();

		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los empleados existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarEmpleados( )
	{
		try 
		{
			List <VOEmpleado> lista = aforoAndes.darVOEmpleados();

			String resultado = "En listar Empleado";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos el empleado con la identificación dada por el usuario
	 * Cuando dicho empleado no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarEmpleadoPorIdentificacion( )
	{
		try 
		{
			String idEmpleado = JOptionPane.showInputDialog (this, "Identificación del empleado: ", "Borrar empleado por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idEmpleado != null)
			{
				long empleadosEliminados = aforoAndes.eliminarEmpleadoPorId(idEmpleado);

				String resultado = "En eliminar Empleado\n\n";
				resultado += empleadosEliminados + " Empleados eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el empleado con la identificación indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarEmpleadoPorIdentificacion( )
	{
		try 
		{
			String idEmpleado = JOptionPane.showInputDialog (this, "Identificacion del empleado: ", "Buscar empleado por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idEmpleado != null)
			{
				VOEmpleado visitante = aforoAndes.darEmpleadoPorId(idEmpleado);
				String resultado = "En buscar Empleado por identificacion\n\n";
				if (visitante != null)
				{
					resultado += "El empleado es: " + visitante;
				}
				else
				{
					resultado += "Un empleado con identificacion: " + idEmpleado + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el lugar de trabajo del empleado con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarLugarTrabajoEmpleado( )
	{
		try 
		{
			String idEmpleado = JOptionPane.showInputDialog (this, "Identificador del domiciliario: ", "Actualizar lugar de trabajo del empleado", JOptionPane.QUESTION_MESSAGE);
			String lugarTrabajo = JOptionPane.showInputDialog (this, "Nuevo identificador del local: ", "Actualizar lugar de trabajo del empleado", JOptionPane.QUESTION_MESSAGE);

			if (idEmpleado != null && lugarTrabajo != null )
			{	
				long modificados = aforoAndes.cambiarLugarTrabajoEmpleado(idEmpleado, lugarTrabajo);
				String resultado = "En actualizar Empleado por lugar de trabajo: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 

		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el horario de turno del empleado con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarHorarioTurnoEmpleado( )
	{
		try 
		{
			String idEmpleado = JOptionPane.showInputDialog (this, "Identificador del empleado: ", "Actualizar horario de turno del empleado", JOptionPane.QUESTION_MESSAGE);
			String horaInicioTurnoStr = JOptionPane.showInputDialog (this, "Nueva hora de inicio del turno (hh:mm): ", "Actualizar horario de turno del empleado", JOptionPane.QUESTION_MESSAGE);
			String horaFinalTurnoStr = JOptionPane.showInputDialog (this, "Nueva hora final del turno (hh:mm): ", "Actualizar horario de turno del empleado", JOptionPane.QUESTION_MESSAGE);

			if (idEmpleado != null && horaInicioTurnoStr != null && horaFinalTurnoStr != null)
			{	
				int horaInicioTurno;
				int minutoInicioTurno;
				int horaFinalTurno;
				int minutoFinalTurno;

				horaInicioTurno = Integer.parseInt(horaInicioTurnoStr.split(":")[0]);
				minutoInicioTurno = Integer.parseInt(horaInicioTurnoStr.split(":")[1]);
				horaFinalTurno = Integer.parseInt(horaFinalTurnoStr.split(":")[0]);
				minutoFinalTurno = Integer.parseInt(horaFinalTurnoStr.split(":")[1]);

				if ( horaInicioTurno > horaFinalTurno || (horaFinalTurno == horaInicioTurno && minutoFinalTurno <= minutoInicioTurno))
				{
					JOptionPane.showMessageDialog( this, "La hora de final de turno debe ser posterior a la hora de inicio de turno", "Actualizar horario de turno del empleado", JOptionPane.ERROR_MESSAGE );
				}

				long modificados = aforoAndes.cambiarHorarioTurnoEmpleado(idEmpleado, horaInicioTurno, minutoInicioTurno, horaFinalTurno, minutoFinalTurno);
				String resultado = "En actualizar Empleado por horario de turno: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "Las horas deben ser números separados por ':'", "Actualizar horario de turno del domiciliario", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de LOCALCOMERCIAL
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un local
	 */
	public void adicionarLocalComercial( )
	{
		if ( administrador )
		{
			DialogoAdicionarLocalComercial dialogo = new DialogoAdicionarLocalComercial( this );
			dialogo.setVisible( true );
			panelDatos.actualizarInterfaz("En proceso de adición");
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}	
	}

	/**
	 * Adiciona un local comercial con la información dada por el usuario
	 * Se crea una nueva tupla de LocalComercial en la base de datos, si un local comercial con ese identificador no existía
	 */
	public void adicionarLocalComercial (String idLocalComercial, int capacidadNormal, double area, long tipoLocal, boolean activoBooleano, String idCentroComercial, DialogoAdicionarLocalComercial pDialogo) 
	{
		try 
		{
			long idArea = buscarAreaPorValor(area).getId();
			Long idCapacidadNormal = null;
			if ( capacidadNormal != -1)
			{
				idCapacidadNormal = buscarCapacidadNormalPorValor(capacidadNormal).getId();
			}
			int activo = 0;
			if (activoBooleano)
				activo = 1;
			VOLocalComercial localComercial = aforoAndes.adicionarLocalComercial(idLocalComercial, idCapacidadNormal, idArea, tipoLocal, activo, idCentroComercial);

			if (localComercial == null)
			{
				throw new Exception ("No se pudo crear un local comercial con identificador: " + idLocalComercial);
			}
			pDialogo.dispose();
			String resultado = "En adicionar LocalComercial\n\n";
			resultado += "Local comercial adicionado exitosamente: " + localComercial;
			resultado += "\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los locales comerciales existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarLocalesComerciales( )
	{
		try 
		{
			List <VOLocalComercial> lista = aforoAndes.darVOLocalesComerciales();

			String resultado = "En listar LocalComercial";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca  el local comercial por identificador
	 */
	public void buscarLocalComercialPorIdentificador( )
	{
		try 
		{
			String identificador = JOptionPane.showInputDialog (this, "Identificador del local comercial: ", "Buscar local comercial por identificador", JOptionPane.QUESTION_MESSAGE);
			if (identificador != null)
			{
				VOLocalComercial localComercial= aforoAndes.darLocalComercialPorId(identificador);
				String resultado = "En buscar LocalComercial por id\n\n";
				if (localComercial != null)
				{
					resultado += "El local comercial es: " + localComercial;
				}
				else
				{
					resultado += "El local comercial con identificador: " + identificador + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca los locales comerciales por identificador del Centro Comercial.
	 */
	public void buscarLocalComercialPorIDCC( )
	{
		try 
		{
			String idCentroComercial = JOptionPane.showInputDialog (this, "Identificador del Centro Comercial: ", "Buscar por identificador Centro Comercial", JOptionPane.QUESTION_MESSAGE);
			if (idCentroComercial != null)
			{
				List <LocalComercial> lista = aforoAndes.darLocalesComercialesPorIDCC(idCentroComercial);
				String resultado = "En listar LocalComercial";
				resultado +=  "\n" + listarObjetos (lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n\n Operación terminada";
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el área de un local comercial
	 */
	public void cambiarAreaLocalComercial( )
	{
		try 
		{
			String idLocal = JOptionPane.showInputDialog (this, "Identificador del local comercial: ", "Actualizar área de un local", JOptionPane.QUESTION_MESSAGE);
			String areaStr = JOptionPane.showInputDialog (this, "Nuevo identificador del área: ", "Actualizar área de un local", JOptionPane.QUESTION_MESSAGE);
			if (idLocal != null && areaStr != null)
			{	
				long area = Long.valueOf(areaStr);
				long modificados = aforoAndes.cambiarAreaLocalComercial(idLocal, area);
				String resultado = "En actualizar LocalComercial: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "El área debe ser un número", "Actualizar área de un local", JOptionPane.ERROR_MESSAGE );
		}		
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar la actividad de un local comercial
	 */
	public void cambiarActividadLocalComercial( )
	{
		try 
		{
			if ( administrador )
			{
				String idLocal = JOptionPane.showInputDialog (this, "Identificador del local comercial: ", "Actualizar actividad de un local", JOptionPane.QUESTION_MESSAGE);
				String actividadStr = JOptionPane.showInputDialog (this, "Nuevo estado de actividad (1 - activo, 0 - inactivo): ", "Actualizar actividad de un local", JOptionPane.QUESTION_MESSAGE);
				if (idLocal != null && actividadStr != null)
				{	
					int activo = Integer.parseInt(actividadStr);
					long modificados = aforoAndes.cambiarActividadLocalComercial(idLocal, activo);
					String resultado = "En actualizar LocalComercial: \n\n";
					resultado += modificados + " registros actualizados";
					resultado += "\n\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else
			{
				String resultado = "\n No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
				throw new Exception (resultado);
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "El estado de actividad debe ser un número", "Actualizar actividad de un local", JOptionPane.ERROR_MESSAGE );
		}		
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el local comercial con el identificador dado por el usuario
	 * Cuando dicho local no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarLocalComercialPorIdentificador( )
	{
		try 
		{
			String idLocalComercial = JOptionPane.showInputDialog (this, "Identificador del local comercial: ", "Borrar local comercial por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idLocalComercial != null)
			{
				long localesEliminados = aforoAndes.eliminarLocalComercialPorId(idLocalComercial);
				String resultado = "En eliminar LocalComercial\n\n";
				resultado += localesEliminados + " Locales comerciales eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos los locales comerciales con la actividad dada por el usuario
	 * Cuando dichos locales no existen, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarLocalComercialPorActividad( )
	{
		try 
		{
			String actividadStr = JOptionPane.showInputDialog (this, "Estado de actividad (1 - activo, 0 - inactivo): ", "Borrar local comercial por actividad", JOptionPane.QUESTION_MESSAGE);
			if (actividadStr != null)
			{
				int actividad = Integer.parseInt(actividadStr);
				if ( actividad != 0 && actividad != 1 )
				{
					throw new Exception ("La actividad ingresada no es válida, debería ser 1 o 0.");
				}
				long localesEliminados = aforoAndes.eliminarLocalComercialPorActividad(actividad);
				String resultado = "En eliminar LocalComercial\n\n";
				resultado += localesEliminados + " Locales comerciales eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "La actividad debe ser un número", "Borrar local comercial por actividad", JOptionPane.ERROR_MESSAGE );
		}		
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Lector
	 *****************************************************************/
	/**
	 * Crea una ventana de diálogo para agregar un lector
	 */
	public void adicionarLector( )
	{

		if ( administrador )
		{
			DialogoAdicionarLector dialogo = new DialogoAdicionarLector( this );
			dialogo.setVisible( true );

		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}			

	}

	/**
	 * Adiciona un lector con la información dada por el usuario
	 * Se crea una nueva tupla de Lector en la base de datos, si un lector con ese identificador no existía
	 */
	public void adicionarLector(long id, long tipoLector, String idCentroComercial, String idLocalComercial, String idAscensor, String idParqueadero, String idBaño, DialogoAdicionarLector pDialogo) 
	{
		try 
		{

			VOLector lector = aforoAndes.adicionarLector(id, tipoLector, idCentroComercial, idLocalComercial, idAscensor, idParqueadero, idBaño);
			if (lector == null)
			{
				throw new Exception ("No se pudo crear un lector con identificador: " + id);
			}
			String resultado = "En adicionar Lector\n\n";
			resultado += "Lector adicionado exitosamente: " + lector;
			resultado += "\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
			pDialogo.dispose();

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los lectores existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarLectores( )
	{
		try 
		{
			List <VOLector> lista = aforoAndes.darVOLectores();

			String resultado = "En listar Lector";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el lector por identificador
	 */
	public void buscarLectorPorIdentificador( )
	{
		try 
		{
			String idStr = JOptionPane.showInputDialog (this, "Identificador del lector: ", "Buscar lector por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idStr != null)
			{
				long id = Long.valueOf(idStr);
				VOLector lector= aforoAndes.darLectorPorId(id);
				String resultado = "En buscar Lector por id\n\n";
				if (lector != null)
				{
					resultado += "El lector es: " + lector;
				}
				else
				{
					resultado += "El lector con identificación: " + id + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "El identificador debe ser un número", "Buscar lector por identificador", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el lector por tipo
	 */
	public void buscarLectorPorTipo( )
	{
		try 
		{
			String idTipoStr = JOptionPane.showInputDialog (this, "Identificador del tipo del lector: ", "Buscar lector por tipo", JOptionPane.QUESTION_MESSAGE);
			if (idTipoStr != null)
			{
				long idTipo = Long.valueOf(idTipoStr);
				List <Lector> lista = aforoAndes.darLectoresPorTipo(idTipo);
				String resultado = "En listar Lector";
				resultado +=  "\n" + listarObjetos (lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n\n Operación terminada";
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "El tipo de lector debe ser un número", "Borrar lector por identificador", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el tipo de un lector
	 */
	public void cambiarTipoLector( )
	{
		try 
		{
			String idLectorStr = JOptionPane.showInputDialog (this, "Identificador del lector: ", "Actualizar tipo de un lector", JOptionPane.QUESTION_MESSAGE);
			String idTipoStr = JOptionPane.showInputDialog (this, "Nuevo identificador del tipo: ", "Actualizar tipo de un lector", JOptionPane.QUESTION_MESSAGE);
			if (idLectorStr != null && idTipoStr != null)
			{	
				long idTipo = Long.valueOf(idTipoStr);
				long idLector = Long.valueOf(idLectorStr);
				long modificados = aforoAndes.cambiarTipoLector(idLector, idTipo);
				String resultado = "En actualizar Lector: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "El identificador del lector y del tipo de lector deben ser números", "Borrar lector por identificador", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Borra de la base de datos el lector con el identificador dado por el usuario
	 * Cuando dicho lector no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarLectorPorIdentificador( )
	{
		try 
		{
			String idLectorStr = JOptionPane.showInputDialog (this, "Identificador del lector: ", "Borrar lector por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idLectorStr != null)
			{
				long idLector = Long.valueOf(idLectorStr);
				long localesEliminados = aforoAndes.eliminarLectorPorId(idLector);
				String resultado = "En eliminar Lector\n\n";
				resultado += localesEliminados + " Lectores eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		}
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "El identificador debe ser un número", "Borrar lector por identificador", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de PARQUEADERO
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un parqueadero
	 */
	public void adicionarParqueadero( )
	{
		if ( administrador )
		{
			DialogoAdicionarParqueadero dialogo = new DialogoAdicionarParqueadero( this );
			dialogo.setVisible( true );
			panelDatos.actualizarInterfaz("En proceso de adición");
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}		
	}
	/**
	 * Adiciona un parqueadero con la información dada por el usuario
	 * Se crea una nueva tupla de Parqueadero en la base de datos, si un parqueadero con ese identificador no existía
	 */
	public void adicionarParqueadero( String idParqueadero, int capacidadNormal, double area, String idCentroComercial, DialogoAdicionarParqueadero pDialogo)
	{
		try 
		{

			Long idArea = null;
			if ( area != -1)
			{
				idArea = buscarAreaPorValor(area).getId();
			}			
			long idCapacidadNormal = buscarCapacidadNormalPorValor(capacidadNormal).getId();
			VOParqueadero ascensor = aforoAndes.adicionarParqueadero(idParqueadero, idCapacidadNormal, idArea, idCentroComercial);

			if (ascensor == null)
			{
				throw new Exception ("No se pudo crear un parqueadero con identificador: " + idParqueadero);
			}
			pDialogo.dispose();
			String resultado = "En adicionar Parqueadero\n\n";
			resultado += "Parqueadero adicionado exitosamente: " + ascensor;
			resultado += "\n\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los parqueaderos existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarParqueaderos( )
	{
		try 
		{
			List <VOParqueadero> lista = aforoAndes.darVOParqueaderos();

			String resultado = "En listar Parqueadero";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el parqueadero con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarParqueaderoPorIdentificador( )
	{
		try 
		{
			String idParqueadero = JOptionPane.showInputDialog (this, "Identificador del parqueadero: ", "Buscar parqueadero por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idParqueadero != null)
			{
				VOParqueadero parqueadero = aforoAndes.darParqueaderoPorId(idParqueadero);
				String resultado = "En buscar Parqueadero por identificador\n\n";
				if (parqueadero != null)
				{
					resultado += "El parqueadero es: " + parqueadero;
				}
				else
				{
					resultado += "Un parqueadero con identificador: " + idParqueadero + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el área de un parqueadero
	 */
	public void cambiarAreaParqueadero( )
	{
		try 
		{
			String idParqueadero = JOptionPane.showInputDialog (this, "Identificador del parqueadero: ", "Actualizar área del parqueadero", JOptionPane.QUESTION_MESSAGE);
			String areaStr = JOptionPane.showInputDialog (this, "Nuevo identificador del área: ", "Actualizar área del parqueadero", JOptionPane.QUESTION_MESSAGE);
			if (idParqueadero != null && areaStr != null)
			{	
				long area = Long.valueOf (areaStr);
				long modificados = aforoAndes.cambiarAreaParqueadero(idParqueadero, area);
				String resultado = "En actualizar Parqueadero: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch( NumberFormatException e )
		{
			JOptionPane.showMessageDialog( this, "El área debe ser un número", "Actualizar área del parqueadero", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el parqueadero con el identificador dado por el usuario
	 * Cuando dicho parqueadero no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarParqueaderoPorIdentificador( )
	{
		try 
		{
			String idParqueadero = JOptionPane.showInputDialog (this, "Identificador del parqueadero:", "Borrar parqueadero por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idParqueadero != null)
			{
				String idTipo = idParqueadero;
				long parqueaderosEliminados = aforoAndes.eliminarParqueaderoPorId (idTipo);

				String resultado = "En eliminar Parqueadero\n\n";
				resultado += parqueaderosEliminados + " parqueaderos eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de TipoLocal
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un tipo de local
	 */
	public void adicionarTipoLocal( )
	{
		if ( administrador )
		{
			DialogoAdicionarTipoLocal dialogo = new DialogoAdicionarTipoLocal( this );
			dialogo.setVisible( true );

		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}			
	}

	/**
	 * Adiciona un tipo de local con la información dada por el usuario
	 * @param tipo - El nombre del tipo del visitante
	 * @param horainicio - La hora de inicio de circulación del tipo del visitante
	 * @param minutoApertura - El minuto de inicio de circulación del tipo del visitante
	 * @param horalimite - La hora límite de circulación del tipo del visitante
	 * @param minutoApertura - El minuto límite de circulación del tipo del visitante
	 * Se crea una nueva tupla de TipoLocal en la base de datos, si un tipoLocal con ese nombre no existía
	 */
	public void adicionarTipoLocal( String tipo, int horaApertura, int minutoApertura, int horaCierre, int minutoCierre, DialogoAdicionarTipoLocal pDialogo )
	{
		try
		{
			VOTipoLocal tl = aforoAndes.adicionarTipoLocal(tipo, horaApertura, minutoApertura, horaCierre, minutoCierre);
			if (tl == null)
			{
				throw new Exception ("No se pudo crear un tipo de local con tipo: " + tipo);
			}
			String resultado = "En adicionar TipoLocal\n\n";
			resultado += "Tipo de local adicionado exitosamente: " + tl;
			resultado += "\n\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
			pDialogo.dispose();

		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los tipos de local existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarTiposLocal( )
	{
		try 
		{
			List <VOTipoLocal> lista = aforoAndes.darVOTiposLocal();

			String resultado = "En listar TipoLocal";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el horario de funcionamiento del tipo de local indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarHorarioTipoLocal( )
	{
		try 
		{
			String tipo = JOptionPane.showInputDialog (this, "Tipo de local: ", "Actualizar horario del tipo de local", JOptionPane.QUESTION_MESSAGE);
			String horaAperturaStr = JOptionPane.showInputDialog (this, "Nueva hora de apertura (hh:mm): ", "Actualizar horario del tipo de local", JOptionPane.QUESTION_MESSAGE);
			String horaCierreStr = JOptionPane.showInputDialog (this, "Nueva hora de cierre (hh:mm): ", "Actualizar horario del tipo de local", JOptionPane.QUESTION_MESSAGE);

			if (tipo != null && horaAperturaStr != null && horaCierreStr != null)
			{	
				int horaApertura;
				int minutoApertura;
				int horaCierre;
				int minutoCierre;

				horaApertura = Integer.parseInt(horaAperturaStr.split(":")[0]);
				minutoApertura = Integer.parseInt(horaAperturaStr.split(":")[1]);
				horaCierre = Integer.parseInt(horaCierreStr.split(":")[0]);
				minutoCierre = Integer.parseInt(horaCierreStr.split(":")[1]);

				if ( horaApertura > horaCierre || (horaCierre == horaApertura && minutoCierre <= minutoApertura))
				{
					JOptionPane.showMessageDialog( this, "La hora de cierre debe ser posterior a la hora de apertura", "Actualizar horario del tipo de local", JOptionPane.ERROR_MESSAGE );
				}
				long modificados = aforoAndes.cambiarHorarioTipoLocal(tipo, horaApertura, minutoApertura, horaCierre, minutoCierre);
				String resultado = "En actualizar TipoLocal por horario de funcionamientos: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "Las horas deben ser números separados por ':'", "Actualizar horario del tipo de local", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el tipo de local con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarTipoLocalPorTipo( )
	{
		try 
		{
			String tipo = JOptionPane.showInputDialog (this, "Nombre del tipo de local: ", "Buscar tipo de local por nombre", JOptionPane.QUESTION_MESSAGE);
			if (tipo != null)
			{
				VOTipoLocal tipoLocal = aforoAndes.darTipoLocalPorTipo(tipo);
				String resultado = "En buscar TipoLocal por nombre\n\n";
				if (tipoLocal != null)
				{
					resultado += "El tipo de local es: " + tipoLocal;
				}
				else
				{
					resultado += "Un tipo de local con tipo: " + tipo + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el tipo local con el tipo dado por el usuario
	 * Cuando dicho tipo no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarTipoLocalPorTipo( )
	{
		try 
		{
			String tipo = JOptionPane.showInputDialog (this, "Nombre del tipo de local: ", "Borrar tipo de local por tipo", JOptionPane.QUESTION_MESSAGE);
			if (tipo != null)
			{
				long tlEliminados = aforoAndes.eliminarTipoLocalPorTipo(tipo);
				String resultado = "En eliminar TipoLocal\n\n";
				resultado += tlEliminados + " Tipos de local eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de TiposVisitante
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un tipo de visitante
	 */
	public void adicionarTipoVisitante( )
	{
		if ( administrador )
		{
			DialogoAdicionarTipoVisitante dialogo = new DialogoAdicionarTipoVisitante( this );
			dialogo.setVisible( true );
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}		
	}

	/**
	 * Adiciona un tipo de visitante con la información dada por el usuario
	 * @param tipo - El nombre del tipo del visitante
	 * @param horainicio - La hora de inicio de circulación del tipo del visitante
	 * @param minutoInicio - El minuto de inicio de circulación del tipo del visitante
	 * @param horalimite - La hora límite de circulación del tipo del visitante
	 * @param minutoInicio - El minuto límite de circulación del tipo del visitante
	 * Se crea una nueva tupla de TipoVisitante en la base de datos, si un tipoVisitante con ese nombre no existía
	 */
	public void adicionarTipoVisitante( String tipo, int horaInicio, int minutoInicio, int horaLimite, int minutoLimite, DialogoAdicionarTipoVisitante pDialogo )
	{
		try
		{
			VOTipoVisitante tv = aforoAndes.adicionarTipoVisitante(tipo, horaInicio, minutoInicio, horaLimite, minutoLimite);
			if (tv == null)
			{
				throw new Exception ("No se pudo crear un tipo de visitante con tipo: " + tipo);
			}
			pDialogo.dispose();
			String resultado = "En adicionar TipoVisitante\n\n";
			resultado += "Tipo de visitante adicionado exitosamente: " + tv;
			resultado += "\n\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los tipos de visitante existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarTiposVisitante( )
	{
		try 
		{
			List <VOTipoVisitante> lista = aforoAndes.darVOTiposVisitante();
			String resultado = "En listar TipoVisitante";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el tipo de visitante con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarTipoVisitantePorTipo( )
	{
		try 
		{
			String tipo = JOptionPane.showInputDialog (this, "Nombre del tipo de visitante: ", "Buscar tipo de visitante por nombre", JOptionPane.QUESTION_MESSAGE);
			if (tipo != null)
			{
				VOTipoVisitante tipoVisitante = aforoAndes.darTipoVisitantePorTipo(tipo);
				String resultado = "En buscar Tipo Visitante por nombre\n\n";
				if (tipoVisitante != null)
				{
					resultado += "El tipo de visitante es: " + tipoVisitante;
				}
				else
				{
					resultado += "Un tipo de visitante con tipo: " + tipo + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el horario de circulación del tipo de visitante indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarHorarioTipoVisitante( )
	{
		try 
		{
			String tipo = JOptionPane.showInputDialog (this, "Tipo de visitante: ", "Actualizar horario del tipo de visitante", JOptionPane.QUESTION_MESSAGE);
			String horarioInicioStr = JOptionPane.showInputDialog (this, "Nueva hora de inicio de circulación(hh:mm): ", "Actualizar horario del tipo de visitante", JOptionPane.QUESTION_MESSAGE);
			String horarioLimiteStr = JOptionPane.showInputDialog (this, "Nueva hora final de circulación (hh:mm): ", "Actualizar horario del tipo de visitante", JOptionPane.QUESTION_MESSAGE);

			if (tipo != null && horarioInicioStr != null && horarioLimiteStr != null)
			{	
				int horaInicio;
				int minutoInicio;
				int horaLimite;
				int minutoLimite;

				horaInicio = Integer.parseInt(horarioInicioStr.split(":")[0]);
				minutoInicio = Integer.parseInt(horarioInicioStr.split(":")[1]);
				horaLimite = Integer.parseInt(horarioLimiteStr.split(":")[0]);
				minutoLimite = Integer.parseInt(horarioLimiteStr.split(":")[1]);

				if ( horaInicio > horaLimite || (horaLimite == horaInicio && minutoLimite <= minutoInicio))
				{
					JOptionPane.showMessageDialog( this, "La hora límite debe ser posterior a la hora de inicio de circulación", "Actualizar horario del tipo de visitante", JOptionPane.ERROR_MESSAGE );
				}
				long modificados = aforoAndes.cambiarHorarioTipoVisitante(tipo, horaInicio, minutoInicio, horaLimite, minutoLimite);
				String resultado = "En actualizar TipoVisitante por horario de circulación: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "Las horas deben ser números separados por ':'", "Actualizar horario del tipo de visitante", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el tipo visitante con el tipo dado por el usuario
	 * Cuando dicho tipo no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarTipoVisitantePorTipo( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del tipo de visitante: ", "Borrar tipo de visitante por tipo", JOptionPane.QUESTION_MESSAGE);
			if (nombre != null)
			{
				long tbEliminados = aforoAndes.eliminarTipoVisitantePorTipo(nombre);

				String resultado = "En eliminar TipoVisitante\n\n";
				resultado += tbEliminados + " Tipos de visitante eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD Registran Carnet
	 *****************************************************************/
	/**
	 * Crea una ventana de diálogo para registrar una visita
	 */
	public void adicionarRegistranCarnet( )
	{
		DialogoRegistrarCarnet dialogo = new DialogoRegistrarCarnet( this );
		dialogo.setVisible( true );
	}

	/**
	 * Registra una visita con la información dada por el usuario
	 * @param idLector - El id del lector 
	 * @param tipoCarnet - El tipo del carnet del visitante
	 * @param idVisitante - El identificador del visitante
	 * @param fecha - La fecha de ingreso
	 * @param horaEntrada - La hora de ingreso
	 * @param minutoEntrada - El minuto de ingreso 
	 * Se crea una nueva tupla de RegistranCarnet en la base de datos, si un registro con esa información no existía
	 */
	public void adicionarRegistranCarnet(long idLector, long tipoCarnet, String idVisitante, Timestamp fecha, int horaEntrada, int minutoEntrada, DialogoRegistrarCarnet pDialogo)
	{
		try
		{
			List<RegistranCarnet> visitasPendientes = aforoAndes.darRegistranCarnetPorIdVisitanteHoraNULL(idVisitante);
			if (visitasPendientes != null && visitasPendientes.get(0) != null)
			{
				throw new Exception ("No es posible registrar una nueva visita porque existe una pendiente de actualizar hora salida");
			}
			String[] idEspacioVisitado = aforoAndes.darLectorPorId(idLector).getEspacioOcupado();
			long tipoVisitante = aforoAndes.darVisitantePorId(idVisitante).getTipo();

			if ( idEspacioVisitado[1].equals("LC"))
			{					
				Object[] horariosLocal = aforoAndes.darHorariosLocal(idEspacioVisitado[0]);
				Integer activo = ((BigDecimal)(horariosLocal[0])).intValue();
				Integer horaApertura = ((BigDecimal)(horariosLocal[1])).intValue();
				Integer minutoApertura = ((BigDecimal)(horariosLocal[2])).intValue();
				Integer horaCierre = ((BigDecimal)(horariosLocal[3])).intValue();
				Integer minutoCierre = ((BigDecimal)(horariosLocal[4])).intValue();

				if ( tipoVisitante != 3 && tipoVisitante != 4 && (activo == 0 || horaEntrada < horaApertura || (horaApertura == horaEntrada && minutoEntrada < minutoApertura ||
						horaEntrada > horaCierre || (horaCierre == horaEntrada && minutoEntrada > minutoCierre))))
				{	
					throw new Exception ("No es posible registrar una visita al local " + idEspacioVisitado[0] + " fuera de los horarios de funcionamiento si no es personal de aseo o mantenimiento.");
				}

			}

			Object[] horariosCirculacionVisitante = aforoAndes.darHorariosVisitante(idVisitante);
			Integer horaInicio = ((BigDecimal)(horariosCirculacionVisitante[1])).intValue();
			Integer minutoInicio = ((BigDecimal)(horariosCirculacionVisitante[2])).intValue();
			Integer horaLimite = ((BigDecimal)(horariosCirculacionVisitante[3])).intValue();
			Integer minutoLimite = ((BigDecimal)(horariosCirculacionVisitante[4])).intValue();

			if ( horaEntrada < horaInicio || (horaInicio == horaEntrada && minutoEntrada < minutoInicio ||
					horaEntrada > horaLimite || (horaLimite == horaEntrada && minutoEntrada > minutoLimite)))
			{
				throw new Exception ("No es posible registrar una visita del visitante " + idVisitante + " fuera de los horarios válidos de circulación.");
			}
			VORegistranCarnet registro = aforoAndes.adicionarRegistranCarnet(idLector, tipoCarnet, idVisitante, fecha, horaEntrada, minutoEntrada);

			if (registro == null)
			{
				throw new Exception ("No se pudo crear un registro de visita para el visitante: " + idVisitante);
			}
			String resultado = "En adicionar RegistranCarnet \n\n";
			resultado += "Registro de visita adicionado exitosamente: " + registro;
			resultado += "\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
			pDialogo.dispose();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los registros existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarRegistranCarnet( )
	{
		try 
		{
			List <VORegistranCarnet> lista = aforoAndes.darVORegistranCarnet();
			String resultado = "En listar RegistranCarnet";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el registro de visita con el identificador de lector indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarRegistranCarnetPorLector( )
	{
		try 
		{
			String idLectorStr = JOptionPane.showInputDialog (this, "Identificador del lector: ", "Buscar registro por identificador del lector", JOptionPane.QUESTION_MESSAGE);
			if (idLectorStr != null)
			{
				long idLector = Long.valueOf(idLectorStr);
				List<RegistranCarnet> lista = aforoAndes.darRegistranCarnetPorLector(idLector);
				String resultado = "En listar RegistranCarnet por lector";
				resultado +=  "\n" + listarObjetos (lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n\n Operación terminada";
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El identificador del lector debe ser un número.", "Buscar registro por identificador del lector", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el registro de visita con el identificador de visitante indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarRegistranCarnetPorIdVisitante( )
	{
		try 
		{
			String idVisitante = JOptionPane.showInputDialog (this, "Identificación del visitante: ", "Buscar registro por identificación del visitante", JOptionPane.QUESTION_MESSAGE);
			if (idVisitante != null)
			{
				List<RegistranCarnet> lista = aforoAndes.darRegistranCarnetPorIdVisitante(idVisitante);
				String resultado = "En listar RegistranCarnet por lector";
				resultado +=  "\n" + listarObjetos (lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n\n Operación terminada";
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar la hora de salida de un registro de visita indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarHoraSalidaRegistranCarnet( )
	{
		DialogoCambiarRegistro dialogo = new DialogoCambiarRegistro( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("En proceso de modificación");
	}

	/**
	 * @param idLector - El id del lector 
	 * @param tipoCarnet - El tipo del carnet del visitante
	 * @param idVisitante - El identificador del visitante
	 * @param fecha - La fecha de ingreso
	 * @param horaEntrada - La hora de ingreso
	 * @param minutoEntrada - El minuto de ingreso 
	 * Cambiar la hora de salida de un registro de visita indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarHoraSalidaRegistranCarnet(long idLector, long tipoCarnet, String idVisitante, Timestamp fecha, int horaEntrada, int minutoEntrada, int horaSalida, int minutoSalida, DialogoCambiarRegistro pDialogo)
	{
		try 
		{
			if (aforoAndes.darRegistranCarnetPorIdVisitanteFecha(idVisitante, fecha, null, horaEntrada, minutoEntrada) == null)
			{
				JOptionPane.showMessageDialog( this, "No se ha registrado la entrada del visitante al espacio.", "Actualizar hora salida de un registro", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				if (horaSalida < horaEntrada || (horaSalida == horaEntrada && minutoSalida < minutoEntrada))
				{
					JOptionPane.showMessageDialog( this, "La hora de salida debe ser posterior a la hora de entrada.", "Actualizar hora salida de un registro", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					long modificados = aforoAndes.cambiarHoraSalidaRegistranCarnet(idLector, idVisitante, fecha, horaEntrada, minutoEntrada, horaSalida, minutoSalida);
					String resultado = "En actualizar RegistranCarnet: \n\n";
					resultado += modificados + " registros actualizados";
					resultado += "\n\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				};
			}
			pDialogo.dispose();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Elimina un registro de visita indicado por el usuario y lo muestra en el panel de datos
	 */
	public void eliminarRegistranCarnet( )
	{
		DialogoCambiarRegistro dialogo = new DialogoCambiarRegistro( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("En proceso de modificación");
	}

	/**
	 * Borra de la base de datos el registro con la información dada por el usuario
	 * Cuando dicho registro no existe, se indica que se borraron 0 registros de la base de datos
	 * @param idLector - El id del lector 
	 * @param tipoCarnet - El tipo del carnet del visitante
	 * @param idVisitante - El identificador del visitante
	 * @param fecha - La fecha de ingreso
	 * @param horaEntrada - La hora de ingreso
	 * @param minutoEntrada - El minuto de ingreso 
	 */
	public void eliminarRegistranCarnet(long idLector, long tipoCarnet, String idVisitante, Timestamp fecha, int horaEntrada, int minutoEntrada, int horaSalida, int minutoSalida)
	{
		try 
		{
			long registrosEliminados = aforoAndes.eliminarRegistranCarnet(idLector, tipoCarnet, idVisitante, fecha, horaEntrada, minutoEntrada, horaSalida, minutoSalida);

			String resultado = "En eliminar RegistranCarnet\n\n";
			resultado += registrosEliminados + " registros eliminados\n";
			resultado += "\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Visitante
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un visitante.
	 */
	public void adicionarVisitante( )
	{
		DialogoAdicionarVisitante dialogo = new DialogoAdicionarVisitante( this );
		dialogo.setVisible( true );
	}

	/**
	 * Adiciona un visitante con la información dada por el usuario
	 * @param identificacion - La identificación de cada visitante del centro comercial
	 * @param nombre - Nombre del visitante
	 * @param tipo - Tipo de visitante
	 * @param correo - Correo del visitante
	 * @param telefonoPropio - Telefono del visitante
	 * @param nombreEmergencia - Contacto de emergencia del visitante
	 * @param telefonoEmergencia - Telefono del contacto de emergencia del visitante
	 * Se crea una nueva tupla de Visitante en la base de datos, si un visitante con esa identificación no existía
	 */
	public void adicionarVisitante( String identificacion, String nombre, long tipo, String correo, String telefonoPropio, String nombreEmergencia, String telefonoEmergencia, DialogoAdicionarVisitante pDialogo)
	{
		try
		{
			VOVisitante visitante = aforoAndes.adicionarVisitante(identificacion, nombre, tipo, correo, telefonoPropio, nombreEmergencia, telefonoEmergencia);
			if (visitante == null)
			{
				throw new Exception ("No se pudo crear un visitante con identificación: " + identificacion);
			}
			pDialogo.dispose();
			String resultado = "En adicionar Visitante\n\n";
			resultado += "Visitante adicionado exitosamente: " + visitante;
			resultado += "\n\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los visitantes existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarVisitantes( )
	{
		try 
		{
			List <VOVisitante> lista = aforoAndes.darVOVisitantes();
			String resultado = "En listar Visitante";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca el visitante con la identificación indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarVisitantePorIdentificacion( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificacion del visitante: ", "Buscar visitante por identificación", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null)
			{
				VOVisitante visitante = aforoAndes.darVisitantePorId(identificacion);
				String resultado = "En buscar Visitante por identificacion\n\n";
				if (visitante != null)
				{
					resultado += "El visitante es: " + visitante;
				}
				else
				{
					resultado += "Un visitante con identificacion: " + identificacion + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el telefono de emergencia del visitante con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarTelefonoEmergenciaVisitante( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificador del visitante: ", "Actualizar teléfono de emergencia del visitante", JOptionPane.QUESTION_MESSAGE);
			String telefonoEmergencia = JOptionPane.showInputDialog (this, "Nuevo teléfono de emergencia del visitante: ", "Actualizar teléfono de emergencia del visitante", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null && telefonoEmergencia != null)
			{	
				if ( telefonoEmergencia.length() < 7 )
				{
					JOptionPane.showMessageDialog( this, "Los teléfonos deben tener más de 6 dígitos.", "Actualizar Visitante", JOptionPane.ERROR_MESSAGE );
				}
				long modificados = aforoAndes.cambiarTelefonoEmergencia(identificacion, telefonoEmergencia);
				String resultado = "En actualizar Visitante por telefono de emergencia: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Cambiar el nombre de emergencia del visitante con el identificador indicado por el usuario y lo muestra en el panel de datos
	 */
	public void cambiarNombreEmergenciaVisitante( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificador del visitante: ", "Actualizar contacto de emergencia del visitante", JOptionPane.QUESTION_MESSAGE);
			String nombreEmergencia = JOptionPane.showInputDialog (this, "Nuevo nombre del contacto de emergencia del visitante: ", "Actualizar contacto de emergencia del visitante", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null && nombreEmergencia != null)
			{	
				long modificados = aforoAndes.cambiarContactoEmergenciaVisitante (identificacion, nombreEmergencia);
				String resultado = "En actualizar Visitante por contacto de emergencia: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos el visitante con la identificación dada por el usuario
	 * Cuando dicho visitante no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarVisitantePorIdentificacion( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificación del visitante: ", "Borrar visitante por identificación", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null)
			{
				long visitantesEliminados = aforoAndes.eliminarVisitantePorId(identificacion);
				String resultado = "En eliminar Visitante\n\n";
				resultado += visitantesEliminados + " Visitantes eliminados\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de ZonaCirculación
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar una zona de circulación
	 */
	public void adicionarZonaCirculacion( )
	{
		if ( administrador )
		{
			DialogoAdicionarZonaCirculacion dialogo = new DialogoAdicionarZonaCirculacion( this );
			dialogo.setVisible( true );
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}		
	}
	/**
	 * Adiciona una zona de circulación con la información dada por el usuario
	 * Se crea una nueva tupla de ZonaCirculacion en la base de datos, si una zona de circulación con ese identificador no existía
	 */
	public void adicionarZonaCirculacion( String idZona, int capacidadNormal, String idCentroComercial, DialogoAdicionarZonaCirculacion pDialogo)
	{
		try 
		{
			VOZonaCirculacion ascensor = aforoAndes.adicionarZonaCirculacion(idZona, capacidadNormal, idCentroComercial);

			if (ascensor == null)
			{
				throw new Exception ("No se pudo crear una zona de circulación con identificador: " + idZona);
			}
			String resultado = "En adicionar Zona circulación\n\n";
			resultado += "Zona circulacion adicionada exitosamente: " + ascensor;
			resultado += "\n\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
			pDialogo.dispose();

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las zonas de circulación existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarZonasCirculacion( )
	{
		try 
		{
			List <VOZonaCirculacion> lista = aforoAndes.darVOZonasCirculacion();

			String resultado = "En listar Zona circulación";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca la zona de circulación con el identificador indicado por el usuario y la muestra en el panel de datos
	 */
	public void buscarZonaCirculacionPorIdentificador( )
	{
		try 
		{
			String idZona = JOptionPane.showInputDialog (this, "Identificador de la zona de circulación: ", "Buscar zona de circulación por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idZona != null)
			{
				VOZonaCirculacion zonaCirculacion = aforoAndes.darZonaCirculacionPorId(idZona);
				String resultado = "En buscar Zona circulacion por identificador\n\n";
				if (zonaCirculacion != null)
				{
					resultado += "La zona de circulación es: " + zonaCirculacion;
				}
				else
				{
					resultado += "Una  zona de circulación con identificador: " + idZona + " NO EXISTE\n";    				
				}
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos la zona de circulación con el identificador dado por el usuario
	 * Cuando dicha zona de circulación no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarZonaCirculacionPorIdentificador( )
	{
		try 
		{
			String idParqueadero = JOptionPane.showInputDialog (this, "Identificador de la zona de circulación:", "Borrar zona de circulación por identificador", JOptionPane.QUESTION_MESSAGE);
			if (idParqueadero != null)
			{
				String idTipo = idParqueadero;
				long parqueaderosEliminados = aforoAndes.eliminarZonaCirculacionPorId (idTipo);

				String resultado = "En eliminar ZonaCirculacion\n\n";
				resultado += parqueaderosEliminados + " zonas de circulación eliminadas\n";
				resultado += "\n\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	/* ****************************************************************
	 * 			Requerimientos de consulta
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para consultar visitantes 
	 */
	public void consultarVisitantesPorEstablecimiento()
	{
		if ( administrador )
		{
			DialogoConsultarVisitantesPorEstablecimiento dialogo = new DialogoConsultarVisitantesPorEstablecimiento( this );
			dialogo.setVisible( true );
			panelDatos.actualizarInterfaz("En proceso de adición");
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}		

	}

	/** Consulta los visitantes atendidos por el establecimiento indicado por parámetro
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La hora de inicio del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de inicio del rango de consulta  o -1 si no aplica
	 * @param horaFin - La hora de fin del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de fin del rango de consulta  o -1 si no aplica
	 * @param idLocalComercial - El id del local comercial a consultar
	 */
	public void consultarVisitantesPorEstablecimiento(String idLocalComercial, Timestamp fechaInicio, Timestamp fechaFin, int horaInicial, int minutoInicial, int horaFinal, int minutoFinal, DialogoConsultarVisitantesPorEstablecimiento pDialogo)
	{
		try 
		{
			if (idLocalAdministrado != null && !idLocalAdministrado.equalsIgnoreCase(idLocalComercial))
			{
				throw new Exception ("No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores del centro comercial o del local.");
			}
				
			String resultado = "En listar Visitantes del establecimiento " + idLocalComercial + "\n";
			List<Visitante> visitantes;
			if ( horaInicial == -1)
			{
				visitantes = aforoAndes.RFC1Fecha(fechaInicio, fechaFin, idLocalComercial);
			}
			else
			{
				visitantes = aforoAndes.RFC1Hora(fechaInicio, horaInicial, minutoInicial, horaFinal, minutoFinal, idLocalComercial);
			}
			pDialogo.dispose();
			if ( visitantes != null )
			{
				resultado +=  "\n" + listarObjetos (visitantes);
			}
			else
			{
				resultado += "No existen visitantes atendidos por el establecimiento " + idLocalComercial;
			}
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}		
	}

	/**
	 * Crea una ventana de diálogo para consultar establecimientos populares 
	 */
	public void consultarEstablecimientosPopulares()
	{
		DialogoConsultarTop20Establecimientos dialogo = new DialogoConsultarTop20Establecimientos( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("En proceso de consulta");

	}

	/** Consulta el top de establecimientos populares (los que tienen el mismo número de visitas se incluyen)
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La hora de inicio del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de inicio del rango de consulta  o -1 si no aplica
	 * @param horaFin - La hora de fin del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de fin del rango de consulta  o -1 si no aplica
	 * @param idLocalComercial - El id del local comercial a consultar
	 */
	public void consultarEstablecimientosPopulares(Timestamp fechaInicio, Timestamp fechaFin, int horaInicial, int minutoInicial, int horaFinal, int minutoFinal, DialogoConsultarTop20Establecimientos pDialogo)
	{
		try 
		{
			String resultado = "En listar Top 20 establecimientos populares:\n ";
			List<LocalComercial> establecimientos;
			if ( horaInicial == -1)
			{
				establecimientos = aforoAndes.RFC2Fecha(fechaInicio, fechaFin);
			}
			else
			{
				establecimientos = aforoAndes.RFC2Hora(fechaInicio, horaInicial, minutoInicial, horaFinal, minutoFinal);
			}
			pDialogo.dispose();
			if ( establecimientos != null )
			{	
				resultado +=  "\n" + listarObjetos (establecimientos);
			}
			else
			{
				resultado += "No existen establecimientos.";
			}
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}		
	}

	/**
	 * Crea una ventana de diálogo para consultar índices de aforo 
	 */
	public void consultarIndicesAforoEstablecimiento()
	{
		if ( administrador )
		{
			DialogoConsultarIndiceAforoLocal dialogo = new DialogoConsultarIndiceAforoLocal( this );
			dialogo.setVisible( true );
			panelDatos.actualizarInterfaz("En proceso de adición");
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}		

	}

	/** Consulta el índice de aforo de un establecimiento
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La hora de inicio del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de inicio del rango de consulta  o -1 si no aplica
	 * @param horaFin - La hora de fin del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de fin del rango de consulta  o -1 si no aplica
	 * @param idLocalComercial - El id del local comercial a consultar
	 */
	public void consultarIndicesAforoEstablecimiento(String idLocalComercial, Timestamp fechaInicio, Timestamp fechaFin, int horaInicial, int minutoInicial, int horaFinal, int minutoFinal, DialogoConsultarIndiceAforoLocal pDialogo)
	{
		try 
		{
			if (idLocalAdministrado != null && !idLocalAdministrado.equalsIgnoreCase(idLocalComercial))
			{
				throw new Exception ("No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores del centro comercial o del local.");
			}
			String resultado = "En consultar índice de aforo del local " + idLocalComercial + "\n ";
			RFC3 indice;
			if ( horaInicial == -1)
			{
				indice = aforoAndes.RFC3FechaEstablecimiento(fechaInicio, fechaFin, idLocalComercial);
			}
			else
			{
				indice = aforoAndes.RFC3HoraEstablecimiento(fechaInicio, horaInicial, minutoInicial, horaFinal, minutoFinal, idLocalComercial);
			}
			pDialogo.dispose();

			if ( indice != null )
			{
				resultado += "El resultado es: " + indice; 
			}
			else
			{
				resultado += "No existe un local comercial con identificador " + idLocalComercial;
			}
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Crea una ventana de diálogo para consultar índices de aforo 
	 */
	public void consultarIndicesAforoCentroComercial()
	{
		if ( administrador )
		{
			DialogoConsultarIndiceAforoCC dialogo = new DialogoConsultarIndiceAforoCC( this );
			dialogo.setVisible( true );
			panelDatos.actualizarInterfaz("En proceso de adición");
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}		
	}

	/** Consulta el índice de aforo de un centro comercial
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La hora de inicio del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de inicio del rango de consulta  o -1 si no aplica
	 * @param horaFin - La hora de fin del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de fin del rango de consulta  o -1 si no aplica
	 * @param idCentroComercial - El id del centro comercial a consultar
	 */
	public void consultarIndicesAforoCentroComercial (String idCentroComercial, Timestamp fechaInicio, Timestamp fechaFin, int horaInicial, int minutoInicial, int horaFinal, int minutoFinal, DialogoConsultarIndiceAforoCC pDialogo)
	{
		try 
		{
			String resultado = "En consultar índice de aforo del centro comercial " + idCentroComercial + "\n ";
			RFC3 indice;
			if ( horaInicial == -1)
			{
				indice = aforoAndes.RFC3FechaCentroComercial(fechaInicio, fechaFin, idCentroComercial);
			}
			else
			{
				indice = aforoAndes.RFC3HoraCentroComercial(fechaInicio, horaInicial, minutoInicial, horaFinal, minutoFinal, idCentroComercial);
			}
			pDialogo.dispose();

			if ( indice != null )
			{
				resultado += "El resultado es: " + indice; 
			}
			else
			{
				resultado += "No existe un centro comercial con identificador " + idCentroComercial;
			}
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Crea una ventana de diálogo para consultar índices de aforo 
	 */
	public void consultarIndicesAforoTipoLocal()
	{
		if ( administrador )
		{
			DialogoConsultarIndiceAforoTipo dialogo = new DialogoConsultarIndiceAforoTipo( this );
			dialogo.setVisible( true );
			panelDatos.actualizarInterfaz("En proceso de adición");
		}
		else
		{
			String resultado = "No cuenta con los privilegios para realizar esta acción. Solo permitida para administradores";
			panelDatos.actualizarInterfaz(resultado);
		}		
	}

	/** Consulta el índice de aforo de un tipo de local
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La hora de inicio del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de inicio del rango de consulta  o -1 si no aplica
	 * @param horaFin - La hora de fin del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de fin del rango de consulta  o -1 si no aplica
	 * @param tipoLocal - El tipo del local a consultar
	 */
	public void consultarIndicesAforoTipoLocal (String tipoLocal, Timestamp fechaInicio, Timestamp fechaFin, int horaInicial, int minutoInicial, int horaFinal, int minutoFinal, DialogoConsultarIndiceAforoTipo pDialogo)
	{
		try 
		{
			String resultado = "En consultar índice de aforo del tipo de local " + tipoLocal + "\n ";
			RFC3 indice;
			if ( horaInicial == -1)
			{
				indice = aforoAndes.RFC3FechaTipoLocal(fechaInicio, fechaFin, tipoLocal);
			}
			else
			{
				indice = aforoAndes.RFC3HoraTipoLocal(fechaInicio, horaInicial, minutoInicial, horaFinal, minutoFinal, tipoLocal);
			}
			pDialogo.dispose();

			if ( indice != null )
			{
				resultado += "El resultado es: " + indice; 
			}
			else
			{
				resultado += "No existe un tipo de local con nombre " + tipoLocal;
			}
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}


	}

	/* ****************************************************************
	 * 			BONO: Requerimientos de consulta
	 *****************************************************************/
	/**
	 * Crea una ventana de diálogo para consultar establecimientos con aforo disponible
	 */
	public void consultarEstablecimientosAforoDisponible ()
	{
		DialogoConsultarAforoDisponible dialogo = new DialogoConsultarAforoDisponible( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("En proceso de consulta");

	}

	/** Consultar los establecimientos con aforo disponible
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta
	 * @param horaInicio - La hora de inicio del rango de consulta
	 * @param minutoFin - El minuto de inicio del rango de consulta  o -1 si no aplica
	 * @param horaFin - La hora de fin del rango de consulta o -1 si no aplica
	 * @param minutoFin - El minuto de fin del rango de consulta  o -1 si no aplica
	 * @param idCentroComercial - El id del centro comercial a consultar
	 */
	public void consultarEstablecimientosAforoDisponible (Timestamp fechaInicio, Timestamp fechaFin, int horaInicial, int minutoInicial, int horaFinal, int minutoFinal, DialogoConsultarAforoDisponible pDialogo)
	{
		try 
		{
			String resultado = "En consultar establecimientos con aforo disponible ";
			List<RFC4> resultados;
			if ( horaFinal == -1)
			{
				resultados = aforoAndes.RFC4FechaHora(fechaInicio, fechaFin, horaInicial, minutoInicial);
			}
			else
			{
				resultados = aforoAndes.RFC4FechaRangoHoras(fechaInicio, horaInicial, minutoInicial, horaFinal, minutoFinal);
			}
			pDialogo.dispose();
			if ( resultados != null )
			{
				resultado +=  "\n" + listarObjetos (resultados);
			}
			else
			{
				resultado += "No existen establecimientos con aforo disponible. ";
			}
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}		

	}



	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/

	/**
	 * Registra al jugador con los datos dados.
	 * @param identificacion - Identificacion del administrador a registrar. identificacion != null && identificacion != "".
	 * @param nombre - Nombre del administrador a registrar. nombre != null && nombre != "".
	 * @param contrasenia - Contraseña del administrador a registrar. contrasenia != null && contrasenia != "".
	 * @param rol - Rol del usuario
	 * @param idLocal - Identificador del local que administra el administrador
	 */
	public void registrar( String identificacion, String nombre, String contrasenia, String rol, String idLocal )
	{
		try
		{
			String resultado = "Registro de administrador ";
			if ( rol.equals("Administrador"))
			{
				aforoAndes.adicionarAdministrador(identificacion, nombre, contrasenia);
			}
			else
			{
				aforoAndes.adicionarAdministradorLocal(identificacion, nombre, contrasenia, idLocal);
			}
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		}
		catch( Exception e )
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra el diálogo para el inicio de sesión.
	 * @param pTipo Tipo del diálogo a crear. pTipo pertenece {DialogoInicioSecion.REGISTRO, DialogoInicioSecion.InicioSesion}
	 */
	public void mostrarDialogoRegistro( )
	{
		DialogoInicioSesion dialogo = new DialogoInicioSesion( this, "Registrar" );
		dialogo.setVisible( true );
	}
	/**
	 * Muestra el diálogo para el inicio de sesión.
	 * @param pTipo Tipo del diálogo a crear. pTipo pertenece {DialogoInicioSecion.REGISTRO, DialogoInicioSecion.InicioSesion}
	 */
	public void mostrarDialogoInicioSesion(  )
	{
		DialogoInicioSesion dialogo = new DialogoInicioSesion( this, "Iniciar sesión" );
		dialogo.setVisible( true );
	}
	/**
	 * Inicia sesión del administrador dado por parámetro.
	 * @param rol - Rol del usuario     
	 * @param identificacion - Login del administrador a iniciar sesión. identificacion != null && identificacion != "".
	 */
	public void iniciarSesion( String identificacion, String rol )
	{
		try
		{
			String resultado = "Registro de administrador \n";
			Object admin = null;
			if ( rol.equals("Administrador"))
			{
				admin = (Administrador) (aforoAndes.darAdministradorPorId(identificacion));
			}
			else
			{
				admin = (AdministradorLocal) (aforoAndes.darAdministradorLocalPorId(identificacion));
				idLocalAdministrado = ((AdministradorLocal) admin).getIdLocal();
			}

			if (admin != null)
			{
				administrador = true;
				if ( rol.equals("Administrador local"))
				{
					resultado += "El inicio de sesión fue exitoso: " + (AdministradorLocal) admin;
				}
				else
				{
					resultado += "El inicio de sesión fue exitoso: " + (Administrador) admin;
				}
			}
			else
			{
				resultado += "El usuario no existe en la base de datos: ";
			}
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n\n Operación terminada";
		}
		catch( Exception e )
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra el log de AforoAndes
	 */
	public void mostrarLogAforoAndes ()
	{
		mostrarArchivo ("aforoAndes.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de aforoAndes
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogAforoAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("aforoAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de aforoAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de AforoAndes
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			long eliminados [] = aforoAndes.limpiarAforoAndes();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " RegistranVehiculo eliminados\n";
			resultado += eliminados [1] + " RegistranCarnet eliminados\n";
			resultado += eliminados [2] + " Vehículo eliminados\n";
			resultado += eliminados [3] + " Empleado eliminados\n";
			resultado += eliminados [4] + " Domiciliario eliminados\n";
			resultado += eliminados [5] + " Carnet eliminados\n";
			resultado += eliminados [6] + " TipoCarnet eliminados\n";
			resultado += eliminados [7] + " Lector eliminados\n";
			resultado += eliminados [8] + " TipoLector eliminados\n";
			resultado += eliminados [9] + " ZonaCirculacion eliminados\n";
			resultado += eliminados [10] + " LocalComercial eliminados\n";
			resultado += eliminados [11] + " TipoLocal eliminados\n";
			resultado += eliminados [12] + " Parqueadero eliminados\n";
			resultado += eliminados [13] + " Bano eliminados\n";
			resultado += eliminados [14] + " Ascensor eliminados\n";
			resultado += eliminados [15] + " CapacidadNormal eliminados\n";
			resultado += eliminados [16] + " Area eliminados\n";
			resultado += eliminados [17] + " CentroComercial eliminados\n";
			resultado += eliminados [18] + " Visitante eliminados\n";
			resultado += eliminados [19] + " TipoVisitante eliminados\n";
			resultado += eliminados [20] + " Horario eliminados\n";

			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de AforoAndes
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual AforoAndes.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de AforoAndes
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD AforoAndes.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaAforoCCAndes.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para AforoAndes
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: AforoAndes\n";
		resultado += " * @author kvsalazar, mcteran\n";
		resultado += " * Octubre de 2020\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}


	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	/**
	 * Genera una cadena de caracteres con la lista de los objetos recibida: una línea por cada objeto
	 * @param <E>
	 * @param lista - La lista con los objetos
	 * @return La cadena con una líea para cada objeto recibido
	 */
	private <E> String listarObjetos(List<E> lista) 
	{
		String resp = "Los objetos existentes son:\n";
		int i = 1;
		for (E objeto : lista)
		{
			System.out.println(objeto);
			resp += i++ + ". " + objeto.toString() + "\n";
		}
		return resp;
	}


	/**
	 * Consulta en la base de datos los centros comerciales existentes y los muestra en el panel de datos de la aplicación
	 */
	public List <VOCentroComercial> listarVOCentrosComerciales( )
	{
		return aforoAndes.darVOCentrosComerciales();
	}


	/**
	 * Consulta en la base de datos los tipos de carnet existentes y los muestra en el panel de datos de la aplicación
	 */
	public List <VOTipoCarnet> listarVOTiposCarnet( )
	{
		return aforoAndes.darVOTiposCarnet();
	}

	/**
	 * Consulta en la base de datos los tipos de lector existentes y los muestra en el panel de datos de la aplicación
	 */
	public List <VOTipoLector> listarVOTiposLector( )
	{
		return aforoAndes.darVOTiposLector();
	}

	/**
	 * Consulta en la base de datos los tipos de local existentes y los muestra en el panel de datos de la aplicación
	 */
	public List <VOTipoLocal> listarVOTiposLocal( )
	{
		return aforoAndes.darVOTiposLocal();
	}

	/**
	 * Consulta en la base de datos los tipos de visitante existentes y los muestra en el panel de datos de la aplicación
	 */
	public List <VOTipoVisitante> listarVOTiposVisitante( )
	{
		return aforoAndes.darVOTiposVisitante();
	}

	/**
	 * Consulta en la base de datos los locales existentes y los muestra en el panel de datos de la aplicación
	 */
	public List <VOLocalComercial> listarVOLocalComercial( )
	{
		return aforoAndes.darVOLocalesComerciales();
	}


	/**
	 * Busca el área con el valor dado por parámetro y lo retorna
	 */
	public VOArea buscarAreaPorValor( double valor)
	{
		return aforoAndes.darAreaPorValor(valor);
	}

	/**
	 * Busca el área con el valor dado por parámetro y lo retorna
	 */
	public VOCapacidadNormal buscarCapacidadNormalPorValor( int valor)
	{
		return aforoAndes.darCapacidadNormalPorValor(valor);
	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y aforoAndes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazAforoAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazAforoAndesApp interfaz = new InterfazAforoAndesApp( );
			//BasicConfigurator.configure();
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
