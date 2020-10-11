/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar la relación REGISTRANVEHICULO del negocio de Aforo-CCAndes:
 * Cada objeto de esta clase representa el hecho que un lector registró un vehículo.
 * Se modela mediante los identificadores del lector y del vehículo (placa) respectivamente
 * Debe existir un lector con el identificador dado
 * Debe existir un vehículo con la placa dada 
 * Adicionalmente contiene la hora de entrada y la hora de salida.
 * 
 */
public class RegistranVehiculo implements VORegistranVehiculo
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del lector que registra el carnet
	 */
	private String idLector;
	
	/**
	 * La placa del vehículo registrado
	 */
	private String placa;
	
	/**
	 * La hora en que se efectuó la lectura de entrada
	 */
	private Timestamp horaEntrada;
	
	/**
	 * La hora en que se efectuó la lectura de salida
	 */
	private Timestamp horaSalida;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public RegistranVehiculo() 
	{
		this.idLector = "";
		this.placa = "";
		this.horaEntrada = new Timestamp (0);
		this.horaSalida = new Timestamp (0);
	}

	/**
	 * Constructor con valores
	 * @param idLector - El identificador del lector. Debe existir un lector con dicho identificador
	 * @param placa - La placa del vehículo registrado. Debe existir un vehículo con dicha placa
	 * @param horaEntrada - La hora de entrada en la cual se realiza la lectura
	 * @param horaSalida - La hora de salida en la cual se realiza la lectura
	 */
	public RegistranVehiculo(String idLector, String placa, Timestamp horaEntrada, Timestamp horaSalida) 
	{
		this.idLector = idLector;
		this.placa = placa;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}

	
	/**
	 * @return El lector que realiza el registro
	 */
	public String getLector() 
	{
		return idLector;
	}

	/**
	 * @param lector - El nuevo lector. Debe existir un lector con dicho identificador
	 */
	public void setLector(String lector) 
	{
		this.idLector = lector;
	}

	/**
	 * @return La placa del vehículo registrado
	 */
	public String getPlaca() 
	{
		return placa;
	}

	/**
	 * @param placa - La nueva placa. Debe existir un vehículo con dicha placa
	 */
	public void setPlaca (String placa) 
	{
		this.placa = placa;
	}

	/**
	 * @return La hora de entrada registrada
	 */
	public Timestamp getHoraEntrada() 
	{
		return horaEntrada;
	}

	/**
	 * @param horaEntrada - La nueva hora de entrada registrada
	 */
	public void setHoraEntrada(Timestamp horaEntrada) 
	{
		this.horaEntrada = horaEntrada;
	}

	/**
	 * @return La horaSalida registrada
	 */
	public Timestamp getHoraSalida() 
	{
		return horaSalida;
	}

	/**
	 * @param horaSalida - La nueva hora de salida registrada
	 */
	public void setHoraSalida(Timestamp horaSalida) 
	{
		this.horaSalida = horaSalida;
	}

	/** 
	 * @return Una cadena con la información básica 
	 */
	@Override
	public String toString() 
	{
		return "RegistranVehiculo [idLector=" + idLector + ", placa=" + placa + ", horaEntrada="
				+ horaEntrada + ", horaSalida=" + horaSalida + "]";
	}
}
