import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Ejercicio5 {

  public static void main(String[] args) {
    try {
      File xml = new File("Ejercicio5/catalogo_libros.xml");
      File xsl = new File("Ejercicio5/catalogo.xsl");
      File html = new File("Ejercicio5/catalogo.html");

      TransformerFactory factory = TransformerFactory.newInstance();

      Transformer transformer = factory.newTransformer(new StreamSource(xsl));

      transformer.transform(new StreamSource(xml), new StreamResult(html));

      System.out.println("HTML generado correctamente.");

    } catch (TransformerException e) {
      System.err.println("Error al transformar el XML.");
    }
  }

}
