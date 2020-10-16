/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.negocio;

/**
 * Clase para modelar un objeto resultante de la consulta del RFC1 por rango de horas
 */
public class RFC1Hora implements VOVisitante
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
	
	/**
	 * La hora de la visita consultada
	 */
	private int hora;
	
	/**
	 * El minuto de la visita consultada
	 */
	private int minuto;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
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

	/**
	 * @return La hora de las visitas realizadas por el visitante
	 */
	public int getHora() 
	{
		return hora;
	}

	/**
	 * @param hora - La nueva hora de las visitas realizadas por el visitante
	 */
	public void setHora(int hora) 
	{
		this.hora = hora;
	}

	/**
	 * @return El minuto de las visitas realizadas por el visitante
	 */
	public int getMinuto() 
	{
		return minuto;
	}

	/**
	 * @param hora - El nuevo minuto de las visitas realizadas por el visitante
	 */
	public void setMinuto(int minuto) 
	{
		this.minuto = minuto;
	}
	
}
