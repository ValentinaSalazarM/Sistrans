--RFC1 - MOSTRAR TODOS LOS VISITANTES ATENDIDOS POR UN ESTABLECIMIENTO EN UN RANGO DE FECHAS

SELECT VISITANTE.*
FROM LECTOR
JOIN
    (
        SELECT IDLECTOR, IDVISITANTE
        FROM REGISTRANCARNET
        WHERE FECHA BETWEEN TO_DATE('25-02-2019','DD-MM-YYYY') AND TO_DATE('25-03-2020','DD-MM-YYYY')
    ) INF_VISITAS
ON INF_VISITAS.IDLECTOR = LECTOR.ID
JOIN VISITANTE
ON INF_VISITAS.IDVISITANTE = VISITANTE.IDENTIFICACION
WHERE LECTOR.IDLOCALCOMERCIAL = 'XXXXXXXXX';

--RFC1 - MOSTRAR TODOS LOS VISITANTES ATENDIDOS POR UN ESTABLECIMIENTO EN UNA FECHA

SELECT VISITANTE.*
FROM LECTOR
JOIN
    (
        SELECT IDLECTOR, IDVISITANTE
        FROM REGISTRANCARNET
        WHERE FECHA =  TO_DATE('25-03-2020','DD-MM-YYYY')
    ) INF_VISITAS
ON INF_VISITAS.IDLECTOR = LECTOR.ID
JOIN VISITANTE
ON INF_VISITAS.IDVISITANTE = VISITANTE.IDENTIFICACION
WHERE LECTOR.IDLOCALCOMERCIAL = 'XXXXXXXXX';

--RFC1 - MOSTRAR TODOS LOS VISITANTES ATENDIDOS POR UN ESTABLECIMIENTO EN UN RANGO DE HORAS

SELECT VISITANTE.*, HORA, MINUTO
FROM LECTOR
JOIN
    (
        SELECT IDLECTOR, IDVISITANTE, HORA, MINUTO
        FROM HORARIO
        JOIN REGISTRANCARNET
        ON HORARIO.ID = REGISTRANCARNET.HORAENTRADA
        WHERE FECHA =  TO_DATE('25-03-2020','DD-MM-YYYY')
        AND HORA BETWEEN 12 AND 16
    ) INF_VISITAS
ON INF_VISITAS.IDLECTOR = LECTOR.ID
JOIN VISITANTE
ON INF_VISITAS.IDVISITANTE = VISITANTE.IDENTIFICACION
WHERE LECTOR.IDLOCALCOMERCIAL = 'XXXXXXXXX';

--RC2 FECHA

SELECT LOCALCOMERCIAL.*
FROM LECTOR
JOIN 
    (SELECT *
    FROM
        (SELECT IDLECTOR, COUNT(*) AS NUM_VISITAS
        FROM REGISTRANCARNET
        WHERE FECHA =  TO_DATE('25-03-2020','DD-MM-YYYY')
        GROUP BY IDLECTOR
        ORDER BY NUM_VISITAS)
    WHERE ROWNUM <= 20 
    ) AUX
ON AUX.IDLECTOR = LECTOR.ID
JOIN LOCALCOMERCIAL
ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.ID;

--RC2 RANGO DE FECHAS
SELECT LOCALCOMERCIAL.*
FROM LECTOR
JOIN 
    (SELECT *
    FROM
        (SELECT IDLECTOR, COUNT(*) AS NUM_VISITAS
        FROM REGISTRANCARNET
        WHERE FECHA BETWEEN TO_DATE('25-02-2019','DD-MM-YYYY') AND TO_DATE('25-03-2020','DD-MM-YYYY')
        GROUP BY IDLECTOR
        ORDER BY NUM_VISITAS DESC)
    WHERE ROWNUM <= 20 
    ) AUX
ON AUX.IDLECTOR = LECTOR.ID
JOIN LOCALCOMERCIAL
ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.ID;


--RC2 RANGO DE HORAS, D�NDE PONERLE EL MINUTO?
SELECT LOCALCOMERCIAL.*
FROM LECTOR
JOIN 
    (SELECT *
    FROM
        (
            SELECT IDLECTOR, COUNT(*) AS NUM_VISITAS
            FROM HORARIO
            JOIN REGISTRANCARNET
            ON HORARIO.ID = REGISTRANCARNET.HORAENTRADA
            WHERE REGISTRANCARNET.FECHA = TO_DATE('25-02-2019','DD-MM-YYYY')
            AND HORA BETWEEN 12 AND 14  
            GROUP BY IDLECTOR
            ORDER BY NUM_VISITAS
        )
    WHERE ROWNUM <= 20 
    ) AUX
ON AUX.IDLECTOR = LECTOR.ID
JOIN LOCALCOMERCIAL
ON LECTOR.IDLOCALCOMERCIAL = LOCALCOMERCIAL.ID;




