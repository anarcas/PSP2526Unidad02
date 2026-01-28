/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC08_EJ02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class Servidor {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        RecursoCompartido rc = new RecursoCompartido();

        ServerSocket ss = new ServerSocket(12349);
        System.out.println("Esperando conexión...");

        while (true) {
            Socket socket = ss.accept();
            new Thread(new HiloServidor(socket, rc)).start();
        }

    }

}
