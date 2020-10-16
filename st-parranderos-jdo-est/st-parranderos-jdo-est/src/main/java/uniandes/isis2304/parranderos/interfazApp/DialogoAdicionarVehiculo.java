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

package uniandes.isis2304.parranderos.interfazApp;

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
import javax.swing.border.TitledBorder;

import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;
import uniandes.isis2304.aforoandes.negocio.VOTipoVisitante;


/**
 * Dialogo para agregar un vehiculo.
 */
@SuppressWarnings("serial")
public class DialogoAdicionarVehiculo extends JDialog implements ActionListener
{
	// -----------------------------------------------
	// Constantes
	// -----------------------------------------------

	/**
	 * Comando para agregar la banda.
	 */
	public final static String AGREGAR = "Agregar Vehículo";

	/**
	 * Comando para cancelar el proceso.
	 */
	public final static String CANCELAR = "Cancelar";


	// -----------------------------------------------
	// Atributos
	// -----------------------------------------------

	/**
	 * Etiqueta placa.
	 */
	private JLabel lblPlaca;

	/**
	 * Etiqueta caracteristicas.
	 */
	private JLabel lblCaracteristicas;

	/**
	 * Etiqueta propietario.
	 */
	private JLabel lblPropietario;

	/**
	 * Campo de texto  placa.
	 */
	private JTextField txtPlaca;

	/**
	 * Campo de texto caracteristicas.
	 */
	private JTextField txtCaracteristicas;

	/**
	 * Campo de texto propietario.
	 */
	private JTextField txtPropietario;

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
	public DialogoAdicionarVehiculo( InterfazAforoAndesApp pPrincipal )
	{
		super( pPrincipal, true );

		principal = pPrincipal;
		setTitle( "Agregar vehículo" );
		setLayout( new java.awt.BorderLayout( ) );

		panelInfo = new JPanel( );
		panelInfo.setPreferredSize( new Dimension( 450, 200 ) );

		panelInfo.setBorder( new TitledBorder( "Información" ) );
		GridLayout layout = new GridLayout( 5, 2, -175, 30 );
		layout.setVgap( 10 );
		panelInfo.setLayout( layout );

		lblPlaca = new JLabel( "Placa: " );
		panelInfo.add( lblPlaca );
		txtPlaca = new JTextField( );
		panelInfo.add( txtPlaca );

		lblCaracteristicas = new JLabel( "Caracteristicas: " );
		panelInfo.add( lblCaracteristicas);
		txtCaracteristicas = new JTextField( );
		panelInfo.add( txtCaracteristicas);



		lblPropietario= new JLabel( "Propietario: " );
		panelInfo.add( lblPropietario );
		txtPropietario = new JTextField( );
		panelInfo.add( txtPropietario );

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

	// -----------------------------------------------------------------
		// Métodos
		// -----------------------------------------------------------------

		/**
		 * Manejo de eventos del usuario.
		 * @param pEvento Evento de usuario. pEvento != null.
		 */
		public void actionPerformed( ActionEvent pEvento )
		{
			String comando = pEvento.getActionCommand( );

			if( comando.equals( AGREGAR ) )
			{
				String placa = txtPlaca.getText();
				String caracteristicas = txtCaracteristicas.getText();
				String propietario = txtPropietario.getText();
				String idCentroComercial = ((VOCentroComercial)cbCentrosComerciales.getSelectedItem()).getIdentificador();
				if( identificador.equals( "" ) || capacidadNormalStr.equals( "" ) || areaStr.equals( "" ) || pesoMaximoStr.equals( "" ) || idCentroComercial.equals( "" ) )
				{
					JOptionPane.showMessageDialog( this, "Datos incompletos", "Agregar Centro Comercial", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					try
					{
						int capacidadNormal = Integer.parseInt(capacidadNormalStr);
						double pesoMaximo = Double.parseDouble(pesoMaximoStr);
						long area = Long.parseLong(areaStr);
						if ( interfaz.buscarAreaPorValor(area) == null)
						{
							interfaz.adicionarArea();
						}
						interfaz.adicionarAscensor(identificador, capacidadNormal, area, pesoMaximo, idCentroComercial, this);;
					}
					catch( NumberFormatException e2 )
					{
						JOptionPane.showMessageDialog( this, "La capacidad normal (int), el área (double) y el peso máximo (double) deben ser números.", "Agregar Ascensor", JOptionPane.ERROR_MESSAGE );
					}
				}
			}
			else if( comando.equals( CANCELAR ) )
			{
				this.dispose( );
			}
		}
}
