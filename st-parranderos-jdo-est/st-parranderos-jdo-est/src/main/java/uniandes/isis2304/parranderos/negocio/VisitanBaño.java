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
 * Clase para modelar la relación VISITANBAÑO del negocio de Aforo-CCAndes:
 * Cada objeto de esta clase representa el hecho que un visitante visita un baño.
 * Se modela mediante los identificadores del visitante y del baño respectivamente.
 * Debe existir un visitante con el identificador dado
 * Debe existir un baño con el identificador dado 
 * 
 */
public class VisitanBaño implements VOVisitanBaño
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del baño que es visitado
	 */
	private String idBaño;

	/**
	 * El identificador del visitante que visita un baño
	 */
	private String idVisitante;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public VisitanBaño() 
	{
		this.idBaño = "";
		this.idVisitante = "";
	}

	/**
	 * Constructor con valores
	 * @param idBaño - El identificador del baño. Debe exixtir un baño con dicho identificador
	 * @param idVisitante - El identificador del visitante. Debe existir un visitante con dicho identificador
	 */
	public VisitanBaño(String idBaño, String idVisitante) 
	{
		this.idBaño = idBaño;
		this.idVisitante = idVisitante;
	}

	/**
	 * @return El id del baño visitado
	 */
	public String getIdBaño() 
	{
		return idBaño;
	}

	/**
	 * @param idBaño - El nuevo idBaño. Debe existir un baño con dicho identificador.
	 */
	public void setIdBaño(String idBaño) 
	{
		this.idBaño = idBaño;
	}

	/**
	 * @return El id del visitante del baño
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
		return "VisitanBaño [idBaño=" + idBaño + ", idVisitante=" + idVisitante + "]";
	}
	
}
