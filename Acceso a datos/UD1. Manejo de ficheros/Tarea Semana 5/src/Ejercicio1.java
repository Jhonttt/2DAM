
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio1 {

    private static final File FICHERO = new File("./Ejercicio1/catalogo_libros.xml");

    private static final String RESET = "\u001B[0m";
    private static final String ROJO = "\u001B[91m";
    private static final String VERDE = "\u001B[92m";
    private static final String AZUL = "\u001B[94m";
    private static final String AMARILLO = "\u001B[93m";
    private static final String BLANCO = "\u001B[97m";

    public static void main(String[] args) {

        if (!FICHERO.isFile()) {
            System.out.println(ROJO + "Error: el fichero no existe." + RESET);
            return;
        }

        if (!FICHERO.canRead()) {
            System.out.println(ROJO + "Error: no se puede leer el fichero." + RESET);
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(
                "http://apache.org/xml/features/disallow-doctype-decl", true
            );

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(FICHERO);
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("libro");

            if (lista.getLength() == 0) {
                System.out.println(AMARILLO + "No se encontraron libros." + RESET);
                return;
            }

            System.out.println(AZUL + "===== CATÁLOGO DE LIBROS =====" + RESET);

            for (int i = 0; i < lista.getLength(); i++) {
                Element libro = (Element) lista.item(i);

                String id = libro.getAttribute("id");
                String titulo = obtenerTexto(libro, "titulo");
                String autor = obtenerTexto(libro, "autor");
                String precio = obtenerTexto(libro, "precio");

                if (id.isBlank() || titulo.isBlank() ||
                    autor.isBlank() || precio.isBlank()) {
                    System.out.println(AMARILLO +
                        "Aviso: libro " + (i + 1) + " con datos incompletos." + RESET);
                    continue;
                }

                System.out.println(
                    VERDE + "ID: " + BLANCO + id +
                    AZUL + " | Título: " + BLANCO + titulo +
                    AZUL + " | Autor: " + BLANCO + autor +
                    AZUL + " | Precio: " + BLANCO + precio +
                    RESET
                );
            }

        } catch (SAXException e) {
            System.out.println(ROJO + "Error: el XML no tiene un formato válido." + RESET);
        } catch (IOException e) {
            System.out.println(ROJO + "Error al leer el fichero XML." + RESET);
        } catch (ParserConfigurationException e) {
            System.out.println(ROJO + "Error al configurar el procesador DOM." + RESET);
        }
    }

    private static String obtenerTexto(Element elemento, String etiqueta) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta);

        if (nodos.getLength() == 0) {
            return "";
        }

        return nodos.item(0).getTextContent().trim();
    }
}
