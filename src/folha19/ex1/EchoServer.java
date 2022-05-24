package folha19.ex1;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Para usar, execute java EchoServer \"número do porto\"");
            System.exit(1);
        }

        int portNumber = 0;

        try {
            portNumber = Integer.parseInt(args[0]);

        } catch (NumberFormatException e) {
            System.err.println("Porto inválido: " + args[0]);
            System.exit(2);
        }

        if (portNumber < 1024 || portNumber > 65535) {
            System.err.println("Porto inválido: " + args[0]);
            System.exit(3);
        }

        System.out.println("À espera de ligação");

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {

            String fromClient;

            do {
                fromClient = in.readLine();
                System.out.println("O cliente enviou " + fromClient);

                out.println(fromClient.length());

            } while (!fromClient.equals("Adeus"));

        } catch (IOException e) {
            System.err.println("Ocorreu um erro de I/O ao criar o socket");
            System.exit(4);

        } catch (IllegalArgumentException e) {
            System.err.println("Número de porto inválido");
            System.exit(5);
        }
    }
}
