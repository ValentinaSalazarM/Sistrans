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
 * Interfaz para los métodos get de TIPOLOCAL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOTipoLocal
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id del tipo de local
	 */
	public long getId();

	/**
	 * @return El nombre del tipo de local
	 */
	public String getTipo();

	/**
	 * @return La hora de apertura del tipo de local
	 */
	public Timestamp getHoraApertura(); 
	
	/**
	 * @return La hora de cierre del tipo de local
	 */
	public Timestamp getHoraCierre(); 

	/**
	 * @return Una cadena de caracteres con la información del tipo de local
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad de dos tipos de local
	 * @param tipoLocal - El tipo de local a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	@Override
	public boolean equals (Object tipoLocal); 
}
