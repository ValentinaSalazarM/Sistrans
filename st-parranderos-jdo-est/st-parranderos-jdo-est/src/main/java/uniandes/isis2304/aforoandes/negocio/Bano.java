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
 * Clase para modelar el concepto BAÑO del negocio de Aforo-CCAndes
 *
 */
public class Bano implements VOBano
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del baño asignado por un individuo.
	 */
	private String identificador;
	
	/**
	 * El identificador de la capacidad normal del baño. Debe existir en la tabla de CAPACIDADNORMAL
	 */
	private Long capacidadNormal;
	
	/**
	 * El identificador del área del baño. Debe existir en la tabla de AREA
	 */
	private Long area;
	
	/**
	 * El número de sanitarios del baño (mayor estricto a 0).
	 */
	private int sanitarios;
	
	/**
	 * El identificador del centro comercial donde se localiza el baño. Debe existir en la tabla de CENTROCOMERCIAL
	 */
	private String idCentroComercial;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Bano() 
	{
		this.identificador = "";
		this.capacidadNormal = null;
		this.area = null;
		this.sanitarios = 0;
		this.idCentroComercial = "";
	}

	/**
	 * Constructor con valores
	 * @param identificador - El identificador del baño
	 * @param area - El identificador del area. Debe existir un área con dicho identificador
	 * @param capacidadNormal - El identificador de la capacidad normal. Debe existir una capacidad normal con dicho identificador
	 * @param sanitarios - El número de sanitarios del baño (mayor a 0)
	 * @param idCentroComercial - El identificador del centro comercial. Debe existir un centro comercial con dicho identificador
	 */
	public Bano(String identificador, Long area, Long capacidadNormal, int sanitarios, String idCentroComercial) 
	{
		this.identificador = identificador;
		this.area = area;
		this.capacidadNormal = capacidadNormal;
		this.sanitarios = sanitarios;
		this.idCentroComercial = idCentroComercial;
	}

	
	/**
	 * @return El identificador del baño
	 */
	public String getIdentificador() 
	{
		return identificador;
	}

	/**
	 * @param identificador - El nuevo identificador del baño
	 */
	public void setIdentificador(String identificador) 
	{
		this.identificador = identificador;
	}

	/**
	 * @return El id de la capacidad normal del baño
	 */
	public Long getCapacidadNormal() 
	{
		return capacidadNormal;
	}

	/**
	 * @param capacidadNormal - El nuevo identificador de la capacidad normal del baño
	 */
	public void setCapacidadNormal(Long capacidadNormal) 
	{
		this.capacidadNormal = capacidadNormal;
	}

	/**
	 * @return El id del área del baño
	 */
	public Long getArea() 
	{
		return area;
	}

	/**
	 * @param area - El nuevo identificador del área del baño
	 */
	public void setArea(Long area) 
	{
		this.area = area;
	}

	/**
	 * @return El número de sanitarios en el baño
	 */
	public int getSanitarios() 
	{
		return sanitarios;
	}

	/**
	 * @param sanitarios - El nuevo número de sanitarios del baño
	 */
	public void setSanitarios(int sanitarios) 
	{
		this.sanitarios = sanitarios;
	}

	/**
	 * @return El id del centro comercial del baño
	 */
	public String getIdCentroComercial() 
	{
		return idCentroComercial;
	}

	/**
	 * @param idCentroComercial - El nuevo identificador del centro comercial
	 */
	public void setIdCentroComercial(String idCentroComercial) 
	{
		this.idCentroComercial = idCentroComercial;
	}

	/**
	 * @return Una cadena con la información básica del baño
	 */
	@Override
	public String toString() 
	{
		return "Bano [identificador=" + identificador + ", capacidadNormal=" + capacidadNormal + ", area=" + area + ", sanitarios=" + sanitarios + ", idCentroComercial=" + idCentroComercial + "]";
	}

	
}
