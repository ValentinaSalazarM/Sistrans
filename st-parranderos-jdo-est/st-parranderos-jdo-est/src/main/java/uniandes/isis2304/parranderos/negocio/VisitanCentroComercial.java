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
 * Clase para modelar la relación VISITANCENTROCOMERCIAL del negocio de Aforo-CCAndes:
 * Cada objeto de esta clase representa el hecho que un visitante visita un centro comercial.
 * Se modela mediante los identificadores del visitante y del centro comercial respectivamente.
 * Debe existir un visitante con el identificador dado
 * Debe existir un centro comercial con el identificador dado 
 * 
 */
public class VisitanCentroComercial implements VOVisitanCentroComercial
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del centro comercial que es visitado
	 */
	private String idCentroComercial;

	/**
	 * El identificador del visitante que visita un centro comercial
	 */
	private String idVisitante;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public VisitanCentroComercial() 
	{
		this.idCentroComercial = "";
		this.idVisitante = "";
	}

	/**
	 * Constructor con valores
	 * @param idCentroComercial - El identificador del centro comercial. Debe exixtir un centro comercial con dicho identificador
	 * @param idVisitante - El identificador del visitante. Debe existir un visitante con dicho identificador
	 */
	public VisitanCentroComercial(String idCentroComercial, String idVisitante) 
	{
		this.idCentroComercial = idCentroComercial;
		this.idVisitante = idVisitante;
	}

	/**
	 * @return El id del centro comercial visitado
	 */
	public String getIdCentroComercial() 
	{
		return idCentroComercial;
	}

	/**
	 * @param idCentroComercial - El nuevo idCentroComercial. Debe existir un centro comercial con dicho identificador.
	 */
	public void setIdCentroComercial(String idCentroComercial) 
	{
		this.idCentroComercial = idCentroComercial;
	}

	/**
	 * @return El id del visitante del centro comercial
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
		return "VisitanCentroComercial [idCentroComercial=" + idCentroComercial + ", idVisitante=" + idVisitante + "]";
	}
	
}
