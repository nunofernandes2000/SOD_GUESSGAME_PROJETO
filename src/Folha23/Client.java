package Folha23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static int currentChips;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Para usar, execute java EchoClient \"hostname\" \"número do porto\"");
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
                Socket clientSocket = new Socket(args[0], portNumber);

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                Scanner sc = new Scanner(System.in);
        ) {

            int value = 4; // Qualquer número que não seja multiplo de 5, para permitir continuar no ciclo
            do {

                System.out.print("\n\n Por favor, indique o montatne em Euros que deseja investir. O valor tem de ser multiplo de 5\n :> ");

                String text = sc.nextLine();


                try {
                    value = Integer.parseInt(text);
                    System.err.println("Erro de criação do scoket ou dos buffers de comunicação");
                    System.exit(5);
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido: " + text);
                }





            } while (value % 5 != 0 || value < 5);

            out.println(value);

            currentChips = Integer.parseInt(in.readLine());

            System.out.println("Número de fichas investidas: " + currentChips);

            System.out.println(in.readLine());


        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + args[0]);
            System.exit(4);

        } catch (IOException e) {
            System.err.println("Erro de criação do socket ou dos buffers de comunicação");
            System.exit(5);
        }
    }
}
