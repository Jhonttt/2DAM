

import java.io.*;
public class Ejemplo05 {
    /** 
     * @param args
     */
    public static void main (String[] args){
	//InputStreamReader convierte los bytes de System.in en caracteres
        InputStreamReader in = new InputStreamReader(System.in);
	//BufferedReader permite leer líneas completas de texto de forma más cómoda.
        BufferedReader br = new BufferedReader(in);
        String texto;
        try {
            System.out.println("Introduce una cadena: ");
            texto = br.readLine();
            System.out.println("Cadena escrita: " + texto);
            in.close();
        } catch (Exception e) {e.printStackTrace();}
    }    
}
