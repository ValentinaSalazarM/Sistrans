package uniandes.isis2304.aforoandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import uniandes.isis2304.aforoandes.negocio.VOCentroComercial;
import uniandes.isis2304.aforoandes.negocio.VOTipoLocal;

/**
 * Diálogo para introducir la información necesaria para crear un local comercial 
 */
@SuppressWarnings("serial")

public class DialogoAdicionarLocalComercial extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para agregar la banda.
	 */
	public final static String AGREGAR = "Agregar Local Comercial";

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
	 * Etiqueta del tipo del local
	 */
	private JLabel lblTipo;

	/**
	 * Listado de los tipos de local disponibles
	 */
	private JComboBox<String> cbTipoLocal;

	/**
	 * Etiqueta del estado de actividad del espacio
	 */
	private JLabel lblActivo;

	/**
	 * CheckBox para el estado de actividad del espacio
	 */
	private JCheckBox cbActivo;

	/**
	 * Etiqueta del id del centro comercial donde se encuentra el local
	 */
	private JLabel lblCentroComercial;

	/**
	 * Listado de los centros comerciales disponibles
	 */
	private JComboBox<String> cbCentrosComerciales;
	
	/**
	 * Botón para agregar 
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
	public DialogoAdicionarLocalComercial( InterfazAforoAndesApp pPrincipal )
	{
		interfaz = pPrincipal;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Local Comercial" );
		setLocationRelativeTo( pPrincipal );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 7, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblIdentificador = new JLabel( "* Identificador: " );
		campos.add( lblIdentificador );
		txtIdentificador = new JTextField( );
		campos.add( txtIdentificador );

		lblCapacidadNormal = new JLabel( "Capacidad Normal: " );
		campos.add( lblCapacidadNormal );
		txtCapacidadNormal = new JTextField( );
		campos.add( txtCapacidadNormal );

		lblArea = new JLabel( "* Área: " );
		campos.add( lblArea );
		txtArea = new JTextField( );
		campos.add( txtArea );

		lblTipo = new JLabel( "* Tipo de local: " );
		campos.add( lblTipo );

		cbTipoLocal = new JComboBox<>();
		for ( VOTipoLocal tipo: pPrincipal.listarVOTiposLocal())
		{
			cbTipoLocal.addItem(tipo.getId() + " - " + tipo.getTipo());
		}
		campos.add( cbTipoLocal );

		lblCentroComercial = new JLabel( "* Id Centro Comercial: " );
		campos.add( lblCentroComercial );
		cbCentrosComerciales = new JComboBox<>();
		for ( VOCentroComercial tv: pPrincipal.listarVOCentrosComerciales())
		{
			cbCentrosComerciales.addItem(tv.getIdentificador() + " - " + tv.getNombre());
		}
		campos.add( cbCentrosComerciales );

		lblActivo = new JLabel( "* Activo: " );
		campos.add(lblActivo);
		cbActivo = new JCheckBox();
		campos.add( cbActivo );
		
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
			String idLocalComercial = txtIdentificador.getText();
			String capacidadNormalStr = txtCapacidadNormal.getText();
			String areaStr = txtArea.getText();
			String comboBoxTipo = (String) (cbTipoLocal.getSelectedItem());
			long tipoLocal = Long.valueOf(comboBoxTipo.split(" - ")[0]);
			String comboBoxCC = (String) (cbCentrosComerciales.getSelectedItem());
			String idCentroComercial = comboBoxCC.split(" - ")[0];
			Boolean activo = cbActivo.isSelected();
			
			if( idLocalComercial.equals( "" ) || areaStr.equals( "" ) || idCentroComercial.equals( "" ) )
			{
				JOptionPane.showMessageDialog( this, "Datos incompletos", "Agregar Local Comercial", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				try
				{
					double area = Long.parseLong(areaStr);
					
					if ( !capacidadNormalStr.isEmpty())
					{
						int capacidadNormal = Integer.parseInt(capacidadNormalStr);
						if ( interfaz.buscarCapacidadNormalPorValor (capacidadNormal) == null)
						{
							interfaz.adicionarCapacidadNormal();
						}
						interfaz.adicionarLocalComercial(idLocalComercial, capacidadNormal, area, tipoLocal, activo, idCentroComercial, this);
					}
					else
					{
						if ( interfaz.buscarAreaPorValor(area) == null)
						{
							interfaz.adicionarArea();
						}
						interfaz.adicionarLocalComercial(idLocalComercial, -1, area, tipoLocal, activo, idCentroComercial, this);
					}
				}
				catch( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( this, "La capacidad normal (int), el área (double) y el peso máximo (double) deben ser números.", "Agregar Local Comercial", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );
		}
	}
}
