/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

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
	public String getIdLector(); 

	/**
	 * @return El tipo del carnet registrado
	 */
	public long getTipoCarnet();

	/**
	 * @return El visitante asociado al carnet registrado
	 */
	public String getIdVisitante();

	/**
	 * @return La fecha en la que se realizó la visita
	 */
	public Date getFecha();
	
	/**
	 * @return El identificador del horario en el cual se realiza la lectura de entrada
	 */
	public long getHoraEntrada();
	
	/**
	 * @return El identificador del horario en el cual se realiza la lectura de salida
	 */
	public long getHoraSalida();
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();

}