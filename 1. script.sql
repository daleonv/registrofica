-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: Diego Leon ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database;
-- -- ddl-end --
-- 

-- object: public.adm_administrador | type: TABLE --
-- DROP TABLE IF EXISTS public.adm_administrador CASCADE;
CREATE TABLE public.adm_administrador(
	username varchar(50) NOT NULL,
	password varchar(250) NOT NULL,
	CONSTRAINT adm_administrador_pk PRIMARY KEY (username)

);
-- ddl-end --
COMMENT ON TABLE public.adm_administrador IS 'Tabla destinada al almacenamiento de credenciales administrativas';
-- ddl-end --
COMMENT ON COLUMN public.adm_administrador.username IS 'Clave primaria de la tabla adm_administrador, campo destinado al Clave primaria de la tabla adm_administrador y nombre de usuario del administrador';
-- ddl-end --
COMMENT ON COLUMN public.adm_administrador.password IS 'Campo destinado al almacenamiento de contraseñas encriptadas de los administradores';
-- ddl-end --
ALTER TABLE public.adm_administrador OWNER TO postgres;
-- ddl-end --

-- object: public.aca_sala | type: TABLE --
-- DROP TABLE IF EXISTS public.aca_sala CASCADE;
CREATE TABLE public.aca_sala(
	id_sala serial NOT NULL,
	nombre varchar(100),
	CONSTRAINT aca_sala_pk PRIMARY KEY (id_sala)

);
-- ddl-end --
COMMENT ON TABLE public.aca_sala IS 'Tabla destinada al almacenamiento de nombre de auditorios, aulas o laboratorios que un administrador considere aptos para ser prestados';
-- ddl-end --
COMMENT ON COLUMN public.aca_sala.id_sala IS 'Clave primaria de la tabla aca_sala';
-- ddl-end --
COMMENT ON COLUMN public.aca_sala.nombre IS 'Campo destinado a almacenar el nombre de las aulas, laboratorios o auditorios';
-- ddl-end --
COMMENT ON CONSTRAINT aca_sala_pk ON public.aca_sala  IS 'Clave primaria de la tabla aca_sala';
-- ddl-end --
ALTER TABLE public.aca_sala OWNER TO postgres;
-- ddl-end --

-- object: public.aca_carrera | type: TABLE --
-- DROP TABLE IF EXISTS public.aca_carrera CASCADE;
CREATE TABLE public.aca_carrera(
	id_carrera serial NOT NULL,
	id_facultad int4,
	nombre varchar(100),
	abreviatura varchar(25),
	CONSTRAINT aca_carrera_pk PRIMARY KEY (id_carrera)

);
-- ddl-end --
COMMENT ON TABLE public.aca_carrera IS 'Tabla destinada al almacenamiento el nombre de las carreras de la facultad';
-- ddl-end --
COMMENT ON COLUMN public.aca_carrera.id_carrera IS 'Clave primaria de la tabla aca_carrera';
-- ddl-end --
COMMENT ON COLUMN public.aca_carrera.id_facultad IS 'Clave foránea de la tabla aca_facultad';
-- ddl-end --
COMMENT ON COLUMN public.aca_carrera.nombre IS 'Campo desintando a almacenar el nombre de las carreras';
-- ddl-end --
COMMENT ON COLUMN public.aca_carrera.abreviatura IS 'Corresponde a las siglas abreviadas del nombre de carrera';
-- ddl-end --
ALTER TABLE public.aca_carrera OWNER TO postgres;
-- ddl-end --

