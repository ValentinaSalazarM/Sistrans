

--RFC 4 - MOSTRAR LOS ESTABLECIMIENTOS CON AFORO DISPONIBLE EN UNA FECHA Y HORA

SELECT LOCALCOMERCIAL.*, AFORO-NUM_VISITAS AS DISPONIBLE
FROM
(
    SELECT LECTOR.IDLOCALCOMERCIAL, COUNT(*) AS NUM_VISITAS
    FROM REGISTRANCARNET
    JOIN LECTOR
    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    WHERE FECHA = TO_DATE('2019/07/19','yyyy/mm/dd') AND HORA = 13 AND MINUTO = 17
    GROUP BY LECTOR.IDLOCALCOMERCIAL
) AUX
JOIN LOCALCOMERCIAL
ON LOCALCOMERCIAL.IDENTIFICADOR = AUX.IDLOCALCOMERCIAL
JOIN AREA
ON LOCALCOMERCIAL.AREA = AREA.ID
WHERE AFORO-NUM_VISITAS > 0;

--RFC 4 - MOSTRAR LOS ESTABLECIMIENTOS CON AFORO DISPONIBLE EN UN RANGO DE FECHAS Y UNA HORA

SELECT LOCALCOMERCIAL.*, AFORO-NUM_VISITAS AS DISPONIBLE
FROM
(
    SELECT LECTOR.IDLOCALCOMERCIAL, COUNT(*) AS NUM_VISITAS
    FROM REGISTRANCARNET
    JOIN LECTOR
    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    WHERE FECHA BETWEEN TO_DATE('2019/07/19','yyyy/mm/dd') AND TO_DATE('2019/07/19','yyyy/mm/dd') 
    AND HORA = 13 AND MINUTO = 17    
    GROUP BY LECTOR.IDLOCALCOMERCIAL
) AUX
JOIN LOCALCOMERCIAL
ON LOCALCOMERCIAL.IDENTIFICADOR = AUX.IDLOCALCOMERCIAL
JOIN AREA
ON LOCALCOMERCIAL.AREA = AREA.ID
WHERE AFORO-NUM_VISITAS > 0;



--RFC 4 - MOSTRAR LOS ESTABLECIMIENTOS CON AFORO DISPONIBLE EN UNA FECHA Y UN RANGO DE HORAS

SELECT LOCALCOMERCIAL.*, AFORO - NUM_VISITAS AS CUPOS_DISPONIBLES
FROM
(
    SELECT LECTOR.IDLOCALCOMERCIAL, COUNT(*) AS NUM_VISITAS
    FROM REGISTRANCARNET
    JOIN LECTOR
    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    WHERE FECHA = TO_DATE('2019/07/19','yyyy/mm/dd') 
    AND (HORA BETWEEN 10 AND 19) OR (HORA = 10 AND MINUTO >= 40) OR (HORA = 19 AND MINUTO <=10)
    GROUP BY LECTOR.IDLOCALCOMERCIAL
) AUX
JOIN LOCALCOMERCIAL
ON LOCALCOMERCIAL.IDENTIFICADOR = AUX.IDLOCALCOMERCIAL
JOIN AREA
ON LOCALCOMERCIAL.AREA = AREA.ID
WHERE AFORO - NUM_VISITAS > 0;


--> RFC5 MOSTRAR TIEMPOS DE VISITA DE UN TIPO DE VISITANTE RANGO DE FECHAS 4:34 a 5:50 = 76 minutos

