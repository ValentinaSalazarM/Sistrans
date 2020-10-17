package uniandes.isis2304.aforoandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Di�logo para introducir la informaci�n necesaria para crear un tipo de visitante
 */
@SuppressWarnings("serial")

public class DialogoAdicionarTipoLocal extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para agregar la banda.
	 */
	public final static String AGREGAR = "Agregar un tipo de visitante";

	/**
	 * Comando para cancelar el proceso.
	 */
	public final static String CANCELAR = "Cancelar";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Instancia principal de la aplicaci�n.
	 */
	private InterfazAforoAndesApp interfaz;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------

	/**
	 * Etiqueta del nombre del tipo.
	 */
	private JLabel lblTipo;

	/**
	 * Campo de texto para el nombre del tipo.
	 */
	private JTextField txtTipo;

	/**
	 * Etiqueta de la hora de apertura del local.
	 */
	private JLabel lblHoraApertura;

	/**
	 * Campo de texto para la hora de apertura del local.
	 */
	private JTextField txtHoraApertura;

	/**
	 * Etiqueta de la hora de cierre del local.
	 */
	private JLabel lblHoraCierre;

	/**
	 * Campo de la hora de cierre del local.
	 */
	private JTextField txtHoraCierre;

	/**
	 * Bot�n para agregar el tipo.
	 */
	private JButton btnAgregar;

	/**
	 * Bot�n para cancelar el proceso.
	 */
	private JButton btnCancelar;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea la ventana de diálogo del tipo de visitante
	 * @param pPrincipal Instancia principal de la aplicación.
	 */
	public DialogoAdicionarTipoLocal( InterfazAforoAndesApp pPrincipal )
	{
		interfaz = pPrincipal;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Tipo de Visitante" );
		setLocationRelativeTo( null );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 6, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblTipo = new JLabel( "Tipo: " );
		campos.add( lblTipo );
		txtTipo = new JTextField( );
		campos.add( txtTipo );

		lblHoraApertura = new JLabel( "Hora de apertura en formato 24h (formato hh:mm): " );
		campos.add( lblHoraApertura );
		txtHoraApertura = new JTextField( );
		campos.add( txtHoraApertura );

		lblHoraCierre = new JLabel( "Hora de cierre en formato 24h (formato hh:mm): " );
		campos.add( lblHoraCierre );
		txtHoraCierre = new JTextField( );
		campos.add( txtHoraCierre );

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
	// M�todos
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
			String horarioApertura = txtHoraApertura.getText( );
			String horarioCierre =  txtHoraCierre.getText( );
			String tipo = txtTipo.getText();

			if( tipo.equals( "" ) || horarioApertura.equals( "" ) || horarioCierre.equals( "" ))
			{
				JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Agregar Tipo Local", JOptionPane.ERROR_MESSAGE );
			}
			else
			{    
				try
				{
					int horaApertura = 0;
					int horaCierre = 0;
					int minutoApertura = 0;
					int minutoCierre = 0;
					horaApertura = Integer.parseInt( horarioApertura.split(":")[0]);
					minutoApertura = Integer.parseInt( horarioApertura.split(":")[1]);
					horaCierre = Integer.parseInt( horarioCierre.split(":")[0]);
					minutoCierre = Integer.parseInt( horarioCierre.split(":")[1]);

					if ( horaCierre < horaApertura || (horaCierre == horaApertura && minutoCierre <= minutoApertura))
					{
						JOptionPane.showMessageDialog( this, "La hora de cierre debe ser posterior a la hora de apertura", "Agregar Tipo Local", JOptionPane.ERROR_MESSAGE );
					}

					interfaz.adicionarTipoLocal(tipo, horaApertura, minutoApertura, horaCierre, minutoCierre, this);
				}
				catch( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( this, "Las horas deben ser números separados por ':'", "Agregar Tipo Local", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );

		}
	}
}
