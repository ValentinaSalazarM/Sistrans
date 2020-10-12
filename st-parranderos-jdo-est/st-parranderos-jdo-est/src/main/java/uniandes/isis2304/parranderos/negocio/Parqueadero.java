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
public class Parqueadero implements VOParqueadero 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del parqueadero asignado por un individuo.
	 */
	private String identificador;
	
	/**
	 * El identificador de la capacidad normal del parqueadero. Debe existir en la tabla de CAPACIDADNORMAL
	 */
	private long capacidadNormal;
	
	/**
	 * El identificador del área del parqueadero. Debe existir en la tabla de AREA
	 */
	private long area;
	
	/**
	 * El identificador del centro comercial donde se localiza el parqueadero. Debe existir en la tabla de CENTROCOMERCIAL
	 */
	private String idCentroComercial;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Parqueadero() 
	{
		this.identificador = "";
		this.capacidadNormal = 0;
		this.area = 0;
		this.idCentroComercial = "";
	}

	/**
	 * Constructor con valores
	 * @param identificador - El identificador del ascensor
	 * @param area - El identificador del area. Debe existir un área con dicho identificador
	 * @param capacidadNormal - El identificador de la capacidad normal. Debe existir una capacidad normal con dicho identificador
	 * @param idCentroComercial - El identificador del centro comercial. Debe existir un centro comercial con dicho identificador
	 */
	public Parqueadero(String identificador, long area, long capacidadNormal, String idCentroComercial) 
	{
		this.identificador = identificador;
		this.area = area;
		this.capacidadNormal = capacidadNormal;
		this.idCentroComercial = idCentroComercial;
	}

	
	/**
	 * @return El identificador del parqueadero
	 */
	public String getIdentificador() 
	{
		return identificador;
	}

	/**
	 * @param identificador - El nuevo identificador del parqueadero
	 */
	public void setIdentificador(String identificador) 
	{
		this.identificador = identificador;
	}

	/**
	 * @return El id de la capacidad normal del parqueadero
	 */
	public long getCapacidadNormal() 
	{
		return capacidadNormal;
	}

	/**
	 * @param capacidadNormal - El nuevo identificador de la capacidad normal del parqueadero
	 */
	public void setCapacidadNormal(long capacidadNormal) 
	{
		this.capacidadNormal = capacidadNormal;
	}

	/**
	 * @return El id del área del parqueadero
	 */
	public long getArea() 
	{
		return area;
	}

	/**
	 * @param area - El nuevo identificador del área del parqueadero
	 */
	public void setArea(long area) 
	{
		this.area = area;
	}

	/**
	 * @return El id del centro comercial del parqueadero
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
	 * @return Una cadena con la información básica del parqueadero
	 */
	@Override
	public String toString() 
	{
		return "Parqueadero [identificador=" + identificador + ", capacidadNormal=" + capacidadNormal + ", area=" + area + ", idCentroComercial=" + idCentroComercial + "]";
	}

	
}
