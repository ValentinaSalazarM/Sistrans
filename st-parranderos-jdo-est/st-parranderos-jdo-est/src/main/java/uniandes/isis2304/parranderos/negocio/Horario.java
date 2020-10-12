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
	 * Identificador ÚNICO del horario
	 */
	private long id;
	/**
	 * La hora del horario
	 */
	private int hora;

	/**
	 * El minuto del horario
	 */
	private int minuto;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Horario() 
	{
		this.hora = 0;
		this.minuto = 0;
	}

	/**
	 * Constructor con valores
	 * @param hora - La hora del horario
	 * @param minuto - El minuto del horario
	 */
	public Horario(int hora, int minutos) 
	{
		this.hora = hora;
		this.minuto = minutos;
	}
	
	/**
	 * @return El identificador del horario
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo identificador del horario
	 */
	public void setId(long id) 
	{
		this.id = id;
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
	 * @return El minuto del horario
	 */
	public int getMinuto() 
	{
		return minuto;
	}

	/**
	 * @param minuto - El nuevo minuto del horario
	 */
	public void setMinuto(int minuto) 
	{
		this.minuto = minuto;
	}

	/**
	 * @return Una cadena de caracteres con la información del horario
	 */
	@Override
	public String toString() 
	{
		return "Horario [id= " + id + ", hora=" + hora + ", minuto=" + minuto + "]";
	}

}
