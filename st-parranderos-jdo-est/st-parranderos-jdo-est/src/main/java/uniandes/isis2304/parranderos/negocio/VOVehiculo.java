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
 * Interfaz para los métodos get de VEHICULO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOVehiculo 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return La placa del vehículo
	 */
	public String getPlaca();

	/**
	 * @return Las caracteristicas del vehículo
	 */
	public String getCaracteristicas();

	/**
	 * @return El identificador del dueño del vehículo
	 */
	public String getDueño();
	
	/**
	 * @return Una cadena de caracteres con la información del vehículo
	 */
	@Override
	public String toString(); 

}
