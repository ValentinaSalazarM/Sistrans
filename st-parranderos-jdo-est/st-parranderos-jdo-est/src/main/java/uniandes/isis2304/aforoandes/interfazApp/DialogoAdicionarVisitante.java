/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiDeportes
 * Autor: Equipo Cupi2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.isis2304.aforoandes.negocio.VOTipoVisitante;


/**
 * Dialogo para agregar un deportista.
 */
@SuppressWarnings("serial")
public class DialogoAdicionarVisitante extends JDialog implements ActionListener
{
	// -----------------------------------------------
	// Constantes
	// -----------------------------------------------

	/**
	 * Constante agregar deportista.
	 */
	private final static String AGREGAR = "Agregar visitante";

	// -----------------------------------------------
	// Atributos
	// -----------------------------------------------

	/**
	 * Etiqueta identificación.
	 */
	private JLabel lblIdentificacion;

	/**
	 * Etiqueta nombre.
	 */
	private JLabel lblNombre;

	/**
	 * Etiqueta tipo.
	 */
	private JLabel lblTipo;

	/**
	 * Etiqueta corre.
	 */
	private JLabel lblCorreo;

	/**
	 * Etiqueta teléfono propio.
	 */
	private JLabel lblTelefonoPropio;

	/**
	 * Etiqueta nombre de emergencia.
	 */
	private JLabel lblNombreEmergencia;

	/**
	 * Etiqueta teléfono de emergencia.
	 */
	private JLabel lblTelefonoEmergencia;

	/**
	 * Campo de texto identificación.
	 */
	private JTextField txtIdentificacion;

	/**
	 * Campo de texto nombre.
	 */
	private JTextField txtNombre;

	/**
	 * Listado de los tipos de visitante disponibles
	 */
	private JComboBox<String> cbTipoVisitante;

	/**
	 * Campo de texto correo.
	 */
	private JTextField txtCorreo;

	/**
	 * Campo de texto teléfono propio.
	 */
	private JTextField txtTelefonoPropio;

	/**
	 * Campo de texto nombre del contacto de emergencia.
	 */
	private JTextField txtNombreEmergencia;

	/**
	 * Campo de texto telefono del contacto de emergencia.
	 */
	private JTextField txtTelefonoEmergencia;

	/**
	 * Panel con la información del deportista.
	 */
	private JPanel panelInfo;

	/**
	 * Botón para agregar visitante.
	 */
	private JButton btnAgregar;

	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazAforoAndesApp principal;

	// -----------------------------------------------
	// Métodos
	// -----------------------------------------------

	/**
	 * Crea el dialogo para agregar un deportista.
	 * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
	 */
	public DialogoAdicionarVisitante( InterfazAforoAndesApp pPrincipal )
	{
		super( pPrincipal, true );

		principal = pPrincipal;
		setTitle( "Agregar visitante" );
		setLayout( new java.awt.BorderLayout( ) );

		panelInfo = new JPanel( );
		panelInfo.setPreferredSize( new Dimension( 650, 300 ) );
		panelInfo.setBorder( new EmptyBorder( 20, 20, 20, 20 ) );

		panelInfo.setBorder( new TitledBorder( "Información" ) );
		GridLayout layout = new GridLayout( 7, 2, -175, 30 );
		layout.setVgap( 10 );
		panelInfo.setLayout( layout );

		lblIdentificacion = new JLabel( "Identificación: " );
		panelInfo.add( lblIdentificacion );
		txtIdentificacion = new JTextField( );
		panelInfo.add( txtIdentificacion );

		lblNombre = new JLabel( "Nombre: " );
		panelInfo.add( lblNombre );
		txtNombre = new JTextField( );
		panelInfo.add( txtNombre );

		lblTipo = new JLabel( "Tipo de visitante: " );
		cbTipoVisitante = new JComboBox<>();
		for ( VOTipoVisitante tv: pPrincipal.listarVOTiposVisitante())
		{
			cbTipoVisitante.addItem(tv.getId() + " - " + tv.getTipo());
		}
		panelInfo.add( lblTipo );
		panelInfo.add( cbTipoVisitante );

		lblCorreo = new JLabel( "Correo: " );
		panelInfo.add( lblCorreo );
		txtCorreo = new JTextField( );
		panelInfo.add( txtCorreo );

		lblTelefonoPropio = new JLabel( "Teléfono propio: " );
		panelInfo.add( lblTelefonoPropio );
		txtTelefonoPropio = new JTextField( );
		panelInfo.add( txtTelefonoPropio );

		lblNombreEmergencia = new JLabel( "Nombre del contacto de emergencia: " );
		panelInfo.add( lblNombreEmergencia );
		txtNombreEmergencia = new JTextField( );
		panelInfo.add( txtNombreEmergencia );

		lblTelefonoEmergencia = new JLabel( "Teléfono del contacto de emergencia: " );
		panelInfo.add( lblTelefonoEmergencia );
		txtTelefonoEmergencia = new JTextField( );
		panelInfo.add( txtTelefonoEmergencia );

		BorderLayout border = new BorderLayout( );
		border.setHgap( 5 );
		border.setVgap( 5 );

		add( panelInfo, BorderLayout.NORTH );

		btnAgregar = new JButton( AGREGAR );
		btnAgregar.setActionCommand( AGREGAR );
		btnAgregar.addActionListener( this );
		add( btnAgregar, BorderLayout.SOUTH );

		pack( );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	}

	/**
	 * Método que recoge las acciones sobre los objetos que está escuchando.
	 * @param e Evento que se realizó.
	 */
	public void actionPerformed( ActionEvent e )
	{
		if( e.getActionCommand( ).equals( AGREGAR ) )
		{
			String identificacion = txtIdentificacion.getText( );
			String comboBox = (String) cbTipoVisitante.getSelectedItem();
			long tipo = Long.valueOf(comboBox.split(" - ")[0]);
			String nombre = txtNombre.getText( );
			String correo = txtCorreo.getText( );
			String telefonoPropio = txtTelefonoPropio.getText( );
			String nombreEmergencia = txtNombreEmergencia.getText( );
			String telefonoEmergencia = txtTelefonoEmergencia.getText( );
			if( identificacion.equals( "" ) || nombre.equals( "" ) || correo.equals( "" ) || telefonoPropio.equals( "" ) || telefonoEmergencia.equals( "" ) 
					|| nombreEmergencia.equals( "" ) )
			{
				JOptionPane.showMessageDialog( this, "Datos incompletos", "Agregar Visitante", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				if( telefonoPropio.length() < 7 || telefonoEmergencia.length() < 7)
				{
					JOptionPane.showMessageDialog( this, "Los teléfonos deben tener más de 6 dígitos.", "Agregar Visitante", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					principal.adicionarVisitante(identificacion, nombre, tipo, correo, telefonoPropio, nombreEmergencia, telefonoEmergencia);
					dispose( );
				}
			}
		}
	}
}
