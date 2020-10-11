/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de LOCALCOMERCIAL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOLocalComercial
{
	/**
	 * @return El identificador del local comercial
	 */
	public String getIdentificador();

	/**
	 * @return El cupo actual del local comercial
	 */
	public int getCupoActual();

	/**
	 * @return El id de la capacidad normal del local comercial
	 */
	public long getCapacidadNormal();

	/**
	 * @return El id del área del local comercial
	 */
	public long getArea();

	/**
	 * @return El id del tipo de local
	 */
	public long getTipoLocal();

	/**
	 * @return El id del centro comercial del local comercial
	 */
	public String getIdCentroComercial();

	/**
	 * @return Una cadena con la información básica del local comercial
	 */
	@Override
	public String toString();

}
