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

public class DialogoAdicionarTipoVisitante extends JDialog implements ActionListener
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
	 * Etiqueta de la hora de inicio de circulación.
	 */
	private JLabel lblHorarioInicio;

	/**
	 * Campo de texto para la hora de inicio de circulación.
	 */
	private JTextField txtHorarioInicio;

	/**
	 * Etiqueta de la hora límite de circulación.
	 */
	private JLabel lblHorarioLimite;

	/**
	 * Campo de la hora límite de circulación.
	 */
	private JTextField txtHorarioLimite;

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
	public DialogoAdicionarTipoVisitante( InterfazAforoAndesApp pPrincipal )
	{
		interfaz = pPrincipal;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Tipo de Visitante" );
		setLocationRelativeTo( pPrincipal );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 7, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 20, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblTipo = new JLabel( "Tipo: " );
		campos.add( lblTipo );
		txtTipo = new JTextField( );
		campos.add( txtTipo );

		lblHorarioInicio = new JLabel( "Hora de inicio de circulación en formato 24h (hh:mm): " );
		campos.add( lblHorarioInicio );
		txtHorarioInicio = new JTextField( );
		campos.add( txtHorarioInicio );

		lblHorarioLimite = new JLabel( "Hora límite de circulación en formato 24h (hh:mm): " );
		campos.add( lblHorarioLimite );
		txtHorarioLimite = new JTextField( );
		campos.add( txtHorarioLimite );

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
			String horarioInicio = txtHorarioInicio.getText( );
			String horarioLimite =  txtHorarioLimite.getText( );
			String tipo = txtTipo.getText();

			if( tipo.equals( "" ) || horarioInicio.equals( "" ) || horarioLimite.equals( "" ))
			{
				JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Agregar Tipo Visitante", JOptionPane.ERROR_MESSAGE );
			}
			else
			{    
				try
				{
					int horaInicio = 0;
					int horaLimite = 0;
					int minutoInicio = 0;
					int minutoLimite = 0;
					horaInicio = Integer.parseInt( horarioInicio.split(":")[0]);
					minutoInicio = Integer.parseInt( horarioInicio.split(":")[1]);
					horaLimite = Integer.parseInt( horarioLimite.split(":")[0]);
					minutoLimite = Integer.parseInt( horarioLimite.split(":")[1]);

					if ( horaLimite < horaInicio || (horaLimite == horaInicio && minutoLimite <= minutoInicio))
					{
						JOptionPane.showMessageDialog( this, "La hora límite debe ser posterior a la hora de inicio", "Agregar Tipo Visitante", JOptionPane.ERROR_MESSAGE );
					}

					interfaz.agregarTipoVisitante(tipo, horaInicio, minutoInicio, horaLimite, minutoLimite, this);
				}
				catch( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( this, "Las horas deben ser números separados por ':'", "Agregar Tipo Visitante", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );

		}
	}
}
