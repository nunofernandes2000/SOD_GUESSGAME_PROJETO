package TrabalhoSod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {

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



            //Pedir dados ao cliente necessários para fazer o login

            System.out.println("Olá, bem-vindo!");
            do {

                boolean autenticado = false;
                String username = "";
                String password = "";
                System.out.println("Introduza o seu username: ");
                username = sc.nextLine();
                System.out.println("Introduza a sua password: ");
                password = sc.nextLine();
                out.println(username);
                out.println(password);
                String resposta = in.readLine();


                if (resposta.equals("Login efetuado com sucesso")) {
                    System.out.println("Login efetuado com sucesso");
                    autenticado = true;
                    break;
                }

                if (resposta.equals("Utilizador já existe")) {
                    System.out.println("Utilizador já existe");
                    autenticado = false;
                    break;
                }

                if (resposta.equals("Tentativas excedidas")) {
                    System.out.println("Tentativas excedidas");
                    autenticado = false;
                    break;
                }

            } while (true);

            String respotaInicial = in.readLine();
            String respotaInicial2 = in.readLine();
            System.out.println(respotaInicial);
            System.out.println(respotaInicial2);


            //TODO: corrigir bug do jogo do lado do cliente
            //Jogo lado do cliente
            boolean autenticado = false;
            while (autenticado == true) {
                String repostaCliente = "";
                String jogo = in.readLine();
                String jogo2 = in.readLine();
                String jogo3 = in.readLine();
                repostaCliente = sc.nextLine();
                out.println(repostaCliente);



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
