/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes

 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.negocio;

/**
 * Interfaz para los métodos get de DOMICILIARIO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VODomiciliario 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * @return El idVisitante respectivo
	 */
	public String getIdVisitante();

	/**
	 * @return La compañiaDomicilios en la que trabaja el domiciliario
	 */
	public String getEmpresaDomicilios();

	/**
	 * @return La hora de inicio de turno del domiciliario
	 */
	public long getHoraInicioTurno(); 

	/**
	 * @return La hora de final de turno del domiciliario
	 */
	public long getHoraFinalTurno(); 
	
	/**
	 * @return Una cadena de caracteres con la información del domiciliario
	 */
	@Override
	public String toString(); 

}
