package Red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {


    public static void main(String [] args) {

        ServerSocket  s; //Socket servidor
        Socket  sc; //Socket cliente

        PrintStream  p; //Canal de escritura
        BufferedReader  b; //Canal de Lectura

        String  mensaje;
        
        System.out.println("arranco el servidor");

        try {
            //Creo el socket server
            s = new ServerSocket (9999);

            //Invoco el metodo accept del socket servidor, me devuelve una referencia al socket cliente
            sc = s.accept();

            //Obtengo una referencia a los canales de escritura y lectura del socket cliente
            b = new BufferedReader ( new InputStreamReader  ( sc.getInputStream() ) );
            p = new PrintStream  ( sc.getOutputStream() );

            while ( true ) {
                //Leo lo que escribio el socket cliente en el canal de lectura
                mensaje = b.readLine();
                System.out.println(mensaje);

                //Escribo en canal de escritura el mismo mensaje recibido
                p.println(mensaje);

                
                if ( mensaje.equals("by")) {
                    break;
                }
            }

            p.close();
            b.close();

            sc.close();
            s.close();
        } catch (IOException  e) {
            System .out.println("No puedo crear el socket");
        }
    }

}