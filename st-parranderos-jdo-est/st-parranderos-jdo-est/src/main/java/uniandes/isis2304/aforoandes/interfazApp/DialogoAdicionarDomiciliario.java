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

public class DialogoAdicionarDomiciliario extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para agregar la banda.
	 */
	public final static String AGREGAR = "Agregar un domiciliario";

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
	 * Etiqueta de la identificación
	 */
	private JLabel lblIdVisitante;

	/**
	 * Campo de texto para la identificación
	 */
	private JTextField txtIdVisitante;

	/**
	 * Etiqueta para la hora de inicio del turno de trabajo
	 */
	private JLabel lblHoraInicioTurno;

	/**
	 * Campo de texto para la hora de inicio del turno de trabajo
	 */
	private JTextField txtHoraInicioTurno;

	/**
	 * Etiqueta de la hora final del turno de trabajo
	 */
	private JLabel lblHoraFinalTurno;

	/**
	 * Campo de la hora final del turno de trabajo
	 */
	private JTextField txtHoraFinalTurno;

	/**
	 * Etiqueta de la empresa de domicilios
	 */
	private JLabel lblEmpresaDomicilios;

	/**
	 * Campo de texto para la empresa de domicilios
	 */
	private JTextField txtEmpresaDomicilios;

	/**
	 * Bot�n para agregar 
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
	 * Crea la ventana de diálogo del domiciliario
	 * @param pPrincipal Instancia principal de la aplicación.
	 */
	public DialogoAdicionarDomiciliario( InterfazAforoAndesApp pPrincipal )
	{
		interfaz = pPrincipal;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Domiciliario" );
		setLocationRelativeTo( interfaz );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 6, 2, 5, 10 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 5, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblIdVisitante = new JLabel( "Identificación como visitante: " );
		campos.add( lblIdVisitante );
		txtIdVisitante = new JTextField( );
		campos.add( txtIdVisitante );

		lblEmpresaDomicilios = new JLabel( "Empresa de domicilios: " );
		campos.add( lblEmpresaDomicilios );
		txtEmpresaDomicilios = new JTextField( );
		campos.add( txtEmpresaDomicilios );

		lblHoraInicioTurno = new JLabel( "<html> Hora de inicio del turno en formato 24h (hh:mm): " );
		campos.add( lblHoraInicioTurno );
		txtHoraInicioTurno = new JTextField( );
		campos.add( txtHoraInicioTurno );

		lblHoraFinalTurno = new JLabel( "<html> Hora final del turno en formato 24h (formato hh:mm): " );
		campos.add( lblHoraFinalTurno );
		txtHoraFinalTurno = new JTextField( );
		campos.add( txtHoraFinalTurno );

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
			String idDomiciliario = txtIdVisitante.getText();
			String empresaDomicilios = txtEmpresaDomicilios.getText();
			String horarioInicioTurno = txtHoraInicioTurno.getText( );
			String horarioFinalTurno =  txtHoraFinalTurno.getText( );
			
			if( idDomiciliario.equals( "" ) || empresaDomicilios.equals("") || horarioInicioTurno.equals( "" ) || horarioFinalTurno.equals( "" ))
			{
				JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Agregar Domiciliario", JOptionPane.ERROR_MESSAGE );
			}
			else
			{    
				try
				{
					int horaInicioTurno = 0;
					int horaFinalTurno = 0;
					int minutoInicioTurno = 0;
					int minutoFinalTurno = 0;
					horaInicioTurno = Integer.parseInt( horarioInicioTurno.split(":")[0]);
					minutoInicioTurno = Integer.parseInt( horarioInicioTurno.split(":")[1]);
					horaFinalTurno = Integer.parseInt( horarioFinalTurno.split(":")[0]);
					minutoFinalTurno = Integer.parseInt( horarioFinalTurno.split(":")[1]);

					if ( horaFinalTurno < horaInicioTurno || (horaFinalTurno == horaInicioTurno && minutoFinalTurno <= minutoInicioTurno))
					{
						JOptionPane.showMessageDialog( this, "La hora de final de turno debe ser posterior a la hora de inicio", "Agregar Domiciliario", JOptionPane.ERROR_MESSAGE );
					}
					interfaz.adicionarDomiciliario(idDomiciliario, empresaDomicilios, horaInicioTurno, minutoInicioTurno, horaFinalTurno, minutoFinalTurno, this);
				}
				catch( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( this, "Las horas deben ser números separados por ':'", "Agregar Domiciliario", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );

		}
	}
}
