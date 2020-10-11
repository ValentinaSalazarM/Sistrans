/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto LECTOR del negocio de Aforo-CCAndes:
 * 
 */
public class Lector implements VOLector
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * El identificador ÚNICO del lector
	 */
	private long id;
	
	/**
	 * El identificador del centro comercial donde se localiza el lector
	 */
	private String idCentroComercial;

	/**
	 * El identificador del local comercial donde se localiza el lector
	 */
	private String idLocalComercial;

	/**
	 * El identificador del baño donde se localiza el lector
	 */
	private String idBaño;

	/**
	 * El identificador del ascensor donde se localiza el lector
	 */
	private String idAscensor;

	/**
	 * El identificador del parqueadero donde se localiza el lector
	 */
	private String idParqueadero;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Lector() 
	{
		this.id = 0;
		this.idCentroComercial = "";
		this.idLocalComercial = "";
		this.idBaño = "";
		this.idAscensor = "";
		this.idParqueadero = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del lector
	 * @param idCentroComercial - El identificador del centro comercial. Debe existir un centro comercial con dicho identificador si es diferente de NULL
	 * @param idLocalComercial - El identificador del local comercial. Debe existir un local comercial con dicho identificador si es diferente de NULL
	 * @param idBaño - El identificador del baño. Debe existir un baño con dicho identificador si es diferente de NULL
	 * @param idAscensor - El identificador del ascensor. Debe existir ascensor con dicho identificador si es diferente de NULL
	 * @param idParqueadero - El identificador del parqueadero. Debe existir un parqueadero con dicho identificador si es diferente de NULL
	 */
	public Lector(long id, String idCentroComercial, String idLocalComercial, String idBaño, String idAscensor, String idParqueadero) 
	{
		this.id = id;
		this.idCentroComercial = idCentroComercial;
		this.idLocalComercial = idLocalComercial;
		this.idBaño = idBaño;
		this.idAscensor = idAscensor;
		this.idParqueadero = idParqueadero;
	}

	
	/**
	 * @return El id del lector
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del lector
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El id del centro comercial donde se localiza el lector
	 */
	public String getIdCentroComercial() 
	{
		return idCentroComercial;
	}

	/**
	 * @param idCentroComercial - El nuevo id del centro comercial donde se localiza el lector
	 */
	public void setIdCentroComercial(String idCentroComercial) 
	{
		this.idCentroComercial = idCentroComercial;
	}

	/**
	 * @return El id del local comercial donde se localiza el lector
	 */
	public String getIdLocalComercial() 
	{
		return idLocalComercial;
	}

	/**
	 * @param idLocalComercial - El nuevo id del local comercial donde se localiza el lector
	 */
	public void setIdLocalComercial(String idLocalComercial) 
	{
		this.idLocalComercial = idLocalComercial;
	}

	/**
	 * @return El id del baño del centro comercial
	 */
	public String getIdBaño() 
	{
		return idBaño;
	}

	/**
	 * @param idBaño - El nuevo id del baño donde se localiza el lector
	 */
	public void setIdBaño(String idBaño) 
	{	
		this.idBaño = idBaño;
	}

	/**
	 * @return El id del ascensor donde se localiza el lector
	 */
	public String getIdAscensor() 
	{
		return idAscensor;
	}

	/**
	 * @param idAscensor - El nuevo id del ascensor donde se localiza el lector
	 */
	public void setIdAscensor(String idAscensor) 
	{
		this.idAscensor = idAscensor;
	}

	/**
	 * @return El id del parqueadero donde se localiza el lector
	 */
	public String getIdParqueadero() 
	{
		return idParqueadero;
	}

	/**
	 * @param idParqueadero - El nuevo id del parqueadero donde se localiza el lector
	 */
	public void setIdParqueadero(String idParqueadero) 
	{
		this.idParqueadero = idParqueadero;
	}

	/** 
	 * @return Una cadena con la información básica del lector
	 */
	@Override
	public String toString() 
	{
		return "Lector [id" + id + ", idCentroComercial=" + idCentroComercial + ", idLocalComercial=" + idLocalComercial + 
				", idBaño="+ idBaño + ", idAscensor=" + idAscensor +", idParqueadero=" + idParqueadero + "]";
	}
	
}
