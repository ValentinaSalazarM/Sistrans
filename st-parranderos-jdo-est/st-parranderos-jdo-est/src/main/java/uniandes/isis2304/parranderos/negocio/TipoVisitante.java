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
 * Clase para modelar el concepto TIPOVISITANTE del negocio de Aforo-CCAndes
 *
 */
public class TipoVisitante implements VOTipoVisitante
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del tipo de visitante
	 */
	private long id;

	/**
	 * El nombre del tipo de visitante
	 */
	private String tipo;

	/**
	 * Hora de inicio del horario válido del tipo de visitante
	 */
	private int horaInicio;

	/**
	 * Minuto de inicio del horario válido del tipo de visitante
	 */
	private int minutoInicio;
	
	/**
	 * Hora de límite de circulación del tipo de visitante
	 */
	private int horaLimite;
	
	/**
	 * Minuto del horario límite de circulación del tipo de visitante
	 */
	private int minutoLimite;
	
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public TipoVisitante() 
	{
		this.id = 0;
		this.tipo = "Default";
		this.horaInicio = 0;
		this.minutoInicio = 0;
		this.horaLimite = 0;
		this.minutoLimite = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de visitante
	 * @param tipo - El nombre del tipo de visitante
	 * @param horaInicio - Hora de inicio del horario válido del tipo de visitante
	 * @param minutoInicio - Minuto de inicio del horario válido del tipo de visitante
	 * @param horaLimite - Hora límite de circulación del tipo de visitante
	 * @param minutoLimite - Minuto límite de circulación del tipo de visitante 
	 */
	public TipoVisitante(long id, String tipo, int horaInicio, int minutoInicio, int horaLimite, int minutoLimite) 
	{
		this.id = id;
		this.tipo = tipo;
		this.horaInicio = horaInicio;
		this.minutoInicio = minutoInicio;
		this.horaLimite = horaLimite;
		this.minutoLimite = minutoLimite;
	}

	/**
	 * @return El id del tipo de visitante
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del tipo de visitante
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del tipo de visitante
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo - El nuevo nombre del tipo de visitante
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	/**
	 * @return La hora inicio del horario válido del tipo de visitante
	 */
	public int getHoraInicio() 
	{
		return horaLimite;
	}

	/**
	 * @param horaInicio - La nueva hora de inicio del horario válido del tipo de visitante
	 */
	public void setHoraInicio(int horaInicio) 
	{
		this.horaLimite = horaInicio;
	}

	/**
	 * @return El minuto de inicio de circulación del tipo de visitante
	 */
	public int getMinutoInicio() 
	{
		return minutoInicio;
	}

	/**
	 * @param minutoInicio - El nuevo minuto de inicio de circulación del tipo de visitante
	 */
	public void setMinutoInicio(int minutoInicio) 
	{
		this.minutoInicio = minutoInicio;
	}	
	
	/**
	 * @return La hora límite de circulación del tipo de visitante
	 */
	public int getHoraLimite() 
	{
		return horaLimite;
	}

	/**
	 * @param horaLimite - La nueva hora límite del tipo de visitante
	 */
	public void setHoraLimite(int horaLimite) 
	{
		this.horaLimite = horaLimite;
	}

	/**
	 * @return El minuto límite de circulación del tipo de visitante
	 */
	public int getMinutoLimite() 
	{
		return minutoLimite;
	}

	/**
	 * @param minutoLimite - El nuevo minuto límite de circulación del tipo de visitante
	 */
	public void setMinutoLimite(int minutoLimite) 
	{
		this.minutoLimite = minutoLimite;
	}

	
	/**
	 * @return Una cadena de caracteres con la información del tipo de visitante
	 */
	@Override
	public String toString() 
	{
		return "TipoVisitante [id=" + id + ", nombre=" + tipo + ", horaLimite= " + horaLimite + "]";
	}

	/**
	 * Define la igualdad de dos tipos de visitante
	 * @param tipoVisitante - El TipoVisitante a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipoVisitante) 
	{
		TipoVisitante tl = (TipoVisitante) tipoVisitante;
		return id == tl.id && tipo.equalsIgnoreCase (tl.tipo);
	}

}
