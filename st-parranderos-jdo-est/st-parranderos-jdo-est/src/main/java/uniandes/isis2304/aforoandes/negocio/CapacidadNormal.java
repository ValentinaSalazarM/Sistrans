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
 * Clase para modelar el concepto CAPACIDADNORMAL del negocio de Aforo-CCAndes:
 * 
 */
public class CapacidadNormal implements VOCapacidadNormal
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de la capacidad normal
	 */
	private long id;
	
	/**
	 * El valor numérico de la capacidad normal
	 */
	private double valor;
	
	/**
	 * El aforo correspondiente a la capacidad normal
	 */
	private int aforo;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public CapacidadNormal () 
	{
		this.id = 0;
		this.valor = 0;
		this.aforo = 0;
	}




	/**
	 * Constructor con valores
	 * @param id - El identificador de la capacidad normal
	 * @param valor - El valor numérico de la capacidad normal
	 * @param aforo - El aforo correspondiente a la capacidad normal
	 */
	public CapacidadNormal (long id, double valor, int aforo) 
	{
		this.id = id;
		this.valor = valor;
		this.aforo = aforo;
	}

	/**
	 * @return El id de la capacidad normal
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id de la capacidad normal
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @param valor - El nuevo valor numérico de la capacidad normal
	 */
	public void setValor(double valor) 
	{
		this.valor = valor;
	}

	/**
	 * @return El aforo correspondiente a la capacidad normal
	 */
	public int getAforo() {
		return aforo;
	}

	/**
	 * @param aforo - El nuevo aforo correspondiente a la capacidad normal
	 */
	public void setAforo(int aforo) 
	{
		this.aforo = aforo;
	}

	/** 
	 * @return Una cadena con la información básica de la capacidad normal
	 */
	@Override
	public String toString() 
	{
		return "CapacidadNormal [id=" + id + ", valor=" + valor + ", aforo=" + aforo + "]";
	}

	/**
	 * @return the valor
	 */
	public double getValor() 
	{
		return valor;
	}


}
