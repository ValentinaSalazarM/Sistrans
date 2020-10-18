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

/**
 * Diálogo para introducir la información necesaria para realizar una consulta
 */
@SuppressWarnings("serial")

public class DialogoConsultarTop20Establecimientos extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para agregar la banda.
	 */
	public final static String AGREGAR = "Consultar establecimientos más populares";

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
	 * Etiqueta de la fecha inicial del rango de consulta
	 */
	private JLabel lblFechaInicial;

	/**
	 * Date chooser de la fecha inicial del rango de consulta
	 */
	private JDateChooser dcFechaInicial;

	/**
	 * Etiqueta de la fecha final del rango de consulta
	 */
	private JLabel lblFechaFinal;

	/**
	 * Date chooser de la fecha final del rango de consulta
	 */
	private JDateChooser dcFechaFinal;

	/**
	 * Etiqueta de la hora inicial de consulta
	 */
	private JLabel lblHoraInicial;

	/**
	 * Campo de texto para la hora inicial de consulta
	 */
	private JTextField txtHoraInicial;

	/**
	 * Etiqueta de la hora final de consulta
	 */
	private JLabel lblHoraFinal;

	/**
	 * Campo de texto para la hora final de consulta
	 */
	private JTextField txtHoraFinal;

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
	public DialogoConsultarTop20Establecimientos( InterfazAforoAndesApp pPrincipal )
	{
		interfaz = pPrincipal;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Registrar visita" );
		setLocationRelativeTo( pPrincipal );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 7, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 20, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblFechaInicial = new JLabel( "* Fecha inicial del rango: " );
		campos.add(lblFechaInicial);
		dcFechaInicial = new JDateChooser();
		campos.add(dcFechaInicial);

		lblFechaFinal = new JLabel( "* Fecha final del rango: " );
		campos.add(lblFechaFinal);
		dcFechaFinal = new JDateChooser();
		campos.add(dcFechaFinal);

		lblHoraInicial = new JLabel( "Hora inicial del rango en formato 24h (hh:mm) - Vacío si no aplica: " );
		campos.add( lblHoraInicial );
		txtHoraInicial = new JTextField( );
		campos.add( txtHoraInicial );

		lblHoraFinal = new JLabel( "Hora final del rango en formato 24h (hh:mm) - Vacío si no aplica: " );
		campos.add( lblHoraFinal );
		txtHoraFinal = new JTextField( );
		campos.add( txtHoraFinal );


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
			String horarioInicial = txtHoraInicial.getText();
			String horarioFinal = txtHoraFinal.getText();
			Date fechaInicial = dcFechaInicial.getDate();
			Date fechaFinal = dcFechaFinal.getDate();

			Timestamp fechaInicialTS = new Timestamp(fechaInicial.getTime());
			Timestamp fechaFinalTS = new Timestamp(fechaFinal.getTime());

			try
			{
				int horaInicial = -1;
				int minutoInicial = -1;
				int horaFinal = -1;
				int minutoFinal = -1;

				if (horarioInicial != null && !horarioInicial.equals(""))
				{
					horaInicial = Integer.parseInt(horarioInicial.split(":")[0]);
					minutoInicial = Integer.parseInt(horarioInicial.split(":")[1]);
					if ( horarioFinal != null && !horarioFinal.equals(""))
					{
						horaFinal = Integer.parseInt(horarioFinal.split(":")[0]);
						minutoFinal = Integer.parseInt(horarioFinal.split(":")[1]);
						if (horaFinal < horaInicial || (horaInicial == horaFinal && minutoFinal < minutoInicial))
						{
							JOptionPane.showMessageDialog( this, "La hora final debe ser posterior a la hora inicial del rango.", "Consultar establecimientos más populares", JOptionPane.ERROR_MESSAGE );
						}
					}
				}
				System.out.println(fechaInicialTS.toString() + " - " + fechaFinalTS.toString() + " - " + horaInicial + " - " + horaFinal);
				interfaz.consultarEstablecimientosPopulares(fechaInicialTS, fechaFinalTS, horaInicial, minutoInicial, horaFinal, minutoFinal, this);
				
			}
			catch( NumberFormatException e2 )
			{
				JOptionPane.showMessageDialog( this, "La hora deben ser números separados por ':'.", "Consultar establecimientos más populares", JOptionPane.ERROR_MESSAGE );
			}

		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );
		}
	}
}
