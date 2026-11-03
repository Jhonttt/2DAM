DROP DATABASE IF EXISTS Alumnos01;

CREATE DATABASE Alumnos01;

USE Alumnos01;

CREATE TABLE
  alumno (
    NIA INT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Apellidos VARCHAR(100) NOT NULL,
    Genero VARCHAR(20),
    Fecha_nacimiento DATE,
    Ciclo VARCHAR(50),
    Curso VARCHAR(20),
    Grupo VARCHAR(20)
  );