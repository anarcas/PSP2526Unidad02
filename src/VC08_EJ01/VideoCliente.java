/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC08_EJ01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class VideoCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Socket s = new Socket("localhost",12349);
        
        // Se captura el buffer de entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // Se imprimer el mensaje de salida
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
        
        // El cliente habla primero al servidor
        pw.println("Hola");
        
        // El cliente escucha al servidor
        String linea;
        linea=br.readLine();
        System.out.println("El servidor dice: " + linea);
        // El cliente responde al servidor por segunda vez
        pw.println("Qué tal?");
        
        // Cierre de recursos
        s.close();
        br.close();
        pw.close();
    }
    
}
