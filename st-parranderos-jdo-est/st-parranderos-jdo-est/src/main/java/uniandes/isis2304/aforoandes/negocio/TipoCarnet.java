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
 * Clase para modelar el concepto TIPOCARNET del negocio de Aforo-CCAndes
 *
 */
public class TipoCarnet implements VOTipoCarnet
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del tipo de carnet
	 */
	private long id;

	/**
	 * El nombre del tipo de carnet (QR, Físico)
	 */
	private String tipo;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public TipoCarnet() 
	{
		this.id = 0;
		this.tipo = "Default";
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de carnet
	 * @param tipo - El nombre del tipo de carnet
	 */
	public TipoCarnet(long id, String tipo) 
	{
		this.id = id;
		this.tipo = tipo;
	}

	/**
	 * @return El id del tipo de carnet
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del tipo de carnet
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del tipo de carnet
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo - El nuevo nombre del tipo de carnet
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}


	/**
	 * @return Una cadena de caracteres con la información del tipo de carnet
	 */
	@Override
	public String toString() 
	{
		return "TipoCarnet [id=" + id + ", tipo=" + tipo + "]";
	}

	/**
	 * Define la igualdad dos tipos de carnet
	 * @param tipoCarnet - El tipo de carnet a comparar
	 * @return true si tienen el mismo identificador y el mismo nombre
	 */
	public boolean equals(Object tipoCarnet) 
	{
		TipoCarnet tc = (TipoCarnet) tipoCarnet;
		return id == tc.id && tipo.equalsIgnoreCase (tc.tipo);
	}

}
