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
 * Clase para modelar la relación VISITANPARQUEADERO del negocio de Aforo-CCAndes:
 * Cada objeto de esta clase representa el hecho que un visitante visita un parqueadero.
 * Se modela mediante los identificadores del visitante y del parqueadero respectivamente.
 * Debe existir un visitante con el identificador dado
 * Debe existir un parqueadero con el identificador dado 
 * 
 */
public class VisitanParqueadero implements VOVisitanParqueadero
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del parqueadero que es visitado
	 */
	private String idParqueadero;

	/**
	 * El identificador del visitante que visita un parqueadero. Debe existir en la tabla VISITANTE
	 */
	private String idVisitante;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public VisitanParqueadero() 
	{
		this.idParqueadero = "";
		this.idVisitante = "";
	}

	/**
	 * Constructor con valores
	 * @param idParqueadero - El identificador del parqueadero. Debe exixtir un parqueadero con dicho identificador
	 * @param idVisitante - El identificador del visitante. Debe existir un visitante con dicho identificador
	 */
	public VisitanParqueadero(String idParqueadero, String idVisitante) 
	{
		this.idParqueadero = idParqueadero;
		this.idVisitante = idVisitante;
	}

	/**
	 * @return El id del parqueadero visitado
	 */
	public String getIdParqueadero() 
	{
		return idParqueadero;
	}

	/**
	 * @param idParqueadero - El nuevo idParqueadero. Debe existir un parqueadero con dicho identificador.
	 */
	public void setIdParqueadero(String idParqueadero) 
	{
		this.idParqueadero = idParqueadero;
	}

	/**
	 * @return El id del visitante del parqueadero
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
		return "VisitanParqueadero [idParqueadero=" + idParqueadero + ", idVisitante=" + idVisitante + "]";
	}
	
}
