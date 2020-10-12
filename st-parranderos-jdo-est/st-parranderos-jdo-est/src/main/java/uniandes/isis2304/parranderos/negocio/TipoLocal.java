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
	 * Identificador del horario de apertura del tipo de local. Debe existir en la tabla HORARIO
	 */
	private long horaApertura;
	
	/**
	 * Identificador del horario cierre del tipo de local. Debe existir en la tabla HORARIO
	 */
	private long horaCierre;

	
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
		this.horaCierre = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de local
	 * @param tipo - El nombre del tipo de local
	 * @param horaApertura - Identificador del horario de apertura del local. Debe existir un horario con dicho identificador
	 * @param horaCierre - Identificador del horario de cierre del local. Debe existir un horario con dicho identificador
	 */
	public TipoLocal(long id, String tipo, long horaApertura, long horaCierre) 
	{
		this.id = id;
		this.tipo = tipo;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
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
	 * @return El identificador del apertura de cierre del tipo de local.
	 */
	public long getHoraApertura() 
	{
		return horaApertura;
	}

	/**
	 * @param horaApertura - El identificador del nuevo horario de apertura del tipo de local. Debe existir un horario con dicho identificador
	 */
	public void setHoraApertura(long horaApertura) 
	{
		this.horaApertura = horaApertura;
	}
	
	/**
	 * @return El identificador del horario de cierre del tipo de local.
	 */
	public long getHoraCierre() 
	{
		return horaCierre;
	}

	/**
	 * @param horaCierre - El identificador del nuevo horario de cierre del tipo de local. Debe existir un horario con dicho identificador
	 */
	public void setHoraCierre(long horaCierre) 
	{
		this.horaCierre = horaCierre;
	}
	
	/**
	 * @return Una cadena de caracteres con la información del tipo de local
	 */
	@Override
	public String toString() 
	{
		return "TipoLocal [id=" + id + ", nombre=" + tipo + ", horaApertura= " + horaApertura + " horaCierre=" + horaCierre + "]";
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
