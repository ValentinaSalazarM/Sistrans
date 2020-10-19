package uniandes.isis2304.aforoandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import uniandes.isis2304.aforoandes.negocio.VOTipoLector;

/**
 * Dialogo para añadir un lector
 *
 */
public class DialogoAdicionarLector extends JDialog implements ActionListener
{
	// -----------------------------------------------
	// Constantes
	// -----------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Comando para el botón Agregar.
	 */
	private static final String ACEPTAR = "Aceptar";

	/**
	 * Comando para el botón Cancelar.
	 */
	private static final String CANCELAR = "Cancelar";

	// -----------------------------------------------------------------
	// Atributos de la Interfaz
	// -----------------------------------------------------------------

	/**
	 * Etiqueta del identificador del lector
	 */
	private JLabel lblIdentificador;

	/**
	 * Etiqueta del identificador del lector
	 */
	private JTextField txtIdentificador;

	/**
	 * Etiqueta identificador del espacio
	 */
	private JLabel lblIdentificadorEspacio;
	
	/**
	 * Etiqueta del identificador del lector
	 */
	private JTextField txtIdentificadorEspacio;

	/**
	 * Etiqueta tipo de lector
	 */
	private JLabel lblTipoLector;	

	/**
	 * Listado de los tipos de lector disponibles
	 */
	private JComboBox<String> cbTiposLector;
	
	/**
	 * Etiqueta espacio donde se ubica el lector
	 */
	private JLabel lblEspacio;

	/**
	 * Botones de tipo de lector
	 */
	private JRadioButton rdAscensor;
	private JRadioButton rdBaño;
	private JRadioButton rdCentroComercial;
	private JRadioButton rdLocalComercial;
	private JRadioButton rdParqueadero;


	/**
	 * Ventana principal de la aplicación.
	 */
	private InterfazAforoAndesApp principal;

	/**
	 * Botón usado para generar el reporte.
	 */
	private JButton botonAgregar;

	/**
	 * Botón para cancelar la creación del reporte.
	 */
	private JButton botonCancelar;

	/**
	 * Crea el dialogo para generar el reporte del circo.
	 * @param pPrincipal Ventana principal de la aplicación. pPrincipal!=null.
	 */
	public DialogoAdicionarLector( InterfazAforoAndesApp pPrincipal )
	{
		super( pPrincipal, true );
		setLocationRelativeTo( pPrincipal );

		principal = pPrincipal;
		setTitle( "Agregar lector" );
		setLayout( new GridBagLayout( ) );
		GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

		lblIdentificador = new JLabel( "Identificador: " );
		add( lblIdentificador, gbcE );

		gbcE.gridy = 1;
		lblTipoLector = new JLabel( "Tipo de lector: " );
		add( lblTipoLector, gbcE );
		
		gbcE.gridy = 2;
		lblIdentificadorEspacio = new JLabel( "Identificador del espacio donde se localiza: " );
		add( lblIdentificadorEspacio, gbcE );
		
		gbcE.gridy = 3;
		lblEspacio = new JLabel( "Espacio donde se localiza: " );
		add( lblEspacio, gbcE );

		GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		
		gbcC.gridy= 0;
		txtIdentificador = new JTextField( );
		add( txtIdentificador, gbcC );
		
		gbcC.gridy= 1;
		cbTiposLector = new JComboBox<>();
		for ( VOTipoLector tipo: pPrincipal.listarVOTiposLector())
		{
			cbTiposLector.addItem(tipo.getId() + " - " + tipo.getTipo());
		}
		add( cbTiposLector, gbcC );

		gbcC.gridy = 2;
		JPanel botones1=new JPanel();
		botones1.setLayout(new BorderLayout());

		JPanel botones2=new JPanel();
		botones2.setLayout(new BorderLayout());

		gbcC.gridy = 3;
		txtIdentificadorEspacio = new JTextField( );
		add( txtIdentificadorEspacio, gbcC );

		rdAscensor= new JRadioButton("Ascensor");
		rdBaño= new JRadioButton("Baño");
		rdCentroComercial= new JRadioButton("Centro Comercial");
		rdLocalComercial = new JRadioButton ("Local Comercial");
		rdParqueadero = new JRadioButton ("Parqueadero");
		
		ButtonGroup grupo1= new ButtonGroup();
		ButtonGroup grupo2= new ButtonGroup();

		grupo1.add(rdAscensor);
		grupo1.add(rdBaño);
		grupo1.add(rdCentroComercial);
		grupo2.add(rdLocalComercial);
		grupo2.add(rdParqueadero);

		botones1.add(rdAscensor, BorderLayout.LINE_START);
		botones1.add(rdBaño, BorderLayout.CENTER);
		botones1.add(rdCentroComercial, BorderLayout.LINE_END);
		
		botones2.add(rdLocalComercial, BorderLayout.LINE_START);
		botones2.add(rdParqueadero, BorderLayout.LINE_END);

		add(botones1,gbcC);
		
		gbcC.gridy = 3;
		
		add(botones2,gbcC);
		
		// Construir e inicializar los botones
		JPanel panelBotones = new JPanel( );

		botonAgregar = new JButton( "Aceptar" );
		botonAgregar.setActionCommand( ACEPTAR );
		botonAgregar.addActionListener( this );
		panelBotones.add( botonAgregar );

		botonCancelar = new JButton( "Cancelar" );
		botonCancelar.setActionCommand( CANCELAR );
		botonCancelar.addActionListener( this );
		panelBotones.add( botonCancelar );

		GridBagConstraints gbcB = new GridBagConstraints( 0, 5, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		add( panelBotones, gbcB );

		pack( );
		setLocationRelativeTo( pPrincipal );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );

	}

	/**
	 * Método que recoge las acciones sobre los objetos que está escuchando.
	 * @param pEvento Evento que se realizó. pEvento!=null.
	 */
	public void actionPerformed(ActionEvent pEvento)
	{
		if( pEvento.getActionCommand( ).equals( ACEPTAR ) )
		{
			String idLectorStr = txtIdentificador.getText( );
			String espacio = txtIdentificadorEspacio.getText( );
			String comboBoxTipo = (String) (cbTiposLector.getSelectedItem());
			long tipoLector = Long.valueOf(comboBoxTipo.split(" - ")[0]);
			long idLector = Long.valueOf(idLectorStr);

			if( idLectorStr.equals( "" ))
			{
				JOptionPane.showMessageDialog( this, "Datos incompletos", "Agregar Lector", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				if(rdAscensor.isSelected())
					principal.adicionarLector(idLector, tipoLector, null, null, espacio, null, null, this);
				else if(rdBaño.isSelected())
					principal.adicionarLector(idLector, tipoLector, null, null, null, null, espacio, this);
				else if(rdCentroComercial.isSelected())
					principal.adicionarLector(idLector, tipoLector, espacio, null, null, null, null, this);
				else if(rdLocalComercial.isSelected())
					principal.adicionarLector(idLector, tipoLector, null, espacio, null, null, null, this);
				else if(rdParqueadero.isSelected())
					principal.adicionarLector(idLector, tipoLector, null, null, null, espacio, null, this);
			}

		}
		if( pEvento.getActionCommand( ).equals( CANCELAR ) )
		{
			setVisible( false );
			dispose( );
		}
	}

}
