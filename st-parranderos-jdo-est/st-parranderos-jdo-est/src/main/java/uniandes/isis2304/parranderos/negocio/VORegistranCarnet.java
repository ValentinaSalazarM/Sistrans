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
 * Interfaz para los métodos get de REGISTRANCARNET.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VORegistranCarnet 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El lector que realiza el registro
	 */
	public String getLector(); 

	/**
	 * @return El tipo del carnet registrado
	 */
	public long getTipoCarnet();

	/**
	 * @return El visitante asociado al carnet registrado
	 */
	public String getVisitante();

	/**
	 * @return La hora de entrada registrada
	 */
	public Timestamp getHoraEntrada();
	
	/**
	 * @return La horaSalida registrada
	 */
	public Timestamp getHoraSalida();
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();

}
