package folha22;

import java.io.*;
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
    private final static String PATH = System.getProperty("user.dir") + "\\src\\folha22\\";
    private final static String RAFFLE_FILE = "raffle.txt";
    private final static String ITEMS_FILE = "items.txt";
    private final static Lock itemsLock = new ReentrantLock();
    private final static Lock raffleLock = new ReentrantLock();

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

            createRaffleFile();

            while (true) {

                System.out.println("Main: À espera de novas ligações");

                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Main: Nova ligação");

                } catch (SocketTimeoutException e) {
                    System.out.println("Main: Acabou o tempo de sorteio");
                    executor.shutdownNow();
                    break;

                } catch (IOException e) {
                    System.err.println("Main: Ocorreu um erro de I/O ao tentar criar o socket no porto " + portNumber);
                    System.exit(4);
                }

                executor.execute(new ServerThread(clientSocket, PATH + ITEMS_FILE,
                        PATH + RAFFLE_FILE, itemsLock, raffleLock));
            }

        } catch (IOException e) {
            System.err.println("Main: Ocorreu um erro de I/O ao tentar criar o socket no porto " + portNumber);
            System.exit(5);
        }
    }

    private static void createRaffleFile() {
        BufferedReader fileItemsReader = null;
        BufferedWriter fileRaffleWriter = null;

        try {
            fileRaffleWriter = new BufferedWriter(new FileWriter(PATH + RAFFLE_FILE));

        } catch (IOException e) {
            System.err.println("Main: Erro de criação do buffer de escrita para o ficheiro " + RAFFLE_FILE);
            System.exit(6);
        }

        try {
            fileItemsReader = new BufferedReader(new FileReader(PATH + ITEMS_FILE));

        } catch (FileNotFoundException e) {
            System.err.println("Main: Erro de criação do buffer de leitura para o ficheiro " + ITEMS_FILE);

            try {
                fileRaffleWriter.close();

            } catch (IOException e2) {
                System.err.println("Main: Erro de fecho dos buffers do ficheiro " + RAFFLE_FILE);
            }
            System.exit(7);
        }

        String line = "";

        try {
            while (line != null) {
                line = fileItemsReader.readLine();

                if (line != null) {
                    fileRaffleWriter.write("0\n");
                }
            }

        } catch (IOException e) {
            System.err.println("Main: Erro de leitura dos ficheiros " + ITEMS_FILE + " ou " + RAFFLE_FILE);
            System.exit(8);

        } finally {
            try {
                fileItemsReader.close();
                fileRaffleWriter.close();

            } catch (IOException e) {
                System.err.println("Main: Erro de fecho dos buffers dos ficheiros " + ITEMS_FILE +
                        " e " + RAFFLE_FILE);
                System.exit(9);
            }
        }
    }
}
