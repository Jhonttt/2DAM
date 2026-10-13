- [`Main.java`](src/Main.java): valida los argumentos y muestra la suma.

Las constantes declaradas son las posiciones del array para evitar `magic strings`, y 4 códigos ANSI para darle color y vida al programa y que sea todo más visual.

Hay una función para normalizar los números recibidos y poder recibir números tanto con comas como con puntos.

Una función para validar si un número es válido, en caso de ser así recibe `true`, en caso contrario recibe `false`.

Y luego en la función principal se valida que se reciban exactamente 2 argumentos; en caso contrario, se muestra un mensaje y se termina con un código de estado `2` que es de error de argumentos. Luego se utilizan las funciones previamente mencionadas para validar que sean números válidos y como antes se termina con un código de estado `2` si no son válidos. Luego los datos recibidos por argumentos se normalizan y se parsean y posteriormente se suman. El `print` contiene la sentencia `Locale.ROOT` que lo que hace es poner los decimales con punto y no con coma y por último se termina con un código de estado exitoso `0`.

- [`Executer.java`](src/Executer.java): 
  
Se declara un ArrayList con los comandos que se pasarán, es decir `java...`, `-Dstdout.encoding=UTF-8` y `-Dstderr.encoding=UTF-8` para que escriba en UTF-8. Se pasan directamente a la lista con `List.of` que crea una lista inmutable y se copian en el ArrayList porque este sí es modificable, lo que permite añadir después los argumentos.

La decisión de usar un ArrayList en vez de pasarlo como varargs en el constructor del `ProcessBuilder` es para poder añadir cuantos argumentos se quieran y que no dé un error, permitiendo poder reutilizar las validaciones del `Main`.

Luego se añaden los argumentos recibidos con el método `addAll` y como los `args` son un array de `String` y el método `addAll` solo permite colecciones se convierte en una lista con `Arrays.asList` y luego se pasa el array de args. Eso lo que hace es poner tantos argumentos como se pongan al final de la instrucción previamente puesta.

Se instancia el proceso y se empieza además de mover todo el output tanto el de error como el normal.

Se lee la salida del proceso línea a línea y se muestra por consola. Si se produce un error de lectura, se destruye el proceso hijo con `destroy()`, ya que no termina automáticamente cuando lo hace el padre, y se sale con el código 1.

En caso de que la lectura no haya fallado, se espera a que el proceso termine con `waitFor()` y se sale con su mismo código de salida.

---

Se utilizo la IA para generar el javadoc y revisar optativas a hacer el código mejor una vez hecho.