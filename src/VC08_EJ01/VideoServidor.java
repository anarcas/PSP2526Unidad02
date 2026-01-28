/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC08_EJ01;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class VideoServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        // Se instancia un objeto ServerSocket
        ServerSocket ss = new ServerSocket (12349);
        System.out.println("Esperando conexión...");
        // Se declara una variable tipo Socket para almacenar la conexión establecida
        Socket s = ss.accept();
        System.out.println("Conectado");
        
        // Se captura el buffer de entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // Se imprime el mensaje de salida
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
        
        // El servidor escucha al cliente
        String linea;
        linea=br.readLine();
        System.out.println("El cliente dice: " + linea);
        // El servidor responde al cliente
        pw.println("Qué tal?");
        
        // Cierre de recursos
        ss.close();
        s.close();
        br.close();
        pw.close();
        
    }
    
}
