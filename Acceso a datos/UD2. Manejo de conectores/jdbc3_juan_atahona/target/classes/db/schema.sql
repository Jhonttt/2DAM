DROP DATABASE IF EXISTS instituto;
CREATE DATABASE instituto;

USE instituto;

CREATE TABLE alumnos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(60) NOT NULL,
  edad INT NOT NULL,
  ciclo VARCHAR(10) NOT NULL
);

INSERT INTO alumnos(nombre,edad,ciclo) VALUES
('Ana',20,'DAM'),('Luis',22,'DAW'),('Marta',19,'DAM');

CREATE TABLE cuentas (
  id INT PRIMARY KEY,
  titular VARCHAR(60) NOT NULL,
  saldo DECIMAL(10,2) NOT NULL
);

CREATE TABLE movimientos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  origen INT NOT NULL, destino INT NOT NULL,
  importe DECIMAL(10,2) NOT NULL,
  CONSTRAINT fk_ori FOREIGN KEY (origen) REFERENCES cuentas(id),
  CONSTRAINT fk_des FOREIGN KEY (destino) REFERENCES cuentas(id)
);
INSERT INTO cuentas VALUES (1,'Ana',500.00),(2,'Luis',100.00);
