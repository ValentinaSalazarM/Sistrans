/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.negocio;

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
	 * Identificador del horario de inicio válido del tipo de visitante. Debe existir en la tabla HORARIO
	 */
	private long horaInicio;
	
	/**
	 * Identificador del horario límite de circulación del tipo de visitante. Debe existir en la tabla HORARIO
	 */
	private long horaLimite;
	
	
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
		this.horaLimite = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de visitante
	 * @param tipo - El nombre del tips de visitante
	 * @param horaInicio - Identificador del horario de inicio de circulación del tipo de visitante. Debe existir un horario con dicho identificador
	 * @param horaLimite - Identificador del horario límite de circulación del tipo de visitante. Debe existir un horario con dicho identificador
	 */
	public TipoVisitante(long id, String tipo, long horaInicio, long horaLimite) 
	{
		this.id = id;
		this.tipo = tipo;
		this.horaInicio = horaInicio;
		this.horaLimite = horaLimite;
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
	 * @return El identificador del horario de inicio válido del tipo de visitante. 
	 */
	public long getHoraInicio() 
	{
		return horaInicio;
	}

	/**
	 * @param horaInicio - El nuevo identificador del horario de inicio válido del tipo de visitante. Debe existir un horario con dicho identificador
	 */
	public void setHoraInicio(long horaInicio) 
	{
		this.horaInicio = horaInicio;
	}
	
	/**
	 * @return El identificador del horario límite de circulación 
	 */
	public long getHoraLimite() 
	{
		return horaLimite;
	}

	/**
	 * @param horaLimite - El nuevo identificador del horario límite de circulación del tipo de visitante. Debe existir un horario con dicho identificador
	 */
	public void setHoraLimite(long horaLimite) 
	{
		this.horaLimite = horaLimite;
	}

	/**
	 * @return Una cadena de caracteres con la información del tipo de visitante
	 */
	@Override
	public String toString() 
	{
		return "TipoVisitante [id=" + id + ", tipo=" + tipo + ", horaInicio=" + horaInicio + ", horaLimite= " + horaLimite + "]";
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
