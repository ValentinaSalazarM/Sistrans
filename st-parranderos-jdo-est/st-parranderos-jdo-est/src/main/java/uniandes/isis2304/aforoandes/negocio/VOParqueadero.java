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
 * Interfaz para los métodos get de PARQUEADERO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOParqueadero
{
	/**
	 * @return El identificador del parqueadero
	 */
	public String getIdentificador();

	/**
	 * @return El id de la capacidad normal del parqueadero
	 */
	public Long getCapacidadNormal();

	/**
	 * @return El id del área del parqueadero
	 */
	public Long getArea();

	/**
	 * @return El id del centro comercial del parqueadero
	 */
	public String getIdCentroComercial();

	/**
	 * @return Una cadena con la información básica del parqueadero
	 */
	@Override
	public String toString();

}

