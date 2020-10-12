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
 * Clase para modelar el concepto TIPOLOCAL del negocio de Aforo-CCAndes
 *
 */
public class TipoLocal implements VOTipoLocal
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del tipo de local
	 */
	private long id;

	/**
	 * El nombre del tipo de local
	 */
	private String tipo;

	/**
	 * Hora de apertura del tipo de local. Debe existir en la tabla HORARIO
	 */
	private int horaApertura;
	
	/**
	 * Minuto del horario de apertura del tipo de local. Debe existir en la tabla HORARIO
	 */
	private int minutoApertura;
	
	/**
	 * Hora de cierre del tipo de local. Debe existir en la tabla HORARIO
	 */
	private int horaCierre;

	/**
	 * Minuto del horario de cierre del tipo de local. Debe existir en la tabla HORARIO
	 */
	private int minutoCierre;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public TipoLocal() 
	{
		this.id = 0;
		this.tipo = "Default";
		this.horaApertura = 0;
		this.minutoApertura = 0;
		this.horaCierre = 0;
		this.minutoCierre = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de local
	 * @param tipo - El nombre del tipo de local
	 * @param horaApertura - Hora de apertura del local. Debe existir en la tabla HORARIO
	 * @param minutoApertura - Minuto de apertura del local. Debe existir en la tabla HORARIO
	 * @param horaCierre - Hora de cierre del local. Debe existir en la tabla HORARIO
	 * @param minutoCierre - Minuto cierre del local. Debe existir en la tabla HORARIO
	 */
	public TipoLocal(long id, String tipo, int horaApertura, int minutoApertura, int horaCierre, int minutoCierre) 
	{
		this.id = id;
		this.tipo = tipo;
		this.horaApertura = horaApertura;
		this.minutoApertura = minutoApertura;
		this.horaCierre = horaCierre;
		this.minutoCierre = minutoCierre;
	}

	/**
	 * @return El id del tipo de local
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del tipo de local
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del tipo de local
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo - El nuevo nombre del tipo de local
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}


	/**
	 * @return La hora de apertura del tipo de local
	 */
	public int getHoraApertura() 
	{
		return horaApertura;
	}

	/**
	 * @param horaApertura - La nueva hora de apertura del tipo de local. Debe existir en la tabla HORARIO
	 */
	public void setHoraApertura(int horaApertura) 
	{
		this.horaApertura = horaApertura;
	}
	
	/**
	 * @return El minuto del horario de apertura del tipo de local
	 */
	public int getMinutoApertura() 
	{
		return minutoApertura;
	}

	/**
	 * @param minutoApertura - El nuevo minuto del horario de apertura del tipo de local. Debe existir en la tabla HORARIO
	 */
	public void setMinutoApertura(int minutoApertura) 
	{
		this.minutoApertura = minutoApertura;
	}

	/**
	 * @return La hora de cierre del tipo de local. Debe existir en la tabla HORARIO
	 */
	public int getHoraCierre() 
	{
		return horaCierre;
	}

	/**
	 * @param horaCierre - La nueva hora de cierre del tipo de local
	 */
	public void setHoraCierre(int horaCierre) 
	{
		this.horaCierre = horaCierre;
	}
	
	/**
	 * @return El minuto del horario de cierre del tipo de local. Debe existir en la tabla HORARIO
	 */
	public int getMinutoCierre() 
	{
		return minutoCierre;
	}

	/**
	 * @param minutoCierre - El nuevo minuto del horario de cierre del tipo de local
	 */
	public void setMinutoCierre(int minutoCierre) 
	{
		this.minutoCierre = minutoCierre;
	}

	/**
	 * @return Una cadena de caracteres con la información del tipo de local
	 */
	@Override
	public String toString() 
	{
		return "TipoLocal [id=" + id + ", nombre=" + tipo + ", horaApertura= " + horaApertura + ", minutoApertura = " + minutoApertura + " horaCierre =" 
				+ horaCierre + "minutoCierre=" + minutoCierre + "]";
	}

	/**
	 * Define la igualdad de dos tipos de local
	 * @param tipoLocal - El TipoLocal a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipoLocal) 
	{
		TipoLocal tl = (TipoLocal) tipoLocal;
		return id == tl.id && tipo.equalsIgnoreCase (tl.tipo);
	}

}