-- object: public.reg_persona | type: TABLE --
-- DROP TABLE IF EXISTS public.reg_persona CASCADE;
CREATE TABLE public.reg_persona(
	cedula varchar(50) NOT NULL,
	id_carrera int4,
	nombre varchar(50),
	apellido varchar(50),
	correo varchar(50),
	celular varchar(50),
	password varchar(250),
	CONSTRAINT reg_persona_pk PRIMARY KEY (cedula)

);
-- ddl-end --
COMMENT ON TABLE public.reg_persona IS 'Tabla desinada a almacenar datos básicos que un usuario del sistema debe llenar en un formulario para solicitar un aula, auditorio a laboratorio';
-- ddl-end --
COMMENT ON COLUMN public.reg_persona.cedula IS 'Número de cédula del usuario y clave primaria de la tabla reg_persona';
-- ddl-end --
COMMENT ON COLUMN public.reg_persona.id_carrera IS 'Clave foránea de la tabla carrera';
-- ddl-end --
COMMENT ON COLUMN public.reg_persona.nombre IS 'Campo destinado a almacenar el nombre del usuario';
-- ddl-end --
COMMENT ON COLUMN public.reg_persona.apellido IS 'Campo desitinado a almacenar el apellido del usuario';
-- ddl-end --
COMMENT ON COLUMN public.reg_persona.correo IS 'Campo desitinado a almacenar el correo del usuario';
-- ddl-end --
COMMENT ON COLUMN public.reg_persona.celular IS 'Campo desitinado a almacenar el número de celular del usuario';
-- ddl-end --
COMMENT ON COLUMN public.reg_persona.password IS 'Contiene la contraseña cifrada de un usuario';
-- ddl-end --
ALTER TABLE public.reg_persona OWNER TO postgres;
-- ddl-end --

-- object: public.reg_registro | type: TABLE --
-- DROP TABLE IF EXISTS public.reg_registro CASCADE;
CREATE TABLE public.reg_registro(
	id_registro serial NOT NULL,
	cedula varchar(50),
	id_sala int4,
	id_motivo int4,
	id_estado int4,
	inicio timestamp,
	fin timestamp,
	descripcion text,
	CONSTRAINT reg_registro_pk PRIMARY KEY (id_registro)

);
-- ddl-end --
COMMENT ON TABLE public.reg_registro IS 'Tabla destinada al almacenamiento de los datos del registro efectuado';
-- ddl-end --
COMMENT ON COLUMN public.reg_registro.id_registro IS 'Clave primaria de la tabla reg_registro';
-- ddl-end --
COMMENT ON COLUMN public.reg_registro.cedula IS 'Clave foránea de la tabla reg_persona';
-- ddl-end --
COMMENT ON COLUMN public.reg_registro.id_sala IS 'Clave foránea de la tabla aca_sala';
-- ddl-end --
COMMENT ON COLUMN public.reg_registro.id_motivo IS 'Detalla el o los motivos por los que se solicita el préstamo del aula, Clave foránea de la tabla reg_motivo';
-- ddl-end --
COMMENT ON COLUMN public.reg_registro.id_estado IS 'Clave foránea de la tabla reg_estado';
-- ddl-end --
COMMENT ON COLUMN public.reg_registro.inicio IS 'Fecha y hora inicial de la reservación';
-- ddl-end --
COMMENT ON COLUMN public.reg_registro.fin IS 'Fecha y hora final de la reservación';
-- ddl-end --
COMMENT ON COLUMN public.reg_registro.descripcion IS 'Contiene información de motivo en caso de seleccionar la opción "otro"';
-- ddl-end --
ALTER TABLE public.reg_registro OWNER TO postgres;
-- ddl-end --

-- object: public.aca_facultad | type: TABLE --
-- DROP TABLE IF EXISTS public.aca_facultad CASCADE;
CREATE TABLE public.aca_facultad(
	id_facultad serial NOT NULL,
	nombre varchar(250),
	abreviatura varchar(50),
	CONSTRAINT aca_facultad_pk PRIMARY KEY (id_facultad)

);
-- ddl-end --
COMMENT ON TABLE public.aca_facultad IS 'Tabla destinada al almacenamiento de las facultades de la UTN';
-- ddl-end --
COMMENT ON COLUMN public.aca_facultad.id_facultad IS 'Clave primaria de la tabla facultad';
-- ddl-end --
COMMENT ON COLUMN public.aca_facultad.nombre IS 'Campo destinado a almacenar el nombre de la facultad';
-- ddl-end --
COMMENT ON COLUMN public.aca_facultad.abreviatura IS 'Campo destinado al almacenaje de siglas de cada facultad';
-- ddl-end --
ALTER TABLE public.aca_facultad OWNER TO postgres;
-- ddl-end --

