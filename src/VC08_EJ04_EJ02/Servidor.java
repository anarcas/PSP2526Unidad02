/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC08_EJ04_EJ02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

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

        // Declaración de variables auxiliares
        String linea;
        String mensajeRespuesta;
        Integer numero;
        String numeroTXT;
        int contador = 0;
        String mensajeDespedida = "FIN";
        Random cantNumeros=new Random();
        int nNumeros;
        
        // El servidor recibe la pregunta inicial del cliente mostrandola por pantalla
        linea=br.readLine();
        System.out.println(String.format("El cliente dice: %s",linea));
        // El servidor genera aleatoriamente una cantidad de números y se los pasa al cliente
        nNumeros=cantNumeros.nextInt(25)+1;
        mensajeRespuesta=String.valueOf(nNumeros);
        pw.println(mensajeRespuesta);
        
        // El servidor recibe la lista de números a sumar
        linea=br.readLine();
        System.out.println(String.format("El cliente dice: La lista de números es la siguiente --> %s",linea));
        // Se reconvierte la cadena de Strings de números en una lista
        int suma=0;
        String [] listaNumeros=linea.split(";");
        
        for (int i = 0; i < listaNumeros.length; i++) {
            suma+=Integer.parseInt(listaNumeros[i]);
        }
        // El servidor responde al cliente con la suma de los números
        mensajeRespuesta=String.valueOf(suma);
        pw.println(mensajeRespuesta);
        
        // Despedida del servidor previo al cierre de los recursos
        linea=br.readLine();
        System.out.println(String.format("El cliente dice: %s", linea));
        pw.println(mensajeDespedida);

        // Cierre de recursos
        ss.close();
        s.close();
        br.close();
        pw.close();
    }

}
