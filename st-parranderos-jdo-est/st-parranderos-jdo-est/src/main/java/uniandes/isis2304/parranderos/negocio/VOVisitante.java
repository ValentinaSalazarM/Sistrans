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
 * Interfaz para los métodos get de VISITANTE.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOVisitante 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return El identificador del visitante
	 */
	public String getIdentificador();
	
	/**
	 * @return El identificador del tipo de visitante
	 */
	public long getTipo();
	
	/**
	 * @return El nombre del visitante
	 */
	public String getNombre();
	
	/**
	 * @return El correo del visitante
	 */
	public String getCorreo();
	
	/**
	 * @return El telefono propio del visitante
	 */
	public String getTelefonoPropio();

	/**
	 * @return El nombre de emergencia del visitante
	 */
	public String getNombreEmergencia();
	
	/**
	 * @return El telefono de emergencia del visitante
	 */
	public String getTelefonoEmergencia();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del visitante
	 */
	public String toString();

}
