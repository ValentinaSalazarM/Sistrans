/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Aforo-CCAndes
 *  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforoandes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.aforoandes.persistencia.PersistenciaAforoAndes;

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
	 * @return El objeto Area, construido con base en las tuplas de la tabla AREA con el valor dado
	 */
	public Area darAreaPorValor (double valor)
	{
		log.info ("Dar información de área por valor: " + valor);
		Area area = pp.darAreaPorValor (valor);
		log.info ("Buscando área por valor: " + area != null ? area : "NO EXISTE");
		return area;
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
		for (Area area : pp.darAreas ())
		{
			voArea.add (area);
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

	/**
	 * Método que actualiza, de manera transaccional, el aforo de un AREA
	 * @param idArea - El identificador del área que se quiere modificar
	 * @param aforo - El nuevo aforo del área
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un área con ese identificador no existe
	 */
	public long cambiarAforoArea (long idArea, int aforo)
	{
		log.info ("Cambiando aforo del área: " + idArea);
		long cambios = pp.cambiarAforoArea (idArea, aforo);
		return cambios;

	}

	/* ****************************************************************
	 * 			Métodos para manejar los ASCENSORES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un ascensor
	 * Adiciona entradas al log de la aplicación
	 * @param idAscensor - El identificador del ascensor
	 * @param capacidadNormal - El identificador de la capacidad normal del ascensor
	 * @param area - El identificador del área del ascensor
	 * @param pesoMaximo - El peso máximo del ascensor
	 * @param idCentroComercial - El identificador del centro comercial del ascensor
	 * @return El objeto Ascensor adicionado. null si ocurre alguna Excepción
	 */
	public Ascensor adicionarAscensor(String idAscensor, long capacidadNormal, long area, double pesoMaximo, String idCentroComercial)	
	{
		
		log.info ("Adicionando Ascensor con identificador: " + idAscensor );
		Ascensor ascensor = pp.adicionarAscensor(idAscensor, capacidadNormal, area, pesoMaximo, idCentroComercial);
		log.info ("Adicionando Ascensor: " + ascensor);
		return ascensor;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ASCENSOR, dado el identificador del ascensor
	 * Adiciona entradas al log de la aplicación
	 * @param idAscensor - El identificador del ascensor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAscensorPorId (String idAscensor) 
	{
		log.info ("Eliminando Ascensor por id: " + idAscensor);
		long resp = pp.eliminarAscensorPorId (idAscensor);		
		log.info ("Eliminando Ascensor por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ASCENSOR, dado el peso máximo del ascensor
	 * Adiciona entradas al log de la aplicación
	 * @param pesoMaximo - El peso máximo del ascensor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAscensorPorPesoMaximo (double pesoMaximo) 
	{
		log.info ("Eliminando Ascensor por peso máximo: " + pesoMaximo);
		long resp = pp.eliminarAscensorPorPesoMaximo(pesoMaximo);		
		log.info ("Eliminando Ascensor por peso máximo: " + resp + " tuplas eliminadas");
		return resp;
	}


	/**
	 * Método que consulta todas las tuplas en la tabla ASCENSOR que tienen el identificador dado
	 * @param idAscensor - El identificador del ascensor
	 * @return El objeto ASCENSOR, construido con base en la tuplas de la tabla ASCENSOR, que tiene el identificador dado
	 */
	public Ascensor darAscensorPorId (String idAscensor)
	{
		log.info ("Dar información de un ascensor por id: " + idAscensor);
		Ascensor ascensor = pp.darAscensorPorId (idAscensor);
		log.info ("Buscando ascensor por Id: " + ascensor != null ? ascensor : "NO EXISTE");
		return ascensor;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ASCENSOR que tienen el peso máximo dado
	 * @param pesoMaximo - El peso máximo del ascensor
	 * @return La lista de objetos ASCENSOR, construidos con base en las tuplas de la tabla ASCENSOR
	 */
	public List<Ascensor> darAscensoresPorPesoMaximo (double pesoMaximo)
	{
		log.info ("Dar información de ascensores por peso máximo: " + pesoMaximo);
		List<Ascensor> ascensores = pp.darAscensoresPorPesoMaximo (pesoMaximo);
		log.info ("Dar información de ascensores por peso máximo: " + ascensores.size() + " ascensores con ese peso máximo existentes");
		return ascensores;

	}

	/**
	 * Método que consulta todas las tuplas en la tabla ASCENSOR
	 * @return La lista de objetos ASCENSOR, construidos con base en las tuplas de la tabla ASCENSOR
	 */
	public List<Ascensor> darAscensores ()
	{
		log.info ("Listando Ascensor");
		List<Ascensor> ascensores = pp.darAscensores ();	
		log.info ("Listando Ascensor: " + ascensores.size() + " ascensores existentes");
		return ascensores;
	}

	/**
	 * Encuentra todos los ascensores en AforoAndes y los devuelve como VOAscensor
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOAscensor con todas los ascensores que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAscensor> darVOAscensores ()
	{
		log.info ("Generando los VO de Ascensor");
		List<VOAscensor> voAscensor = new LinkedList<VOAscensor> ();
		for (Ascensor ascensor : pp.darAscensores ())
		{
			voAscensor.add (ascensor);
		}
		log.info ("Generando los VO de Ascensor: " + voAscensor.size() + " ascensores existentes");
		return voAscensor;
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de un ASCENSOR
	 * @param idAscensor - El identificador del ascensor que se quiere modificar
	 * @param pesoMaximo - El nuevo peso máximo del ascensor
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un ascensor con ese identificador no existe
	 */
	public long cambiarPesoMaximoAscensor (String idAscensor, double pesoMaximo)
	{
		log.info ("Cambiando el peso máximo del ascensor: " + idAscensor);
		long cambios = pp.cambiarPesoMaximoAscensor(idAscensor, pesoMaximo);
		return cambios;

	}

	/**
	 * Método que actualiza, de manera transaccional, el área de un ASCENSOR
	 * @param idAscensor - El identificador del parqueadero que se quiere modificar
	 * @param area - Identificador de la nueva área de un ascensor
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un ascensor con ese identificador no existe
	 */
	public long cambiarAreaAscensor (String idAscensor, long area)
	{
		log.info ("Cambiando área del ascensor: " + idAscensor);
		long cambios = pp.cambiarAreaAscensor (idAscensor, area);
		return cambios;

	}

	/* ****************************************************************
	 * 			Métodos para manejar los BAÑOS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un baño
	 * Adiciona entradas al log de la aplicación
	 * @param idBaño - El identificador del baño
	 * @param capacidadNormal - El identificador de la capacidad normal del baño
	 * @param area - El identificador del área del baño
	 * @param numeroSanitarios - El número de sanitarios del baño
	 * @param idCentroComercial - El identificador del centro comercial del baño
	 * @return El objeto Bano adicionado. null si ocurre alguna Excepción
	 */
	public Bano adicionarBaño (String idBaño, long capacidadNormal, long area, int numeroSanitarios, String idCentroComercial) 
	{
		log.info ("Adicionando Bano con identificador: " + idBaño );
		Bano bano = pp.adicionarBaño(idBaño, capacidadNormal, area, numeroSanitarios, idCentroComercial);
		log.info ("Adicionando Bano: " + bano);
		return bano;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAÑO, dado el identificador del baño
	 * Adiciona entradas al log de la aplicación
	 * @param idBaño - El identificador del baño
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarBañoPorId (String idBaño) 
	{
		log.info ("Eliminando Bano por id: " + idBaño);
		long resp = pp.eliminarBañoPorId (idBaño);		
		log.info ("Eliminando Bano por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAÑO, dado el número de sanitarios del baño
	 * Adiciona entradas al log de la aplicación
	 * @param sanitarios - El número de sanitarios del baño
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarBañoPorSanitarios (int sanitarios) 
	{
		log.info ("Eliminando Bano por número de sanitarios: " + sanitarios);
		long resp = pp.eliminarBañoPorSanitarios(sanitarios);		
		log.info ("Eliminando Bano por número de sanitarios: " + resp + " tuplas eliminadas");
		return resp;
	}


	/**
	 * Método que consulta todas las tuplas en la tabla BAÑO que tienen el identificador dado
	 * @param idBaño - El identificador del baño
	 * @return El objeto BAÑO, construido con base en la tuplas de la tabla BAÑO, que tiene el identificador dado
	 */
	public Bano darBañoPorId (String idBaño)
	{
		log.info ("Dar información de un baño por id: " + idBaño);
		Bano bano = pp.darBañoPorId (idBaño);
		log.info ("Buscando baño por Id: " + bano != null ? bano : "NO EXISTE");
		return bano;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BAÑO que tienen el número de sanitarios dado
	 * @param numeroSanitarios - El número de sanitarios del baño
	 * @return La lista de objetos BAÑO, construidos con base en las tuplas de la tabla BAÑO
	 */
	public List<Bano> darBañosPorSanitarios (int numeroSanitarios)
	{
		log.info ("Dar información de baños por número de sanitarios: " + numeroSanitarios);
		List<Bano> banos = pp.darBañosPorSanitarios (numeroSanitarios);
		log.info ("Dar información de baños por número de sanitarios: " + banos.size() + " baños con ese número de sanitarios existentes");
		return banos;

	}

	/**
	 * Método que consulta todas las tuplas en la tabla BAÑO
	 * @return La lista de objetos BAÑO, construidos con base en las tuplas de la tabla BAÑO
	 */
	public List<Bano> darBaños ()
	{
		log.info ("Listando Bano");
		List<Bano> banos = pp.darBaños ();	
		log.info ("Listando Bano: " + banos.size() + " baños existentes");
		return banos;
	}

	/**
	 * Encuentra todos los baños en AforoAndes y los devuelve como VOBano
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOBano con todas los baños que conoce la aplicación, llenos con su información básica
	 */
	public List<VOBano> darVOBaños ()
	{
		log.info ("Generando los VO de Bano");
		List<VOBano> voBaño = new LinkedList<VOBano> ();
		for (Bano bano : pp.darBaños ())
		{
			voBaño.add (bano);
		}
		log.info ("Generando los VO de Bano: " + voBaño.size() + " baños existentes");
		return voBaño;
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de un BAÑO
	 * @param idBaño - El identificador del baño que se quiere modificar
	 * @param numeroSanitarios - El nuevo número de sanitarios del baño
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un baño con ese identificador no existe
	 */
	public long cambiarNumeroSanitariosBaño (String idBaño, int numeroSanitarios)
	{
		log.info ("Cambiando el número de sanitarios del baño: " + idBaño);
		long cambios = pp.cambiarNumeroSanitariosBaño (idBaño, numeroSanitarios);
		return cambios;

	}

	/**
	 * Método que actualiza, de manera transaccional, el área de un BAÑO
	 * @param idBaño - El identificador del parqueadero que se quiere modificar
	 * @param area - Identificador de la nueva área de un baño
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un baño con ese identificador no existe
	 */
	public long cambiarAreaBaño (String idBaño, long area)
	{
		log.info ("Cambiando área del baño: " + idBaño);
		long cambios = pp.cambiarAreaBaño (idBaño, area);
		return cambios;

	}

	/* ****************************************************************
	 * 			Métodos para manejar las CAPACIDADESNORMALES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente una capacidad normal
	 * Adiciona entradas al log de la aplicación
	 * @param valor - El valor de la capacidad normal
	 * @param aforo - El aforo de la capacidad normal
	 * @return El objeto CapacidadNormal adicionado. null si ocurre alguna Excepción
	 */
	public CapacidadNormal adicionarCapacidadNormal (double valor, int aforo)
	{
		log.info ("Adicionando CapacidadNormal con valor: " + valor + " y aforo: " + aforo);
		CapacidadNormal capacidadNormal = pp.adicionarCapacidadNormal (valor, aforo);		
		log.info ("Adicionando CapacidadNormal: " + capacidadNormal);
		return capacidadNormal;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CapacidadNormal, dado el valor de la capacidad normal
	 * Adiciona entradas al log de la aplicación
	 * @param valorArea - El valor de la capacidad normal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCapacidadNormalPorValor (double valor) 
	{
		log.info ("Eliminando CapacidadNormal por valor: " + valor);
		long resp = pp.eliminarCapacidadNormalPorValor (valor);		
		log.info ("Eliminando CapacidadNormal por valor: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CapacidadNormal, dado el identificador de la capacidad normal
	 * Adiciona entradas al log de la aplicación
	 * @param idCapacidadNormal - El identificador de la capacidad normal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCapacidadNormalPorId (long idCapacidadNormal) 
	{
		log.info ("Eliminando CapacidadNormal por id: " + idCapacidadNormal);
		long resp = pp.eliminarAreaPorId (idCapacidadNormal);		
		log.info ("Eliminando CapacidadNormal por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CapacidadNormal con un identificador dado
	 * @param idCapacidadNormal - El identificador de la capacidad normal
	 * @return El objeto CapacidadNormal, construido con base en las tuplas de la tabla CAPACIDADNORMAL con el identificador dado
	 */
	public CapacidadNormal darCapacidadNormalPorId (long idCapacidadNormal)
	{
		log.info ("Dar información de una capacidad normal por id: " + idCapacidadNormal);
		CapacidadNormal capacidadNormal = pp.darCapacidadNormalPorId (idCapacidadNormal);
		log.info ("Buscando capacidad normal por Id: " + capacidadNormal != null ? capacidadNormal : "NO EXISTE");
		return capacidadNormal;
	}


	/**
	 * Método que consulta todas las tuplas en la tabla CapacidadNormal que tienen el valor dado
	 * @param valor - El valor de la capacidad normal
	 * @return El objeto CapacidadNormal, construido con base en las tuplas de la tabla CAPACIDADNORMAL con el valor dado
	 */
	public CapacidadNormal darCapacidadNormalPorValor (double valor)
	{
		log.info ("Dar información de capacidad normal por valor: " + valor);
		CapacidadNormal capacidadNormal = pp.darCapacidadNormalPorValor (valor);
		log.info ("Buscando capacidad normal por valor: " + capacidadNormal != null ? capacidadNormal : "NO EXISTE");
		return capacidadNormal;

	}

	/**
	 * Método que consulta todas las tuplas en la tabla CapacidadNormal
	 * @return La lista de objetos CapacidadNormal, construidos con base en las tuplas de la tabla CAPACIDADNORMAL
	 */
	public List<CapacidadNormal> darCapacidadesNormales ()
	{
		log.info ("Listando CapacidadNormal");
		List<CapacidadNormal> capacidadesNormales = pp.darCapacidadesNormales ();	
		log.info ("Listando CapacidadNormal: " + capacidadesNormales.size() + " capacidades normales existentes");
		return capacidadesNormales;
	}

	/**
	 * Encuentra todas las capacidades normales en AforoAndes y los devuelve como VOCapacidadNormal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCapacidadNormal con todas las capacidades normales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCapacidadNormal> darVOCapacidadesNormales ()
	{
		log.info ("Generando los VO de CapacidadNormal");
		List<VOCapacidadNormal> voCapacidadNormal = new LinkedList<VOCapacidadNormal> ();
		for (CapacidadNormal capacidades : pp.darCapacidadesNormales ())
		{
			voCapacidadNormal.add (capacidades);
		}
		log.info ("Generando los VO de CapacidadNormal: " + voCapacidadNormal.size() + " capacidades normales existentes");
		return voCapacidadNormal;
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de una CAPACIDADNORMAL
	 * @param idCapacidadNormal - El identificador de la capacidad normal que se quiere modificar
	 * @param valor - El nuevo valor de la capacidad normal
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que una capacidad normal con ese identificador no existe
	 */
	public long cambiarValorCapacidad (long idCapacidadNormal, double valor)
	{
		log.info ("Cambiando valor de la capacidad normal: " + idCapacidadNormal);
		long cambios = pp.cambiarValorCapacidad (idCapacidadNormal, valor);
		return cambios;

	}

	/**
	 * Método que actualiza, de manera transaccional, el aforo de una CAPACIDADNORMAL
	 * @param idCapacidadNormal - El identificador de la capacidad normal que se quiere modificar
	 * @param aforo - El nuevo aforo de la capacidad normal
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que una capacidad normal con ese identificador no existe
	 */
	public long cambiarAforoCapacidad (long idCapacidadNormal, int aforo)
	{
		log.info ("Cambiando aforo de la capacidad normal: " + idCapacidadNormal);
		long cambios = pp.cambiarAforoCapacidad (idCapacidadNormal, aforo);
		return cambios;

	}


	/* ****************************************************************
	 * 			Métodos para manejar los CARNETS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un carnet 
	 * Adiciona entradas al log de la aplicación
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @param idVisitante - El identificador del visitante del carnet
	 * @return El objeto Carnet adicionado. null si ocurre alguna Excepción
	 */
	public Carnet adicionarCarnet (long tipoCarnet, String idVisitante)
	{
		log.info ("Adicionando Carnet con tipo: " + tipoCarnet + " y idVisitante: " + idVisitante);
		Carnet carnet = pp.adicionarCarnet(tipoCarnet, idVisitante);
		log.info ("Adicionando Carnetr: " + carnet);
		return carnet;

	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Carnet, dado el tipo del carnet
	 * Adiciona entradas al log de la aplicación
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCarnetsPorTipo (long tipoCarnet) 
	{
		log.info ("Eliminando Carnet por tipoCarnet: " + tipoCarnet);
		long resp = pp.eliminarCarnetsPorTipo(tipoCarnet);
		log.info ("Eliminando Carnet por tipoCarnet: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Carnet, dado identificador del visitante
	 * Adiciona entradas al log de la aplicación
	 * @param idVisitante - El identificador del visitante del carnet
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCarnetPorIdVisitante (String idVisitante) 
	{
		log.info ("Eliminando Carnet por idVisitante: " + idVisitante);
		long resp = pp.eliminarCarnetPorIdVisitante(idVisitante);
		log.info ("Eliminando Carnet por idVisitante: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Carnet con un tipo dado
	 * @param tipoCarnet - El identificador del tipo de carnet
	 * @return La lista de objetos Carnet que tienen el tipo buscado, construidos con base en las tuplas de la tabla CARNET
	 */
	public List<Carnet> darCarnetsPorTipo (long tipoCarnet)
	{
		log.info ("Dar información de carnets por tipoCarnet: " + tipoCarnet);
		List<Carnet> carnets = pp.darCarnetsPorTipo(tipoCarnet);
		log.info ("Dar información de carnets por tipoCarnet: " + carnets.size() + " carnets con ese tipo existentes");
		return carnets;

	}

	/**
	 * Método que consulta todas las tuplas en la tabla Carnet con un identificador de visitante dado
	 * @param idVisitante - El identificador del dueño del carnet
	 * @return El objeto Carnet, construido con base en las tuplas de la tabla CARNET con el idVisitante dado
	 */
	public Carnet darCarnetPorIdVisitante (String idVisitante)
	{

		log.info ("Dar información de carnets por idVisitante: " + idVisitante);
		Carnet carnet = pp.darCarnetPorIdVisitante(idVisitante);
		log.info ("Buscando carnet por IdVisitante: " + carnet != null ? carnet : "NO EXISTE");
		return carnet;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Carnet
	 * @return La lista de objetos Carnet, construidos con base en las tuplas de la tabla CARNET
	 */
	public List<Carnet> darCarnets ()
	{
		log.info ("Listando Carnet");
		List<Carnet> carnets = pp.darCarnets ();	
		log.info ("Listando Carnet: " + carnets.size() + " carnets existentes");
		return carnets;

	}

	/**
	 * Encuentra todos los carnets en AforoAndes y los devuelve como una lista de VoCarnet
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VoCarnet con todos los carnets que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCarnet> darVOCarnets ()
	{
		log.info ("Generando VO de carnets");
		List<VOCarnet> voCarnet = new LinkedList<VOCarnet> ();
		for (Carnet carnet : pp.darCarnets ())
		{
			voCarnet.add (carnet);
		}
		log.info ("Generando los VO de Carnet: " + voCarnet.size() + " carnets existentes");
		return voCarnet;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los CENTROSCOMERCIALES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un local comercial
	 * Adiciona entradas al log de la aplicación
	 * @param idCentroComercial - El identificador del centro comercial
	 * @param nombre - El nombre del centro comercial
	 * @return El objeto Área adicionado. null si ocurre alguna Excepción
	 */
	public CentroComercial adicionarCentroComercial (String id, String nombre)
	{
		log.info ("Adicionando centro comercial " + nombre);
		CentroComercial centroComercial = pp.adicionarCentroComercial(id, nombre);
		log.info ("Adicionando centro comercial: " + centroComercial);
		return centroComercial;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CentroComercial, dado el identificador del centro comercial
	 * Adiciona entradas al log de la aplicación
	 * @param idCentroComercial - El identificador del centro comercial
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCentroComercialPorId (String idCentroComercial) 
	{
		log.info ("Eliminando centro comercial por id: " + idCentroComercial);
		long resp = pp.eliminarCentroComercialPorId(idCentroComercial);
		log.info ("Eliminando centro comercial por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla CentroComercial, dado el identificador del centro comercial
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del centro comercial
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCentroComercialPorNombre (String nombre) 
	{
		log.info ("Eliminando centro comercial por nombre: " + nombre);
		long resp = pp.eliminarCentroComercialPorNombre(nombre);
		log.info ("Eliminando centro comercial por nombre: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CentroComercial con un identificador dado
	 * @param idCentroComercial - El identificador del centro comercial
	 * @return El objeto CentroComercial, construido con base en las tuplas de la tabla CENTROCOMERCIAL con el identificador dado
	 */
	public CentroComercial darCentroComercialPorId (String idCentroComercial)
	{
		log.info ("Dar información de un centro comercial por id: " + idCentroComercial);
		CentroComercial centroComercial = pp.darCentroComercialPorId(idCentroComercial);
		log.info ("Buscando centro comercial por Id: " + centroComercial != null ? centroComercial : "NO EXISTE");
		return centroComercial;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CentroComercial con un nomre dado
	 * @param nombre - El nombre del centro comercial
	 * @return El objeto CentroComercial, construido con base en las tuplas de la tabla CENTROCOMERCIAL con el nombre dado
	 */
	public List<CentroComercial> darCentroComercialPorNombre (String nombre)
	{
		log.info ("Dar información de un centro comercial por nombre: " + nombre);
		List<CentroComercial> centrosComerciales = pp.darCentrosComercialesPorNombre(nombre);
		log.info ("Dar información de centros comerciales por nombre: " + centrosComerciales.size() + " centros comerciales con ese nombre existentes");
		return centrosComerciales;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CentroComercial
	 * @return La lista de objetos CentroComercial, construidos con base en las tuplas de la tabla CENTROCOMERCIAL
	 */
	public List<CentroComercial> darCentrosComerciales ()
	{
		log.info ("Consultando CentroComercial");
		List<CentroComercial> centrosComerciales = pp.darCentrosComerciales();	
		log.info ("Consultando CentroComercial: " + centrosComerciales.size() + " centros comerciales existentes");
		return centrosComerciales;
	}

	/**
	 * Encuentra todos los centros comerciales en AforoAndes y los devuelve como una lista de VOCentroComercial
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOCentroComercial con todos los centros comerciales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCentroComercial> darVOCentrosComerciales ()
	{
		log.info ("Generando los VO de los centros comerciales");       
		List<VOCentroComercial> centrosComerciales = new LinkedList<VOCentroComercial> ();
		for (CentroComercial cc : pp.darCentrosComerciales())
		{
			centrosComerciales.add (cc);
		}
		log.info ("Generando los VO de los centros comerciales: " + centrosComerciales.size() + " existentes");
		return centrosComerciales;
	}

	/**
	 * Método que actualiza, de manera transaccional, el nombre de un CENTROCOMERCIAL
	 * @param idCentroComercial - El identificador del centro comercial 
	 * @param nombre - El nuevo nombre del Centro Comercial
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un centro comercial con ese identificador no existe
	 */
	public long cambiarNombreCentroComercial (String idCentroComercial, String nombre)
	{
		log.info ("Cambiando nombre del centro comercial: " + idCentroComercial);
		long cambios = pp.cambiarNombreCentroComercial(idCentroComercial, nombre);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los DOMICILIARIOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un Domiciliario
	 * Adiciona entradas al log de la aplicación
	 * @param idVisitante - El identificador del visitante
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return El objeto Domiciliario adicionado. null si ocurre alguna Excepción
	 */
	public Domiciliario adicionarDomiciliario (String idVisitante, String empresaDomicilios)
	{
		log.info ("Adicionando domiciliario: " + idVisitante);
		Domiciliario domiciliario = pp.adicionarDomiciliario(idVisitante, empresaDomicilios);
		log.info ("Adicionando domiciliario: " + domiciliario);
		return domiciliario;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Domiciliario, dado el identificador del domiciliario
	 * Adiciona entradas al log de la aplicación
	 * @param idVisitante - El identificador del domiciliario
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarDomiciliarioPorId (String idVisitante) 
	{
		log.info ("Eliminando domiciliario por idVisitante: " + idVisitante);
		long resp = pp.eliminarDomiciliarioPorId(idVisitante);
		log.info ("Eliminando domiciliario por idVisitante: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Domiciliario, dado la empresa donde trabaja el domiciliario
	 * Adiciona entradas al log de la aplicación
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarDomiciliarioPorEmpresa (String empresaDomicilios) 
	{
		log.info ("Eliminando domiciliario por empresa: " + empresaDomicilios);
		long resp = pp.eliminarDomiciliarioPorEmpresa(empresaDomicilios);
		log.info ("Eliminando domiciliario por empresa: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Domiciliario con un identificador dado
	 * @param idDomiciliario - El identificador de la capacidad normal
	 * @return El objeto Domiciliario, construido con base en las tuplas de la tabla DOMICILIARIO con el identificador dado
	 */
	public Domiciliario darDomiciliarioPorId (String idDomiciliario)
	{
		log.info ("Dar información de un domiciliario por id: " + idDomiciliario);
		Domiciliario domiciliario = pp.darDomiciliarioPorId(idDomiciliario);
		log.info ("Buscando domiciliario por id: " + domiciliario != null ? domiciliario : "NO EXISTE");
		return domiciliario;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Domiciliario que trabajan en la empresa dada
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return La lista de objetos Domiciliario, construidos con base en las tuplas de la tabla DOMICILIARIO
	 */
	public List<Domiciliario> darDomiciliariosPorEmpresa (String empresaDomicilios)
	{
		log.info ("Dar información de un domiciliario por empresa: " + empresaDomicilios);
		List<Domiciliario> domiciliarios = pp.darDomiciliariosPorEmpresa(empresaDomicilios);
		log.info ("Dar información de domiciliarios por empresa: " + domiciliarios.size() + " domiciliarios con esa empresa existentes");
		return domiciliarios;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Domiciliario
	 * @return La lista de objetos Domiciliario, construidos con base en las tuplas de la tabla DOMICILIARIO
	 */
	public List<Domiciliario> darDomiciliarios ()
	{
		log.info ("Listando domiciliarios");
		List<Domiciliario> domiciliarios = pp.darDomiciliarios ();	
		log.info ("Listando domiciliarios: " + domiciliarios.size() + " domiciliarios existentes");
		return domiciliarios;
	}

	/**
	 * Encuentra todos los domiciliarios en AforoAndes y los devuelve como VODomiciliario
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VODomiciliario con todos los domiciliarios que conoce la aplicación, llenos con su información básica
	 */
	public List<VODomiciliario> darVODomiciliario ()
	{
		log.info ("Generando los VO de Domiciliario");
		List<VODomiciliario> voDomiciliarios = new LinkedList<VODomiciliario> ();
		for (Domiciliario domiciliario : pp.darDomiciliarios ())
		{
			voDomiciliarios.add (domiciliario);
		}
		log.info ("Generando los VO de Domiciliario: " + voDomiciliarios.size() + " domiciliarios existentes");
		return voDomiciliarios;
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de una DOMICILIARIO
	 * @param id - El identificador del domiciliario que se quiere modificar
	 * @param empresaDomicilios - La empresa donde trabaja el domiciliario
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un domiciliario con ese identificador no existe
	 */
	public long cambiarEmpresaDomiciliario (String idDomiciliario, String empresaDomicilios)
	{
		log.info ("Cambiando empresa del domiciliario: " + idDomiciliario);
		long cambios = pp.cambiarEmpresaDomiciliario(idDomiciliario, empresaDomicilios);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los EMPLEADOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un empleado
	 * Adiciona entradas al log de la aplicación
	 * @param idVisitante - El identificador del visitante
	 * @param lugartrabajo - El lugar de trabajo del empleado 
	 * @return El objeto Empleado adicionado. null si ocurre alguna Excepción
	 */
	public Empleado adicionarEmpleado(String idVisitante, String lugartrabajo)
	{
		log.info ("Adicionando empleado: " + idVisitante);
		Empleado empleado = pp.adicionarEmpleado(idVisitante, lugartrabajo);
		log.info ("Adicionando empleado: " + empleado);
		return empleado;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Empleado, dado el identificador de este 
	 * Adiciona entradas al log de la aplicación
	 * @param idVisitante - El identificador del empleado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarEmpleadoPorId(String idVisitante) 	
	{
		log.info ("Eliminando empleado por idVisitante: " + idVisitante);
		long resp = pp.eliminarEmpleadoPorId(idVisitante);
		log.info ("Eliminando empleado por idVisitante: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Empleado, dado el lugar de trabajo del empleado
	 * Adiciona entradas al log de la aplicación
	 * @param lugartrabajo - El lugar de trabajo del empleado
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarEmpleadoPorLugar (String lugartrabajo) 
	{
		log.info ("Eliminando empleado por lugar de trabajo: " + lugartrabajo);
		long resp = pp.eliminarEmpleadoPorLugar(lugartrabajo);
		log.info ("Eliminando empleado por lugar de trabajo: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Empleado con un identificador dado
	 * @param idEmpleado - El identificador del empleado 
	 * @return El objeto Empleado, construido con base en las tuplas de la tabla EMPLEADO con el identificador dado
	 */
	public Empleado darEmpleadoPorId (String idEmpleado)
	{
		log.info ("Dar información de un empleado por id: " + idEmpleado);
		Empleado empleado = pp.darEmpleadoPorId(idEmpleado);
		log.info ("Buscando empleado por id: " + empleado != null ? empleado : "NO EXISTE");
		return empleado;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla EMPLEADO que tienen el valor dado
	 * @param lugartrabajo - El lugar de trabajo del empleado 
	 * @return La lista de objetos Empleado, construidos con base en las tuplas de la tabla EMPLEADO
	 */
	public List<Empleado> darEmpleadosPorLugar (String lugar)
	{
		log.info ("Dar información de un empleados por lugar de trabajo: " + lugar);
		List<Empleado> empleados = pp.darEmpleadosPorLugar(lugar);
		log.info ("Dar información de empleados por lugar de trabajo: " + empleados.size() + " empleados con ese lugar de trabajo existentes");
		return empleados;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Empleado
	 * @return La lista de objetos Empleado, construidos con base en las tuplas de la tabla EMPLEADO
	 */
	public List<Empleado> darEmpleados ()
	{
		log.info ("Listando empleados");
		List<Empleado> empleados = pp.darEmpleados ();	
		log.info ("Listando empleados: " + empleados.size() + " empleados existentes");
		return empleados;
	}

	/**
	 * Encuentra todos los empleados en AforoAndes y los devuelve como VOEmpleado
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOEmpleado con todos los empleados que conoce la aplicación, llenos con su información básica
	 */
	public List<VOEmpleado> darVOEmpleados ()
	{
		log.info ("Generando los VO de Empleado");
		List<VOEmpleado> voEmpleados = new LinkedList<VOEmpleado> ();
		for (Empleado domiciliario : pp.darEmpleados ())
		{
			voEmpleados.add (domiciliario);
		}
		log.info ("Generando los VO de Empleado: " + voEmpleados.size() + " empleados existentes");
		return voEmpleados;
	}

	/**
	 * Método que actualiza, de manera transaccional, el valor de un EMPLEADO
	 * @param idEmpleado - El identificador del empleado que se quiere modificar
	 * @param lugartrabajo - El lugar de trabajo del empleado
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un empleado con ese identificador no existe
	 */
	public long cambiarLugarEmpleado(String idEmpleado, String lugartrabajo)
	{        
		log.info ("Cambiando empresa del domiciliario: " + idEmpleado);
		long cambios = pp.cambiarLugarEmpleado(idEmpleado, lugartrabajo);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HORARIOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un horario
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del horario
	 * @param hora - La hora del horario
	 * @param minuto - El minuto del horario
	 * @return El objeto HORARIO adicionado. null si ocurre alguna Excepción
	 */
	public Horario adicionarHorario( int hora, int minuto)
	{
		log.info ("Adicionando horario con hora " + hora + " y minuto: " + minuto);
		Horario horario = pp.adicionarHorario(hora, minuto);
		log.info ("Adicionando horario: " + horario);
		return horario;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Horario, dado el identificador de este 
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del horario 
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHorarioPorId(long id)
	{
		log.info ("Eliminando horario por id: " + id);
		long resp = pp.eliminarHorarioPorId(id);
		log.info ("Eliminando horario por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Horario con un identificador dado
	 * @param id - El identificador del horario 
	 * @return El objeto Horario, construido con base en las tuplas de la tabla HORARIO con el identificador dado
	 */
	public Horario darHorarioPorId (long id)
	{
		log.info ("Dar información de un horario por id: " + id);
		Horario horario = pp.darHorarioPorId(id);
		log.info ("Buscando horario por Id: " + horario != null ? horario : "NO EXISTE");
		return horario;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Horario
	 * @return La lista de objetos Horario, construidos con base en las tuplas de la tabla HORARIO
	 */
	public List<Horario> darHorarios ()
	{
		log.info ("Listando horarios");
		List<Horario> horarios = pp.darHorarios ();	
		log.info ("Listando horarios: " + horarios.size() + " horarios existentes");
		return horarios;
	}

	/**
	 * Encuentra todos los horario en AforoAndes y los devuelve como VOHorario
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOHorario con todos los horarios que conoce la aplicación, llenos con su información básica
	 */
	public List<VOHorario> darVOHorarios ()
	{
		log.info ("Generando los VO de Bebedores");
		List<VOHorario> voHorarios = new LinkedList<VOHorario> ();
		for (Horario horario : pp.darHorarios ())
		{
			voHorarios.add (horario);
		}
		log.info ("Generando los VO de Horario: " + voHorarios.size() + " horarios existentes");
		return voHorarios;
	}

	/**
	 * Método que actualiza, de manera transaccional, la hora de un horario
	 * @param id - El identificador del horario a modificar
	 * @param hora - La hora que pertenece al horario
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un horario con ese identificador no existe
	 */
	public long cambiarHoraHorario(long id, int hora)
	{
		log.info ("Cambiando hora del horario: " + id);
		long cambios = pp.cambiarHoraHorario(id, hora);
		return cambios;
	}

	/**
	 * Método que actualiza, de manera transaccional, el minuto del horario
	 * @param id - El identificador del horario a modificar
	 * @param minuto - El minuto que pertenece al horario
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un horario con ese identificador no existe
	 */
	public long cambiarMinutoHorario (long id, int minuto)
	{
		log.info ("Cambiando minuto del horario: " + id);
		long cambios = pp.cambiarMinutoHorario(id, minuto);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los LECTORES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un lector
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del lector
	 * @param tipolector - El tipo del lector
	 * @param idCentroComercial - El identificador del centro comercial 
	 * @param idLocalComercial - El identificador del local comercial en el caso de que pertenezca a un local
	 * @param idAscensor - El identificador del ascensor en el caso de que pertenezca a un ascensor
	 * @param idParqueadero - El identificador del parqueadero en el caso de que pertenezca a un parqueadero
	 * @param idBaño - El identificador del baño en el caso de que pertenezca a un baño
	 * @return El objeto LECTOR adicionado. null si ocurre alguna Excepción
	 */
	public Lector adicionarLector(long id, long tipoLector, String idCentroComercial, String idLocalComercial, String idAscensor, String idParqueadero, String idBaño) 
	{
		log.info ("Adicionando lector: " + id);
		Lector lector = pp.adicionarLector(id, tipoLector, idCentroComercial, idLocalComercial, idAscensor, idParqueadero, idBaño);
		log.info ("Adicionando lector: " + lector);
		return lector;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Lector, dado el identificador de este 
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del lector 
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarLectorPorId(long id) 
	{
		log.info ("Eliminando lector por id: " + id);
		long resp = pp.eliminarLectorPorId(id);
		log.info ("Eliminando lector por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Lector, dado el tipo de este 
	 * Adiciona entradas al log de la aplicación
	 * @param tipoLector - El tipo del lector 
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarLectoresPorTipo (long tipoLector) 
	{
		log.info ("Eliminando lector por tipo: " + tipoLector);
		long resp = pp.eliminarLectoresPorTipo(tipoLector);
		log.info ("Eliminando lector por tipo: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Lector con un identificador dado
	 * @param id - El identificador del lector
	 * @return El objeto Lector, construido con base en las tuplas de la tabla LECTOR con el identificador dado
	 */
	public Lector darLectorPorId (long id)
	{
		log.info ("Dar información de un lector por id: " + id);
		Lector lector = pp.darLectorPorId(id);
		log.info ("Buscando lector por id: " + lector != null ? lector : "NO EXISTE");
		return lector;
	}

	/**
	 * Método que consulta los LECTORES por tipo
	 * @param tipo - El tipo del lector
	 * @return La lista de objetos Lector, construidos con base en las tuplas de la tabla LECTOR
	 */
	public List<Lector> darLectoresPorTipo (long tipo)
	{
		log.info ("Dar información de lectores por tipo: " + tipo);
		List<Lector> lectores = pp.darLectoresPorTipo(tipo);
		log.info ("Dar información de lectores por tipo: " + lectores.size() + " lectores con ese tipo existentes");
		return lectores;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Lector
	 * @return La lista de objetos Lector, construidos con base en las tuplas de la tabla LECTOR
	 */
	public List<Lector> darLectores ()
	{
		log.info ("Listando lectores");
		List<Lector> lectores = pp.darLectores();
		log.info ("Listando lectores: " + lectores.size() + " lectores existentes");
		return lectores;
	}

	/**
	 * Encuentra todos los lectores en AforoAndes y los devuelve como VOLector
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOLector con todos los lectores que conoce la aplicación, llenos con su información básica
	 */
	public List<VOLector> darVOLectores ()
	{
		log.info ("Generando los VO de Lector");
		List<VOLector> voLectores = new LinkedList<VOLector> ();
		for (Lector lector : pp.darLectores ())
		{
			voLectores.add (lector);
		}
		log.info ("Generando los VO de Lector: " + voLectores.size() + " lectores existentes");
		return voLectores;
	}

	/**
	 * Método que actualiza, de manera transaccional, el tipo del lector
	 * @param id - El identificador del lector a modificar 
	 * @param tipo - Tipo del lector 
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un lector con ese identificador no existe
	 */
	public long cambiarTipoLector (long id, long tipo)
	{
		log.info ("Cambiando tipo del lector: " + id);
		long cambios = pp.cambiarTipoLector(id, tipo);
		return cambios;

	}

	/* ****************************************************************
	 * 			Métodos para manejar los LOCALESCOMERCIALES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un local comercial
	 * Adiciona entradas al log de la aplicación
	 * @param idLocalComercial - El identificador del local
	 * @param capacidadNormal - La capacidad común del local comercial
	 * @param area - El área del local comercial
	 * @param tipoLocal - El tipo del local comercial
	 * @param activoBooleano - Si está en funcionamiento o no 
	 * @param idCentroComercial - El identificador del centro comercial al que pertenece el local comercial
	 * @return El objeto LocalComercial adicionado. null si ocurre alguna Excepción
	 */
	public LocalComercial adicionarLocalComercial (String idLocalComercial, long capacidadNormal, long area, long tipoLocal, boolean activoBooleano, String idCentroComercial) 
	{
		log.info ("Adicionando LocalComercial con identificador: " + idLocalComercial );
		LocalComercial localComercial = pp.adicionarLocalComercial(idLocalComercial, capacidadNormal, area, tipoLocal, activoBooleano, idCentroComercial);
		log.info ("Adicionando LocalComercial: " + localComercial);
		return localComercial;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla LOCALCOMERCIAL, dado el identificador del local comercial
	 * Adiciona entradas al log de la aplicación
	 * @param idLocalComercial - El identificador del local comercial
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarLocalComercialPorId (String idLocalComercial)  
	{
		log.info ("Eliminando LocalComercial por id: " + idLocalComercial);
		long resp = pp.eliminarLocalComercialPorId (idLocalComercial);		
		log.info ("Eliminando LocalComercial por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla LOCALCOMERCIAL, dada la actividad del local comercial
	 * Adiciona entradas al log de la aplicación
	 * @param activo - La actividad del local comercial
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarLocalComercialPorActividad (int activo)
	{
		log.info ("Eliminando LocalComercial por actividad: " + activo);
		long resp = pp.eliminarLocalComercialPorActividad(activo);	
		log.info ("Eliminando LocalComercial por actividad: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla LOCALCOMERCIAL que tienen el identificador dado
	 * @param idLocalComercial - El identificador del local comercial
	 * @return El objeto LOCALCOMERCIAL, construido con base en la tuplas de la tabla LOCALCOMERCIAL, que tiene el identificador dado
	 */
	public LocalComercial darLocalComercialPorId (String idLocalComercial)
	{
		log.info ("Dar información de un local comercial por id: " + idLocalComercial);
		LocalComercial localComercial = pp.darLocalComercialPorId(idLocalComercial);
		log.info ("Buscando local comercial por Id: " + localComercial != null ? localComercial : "NO EXISTE");
		return localComercial;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla LOCALCOMERCIAL que tienen el id del centro comercial dado
	 * @param idCentroComercial - El identificador del centro comercial en el que se encuentra el local
	 * @return La lista de objetos LOCALCOMERCIAL, construidos con base en las tuplas de la tabla LOCALCOMERCIAL
	 */
	public List<LocalComercial> darLocalesComercialesPorIDCC (String idCentroComercial)
	{
		log.info ("Dar información de locales comerciales por idCentroComercial: " + idCentroComercial);
		List<LocalComercial> localesComerciales = pp.darLocalesComercialesPorIDCC(idCentroComercial);
		log.info ("Dar información de locales comerciales por idCentroComercial: " + localesComerciales.size() + " locales comerciales con ese número de sanitarios existentes");
		return localesComerciales;

	}

	/**
	 * Método que consulta todas las tuplas en la tabla LOCALCOMERCIAL
	 * @return La lista de objetos LOCALCOMERCIAL, construidos con base en las tuplas de la tabla LOCALCOMERCIAL
	 */
	public List<LocalComercial> darLocalesComerciales ()
	{
		log.info ("Listando LocalComercial");
		List<LocalComercial> localesComerciales = pp.darLocalesComerciales();	
		log.info ("Listando LocalComercial: " + localesComerciales.size() + " locales comerciales existentes");
		return localesComerciales;
	}

	/**
	 * Encuentra todos los locales comerciales en AforoAndes y los devuelve como VOLocalComercial
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOLocalComercial con todos los locales comerciales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOLocalComercial> darVOLocalesComerciales ()
	{
		log.info ("Generando los VO de LocalComercial");
		List<VOLocalComercial> voLocalComercial = new LinkedList<VOLocalComercial> ();
		for (LocalComercial localComercial : pp.darLocalesComerciales ())
		{
			voLocalComercial.add (localComercial);
		}
		log.info ("Generando los VO de LocalComercial: " + voLocalComercial.size() + " locales comerciales existentes");
		return voLocalComercial;
	}

	/**
	 * Método que actualiza, de manera transaccional, el estado de actividad de un LOCALCOMERCIAL
	 * @param idLocal - El identificador del local que se quiere modificar
	 * @param activo - Nuevo estado de actividad del local comercial(1 si está activo o 0 de lo contrario)
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un local comercial con ese identificador no existe
	 */
	public long cambiarActividadLocalComercial (String idLocal, int activo)
	{
		log.info ("Cambiando estado de actividad del local comercial: " + idLocal);
		long cambios = pp.cambiarActividadLocalComercial (idLocal, activo);
		return cambios;
	}

	/**
	 * Método que actualiza, de manera transaccional, el área de un LOCALCOMERCIAL
	 * @param idLocal - El identificador del local que se quiere modificar
	 * @param area - Identificador de la nueva área de un local comercial
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un local comercial con ese identificador no existe
	 */
	public long cambiarAreaLocalComercial (String idLocal, long area)
	{
		log.info ("Cambiando área del local comercial: " + idLocal);
		long cambios = pp.cambiarAreaLocalComercial (idLocal, area);
		return cambios;

	}


	/* ****************************************************************
	 * 			Métodos para manejar los PARQUEADEROS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un parqueadero
	 * Adiciona entradas al log de la aplicación
	 * @param idParqueadero - El identificador del parqueadero
	 * @param capacidadNormal - El identificador de la capacidad normal del parqueadero
	 * @param area - El identificador del área del parqueadero
	 * @param idCentroComercial - El identificador del centro comercial del parqueadero
	 * @return El objeto Parqueadero adicionado. null si ocurre alguna Excepción
	 */
	public Parqueadero adicionarParqueadero (String idParqueadero, long capacidadNormal, long area, String idCentroComercial) 
	{
		log.info ("Adicionando Parqueadero con identificador: " + idParqueadero );
		Parqueadero parqueadero = pp.adicionarParqueadero(idParqueadero, capacidadNormal, area, idCentroComercial);
		log.info ("Adicionando Parqueadero: " + parqueadero);
		return parqueadero;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla PARQUEADERO, dado el identificador del parqueadero
	 * Adiciona entradas al log de la aplicación
	 * @param idParqueadero - El identificador del parqueadero
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarParqueaderoPorId (String idParqueadero)   
	{
		log.info ("Eliminando Parqueadero por id: " + idParqueadero);
		long resp = pp.eliminarParqueaderoPorId (idParqueadero);		
		log.info ("Eliminando Parqueadero por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PARQUEADERO que tienen el identificador dado
	 * @param idParqueadero - El identificador del parqueadero
	 * @return El objeto LOCALCOMERCIAL, construido con base en la tuplas de la tabla PARQUEADERO, que tiene el identificador dado
	 */
	public Parqueadero darParqueaderoPorId (String idParqueadero)
	{
		log.info ("Dar información de un parqueadero por id: " + idParqueadero);
		Parqueadero parqueadero = pp.darParqueaderoPorId(idParqueadero);
		log.info ("Buscando parqueadero por Id: " + parqueadero != null ? parqueadero : "NO EXISTE");
		return parqueadero;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PARQUEADERO
	 * @return La lista de objetos PARQUEADERO, construidos con base en las tuplas de la tabla PARQUEADERO
	 */
	public List<Parqueadero> darParqueaderos ()
	{
		log.info ("Listando Parqueadero");
		List<Parqueadero> parqueaderos = pp.darParqueaderos();	
		log.info ("Listando Parqueadero: " + parqueaderos.size() + "parqueaderos existentes");
		return parqueaderos;
	}

	/**
	 * Encuentra todos los locales comerciales en AforoAndes y los devuelve como VOParqueadero
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOParqueaderos con todas los parqueaderos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOParqueadero> darVOParqueaderos ()
	{
		log.info ("Generando los VO de Parqueadero");
		List<VOParqueadero> voParqueadero = new LinkedList<VOParqueadero> ();
		for (Parqueadero parqueadero : pp.darParqueaderos ())
		{
			voParqueadero.add (parqueadero);
		}
		log.info ("Generando los VO de Parqueadero: " + voParqueadero.size() + " parqueaderos existentes");
		return voParqueadero;
	}


	/**
	 * Método que actualiza, de manera transaccional, el area de un PARQUEADERO
	 * @param idParqueadero - El identificador del parqueadero que se quiere modificar
	 * @param area - Identificador de la nueva área de un parqueadero
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un parqueadero con ese identificador no existe
	 */
	public long cambiarAreaParqueadero (String idParqueadero, long area)	
	{
		log.info ("Cambiando área del parqueadero: " + idParqueadero);
		long cambios = pp.cambiarAreaParqueadero(idParqueadero, area);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los REGISTRANCARNET
	 *****************************************************************/

	/**
	 * Adiciona de manera persitente un registro de carnet
	 * Adiciona entradas al log de la aplicación
	 * @param idLector - El id del lector 
	 * @param tipoCarnet - El tipo del carnet del visitante
	 * @param idVisitante - El identificador del visitante
	 * @param fecha - La fecha de ingreso
	 * @param horaEntrada - La hora de ingreso
	 * @param horaSalida - La hora de salida 
	 * @return Las tuplas insertadas 
	 * @return El objeto TipoCarnet adicionado. null si ocurre alguna Excepción
	 */
	public RegistranCarnet adicionarRegistranCarnet(String idLector, long tipoCarnet, String idVisitante, Timestamp fecha, long horaEntrada, long horaSalida )
	{
		log.info ("Adicionando RegistranCarnet con lector: " + idLector + " y visitante: " + idVisitante);
		RegistranCarnet registranCarnet = pp.adicionarRegistranCarnet(idLector, tipoCarnet, idVisitante, fecha, horaEntrada, horaSalida);
		log.info ("Adicionando RegistranCarnet: " + registranCarnet);
		return registranCarnet;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RegistranCarnet
	 * Adiciona entradas al log de la aplicación 
	 * @param idLector - El identificador del lector por el cual ingresa el visitante
	 * @param tipoCarnet - El tipo del carnet del visitante
	 * @param idVisitante - El identificador del visitante
	 * @param fecha - La fecha de ingreso
	 * @param horaentrada - Hora de entrada 
	 * @param horasalida - Hora de salida 
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarRegistranCarnet(String idLector, long tipoCarnet, String idVisitante, Timestamp fecha, long horaentrada, long horasalida)
	{
		log.info ("Eliminando RegistranCarnet del lector: " + idLector + " y visitante: " + idVisitante);
		long resp = pp.eliminarRegistranCarnet(idLector, tipoCarnet, idVisitante, fecha, horaentrada, horasalida);
		log.info ("Eliminando RegistranCarnet: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta los REGISTRANCARNET por lector
	 * @param idLector - El id del lector por el cual quedó registrada la visita
	 * @return La lista de objetos RegistranCarnet, construidos con base en las tuplas de la tabla REGISTRANCARNET
	 */
	public List<RegistranCarnet> darRegistranCarnetPorLector (String idLector) 
	{
        log.info ("Dar información de RegistranCarnet por idLector: " + idLector);
        List<RegistranCarnet> registros = pp.darRegistranCarnetPorLector(idLector);
        log.info ("Dar información de RegistranCarnet por idLector: " + registros.size() + " registros con ese idLector existentes");
        return registros;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla RegistranCarnet con un identificador del visitante
	 * @param idVisitante - El identificador del visitante
	 * @return El objeto RegistranCarnet, construido con base en las tuplas de la tabla REGISTRANCARNET con el identificador dado
	 */
	public List<RegistranCarnet> darRegistranCarnetPorIdVisitante (String idVisitante)
	{
        log.info ("Dar información de RegistranCarnet por idVisitante: " + idVisitante);
        List<RegistranCarnet> registros = pp.darRegistranCarnetPorIdVisitante(idVisitante);
        log.info ("Dar información de RegistranCarnet por idVisitante: " + registros.size() + " registros con ese idVisitante existentes");
        return registros;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla RegistranCarnet con un identificador de visitante y una fecha o rango de fechas
	 * @param idVisitante - El identificador del visitante
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta o null si solo se requiere una fecha
	 * @return El objeto RegistranCarnet, construido con base en las tuplas de la tabla REGISTRANCARNET con el identificador dado
	 */
	public List<RegistranCarnet> darRegistranCarnetPorIdVisitanteFecha (String idVisitante, Timestamp fechaInicio, Timestamp fechaFin)
	{
        log.info ("Dar información de RegistranCarnet por idVisitante: " + idVisitante);
        List<RegistranCarnet> registros = pp.darRegistranCarnetPorIdVisitanteFecha(idVisitante, fechaInicio, fechaFin);
        log.info ("Dar información de RegistranCarnet por idVisitante: " + registros.size() + " registros con ese idVisitante y fecha(s) existentes");
        return registros;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla RegistranCarnet
	 * @return La lista de objetos RegistranCarnet, construidos con base en las tuplas de la tabla REGISTRANCARNET
	 */
	public List<RegistranCarnet> darRegistranCarnet ()
	{
        log.info ("Listando RegistranCarnet");
        List<RegistranCarnet> registros = pp.darRegistranCarnet();	
        log.info ("Listando RegistranCarnet: " + registros.size() + " registros existentes");
        return registros;
	}
	
	/**
	 * Encuentra todos los registros de carnet en AforoAndes y los devuelve como VORegistranCarnet
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VORegistranCarnet con todos los registros de carnet que conoce la aplicación, llenos con su información básica
	 */
	public List<VORegistranCarnet> darVORegistranCarnet ()
	{
        log.info ("Generando los VO de RegistranCarnet");
         List<VORegistranCarnet> voRegistranCarnet = new LinkedList<VORegistranCarnet> ();
        for (RegistranCarnet registro : pp.darRegistranCarnet ())
        {
        	voRegistranCarnet.add (registro);
        }
        log.info ("Generando los VO de RegistranCarnet: " + voRegistranCarnet.size() + " registros existentes");
       return voRegistranCarnet;
	}
	
	/**
	 * Método que actualiza, de manera transaccional, la hora de salida de un registro
	 * @param idLector - Lector donde se realizó el registro de la visita
	 * @param idVisitante - Identificador del visitante 
	 * @param fecha - La fecha en la que se realizó el registro
	 * @param horaEntrada - La hora de entrada de la visita
	 * @param horaSalida - La hora de salida de la visita
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un registro con ese identificador no existe
	 */
	public long cambiarHoraSalidaRegistranCarnet (String idLector, String idVisitante, Timestamp fecha, long horaEntrada, long horaSalida) 
	{
        log.info ("Cambiando hora de salida del registro del visitante: " + idVisitante);
        long cambios = pp.cambiarHoraSalidaRegistranCarnet(idLector, idVisitante, fecha, horaEntrada, horaSalida);
        return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los REGISTRANVEHICULO
	 *****************************************************************/

	/**
	 * Adiciona de manera persitente un registro de carnet
	 * Adiciona entradas al log de la aplicación
	 * @param idLector - El id del lector 
	 * @param vehiculo - La placa del vehículo ingresado
	 * @param fecha - La fecha de ingreso
	 * @param horaEntrada - La hora de ingreso
	 * @param horaSalida - La hora de salida 
	 * @return Las tuplas insertadas 
	 * @return El objeto RegistranVehiculo adicionado. null si ocurre alguna Excepción
	 */
	public RegistranVehiculo adicionarRegistranVehiculo (String idLector, String vehiculo, Timestamp fecha, long horaEntrada, long horaSalida )
	{
		log.info ("Adicionando RegistranVehiculo con lector: " + idLector + " y vehículo: " + vehiculo);
		RegistranVehiculo registranVehiculo = pp.adicionarRegistranVehiculo(idLector, vehiculo, fecha, horaEntrada, horaSalida);
		log.info ("Adicionando RegistranVehiculo: " + registranVehiculo);
		return registranVehiculo;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RegistranVehiculo
	 * Adiciona entradas al log de la aplicación 
	 * @param idLector - El identificador del lector por el cual ingresa el visitante
	 * @param tipoCarnet - El tipo del carnet del visitante
	 * @param vehiculo - La placa del vehiculo registrado 
	 * @param fecha - La fecha de ingreso
	 * @param horaentrada - Hora de entrada 
	 * @param horasalida - Hora de salida 
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarRegistranVehiculo(String idLector, long tipoCarnet, String idVisitante, Timestamp fecha, long horaentrada, long horasalida)
	{
		log.info ("Eliminando RegistranVehiculo del lector: " + idLector + " y vehículo: " + idVisitante);
		long resp = pp.eliminarRegistranCarnet(idLector, tipoCarnet, idVisitante, fecha, horaentrada, horasalida);
		log.info ("Eliminando RegistranVehiculo: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta los REGISTRANVEHICULO por lector
	 * @param idLector - El id del lector por el cual quedó registrada la visita
	 * @return La lista de objetos RegistranVehiculo, construidos con base en las tuplas de la tabla REGISTRANVEHICULO
	 */
	public List<RegistranVehiculo> darRegistranVehiculoPorLector (String idLector) 
	{
        log.info ("Dar información de RegistranVehiculo por idLector: " + idLector);
        List<RegistranVehiculo> registros = pp.darRegistranVehiculoPorLector(idLector);
        log.info ("Dar información de RegistranVehiculo por idLector: " + registros.size() + " registros con ese idLector existentes");
        return registros;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla RegistranCarnet con un identificador del visitante
	 * @param vehiculo - La placa del vehículo ingresado 
	 * @return El objeto RegistranVehiculo, construido con base en las tuplas de la tabla REGISTRANVEHICULO con el identificador dado
	 */
	public List<RegistranVehiculo> darRegistranVehiculoPorPlaca (String vehiculo)
	{
        log.info ("Dar información de RegistranVehiculo por vehículo: " + vehiculo);
        List<RegistranVehiculo> registros = pp.darRegistranVehiculoPorPlaca(vehiculo);
        log.info ("Dar información de RegistranVehiculo por vehículo: " + registros.size() + " registros con ese vehículo existentes");
        return registros;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla RegistranCarnet con un identificador de visitante y una fecha o rango de fechas
	 * @param id - El identificador del visitante
	 * @param fechaInicio - La fecha de inicio del rango de consulta
	 * @param fechaFin - La fecha de fin del rango de consulta o null si solo se requiere una fecha
	 * @return El objeto RegistranCarnet, construido con base en las tuplas de la tabla REGISTRANVEHICULO con el identificador dado
	 */
	public List<RegistranVehiculo> darRegistranVehiculoPorPlacaFecha (String vehiculo, Timestamp fechaInicio, Timestamp fechaFin)
	{
        log.info ("Dar información de RegistranCarnet por vehículo: " + vehiculo);
        List<RegistranVehiculo> registros = pp.darRegistranVehiculoPorPlacaFecha(vehiculo, fechaInicio, fechaFin);
        log.info ("Dar información de RegistranCarnet por vehículo: " + registros.size() + " registros con ese vehículo y fecha(s) existentes");
        return registros;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla RegistranVehiculo
	 * @return La lista de objetos RegistranVehiculo, construidos con base en las tuplas de la tabla REGISTRANVEHICULO
	 */
	public List<RegistranVehiculo> darRegistranVehiculo ()
	{
        log.info ("Listando RegistranVehiculo");
        List<RegistranVehiculo> registros = pp.darRegistranVehiculo();	
        log.info ("Listando RegistranVehiculo: " + registros.size() + " registros existentes");
        return registros;
	}
	
	/**
	 * Encuentra todos los registros de vehículo en AforoAndes y los devuelve como VORegistranVehiculo
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VORegistranVehiculo con todos los registros de vehículo que conoce la aplicación, llenos con su información básica
	 */
	public List<VORegistranVehiculo> darVORegistranVehiculo ()
	{
        log.info ("Generando los VO de RegistranVehiculo");
         List<VORegistranVehiculo> voRegistranVehiculo = new LinkedList<VORegistranVehiculo> ();
        for (RegistranVehiculo registro : pp.darRegistranVehiculo ())
        {
        	voRegistranVehiculo.add (registro);
        }
        log.info ("Generando los VO de RegistranVehiculo: " + voRegistranVehiculo.size() + " registros existentes");
       return voRegistranVehiculo;
	}
	
	/**
	 * Método que actualiza, de manera transaccional, la hora de salida de un registro
	 * @param idLector - Lector donde se realizó el registro de la visita
	 * @param vehiculo - Placa del vehículo 
	 * @param fecha - La fecha en la que se realizó el registro
	 * @param horaEntrada - La hora de entrada de la visita
	 * @param horaSalida - La hora de salida de la visita
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un registro con ese identificador no existe
	 */
	public long cambiarHoraSalidaRegistranVehiculo (String idLector, String vehiculo, Timestamp fecha, long horaEntrada, long horaSalida) 
	{
        log.info ("Cambiando hora de salida del registro del vehículo: " + vehiculo);
        long cambios = pp.cambiarHoraSalidaRegistranVehiculo(idLector, vehiculo, fecha, horaEntrada, horaSalida);
        return cambios;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE CARNET
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un tipo de carnet
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo de carnet
	 * @return El objeto TipoCarnet adicionado. null si ocurre alguna Excepción
	 */
	public TipoCarnet adicionarTipoCarnet(String tipo)
	{
		log.info ("Adicionando Tipo de carnet: " + tipo);
		TipoCarnet tipoCarnet = pp.adicionarTipoCarnet(tipo);	
		log.info ("Adicionando Tipo de carnet: " + tipoCarnet);
		return tipoCarnet;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoCarnet, dado el nombre del tipo de carnet
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo de carnet
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoCarnetPorTipo (String tipo) 
	{
		log.info ("Eliminando Tipo de carnet por tipo: " + tipo);
		long resp = pp.eliminarTipoCarnetPorTipo(tipo);
		log.info ("Eliminando Tipo de carnet por tipo: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoCarnet, dado el identificador del tipo de carnet
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoCarnet - El identificador del tipo de carnet
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoCarnetPorId (long idTipoCarnet) 
	{
		log.info ("Eliminando Tipo de carnet por id: " + idTipoCarnet);
		long resp = pp.eliminarTipoCarnetPorId(idTipoCarnet);	
		log.info ("Eliminando Tipo de carnet por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoCarnet
	 * @return La lista de objetos TipoCarnet, construidos con base en las tuplas de la tabla TipoCarnet
	 */
	public List<TipoCarnet> darTiposCarnet ()
	{
		log.info ("Consultando Tipos de carnet");
		List<TipoCarnet> tiposCarnet = pp.darTiposCarnet();
		log.info ("Consultando Tipos de carnet: " + tiposCarnet.size() + " existentes");
		return tiposCarnet;
	}

	/**
	 * Encuentra todos los tipos de carnet en AforoAndes y los devuelve como una lista de VOTipoCarnet
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoCarnet con todos los tipos de carnet que conoce la aplicación, llenos con su información básica
	 */
	public List<VOTipoCarnet> darVOTiposCarnet ()
	{
		log.info ("Generando los VO de Tipos de carnet");        
		List<VOTipoCarnet> voTipos = new LinkedList<VOTipoCarnet> ();
		for (TipoCarnet tc : pp.darTiposCarnet ())
		{
			voTipos.add (tc);
		}
		log.info ("Generando los VO de Tipos de carnet: " + voTipos.size() + " existentes");
		return voTipos;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoCarnet que tienen el nombre dado
	 * @param tipo - El nombre del tipo de carnet
	 * @return La lista de objetos TipoCarnet, construidos con base en las tuplas de la tabla TipoCarnet
	 */
	public List<TipoCarnet> darTiposCarnetPorTipo (String tipo)
	{
		log.info ("Dar información de tipos de carnet por tipo: " + tipo);
		List<TipoCarnet> tiposCarnet = pp.darTiposCarnetPorTipo(tipo);
		log.info ("Dar información de tipos de carnet por tipo: " + tiposCarnet.size() + " tipos de carnet con ese tipo existentes");
		return tiposCarnet;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoCarnet con un identificador dado
	 * @param idTipoCarnet - El identificador del tipo de carnet
	 * @return El objeto TipoCarnet, construido con base en las tuplas de la tabla TipoCarnet con el identificador dado
	 */
	public TipoCarnet darTipoCarnetPorId (long idTipoCarnet)
	{
		log.info ("Dar información de tipos de carnet por id: " + idTipoCarnet);
		TipoCarnet tipoCarnet = pp.darTipoCarnetPorId(idTipoCarnet);
		log.info ("Buscando tipo de carnet por Id: " + tipoCarnet != null ? tipoCarnet : "NO EXISTE");
		return tipoCarnet;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE LECTOR
	 *****************************************************************/

	/**
	 * Adiciona de manera pesistente un tipo de lector
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo de lector
	 * @return El objeto TipoCarnet adicionado. null si ocurre alguna Excepción
	 */
	public TipoLector adicionarTipoLector(String tipo)
	{
		log.info ("Adicionando Tipo de lector: " + tipo);
		TipoLector tipoLector = pp.adicionarTipoLector(tipo);	
		log.info ("Adicionando Tipo de lector: " + tipoLector);
		return tipoLector;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoLector, dado el nombre del tipo de lector
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo de lector
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoLectorPorTipo (String tipo) 
	{
		log.info ("Eliminando Tipo de lector por tipo: " + tipo);
		long resp = pp.eliminarTipoLectorPorTipo(tipo);
		log.info ("Eliminando Tipo de lector por tipo: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoLector, dado el identificador del tipo de lector
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoLector - El identificador del tipo de lector
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoLectorPorId (long idTipoLector) 
	{
		log.info ("Eliminando Tipo de lector por id: " + idTipoLector);
		long resp = pp.eliminarTipoLectorPorId(idTipoLector);	
		log.info ("Eliminando Tipo de lector por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoLector
	 * @return La lista de objetos TipoLector, construidos con base en las tuplas de la tabla TipoLector
	 */
	public List<TipoLector> darTiposLector ()
	{
		log.info ("Consultando Tipos de lector");
		List<TipoLector> tiposLector = pp.darTiposLector();
		log.info ("Consultando Tipos de lector: " + tiposLector.size() + " existentes");
		return tiposLector;
	}

	/**
	 * Encuentra todos los tipos de lector en AforoAndes y los devuelve como una lista de VOTipoLector
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoLector con todos los tipos de lector que conoce la aplicación, llenos con su información básica
	 */
	public List<VOTipoLector> darVOTiposLector ()
	{
		log.info ("Generando los VO de Tipos de lector");        
		List<VOTipoLector> voTipos = new LinkedList<VOTipoLector> ();
		for (TipoLector tl : pp.darTiposLector ())
		{
			voTipos.add (tl);
		}
		log.info ("Generando los VO de Tipos de lector: " + voTipos.size() + " existentes");
		return voTipos;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoLector que tienen el nombre dado
	 * @param tipo - El nombre del tipo de lector
	 * @return La lista de objetos TipoLector, construidos con base en las tuplas de la tabla TipoLector
	 */
	public List<TipoLector> darTiposLectorPorTipo (String tipo)
	{
		log.info ("Dar información de tipos de lector por tipo: " + tipo);
		List<TipoLector> tiposLector = pp.darTiposLectorPorTipo(tipo);
		log.info ("Dar información de tipos de lector por tipo: " + tiposLector.size() + " tipos de lector con ese tipo existentes");
		return tiposLector;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoLector con un identificador dado
	 * @param idTipoLector - El identificador del tipo de lector
	 * @return El objeto TipoLector, construido con base en las tuplas de la tabla TipoLector con el identificador dado
	 */
	public TipoLector darTipoLectorPorId (long idTipoLector)
	{
		log.info ("Dar información de tipos de lector por id: " + idTipoLector);
		TipoLector tipoLector = pp.darTipoLectorPorId(idTipoLector);
		log.info ("Buscando tipo de lector por Id: " + tipoLector != null ? tipoLector : "NO EXISTE");
		return tipoLector;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE LOCAL
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoLocal
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo del local
	 * @param horaApertura - La hora de apertura del tipo del local
	 * @param minutoApertura - El minuto de apertura del tipo del local
	 * @param horaCierre - La hora de cierre del tipo del local
	 * @param minutoCierre - El minuto de cierre del tipo de local
	 * @return El objeto TipoLocal adicionado. null si ocurre alguna Excepción
	 */
	public TipoLocal adicionarTipoLocal( String tipo, int horaApertura, int minutoApertura, int horaCierre, int minutoCierre )
	{
		long horarioApertura = pp.darHorarioPorHorayMinuto(horaApertura, minutoApertura).getId();
		long horarioCierre = pp.darHorarioPorHorayMinuto(horaCierre, minutoCierre).getId();
		log.info ("Adicionando Tipo de local: " + tipo);
		TipoLocal tipoLocal = pp.adicionarTipoLocal(tipo, horarioApertura, horarioCierre);
		log.info ("Adicionando Tipo de local: " + tipoLocal);
		return tipoLocal;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoLocal, dado el nombre del tipo del local
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo del local
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoLocalPorTipo (String tipo) 
	{
		log.info ("Eliminando Tipo de local por tipo: " + tipo);
		long resp = pp.eliminarTipoLocalPorTipo(tipo);
		log.info ("Eliminando Tipo de local por tipo: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoLocal, dado el identificador del tipo del local
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoLocal - El identificador del tipo del local
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoLocalPorId (long idTipoLocal) 
	{
		log.info ("Eliminando Tipo de local por id: " + idTipoLocal);
		long resp = pp.eliminarTipoLocalPorId(idTipoLocal);	
		log.info ("Eliminando Tipo de local por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoLocal
	 * @return La lista de objetos TipoLocal, construidos con base en las tuplas de la tabla TipLocal
	 */
	public List<TipoLocal> darTiposLocal ()
	{
		log.info ("Consultando Tipos de lector");
		List<TipoLocal> tiposLocal = pp.darTiposLocal();
		log.info ("Consultando Tipos de local: " + tiposLocal.size() + " existentes");
		return tiposLocal;
	}

	/**
	 * Encuentra todos los tipos de local en AforoAndes y los devuelve como una lista de VOTipoLocal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoLocal con todos los tipos de local que conoce la aplicación, llenos con su información básica
	 */
	public List<VOTipoLocal> darVOTiposLocal ()
	{
		log.info ("Generando los VO de Tipos de local");        
		List<VOTipoLocal> voTipos = new LinkedList<VOTipoLocal> ();
		for (TipoLocal tl : pp.darTiposLocal ())
		{
			voTipos.add (tl);
		}
		log.info ("Generando los VO de Tipos de local: " + voTipos.size() + " existentes");
		return voTipos;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoLocal que tienen el nombre dado
	 * @param tipo - El nombre del tipo del local
	 * @return El objeto TipoLocal, construido con base en las tuplas de la tabla TipoLocal con el tipo dado
	 */
	public TipoLocal darTipoLocalPorTipo (String tipo)
	{
		log.info ("Dar información de tipos de local por tipo: " + tipo);
		TipoLocal tipoLocal = pp.darTipoLocalPorTipo(tipo);
		log.info ("Buscando tipo de local por tipo: " + tipoLocal != null ? tipoLocal : "NO EXISTE");
		return tipoLocal;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoLector con un identificador dado
	 * @param idTipoLector - El identificador del tipo de lector
	 * @return El objeto TipoLocal, construido con base en las tuplas de la tabla TipoLocal con el identificador dado
	 */
	public TipoLocal darTipoLocalPorId (long idTipoLocal)
	{
		log.info ("Dar información de tipos de local por id: " + idTipoLocal);
		TipoLocal tipoLocal = pp.darTipoLocalPorId(idTipoLocal);
		log.info ("Buscando tipo de local por Id: " + tipoLocal != null ? tipoLocal : "NO EXISTE");
		return tipoLocal;
	}

	/**
	 * Método que actualiza, de manera transaccional, la hora de apertura
	 * @param id - El identificador del tipo local a modificar
	 * @param horaApertura - El identificador del horario de apertura del tipo de local
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un tipo de local con ese identificador no existe
	 */
	public long cambiarHoraAperturaTipoLocal (long id, long horaApertura)
	{
        log.info ("Cambiando hora de apertura del tipo de local: " + id);
        long cambios = pp.cambiarHoraAperturaTipoLocal(id, horaApertura);
        return cambios;
	}

	/**
	 * Método que actualiza, de manera transaccional, la hora limite
	 * @param id - El identificador del visitante a modificar
	 * @param horaCierre - El identificador de la hora de cierre del tipo de local
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un tipo de local con ese identificador no existe
	 */
	public long cambiarHoraCierreTipoLocal (long id, long horaCierre)
	{
        log.info ("Cambiando hora de cierre del tipo de local: " + id);
        long cambios = pp.cambiarHoraCierreTipoLocal(id, horaCierre);
        return cambios;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE VISITANTE
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoVisitante
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo del visitante
	 * @param horainicio - La hora de inicio de circulación del tipo del visitante
	 * @param minutoInicio - El minuto de inicio de circulación del tipo del visitante
	 * @param horalimite - La hora límite de circulación del tipo del visitante
	 * @param minutoLimite - El minuto límite de circulación del tipo del visitante
	 * @return El objeto TipoVisitante adicionado. null si ocurre alguna Excepción
	 */
	public TipoVisitante adicionarTipoVisitante( String tipo, int horainicio, int minutoInicio, int horalimite, int minutoLimite )
	{
		long horarioinicio = pp.darHorarioPorHorayMinuto(horainicio, minutoInicio).getId();
		long horarioLimite = pp.darHorarioPorHorayMinuto(horalimite, minutoLimite).getId();
		log.info ("Adicionando Tipo de visitante: " + tipo);
		TipoVisitante tipoVisitante = pp.adicionarTipoVisitante(tipo, horarioinicio, horarioLimite);
		log.info ("Adicionando Tipo de visitante: " + tipoVisitante);
		return tipoVisitante;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoVisitante, dado el nombre del tipo de lector
	 * Adiciona entradas al log de la aplicación
	 * @param tipo - El nombre del tipo de lector
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoVisitantePorTipo (String tipo) 
	{
		log.info ("Eliminando Tipo de visitante por tipo: " + tipo);
		long resp = pp.eliminarTipoVisitantePorTipo(tipo);
		log.info ("Eliminando Tipo de visitante por tipo: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoVisitante, dado el identificador del tipo de visitante
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoVisitnte - El identificador del tipo del visitante
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarTipoVisitantePorId (long idTipoVisitante) 
	{
		log.info ("Eliminando Tipo de visitante por id: " + idTipoVisitante);
		long resp = pp.eliminarTipoVisitantePorId(idTipoVisitante);	
		log.info ("Eliminando Tipo de visitante por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoVisitante
	 * @return La lista de objetos TipoVisitante, construidos con base en las tuplas de la tabla TipoVisitante
	 */
	public List<TipoVisitante> darTiposVisitante ()
	{
		log.info ("Consultando Tipos de visitante");
		List<TipoVisitante> tiposVisitante = pp.darTiposVisitante();
		log.info ("Consultando Tipos de visitante: " + tiposVisitante.size() + " existentes");
		return tiposVisitante;
	}

	/**
	 * Encuentra todos los tipos de visitante en AforoAndes y los devuelve como una lista de VOTipoVisitante
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoVisitante con todos los tipos de visitante que conoce la aplicación, llenos con su información básica
	 */
	public List<VOTipoVisitante> darVOTiposVisitante ()
	{
		log.info ("Generando los VO de Tipos de visitante");        
		List<VOTipoVisitante> voTipos = new LinkedList<VOTipoVisitante> ();
		for (TipoVisitante tv : pp.darTiposVisitante ())
		{
			voTipos.add (tv);
		}
		log.info ("Generando los VO de Tipos de visitante: " + voTipos.size() + " existentes");
		return voTipos;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoVisitante que tienen el nombre dado
	 * @param tipo - El nombre del tipo de visitante
	 * @return El objeto TipoVisitante, construido con base en las tuplas de la tabla TipoVisitante con el tipo dado
	 */
	public TipoVisitante darTipoVisitantePorTipo (String tipo)
	{
		log.info ("Dar información de tipos de visitante por tipo: " + tipo);
		TipoVisitante tipoVisitante = pp.darTipoVisitantePorTipo(tipo);
		log.info ("Buscando tipo de visitante por tipo: " + tipo != null ? tipo : "NO EXISTE");
		return tipoVisitante;
	}

	/**
	 * Método que actualiza, de manera transaccional, la hora de inicio
	 * @param id - El identificador del tipo de visitante a modificar
	 * @param horainicio - El identificador del horario de inicio
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un tipo de visitante con ese identificador no existe
	 */
	public long cambiarHoraInicioTipoVisitante (long id, long horainicio)
	{
        log.info ("Cambiando hora de inicio del tipo de visitante: " + id);
        long cambios = pp.cambiarHoraInicioTipoVisitante(id, horainicio);
        return cambios;
	}
	
	/**
	 * Método que actualiza, de manera transaccional, la hora limite
	 * @param id - El identificador del tipo de visitante a modificar
	 * @param horalimite - El identificador de la hora límite del tipo de visitante
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un tipo de visitante con ese identificador no existe
	 */
	public long cambiarHoraLimiteTipoVisitante (long id, long horalimite)
	{
        log.info ("Cambiando hora límite del tipo de visitante: " + id);
        long cambios = pp.cambiarHoraInicioTipoVisitante(id, horalimite);
        return cambios;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar los VEHICULOS
	 *****************************************************************/

	/**
	 * Adicionar de manera persistente un vehiculo
	 * @param placa - La placa con la que se identifica el vehículo
	 * @param caracteristicas - Las característias del vehículo
	 * @param dueño - El dueño del vehículo
	 * @return Las tuplas insertadas
	 * @return El objeto VEHICULO adicionado. null si ocurre alguna Excepción
	 */
	public Vehiculo adicionarVehiculo( String placa, String caracteristicas, String dueño)
	{
		log.info ("Adicionando vehículo: " + placa);
		Vehiculo vehiculo = pp.adicionarVehiculo(placa, caracteristicas, dueño);
		log.info ("Adicionando vehículo: " + vehiculo);
		return vehiculo;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Vehiculo, dado su placa 
	 * Adiciona entradas al log de la aplicación
	 * @param placa - La placa del vehiculo
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarVehiculoPorPlaca(String placa) 
	{
		log.info ("Eliminando vehículo por placa: " + placa);
		long resp = pp.eliminarVehiculoPorPlaca(placa);
		log.info ("Eliminando vehículo por placa: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Vehiculo, dado su dueño
	 * Adiciona entradas al log de la aplicación
	 * @param dueño - El dueño del vehiculo
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarVehiculoPorDueño(String dueño) 
	{
		log.info ("Eliminando vehículo por propietario: " + dueño);
		long resp = pp.eliminarVehiculoPorDueño(dueño);
		log.info ("Eliminando vehículo por propietario: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Vehiculo con un identificador dado
	 * @param placa - La placa del vehiculo
	 * @return El objeto Vehiculo, construido con base en las tuplas de la tabla VEHICULO con la placa dada
	 */
	public Vehiculo darVehiculoPorPlaca (String placa)
	{
		log.info ("Dar información de un vehiculo por placa: " + placa);
		Vehiculo vehiculo = pp.darVehiculoPorPlaca(placa);
		log.info ("Buscando vehiculo por placa: " + vehiculo != null ? vehiculo : "NO EXISTE");
		return vehiculo;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Vehiculo con un propietario dado
	 * @param dueño - El dueño del vehículo
	 * @return La lista de objetos Vehiculo, construidos con base en las tuplas de la tabla Vehiculo con un propietario dado
	 */
	public List<Vehiculo> darVehiculosPorDueño (String dueño)	
	{
		log.info ("Dar información de vehículos por propietario: " + dueño);
		List<Vehiculo> vehiculos = pp.darVehiculosPorDueño(dueño);
		log.info ("Dar información de vehículos por propietario: " + vehiculos.size() + " vehículos con ese propietario existentes");
		return vehiculos;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Vehiculo
	 * @return La lista de objetos Vehiculo, construidos con base en las tuplas de la tabla Vehiculo
	 */
	public List<Vehiculo> darVehiculos ()
	{
		log.info ("Listando vehículos");
		List<Vehiculo> vehiculos = pp.darVehiculos ();	
		log.info ("Listando vehículos: " + vehiculos.size() + " vehículos existentes");
		return vehiculos;
	}

	/**
	 * Encuentra todos los vehículos en AforoAndes y los devuelve como VOVehiculo
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOVehiculo con todos los vehículos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOVehiculo> darVOVehiculos ()
	{
		log.info ("Generando los VO de vehículos");
		List<VOVehiculo> voVehiculos = new LinkedList<VOVehiculo> ();
		for (Vehiculo vehiculo : pp.darVehiculos ())
		{
			voVehiculos.add (vehiculo);
		}
		log.info ("Generando los VO de vehículos: " + voVehiculos.size() + " vehículos existentes");
		return voVehiculos;
	}

	/**
	 * Método que actualiza, de manera transaccional, las caracteristicas del vehiculo
	 * @param placa - La placa del vehiculo a modificar
	 * @param caracteristicas - Las caracteristicas nuevas 
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un vehículo con ese identificador no existe
	 */
	public long cambiarCaracteristicasVehiculo (String placa, String caracteristicas)
	{
		log.info ("Cambiando las características del vehículo: " + placa);
		long cambios = pp.cambiarCaracteristicasVehiculo(placa, caracteristicas);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los VISITANTES
	 *****************************************************************/

	/**
	 * Adicionar de manera persistente un visitante
	 * @param identificacion - La identificación de cada visitante del centro comercial
	 * @param nombre - Nombre del visitante
	 * @param tipo - Tipo de visitante
	 * @param correo - Correo del visitante
	 * @param telefonoPropio - Telefono del visitante
	 * @param nombreEmergencia - Contacto de emergencia del visitante
	 * @param telefonoEmergencia - Telefono del contacto de emergencia del visitante
	 * @return El objeto VISITANTE adicionado. null si ocurre alguna Excepción
	 */
	public Visitante adicionarVisitante( String identificacion, String nombre, long tipo, String correo, String telefonoPropio, String nombreEmergencia, String telefonoEmergencia)
	{
		log.info ("Adicionando visitante: " + nombre);
		Visitante visitante = pp.adicionarVisitante(identificacion, nombre, tipo, correo, telefonoPropio, nombreEmergencia, telefonoEmergencia);
		log.info ("Adicionando visitante: " + visitante);
		return visitante;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Visitante, dado el identificador de este 
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del visitante
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarVisitantePorId(String id) 
	{
		log.info ("Eliminando visitante por identificación: " + id);
		long resp = pp.eliminarVisitantePorId(id);
		log.info ("Eliminando visitante: " + resp);
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Visitante, dado su nombre  
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del visitante 
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarVisitantePorNombre(String nombre) 
	{
		log.info ("Eliminando visitante por nombre: " + nombre);
		long resp = pp.eliminarVisitantePorNombre(nombre);
		log.info ("Eliminando visitante: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Visitante con un identificador dado
	 * @param id - El identificador del visitante
	 * @return El objeto Visitante, construido con base en las tuplas de la tabla VISITANTE con el identificador dado
	 */
	public Visitante darVisitantePorId (String id)
	{
		log.info ("Dar información de un visitante por identificación: " + id);
		Visitante visitante = pp.darVisitantePorId(id);
		log.info ("Buscando visitante por identificación: " + visitante != null ? visitante : "NO EXISTE");
		return visitante;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Visitante dado su nombre
	 * @param nombre - el nombre del visitante
	 * @return El objeto Visitante, construido con base en las tuplas de la tabla VISITANTE con el nombre dado
	 */
	public Visitante darVisitantePorNombre (String nombre)
	{
		log.info ("Dar información de un visitante por nombre: " + nombre);
		Visitante visitante = pp.darVisitantePorNombre(nombre);
		log.info ("Buscando visitante por identificación: " + visitante != null ? visitante : "NO EXISTE");
		return visitante;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Visitante
	 * @return La lista de objetos Visitante, construidos con base en las tuplas de la tabla VISITANTE
	 */
	public List<Visitante> darVisitantes ()
	{
		log.info ("Listando visitantes");
		List<Visitante> visitantes = pp.darVisitantes();	
		log.info ("Listando visitantes: " + visitantes.size() + " visitantes existentes");
		return visitantes;
	}

	/**
	 * Encuentra todos los bares en AforoAndes y los devuelce como VOVisitante
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bar con todos los visitantes que conoce la aplicación, llenos con su información básica
	 */
	public List<VOVisitante> darVOVisitantes ()
	{
		log.info ("Generando los VO de visitantes");
		List<VOVisitante> voVisitantes = new LinkedList<VOVisitante> ();
		for (Visitante visitante: pp.darVisitantes ())
		{
			voVisitantes.add (visitante);
		}
		log.info ("Generando los VO de visitantes: " + voVisitantes.size () + " visitantes existentes");
		return voVisitantes;
	}

	/**
	 * Método que actualiza, de manera transaccional, el contacto de emergencia del visitante
	 * @param id - El identificador del visitante a modificar
	 * @param nombreemergencia - nombre de emergencia nuevo
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un visitante con ese identificador no existe
	 */
	public long cambiarContactoEmergenciaVisitante (String id, String nombreemergencia)
	{
		log.info ("Cambiando contacto de emergencia del visitante: " + id);
		long cambios = pp.cambiarContactoEmergenciaVisitante(id, nombreemergencia);
		return cambios;
	}

	/**
	 * Método que actualiza, de manera transaccional, el telefono de emergencia del visitante
	 * @param id - El identificador del visitante a modificar
	 * @param telefono - telefono de emergencia nuevo
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un visitante con ese identificador no existe
	 */
	public long cambiarTelefonoEmergencia (String id, String telefono)
	{
		log.info ("Cambiando teléfono de emergencia del visitante: " + id);
		long cambios = pp.cambiarTelefonoEmergenciaVisitante(id, telefono);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las ZONAS DE CIRCULACIÓN
	 *****************************************************************/
	/**
	 * Adicionar de manera persistente una zona de circulación
	 * @param identificador - Identificador de la zona de circulación
	 * @param capacidadNormal - Capacidad normal de la zona de circulación
	 * @param idCentroComercial - El identificador del centro comercial al que pertenece la zona de circulación 
	 * @return Las tuplas insertadas
	 * @return El objeto  ZONACIRCULACION  adicionado. null si ocurre alguna Excepción
	 */
	public ZonaCirculacion adicionarZona(String identificador, int capacidadNormal, String idCentroComercial )
	{
		log.info ("Adicionando Zona de circulación: " + identificador);
		ZonaCirculacion zonaCirculacion = pp.adicionarZona(identificador, capacidadNormal, idCentroComercial);
		log.info ("Adicionando Zona de circulación: " + zonaCirculacion);
		return zonaCirculacion;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ZonaCirculacion, dado su identificador 
	 * Adiciona entradas al log de la aplicación
	 * @param id - Identificador de la zona de circulación
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarZonaPorId(String id) 
	{
		log.info ("Eliminando Zona de circulación por id: " + id);
		long resp = pp.eliminarZonaPorId(id)	;
		log.info ("Eliminando Zona de circulación por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ZonaCirculacion con un identificador dado
	 * @param id - Identificador de la zona de circulacion
	 * @return El objeto ZonaCirculacion, construido con base en las tuplas de la tabla ZONACIRCULACION con el identificador dado
	 */
	public ZonaCirculacion darZonaPorId (String id)
	{
		log.info ("Dar información de un zona de circulación por id: " + id);
		ZonaCirculacion zonaCirculacion = pp.darZonaPorID(id);
		log.info ("Buscando zona de circulación por Id: " + zonaCirculacion != null ? zonaCirculacion : "NO EXISTE");
		return zonaCirculacion;
	}

	/**
	 * Encuentra todas las zonas de circulación en AforoAndes y los devuelve como una lista de VOZonaCirculacion
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOZonaCirculacion con todas las zonas de circulación que conoce la aplicación, llenos con su información básica
	 */
	public List<VOZonaCirculacion> darVOZonasCirculacion ()
	{
		log.info ("Generando los VO de zonas de circulación");        
		List<VOZonaCirculacion> voZonasCirculacion = new LinkedList<VOZonaCirculacion> ();
		for (ZonaCirculacion zonaCirculacion : pp.darZonasCirculacion())
		{
			voZonasCirculacion.add (zonaCirculacion);
		}
		log.info ("Generando los VO de zonas de circulación: " + voZonasCirculacion.size() + " zonas de circulación existentes");
		return voZonasCirculacion;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ZonaCirculacion
	 * @return La lista de objetos ZonaCirculacion, construidos con base en las tuplas de la tabla ZonaCirculacion
	 */
	public List<ZonaCirculacion> darZonasCirculacion ()
	{
		log.info ("Consultando zonas de circulación");
		List<ZonaCirculacion> zonasCirculacion = pp.darZonasCirculacion();	
		log.info ("Consultando zonas de circulación: " + zonasCirculacion.size() + " zonas de circulación existentes");
		return zonasCirculacion;
	}

	/* ****************************************************************
	 * 	Métodos para manejar los Requerimientos Funcionales de Consulta
	 *****************************************************************/
	
	/**
	 * Método que realiza la consulta de los visitantes atendidos por un establecimiento en una fecha o rango de fechas
	 * @return La lista de objetos Visitante construidos de acuerdo a la consulta realizada
	 */
	public List<Visitante> RFC1Fecha(Timestamp fechaInicio, Timestamp fechaFin, String idLocalComercial)
	{
		log.info ("Consultando visitantes atendidos por el establecimiento: " + idLocalComercial);
		List<Visitante> visitantes = pp.RFC1Fecha(fechaInicio, fechaFin, idLocalComercial);
		log.info ("Consultando visitantes: " + visitantes.size() + " visitantes atendidos en la(s) fecha(s)");
		return visitantes;
	}

	/**
	 * Método que realiza la consulta de los visitantes atendidos por un establecimiento en un rango de horas
	 * @return La lista de objetos Visitante construidos de acuerdo a la consulta realizada
	 */
	public List<RFC1Hora> RFC1Hora(Timestamp fecha, int horaInicio, int minutoInicio, int horaFin, int minutoFin, String idLocalComercial)
	{
		log.info ("Consultando visitantes atendidos por el establecimiento: " + idLocalComercial);
		List<RFC1Hora> visitantes = pp.RFC1Horas(fecha, horaInicio, minutoInicio, horaFin, minutoFin, idLocalComercial);
		log.info ("Consultando visitantes: " + visitantes.size() + " visitantes atendidos en el rango de horas");
		return visitantes;
	}
	
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AforoAndes
	 * @return Un arreglo con números que indican el número de tuplas borradas en las tablas 
	 */
	public long [] limpiarAforoAndes ()
	{
		log.info ("Limpiando la BD de AforoAndes");
		long [] borrrados = pp.limpiarAforoAndes();	
		log.info ("Limpiando la BD de AforoAndes: Listo!");
		return borrrados;
	}
}
