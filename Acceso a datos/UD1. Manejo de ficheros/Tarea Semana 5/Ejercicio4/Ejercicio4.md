Ejercicio 4. Catálogo de productos en XML y JSON

Una empresa de material informático debe distribuir su catálogo a aplicaciones que aceptan XML y a otras que utilizan JSON. La empresa necesita comprobar que ambas representaciones contienen exactamente los mismos datos y elegir formatos adecuados para distintos usos.

1. Diseña un catálogo de cinco productos, cada uno con código, nombre, categoría, precio y unidades disponibles.
2. Crea productos.xml y productos.json con la misma información, sin alterar valores entre versiones.

[Ejercicio4 JSON](productos.json)
[Ejercicio4 XML](productos.xml)

3. Compara tamaño aproximado, legibilidad, representación de listas y objetos, y facilidad de modificación.

- Prácticamente el mismo tamaño de líneas pues donde se gana longitud es en los objetos. En cuanto al peso JSON ocupa menos pues no necesita de etiquetas de apertura y cierre.
- Son muy legible ambos casos, el XML más declarativo/informativo y el JSON más compacto, útil para leer de forma más fácil muchos datos.
- En XML para representar listas y objetos se hace de la misma forma, meter etiquetas dentro de etiquetas, en JSON se usa `{}` o `[]` dependiendo del caso.
- Ambos se puden modificar facilmente, JSON para datos estructurados y XML para documentos y atributos.

4. Justifica el formato que escogerías para una API web, un documento con mucho marcado, un archivo de configuración y el intercambio entre aplicaciones.

- API web: usaría JSON pues es más ligero y se trabaja más facil con el, pues esos datos existen en código (listas, objetos, ints...) y se separan los datos a la hora de crearlo (no se puede declarar todo de la misma forma).
- Documento con mucho marcado: usaría XML pues tiene mucho marcado.
- Archivo de configuración: el JSON porque tiene sentido al ser clave valor, donde en la clave sería la configuración y el valor el valor que le das.
- Intercambio entre aplicaciones: usaría generalmente JSON por ser ligero, y los datos ya representados que tiene aunque si la app necesita XML usaría XML.

Entrega: productos.xml, productos.json y una comparación razonada de aproximadamente media página.