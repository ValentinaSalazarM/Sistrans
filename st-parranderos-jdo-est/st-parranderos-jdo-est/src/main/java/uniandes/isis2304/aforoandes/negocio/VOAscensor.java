/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.negocio;

/**
 * Interfaz para los métodos get de ASCENSOR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOAscensor 
{
	/**
	 * @return El identificador del ascensor
	 */
	public String getIdentificador();

	/**
	 * @return El id de la capacidad normal del ascensor
	 */
	public Long getCapacidadNormal();

	/**
	 * @return El id del área del ascensor
	 */
	public Long getArea();

	/**
	 * @return El peso máximo del ascensor
	 */
	public double getPesoMaximo();

	/**
	 * @return El id del centro comercial del ascensor
	 */
	public String getIdCentroComercial();

	/**
	 * @return Una cadena con la información básica del ascensor
	 */
	@Override
	public String toString();

}
