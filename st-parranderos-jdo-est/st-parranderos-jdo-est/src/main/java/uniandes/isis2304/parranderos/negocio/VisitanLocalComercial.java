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
 * Clase para modelar la relación VISITANLOCALCOMERCIAL del negocio de Aforo-CCAndes:
 * Cada objeto de esta clase representa el hecho que un visitante visita un local comercial.
 * Se modela mediante los identificadores del visitante y del local comercial respectivamente.
 * Debe existir un visitante con el identificador dado
 * Debe existir un local comercial con el identificador dado 
 * 
 */
public class VisitanLocalComercial implements VOVisitanLocalComercial
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del local comercial que es visitado
	 */
	private String idLocalComercial;

	/**
	 * El identificador del visitante que visita un local comercial
	 */
	private String idVisitante;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public VisitanLocalComercial() 
	{
		this.idLocalComercial = "";
		this.idVisitante = "";
	}

	/**
	 * Constructor con valores
	 * @param idLocalComercial - El identificador del local comercial. Debe exixtir un local comercial con dicho identificador
	 * @param idVisitante - El identificador del visitante. Debe existir un visitante con dicho identificador
	 */
	public VisitanLocalComercial(String idLocalComercial, String idVisitante) 
	{
		this.idLocalComercial = idLocalComercial;
		this.idVisitante = idVisitante;
	}

	/**
	 * @return El id del local comercial visitado
	 */
	public String getIdLocalComercial() 
	{
		return idLocalComercial;
	}

	/**
	 * @param idLocalComercial - El nuevo idLocalComercial. Debe existir un local comercial con dicho identificador.
	 */
	public void setIdLocalComercial(String idLocalComercial) 
	{
		this.idLocalComercial = idLocalComercial;
	}

	/**
	 * @return El id del visitante del local comercial
	 */
	public String getIdVisitante() 
	{
		return idVisitante;
	}

	/**
	 * @param idVisitante - El nuevo idVisitante. Debe existir un visitante con dicho identificador
	 */
	public void setIdVisitante(String idVisitante) 
	{
		this.idVisitante = idVisitante;
	}

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "VisitanLocalComercial [idLocalComercial=" + idLocalComercial + ", idVisitante=" + idVisitante + "]";
	}
	
}
