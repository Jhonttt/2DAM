Ejercicio 3. Centro educativo: datos anidados y conversión

El centro necesita representar un curso con su profesor responsable y el alumnado matriculado. Se desea que los datos estén organizados y puedan intercambiarse entre aplicaciones que usan distintos formatos.

1. Crea curso.json con datos generales del curso, un objeto profesor (nombre y apellidos) y un array alumnos con tres estudiantes como mínimo.
2. Incluye para cada alumno id, nombre, edad y ciclo; evita repetir información común del curso.
   
[Ejercicio3 JSON](curso.json)

1. Representa manualmente la misma información básica en XML.
   
[Ejercicio3 XML](curso.xml)

1. Explica al menos tres diferencias entre las estructuras XML y JSON obtenidas.
   
1.- La cantidad de líneas
2.- La diferencia de crearlos JSON -> clave valor - XML -> etiquetas
3.- En XML todo es texto, no se diferencian los datos, en JSON si, si es texto entre comillas si es número solo el número
4.- En XML para representar un array (no existen estos datos, ni array ni objetos) se utiliza una etiqueta (por tanto una palabra más que en JSON) extra para cada iteracción del array.

Entrega: curso.json, XML equivalente y comparación.