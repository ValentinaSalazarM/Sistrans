/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.negocio;

public class RFC2Hora implements VOLocalComercial
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
	private Long capacidadNormal;
	
	/**
	 * El identificador del área del local comercial. Debe existir en la tabla de AREA
	 */
	private Long area;
	
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

	/**
	 * La hora de la visita consultada
	 */
	private int hora;
	
	/**
	 * El minuto de la visita consultada
	 */
	private int minuto;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Constructor por defecto
	 */
	public RFC2Hora() 
	{
		this.identificador = "";
		this.capacidadNormal = null;
		this.area = null;
		this.tipoLocal = 0;
		this.idCentroComercial = "";
		activo = true;
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
	public Long getCapacidadNormal() 
	{
		return capacidadNormal;
	}

	/**
	 * @param capacidadNormal - El nuevo identificador de la capacidad normal del ascensor
	 */
	public void setCapacidadNormal(Long capacidadNormal) 
	{
		this.capacidadNormal = capacidadNormal;
	}

	/**
	 * @return El id del área del local comercial
	 */
	public Long getArea() 
	{
		return area;
	}

	/**
	 * @param area - El nuevo identificador del área del local comercial
	 */
	public void setArea(Long area) 
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
	 * @return La hora de las visitas realizadas por el visitante
	 */
	public int getHora() 
	{
		return hora;
	}

	/**
	 * @param hora - La nueva hora de las visitas realizadas por el visitante
	 */
	public void setHora(int hora) 
	{
		this.hora = hora;
	}

	/**
	 * @return El minuto de las visitas realizadas por el visitante
	 */
	public int getMinuto() 
	{
		return minuto;
	}

	/**
	 * @param hora - El nuevo minuto de las visitas realizadas por el visitante
	 */
	public void setMinuto(int minuto) 
	{
		this.minuto = minuto;
	}
}