SELECT INF_ENTRADA.IDVISITANTE, (HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA AS DURACION_VISITA
FROM
(
    SELECT REGISTRANCARNET.IDVISITANTE, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    WHERE (HORAENTRADA BETWEEN 10 AND 18) OR (HORAENTRADA = 10 AND MINUTO >= 15) OR (HORAENTRADA = 18 AND MINUTO <= 10)
) INF_ENTRADA
JOIN
(
    SELECT REGISTRANCARNET.IDVISITANTE, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID
    WHERE FECHA BETWEEN TO_DATE('2017/07/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
    AND TIPOVISITANTE.TIPO = 'Domiciliario'
) INF_SALIDA
ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE;


--> RFC5 MOSTRAR TIEMPOS DE VISITA PROMEDIO DE UN TIPO DE VISITANTE RANGO DE FECHAS

SELECT INF_ENTRADA.TIPO, ROUND(AVG((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA), 3) AS DURACION_VISITA
FROM
(
    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    WHERE (HORAENTRADA BETWEEN 10 AND 18) OR (HORAENTRADA = 10 AND MINUTO >= 15) OR (HORAENTRADA = 18 AND MINUTO <= 10)
) INF_ENTRADA
JOIN
(
    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID
    WHERE FECHA BETWEEN TO_DATE('2017/07/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
    AND TIPOVISITANTE.TIPO = 'Domiciliario'
) INF_SALIDA
ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE
GROUP BY INF_ENTRADA.TIPO;

--> RFC5 MOSTRAR TIEMPOS DE VISITA MINIMO DE UN TIPO DE VISITANTE RANGO DE FECHAS
SELECT INF_ENTRADA.TIPO, ROUND(MIN((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA), 3) AS DURACION_VISITA_MINIMO
FROM
(
    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    WHERE (HORAENTRADA BETWEEN 10 AND 18) OR (HORAENTRADA = 10 AND MINUTO >= 15) OR (HORAENTRADA = 18 AND MINUTO <= 10)
) INF_ENTRADA
JOIN
(
    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID
    WHERE FECHA BETWEEN TO_DATE('2017/07/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
    AND TIPOVISITANTE.TIPO = 'Domiciliario'
) INF_SALIDA
ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE
GROUP BY INF_ENTRADA.TIPO;

--> RFC5 MOSTRAR TIEMPOS DE VISITA MAXIMO DE UN TIPO DE VISITANTE RANGO DE FECHAS
SELECT INF_ENTRADA.TIPO, ROUND(MAX((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA), 3) AS DURACION_VISITA_MAXIMO
FROM
(
    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    WHERE (HORAENTRADA BETWEEN 10 AND 18) OR (HORAENTRADA = 10 AND MINUTO >= 15) OR (HORAENTRADA = 18 AND MINUTO <= 10)
) INF_ENTRADA
JOIN
(
    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID
    WHERE FECHA BETWEEN TO_DATE('2017/07/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
    AND TIPOVISITANTE.TIPO = 'Domiciliario'
) INF_SALIDA
ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE
GROUP BY INF_ENTRADA.TIPO;


--> RFC5 MOSTRAR TIEMPOS DE VISITA TOTALES DE UN TIPO DE VISITANTE RANGO DE FECHAS
SELECT INF_ENTRADA.TIPO, ROUND(SUM((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA), 3) AS DURACION_VISITA_TOTAL
FROM
(
    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    WHERE (HORAENTRADA BETWEEN 10 AND 18) OR (HORAENTRADA = 10 AND MINUTO >= 15) OR (HORAENTRADA = 18 AND MINUTO <= 10)
) INF_ENTRADA
JOIN
(
    SELECT REGISTRANCARNET.IDVISITANTE, TIPOVISITANTE.TIPO, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA
    FROM REGISTRANCARNET
    JOIN VISITANTE
    ON REGISTRANCARNET.IDVISITANTE = VISITANTE.IDENTIFICACION
    JOIN TIPOVISITANTE
    ON VISITANTE.TIPO = TIPOVISITANTE.ID
    JOIN HORARIO
    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID
    WHERE FECHA BETWEEN TO_DATE('2017/07/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
    AND TIPOVISITANTE.TIPO = 'Domiciliario'
) INF_SALIDA
ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE
GROUP BY INF_ENTRADA.TIPO;




--> RF5 CANTIDAD DE VISITANTES DE UN TIPO DE LOCAL

SELECT TIPOLOCAL.TIPO, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES
FROM REGISTRANCARNET
JOIN LECTOR
ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
JOIN LOCALCOMERCIAL
ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR
JOIN TIPOLOCAL
ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID
JOIN HORARIO
ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
WHERE UPPER(TIPOLOCAL.TIPO) = UPPER('Restaurante') AND (HORA BETWEEN 10 AND 18) OR (HORA = 10 AND MINUTO < 15) OR (HORA = 18 AND MINUTO < 10) 
AND FECHA BETWEEN TO_DATE('2017/05/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
GROUP BY TIPOLOCAL.TIPO;



--> RF5 CANTIDAD DE VISITANTES PROMEDIO DE UN TIPO DE LOCAL

    SELECT TIPO, ROUND(AVG(CANTIDAD_VISITANTES),4) AS PROMEDIO_CANTIDAD
    FROM
    (
        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES
        FROM REGISTRANCARNET
        JOIN LECTOR
        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
        JOIN LOCALCOMERCIAL
        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR
        JOIN TIPOLOCAL
        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID
        JOIN HORARIO
        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
        WHERE (HORA BETWEEN 10 AND 18) OR (HORA = 10 AND MINUTO >= 15) OR (HORA = 18 AND MINUTO <= 10) 
        AND FECHA BETWEEN TO_DATE('2017/05/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL
    )
    WHERE UPPER(TIPO) = UPPER('Restaurante')
    GROUP BY TIPO;

--> RF5 CANTIDAD DE VISITANTES M�NIMO DE UN TIPO DE LOCAL

    SELECT TIPO, ROUND(MIN(CANTIDAD_VISITANTES),4) AS MIN_CANTIDAD
    FROM
    (
        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES
        FROM REGISTRANCARNET
        JOIN LECTOR
        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
        JOIN LOCALCOMERCIAL
        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR
        JOIN TIPOLOCAL
        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID
        JOIN HORARIO
        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
        WHERE (HORA BETWEEN 10 AND 18) OR (HORA = 10 AND MINUTO >= 15) OR (HORA = 18 AND MINUTO <= 10) 
        AND FECHA BETWEEN TO_DATE('2017/05/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL
    )
    WHERE UPPER(TIPO) = UPPER('Restaurante')
    GROUP BY TIPO;

--> RF5 CANTIDAD DE VISITANTES M�XIMO DE UN TIPO DE LOCAL


    SELECT TIPO, ROUND(MAX(CANTIDAD_VISITANTES),4) AS MAXIMO_CANTIDAD
    FROM
    (
        SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES
        FROM REGISTRANCARNET
        JOIN LECTOR
        ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
        JOIN LOCALCOMERCIAL
        ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR
        JOIN TIPOLOCAL
        ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID
        JOIN HORARIO
        ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
        WHERE (HORA BETWEEN 10 AND 18) OR (HORA = 10 AND MINUTO >= 15) OR (HORA = 18 AND MINUTO <= 10) 
        AND FECHA BETWEEN TO_DATE('2017/05/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
        GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL
    )
    WHERE UPPER(TIPO) = UPPER('Restaurante')
    GROUP BY TIPO;


--> RF5 VISITAS POR ESTABLECIMIENTO

SELECT *
FROM
        (
            SELECT TIPOLOCAL.TIPO, IDLOCALCOMERCIAL, COUNT(DISTINCT IDVISITANTE) AS CANTIDAD_VISITANTES
            FROM REGISTRANCARNET
            JOIN LECTOR
            ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
            JOIN LOCALCOMERCIAL
            ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.IDENTIFICADOR
            JOIN TIPOLOCAL
            ON LOCALCOMERCIAL.TIPOLOCAL = TIPOLOCAL.ID
            JOIN HORARIO
            ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
            WHERE (HORA BETWEEN 10 AND 18) OR (HORA = 10 AND MINUTO >= 15) OR (HORA = 18 AND MINUTO <= 10) 
            AND FECHA BETWEEN TO_DATE('2017/05/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
            GROUP BY TIPOLOCAL.TIPO, IDLOCALCOMERCIAL
        )
WHERE TIPO = 'Restaurante';

--> RF5 DURACI�N DE CADA VISITA A UN LOCAL COMERCIAL REALIZADA POR UN VISITANTE DADO EN UN RANGO DE FECHAS Y HORAS

SELECT INF_ENTRADA.IDVISITANTE, INF_ENTRADA.IDLOCALCOMERCIAL,((HORASALIDA-HORAENTRADA)*60 + MINUTOSALIDA - MINUTOENTRADA) AS DURACION_VISITA
FROM
(
    SELECT REGISTRANCARNET.IDVISITANTE, LECTOR.IDLOCALCOMERCIAL, HORA AS HORAENTRADA, MINUTO AS MINUTOENTRADA
    FROM REGISTRANCARNET
    JOIN HORARIO
    ON REGISTRANCARNET.HORAENTRADA = HORARIO.ID
    JOIN LECTOR
    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
    WHERE (HORA BETWEEN 10 AND 18) OR (HORA = 10 AND MINUTO >= 15) OR (HORA = 18 AND MINUTO <= 10) AND LECTOR.IDLOCALCOMERCIAL IS NOT NULL
) INF_ENTRADA
JOIN
(
    SELECT REGISTRANCARNET.IDVISITANTE, LECTOR.IDLOCALCOMERCIAL, HORA AS HORASALIDA, MINUTO AS MINUTOSALIDA
    FROM REGISTRANCARNET
    JOIN HORARIO
    ON REGISTRANCARNET.HORASALIDA = HORARIO.ID
    JOIN LECTOR
    ON REGISTRANCARNET.IDLECTOR = LECTOR.ID
    WHERE FECHA BETWEEN TO_DATE('2017/05/19','yyyy/mm/dd') AND TO_DATE('2021/07/20','yyyy/mm/dd')
    AND REGISTRANCARNET.IDVISITANTE = '3757003542'
) INF_SALIDA
ON INF_ENTRADA.IDVISITANTE = INF_SALIDA.IDVISITANTE;


