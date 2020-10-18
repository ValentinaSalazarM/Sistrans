/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.negocio;

/**
 * Clase para modelar el concepto ÁREA del negocio de Aforo-CCAndes:
 * 
 */
public class Area implements VOArea
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del área
	 */
	private long id;
	
	/**
	 * El valor numérico del área
	 */
	private double valor;
	
	/**
	 * El aforo correspondiente al área
	 */
	private int aforo;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Area () 
	{
		this.id = 0;
		this.valor = 0;
		this.aforo = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del área
	 * @param valor - El valor numérico del área
	 * @param aforo - El aforo correspondiente al área
	 */
	public Area (long id, double valor, int aforo) 
	{
		this.id = id;
		this.valor = valor;
		this.aforo = aforo;
	}

	/**
	 * @return El id del área
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del área
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El valor numérico del área
	 */
	public double getValor() 
	{
		return valor;
	}

	/**
	 * @param valor - El nuevo valor numérico del área
	 */
	public void setValor(double valor) 
	{
		this.valor = valor;
	}

	/**
	 * @return El aforo correspondiente al área 
	 */
	public int getAforo() {
		return aforo;
	}

	/**
	 * @param aforo - El nuevo aforo correspondiente al área
	 */
	public void setAforo(int aforo) 
	{
		this.aforo = aforo;
	}

	/** 
	 * @return Una cadena con la información básica del Área
	 */
	@Override
	public String toString() 
	{
		return "Area [id = " + id + ", valor = " + valor + ", aforo = " + aforo + "]";
	}
}
