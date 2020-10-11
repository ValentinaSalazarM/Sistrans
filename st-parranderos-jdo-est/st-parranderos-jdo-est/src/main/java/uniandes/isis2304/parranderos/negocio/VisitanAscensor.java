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
 * Clase para modelar la relación VISITANASCENSOR del negocio de Aforo-CCAndes:
 * Cada objeto de esta clase representa el hecho que un visitante visita un ascensor.
 * Se modela mediante los identificadores del visitante y del ascensor respectivamente.
 * Debe existir un visitante con el identificador dado
 * Debe existir un ascensor con el identificador dado 
 * 
 */
public class VisitanAscensor implements VOVisitanAscensor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del ascensor que es visitado
	 */
	private String idAscensor;

	/**
	 * El identificador del visitante que visita un ascensor
	 */
	private String idVisitante;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public VisitanAscensor() 
	{
		this.idAscensor = "";
		this.idVisitante = "";
	}

	/**
	 * Constructor con valores
	 * @param idAscensor - El identificador del ascensor. Debe exixtir un ascensor con dicho identificador
	 * @param idVisitante - El identificador del visitante. Debe existir un visitante con dicho identificador
	 */
	public VisitanAscensor(String idAscensor, String idVisitante) 
	{
		this.idAscensor = idAscensor;
		this.idVisitante = idVisitante;
	}

	/**
	 * @return El id del ascensor visitado
	 */
	public String getIdAscensor() 
	{
		return idAscensor;
	}

	/**
	 * @param idAscensor - El nuevo idAscensor. Debe existir un ascensor con dicho identificador.
	 */
	public void setIdAscensor(String idAscensor) 
	{
		this.idAscensor = idAscensor;
	}

	/**
	 * @return El id del visitante del ascensor
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
		return "VisitanAscensor [idAscensor=" + idAscensor + ", idVisitante=" + idVisitante + "]";
	}
	
}
