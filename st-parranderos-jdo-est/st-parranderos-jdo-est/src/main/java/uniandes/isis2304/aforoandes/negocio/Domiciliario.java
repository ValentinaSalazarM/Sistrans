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
 * Clase para modelar el concepto DOMICILIARIO del negocio de Aforo-CCAndes
 *
 */
public class Domiciliario implements VODomiciliario
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La identificación del visitante respectivo. Debe existir en la tabla VISITANTE
	 */
	private String idVisitante;

	/**
	 * La compañía de domicilios para la que trabaja el domiciliario
	 */
	private String empresaDomicilios;
	
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
	public Domiciliario() 
	{
		this.idVisitante = "";
		this.empresaDomicilios = "";
		this.horaInicioTurno = 0;
		this.horaFinalTurno = 0;

	}

	/**
	 * Constructor con valores
	 * @param idVisitante - El identificador del visitante respectivo. Debe existir un visitante con dicho identificador
	 * @param empresaDomicilios - Compañía para la que trabaja el domiciliario
	 * @param horaInicioTurno - El identificador del horario de inicio de turno. Debe existir un horario con dicho identificador
	 * @param horaFinalTurno - El identificador del horario de fin de turno. Debe existir un horario con dicho identificador
	 */
	public Domiciliario(String idVisitante, String empresaDomicilios, long horaInicioTurno, long horaFinalTurno) 
	{
		this.idVisitante = idVisitante;
		this.empresaDomicilios = empresaDomicilios;
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
	 * @return La empresaDomicilios en la que trabaja el domiciliario
	 */
	public String getEmpresaDomicilios() 
	{
		return empresaDomicilios;
	}

	/**
	 * @param empresaDomicilios - La nueva compañia de domicilios del domiciliario 
	 */
	public void setEmpresaDomicilios(String empresaDomicilios) 
	{
		this.empresaDomicilios = empresaDomicilios;
	}

	/**
	 * @return La hora de inicio de turno del domiciliario
	 */
	public long getHoraInicioTurno() 
	{
		return horaInicioTurno;
	}

	/**
	 * @return horaInicioTurno - La hora de inicio de turno del domiciliario. Debe existir un horario con dicho identificador
	 */
	public void setHoraInicioTurno(long horaInicioTurno) 
	{
		this.horaInicioTurno = horaInicioTurno;
	}

	/**
	 * @return La hora de final de turno del domiciliario
	 */
	public long getHoraFinalTurno() 
	{
		return horaFinalTurno;
	}

	/**
	 * @return horaInicioTurno - La hora de final de turno del domiciliario. Debe existir un horario con dicho identificador
	 */
	public void setHoraFinalTurno(long horaFinalTurno) 
	{
		this.horaFinalTurno = horaFinalTurno;
	}

	/**
	 * @return Una cadena de caracteres con la información del domiciliario
	 */
	@Override
	public String toString() 
	{
		return "Domiciliario [idVisitante = " + idVisitante + ", empresaDomicilios = " + empresaDomicilios + ", horaInicioTurno = " + horaInicioTurno + ", horaFinalTurno = " + horaFinalTurno + "]";
	}


}
