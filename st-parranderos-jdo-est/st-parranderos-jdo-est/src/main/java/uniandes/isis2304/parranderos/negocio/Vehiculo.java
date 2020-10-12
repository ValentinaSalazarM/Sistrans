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
 * Clase para modelar el concepto VEHICULO del negocio de Aforo-CCAndes
 *
 */
public class Vehiculo implements VOVehiculo
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La placa identificativa del vehículo
	 */
	private String placa;

	/**
	 * Las características descriptivas del vehículo
	 */
	private String caracteristicas;
	
	/**
	 * Identificador del dueño del vehículo
	 */
	private String dueño;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Vehiculo() 
	{
		this.placa = "";
		this.caracteristicas = "";
		this.dueño = "";
	}

	/**
	 * Constructor con valores
	 * @param placa - La placa del vehículo
	 * @param caracteristicas - Las características del vehículo
	 * @param dueño - El identificador del visitante dueño del vehículo
	 */
	public Vehiculo(String placa, String caracteristicas, String dueño) 
	{
		this.placa = placa;
		this.caracteristicas = caracteristicas;
		this.dueño = dueño;
	}
	
	/**
	 * @return La placa del vehículo
	 */
	public String getPlaca() 
	{
		return placa;
	}

	/**
	 * @param placa - La nueva placa del vehículo
	 */
	public void setPlaca(String placa) 
	{
		this.placa = placa;
	}

	/**
	 * @return Las caracteristicas del vehículo
	 */
	public String getCaracteristicas() 
	{
		return caracteristicas;
	}

	/**
	 * @param caracteristicas - Las nuevas caracteristicas del vehículo
	 */
	public void setCaracteristicas(String caracteristicas) 
	{
		this.caracteristicas = caracteristicas;
	}

	/**
	 * @return El identificador del dueño del vehículo
	 */
	public String getDueño() 
	{
		return dueño;
	}

	/**
	 * @param dueño - El nuevo identificador del dueño del vehículo
	 */
	public void setDueño(String dueño) 
	{
		this.dueño = dueño;
	}

	/**
	 * @return Una cadena de caracteres con la información del vehículo
	 */
	@Override
	public String toString() 
	{
		return "Vehiculo [placa=" + placa + ", caracteristicas=" + caracteristicas + ", dueño=" + dueño + "]";
	}


}
