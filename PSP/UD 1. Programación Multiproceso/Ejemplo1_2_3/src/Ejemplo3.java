

import java.io.*;

public class Ejemplo3 {
    public static void main (String[] args) throws IOException{
        //creamos objeto File al directorio donde está Ejemplo2
        File d = new File(".\\build\\classes");
        //proceso a ejecutar es Ejemplo2
        ProcessBuilder pb = new ProcessBuilder("java","Ejemplo2");//java es el comando para ejecutar un class
        //establecemos el directorio donde está el ejecutable
        pb.directory(d);
        System.out.print("Directorio de trabajo: ");
        System.out.println(pb.directory());
        //ejecutar proceso
        Process p = pb.start();
        //obtener la salida
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

