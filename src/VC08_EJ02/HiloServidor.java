/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC08_EJ02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anaranjo
 */
public class HiloServidor implements Runnable {

    private final Socket s;
    private RecursoCompartido rc;

    public HiloServidor(Socket socket, RecursoCompartido rc) {
        this.s = socket;
        this.rc = rc;
    }

    @Override
    public void run() {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            int numCliente = rc.incrementarContador();

            String linea;
            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("Adios")) {
                System.out.println("El cliente " + numCliente + " dice: " + linea);
                pw.println("Hola");
            }

            System.out.println("Se fue el cliente: " + numCliente);
            pw.println("Adios");
            br.close();
            pw.close();
            s.close();

        } catch (SocketException e) {
            System.err.println("Error de socket: " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
