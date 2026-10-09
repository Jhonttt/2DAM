

import java.io.*;

public class Ejemplo2 {
    public static void main(String[] args) throws IOException {
        //Ejecutamos el proceso DIR
        Process p = new ProcessBuilder("CMD","/C","DIR").start();
        
        //Mostramos caracter a caracer la salida del comando
        try {
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read())!=-1){
                System.out.print((char) c);
            }
            is.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
