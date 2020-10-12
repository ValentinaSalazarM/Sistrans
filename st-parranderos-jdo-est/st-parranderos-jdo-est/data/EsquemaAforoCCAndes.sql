---------------------------------------
-- CREACI�N DE TABLAS DE CONCEPTOS
---------------------------------------

CREATE TABLE HORARIO
(
    HORA NUMBER NOT NULL,
    MINUTO NUMBER NOT NULL
);

CREATE TABLE TIPOVISITANTE
(
    ID NUMBER,
    TIPO VARCHAR2 (255 BYTE) NOT NULL,
    HORAINICIO NUMBER NOT NULL,
    MINUTOINICIO NUMBER NOT NULL,
    HORALIMITE NUMBER NOT NULL,
    MINUTOLIMITE NUMBER NOT NULL
);


CREATE TABLE VISITANTE 
(
    IDENTIFICACION VARCHAR2 (255 BYTE),
    NOMBRE VARCHAR2 (255 BYTE) NOT NULL,
    TIPO NUMBER NOT NULL,
    CORREO VARCHAR2 (255 BYTE) NOT NULL,
    TELEFONOPROPIO VARCHAR2 (255 BYTE) NOT NULL,
    NOMBREEMERGENCIA VARCHAR2 (255 BYTE) NOT NULL,
    TELEFONOEMERGENCIA VARCHAR2 (255 BYTE) NOT NULL
);

CREATE TABLE CENTROCOMERCIAL
(
    ID VARCHAR2 (255 BYTE),
    NOMBRE VARCHAR2 (255 BYTE) NOT NULL
);

CREATE TABLE AREA
(
    ID NUMBER,
    VALOR NUMBER NOT NULL,
    AFORO NUMBER
);

CREATE TABLE CAPACIDADNORMAL
(
    ID NUMBER,
    VALOR NUMBER NOT NULL,
    AFORO NUMBER
);

CREATE TABLE ASCENSOR
(
    IDENTIFICADOR VARCHAR2 (255 BYTE),
    CAPACIDADNORMAL NUMBER,
    AREA NUMBER,
    PESOMAXIMO NUMBER,
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE) NOT NULL
);

CREATE TABLE BA�O
(
    IDENTIFICADOR VARCHAR2 (255 BYTE),
    CAPACIDADNORMAL NUMBER,
    AREA NUMBER,
    SANITARIOS NUMBER NOT NULL,
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE) NOT NULL
);

CREATE TABLE PARQUEADERO
(
    IDENTIFICADOR VARCHAR2 (255 BYTE),
    CAPACIDADNORMAL NUMBER,
    AREA NUMBER,
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE) NOT NULL
);


CREATE TABLE TIPOLOCAL
(
    ID NUMBER,
    TIPO VARCHAR2 (255 BYTE) NOT NULL,
    HORAAPERTURA NUMBER NOT NULL,
    MINUTOAPERTURA NUMBER NOT NULL,
    HORACIERRE NUMBER NOT NULL,
    MINUTOCIERRE NUMBER NOT NULL
);


CREATE TABLE LOCALCOMERCIAL
(
    IDENTIFICADOR VARCHAR2 (255 BYTE),
    CAPACIDADNORMAL NUMBER NOT NULL,
    AREA NUMBER NOT NULL,
    TIPOLOCAL NUMBER NOT NULL,
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE) NOT NULL,
    ACTIVO NUMBER
);


CREATE TABLE ZONACIRCULACION 
(
    IDENTIFICADOR VARCHAR2 (255 BYTE),
    CAPACIDADNORMAL NUMBER NOT NULL,
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE) NOT NULL
);

CREATE TABLE TIPOLECTOR
(
    ID NUMBER,
    TIPO VARCHAR2 (255 BYTE) NOT NULL
);


CREATE TABLE LECTOR
(
    ID VARCHAR2 (255 BYTE),
    TIPOLECTOR NUMBER NOT NULL,
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE),
    IDLOCALCOMERCIAL VARCHAR2 (255 BYTE),
    IDASCENSOR VARCHAR2 (255 BYTE),
    IDPARQUEADERO VARCHAR2 (255 BYTE),
    IDBA�O VARCHAR2 (255 BYTE)
);


