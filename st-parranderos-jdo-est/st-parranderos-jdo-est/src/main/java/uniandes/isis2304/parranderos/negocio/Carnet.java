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
 * Clase para modelar el concepto CARNET del negocio de Aforo-CCAndes
 *
 */
public class Carnet implements VOCarnet
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador tipo del carnet. Debe existir en la tabla de TIPOCARNET
	 */
	private long tipoCarnet;

	/**
	 * El identificador del visitante dueño del carnet. Debe existir en la tabla de VISITANTE
	 */
	private String idVisitante;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Carnet() 
	{
		this.tipoCarnet = 0;
		this.idVisitante = "";
	}

	/**
	 * Constructor con valores
	 * @param tipoCarnet - El identificador tipo del carnet
	 * @param idVisitante - El identificador del dueño del carnet. Debe existir un visitante con dicho identificador
	 */
	public Carnet(long tipoCarnet, String idVisitante) 
	{
		this.tipoCarnet = tipoCarnet;
		this.idVisitante = idVisitante;
	}
	
	/**
	 * @return El tipo del carnet
	 */
	public long getTipoCarnet() 
	{
		return tipoCarnet;
	}

	/**
	 * @param tipoCarnet - El nuevo tipo del carnet 
	 */
	public void setTipoCarnet(long tipoCarnet) 
	{
		this.tipoCarnet = tipoCarnet;
	}

	/**
	 * @return El visitante dueño del carnet
	 */
	public String getVisitante() 
	{
		return idVisitante;
	}

	/**
	 * @param visitante - El identificador del nuevo dueño del carnet. Debe existir un visitante con dicho identificador
	 */
	public void setVisitante(String visitante) {
		this.idVisitante = visitante;
	}

	/**
	 * @return Una cadena de caracteres con la información del carnet
	 */
	@Override
	public String toString() 
	{
		return "Carnet [tipoCarnet=" + tipoCarnet + ", idVisitante=" + idVisitante + "]";
	}


}
