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

/**
 *
 * @author anaranjo
 */
public class Cliente implements Runnable {
    
    private static final String HOST="localhost";
    private static final int PUERTO=12349;
    private String nombreCliente;
    
    public Cliente(String nombre){
        this.nombreCliente=nombre;
    }

    @Override
    public void run() {

        String linea;
        String fraseRespuesta;
        
        try {
            
            Socket s = new Socket(HOST, PUERTO);

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            // El cliente recibe el nombre del servidor y le devuelve su nombre
            linea=br.readLine();
            pw.println(this.nombreCliente);
            
            // El cliente responde al servidor
            String solicitud = null;
            int numeroAleatorio = (int) (Math.random() * 2 + 1);
            String mensajeDespedida = "FIN";

            switch (numeroAleatorio) {
                case 1:
                    solicitud = "motivadora";
                    break;
                case 2:
                    solicitud = "desmotivadora";
                    break;
                default:
            }

            pw.println(solicitud);

            // El cliente recibe la frase solicitada al servidor y la imprime en consola
            fraseRespuesta = br.readLine();
            System.out.println(String.format("Hola soy el %s y el %s me ha respondido como frase %s: %s", Thread.currentThread().getName(),linea,solicitud, fraseRespuesta));
            
            // El cliente se despide y cierra los recursos
            pw.println(mensajeDespedida);
            s.close();
            br.close();
            pw.close();
            
         } catch (IOException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }

    }

}