CREATE TABLE TIPOCARNET 
(
    ID NUMBER,
    TIPO VARCHAR2 (255 BYTE) NOT NULL
);


CREATE TABLE CARNET 
(
    TIPOCARNET NUMBER,
    IDVISITANTE VARCHAR2 (255 BYTE)
);


CREATE TABLE DOMICILIARIO
(
    IDVISITANTE VARCHAR2 (255 BYTE),
    COMPA�IADOMICILIOS VARCHAR2 (255 BYTE)
);

CREATE TABLE EMPLEADO
(
    IDVISITANTE VARCHAR2 (255 BYTE),
    LUGARTRABAJO VARCHAR2 (255 BYTE)
);

CREATE TABLE VEHICULO
(
    PLACA VARCHAR2 (255 BYTE),
    CARACTERISTICAS VARCHAR2 (255 BYTE),
    DUE�O VARCHAR2 (255 BYTE) NOT NULL
);


---------------------------------------
-- CREACI�N DE TABLAS DE RELACIONES 
---------------------------------------

CREATE TABLE VISITANCENTROCOMERCIAL
(
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE),
    IDVISITANTE VARCHAR2 (255 BYTE)
);

CREATE TABLE VISITANASCENSOR
(
    IDASCENSOR VARCHAR2 (255 BYTE),
    IDVISITANTE VARCHAR2 (255 BYTE)
);


CREATE TABLE VISITANBA�O
(
    IDBA�O VARCHAR2 (255 BYTE),
    IDVISITANTE VARCHAR2 (255 BYTE)
);


CREATE TABLE VISITANLOCALCOMERCIAL
(
    IDLOCALCOMERCIAL VARCHAR2 (255 BYTE),
    IDVISITANTE VARCHAR2 (255 BYTE)
);


CREATE TABLE VISITANPARQUEADERO
(
    IDPARQUEADERO VARCHAR2 (255 BYTE),
    IDVISITANTE VARCHAR2 (255 BYTE)
);


CREATE TABLE REGISTRANCARNET
(
    IDLECTOR VARCHAR2 (255 BYTE),
    TIPOCARNET NUMBER,
    IDVISITANTE VARCHAR2 (255 BYTE),
    HORAENTRADA NUMBER NOT NULL,
    MINUTOENTRADA NUMBER NOT NULL,
    HORASALIDA NUMBER NOT NULL,
    MINUTOSALIDA NUMBER NOT NULL
);


CREATE TABLE REGISTRANVEHICULO
(
    IDLECTOR VARCHAR2 (255 BYTE),
    VEHICULO VARCHAR2 (255 BYTE),
    HORAENTRADA NUMBER NOT NULL,
    MINUTOENTRADA NUMBER NOT NULL,
    HORASALIDA NUMBER NOT NULL,
    MINUTOSALIDA NUMBER NOT NULL
);

---------------------------------------
-- CREACI�N DE SECUENCIADORES 
---------------------------------------

CREATE SEQUENCE capacidadNormal_sequence;
CREATE SEQUENCE area_sequence;
CREATE SEQUENCE tipoLocal_sequence;
CREATE SEQUENCE tipoCarnet_sequence;
CREATE SEQUENCE tipoLector_sequence;
CREATE SEQUENCE tipoVisitante_sequence;

---------------------------------------
-- CONSTRAINTS 
---------------------------------------

-------------------------------------------
-- Constraints para la tabla HORARIO
-------------------------------------------

ALTER TABLE HORARIO
    ADD CONSTRAINT PK_HORARIO
    PRIMARY KEY (HORA, MINUTO)
ENABLE;

ALTER TABLE HORARIO
    ADD CONSTRAINT CK_HORARIO_HORA
    CHECK (HORA BETWEEN 00 AND 23)
ENABLE;

ALTER TABLE HORARIO
    ADD CONSTRAINT CK_HORARIO_MINUTO
    CHECK (MINUTO BETWEEN 00 AND 59)
ENABLE;
-------------------------------------------
-- Constraints para la tabla TIPOVISITANTE
-------------------------------------------

ALTER TABLE TIPOVISITANTE
    ADD CONSTRAINT PK_TIPOVISITANTE
    PRIMARY KEY (ID)
