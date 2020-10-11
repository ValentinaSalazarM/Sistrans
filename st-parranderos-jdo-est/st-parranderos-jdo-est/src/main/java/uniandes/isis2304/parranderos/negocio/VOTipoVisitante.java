/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes

 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

/**
 * Interfaz para los métodos get de TIPOVISITANTE.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOTipoVisitante
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id del tipo de visitante
	 */
	public long getId();

	/**
	 * @return El nombre del tipo de visitante
	 */
	public String getTipo();

	/**
	 * @return La hora límite del tipo de visitante
	 */
	public Timestamp getHoraLimite(); 
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de visitante
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad de dos tipos de visitante
	 * @param tipoVisitante - El tipo de visitante a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object tipoVisitante); 
}
