/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.negocio;

public class RFC3Fecha 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del espacio asignado
	 */
	private String identificador;
	
	/**
	 * Índice de aforo del espacio
	 */
	private double indice;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public RFC3Fecha() 
	{
		this.identificador = "";
		this.indice = 0;
	}
	
	/**
	 * @return El identificador del espacio
	 */
	public String getIdentificador() 
	{
		return identificador;
	}

	/**
	 * @param identificador - El nuevo identificador del espacio
	 */
	public void setIdentificador(String identificador) 
	{
		this.identificador = identificador;
	}
	
	/**
	 * @return El índice del espacio
	 */
	public double getIndice() 
	{
		return indice;
	}

	/**
	 * @param indice - El nuevo índice de aforo del espacio
	 */
	public void setIdentificador(double indice) 
	{
		this.indice = indice;
	}

}
