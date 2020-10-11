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
	 * Hora de apertura del tipo de local
	 */
	private Timestamp horaApertura;
	
	/**
	 * Hora de cierre del tipo de local
	 */
	private Timestamp horaCierre;
	
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
		this.horaApertura = new Timestamp (0);
		this.horaCierre = new Timestamp (0);
	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de local
	 * @param tipo - El nombre del tipo de local
	 */
	public TipoLocal(long id, String tipo, Timestamp horaApertura, Timestamp horaCierre) 
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
	 * @return La hora de apertura del tipo de local
	 */
	public Timestamp getHoraApertura() 
	{
		return horaApertura;
	}

	/**
	 * @param horaApertura - La nueva hora de apertura del tipo de local
	 */
	public void setHoraApertura(Timestamp horaApertura) 
	{
		this.horaApertura = horaApertura;
	}

	/**
	 * @return La hora de cierre del tipo de local
	 */
	public Timestamp getHoraCierre() 
	{
		return horaCierre;
	}

	/**
	 * @param horaCierre - La nueva hora de cierre del tipo de local
	 */
	public void setHoraCierre(Timestamp horaCierre) 
	{
		this.horaCierre = horaCierre;
	}

	/**
	 * @return Una cadena de caracteres con la información del tipo de local
	 */
	@Override
	public String toString() 
	{
		return "TipoLocal [id=" + id + ", nombre=" + tipo + ", horaApertura= " + horaApertura + ", horaCierre =" + horaCierre + "]";
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
