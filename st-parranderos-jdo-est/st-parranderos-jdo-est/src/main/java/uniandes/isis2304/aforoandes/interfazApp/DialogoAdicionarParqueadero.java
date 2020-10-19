package uniandes.isis2304.aforoandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;

/**
 * Diálogo para introducir la información necesaria para crear un parqueadero 
 */
@SuppressWarnings("serial")

public class DialogoAdicionarParqueadero extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para agregar la banda.
	 */
	public final static String AGREGAR = "Agregar Parqueadero";

	/**
	 * Comando para cancelar el proceso.
	 */
	public final static String CANCELAR = "Cancelar";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Instancia principal de la aplicación.
	 */
	private InterfazAforoAndesApp interfaz;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------

	/**
	 * Etiqueta del identificador del espacio
	 */
	private JLabel lblIdentificador;

	/**
	 * Campo de texto para el identificador del espacio
	 */
	private JTextField txtIdentificador;

	/**
	 * Etiqueta de la capacidad normal del espacio
	 */
	private JLabel lblCapacidadNormal;

	/**
	 * Campo de texto para la capacidad normal del espacio
	 */
	private JTextField txtCapacidadNormal;

	/**
	 * Etiqueta del área del espacio
	 */
	private JLabel lblArea;

	/**
	 * Campo de texto para el área del espacio
	 */
	private JTextField txtArea;

	/**
	 * Etiqueta del id del centro comercial donde se encuentra el parqueadero
	 */
	private JLabel lblCentroComercial;

	/**
	 * Listado de los centros comerciales disponibles
	 */
	private JComboBox<String> cbCentrosComerciales;

	/**
	 * Botón para agregar al parqueadero
	 */
	private JButton btnAgregar;

	/**
	 * Botón para cancelar el proceso
	 */
	private JButton btnCancelar;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea la ventana de diálogo del espacio.
	 * @param pPrincipal Instancia principal de la aplicación.
	 */
	public DialogoAdicionarParqueadero( InterfazAforoAndesApp pPrincipal )
	{
		interfaz = pPrincipal;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar parqueadero" );
		setLocationRelativeTo( pPrincipal );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 6, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 20, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblIdentificador = new JLabel( "Identificador: " );
		campos.add( lblIdentificador );
		txtIdentificador = new JTextField( );
		campos.add( txtIdentificador );

		lblCapacidadNormal = new JLabel( "Capacidad Normal: " );
		campos.add( lblCapacidadNormal );
		txtCapacidadNormal = new JTextField( );
		campos.add( txtCapacidadNormal );

		lblArea = new JLabel( "Área: " );
		campos.add( lblArea );
		txtArea = new JTextField( );
		campos.add( txtArea );

		lblCentroComercial = new JLabel( "Id Centro Comercial: " );
		campos.add( lblCentroComercial );
		cbCentrosComerciales = new JComboBox<>();

		for ( VOCentroComercial tv: pPrincipal.listarVOCentrosComerciales())
		{
			cbCentrosComerciales.addItem(tv.getIdentificador() + " - " + tv.getNombre());
		}
		campos.add( cbCentrosComerciales );

		campos.add( new JLabel() );        

		JPanel botones = new JPanel( );
		botones.setBorder( new EmptyBorder(0,30,20,30) );
		botones.setLayout( new GridLayout( 1, 2 ) );
		add( botones, BorderLayout.SOUTH );

		btnAgregar = new JButton( "Agregar" );
		btnAgregar.setActionCommand( AGREGAR );
		btnAgregar.addActionListener( this );
		botones.add( btnAgregar );

		btnCancelar = new JButton( "Cancelar" );
		btnCancelar.addActionListener( this );
		btnCancelar.setActionCommand( CANCELAR );
		botones.add( btnCancelar );

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
			String identificador = txtIdentificador.getText();
			String capacidadNormalStr = txtCapacidadNormal.getText();
			String areaStr = txtArea.getText();
			String comboBox = (String) (cbCentrosComerciales.getSelectedItem());
			String idCentroComercial = comboBox.split(" - ")[0];
			
			if( identificador.equals( "" ) || capacidadNormalStr.equals( "" ) || idCentroComercial.equals( "" ) )
			{
				JOptionPane.showMessageDialog( this, "Datos incompletos", "Agregar parqueadero", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				try
				{
					int capacidadNormal = Integer.parseInt(capacidadNormalStr);
					double area = Long.parseLong(areaStr);
					if ( interfaz.buscarAreaPorValor(area) == null)
					{
						interfaz.adicionarArea();
					}
					if ( interfaz.buscarCapacidadNormalPorValor (capacidadNormal) == null)
					{
						interfaz.adicionarCapacidadNormal();
					}				
					interfaz.adicionarParqueadero(identificador, capacidadNormal, area, idCentroComercial, this);
				}
				catch( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( this, "La capacidad normal (int) y el área (double) deben ser números ", "Agregar Parqueadero", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );
		}
	}
}