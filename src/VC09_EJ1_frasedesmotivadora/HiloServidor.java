/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC09_EJ1_frasedesmotivadora;

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
    private final String FRASE_DESMOTIVADORA = "Si no cambias tu forma de estudiar no llegarás a ningún sitio.";
    private final String FRASE_MOTIVADORA = "Eres fenómeno, sigue así que conseguirás lo que te propongas.";
    private String nombreServidor;

    public HiloServidor(Socket socket, String nombre) {
        this.s = socket;
        this.nombreServidor = nombre;
    }

    @Override
    public void run() {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            // El servidor se presenta al cliente indicándole su nombre (equivale a preguntarle qué tipo de frase) y recibe el nombre del cliente
            pw.println(this.nombreServidor);
            String nombreCliente;
            nombreCliente=br.readLine();

            // El servidor recibe la solicitud del cliente y le envía la petición
            String linea;

            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("FIN")) {
                System.out.println(String.format("Hola soy el %s y el %s solicita una frase tipo: %s", this.nombreServidor,nombreCliente, linea));

                if (linea.equals("desmotivadora")) {
                    pw.println(FRASE_DESMOTIVADORA);
                } else {
                    pw.println(FRASE_MOTIVADORA);
                }
            }

            // Cierre de recursos
            s.close();
            br.close();
            pw.close();

        } catch (SocketException e) {
            System.err.println("Error de socket: " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
