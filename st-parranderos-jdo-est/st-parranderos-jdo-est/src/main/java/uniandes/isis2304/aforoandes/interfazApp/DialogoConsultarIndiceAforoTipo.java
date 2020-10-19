/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
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

public class DialogoConsultarIndiceAforoTipo extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para realizar consulta.
	 */
	public final static String ACEPTAR = "Consultar índice de aforo de un tipo de local";

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
	 * Etiqueta del identificador del tipo de local
	 */
	private JLabel lblTipoLocal;

	/**
	 * Campo de texto para el identificador del tipo de local
	 */
	private JTextField txtTipoLocal;

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
	public DialogoConsultarIndiceAforoTipo( InterfazAforoAndesApp pPrincipal )
	{
		interfaz = pPrincipal;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Consultar índice de aforo de un tipo de local" );
		setLocationRelativeTo( pPrincipal );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 6, 2, 5, 1 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 20, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblTipoLocal = new JLabel( "* Tipo de local: " );
		campos.add( lblTipoLocal );
		txtTipoLocal = new JTextField( );
		campos.add( txtTipoLocal );

		lblFechaInicial = new JLabel( "* Fecha inicial del rango: " );
		campos.add(lblFechaInicial);
		dcFechaInicial = new JDateChooser();
		campos.add(dcFechaInicial);

		lblFechaFinal = new JLabel( "* Fecha final del rango: " );
		campos.add(lblFechaFinal);
		dcFechaFinal = new JDateChooser();
		campos.add(dcFechaFinal);

		lblHoraInicial = new JLabel( "<html> Hora inicial del rango en formato 24h (hh:mm) - Vacío si no aplica: " );
		campos.add( lblHoraInicial );
		txtHoraInicial = new JTextField( );
		campos.add( txtHoraInicial );

		lblHoraFinal = new JLabel( "<html> Hora final del rango en formato 24h (hh:mm) - Vacío si no aplica: " );
		campos.add( lblHoraFinal );
		txtHoraFinal = new JTextField( );
		campos.add( txtHoraFinal );


		campos.add( new JLabel() );        

		JPanel botones = new JPanel( );
		botones.setBorder( new EmptyBorder(0,30,20,30) );
		botones.setLayout( new GridLayout( 1, 2 ) );
		add( botones, BorderLayout.SOUTH );

		btnAgregar = new JButton( "Agregar" );
		btnAgregar.setActionCommand( ACEPTAR );
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

		if( comando.equals( ACEPTAR ) )
		{
			String tipoLocal = txtTipoLocal.getText();
			String horarioInicial = txtHoraInicial.getText();
			String horarioFinal = txtHoraFinal.getText();
			Date fechaInicial = dcFechaInicial.getDate();
			Date fechaFinal = dcFechaFinal.getDate();

			Timestamp fechaInicialTS = new Timestamp(fechaInicial.getTime());
			Timestamp fechaFinalTS = new Timestamp(fechaFinal.getTime());

			if( tipoLocal.equals( "" ) )
			{
				JOptionPane.showMessageDialog( this, "Datos incompletos", "Consultar índice de aforo", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				try
				{
					int horaInicial = -1;
					int minutoInicial = -1;
					int horaFinal = -1;
					int minutoFinal = -1;

					if ( fechaFinal.after(fechaInicial))
					{
						JOptionPane.showMessageDialog( this, "La fecha final debe ser posterior o igual a la fecha inicial del rango.", "Consultar índice de aforo", JOptionPane.ERROR_MESSAGE );
					}
					else
					{
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
									JOptionPane.showMessageDialog( this, "La hora final debe ser posterior a la hora inicial del rango.", "Consultar índice de aforo", JOptionPane.ERROR_MESSAGE );
									throw new Exception();
								}
							}
						}
						interfaz.consultarIndicesAforoTipoLocal(tipoLocal, fechaInicialTS, fechaFinalTS, horaInicial, minutoInicial, horaFinal, minutoFinal, this);
					}
				}
				catch( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( this, "La hora deben ser números separados por ':'.", "Consultar índice de aforo", JOptionPane.ERROR_MESSAGE );
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );
		}
	}
}
