Ejercicio 2. Centro educativo: comprensión y creación de JSON

Un centro de FP quiere compartir información de alumnado con otras aplicaciones. Antes de preparar el fichero definitivo,el equipo necesita interpretar correctamente cómo se organizan los datos en JSON.

Analiza el siguiente ejemplo:
{
  "alumnos": [
    { "id": 1, "nombre": "Lucía", "edad": 19, "ciclo": "DAM" },
    { "id": 2, "nombre": "Mario", "edad": 20, "ciclo": "ASIR" }
  ]
}


1. Identifica el objeto principal, el array alumnos, sus objetos y ejemplos de claves y valores. Indica el tipo de dato de los valores.

Es un array de alumnos donde contiene 4 valores diferentes. El id que es un número (int), el nombre que es texto (String) la eddad otro número (int) y el ciclo otro texto (String). Esos valores serían para cada alumno todo dentro del array de alumnos.

2. Crea alumnos.json con al menos tres alumnos diferentes y los campos id, nombre, edad y ciclo.
[Ejercicio2 JSON](Ejercicio2.json)

3. Comprueba que el JSON es válido y explica por qué la colección de alumnos se representa mediante un array.
   
El JSON es válido ya que esta todo separado por comas menos el último aunque se podría poner y cada dato con su key (key - value). Se representa mediante un array para decir/indicar que van a haber muchos alumnos, y luego ya dentro de cada uno se ponen las propiedades de dicho alumno. 

Entrega: alumnos.json y análisis de la estructura.