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
 * Clase para modelar el concepto LOCALCOMERCIAL del negocio de Aforo-CCAndes
 *
 */
public class LocalComercial implements VOLocalComercial
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del local comercial asignado por un individuo.
	 */
	private String identificador;
	
	/**
	 * El cupo actual del ascensor
	 */
	private int cupoActual;
	
	/**
	 * El identificador de la capacidad normal del local comercial. Debe existir en la tabla de CAPACIDADNORMAL
	 */
	private long capacidadNormal;
	
	/**
	 * El identificador del área del local comercial. Debe existir en la tabla de AREA
	 */
	private long area;
	
	/**
	 * El identificador del tipo de local. Debe exitir en la tabla de TIPOLOCAL
	 */
	private long tipoLocal;
	
	/**
	 * El identificador del centro comercial donde se localiza el local comercial. Debe existir en la tabla de CENTROCOMERCIAL
	 */
	private String idCentroComercial;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public LocalComercial() 
	{
		this.identificador = "";
		this.cupoActual = 0;
		this.capacidadNormal = 0;
		this.area = 0;
		this.tipoLocal = 0;
		this.idCentroComercial = "";
	}

	/**
	 * Constructor con valores
	 * @param identificador - El identificador del ascensor
	 * @param cupoActual - El cupo actual del ascensor
	 * @param area - El identificador del area
	 * @param capacidadNormal - El identificador de la capacidad normal
	 * @param tipoLocal - El peso máximo del ascensor (mayor a 0)
	 * @param idCentroComercial - El identificador del centro comercial
	 */
	public LocalComercial (String identificador, int cupoActual, long area, long capacidadNormal, long tipoLocal, String idCentroComercial) 
	{
		this.identificador = identificador;
		this.cupoActual = cupoActual;
		this.area = area;
		this.capacidadNormal = capacidadNormal;
		this.tipoLocal = tipoLocal;
		this.idCentroComercial = idCentroComercial;
	}

	
	/**
	 * @return El identificador del local comercial
	 */
	public String getIdentificador() 
	{
		return identificador;
	}

	/**
	 * @param identificador - El nuevo identificador del local comercial
	 */
	public void setIdentificador(String identificador) 
	{
		this.identificador = identificador;
	}

	/**
	 * @return El cupo actual del local comercial
	 */
	public int getCupoActual() 
	{
		return cupoActual;
	}

	/**
	 * @param cupoActual - El nuevo cupo actual del local comercial
	 */
	public void setCupoActual(int cupoActual) 
	{
		this.cupoActual = cupoActual;
	}

	/**
	 * @return El id de la capacidad normal del local comercial
	 */
	public long getCapacidadNormal() 
	{
		return capacidadNormal;
	}

	/**
	 * @param capacidadNormal - El nuevo identificador de la capacidad normal del ascensor
	 */
	public void setCapacidadNormal(long capacidadNormal) 
	{
		this.capacidadNormal = capacidadNormal;
	}

	/**
	 * @return El id del área del local comercial
	 */
	public long getArea() 
	{
		return area;
	}

	/**
	 * @param area - El nuevo identificador del área del local comercial
	 */
	public void setArea(long area) 
	{
		this.area = area;
	}

	/**
	 * @return El id del tipo de local
	 */
	public long getTipoLocal() 
	{
		return tipoLocal;
	}

	/**
	 * @param tipoLocal - El nuevo identificador del tipo de local
	 */
	public void setTipoLocal(long tipoLocal) 
	{
		this.tipoLocal = tipoLocal;
	}

	/**
	 * @return El id del centro comercial del local comercial
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
	 * @return Una cadena con la información básica del local comercial
	 */
	@Override
	public String toString() 
	{
		return "LocalComercial [identificador=" + identificador + ", cupoActual=" + cupoActual + ", capacidadNormal=" + capacidadNormal + ", area=" + area + ", tipoLocal=" + tipoLocal + ", idCentroComercial=" + idCentroComercial + "]";
	}

	
}
