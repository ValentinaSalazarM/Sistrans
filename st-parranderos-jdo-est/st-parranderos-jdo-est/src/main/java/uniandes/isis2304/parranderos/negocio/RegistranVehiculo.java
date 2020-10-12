/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

/**
 * Clase para modelar la relación REGISTRANVEHICULO del negocio de Aforo-CCAndes:
 * Cada objeto de esta clase representa el hecho que un lector registró un vehículo.
 * Se modela mediante los identificadores del lector y del vehículo (placa) respectivamente
 * Debe existir un lector con el identificador dado
 * Debe existir un vehículo con la placa dada 
 * Adicionalmente contiene la fecha de la visita, la hora de entrada y la hora de salida.
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
	 * Fecha en la que se realizó la visita
	 */
	private Date fecha;
	
	/**
	 * Identificador del horario en que se efectuó la lectura de entrada. Debe existir en la tabla HORARIO
	 */
	private long horaEntrada;
	
	/**
	 * Identificador del horario en que se efectuó la lectura de salida. Debe existir en la tabla HORARIO
	 */
	private long horaSalida;


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
		this.fecha = new Date(0);
		this.horaEntrada = 0;
		this.horaSalida = 0;

	}

	/**
	 * Constructor con valores
	 * @param idLector - El identificador del lector. Debe existir un lector con dicho identificador
	 * @param placa - La placa del vehículo registrado. Debe existir un vehículo con dicha placa
	 * @param fecha - Fecha en la que se efectuó la visita
	 * @param horaEntrada - El identificador del horario en el cual se realiza la lectura de entrada. Debe existir un horario con dicho identificador
	 * @param horaSalida -  El identificador del horario en el cual se realiza la lectura de salida. Debe existir un horario con dicho identificador
	 */
	public RegistranVehiculo(String idLector, String placa, Date fecha, long horaEntrada, long horaSalida) 
	{
		this.idLector = idLector;
		this.placa = placa;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}

	
	/**
	 * @return El lector que realiza el registro
	 */
	public String getIdLector() 
	{
		return idLector;
	}

	/**
	 * @param lector - El nuevo lector. Debe existir un lector con dicho identificador
	 */
	public void setIdLector(String lector) 
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
	 * @return La fecha en la que se realizó la visita
	 */
	public Date getFecha() 
	{
		return fecha;
	}

	/**
	 * @param fecha - La nueva fecha en la que se registra la visitia
	 */
	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}

	/**
	 * @return El identificador del horario en el cual se realiza la lectura de entrada
	 */
	public long getHoraEntrada() 
	{
		return horaEntrada;
	}

	/**
	 * @param horaEntrada - El nuevo identificador del horario en el que se realizó la lectura de entrada. Debe existir un horario con dicho identificador
	 */
	public void setHoraEntrada(long horaEntrada) 
	{
		this.horaEntrada = horaEntrada;
	}

	/**
	 * @return El identificador del horario en el cual se realiza la lectura de salida
	 */
	public long getHoraSalida() 
	{
		return horaSalida;
	}

	/**
	 * @param horaSalida - El nuevo identificador del horario en el que se realizó la lectura de salida. Debe existir un horario con dicho identificador
	 */
	public void setHoraSalida(long horaSalida) 
	{
		this.horaSalida = horaSalida;
	}
	
	/** 
	 * @return Una cadena con la información básica 
	 */
	@Override
	public String toString() 
	{
		return "RegistranVehiculo [idLector=" + idLector + ", placa=" + placa + ", fecha=" + fecha + ", horaEntrada="
				+ horaEntrada + ", horaSalida=" + horaSalida + "]";
	}
}
