/* Ejercicio 4. Crear una base de datos.  */
DROP DATABASE IF EXISTS biblioteca; 
CREATE DATABASE IF NOT EXISTS biblioteca; 
USE biblioteca; 

CREATE TABLE libros ( 
    id INT PRIMARY KEY AUTO_INCREMENT,  
    titulo VARCHAR(100) UNIQUE NOT NULL, 
    autor VARCHAR(25), 
    precio FLOAT(2), 
    anio_publicacion INT 
); 

/* Ejercicio 5. INSERT  */
INSERT INTO libros (titulo, autor, precio, anio_publicacion) VALUES 
    ("El Quijote", "Luis", 14.01, 1900), 
    ("1984", "Pedro", 33.12, 1910), 
    ("El Hobbit", "Javier", 23.55, 2002), 
    ("Fundación", "Juan", 34.12, 2003), 
    ("Dune", "Manuel", 12.53, 2004), 
    ("La sombra del viento", "Bartolomeo", 84.23, 2005); 

/* Ejercicio 6. SELECT básico  */
SELECT * FROM libros;
SELECT titulo, autor FROM libros;
SELECT * FROM libros WHERE precio > 15;
SELECT * FROM libros WHERE anio_publicacion > 2000;
SELECT * FROM libros ORDER BY titulo;
SELECT * FROM libros ORDER BY precio DESC;

/* Ejercico 7. Filtros */
SELECT * FROM libros WHERE precio > 10 AND precio < 20; /* SELECT * FROM libros WHERE precio BETWEEN 10 AND 20; */
SELECT * FROM libros WHERE autor = "Juan";
SELECT titulo FROM libros WHERE titulo COLLATE utf8mb4_bin LIKE 'El%';
SELECT titulo FROM libros WHERE titulo LIKE "% de %";
SELECT * FROM libros WHERE anio_publicacion < 1990 AND precio < 20;

/* Ejercicio 8. UPDATE */
UPDATE libros SET precio = 500 where titulo = "1984";
UPDATE libros SET autor = "Marta" where titulo LIKE "%quijote";
UPDATE libros SET precio = precio + 2;
UPDATE libros SET anio_publicacion = 1000 WHERE id = 6;

/* Ejercicio 9. DELETE */
DELETE FROM libros where id = 5;
DELETE FROM libros where anio_publicacion < 1500;

/* Ejercicio 10. Relaciones */
CREATE TABLE editoriales (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(100),
    pais varchar(50)
);

ALTER TABLE libros ADD COLUMN id_editorial INT,
    ADD CONSTRAINT fk_editorial FOREIGN KEY (id_editorial) REFERENCES editoriales(id)
        ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO editoriales (nombre, pais) VALUES
("Calevo", "Argentina"),
("Lorez", "España"),
("Fully", "EEUU");

UPDATE libros SET id_editorial = 1 WHERE id % 2;
UPDATE libros SET id_editorial = 2 WHERE NOT id % 2;
UPDATE libros SET id_editorial = 3 WHERE id = 3;

SELECT l.titulo as libro, e.nombre as editorial, e.pais FROM libros l, editoriales e WHERE l.id_editorial = e.id;

/* Reto Final. Videoclub. */
DROP DATABASE IF EXISTS videoclub; 
CREATE DATABASE IF NOT EXISTS videoclub; 
USE videoclub; 

CREATE TABLE peliculas ( 
    id INT PRIMARY KEY AUTO_INCREMENT, 
    titulo VARCHAR(100), 
    director VARCHAR(100), 
    anio INT, 
    precio_alquiler FLOAT(2) 
); 

CREATE TABLE clientes ( 
    id INT PRIMARY KEY AUTO_INCREMENT, 
    nombre VARCHAR(100), 
    email VARCHAR(100) NOT NULL, 
    CHECK (email LIKE "%@%.%") 
); 

INSERT INTO peliculas (titulo, director, anio, precio_alquiler) VALUES 
    ("Matrix", "Lana y Lilly Wachowski", 1999, 3.99), 
    ("Gladiator", "Ridley Scott", 2000, 4.50), 
    ("Interstellar", "Christopher Nolan", 2014, 4.99), 
    ("Parásitos", "Bong Joon-ho", 2019, 3.50), 
    ("Pulp Fiction", "Quentin Tarantino", 1994, 2.99); 
 
INSERT INTO clientes (nombre, email) VALUES 
    ("Juan Pérez", "juan.perez@gmail.com"), 
    ("María García", "maria.garcia@hotmail.com"), 
    ("Carlos López", "carlos.lopez@yahoo.es"), 
    ("Laura Sánchez", "laura.sanchez@gmail.com"); 

SELECT * FROM peliculas; 
SELECT * FROM peliculas WHERE anio > 2015; 
SELECT * FROM peliculas WHERE precio_alquiler < 4; 
SELECT * FROM peliculas WHERE titulo LIKE "%Pulp%"; 
UPDATE peliculas SET precio_alquiler = 1.99 WHERE titulo LIKE "Ma%"; 
DELETE FROM peliculas WHERE titulo = "Parásitos";