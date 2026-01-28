/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC09_EJ1_frasedesmotivadora;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class Servidor {

    private static final int PUERTO = 12349;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Thread hilo;
        int contadorHilos=1;
        String nombreServidor;
        
        try (ServerSocket ss = new ServerSocket(PUERTO)) {
            System.out.println(String.format("Esperando conexión en el puerto: %d", PUERTO));

            while (true) {
                Socket socket = ss.accept();
                System.out.println("Cliente conectado.");
                nombreServidor=String.format("Servidor %d",contadorHilos);
                hilo=new Thread(new HiloServidor(socket,nombreServidor), nombreServidor);
                hilo.start();
                contadorHilos++;
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }

    }

}
