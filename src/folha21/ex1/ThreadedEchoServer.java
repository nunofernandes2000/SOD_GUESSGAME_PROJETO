package folha21.ex1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Para usar, execute java EchoServer \"número do porto\"");
            System.exit(1);
        }

        int portNumber = 0;

        try {
            portNumber = Integer.parseInt(args[0]);

        } catch (NumberFormatException e) {
            System.err.println("Main: Porto inválido: " + args[0]);
            System.exit(2);
        }

        if (portNumber < 1024 || portNumber > 65535) {
            System.err.println("Main: Porto inválido: " + args[0]);
            System.exit(3);
        }

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
            Socket clientSocket;

            while (true) {
                System.out.println("Main: À espera de ligação");
                clientSocket = serverSocket.accept();

                System.out.println("Main: Nova ligação estabelecida");

                new EchoThread(clientSocket).start();
            }

        } catch (IOException e) {
            System.err.println("Main: Erro ao tentar criar o Socket");
        }
    }
}
