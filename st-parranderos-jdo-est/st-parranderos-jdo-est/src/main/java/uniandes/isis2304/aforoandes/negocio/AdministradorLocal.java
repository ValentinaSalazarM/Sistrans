/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.isis2304.aforoandes.negocio;

/**
 * Clase para modelar el concepto ADMINISTRADORLOCAL del negocio de Aforo-CCAndes 
 */
public class AdministradorLocal 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del administrador
	 */
	private String identificacion;
	
	/**
	 * El nombre del administrador
	 */
	private String nombre;
	
	/**
	 * El contrasena del administrador
	 */
	private String contrasena;
	
	/**
	 * El local del administrador
	 */
	private String idLocal;
	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public AdministradorLocal () 
	{
		this.identificacion = "";
		this.nombre = "";
		this.contrasena = "";
		this.idLocal = "";
	}

	/**
	 * Constructor con valores
	 * @param identificacion - El identificador del administrador
	 * @param nombre - El nombre del administrador
	 * @param contrasena - El contrasena correspondiente al administrador
	 */
	public AdministradorLocal (String identificacion, String nombre, String contrasena, String idLocal) 
	{
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.idLocal = idLocal;
	}

	/**
	 * @return La identificacion del administrador
	 */
	public String getIdentificacion() 
	{
		return identificacion;
	}

	/**
	 * @param identificacion - La nueva identificación del administrador
	 */
	public void setIdentificacion(String identificacion) 
	{
		this.identificacion = identificacion;
	}

	/**
	 * @return El nombre del administrador
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nombre del administrador
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	/**
	 * @return La contrasena del administrador
	 */
	public String getContrasena() 
	{
		return contrasena;
	}

	/**
	 * @param contrasena - La nueva contraseña del administrador
	 */
	public void setContrasena(String contrasena) 
	{
		this.contrasena = contrasena;
	}

	/**
	 * @return El id del local que administra 
	 */
	public String getIdLocal() 
	{
		return idLocal;
	}

	/**
	 * @param idLocal - El nuevo id del local que administra
	 */
	public void setIdLocal(String idLocal) 
	{
		this.idLocal = idLocal;
	}

	/** 
	 * @return Una cadena con la información básica del Área
	 */
	@Override
	public String toString() 
	{
		return "Administrador Local [identificacion = " + identificacion + ", nombre = " + nombre + ", contrasena = " + contrasena + "]";
	}

}
