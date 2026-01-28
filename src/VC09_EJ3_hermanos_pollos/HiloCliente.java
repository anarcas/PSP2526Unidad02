/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC09_EJ3_hermanos_pollos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class HiloCliente implements Runnable {

    // Declaración de variables
    private String nombre;
    private static final int PUERTO = 12349;
    private static final String HOST = "localhost";

    // Método contructor
    public HiloCliente(String nombre) {

        this.nombre = nombre;

    }

    @Override
    public void run() {

        // Declaración de variables
        String linea;
        int pedidoPollos;
        int pedidoPatatas;
        final String mensajeCerrado = "Cerrado";

        try {
            // Se establece una conexión con el hilo servidor
            Socket s = new Socket(HOST, PUERTO);
            // Se establece un flujo de comunicación con el hilo servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            // Comunicación
            // Enviado --> #01 (Nombre del cliente)
            pw.println(this.nombre);
            // Recibido <-- #01 (El servidor pregunta el pedido solicitado)
            linea = br.readLine();
            if (!linea.equalsIgnoreCase(mensajeCerrado)) {
                // El cliente simula un pedido de pollos y patatas
                pedidoPollos = (int) (Math.random() * 3 + 1);
                pedidoPatatas = (int) (Math.random() * 5);
                // Enviado --> #02+#03 (Se le envía al servidor el pedido)
                pw.println(pedidoPollos);
                pw.println(pedidoPatatas);
                System.out.println(String.format("El %s solicita encargo de: %d %s",
                        this.nombre,
                        pedidoPollos,
                        String.format("%s %s",
                                pedidoPollos > 1 ? "pollos" : "pollo",
                                pedidoPatatas > 0 ? "con " + pedidoPatatas + " patatas" : "")));
                // Recibido <-- #02 (Mensaje de despedida)
                linea = br.readLine();
            } else {
                System.err.println(String.format("El %s se encuentra la pollería cerrada y volverá otro día", this.nombre));
            }
            // Cierre de recursos
            br.close();
            pw.close();
            s.close();

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e);
        }

    }

}
