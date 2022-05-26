package Folha23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Client {

    private static int currentChips;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Para usar, execute java EchoClient \"hostname\" \"número do porto\"");
            System.exit(1);
        }

        int portNumber = 0;

        try {
            portNumber = parseInt(args[1]);

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

                System.out.print("\n\n Por favor, indique o montante em Euros que deseja investir. O valor tem de ser multiplo de 5\n :> ");

                String text = sc.nextLine();


                try {
                    value = parseInt(text);
                    System.err.println("Erro de criação do scoket ou dos buffers de comunicação");
                    System.exit(5);
                } catch (NumberFormatException e) {
                    System.out.println("Número inválido: " + text);
                }





            } while (value % 5 != 0 || value < 5);

            out.println(value);

            currentChips = parseInt(in.readLine());

            System.out.println("Número de fichas investidas: " + currentChips);

            System.out.println(in.readLine());





            //Pedir o número ao utilizador que pretende apostar

            String text;
            int number;

            while (true) {

                text = "";
                number = 0;
                do {

                    System.out.print("Por favor, introduza o número em que quer apostar. Tem de ser um número entre 1 e 36\n :> ");
                    text = sc.nextLine();

                    try {
                        number = Integer.parseInt(text); //Transformar o texto em um inteiro

                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido: " + text);
                    }

                } while (number < 1 || number > 36);


                out.println(number); //Enviar o número ao servidor


                //Pedir o número de fichas que o utilizador quer apostar


                int chipsToBet = 0;
                do {

                    System.out.print("Por favor, introduza o número de fichas que quer apostar no número " + number + "\n :> ");
                    text = sc.nextLine();

                    try {
                        chipsToBet = Integer.parseInt(text); //Transformar o texto em um inteiro

                    } catch (NumberFormatException e) {
                        System.out.println("Número  de fichas inválido: " + text);
                    }

                } while (chipsToBet < 1 || chipsToBet <= currentChips);


                out.println(chipsToBet); //Enviar o número ao servidor


                int extractedNumber = Integer.parseInt(in.readLine());
                currentChips = Integer.parseInt(in.readLine());


                if (extractedNumber == number) {
                    System.out.println("Parabéns, acertou no número");
                } else {
                    System.out.println("O número extraido foi " + extractedNumber);
                    System.out.println("Ficou com " + currentChips + " fichas");
                }

                if (currentChips == 0) {
                    System.out.println("Não tem mais fichas para apostar. Obrigado");
                    break;
                }


                //Ciclo para ver o jogador quer continuar ou não a jogar

                do {

                    System.out.println("Deseja fazer mais uma aposta? (S/N)");

                    text = sc.nextLine().toLowerCase();


                } while (!text.equals("s") && !text.equals("n"));

                if (text.equals("n")) {
                    System.out.println("Obrigado por jogar. Até breve.");
                    out.println("Sair");
                    break;
                } else {
                    out.println("Continuar");
                }

            }




        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + args[0]);
            System.exit(4);

        } catch (IOException e) {
            System.err.println("Erro de criação do socket ou dos buffers de comunicação");
            System.exit(5);
        }
    }
}
