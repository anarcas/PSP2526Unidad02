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
public class HiloCliente implements Runnable {

    // Declaración de variables
    private static final int PUERTO = 12349;
    private static final String HOST = "localhost";

    @Override
    public void run() {

        // Declaración de variables
        String dia;
        String numComensales;
        String nombre;

        try {
            // Se intenta establecer una conexión con el servidor

            Socket s = new Socket(HOST, PUERTO);
            // Se intenta crear un flujo de entrada y salida de datos, buffer de comunicación con el hilo servidor
            // getInputStream()  --> método de la clase Socket mediante el cual se recibe el flujo de bytes
            // InputStreamReader --> transforma los bytes en caracteres legibles
            // BufferedReader    --> almacena los caracteres en un buffer para mostrarlos al usuario.
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // getOutputStream   --> método de la clase Socket mediante el cual se envía el fluyo de bytes
            // true (autoFlush)  --> el método constructor PrintWriter vacia el buffer tras el envío de datos (para evitar datos atrapados en memoria) 
            // PrintWriter       --> transforma los caracteres escritos por el usuario en bytes
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            // RECIBIDO 01
            // Nombre del cliente
            nombre = br.readLine();
            // RECIBIDO 02
            br.readLine();
            // RECIBIDO 03
            br.readLine();
            // ENVIO 01
            // Elegir el día de la reserva (número aleatorio entre 1:Sábado y 2:Domingo)
            dia = String.valueOf((int) (Math.random() * 2 + 1));
            pw.println(dia);
            // ENVIO 02
            // Elegir el número de comensales (número aleatorio entre 2 y 6)
            //numComensales = String.valueOf((int) (Math.random() * 5 + 2));
            numComensales = String.valueOf((int) (Math.random() * 6 + 1));
            pw.println(numComensales);
            // RECIBIDO 04
            br.readLine();
            // Imprimir mensaje en consola
            System.out.println(String.format("%s solicita mesa para %d comensales para el %s.",
                    nombre,
                    Integer.parseInt(numComensales),
                    (dia.equalsIgnoreCase("1")) ? "Sábado" : "Domingo"));

            // Cierre de recursos
            s.close();
            br.close();
            pw.close();
        } catch (IOException e) {
            System.out.println("Comunicación fallida. Error" + e);
        }

    }

}
