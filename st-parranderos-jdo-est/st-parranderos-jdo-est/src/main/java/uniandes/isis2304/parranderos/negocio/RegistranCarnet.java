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
 * Adicionalmente contiene la hora de entrada y la hora de salida.
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
	public RegistranCarnet() 
	{
		this.idLector = "";
		this.tipoCarnet = 0;
		this.idVisitante = "";
		this.horaEntrada = new Timestamp (0);
		this.horaSalida = new Timestamp (0);
	}

	/**
	 * Constructor con valores
	 * @param lector - El identificador del lector. Debe existir un lector con dicho identificador
	 * @param tipoCarnet - El tipo de carnet. Debe exixtir un carnet con dicho tipo
	 * @param idVisitante - El identificador del visitante dueño del carnet. Debe existir un carnet con dicho dueño.
	 * @param horaEntrada - La hora de entrada en la cual se realiza la lectura
	 * @param horaSalida - La hora de salida en la cual se realiza la lectura
	 */
	public RegistranCarnet(String lector, long tipoCarnet, String idVisitante, Timestamp horaEntrada, Timestamp horaSalida) 
	{
		this.idLector = lector;
		this.tipoCarnet = tipoCarnet;
		this.idVisitante = idVisitante;
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
	public String getVisitante() 
	{
		return idVisitante;
	}

	/**
	 * @param visitante - El nuevo visitante. Debe existir un visitante con dicho identificador
	 */
	public void setVisitante(String visitante) 
	{
		this.idVisitante = visitante;
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
		return "RegistranCarnet [idLector=" + idLector + ", tipoCarnet=" + tipoCarnet + ", idVisitante=" + idVisitante + ", horaEntrada="
				+ horaEntrada + ", horaSalida=" + horaSalida + "]";
	}
}
