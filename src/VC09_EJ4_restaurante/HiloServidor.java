/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC09_EJ4_restaurante;

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
    private Restaurante restaurante;
    private int numClienteAtendido;

    // Método constructor
    public HiloServidor(Socket s, Restaurante restaurante,int numClienteAtendido) {
        this.s = s;
        this.restaurante = restaurante;
        this.numClienteAtendido=numClienteAtendido;
    }

    // Método getter

    public int getNumClienteAtendido() {
        return numClienteAtendido;
    }
    
    
    @Override
    public void run() {
        
        String nombreCliente;
        Integer dia;
        Integer numComensales;

        try {
            // Se establece el flujo de comunicación (buffer de entrada y salida de datos)
            BufferedReader br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
            PrintWriter pw = new PrintWriter(this.s.getOutputStream(), true);

            // ENVIO 01
            // Nombre del cliente
            nombreCliente=String.format("Cliente%d",this.getNumClienteAtendido());
            pw.println(nombreCliente);
            
            if (this.restaurante.isCerrado()){
            
                System.out.println(String.format("No quedan plazas %s, se cierran las reservas.",nombreCliente));
                // ENVIO 02
                pw.println("Las reservas están cerradas.");
                
            } else{
            
            // ENVIO 02
            pw.println("¿Para qué día desea reservar? (Sábado o Domingo)");
            // ENVIO 03
            pw.println("¿Para cuántos comensales?");
            // RECIBIDO 01
            dia=Integer.parseInt(br.readLine());
            // RECIBIDO 02
            numComensales=Integer.parseInt(br.readLine());
            // ENVIO 03
            // Realizar la reserva
            pw.println(String.valueOf(this.restaurante.realizarReserva(dia, numComensales, nombreCliente)));

            }
            
            // Cierre de recursos
            br.close();
            pw.close();

        } catch (IOException e) {
            System.out.println("Comunicación fallida. Error: " + e);
        }
    }

}
