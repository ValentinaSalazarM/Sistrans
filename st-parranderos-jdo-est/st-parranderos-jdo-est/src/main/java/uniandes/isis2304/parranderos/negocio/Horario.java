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
 * Clase para modelar el concepto HORARIO del negocio de Aforo-CCAndes
 *
 */
public class Horario implements VOHorario
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La hora del horario
	 */
	private int hora;

	/**
	 * Los minutos del horario
	 */
	private int minutos;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Horario() 
	{
		this.hora = 0;
		this.minutos = 0;
	}

	/**
	 * Constructor con valores
	 * @param hora - La hora del horario
	 * @param minutos - Los minutos del horario
	 */
	public Horario(int hora, int minutos) 
	{
		this.hora = hora;
		this.minutos = minutos;
	}
	
	/**
	 * @return La hora del horario
	 */
	public int getHora() 
	{
		return hora;
	}

	/**
	 * @param hora - La nueva hora del horario
	 */
	public void setHora(int hora) 
	{
		this.hora = hora;
	}

	/**
	 * @return Los minutos del horario
	 */
	public int getMinutos() 
	{
		return minutos;
	}

	/**
	 * @param minutos - Los nuevos minutos del horario
	 */
	public void setMinutos(int minutos) 
	{
		this.minutos = minutos;
	}

	/**
	 * @return Una cadena de caracteres con la información del horario
	 */
	@Override
	public String toString() 
	{
		return "Horario [hora=" + hora + ", minutos=" + minutos + "]";
	}

}
