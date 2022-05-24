package folha21.ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Para usar, execute java EchoClient \"IP do servidor\" \"número do porto\"");
            System.exit(1);
        }

        int portNumber = 0;

        try {
            portNumber = Integer.parseInt(args[1]);

        } catch (NumberFormatException e) {
            System.err.println("Porto inválido: " + args[1]);
            System.exit(2);
        }

        if (portNumber < 1024 || portNumber > 65535) {
            System.err.println("Porto inválido: " + args[1]);
            System.exit(3);
        }

        try (
                Socket clientSocket = new Socket(InetAddress.getByName(args[0]), portNumber);

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                Scanner sc = new Scanner(System.in);
        ) {

            String toServer;

            do {
                System.out.println("Por favor, introduza uma mensagem a enviar ao servidor");
                toServer = sc.nextLine();

                out.println(toServer);

                System.out.println("O servidor disse que a mensagem tinha " + in.readLine() + " caracteres");

            } while (!toServer.equals("Adeus"));

        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido");
            System.exit(4);

        } catch (IOException e) {
            System.err.println("Ocorreu um erro de I/O ao criar o socket");
            System.exit(5);

        } catch (IllegalArgumentException e) {
            System.err.println("Número de porto inválido");
            System.exit(6);
        }
    }
}
