---------------------------------------
-- CREACI�N DE TABLAS DE CONCEPTOS
---------------------------------------

CREATE TABLE HORARIO
(
    ID NUMBER,
    HORA NUMBER NOT NULL,
    MINUTO NUMBER NOT NULL
);

CREATE TABLE TIPOVISITANTE
(
    ID NUMBER,
    TIPO VARCHAR2 (255 BYTE) NOT NULL,
    HORAINICIO NUMBER NOT NULL,
    HORALIMITE NUMBER NOT NULL
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

CREATE TABLE BANO
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
    HORACIERRE NUMBER NOT NULL
);


CREATE TABLE LOCALCOMERCIAL
(
    IDENTIFICADOR VARCHAR2 (255 BYTE),
    CAPACIDADNORMAL NUMBER,
    AREA NUMBER,
    TIPOLOCAL NUMBER NOT NULL,
    ACTIVO NUMBER,
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE) NOT NULL
);


CREATE TABLE ADMINISTRADOR 
(
    IDENTIFICACION VARCHAR2 (255 BYTE),
    NOMBRE VARCHAR2 (255 BYTE) NOT NULL,
    CONTRASENA VARCHAR2 (10 BYTE) NOT NULL
);

CREATE TABLE ADMINISTRADORLOCAL
(
    IDENTIFICACION VARCHAR2 (255 BYTE),
    NOMBRE VARCHAR2 (255 BYTE) NOT NULL,
    CONTRASENA VARCHAR2 (10 BYTE) NOT NULL,
    LOCAL VARCHAR2 (255 BYTE) NOT NULL
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
    ID NUMBER,
    TIPOLECTOR NUMBER NOT NULL,
    IDCENTROCOMERCIAL VARCHAR2 (255 BYTE),
    IDLOCALCOMERCIAL VARCHAR2 (255 BYTE),
    IDASCENSOR VARCHAR2 (255 BYTE),
    IDPARQUEADERO VARCHAR2 (255 BYTE),
    IDBANO VARCHAR2 (255 BYTE)
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
    EMPRESADOMICILIOS VARCHAR2 (255 BYTE),
    HORAINICIOTURNO NUMBER NOT NULL,
    HORAFINALTURNO NUMBER NOT NULL
);

CREATE TABLE EMPLEADO
(
    IDVISITANTE VARCHAR2 (255 BYTE),
    LUGARTRABAJO VARCHAR2 (255 BYTE),
    HORAINICIOTURNO NUMBER NOT NULL,
    HORAFINALTURNO NUMBER NOT NULL
);

CREATE TABLE VEHICULO
(
    PLACA VARCHAR2 (255 BYTE),
    CARACTERISTICAS VARCHAR2 (255 BYTE),
    PROPIETARIO VARCHAR2 (255 BYTE) NOT NULL
);


---------------------------------------
-- CREACI�N DE TABLAS DE RELACIONES 
---------------------------------------

CREATE TABLE REGISTRANCARNET
(
    IDLECTOR NUMBER,
    TIPOCARNET NUMBER,
    IDVISITANTE VARCHAR2 (255 BYTE),
    FECHA DATE,
    HORAENTRADA NUMBER NOT NULL,
    HORASALIDA NUMBER DEFAULT NULL
);

CREATE TABLE REGISTRANVEHICULO
(
    IDLECTOR NUMBER,
    VEHICULO VARCHAR2 (255 BYTE),
    FECHA DATE,
    HORAENTRADA NUMBER NOT NULL,
    HORASALIDA NUMBER DEFAULT NULL
);

