/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC09_EJ3_hermanos_pollos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class Servidor {

    public static final int PUERTO = 12349;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Declaración de variables
        Thread hiloServidor;
        Polleria pollosHermanos = new Polleria();

        // Se inicia el servidor con puerto 12349
        try (ServerSocket ss = new ServerSocket(PUERTO)) {
            System.out.println("Pollos Hermanos abierto. Esperando clientes...");

            // Se genera un bucle infinito para tener al servidor habilitado 24/7
            // Mientra exista una conexión se creará un hilo servidor
            while (true) {
                Socket socket = ss.accept();
                hiloServidor = new Thread(new HiloServidor(socket, pollosHermanos));
                hiloServidor.start();
            }
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor: " + e);

        }

    }

}
