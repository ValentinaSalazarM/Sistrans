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
	
	/**
	 * Identificador del horario de inicio de turno. Debe existir en la tabla HORARIO
	 */
	private long horaInicioTurno;
	
	/**
	 * Identificador del horario final del turno. Debe existir en la tabla HORARIO
	 */
	private long horaFinalTurno;
	
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
		this.horaInicioTurno = 0;
		this.horaFinalTurno = 0;
	}

	/**
	 * Constructor con valores
	 * @param idVisitante - El identificador del visitante respectivo. Debe existir un visitante con dicho identificador
	 * @param localTrabajo - El identificador del local comercial donde trabaja el empleado. Debe existir un local con dicho identificador
	 * @param horaInicioTurno - El identificador del horario de inicio de turno. Debe existir un horario con dicho identificador
	 * @param horaFinalTurno - El identificador del horario de fin de turno. Debe existir un horario con dicho identificador
	 */
	public Empleado(String idVisitante, String localTrabajo, long horaInicioTurno, long horaFinalTurno) 
	{
		this.idVisitante = idVisitante;
		this.lugarTrabajo = localTrabajo;
		this.horaInicioTurno = horaInicioTurno;
		this.horaFinalTurno = horaFinalTurno;
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
	 * @return La hora de inicio de turno del empleado
	 */
	public long getHoraInicioTurno() 
	{
		return horaInicioTurno;
	}

	/**
	 * @return horaInicioTurno - La hora de inicio de turno del empleado
	 */
	public void setHoraInicioTurno(long horaInicioTurno) 
	{
		this.horaInicioTurno = horaInicioTurno;
	}

	/**
	 * @return La hora de final de turno del empleado
	 */
	public long getHoraFinalTurno() 
	{
		return horaFinalTurno;
	}

	/**
	 * @return horaInicioTurno - La hora de final de turno del empleado
	 */
	public void setHoraFinalTurno(long horaFinalTurno) 
	{
		this.horaFinalTurno = horaFinalTurno;
	}


	/**
	 * @return Una cadena de caracteres con la información del empleado
	 */
	@Override
	public String toString() 
	{
		return "Empleado [idVisitante = " + idVisitante + ", lugarTrabajo = " + lugarTrabajo + ", horaInicioTurno = " + horaInicioTurno + ", horaFinalTurno = " + horaFinalTurno + "]";
	}


}
