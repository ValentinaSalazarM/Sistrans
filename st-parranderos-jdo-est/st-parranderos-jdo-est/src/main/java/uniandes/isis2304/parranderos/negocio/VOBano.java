/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de BAÑO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOBano
{
	/**
	 * @return El identificador del baño
	 */
	public String getIdentificador();

	/**
	 * @return El id de la capacidad normal del baño
	 */
	public long getCapacidadNormal();

	/**
	 * @return El id del área del baño
	 */
	public long getArea();

	/**
	 * @return El número de sanitarios en el baño
	 */
	public int getSanitarios();

	/**
	 * @return El id del centro comercial del baño
	 */
	public String getIdCentroComercial();

	/**
	 * @return Una cadena con la información básica del baño
	 */
	@Override
	public String toString();

}

