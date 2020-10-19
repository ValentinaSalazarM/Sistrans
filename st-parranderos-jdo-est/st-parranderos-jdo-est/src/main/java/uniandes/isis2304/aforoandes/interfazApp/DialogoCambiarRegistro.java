package uniandes.isis2304.aforoandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import uniandes.isis2304.aforoandes.negocio.VOTipoCarnet;

/**
 * Diálogo para introducir la información necesaria para modificar una visita
 */
@SuppressWarnings("serial")

public class DialogoCambiarRegistro extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para agregar la banda.
	 */
	public final static String AGREGAR = "Buscar una visita";

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
	 * Etiqueta del identificador del lector
	 */
	private JLabel lblIdLector;

	/**
	 * Campo de texto para el identificador del lector
	 */
	private JTextField txtIdLector;

	/**
	 * Etiqueta del tipo del carnet
	 */
	private JLabel lblTipoCarnet;

	/**
	 * Listado de los tipos de carnet disponibles
	 */
	private JComboBox<String> cbTipoCarnet;

	/**
	 * Etiqueta del id del visitante
	 */
	private JLabel lblIdVisitante;

	/**
	 * Campo de texto para el id del visitante
	 */
	private JTextField txtIdVisitante;

	/**
	 * Etiqueta de la fecha de la visita
	 */
	private JLabel lblFecha;

	/**
	 * Etiqueta de la fecha de la visita
	 */
	private JDateChooser dcFecha;
	
	/**
	 * Etiqueta del id de la hora de entrada registrada
	 */
	private JLabel lblHoraEntrada;
	
	/**
	 * Campo de texto para la hora de entrada registrada
	 */
	private JTextField txtHoraEntrada;

	/**
	 * Etiqueta del id de la hora de salida registrada
	 */
	private JLabel lblHoraSalida;
	
	/**
	 * Campo de texto para la hora de salida registrada
	 */
	private JTextField txtHoraSalida;
	
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
	public DialogoCambiarRegistro( InterfazAforoAndesApp pPrincipal )
	{
		interfaz = pPrincipal;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Buscar una visita" );
		setLocationRelativeTo( pPrincipal );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 7, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblIdLector = new JLabel( "Identificador del lector: " );
		campos.add( lblIdLector );
		txtIdLector = new JTextField( );
		campos.add( txtIdLector );

		lblIdVisitante = new JLabel( "Identificación del visitante: " );
		campos.add( lblIdVisitante );
		txtIdVisitante = new JTextField( );
		campos.add( txtIdVisitante );

		lblTipoCarnet = new JLabel( "Tipo de carnet: " );
		campos.add( lblTipoCarnet );

		cbTipoCarnet = new JComboBox<>();
		for ( VOTipoCarnet tipo: pPrincipal.listarVOTiposCarnet())
		{
			cbTipoCarnet.addItem(tipo.getId() + " - " + tipo.getTipo());
		}
		campos.add( cbTipoCarnet );

		lblFecha = new JLabel( "Fecha: " );
		campos.add(lblFecha);
		dcFecha = new JDateChooser();
		campos.add(dcFecha);
		
		lblHoraEntrada = new JLabel( "<html> Hora de entrada en formato 24h (hh:mm): " );
		campos.add( lblHoraEntrada );
		txtHoraEntrada = new JTextField( );
		campos.add( txtHoraEntrada );
		
		lblHoraSalida = new JLabel( "<html> Hora de salida en formato 24h (hh:mm): " );
		campos.add( lblHoraSalida );
		txtHoraSalida = new JTextField( );
		campos.add( txtHoraSalida );
		
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
			String idLectorStr = txtIdLector.getText();
			String idVisitante = txtIdVisitante.getText();
			String comboBoxTipo = (String) (cbTipoCarnet.getSelectedItem());
			long tipoCarnet = Long.valueOf(comboBoxTipo.split(" - ")[0]);
			Date fecha = dcFecha.getDate();
			Timestamp fechaTimeStamp = new Timestamp(fecha.getTime());
			String horarioEntrada = txtHoraEntrada.getText();
			String horarioSalida = txtHoraSalida.getText();

			if( idLectorStr.equals( "" ) || idVisitante.equals( "" ) || horarioEntrada.equals( "" ) || horarioSalida.equals( "" ) )
			{
				JOptionPane.showMessageDialog( this, "Datos incompletos", "Registrar visita", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				try
				{
					long idLector = Long.valueOf(idLectorStr);
					int horaEntrada = Integer.parseInt(horarioEntrada.split(":")[0]);
					int minutoEntrada = Integer.parseInt(horarioEntrada.split(":")[1]);
					int horaSalida = Integer.parseInt(horarioSalida.split(":")[0]);
					int minutoSalida = Integer.parseInt(horarioSalida.split(":")[1]);
					if ( horaEntrada > horaSalida || (horaEntrada == horaSalida && minutoSalida <= minutoEntrada))
					{
						JOptionPane.showMessageDialog( this, "La hora de salida debe ser posterior a la hora de entrada registrada", "Actualizar hora de salida de una visita", JOptionPane.ERROR_MESSAGE );
					}
					interfaz.cambiarHoraSalidaRegistranCarnet(idLector, tipoCarnet, idVisitante, fechaTimeStamp, horaEntrada, minutoEntrada, horaSalida, minutoSalida, this);
				}
				catch( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( this, "Las horas deben ser números separados por ':' y el identificador del lector un número.", "Registrar visita", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );
		}
	}
}
