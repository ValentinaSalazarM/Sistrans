/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.parranderos.persistencia.PersistenciaAforoAndes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 */
public class AforoAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(AforoAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAforoAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public AforoAndes ()
	{
		pp = PersistenciaAforoAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public AforoAndes (JsonObject tableConfig)
	{
		pp = PersistenciaAforoAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las ÁREAS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un área
	 * Adiciona entradas al log de la aplicación
	 * @param valor - El valor del área
	 * @param aforo - El aforo del área
	 * @return El objeto Área adicionado. null si ocurre alguna Excepción
	 */
	public Area adicionarArea(double valor, int aforo)
	{
        log.info ("Adicionando Área con valor: " + valor + " y aforo: " + aforo);
        Area area = pp.adicionarArea (valor, aforo);		
        log.info ("Adicionando Área: " + area);
        return area;
	}
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Area, dado el valor del área
	 * Adiciona entradas al log de la aplicación
	 * @param valorArea - El valor del area
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAreaPorValor (double valor) 
	{
		log.info ("Eliminando Área por valor: " + valor);
        long resp = pp.eliminarAreaPorValor(valor);		
        log.info ("Eliminando Área por valor: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Area, dado el identificador del área
	 * Adiciona entradas al log de la aplicación
	 * @param idArea - El identificador del área
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAreaPorId (long idArea) 
	{
		log.info ("Eliminando Área por id: " + idArea);
        long resp = pp.eliminarAreaPorId (idArea);		
        log.info ("Eliminando Área por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla Area con un identificador dado
	 * @param idArea - El identificador del área
	 * @return El objeto Area, construido con base en las tuplas de la tabla AREA con el identificador dado
	 */
	public Area darAreaPorId (long idArea)
	{
        log.info ("Dar información de un área por id: " + idArea);
        Area area = pp.darAreaPorId (idArea);
        log.info ("Buscando área por Id: " + area != null ? area : "NO EXISTE");
        return area;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla Area que tienen el valor dado
	 * @param valor - El valor del área
	 * @return La lista de objetos Area, construidos con base en las tuplas de la tabla AREA
	 */
	public List<Area> darAreaPorValor (double valor)
	{
        log.info ("Dar información de áreas por valor: " + valor);
        List<Area> areas = pp.darAreaPorValor (valor);
        log.info ("Dar información de Áreas por valor: " + areas.size() + " áreas con ese valor existentes");
        return areas;

	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla Area
	 * @return La lista de objetos Area, construidos con base en las tuplas de la tabla AREA
	 */
	public List<Area> darAreas ()
	{
        log.info ("Listando Áreas");
        List<Area> areas = pp.darAreas ();	
        log.info ("Listando Áreas: " + areas.size() + " áreas existentes");
        return areas;
	}
	
	/**
	 * Encuentra todas las áreas en AforoAndes y los devuelve como VOArea
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOArea con todas las áreas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOArea> darVOAreas ()
	{
        log.info ("Generando los VO de Area");
         List<VOArea> voArea = new LinkedList<VOArea> ();
        for (Area bdor : pp.darAreas ())
        {
        	voArea.add (bdor);
        }
        log.info ("Generando los VO de Area: " + voArea.size() + " áreas existentes");
       return voArea;
	}
	
	/**
	 * Método que actualiza, de manera transaccional, el valor de un AREA dado su identificador
	 * @param idArea - El identificador del área que se quiere modificar
	 * @param valor - El nuevo valor del área
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un área con ese identificador no existe
	 */
	public long cambiarValorArea (long idArea, double valor)
	{
        log.info ("Cambiando valor del área: " + idArea);
        long cambios = pp.cambiarValorArea (idArea, valor);
        return cambios;

	}
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AforoAndes
	 * @return Un arreglo de 25 posiciones con números que indican el número de tuplas borradas en las tablas respectivamente
	 */
	public long [] limpiarAforoAndes ()
	{
        log.info ("Limpiando la BD de AforoAndes");
        long [] borrrados = pp.limpiarParranderos();	
        log.info ("Limpiando la BD de AforoAndes: Listo!");
        return borrrados;
	}
}
