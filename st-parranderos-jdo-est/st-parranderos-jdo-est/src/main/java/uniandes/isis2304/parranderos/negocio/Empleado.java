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
 * Clase para modelar el concepto EMPLEADO del negocio de Aforo-CCAndes
 *
 */
public class Empleado implements VOEmpleado
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La identificación del visitante respectivo. Debe existir en la tabla VISITANTE
	 */
	private String idVisitante;

	/**
	 * El identificador del local comercial para el que trabaja el empleado. Debe existir en la tabla LOCALCOMERCIAL
	 */
	private String lugarTrabajo;
	
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Empleado() 
	{
		this.idVisitante = "";
		this.lugarTrabajo = "";
	}

	/**
	 * Constructor con valores
	 * @param idVisitante - El identificador del visitante respectivo. Debe existir un visitante con dicho identificador
	 * @param localTrabajo - El identificador del local comercial donde trabaja el empleado. Debe existir un local con dicho identificador
	 */
	public Empleado(String idVisitante, String localTrabajo) 
	{
		this.idVisitante = idVisitante;
		this.lugarTrabajo = localTrabajo;
	}
	
	/**
	 * @return El idVisitante respectivo
	 */
	public String getIdVisitante() 
	{
		return idVisitante;
	}

	/**
	 * @param idVisitante - El nuevo idVisitante. Debe existir un visitante con dicho identificador
	 */
	public void setIdVisitante(String idVisitante) 
	{
		this.idVisitante = idVisitante;
	}

	/**
	 * @return El identificador del lugar donde trabaja el empleado
	 */
	public String getLugarTrabajo() 
	{
		return lugarTrabajo;
	}

	/**
	 * @param lugarTrabajo - El identificador del nuevo lugar de trabajo del empleado. Debe existir un local comercial con dicho identificador
	 */
	public void setLugarTrabajo (String lugarTrabajo) 
	{
		this.lugarTrabajo = lugarTrabajo;
	}

	/**
	 * @return Una cadena de caracteres con la información del empleado
	 */
	@Override
	public String toString() 
	{
		return "Empleado [idVisitante=" + idVisitante + ", lugarTrabajo=" + lugarTrabajo + "]";
	}


}
