/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
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
import uniandes.isis2304.aforoandes.negocio.AforoAndes;
import uniandes.isis2304.aforoandes.negocio.Area;
import uniandes.isis2304.aforoandes.negocio.Bano;
import uniandes.isis2304.aforoandes.negocio.CapacidadNormal;
import uniandes.isis2304.aforoandes.negocio.CentroComercial;
import uniandes.isis2304.aforoandes.negocio.VOArea;
import uniandes.isis2304.aforoandes.negocio.VOAscensor;
import uniandes.isis2304.aforoandes.negocio.VOBano;
import uniandes.isis2304.aforoandes.negocio.VOCapacidadNormal;
import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;
import uniandes.isis2304.aforoandes.negocio.VOTipoVisitante;
import uniandes.isis2304.aforoandes.negocio.VOVisitante;
import uniandes.isis2304.aforoandes.negocio.Visitante;


/**
 * Clase principal de la interfaz
 * @author Germán Bravo
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
	 * Se crea una nueva tupla de Area en la base de datos, si un área con ese nombre no existía
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
				resultado += "\n Operación terminada";
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
			resultado += "\n Operación terminada";
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
			String id = JOptionPane.showInputDialog (this, "Id del área: ", "Borrar área por Id", JOptionPane.QUESTION_MESSAGE);
			if (id != null)
			{
				long idArea = Long.valueOf (id);
				long areasEliminadas = aforoAndes.eliminarAreaPorId (idArea);

				String resultado = "En eliminar Área\n\n";
				resultado += areasEliminadas + " Áreas eliminadas\n";
				resultado += "\n Operación terminada";
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
				resultado += "\n Operación terminada";
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
	 * 			CRUD de CAPACIDAD NORMAL
	 *****************************************************************/

	/**
	 * Adiciona un área con la información dada por el usuario
	 * Se crea una nueva tupla de Capacidad Normal en la base de datos, si un área con ese nombre no existía
	 */
	public void adicionarCapacidadNormal()
	{
		try 
		{
			String valorStr = JOptionPane.showInputDialog (this, "Valor: ", "Adicionar capacidad", JOptionPane.QUESTION_MESSAGE);
			String aforoStr = JOptionPane.showInputDialog (this, "Aforo: ", "Adicionar capacidad", JOptionPane.QUESTION_MESSAGE);

			double valor = Double.parseDouble(valorStr);
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
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog( this, "El valor (double) y el aforo (int) deben ser números.", "Agregar Capacidad", JOptionPane.ERROR_MESSAGE );
		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos las capacidades existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarCapacidades( )
	{
		try 
		{
			List <VOCapacidadNormal> lista = aforoAndes.darVOCapacidadesNormales();

			String resultado = "En listar Capacidad Normal";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
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
			String id = JOptionPane.showInputDialog (this, "Id de la capacidad: ", "Borrar capacidad por Id", JOptionPane.QUESTION_MESSAGE);
			if (id != null)
			{
				long idCapacidad = Long.valueOf (id);
				long eliminadas = aforoAndes.eliminarCapacidadNormalPorId(idCapacidad);

				String resultado = "En eliminar Capacidad\n\n";
				resultado += eliminadas  + "Capacidades eliminadas\n";
				resultado += "\n Operación terminada";
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
	 * Borra de la base de datos la capacidad  con el identificador dado por el usuario
	 * Cuando dicha capacidad no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarCapacidadPorValor( )
	{
		try 
		{
			String valor = JOptionPane.showInputDialog (this, "Valor de la capacidad: ", "Borrar capacidad por valor", JOptionPane.QUESTION_MESSAGE);
			if (valor != null)
			{
				double valorS = Double.valueOf (valor);
				long eliminadas = aforoAndes.eliminarCapacidadNormalPorValor(valorS);

				String resultado = "En eliminar Capacidad\n\n";
				resultado += eliminadas  + "Capacidades eliminadas\n";
				resultado += "\n Operación terminada";
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
	 * Busca la capacidad normal por el ID.
	 */
	public void buscarCapacidadPorID( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificacion de la capacidad: ", "Buscar capacidad por identificacion", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null)
			{
				long id = Long.valueOf (identificacion);
				VOCapacidadNormal capacity = aforoAndes.darCapacidadNormalPorId(id);
				String resultado = "En buscar Capacidad por id\n\n";
				if (capacity != null)
				{
					resultado += "La capacidad es: " + capacity;
				}
				else
				{
					resultado += "La capacidad con identificación: " + identificacion + " NO EXISTE\n";    				
				}
				resultado += "\n Operación terminada";
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
	 * Busca la capacidad normal por el valor.
	 */
	public void buscaLasCapacidadesNormalesPorValor( )
	{
		try 
		{
			String valor = JOptionPane.showInputDialog (this, "Valor de la capacidad: ", "Buscar capacidades por valor", JOptionPane.QUESTION_MESSAGE);
			if (valor != null)
			{
				double value = Double.valueOf (valor);
				List <CapacidadNormal> lista = aforoAndes.darCapacidadesNormalesPorValor(value);
				String resultado = "En listar capacidad normal";
				resultado +=  "\n" + listarObjetos (lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n Operación terminada";
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
	 * Cambiar el el valor de una capacidad normal.
	 */
	public void cambiarValorCapacidad( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificador de la capacidad: ", "Actualizar valor", JOptionPane.QUESTION_MESSAGE);
			String valor = JOptionPane.showInputDialog (this, "Nuevo valor: ", "Actualizar valor de la capacidad normal", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null && valor != null)
			{	
				long id = Long.valueOf (identificacion);
				double valorS = Double.valueOf (valor);
			
				long modificados = aforoAndes.cambiarValorCapacidad(id, valorS);
				String resultado = "En actualizar Capacidad Normal: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n Operación terminada";
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
	 * Cambiar el el valor de una capacidad normal.
	 */
	public void cambiarAforoCapacidad( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificador de la capacidad: ", "Actualizar aforo", JOptionPane.QUESTION_MESSAGE);
			String aforo = JOptionPane.showInputDialog (this, "Nuevo aforo: ", "Actualizar aforo de la capacidad normal", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null && aforo != null)
			{	
				long id = Long.valueOf (identificacion);
				int aforoS = Integer.valueOf (aforo);
			
				long modificados = aforoAndes.cambiarAforoCapacidad(id, aforoS);
				String resultado = "En actualizar Capacidad Normal: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n Operación terminada";
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
	public void agregarBano( )
	{
		DialogoAdicionarBano dialogo = new DialogoAdicionarBano( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("Operación finalizada");
	}
	
	/**
	 * Adiciona un abaño con la información dada por el usuario
	 * Se crea una nueva tupla de Bano en la base de datos, si un baño con ese nombre no existía
	 */
	public void adicionarBano( String idBano,int capacidadNormal, long area, int numSanitarios, String idCentroComercial, DialogoAdicionarBano pDialogo)
	{
		try 
		{
			long idArea = buscarAreaPorValor(area).getId();
			VOBano tb = aforoAndes.adicionarBaño(idBano, capacidadNormal, area, numSanitarios, idCentroComercial);
			if (tb == null)
			{
				throw new Exception ("No se pudo crear un baño con nombre: " + idBano);
			}
			String resultado = "En adicionarBano\n\n";
			resultado += "Baño adicionado exitosamente: " + tb;
			resultado += "\n Operación terminada";
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
			List <Bano> lista = aforoAndes.darBaños();

			String resultado = "En listarBaños";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Busca  el baño por id.
	 */
	public void buscarBanoPorID( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identifidor del baño: ", "Buscar baño por id", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null)
			{
				VOBano baño= aforoAndes.darBañoPorId(identificacion);
				String resultado = "En buscar Baño por id\n\n";
				if (baño != null)
				{
					resultado += "El baño es: " + baño;
				}
				else
				{
					resultado += "El baño con identificación: " + identificacion + " NO EXISTE\n";    				
				}
				resultado += "\n Operación terminada";
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
	public void buscarLosBanosPorSanitarios( )
	{
		try 
		{
			String sanitarios = JOptionPane.showInputDialog (this, "Número de sanitarios: ", "Buscar por número de sanitarios", JOptionPane.QUESTION_MESSAGE);
			if (sanitarios != null)
			{
				int value = Integer.valueOf (sanitarios);
				List <Bano> lista = aforoAndes.darBañosPorSanitarios(value);
				String resultado = "En listar baños";
				resultado +=  "\n" + listarObjetos (lista);
				panelDatos.actualizarInterfaz(resultado);
				resultado += "\n Operación terminada";
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
	 * Borra de la base de datos el baño con el identificador dado por el usuario
	 * Cuando dicho baño no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarBañoPorId( )
	{
		try 
		{
			String idBano = JOptionPane.showInputDialog (this, "Id baño?", "Borrar baño por ID", JOptionPane.QUESTION_MESSAGE);
			if (idBano != null)
			{
				String bano = idBano;
				long tbEliminados = aforoAndes.eliminarBañoPorId(bano);

				String resultado = "En eliminar Baño\n\n";
				resultado += tbEliminados + " Baños eliminados\n";
				resultado += "\n Operación terminada";
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
	public void eliminarBañoPorSanitarios( )
	{
		try 
		{
			String sanitarios = JOptionPane.showInputDialog (this, "¿Número de sanitarios del baño a eliminar?", "Borrar baño por sanitarios", JOptionPane.QUESTION_MESSAGE);
			if (sanitarios != null)
			{
                int value = Integer.valueOf (sanitarios);;
				long tbEliminados = aforoAndes.eliminarBañoPorSanitarios(value);

				String resultado = "En eliminar Baño\n\n";
				resultado += tbEliminados + " Baños eliminados\n";
				resultado += "\n Operación terminada";
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
	 * 			CRUD de Ascensor
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un ascensor
	 */
	public void agregarAscensor( )
	{
		DialogoAdicionarAscensor dialogo = new DialogoAdicionarAscensor( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("Operación finalizada");
	}

	/**
	 * Adiciona un ascensor con la información dada por el usuario
	 * Se crea una nueva tupla de Ascensor en la base de datos, si un ascensor con ese nombre no existía
	 */
	public void adicionarAscensor( String idAscensor, int capacidadNormal, long area, double pesoMaximo, String idCentroComercial, DialogoAdicionarAscensor pDialogo)
	{
		try 
		{
			long idArea = buscarAreaPorValor(area).getId();
			VOAscensor tb = aforoAndes.adicionarAscensor(idAscensor, capacidadNormal, area, pesoMaximo, idCentroComercial);
			if (tb == null)
			{
				throw new Exception ("No se pudo crear un ascensor con nombre: " + idAscensor);
			}
			String resultado = "En adicionarAscensor\n\n";
			resultado += "Tipo de bebida adicionado exitosamente: " + tb;
			resultado += "\n Operación terminada";
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
	 * Consulta en la base de datos los ascensores existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarAscensor( )
	{
		try 
		{
			List <VOAscensor> lista = aforoAndes.darVOAscensores();

			String resultado = "En listarAscensor";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
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
	public void eliminarAscensorPorId( )
	{
		try 
		{
			String idTipoStr = JOptionPane.showInputDialog (this, "Id del tipo de bedida?", "Borrar ascensor por Id", JOptionPane.QUESTION_MESSAGE);
			if (idTipoStr != null)
			{
				String idTipo = idTipoStr;
				long tbEliminados = aforoAndes.eliminarAscensorPorId (idTipo);

				String resultado = "En eliminar Ascensor\n\n";
				resultado += tbEliminados + " Tipos de bebida eliminados\n";
				resultado += "\n Operación terminada";
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

//	/**
//	 * Busca el ascensor con el nombre indicado por el usuario y lo muestra en el panel de datos
//	 */
//	public void buscarAscensorPorNombre( )
//	{
//		try 
//		{
//			String nombreTb = JOptionPane.showInputDialog (this, "Nombre del tipo de bedida?", "Buscar ascensor por nombre", JOptionPane.QUESTION_MESSAGE);
//			if (nombreTb != null)
//			{
//				VOAscensor Ascensor = aforoAndes.darAscensorPorNombre (nombreTb);
//				String resultado = "En buscar Tipo Bebida por nombre\n\n";
//				if (Ascensor != null)
//				{
//					resultado += "El ascensor es: " + Ascensor;
//				}
//				else
//				{
//					resultado += "Un ascensor con nombre: " + nombreTb + " NO EXISTE\n";    				
//				}
//				resultado += "\n Operación terminada";
//				panelDatos.actualizarInterfaz(resultado);
//			}
//			else
//			{
//				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
//			}
//		} 
//		catch (Exception e) 
//		{
//			//			e.printStackTrace();
//			String resultado = generarMensajeError(e);
//			panelDatos.actualizarInterfaz(resultado);
//		}
//	}





	/* ****************************************************************
	 * 			CRUD de TiposVisitante
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un tipo de visitante
	 */
	public void agregarTipoVisitante( )
	{
		DialogoAdicionarTipoVisitante dialogo = new DialogoAdicionarTipoVisitante( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("Operación finalizada");
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
	public void agregarTipoVisitante( String tipo, int horaInicio, int minutoInicio, int horaLimite, int minutoLimite, DialogoAdicionarTipoVisitante pDialogo )
	{
		try
		{
			VOTipoVisitante tv = aforoAndes.adicionarTipoVisitante(tipo, horaInicio, minutoInicio, horaLimite, minutoLimite);
			if (tv == null)
			{
				throw new Exception ("No se pudo crear un tipo de visitante con tipo: " + tipo);
			}
			String resultado = "En adicionar TipoVisitante\n\n";
			resultado += "Tipo de visitante adicionado exitosamente: " + tv;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los tipos de visitante existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarTipoVisitante( )
	{
		try 
		{
			List <VOTipoVisitante> lista = aforoAndes.darVOTiposVisitante();

			String resultado = "En listar TipoVisitante";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
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
				resultado += "\n Operación terminada";
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
	 * Busca el tipo de visitante con el nombre indicado por el usuario y lo muestra en el panel de datos
	 */
	public void buscarTipoVisitantePorNombre( )
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
				resultado += "\n Operación terminada";
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
	 * 			CRUD de Visitante
	 *****************************************************************/

	/**
	 * Crea una ventana de diálogo para agregar un visitante.
	 */
	public void adicionarVisitante( )
	{
		DialogoAdicionarVisitante dialogo = new DialogoAdicionarVisitante( this );
		dialogo.setVisible( true );
		panelDatos.actualizarInterfaz("Operación finalizada");
	}

	/**
	 * Adiciona visitante con la información dada por el usuario
	 * Se crea una nueva tupla de TipoVisitante en la base de datos, si un tipoVisitante con ese nombre no existía
	 * @param identificacion - La identificación de cada visitante del centro comercial
	 * @param nombre - Nombre del visitante
	 * @param tipo - Tipo de visitante
	 * @param correo - Correo del visitante
	 * @param telefonoPropio - Telefono del visitante
	 * @param nombreEmergencia - Contacto de emergencia del visitante
	 * @param telefonoEmergencia - Telefono del contacto de emergencia del visitante
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
			String resultado = "En adicionar Visitante\n\n";
			resultado += "Visitante adicionado exitosamente: " + visitante;
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);

		} 
		catch (Exception e) 
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Consulta en la base de datos los visitantes existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarVisitante( )
	{
		try 
		{
			List <VOVisitante> lista = aforoAndes.darVOVisitantes();

			String resultado = "En listar Visitante";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
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
	public void eliminarVisitantePorId( )
	{
		try 
		{
			String identificacion = JOptionPane.showInputDialog (this, "Identificación del visitante: ", "Borrar visitante por identificación", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null)
			{
				long tbEliminados = aforoAndes.eliminarVisitantePorId(identificacion);

				String resultado = "En eliminar Visitante\n\n";
				resultado += tbEliminados + " Visitantes eliminados\n";
				resultado += "\n Operación terminada";
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
				resultado += "\n Operación terminada";
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
				resultado += "\n Operación terminada";
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
			String nombreEmergencia = JOptionPane.showInputDialog (this, "Nuevo nombre del contacto de emergencia del visitante: ", "Actualizar teléfono de emergencia del visitante", JOptionPane.QUESTION_MESSAGE);
			if (identificacion != null && nombreEmergencia != null)
			{	
				long modificados = aforoAndes.cambiarTelefonoEmergencia(identificacion, nombreEmergencia);
				String resultado = "En actualizar Visitante por contacto de emergencia: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n Operación terminada";
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
	 * 			CRUD de CentroComercial
	 *****************************************************************/

	/**
	 * Adiciona un centro comercial con la información dada por el usuario
	 * Se crea una nueva tupla de TipoVisitante en la base de datos, si un tipoVisitante con ese nombre no existía
	 */
	public void adicionarCentroComercial()
	{
		try
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
				resultado += "\n Operación terminada";
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
	 * Consulta en la base de datos los centros comerciales existentes y los muestra en el panel de datos de la aplicación
	 */
	public void listarCentroComercial( )
	{
		try 
		{
			List <VOCentroComercial> lista = aforoAndes.darVOCentrosComerciales();

			String resultado = "En listar CentroComercial";
			resultado +=  "\n" + listarObjetos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
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
	public void eliminarCentroComercialPorId( )
	{
		try 
		{
			String idCentroComercial = JOptionPane.showInputDialog (this, "Identificación del centro comercial: ", "Borrar centro comercial por identificación", JOptionPane.QUESTION_MESSAGE);
			if (idCentroComercial != null)
			{
				long tbEliminados = aforoAndes.eliminarCentroComercialPorId(idCentroComercial);

				String resultado = "En eliminar CentroComercial\n\n";
				resultado += tbEliminados + " Centros comerciales eliminados\n";
				resultado += "\n Operación terminada";
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
	public void buscarCentroComercialPorIdentificacion( )
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
				resultado += "\n Operación terminada";
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
				String resultado = "En buscar CentroComercial por nombre: \n\n";
				resultado +=  "\n" + listarObjetos (lista);
				resultado += "\n Operación terminada";
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
				String resultado = "En actualizar CentroComercial por nombre: \n\n";
				resultado += modificados + " registros actualizados";
				resultado += "\n Operación terminada";
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
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de AforoAndes
	 */
	public void mostrarLogParranderos ()
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
	public void limpiarLogParranderos ()
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
			resp += i++ + ". " + objeto.toString() + "\n";
		}
		return resp;
	}

	/**
	 * Consulta en la base de datos los tipos de visitante existentes y los muestra en el panel de datos de la aplicación
	 */
	public List <VOTipoVisitante> listarTiposVisitante( )
	{
		return aforoAndes.darVOTiposVisitante();
	}

	/**
	 * Consulta en la base de datos los centros comerciales existentes y los muestra en el panel de datos de la aplicación
	 */
	public List <VOCentroComercial> listarCentrosComerciales( )
	{
		return aforoAndes.darVOCentrosComerciales();
	}


	/**
	 * Busca el área con el valor dado por parámetro y lo retorna
	 */
	public Area buscarAreaPorValor( double valor)
	{
		return aforoAndes.darAreaPorValor(valor);
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
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
