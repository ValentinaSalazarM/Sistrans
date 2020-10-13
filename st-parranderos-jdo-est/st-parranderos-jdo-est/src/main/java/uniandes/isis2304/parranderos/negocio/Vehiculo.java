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
	 * Identificador del propietario del vehículo. Debe existir en la tabla VISITANTE
	 */
	private String propietario;

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
		this.propietario = "";
	}

	/**
	 * Constructor con valores
	 * @param placa - La placa del vehículo
	 * @param caracteristicas - Las características del vehículo
	 * @param propietario - El identificador del visitante propietario del vehículo. Debe existir un visitante con dicho identificador
	 */
	public Vehiculo(String placa, String caracteristicas, String dueño) 
	{
		this.placa = placa;
		this.caracteristicas = caracteristicas;
		this.propietario = dueño;
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
	 * @return El identificador del propietario del vehículo
	 */
	public String getPropietario() 
	{
		return propietario;
	}

	/**
	 * @param propietario - El nuevo identificador del propietario del vehículo. Debe existir un visitante con dicho identificador
	 */
	public void setPropietario(String propietario) 
	{
		this.propietario = propietario;
	}

	/**
	 * @return Una cadena de caracteres con la información del vehículo
	 */
	@Override
	public String toString() 
	{
		return "Vehiculo [placa=" + placa + ", caracteristicas=" + caracteristicas + ", propietario=" + propietario + "]";
	}


}
