/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.negocio;

import java.math.BigDecimal;

public class RFC3 
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
	private BigDecimal indice;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public RFC3() 
	{
		this.identificador = "";
		this.indice = null;
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
	public BigDecimal getIndice() 
	{
		return indice;
	}

	/**
	 * @param indice - El nuevo índice de aforo del espacio
	 */
	public void setIndice(BigDecimal indice) 
	{
		this.indice = indice;
	}
	
	/**
	 * @return Una cadena de caracteres con la información
	 */
	@Override
	public String toString() 
	{
		return "RFC3 [Identificador espacio = " + identificador + ", índice aforo = " + ((BigDecimal)indice).doubleValue() + "]";
	}
}
