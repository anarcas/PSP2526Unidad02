/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC08_EJ03_EJ01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        // Se instancia un objeto ServerSocket
        ServerSocket ss = new ServerSocket(12349);
        System.out.println("Esperando conexión...");
        // Se declara una variable tipo Socket para almacenar la conexión establecida
        Socket s = ss.accept();
        System.out.println("Conectado");

        // Se captura el buffer de entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // Se imprime el mensaje de salida
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

        String linea;
        String mensajeRespuesta;
        Integer numero;
        String numeroTXT;
        int contador = 0;
        String mensajeDespedida = "FIN";
        while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("FIN")) {
            // El servidor escucha al cliente
            contador++;
            mensajeRespuesta = String.format("El cliente dice: El %dº número aleatorio es: %s", contador, linea);
            System.out.println(mensajeRespuesta);

            // El servidor responde al cliente
            numero = Integer.parseInt(linea);
            if (numero % 2 == 0) {
                numeroTXT = String.valueOf(numero);
                mensajeRespuesta = String.format("El %dº número aleatorio (%s) es par", contador, numeroTXT);
                pw.println(mensajeRespuesta);
            } else {
                numeroTXT = String.valueOf(numero);
                mensajeRespuesta = String.format("El %dº número aleatorio (%s) es impar", contador, numeroTXT);
                pw.println(mensajeRespuesta);
            }
        }
        // Despedida del servidor previo al cierre de los recursos
        System.out.println(String.format("El cliente dice: %s", linea));
        pw.println(mensajeDespedida);

        // Cierre de recursos
        ss.close();
        s.close();
        br.close();
        pw.close();
    }

}