ENABLE;

ALTER TABLE TIPOVISITANTE
    ADD CONSTRAINT ND_TIPOVISITANTE_TIPO
    UNIQUE (TIPO)
ENABLE;

ALTER TABLE TIPOVISITANTE
    ADD CONSTRAINT CK_TV_TIPO
    CHECK (TIPO IN ('Vigilancia', 'Aseo', 'Mantenimiento', 'Cliente', 'Domiciliario', 'Empleado'))
ENABLE;

ALTER TABLE TIPOVISITANTE
    ADD CONSTRAINT FK_TV_HORAINICIO
    FOREIGN KEY (HORAINICIO, MINUTOINICIO) REFERENCES HORARIO (HORA, MINUTO)
ENABLE;

ALTER TABLE TIPOVISITANTE
    ADD CONSTRAINT FK_TV_HORALIMITE
    FOREIGN KEY (HORALIMITE, MINUTOLIMITE) REFERENCES HORARIO (HORA, MINUTO)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE VISITANTE
    ADD CONSTRAINT PK_VISITANTE
    PRIMARY KEY (IDENTIFICACION)
ENABLE;

ALTER TABLE VISITANTE
    ADD CONSTRAINT ND_VISITANTE_NOMBRE
    UNIQUE (NOMBRE)
ENABLE;

ALTER TABLE VISITANTE
    ADD CONSTRAINT ND_VISITANTE_CORREO
    UNIQUE (CORREO)
ENABLE;

