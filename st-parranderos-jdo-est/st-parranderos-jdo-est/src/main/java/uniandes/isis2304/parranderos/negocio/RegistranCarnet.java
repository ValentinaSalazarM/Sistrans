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
 * Clase para modelar la relación REGISTRANCARNET del negocio de Aforo-CCAndes:
 * Cada objeto de esta clase representa el hecho que un lector registró un carnet.
 * Se modela mediante los identificadores del lector y del carnet (tipoCarnet y Visitante) respectivamente
 * Debe existir un lector con el identificador dado
 * Debe existir un carnet con el tipoCarnet y el visitante dado 
 * Adicionalmente contiene la fecha de la visita, la hora de entrada y la hora de salida.
 * 
 */
public class RegistranCarnet implements VORegistranCarnet
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del lector que registra el carnet
	 */
	private String idLector;
	
	/**
	 * El tipo del carnet registrado
	 */
	private long tipoCarnet;
	
	/**
	 * El identificador del visitante al que le pertenece el carnet
	 */
	private String idVisitante;
	
	/**
	 * Fecha en la que se realizó la visita
	 */
	private Timestamp fecha;
	
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
	public RegistranCarnet() 
	{
		this.idLector = "";
		this.tipoCarnet = 0;
		this.idVisitante = "";
		this.fecha = new Timestamp(0);
		this.horaEntrada = 0;
		this.horaSalida = 0;
	}

	/**
	 * Constructor con valores
	 * @param lector - El identificador del lector. Debe existir un lector con dicho identificador
	 * @param tipoCarnet - El tipo de carnet. Debe exixtir un carnet con dicho tipo
	 * @param idVisitante - El identificador del visitante dueño del carnet. Debe existir un carnet con dicho dueño.
	 * @param fecha - Fecha en la que se efectuó la visita
	 * @param horaEntrada - El identificador del horario en el cual se realiza la lectura de entrada. Debe existir un horario con dicho identificador
	 * @param horaSalida -  El identificador del horario en el cual se realiza la lectura de salida. Debe existir un horario con dicho identificador
	 */
	public RegistranCarnet(String lector, long tipoCarnet, String idVisitante, Timestamp fecha, long horaEntrada, long horaSalida) 
	{
		this.idLector = lector;
		this.tipoCarnet = tipoCarnet;
		this.idVisitante = idVisitante;
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
	 * @return El tipo del carnet registrado
	 */
	public long getTipoCarnet() 
	{
		return tipoCarnet;
	}

	/**
	 * @param tipoCarnet - El nuevo tipoCarnet. Debe existir un carnet con dicho identificador de tipo
	 */
	public void setTipoCarnet(long tipoCarnet) 
	{
		this.tipoCarnet = tipoCarnet;
	}

	/**
	 * @return El visitante asociado al carnet registrado
	 */
	public String getIdVisitante() 
	{
		return idVisitante;
	}

	/**
	 * @param visitante - El nuevo visitante. Debe existir un visitante con dicho identificador
	 */
	public void setIdVisitante(String visitante) 
	{
		this.idVisitante = visitante;
	}

	/**
	 * @return La fecha en la que se realizó la visita
	 */
	public Timestamp getFecha() 
	{
		return fecha;
	}

	/**
	 * @param fecha - La nueva fecha en la que se registra la visitia
	 */
	public void setFecha(Timestamp fecha) 
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
		return "RegistranCarnet [idLector=" + idLector + ", tipoCarnet=" + tipoCarnet + ", idVisitante=" + idVisitante + ", fecha=" + fecha + ", horaEntrada="
				+ horaEntrada + ", horaSalida=" + horaSalida + "]";
	}
}
