
import java.io.*;
public class Ejemplo07 {
    /** 
     * @param args
     * @throws IOException
     */
    public static void main (String[] args) throws IOException{
        ProcessBuilder pb = new ProcessBuilder("CMD","/C","DIR");
        File fOut = new File ("salida.txt");
        File fErr = new File ("error.txt");
        pb.redirectOutput(fOut);
        pb.redirectError(fErr);
        Process p = pb.start();
    }   
}