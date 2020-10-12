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
	 * Indica si el establecimiento se encuentra en funcionamiento
	 */
	private boolean activo;
	
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
		this.capacidadNormal = 0;
		this.area = 0;
		this.tipoLocal = 0;
		this.idCentroComercial = "";
		activo = true;
	}

	/**
	 * Constructor con valores
	 * @param identificador - El identificador del local comercial
	 * @param area - El identificador del area. Debe existir un área con dicho identificador
	 * @param capacidadNormal - El identificador de la capacidad normal. Debe existir una capacidad normal con dicho identificador
	 * @param tipoLocal - El identificador del tipo de local. Debe existir un tipo de local con dicho identificador 
	 * @param idCentroComercial - El identificador del centro comercial. Debe existir un centro comercial con dicho identificador
	 * @param activo - Estado de funcionamiento del local comercial
	 */
	public LocalComercial (String identificador, long area, long capacidadNormal, long tipoLocal, String idCentroComercial, boolean activo) 
	{
		this.identificador = identificador;
		this.area = area;
		this.capacidadNormal = capacidadNormal;
		this.tipoLocal = tipoLocal;
		this.activo = activo;
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
	 * @return El estado de funcionamiento del local comercial
	 */
	public boolean getActivo() 
	{
		return activo;
	}

	/**
	 * @param activo - El nuevo estado de funcionamiento del local comercial
	 */
	public void setActivo(boolean activo) 
	{
		this.activo = activo;
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
		return "LocalComercial [identificador=" + identificador + ", capacidadNormal=" + capacidadNormal + ", area=" + area + ", tipoLocal=" + tipoLocal + ", activo = " + activo + ", idCentroComercial=" + idCentroComercial + "]";
	}

	
}