---------------------------------------
-- CREACI�N DE SECUENCIADORES 
---------------------------------------
CREATE SEQUENCE horario_sequence;
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
    PRIMARY KEY (ID)
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
    ADD CONSTRAINT FK_TV_HORAINICIO
    FOREIGN KEY (HORAINICIO) REFERENCES HORARIO (ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE TIPOVISITANTE
    ADD CONSTRAINT FK_TV_HORALIMITE
    FOREIGN KEY (HORALIMITE) REFERENCES HORARIO (ID)
    ON DELETE CASCADE
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

AlTER TABLE VISITANTE
    ADD CONSTRAINT CK_VISITANTE_NOMBRE
    CHECK (REGEXP_LIKE (NOMBRE, '^[ a-zA-Z]+$'))
ENABLE;

AlTER TABLE VISITANTE
    ADD CONSTRAINT CK_VISITANTE_TELEFONOPROPIO
    CHECK (REGEXP_LIKE (TELEFONOPROPIO, '^[ 0-9]+$'))
ENABLE;


AlTER TABLE VISITANTE
    ADD CONSTRAINT CK_VISITANTE_TELEFONOEMERGENCIA
    CHECK (REGEXP_LIKE (TELEFONOEMERGENCIA, '^[ 0-9]+$'))
ENABLE;

ALTER TABLE VISITANTE
    ADD CONSTRAINT FK_V_TIPOVISITANTE
    FOREIGN KEY (TIPO) REFERENCES TIPOVISITANTE (ID)
    ON DELETE CASCADE
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE CENTROCOMERCIAL
    ADD CONSTRAINT PK_CENTROCOMERCIAL
    PRIMARY KEY (ID)
ENABLE;

ALTER TABLE CENTROCOMERCIAL
    ADD CONSTRAINT ND_CENTROCOMERCIAL_NOMBRE
    UNIQUE (NOMBRE)
ENABLE;


---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE AREA
    ADD CONSTRAINT PK_AREA
    PRIMARY KEY (ID)
ENABLE;    

ALTER TABLE AREA
    ADD CONSTRAINT ND_AREA_VALOR
    UNIQUE(VALOR)
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
    ADD CONSTRAINT ND_CAPACIDADNORMAL_VALOR
    UNIQUE(VALOR)
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
    ON DELETE CASCADE
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE BANO
    ADD CONSTRAINT PK_BANO
    PRIMARY KEY (IDENTIFICADOR)
ENABLE;

ALTER TABLE BANO
    ADD CONSTRAINT CK_BANO_SANITARIOS
    CHECK (SANITARIOS > 0)
ENABLE;

ALTER TABLE BANO
    ADD CONSTRAINT FK_B_CAPACIDADNORMAL
    FOREIGN KEY (CAPACIDADNORMAL) REFERENCES CAPACIDADNORMAL (ID)
ENABLE;

ALTER TABLE BANO
    ADD CONSTRAINT FK_B_AREA
    FOREIGN KEY (AREA) REFERENCES AREA (ID)
ENABLE;

ALTER TABLE BANO
    ADD CONSTRAINT FK_B_CENTROCOMERCIAL
    FOREIGN KEY (IDCENTROCOMERCIAL) REFERENCES CENTROCOMERCIAL (ID)
    ON DELETE CASCADE
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
    ON DELETE CASCADE
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
    FOREIGN KEY (HORAAPERTURA) REFERENCES HORARIO (ID)
ENABLE;

ALTER TABLE TIPOLOCAL
    ADD CONSTRAINT FK_TL_HORACIERRE
    FOREIGN KEY (HORACIERRE) REFERENCES HORARIO (ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE LOCALCOMERCIAL
    ADD CONSTRAINT PK_LOCALCOMERCIAL
    PRIMARY KEY (IDENTIFICADOR)
ENABLE;

ALTER TABLE LOCALCOMERCIAL
    ADD CONSTRAINT CK_ACTIVO
    CHECK (ACTIVO = 0 OR ACTIVO = 1)
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
    ON DELETE CASCADE
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------
ALTER TABLE ADMINISTRADOR
    ADD CONSTRAINT PK_ADMINISTRADOR
    PRIMARY KEY (IDENTIFICACION)
ENABLE;

ALTER TABLE ADMINISTRADOR
    ADD CONSTRAINT ND_ADMINISTRADOR_NOMBRE
    UNIQUE (NOMBRE)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------
ALTER TABLE ADMINISTRADORLOCAL
    ADD CONSTRAINT PK_ADMINISTRADORLOCAL
    PRIMARY KEY (IDENTIFICACION)
ENABLE;

ALTER TABLE ADMINISTRADORLOCAL
    ADD CONSTRAINT ND_ADMINISTRADORLOCAL_NOMBRE
    UNIQUE (NOMBRE)
ENABLE;

ALTER TABLE ADMINISTRADORLOCAL
    ADD CONSTRAINT FK_AL_LOCAL
    FOREIGN KEY (LOCAL) REFERENCES LOCALCOMERCIAL (IDENTIFICADOR)
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
    ON DELETE CASCADE
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
    CHECK (TIPO IN ('Temperatura', 'Visitante', 'Vehiculo'))
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
    ON DELETE CASCADE
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_LOCALCOMERCIAL
    FOREIGN KEY (IDLOCALCOMERCIAL) REFERENCES LOCALCOMERCIAL (IDENTIFICADOR)
    ON DELETE CASCADE
ENABLE;
    
ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_ASCENSOR
    FOREIGN KEY (IDASCENSOR) REFERENCES ASCENSOR (IDENTIFICADOR)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_PARQUEADERO
    FOREIGN KEY (IDPARQUEADERO) REFERENCES PARQUEADERO (IDENTIFICADOR)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_BANO
    FOREIGN KEY (IDBANO) REFERENCES BANO (IDENTIFICADOR)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE LECTOR
    ADD CONSTRAINT FK_L_TIPOLECTOR
    FOREIGN KEY (TIPOLECTOR) REFERENCES TIPOLECTOR (ID)
    ON DELETE CASCADE
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
    ON DELETE CASCADE
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
    PRIMARY KEY (IDVISITANTE, EMPRESADOMICILIOS)
ENABLE;

ALTER TABLE DOMICILIARIO
    ADD CONSTRAINT FK_D_VISITANTE
    FOREIGN KEY (IDVISITANTE) REFERENCES VISITANTE (IDENTIFICACION)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE DOMICILIARIO
    ADD CONSTRAINT FK_D_HORARIOINICIO
    FOREIGN KEY (HORAINICIOTURNO) REFERENCES HORARIO (ID)
ENABLE;

ALTER TABLE DOMICILIARIO
    ADD CONSTRAINT FK_D_HORARIOFINAL
    FOREIGN KEY (HORAFINALTURNO) REFERENCES HORARIO (ID)
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
    ON DELETE CASCADE
ENABLE;

ALTER TABLE EMPLEADO
    ADD CONSTRAINT FK_E_HORARIOINICIO
    FOREIGN KEY (HORAINICIOTURNO) REFERENCES HORARIO (ID)
ENABLE;

ALTER TABLE EMPLEADO
    ADD CONSTRAINT FK_E_HORARIOFINAL
    FOREIGN KEY (HORAFINALTURNO) REFERENCES HORARIO (ID)
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
    FOREIGN KEY (PROPIETARIO) REFERENCES VISITANTE (IDENTIFICACION)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE REGISTRANCARNET
    ADD CONSTRAINT PK_LECTOR_CARNET
    PRIMARY KEY (IDLECTOR, TIPOCARNET, IDVISITANTE, FECHA, HORAENTRADA, HORASALIDA)
ENABLE;

ALTER TABLE REGISTRANCARNET
    ADD CONSTRAINT FK_RC_LECTOR
    FOREIGN KEY (IDLECTOR) REFERENCES LECTOR(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE REGISTRANCARNET
    ADD CONSTRAINT FK_RC_TIPOCARNET
    FOREIGN KEY (TIPOCARNET, IDVISITANTE) REFERENCES CARNET (TIPOCARNET, IDVISITANTE)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE REGISTRANCARNET
    ADD CONSTRAINT FK_RC_HORAENTRADA
    FOREIGN KEY (HORAENTRADA) REFERENCES HORARIO (ID)
ENABLE;

ALTER TABLE REGISTRANCARNET
    ADD CONSTRAINT FK_RC_HORASALIDA
    FOREIGN KEY (HORASALIDA) REFERENCES HORARIO(ID)
ENABLE;

---------------------------------------
-- Constraints para la tabla 
---------------------------------------

ALTER TABLE REGISTRANVEHICULO
    ADD CONSTRAINT PK_LECTOR_VEHICULO
    PRIMARY KEY (IDLECTOR, VEHICULO, FECHA, HORAENTRADA, HORASALIDA)
ENABLE;

ALTER TABLE REGISTRANVEHICULO
    ADD CONSTRAINT FK_RV_LECTOR
    FOREIGN KEY (IDLECTOR) REFERENCES LECTOR (ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE REGISTRANVEHICULO
    ADD CONSTRAINT FK_RV_VEHICULO
    FOREIGN KEY (VEHICULO) REFERENCES VEHICULO (PLACA)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE REGISTRANVEHICULO
    ADD CONSTRAINT FK_RV_HORAENTRADA
    FOREIGN KEY (HORAENTRADA) REFERENCES HORARIO (ID)
ENABLE;

ALTER TABLE REGISTRANVEHICULO
    ADD CONSTRAINT FK_RV_HORASALIDA
    FOREIGN KEY (HORASALIDA) REFERENCES HORARIO(ID)
ENABLE;


COMMIT;