ALTER TABLE VISITANTE
    ADD CONSTRAINT FK_V_TIPOVISITANTE
    FOREIGN KEY (TIPO) REFERENCES TIPOVISITANTE (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE CENTROCOMERCIAL
    ADD CONSTRAINT PK_CENTROCOMERCIAL
    PRIMARY KEY (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE AREA
    ADD CONSTRAINT PK_AREA
    PRIMARY KEY (ID)
ENABLE;    

ALTER TABLE AREA
    ADD CONSTRAINT CK_AREA_VALOR
    CHECK (VALOR > 0)
ENABLE;

ALTER TABLE AREA
    ADD CONSTRAINT CK_AREA_AFORO
    CHECK (AFORO > 0)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE CAPACIDADNORMAL
    ADD CONSTRAINT PK_CAPACIDADNORMAL
    PRIMARY KEY (ID)
ENABLE;    

ALTER TABLE CAPACIDADNORMAL
    ADD CONSTRAINT CK_CAPACIDADNORMAL_VALOR
    CHECK (VALOR > 0)
ENABLE;

ALTER TABLE CAPACIDADNORMAL
    ADD CONSTRAINT CK_CAPACIDADNORMAL_AFORO
    CHECK (AFORO > 0)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE ASCENSOR
    ADD CONSTRAINT PK_ASCENSOR
    PRIMARY KEY (IDENTIFICADOR)
ENABLE;

ALTER TABLE ASCENSOR
    ADD CONSTRAINT CK_ASCENSOR_PESOMAXIMO
    CHECK (PESOMAXIMO > 0)
ENABLE;

ALTER TABLE ASCENSOR
    ADD CONSTRAINT FK_AS_CAPACIDADNORMAL
    FOREIGN KEY (CAPACIDADNORMAL) REFERENCES CAPACIDADNORMAL (ID)
ENABLE;

ALTER TABLE ASCENSOR
    ADD CONSTRAINT FK_AS_AREA
    FOREIGN KEY (AREA) REFERENCES AREA (ID)
ENABLE;

ALTER TABLE ASCENSOR
    ADD CONSTRAINT FK_AS_CENTROCOMERCIAL
    FOREIGN KEY (IDCENTROCOMERCIAL) REFERENCES CENTROCOMERCIAL (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE BA�O
    ADD CONSTRAINT PK_BA�O
    PRIMARY KEY (IDENTIFICADOR)
ENABLE;

ALTER TABLE BA�O
    ADD CONSTRAINT CK_BA�O_SANITARIOS
    CHECK (SANITARIOS > 0)
ENABLE;

ALTER TABLE BA�O
    ADD CONSTRAINT FK_B_CAPACIDADNORMAL
    FOREIGN KEY (CAPACIDADNORMAL) REFERENCES CAPACIDADNORMAL (ID)
ENABLE;

ALTER TABLE BA�O
    ADD CONSTRAINT FK_B_AREA
    FOREIGN KEY (AREA) REFERENCES AREA (ID)
ENABLE;

ALTER TABLE BA�O
    ADD CONSTRAINT FK_B_CENTROCOMERCIAL
    FOREIGN KEY (IDCENTROCOMERCIAL) REFERENCES CENTROCOMERCIAL (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE PARQUEADERO
    ADD CONSTRAINT PK_PARQUEADERO
    PRIMARY KEY (IDENTIFICADOR)
ENABLE;

ALTER TABLE PARQUEADERO
    ADD CONSTRAINT FK_P_CAPACIDADNORMAL
    FOREIGN KEY (CAPACIDADNORMAL) REFERENCES CAPACIDADNORMAL (ID)
ENABLE;

ALTER TABLE PARQUEADERO
    ADD CONSTRAINT FK_P_AREA
    FOREIGN KEY (AREA) REFERENCES AREA (ID)
ENABLE;

ALTER TABLE PARQUEADERO
    ADD CONSTRAINT FK_P_CENTROCOMERCIAL
    FOREIGN KEY (IDCENTROCOMERCIAL) REFERENCES CENTROCOMERCIAL (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE TIPOLOCAL
    ADD CONSTRAINT PK_TIPOLOCAL
    PRIMARY KEY (ID)
ENABLE;

ALTER TABLE TIPOLOCAL
    ADD CONSTRAINT ND_TIPOLOCAL_TIPO
    UNIQUE (TIPO)
ENABLE;

ALTER TABLE TIPOLOCAL
    ADD CONSTRAINT FK_TL_HORAAPERTURA
    FOREIGN KEY (HORAAPERTURA, MINUTOAPERTURA) REFERENCES HORARIO (HORA, MINUTO)
ENABLE;

ALTER TABLE TIPOLOCAL
    ADD CONSTRAINT FK_TL_HORACIERRE
    FOREIGN KEY (HORACIERRE, MINUTOCIERRE) REFERENCES HORARIO (HORA,MINUTO)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE LOCALCOMERCIAL
    ADD CONSTRAINT PK_LOCALCOMERCIAL
    PRIMARY KEY (IDENTIFICADOR)
ENABLE;

ALTER TABLE LOCALCOMERCIAL
    ADD CONSTRAINT FK_LOCAL_TIPOLOCAL
    FOREIGN KEY (TIPOLOCAL) REFERENCES TIPOLOCAL (ID)
ENABLE;

ALTER TABLE LOCALCOMERCIAL
    ADD CONSTRAINT FK_LOCAL_CAPACIDADNORMAL
    FOREIGN KEY (CAPACIDADNORMAL) REFERENCES CAPACIDADNORMAL (ID)
ENABLE;

ALTER TABLE LOCALCOMERCIAL
    ADD CONSTRAINT FK_LC_AREA
    FOREIGN KEY (AREA) REFERENCES AREA (ID)
ENABLE;

ALTER TABLE LOCALCOMERCIAL
    ADD CONSTRAINT FK_LC_CENTROCOMERCIAL
    FOREIGN KEY (IDCENTROCOMERCIAL) REFERENCES CENTROCOMERCIAL (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE ZONACIRCULACION
    ADD CONSTRAINT PK_ZONACIRCULACION
    PRIMARY KEY (IDENTIFICADOR)
ENABLE;

ALTER TABLE ZONACIRCULACION
    ADD CONSTRAINT CK_ZCIRCULACION_CAPACIDADNORMAL
    CHECK (CAPACIDADNORMAL > 0)
ENABLE;

ALTER TABLE ZONACIRCULACION
    ADD CONSTRAINT FK_ZC_CENTROCOMERCIAL
    FOREIGN KEY (IDCENTROCOMERCIAL) REFERENCES CENTROCOMERCIAL (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE TIPOLECTOR
    ADD CONSTRAINT PK_TIPOLECTOR
    PRIMARY KEY (ID)
ENABLE;

ALTER TABLE TIPOLECTOR
    ADD CONSTRAINT ND_TIPOLECTOR_TIPO
    UNIQUE (TIPO)
ENABLE;

ALTER TABLE TIPOLECTOR
    ADD CONSTRAINT CK_TIPOLECTOR_TIPO
    CHECK (TIPO IN ('Temperatura', 'Visitante', 'Veh�culo'))
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE LECTOR
    ADD CONSTRAINT PK_LECTOR
    PRIMARY KEY (ID)
ENABLE;
    
ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_CENTROCOMERCIAL
    FOREIGN KEY (IDCENTROCOMERCIAL) REFERENCES CENTROCOMERCIAL (ID)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_LOCALCOMERCIAL
    FOREIGN KEY (IDLOCALCOMERCIAL) REFERENCES LOCALCOMERCIAL (IDENTIFICADOR)
ENABLE;
    
ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_ASCENSOR
    FOREIGN KEY (IDASCENSOR) REFERENCES ASCENSOR (IDENTIFICADOR)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_PARQUEADERO
    FOREIGN KEY (IDPARQUEADERO) REFERENCES PARQUEADERO (IDENTIFICADOR)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_BA�O
    FOREIGN KEY (IDBA�O) REFERENCES BA�O (IDENTIFICADOR)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_TIPOLECTOR
    FOREIGN KEY (TIPOLECTOR) REFERENCES TIPOLECTOR (ID)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT ND_LECTOR_IDCENTROCOMERCIAL
    UNIQUE (IDCENTROCOMERCIAL)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT ND_LECTOR_IDLOCALCOMERCIAL
    UNIQUE (IDLOCALCOMERCIAL)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT ND_LECTOR_IDASCENSOR
    UNIQUE (IDASCENSOR)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT ND_LECTOR_IDPARQUEADERO
    UNIQUE (IDPARQUEADERO)
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT ND_LECTOR_IDBA�O
    UNIQUE (IDBA�O)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE TIPOCARNET
    ADD CONSTRAINT PK_TIPOCARNET
    PRIMARY KEY (ID)
ENABLE;

ALTER TABLE TIPOCARNET
    ADD CONSTRAINT ND_TIPOCARNET_TIPO
    UNIQUE (TIPO)
ENABLE;

ALTER TABLE TIPOCARNET
    ADD CONSTRAINT CK_TIPOCARNET_TIPO
    CHECK (TIPO IN ('QR', 'Fisico'))
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE CARNET
    ADD CONSTRAINT PK_CARNET
    PRIMARY KEY (TIPOCARNET, IDVISITANTE)
ENABLE;

ALTER TABLE CARNET
    ADD CONSTRAINT ND_CARNET_IDVISITANTE
    UNIQUE (IDVISITANTE)
ENABLE;

ALTER TABLE CARNET
    ADD CONSTRAINT FK_C_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;
    
ALTER TABLE CARNET
    ADD CONSTRAINT FK_C_TIPOCARNET
    FOREIGN KEY (TIPOCARNET) REFERENCES TIPOCARNET (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE DOMICILIARIO
    ADD CONSTRAINT PK_DOMICILIARIO
    PRIMARY KEY (IDVISITANTE, COMPA�IADOMICILIOS)
ENABLE;

ALTER TABLE DOMICILIARIO
    ADD CONSTRAINT FK_D_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE EMPLEADO
    ADD CONSTRAINT PK_EMPLEADO
    PRIMARY KEY (IDVISITANTE, LUGARTRABAJO)
ENABLE;

ALTER TABLE EMPLEADO
    ADD CONSTRAINT FK_E_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

ALTER TABLE EMPLEADO
    ADD CONSTRAINT FK_E_LOCALCOMERCIAL
    FOREIGN KEY (LUGARTRABAJO) REFERENCES LOCALCOMERCIAL (IDENTIFICADOR)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE VEHICULO
    ADD CONSTRAINT PK_VEHICULO
    PRIMARY KEY (PLACA)
ENABLE;

ALTER TABLE VEHICULO
    ADD CONSTRAINT FK_VH_VISITANTE
    FOREIGN KEY (DUE�O) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE VISITANCENTROCOMERCIAL
    ADD CONSTRAINT PK_VISITANCC
    PRIMARY KEY (IDCENTROCOMERCIAL, IDVISITANTE)
ENABLE;

ALTER TABLE VISITANCENTROCOMERCIAL
    ADD CONSTRAINT FK_VCC_CENTROCOMERCIAL
    FOREIGN KEY (IDCENTROCOMERCIAL) REFERENCES CENTROCOMERCIAL (ID)
ENABLE;

ALTER TABLE VISITANCENTROCOMERCIAL
    ADD CONSTRAINT FK_VCC_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE VISITANASCENSOR
    ADD CONSTRAINT PK_VISITANASCENSOR
    PRIMARY KEY (IDASCENSOR, IDVISITANTE)
ENABLE;

ALTER TABLE VISITANASCENSOR
    ADD CONSTRAINT FK_VAS_CENTROCOMERCIAL
    FOREIGN KEY (IDASCENSOR) REFERENCES ASCENSOR (IDENTIFICADOR)
ENABLE;

ALTER TABLE VISITANASCENSOR
    ADD CONSTRAINT FK_VAS_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE VISITANBA�O
    ADD CONSTRAINT PK_VB_VISITANTE
    PRIMARY KEY (IDBA�O, IDVISITANTE)
ENABLE;

ALTER TABLE VISITANBA�O
    ADD CONSTRAINT FK_VB_BA�O
    FOREIGN KEY (IDBA�O) REFERENCES BA�O (IDENTIFICADOR)
ENABLE;

ALTER TABLE VISITANBA�O
    ADD CONSTRAINT FK_VB_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE VISITANLOCALCOMERCIAL
    ADD CONSTRAINT PK_VISITANLOCALCOMERCIAL
    PRIMARY KEY (IDLOCALCOMERCIAL, IDVISITANTE)
ENABLE;

ALTER TABLE VISITANLOCALCOMERCIAL
    ADD CONSTRAINT FK_VLC_CENTROCOMERCIAL
    FOREIGN KEY (IDLOCALCOMERCIAL) REFERENCES LOCALCOMERCIAL (IDENTIFICADOR)
ENABLE;

ALTER TABLE VISITANLOCALCOMERCIAL
    ADD CONSTRAINT FK_VLC_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE VISITANPARQUEADERO
    ADD CONSTRAINT PK_VISITANPARQUEADERO
    PRIMARY KEY (IDPARQUEADERO, IDVISITANTE)
ENABLE;

ALTER TABLE VISITANPARQUEADERO
    ADD CONSTRAINT FK_VP_CENTROCOMERCIAL
    FOREIGN KEY (IDPARQUEADERO) REFERENCES PARQUEADERO (IDENTIFICADOR)
ENABLE;

ALTER TABLE VISITANPARQUEADERO
    ADD CONSTRAINT FK_VP_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE REGISTRANCARNET
    ADD CONSTRAINT PK_LECTOR_CARNET
    PRIMARY KEY (IDLECTOR, TIPOCARNET, IDVISITANTE, HORAENTRADA, HORASALIDA)
ENABLE;

ALTER TABLE REGISTRANCARNET
    ADD CONSTRAINT FK_LC_LECTOR
    FOREIGN KEY (IDLECTOR) REFERENCES LECTOR(ID)
ENABLE;

ALTER TABLE REGISTRANCARNET
    ADD CONSTRAINT FK_LC_TIPOCARNET
    FOREIGN KEY (TIPOCARNET, IDVISITANTE) REFERENCES CARNET (TIPOCARNET, IDVISITANTE)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE REGISTRANVEHICULO
    ADD CONSTRAINT PK_LECTOR_VEHICULO
    PRIMARY KEY (IDLECTOR, VEHICULO, HORAENTRADA, HORASALIDA)
ENABLE;

ALTER TABLE REGISTRANVEHICULO
    ADD CONSTRAINT FK_LV_LECTOR
    FOREIGN KEY (IDLECTOR) REFERENCES LECTOR (ID)
ENABLE;

ALTER TABLE REGISTRANVEHICULO
    ADD CONSTRAINT FK_LV_VEHICULO
    FOREIGN KEY (VEHICULO) REFERENCES VEHICULO (PLACA)
ENABLE;


