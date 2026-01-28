/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC08_EJ03_EJ01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author anaranjo
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Socket s = new Socket("localhost", 12349);

        // Se captura el buffer de entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // Se imprimer el mensaje de salida
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

        // El cliente envía 25 números aleatorios al servidor
        Random numAleatorio = new Random();
        String mensajeDespedida = "FIN";
        int numero;
        for (int i = 0; i < 25; i++) {
            numero = numAleatorio.nextInt(100) + 1;
            pw.println(numero);
        }
        // Despedida del cliente
        pw.println(mensajeDespedida);

        // El cliente escucha al servidor
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(String.format("El servidor dice: %s", linea));
        }

        // Cierre de recursos
        s.close();
        br.close();
        pw.close();
    }

}
