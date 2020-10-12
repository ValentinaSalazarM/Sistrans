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
 * Interfaz para los métodos get de EMPLEADO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOEmpleado
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * @return El idVisitante respectivo
	 */
	public String getIdVisitante();

	/**
	 * @return El identificador del lugar donde trabaja el empleado
	 */
	public String getLugarTrabajo();

	/**
	 * @return Una cadena de caracteres con la información del empleado
	 */
	@Override
	public String toString(); 

}
