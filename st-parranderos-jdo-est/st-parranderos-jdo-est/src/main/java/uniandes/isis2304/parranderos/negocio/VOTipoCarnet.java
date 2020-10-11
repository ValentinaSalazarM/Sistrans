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
 * Interfaz para los métodos get de TIPOCARNET.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOTipoCarnet 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id del tipo de carnet
	 */
	public long getId();

	/**
	 * @return El nombre del tipo de carnet
	 */
	public String getTipo();

	/**
	 * @return Una cadena de caracteres con la información del tipo de carnet
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos tipos de carnet
	 * @param tipoCarnet - El tipo de carnet a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object tipoCarnet); 
}
