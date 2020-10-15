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
 * @author Usuario
 *
 */
public class RFC1Hora 
{
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

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the tipo
	 */
	public long getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the telefonoPropio
	 */
	public String getTelefonoPropio() {
		return telefonoPropio;
	}

	/**
	 * @param telefonoPropio the telefonoPropio to set
	 */
	public void setTelefonoPropio(String telefonoPropio) {
		this.telefonoPropio = telefonoPropio;
	}

	/**
	 * @return the nombreEmergencia
	 */
	public String getNombreEmergencia() {
		return nombreEmergencia;
	}

	/**
	 * @param nombreEmergencia the nombreEmergencia to set
	 */
	public void setNombreEmergencia(String nombreEmergencia) {
		this.nombreEmergencia = nombreEmergencia;
	}

	/**
	 * @return the telefonoEmergencia
	 */
	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}

	/**
	 * @param telefonoEmergencia the telefonoEmergencia to set
	 */
	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}

	/**
	 * @return the hora
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(int hora) {
		this.hora = hora;
	}

	/**
	 * @return the minuto
	 */
	public int getMinuto() {
		return minuto;
	}

	/**
	 * @param minuto the minuto to set
	 */
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	
}
