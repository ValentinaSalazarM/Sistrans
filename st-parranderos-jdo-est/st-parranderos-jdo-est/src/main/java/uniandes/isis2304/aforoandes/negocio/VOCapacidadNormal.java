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
 * Interfaz para los métodos get de CAPACIDADNORMAL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOCapacidadNormal
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id de la capacidad normal
	 */
	public long getId();

	/**
	 * @return El valor numérico de la capacidad normal
	 */
	public double getValor();

	/**
	 * @return El aforo correspondiente a la capacidad normal
	 */
	public int getAforo();

	/** 
	 * @return Una cadena con la información básica de la capacidad normal
	 */
	@Override
	public String toString();

}
