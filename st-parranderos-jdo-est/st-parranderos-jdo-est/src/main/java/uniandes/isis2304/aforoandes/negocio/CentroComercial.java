/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.negocio;


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
	}

	/**
	 * Constructor con valores
	 * @param identificador - El identificador del centro comercial
	 * @param nombre - El nombre del centro comercial
	 */
	public CentroComercial (String identificador, String nombre) 
	{
		this.identificador = identificador;
		this.nombre = nombre;
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
	 * @return Una cadena con la información básica del centro comercial
	 */
	@Override
	public String toString() 
	{
		return "CentroComercial [identificador=" + identificador + ", nombre=" + nombre + "]";
	}

	
}
