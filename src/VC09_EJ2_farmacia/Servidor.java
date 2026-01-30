/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC09_EJ2_farmacia;

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
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Declaración/Iniciación de variables
        Thread hilo;
        int contadorHilos=1;
        String nombreServidor;
        // Recurso compartido
        Farmacia farmaciaViva = new Farmacia();
        
        // El servidor quedará operativo 24/7 dentro de un bucle infinito esperando la llegada de un cliente que se conecte
        try (ServerSocket ss = new ServerSocket(PUERTO)) {
            System.out.println(String.format("Esperando conexión en el puerto: %d", PUERTO));

            while (true) {
                Socket socket = ss.accept();
                System.out.println("Cliente conectado.");
                nombreServidor=String.format("Servidor %d",contadorHilos);
                hilo=new Thread(new HiloServidor(socket,nombreServidor,farmaciaViva), nombreServidor);
                hilo.start();
                contadorHilos++;
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
    
}
