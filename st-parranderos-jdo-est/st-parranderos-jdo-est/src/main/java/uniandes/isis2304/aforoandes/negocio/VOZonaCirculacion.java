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
 * Interfaz para los métodos get de ZONACIRCULACION.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOZonaCirculacion
{
	/**
	 * @return El identificador de la zona de circulación
	 */
	public String getIdentificador();

	/**
	 * @return La capacidad normal de la zona de circulación
	 */
	public int getCapacidadNormal();

	/**
	 * @return El id del centro comercial de la zona de circulación
	 */
	public String getIdCentroComercial();

	/**
	 * @return Una cadena con la información básica de la zona de circulación
	 */
	@Override
	public String toString();

}

