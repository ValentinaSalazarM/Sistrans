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
 * Clase para modelar el concepto ASCENSOR del negocio de Aforo-CCAndes
 *
 */
public class Ascensor implements VOAscensor
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del ascensor asignado por un individuo.
	 */
	private String identificador;
	
	/**
	 * El identificador de la capacidad normal del ascensor. Debe existir en la tabla de CAPACIDADNORMAL
	 */
	private long capacidadNormal;
	
	/**
	 * El identificador del área del ascensor. Debe existir en la tabla de AREA
	 */
	private long area;
	
	/**
	 * El peso maximo del ascensor (mayor estricto a 0).
	 */
	private double pesoMaximo;
	
	/**
	 * El identificador del centro comercial donde se localiza el ascensor. Debe existir en la tabla de CENTROCOMERCIAL
	 */
	private String idCentroComercial;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Ascensor() 
	{
		this.identificador = "";
		this.capacidadNormal = 0;
		this.area = 0;
		this.pesoMaximo = 0;
		this.idCentroComercial = "";
	}

	/**
	 * Constructor con valores
	 * @param identificador - El identificador del ascensor
	 * @param area - El identificador del area. Debe existir un área con dicho identificador
	 * @param capacidadNormal - El identificador de la capacidad normal. Debe existir una capacidad normal con dicho identificador
	 * @param pesoMaximo - El peso máximo del ascensor (mayor a 0)
	 * @param idCentroComercial - El identificador del centro comercial. Debe existir un centro comercial con dicho identificador.
	 */
	public Ascensor(String identificador, long area, long capacidadNormal, double pesoMaximo, String idCentroComercial) 
	{
		this.identificador = identificador;
		this.area = area;
		this.capacidadNormal = capacidadNormal;
		this.pesoMaximo = pesoMaximo;
		this.idCentroComercial = idCentroComercial;
	}

	
	/**
	 * @return El identificador del ascensor
	 */
	public String getIdentificador() 
	{
		return identificador;
	}

	/**
	 * @param identificador - El nuevo identificador del ascensor
	 */
	public void setIdentificador(String identificador) 
	{
		this.identificador = identificador;
	}

	/**
	 * @return El id de la capacidad normal del ascensor
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
	 * @return El id del área del ascensor
	 */
	public long getArea() 
	{
		return area;
	}

	/**
	 * @param area - El nuevo identificador del área del ascensor
	 */
	public void setArea(long area) 
	{
		this.area = area;
	}

	/**
	 * @return El peso máximo del ascensor
	 */
	public double getPesoMaximo() 
	{
		return pesoMaximo;
	}

	/**
	 * @param pesoMaximo - El nuevo peso máximo del ascensor
	 */
	public void setPesoMaximo(double pesoMaximo) 
	{
		this.pesoMaximo = pesoMaximo;
	}

	/**
	 * @return El id del centro comercial del ascensor
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
	 * @return Una cadena con la información básica del ascensor
	 */
	@Override
	public String toString() 
	{
		return "Ascensor [identificador=" + identificador + ", capacidadNormal=" + capacidadNormal + ", area=" + area + ", pesoMaximo=" + pesoMaximo + ", idCentroComercial=" + idCentroComercial + "]";
	}

	
}
