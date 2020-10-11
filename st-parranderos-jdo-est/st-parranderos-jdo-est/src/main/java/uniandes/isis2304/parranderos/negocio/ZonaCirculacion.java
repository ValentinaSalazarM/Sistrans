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
 * Clase para modelar el concepto ZONACIRCULACION del negocio de Aforo-CCAndes
 *
 */
public class ZonaCirculacion implements VOZonaCirculacion
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de la zona de circulación asignado por un individuo.
	 */
	private String identificador;
	
	/**
	 * Capacidad normal de la zona de circulación.
	 */
	private int capacidadNormal;
	
	/**
	 * El identificador del centro comercial donde se localiza la zona de circulación. Debe existir en la tabla de CENTROCOMERCIAL
	 */
	private String idCentroComercial;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ZonaCirculacion() 
	{
		this.identificador = "";
		this.capacidadNormal = 0;
		this.idCentroComercial = "";
	}

	/**
	 * Constructor con valores
	 * @param identificador - El identificador del baño
	 * @param capacidadNormal - La capacidad normal de la zona de circulación.
	 * @param idCentroComercial - El identificador del centro comercial. Debe existir un centro comercial con dicho identificador
	 */
	public ZonaCirculacion(String identificador, int capacidadNormal, String idCentroComercial) 
	{
		this.identificador = identificador;
		this.capacidadNormal = capacidadNormal;
		this.idCentroComercial = idCentroComercial;
	}

	
	/**
	 * @return El identificador de la zona de circulación
	 */
	public String getIdentificador() 
	{
		return identificador;
	}

	/**
	 * @param identificador - El nuevo identificador de la zona de circulación
	 */
	public void setIdentificador(String identificador) 
	{
		this.identificador = identificador;
	}


	/**
	 * @return La capacidad normal de la zona de circulación
	 */
	public int getCapacidadNormal() 
	{
		return capacidadNormal;
	}

	/**
	 * @param capacidadNormal - La nueva capacidad normal de la zona de circulación
	 */
	public void setCapacidadNormal(int capacidadNormal) 
	{
		this.capacidadNormal = capacidadNormal;
	}

	/**
	 * @return El id del centro comercial de la zona de circulación
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
	 * @return Una cadena con la información básica de la zona de circulación
	 */
	@Override
	public String toString() 
	{
		return "ZonaCirculacion [identificador=" + identificador + ", capacidadNormal=" + capacidadNormal + ", idCentroComercial=" + idCentroComercial + "]";
	}

	
}
