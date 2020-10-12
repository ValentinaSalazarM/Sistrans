/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

import java.util.Calendar;

/**
 * Interfaz para los métodos get de CENTROCOMERCIAL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOCentroComercial
{
	/**
	 * @return El identificador del centro comercial
	 */
	public String getIdentificador();

	/**
	 * @return El nombre del centro comercial 
	 */
	public String getNombre();
	
	/**
	 * @return La hora de apertura del centro comercial
	 */
	public Calendar getHoraApertura();
	
	/**
	 * @return Una cadena con la información básica del centro comercial
	 */
	@Override
	public String toString();

}
