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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.isis2304.aforoandes.negocio.VOLocalComercial;

/**
 * Dialogo para el registro o inicio de sesión.
 */
public class DialogoInicioSesion extends JDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para indicar que se desea registrar un nuevo jugador.
     */
    public final static String REGISTRO = "Registrar";

    /**
     * Constante para indicar que se desea iniciar sesión.
     */
    public final static String INICIO_SESION = "Iniciar sesión";

    /**
     * Constante para iniciar el inicio de sesión.
     */
    public final static String ACEPTAR = "Aceptar";

    /**
     * Constante para cancelar el inicio de sesión.
     */
    public final static String CANCELAR = "Cancelar";
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazAforoAndesApp principal;

    /**
     * Tipo del diálogo.
     */
    private String tipo;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Campo de texto para el login.
     */
    private JTextField txtIdentificacion;

    /**
     * Campo de texto para el nombre.
     */
    private JTextField txtNombre;
    
    /**
     * Combobox de los locales
     */
    private JComboBox cbLocales;
    
    /**
     * Combobox del rol de usuario
     */
    private JComboBox cbRol;

    /**
     * Campo de texto para la contraseña.
     */
    private JPasswordField txtContrasenia;

    /**
     * Botón para el inicio de sesión.
     */
    private JButton btnAceptar;

    /**
     * Botón para cancelar el inicio de sesión.
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo con todos sus componentes.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null
     * @param pTipo Tipo del diálogo a crear. pTipo pertenece{REGISTRO, INICIO_SESION}
     */
    public DialogoInicioSesion( InterfazAforoAndesApp pPrincipal, String pTipo )
    {
        principal = pPrincipal;
        tipo = pTipo;

        setTitle( pTipo );
        setSize( 500, tipo.equals( REGISTRO ) ? 310 : 370 );
        setLayout( new BorderLayout( ) );
        setLocationRelativeTo( principal );

        JPanel panelInformacion = new JPanel( );
        panelInformacion.setLayout( new BorderLayout( ) );
        add( panelInformacion );

        JPanel panelDatos = new JPanel( );
        panelDatos.setBorder( new TitledBorder( "Datos administrador" ) );
        panelDatos.setLayout( new GridLayout( tipo.equals( REGISTRO ) ? 3 : 2, 2, 5, 5 ) );
        panelInformacion.add( panelDatos, BorderLayout.CENTER );

        JLabel lblIdentificacion = new JLabel( "Identificación:" );
        panelDatos.add( lblIdentificacion );

        txtIdentificacion = new JTextField( );
        panelDatos.add( txtIdentificacion );

        if( tipo.equals( REGISTRO ) )
        {
            JLabel lblNombre = new JLabel( "Nombre:" );
            panelDatos.add( lblNombre );

            txtNombre = new JTextField( );
            panelDatos.add( txtNombre );
        }
        
        JLabel lblContrasenia = new JLabel( "Contraseña" );
        panelDatos.add( lblContrasenia );
        
        txtContrasenia = new JPasswordField( );
        panelDatos.add( txtContrasenia );

        JLabel lblRol = new JLabel( "Rol:" );
        panelDatos.add( lblRol );
        cbRol = new JComboBox();
        cbRol.addItem("Administrador");
        cbRol.addItem("Administrador local");
        panelDatos.add(cbRol);

        JLabel lblLocal = new JLabel( "Local administrado (si aplica)" );
        panelDatos.add( lblLocal );

        for ( VOLocalComercial local: pPrincipal.listarVOLocalComercial())
		{
			cbLocales.addItem(local.getIdentificador());
		}
		panelDatos.add(cbLocales);
        
        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 2, 10, 10 ) );
        panelBotones.setBorder( new EmptyBorder( 3, 55, 3, 55 ) );
        panelBotones.setPreferredSize( new Dimension( 0, 40 ) );
        add( panelBotones, BorderLayout.SOUTH );

        btnAceptar = new JButton( ACEPTAR );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        panelBotones.add( btnAceptar );

        btnCancelar = new JButton( CANCELAR );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelBotones.add( btnCancelar );

        JLabel lblImagen = new JLabel( );
        lblImagen.setIcon( new ImageIcon( "./data/imagenes/login.png" ) );
        add( lblImagen, BorderLayout.WEST );
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando == ACEPTAR )
        {

            String identificacion = txtIdentificacion.getText( );
            String contrasenia = String.valueOf(txtContrasenia.getPassword());
            String rol = (String) cbRol.getSelectedItem();
            String local = (String) cbRol.getSelectedItem();

            try
            {
                if( rol.isEmpty( ) || identificacion.isEmpty( ) || contrasenia.isEmpty( ) )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar todos los valores.", tipo, JOptionPane.WARNING_MESSAGE );
                }
                else
                {
                    if( tipo.equals( REGISTRO ) )
                    {
                        String nombre = txtNombre.getText( );
                        if( nombre.isEmpty( ) )
                        {
                            JOptionPane.showMessageDialog( this, "Debe ingresar todos los valores.", tipo, JOptionPane.WARNING_MESSAGE );
                        }
                        else
                        {
                            principal.registrar( identificacion, nombre, contrasenia, rol, local);
                            dispose( );
                        }
                    }
                    else if( tipo.equals( INICIO_SESION ) )
                    {

                        principal.iniciarSesion( identificacion, rol);
                        dispose( );
                    }
                }
            }
            catch( NumberFormatException nfe )
            {
                JOptionPane.showMessageDialog( this, "El puerto debe  ser un valor numérico.", tipo, JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( comando.equals( CANCELAR ) )
        {
            dispose( );
        }
    }

}
