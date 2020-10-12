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
 * Clase para modelar el concepto DOMICILIARIO del negocio de Aforo-CCAndes
 *
 */
public class Domiciliario implements VODomiciliario
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La identificación del visitante respectivo. Debe existir en la tabla VISITANTE
	 */
	private String idVisitante;

	/**
	 * La compañía de domicilios para la que trabaja el domiciliario
	 */
	private String compañiaDomicilios;
	
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Domiciliario() 
	{
		this.idVisitante = "";
		this.compañiaDomicilios = "";
	}

	/**
	 * Constructor con valores
	 * @param idVisitante - El identificador del visitante respectivo. Debe existir un visitante con dicho identificador
	 * @param compañiaDomicilios - Compañía para la que trabaja el domiciliario
	 */
	public Domiciliario(String idVisitante, String compañiaDomicilios) 
	{
		this.idVisitante = idVisitante;
		this.compañiaDomicilios = compañiaDomicilios;
	}
	
	/**
	 * @return El idVisitante respectivo
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
	 * @return La compañiaDomicilios en la que trabaja el domiciliario
	 */
	public String getCompañiaDomicilios() 
	{
		return compañiaDomicilios;
	}

	/**
	 * @param compañiaDomicilios - La nueva compañia de domicilios del domiciliario 
	 */
	public void setCompañiaDomicilios(String compañiaDomicilios) 
	{
		this.compañiaDomicilios = compañiaDomicilios;
	}

	/**
	 * @return Una cadena de caracteres con la información del domiciliario
	 */
	@Override
	public String toString() 
	{
		return "Domiciliario [idVisitante=" + idVisitante + ", compañiaDomicilios=" + compañiaDomicilios + "]";
	}


}
