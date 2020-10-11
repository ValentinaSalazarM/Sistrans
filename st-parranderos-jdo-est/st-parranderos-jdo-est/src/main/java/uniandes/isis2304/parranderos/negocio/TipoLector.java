/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto TIPOLECTOR del negocio de Aforo-CCAndes
 *
 */
public class TipoLector implements VOTipoLector
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del tipo de lector
	 */
	private long id;

	/**
	 * El nombre del tipo de lector (Temperatura, Visitante, Vehículo)
	 */
	private String tipo;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public TipoLector() 
	{
		this.id = 0;
		this.tipo = "Default";
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de lector
	 * @param tipo - El nombre del tipo de lector
	 */
	public TipoLector(long id, String tipo) 
	{
		this.id = id;
		this.tipo = tipo;
	}

	/**
	 * @return El id del tipo de lector
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del tipo de lector
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del tipo de lector
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo - El nuevo nombre del tipo de lector
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}


	/**
	 * @return Una cadena de caracteres con la información del tipo de lector
	 */
	@Override
	public String toString() 
	{
		return "TipoLector [id=" + id + ", tipo=" + tipo + "]";
	}

	/**
	 * Define la igualdad dos tipos de lector
	 * @param tipoLector - El tipo de lector a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	public boolean equals(Object tipoLector) 
	{
		TipoLector tl = (TipoLector) tipoLector;
		return id == tl.id && tipo.equalsIgnoreCase (tl.tipo);
	}

}
