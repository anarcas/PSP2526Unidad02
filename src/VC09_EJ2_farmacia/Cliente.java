/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC09_EJ2_farmacia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class Cliente implements Runnable {

    private static final String HOST = "localhost";
    private static final int PUERTO = 12349;
    private String nombreCliente;

    public Cliente(String nombre) {
        this.nombreCliente = nombre;
    }

    @Override
    public void run() {

        String linea;
        String nombreServidor;
        final String DESPEDIDA = "FIN";

        String[] pedido = new String[2];
        String mensajePedido;

        try {

            Socket s = new Socket(HOST, PUERTO);

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            // El cliente se presenta al servidor de la farmacia y recibe el nombre del servidor
            pw.println(this.nombreCliente);
            nombreServidor = br.readLine();

            // El cliente solicita un determinado medicacmento y una determinada cantidad (ambas aleatorias) siempre y cuando la farmacia esté abierta
            // Tipo de medicamento
            pedido[0] = String.valueOf((int) (Math.random() * 3 + 1));
            // Cantidad del medicamento
            pedido[1] = String.valueOf((int) (Math.random() * 2 + 1));

            System.out.println(String.format("El %s ha realizado un pedido de %s de %s", nombreCliente, pedido[1], pedido[0]));
            mensajePedido = String.join(";", pedido);

            pw.println(mensajePedido);

            // El cliente se despide y cierra los recursos
            pw.println(DESPEDIDA);
            s.close();
            br.close();
            pw.close();

        } catch (IOException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }

    }
}