-- object: public.reg_motivo | type: TABLE --
-- DROP TABLE IF EXISTS public.reg_motivo CASCADE;
CREATE TABLE public.reg_motivo(
	id_motivo serial NOT NULL,
	detalle varchar(500),
	CONSTRAINT reg_motivo_pk PRIMARY KEY (id_motivo)

);
-- ddl-end --
COMMENT ON COLUMN public.reg_motivo.id_motivo IS 'Clave primaria de la tabla reg_motivo';
-- ddl-end --
ALTER TABLE public.reg_motivo OWNER TO postgres;
-- ddl-end --

-- object: public.reg_estado | type: TABLE --
-- DROP TABLE IF EXISTS public.reg_estado CASCADE;
CREATE TABLE public.reg_estado(
	id_estado serial NOT NULL,
	nombre varchar(100),
	CONSTRAINT reg_estado_pk PRIMARY KEY (id_estado)

);
-- ddl-end --
COMMENT ON TABLE public.reg_estado IS 'Tabla destinada al alamcenamiento de tipos de estados posibles para un registro de reservación';
-- ddl-end --
COMMENT ON COLUMN public.reg_estado.nombre IS 'Corresponde a los tipos de estado en que puede encontrarse un registro de reservación';
-- ddl-end --
ALTER TABLE public.reg_estado OWNER TO postgres;
-- ddl-end --

-- object: aca_carrera_aca_facultad_fk | type: CONSTRAINT --
-- ALTER TABLE public.aca_carrera DROP CONSTRAINT IF EXISTS aca_carrera_aca_facultad_fk CASCADE;
ALTER TABLE public.aca_carrera ADD CONSTRAINT aca_carrera_aca_facultad_fk FOREIGN KEY (id_facultad)
REFERENCES public.aca_facultad (id_facultad) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: aca_persona_aca_carrera_fk | type: CONSTRAINT --
-- ALTER TABLE public.reg_persona DROP CONSTRAINT IF EXISTS aca_persona_aca_carrera_fk CASCADE;
ALTER TABLE public.reg_persona ADD CONSTRAINT aca_persona_aca_carrera_fk FOREIGN KEY (id_carrera)
REFERENCES public.aca_carrera (id_carrera) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: reg_registro_aca_auditorio_fk | type: CONSTRAINT --
-- ALTER TABLE public.reg_registro DROP CONSTRAINT IF EXISTS reg_registro_aca_auditorio_fk CASCADE;
ALTER TABLE public.reg_registro ADD CONSTRAINT reg_registro_aca_auditorio_fk FOREIGN KEY (id_sala)
REFERENCES public.aca_sala (id_sala) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: reg_registro_reg_persona_fk | type: CONSTRAINT --
-- ALTER TABLE public.reg_registro DROP CONSTRAINT IF EXISTS reg_registro_reg_persona_fk CASCADE;
ALTER TABLE public.reg_registro ADD CONSTRAINT reg_registro_reg_persona_fk FOREIGN KEY (cedula)
REFERENCES public.reg_persona (cedula) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: reg_registro_reg_motivo_fk | type: CONSTRAINT --
-- ALTER TABLE public.reg_registro DROP CONSTRAINT IF EXISTS reg_registro_reg_motivo_fk CASCADE;
ALTER TABLE public.reg_registro ADD CONSTRAINT reg_registro_reg_motivo_fk FOREIGN KEY (id_motivo)
REFERENCES public.reg_motivo (id_motivo) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: reg_registro_reg_estado_fk | type: CONSTRAINT --
-- ALTER TABLE public.reg_registro DROP CONSTRAINT IF EXISTS reg_registro_reg_estado_fk CASCADE;
ALTER TABLE public.reg_registro ADD CONSTRAINT reg_registro_reg_estado_fk FOREIGN KEY (id_estado)
REFERENCES public.reg_estado (id_estado) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --