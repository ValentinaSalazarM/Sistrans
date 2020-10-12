/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAandes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto VISITANTE del negocio de Aforo-CCAndes
 *
 */
public class Visitante implements VOVisitante
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los visitantes
	 */
	private String identificacion;
	
	/**
	 * El identificador del tipo de visitante. Debe existir en la tabla TIPOVISITANTE
	 */
	private long tipo;
	
	/**
	 * El nombre del visitante
	 */
	private String nombre;

	/**
	 * El correo del visitante
	 */
	private String correo;
	
	/**
	 * El teléfono del visitante
	 */
	private String telefonoPropio;
	
	/**
	 * El nombre del contacto de emergencia del visitante
	 */
	private String nombreEmergencia;
	
	/**
	 * El teléfono de emergencia del contacto de emergencia del visitante
	 */
	private String telefonoEmergencia;
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Visitante() 
    {
    	this.identificacion = "";
    	this.tipo = 0;
		this.nombre = "";
		this.correo = "";
		this.telefonoPropio = "";
		this.nombreEmergencia = "";
		this.telefonoEmergencia = "";
	}

	/**
	 * Constructor con valores
	 * @param identificador - El id del visitante
	 * @param tipo - El identificador del tipo de visitante. Debe existir un tipo con dicho identificador
	 * @param nombre - El nombre del visitante
	 * @param correo - El correo del visitante
	 * @param telefonoPropio - El teléfono del visitante
	 * @param nombreEmergencia - El nombre del contacto de emergencia del visitante
	 * @param telefonoEmergencia - El teléfono del contacto de emergencia del visitante
	 */
    public Visitante(String identificador, String nombre, long tipo, String correo, String telefonoPropio, String nombreEmergencia, String telefonoEmergencia) 
    {
    	this.identificacion = identificador;
    	this.tipo = tipo;
		this.nombre = nombre;
		this.correo = correo;
		this.telefonoPropio = telefonoPropio;
		this.nombreEmergencia = nombreEmergencia;
		this.telefonoEmergencia = telefonoEmergencia;
	}
    
	/**
	 * @return El identificador del visitante
	 */
	public String getIdentificador() 
	{
		return identificacion;
	}

	/**
	 * @param identificador - El nuevo identificador del visitante
	 */
	public void setIdentificador(String identificador) 
	{
		this.identificacion = identificador;
	}

	/**
	 * @return El identificador del tipo de visitante
	 */
	public long getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo - El nuevo tipo de visitante. Debe existir un tipo con dicho identificador
	 */
	public void setTipo(long tipo) 
	{
		this.tipo = tipo;
	}

	/**
	 * @return El nombre del visitante
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del visitante
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	/**
	 * @return El correo del visitante
	 */
	public String getCorreo() 
	{
		return correo;
	}

	/**
	 * @param correo - El nuevo correo del visitante
	 */
	public void setCorreo(String correo) 
	{
		this.correo = correo;
	}

	/**
	 * @return El telefono propio del visitante
	 */
	public String getTelefonoPropio() 
	{
		return telefonoPropio;
	}

	/**
	 * @param telefonoPropio - El nuevo telefono propio del visitante
	 */
	public void setTelefonoPropio(String telefonoPropio) 
	{
		this.telefonoPropio = telefonoPropio;
	}

	/**
	 * @return El nombre de emergencia del visitante
	 */
	public String getNombreEmergencia() 
	{
		return nombreEmergencia;
	}

	/**
	 * @param nombreEmergencia - El nuevo nombre de emergencia del visitante
	 */
	public void setNombreEmergencia(String nombreEmergencia) 
	{
		this.nombreEmergencia = nombreEmergencia;
	}

	/**
	 * @return El telefono de emergencia del visitante
	 */
	public String getTelefonoEmergencia() 
	{
		return telefonoEmergencia;
	}

	/**
	 * @param telefonoEmergencia - El nuevo telefono de emergencia del visitante
	 */
	public void setTelefonoEmergencia(String telefonoEmergencia) 
	{
		this.telefonoEmergencia = telefonoEmergencia;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del visitante
	 */
	public String toString() 
	{
		return "Visitante [identificador=" + identificacion + ", tipo=" + tipo + ", nombre=" + nombre + ", correo=" + correo + ", telefonoPropio=" + telefonoPropio
				+ ", nombreEmergencia=" + nombreEmergencia + ", telefonoEmergencia=" + telefonoEmergencia + "]";
	}
	

}
