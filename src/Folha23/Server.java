package Folha23;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    private final static int SOCKET_TIMEOUT = 10000;
    private final static int N_THREADS = 10;


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

        try
                (
                        ServerSocket serverSocket = new ServerSocket(portNumber)
                )
        {
            Socket clientSocket = new Socket();

            serverSocket.setSoTimeout(SOCKET_TIMEOUT);

            ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);



            while (true) {

                System.out.println("Main: À espera de novas ligações");

                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Main: Nova ligação");

                } catch (SocketTimeoutException e) {
                    System.out.println("Main: Acabou o tempo de aceitar novos jogadores");
                    executor.shutdownNow();
                    break;

                } catch (IOException e) {
                    System.err.println("Main: Ocorreu um erro de I/O ao tentar criar o socket no porto " + portNumber);
                    System.exit(4);
                }

                executor.execute(new ServerThread(clientSocket));
            }

        } catch (IOException e) {
            System.err.println("Main: Ocorreu um erro de I/O ao tentar criar o socket no porto " + portNumber);
            System.exit(5);
        }
    }

}
