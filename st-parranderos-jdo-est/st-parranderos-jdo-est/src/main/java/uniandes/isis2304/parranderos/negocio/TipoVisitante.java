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
	 * Hora de límite del tipo de visitante
	 */
	private Timestamp horaLimite;
	
	
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
		this.horaLimite = new Timestamp (0);

	}

	/**
	 * Constructor con valores
	 * @param id - El identificador del tipo de visitante
	 * @param tipo - El nombre del tipo de visitante
	 */
	public TipoVisitante(long id, String tipo, Timestamp horaApertura) 
	{
		this.id = id;
		this.tipo = tipo;
		this.horaLimite = horaApertura;
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
	 * @return La hora límite del tipo de visitante
	 */
	public Timestamp getHoraLimite() 
	{
		return horaLimite;
	}

	/**
	 * @param horaApertura - La nueva hora límite del tipo de visitante
	 */
	public void setHoraLimite(Timestamp horaApertura) 
	{
		this.horaLimite = horaApertura;
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
