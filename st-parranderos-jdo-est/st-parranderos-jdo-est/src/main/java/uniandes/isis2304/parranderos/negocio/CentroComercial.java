/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

import java.util.Calendar;

/**
 * Clase para modelar el concepto CENTROCOMERCIAL del negocio de Aforo-CCAndes
 *
 */
public class CentroComercial implements VOCentroComercial
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del centro comercial asignado por un individuo.
	 */
	private String identificador;
	
	/**
	 * El nombre del centro comercial
	 */
	private String nombre;
	
	/**
	 * Hora apertura del centro comercial 
	 */
	private Calendar horaApertura;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public CentroComercial() 
	{
		this.identificador = "";
		this.nombre = "";
		this.horaApertura = Calendar.getInstance();
	}

	/**
	 * Constructor con valores
	 * @param identificador - El identificador del centro comercial
	 * @param nombre - El nombre del centro comercial
	 * @param horaApertura - La hora de apertura del centro comercial
	 * @param cupoActual - El cupo actual del centro comercial
	 * @param aforoTotal - El aforo total del centro comercial
	 */
	public CentroComercial (String identificador, String nombre, Calendar horaApertura, int cupoActual, int aforoTotal) 
	{
		this.identificador = identificador;
		this.nombre = nombre;
		this.horaApertura = horaApertura;
	}
	
	/**
	 * @return El identificador del centro comercial
	 */
	public String getIdentificador() 
	{
		return identificador;
	}

	/**
	 * @param identificador - El nuevo identificador del centro comercial
	 */
	public void setIdentificador(String identificador) 
	{
		this.identificador = identificador;
	}

	/**
	 * @return El nombre del centro comercial 
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del centro comercial
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return La hora de apertura del centro comercial
	 */
	public Calendar getHoraApertura() 
	{
		return horaApertura;
	}

	/**
	 * @param horaApertura - La nueva hora apertura del centro comercial
	 */
	public void setHoraApertura(Calendar horaApertura) 
	{
		this.horaApertura = horaApertura;
	}

	/**
	 * @return Una cadena con la información básica del centro comercial
	 */
	@Override
	public String toString() 
	{
		return "CentroComercial [identificador=" + identificador + ", nombre=" + nombre + ", horaApertura=" + horaApertura + "]";
	}

	
}
