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
public class HiloServidor implements Runnable {

    // Declaración de variables
    private Socket s;
    private Polleria polleria;

    // Método contructor
    public HiloServidor(Socket s, Polleria polleria) {

        this.s = s;
        this.polleria = polleria;

    }

    @Override
    public void run() {

        // Declaración de variables
        String nombreCliente;
        Integer pedidoPollos;
        Integer pedidoPatatas;
        final String mensajeDespedida = "Hasta la próxima";
        final String mensajeCerrado = "Cerrado";

        try {

            // Se establece el flujo de comunicación con los hilos clientes
            BufferedReader br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            PrintWriter pw = new PrintWriter(this.s.getOutputStream(), true);

            // Recibido <-- #01 (El cliente envía su nombre)
            nombreCliente = br.readLine();
            
            // Se atienden a los cliente uno tras otro, simulando que solo existe un dependiente aunque se genere un hilo servidor por cada hilo cliente
            this.polleria.hacerCola(nombreCliente);
            
            // Si quedan pollos disponibles se atiende
            if (this.polleria.isAbierta() && this.polleria.getPollos() > 0) {
                
                // Comunicación
                // Enviado --> #01
                pw.println("¿Cuántos pollos desea reservar?");
                // Recibido <-- #02+#03 (Pedido por parte del cliente)
                pedidoPollos = Integer.parseInt(br.readLine());
                pedidoPatatas = Integer.parseInt(br.readLine());
                System.out.println(String.format("Encargo %s: %d %s %s",
                        nombreCliente,
                        pedidoPollos,
                        pedidoPollos > 1 ? "pollos" : "pollo",
                        pedidoPatatas > 0 ? "con " + pedidoPatatas + " patatas" : ""));

                // Se comprueba si existe raciones de pollos suficientes
                if ((this.polleria.getPollos() - pedidoPollos) >= 0) {
                    // Actualización de variables estáticas del recurso compartido Pollería pollosHermanos
                    this.polleria.setPollos(this.polleria.getPollos() - pedidoPollos);
                    // Si no existen patatas suficientes el cliente acepta las disponibles
                    if (this.polleria.getPatatas() - pedidoPatatas >= 0) {
                        this.polleria.setPatatas(this.polleria.getPatatas() - pedidoPatatas);
                    } else {
                        this.polleria.setPatatas(this.polleria.getPatatas());
                    }
                } else {
                    System.out.println(String.format("Lo sentimos %s, no nos queda %d %s",
                            nombreCliente,
                            pedidoPollos,
                            (this.polleria.getPollos() - pedidoPollos) <= 1 ? "pollo" : "pollos"));
                }
                System.out.println(String.format("Tras el encargo del %s nos quedan: %s y %s",
                        nombreCliente,
                        String.format("%d %s", this.polleria.getPollos(), this.polleria.getPollos() <= 1 ? "pollo" : "pollos"),
                        String.format("%d patatas", this.polleria.getPatatas())));

                // Se despide del cliente
                // Enviado --> #02 (Mensaje de despedida)
                pw.println(mensajeDespedida);
                // Avisa al cliente siguiente
                this.polleria.siguienteCliente();

            } else {

                // Cuando no existan pollos disponibles se cierra la pollería
                polleria.cerrarPolleria();
                // El servidor comunica al cliente que no hay pollos disponibles y que venga otro día
                pw.println(mensajeCerrado);
                System.out.println((this.polleria.getPatatas() == 0
                        ? String.format("Cerramos la pollería porque no quedan raciones (%d pollos y %d patatas disponibles)",
                                this.polleria.getPollos(),
                                this.polleria.getPatatas())
                        : String.format("Han sobrado %d patatas (%d pollos y %d patatas disponibles)",
                                this.polleria.getPatatas(),
                                this.polleria.getPollos(),
                                this.polleria.getPatatas())));
            }

            // Cierre de recursos
            br.close();
            pw.close();
            this.s.close();

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e);
        } catch (NumberFormatException e) {
            System.err.println("No se ha podido convertir a tipo de dato entero. Error: " + e);
        } catch (InterruptedException e) {
            System.err.println("Hilo interrumpido inesperadamente. Error: " + e);
            Thread.currentThread().interrupt();
        }
    }
}
