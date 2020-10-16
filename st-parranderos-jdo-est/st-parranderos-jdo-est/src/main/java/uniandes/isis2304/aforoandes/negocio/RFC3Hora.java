/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.negocio;

public class RFC3Hora
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del local comercial asignado por un individuo.
	 */
	private String identificador;

	/**
	 * Índice de aforo del espacio
	 */
	private double indice;

	/**
	 * La hora de la visita consultada
	 */
	private int hora;
	
	/**
	 * El minuto de la visita consultada
	 */
	private int minuto;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public RFC3Hora() 
	{
		this.identificador = "";
		this.indice = 0;
		this.hora = 0;
		this.minuto = 0;
	}

	/**
	 * @return El identificador del local comercial
	 */
	public String getIdentificador() 
	{
		return identificador;
	}

	/**
	 * @param identificador - El nuevo identificador del local comercial
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

	/**
	 * @return La hora de las visitas realizadas por el visitante
	 */
	public int getHora() 
	{
		return hora;
	}

	/**
	 * @param hora - La nueva hora de las visitas realizadas por el visitante
	 */
	public void setHora(int hora) 
	{
		this.hora = hora;
	}

	/**
	 * @return El minuto de las visitas realizadas por el visitante
	 */
	public int getMinuto() 
	{
		return minuto;
	}

	/**
	 * @param hora - El nuevo minuto de las visitas realizadas por el visitante
	 */
	public void setMinuto(int minuto) 
	{
		this.minuto = minuto;
	}
}
