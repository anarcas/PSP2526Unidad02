/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC09_EJ4_restaurante;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class Servidor {

    final static int PUERTO = 12349;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Declaración de variables
        Thread hiloServidor;
        int contadorClientesAtendidos=1;

        // Recurso compartido
        Restaurante besana = new Restaurante();

        // Se enciende el servidor
        try (ServerSocket ss = new ServerSocket(PUERTO)) {
            System.out.println("Restaurante abierto se encuentra esperando clientes...");

            // Mientras exista una conexión al servidor se lanzará un hilo servidor (servidor operativo 24/7)
            while (true) {

                Socket s = ss.accept();
                System.out.println("Conexión establecida.");

                hiloServidor = new Thread(new HiloServidor(s, besana,contadorClientesAtendidos));
                contadorClientesAtendidos++;
                hiloServidor.start();

            }

        } catch (IOException e) {

            System.err.println("Se ha perdido la conexión. Error: " + e);

        }

    }

}
