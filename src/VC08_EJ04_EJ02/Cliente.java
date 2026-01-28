/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC08_EJ04_EJ02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
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

        // Declaración de variables auxiliares
        Random numAleatorio = new Random();
        String mensajeRespuesta;
        String mensajeDespedida = "FIN";
        String linea;
        Integer nNumeros;
        int numero;

        // El cliente pregunta al servidor cuántos números quiere que genere
        pw.println("Hola Servidor ¿Cuántos números necesitas?");

        // El cliente recibe del servidor la cantidad de números que debe generar aleatoriamente
        linea = br.readLine();
        System.out.println(String.format("El servidor dice: Necesito %s números.", linea));
        nNumeros = Integer.parseInt(linea);
        // Se define una lista donde se almacenarán la cantidad de números aleatorios
        int[] numeros = new int[nNumeros];
        // El cliente genera la cantidad de números aleatorios solicitados por el servidor
        for (int i = 0; i < nNumeros; i++) {
            numero = numAleatorio.nextInt(100) + 1;
            numeros[i] = numero;
        }
        // Se construye una cadena de caracteres delimintada por el caracter "|" de la lista de números generada 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeros.length; i++) {
            sb.append(numeros[i]);
            // Solo añadimos el separador si no es el último elemento
            if (i < numeros.length - 1) {
                sb.append(";");
            }
        }
        // El cliente responde al servidor con la lista de números delimitada
        mensajeRespuesta = sb.toString();
        pw.println(mensajeRespuesta);

        // Despedida del cliente
        linea=br.readLine();
        System.out.println(String.format("El servidor dice: La suma de todos los números es --> %s",linea));
        pw.println(mensajeDespedida);
        linea=br.readLine();
        System.out.println(String.format("El servidor dice: %s", linea));
        
        // Cierre de recursos
        s.close();
        br.close();
        pw.close();
    }

}
