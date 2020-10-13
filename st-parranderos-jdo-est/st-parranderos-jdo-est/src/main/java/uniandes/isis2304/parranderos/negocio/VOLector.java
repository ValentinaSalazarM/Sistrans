/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de LECTOR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOLector
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id del lector
	 */
	public long getId();
	
	/**
	 * @return El id del tipo de lector. Debe existir un tipo de lector con dicho identificador
	 */
	public long getTipoLector();
	
	/**
	 * @return El id del centro comercial donde se localiza el lector
	 */
	public String getIdCentroComercial();
	
	/**
	 * @return El id del local comercial donde se localiza el lector
	 */
	public String getIdLocalComercial();

	/**
	 * @return El id del baño del centro comercial
	 */
	public String getIdBaño();
	
	/**
	 * @return El id del ascensor donde se localiza el lector
	 */
	public String getIdAscensor();
	
	/**
	 * @return El id del parqueadero donde se localiza el lector
	 */
	public String getIdParqueadero();
	
	/** 
	 * @return Una cadena con la información básica del lector
	 */
	@Override
	public String toString();
	
}
