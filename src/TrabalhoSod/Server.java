package TrabalhoSod;

import TrabalhoSod.ServerThread;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    private final static int SOCKET_TIMEOUT = 10000; // 40 segundos
    private final static int N_THREADS = 10; // numero de threads
    private static int min; //min for the random number generator
    private static int max; //min for the random number generator
    private static int GuessNumber; //valor que o cliente deve adivinhar

    //private static String AlreadyLogged; // Jogador já se encontra logado no server
    private static String PATH = System.getProperty("user.dir") + "\\src\\TrabalhoSod\\"; // path do arquivo de login
    private static String loginFile = "login.txt"; //arquivo de login

    private final static Lock Lock1 = new ReentrantLock();
    private final static Lock LockLogin = new ReentrantLock(); //lock para o login

    private final static Lock LockTimer = new ReentrantLock(); // Lock para o timer

    public static void main(String[] args) {


        //Pede os valores do minimo e do maximo para o gerador de numeros aleatorios

        Scanner teclado = new Scanner(System.in);
        do {

            System.out.println("Introduza um valor Máximo para o jogo do GuessNumber");
            max = teclado.nextInt();
            System.out.println("Introduza um valor Mínimo para o jogo do GuessNumber");
            min = teclado.nextInt();
            if (min >= max){
                System.out.println("Introduza um valor máximo maior que o mínimo\n");
            }
        } while (min >= max);
        System.out.println("Minimo"+min);
        System.out.println("Maximo"+max);
        GuessNumber = (int)(Math.random()*(max -min +1)+min);
        System.out.println(GuessNumber);
        System.out.println("O número que vai ter de ser adivinhado é: " + GuessNumber);




        //Validação do Porto

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


        //Criação dos Sockets


        try
                (
                        ServerSocket serverSocket = new ServerSocket(portNumber)
                ) {
            Socket clientSocket = new Socket();

            serverSocket.setSoTimeout(SOCKET_TIMEOUT);

            ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);


            while (true) {

                System.out.println("Main: À espera de novos jogadores");

                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Main: Nova ligação");

                } catch (SocketTimeoutException e) {
                    System.out.println("Main: Acabou o tempo de login");
                    executor.shutdownNow();
                    break;

                } catch (IOException e) {
                    System.err.println("Main: Ocorreu um erro de I/O ao tentar criar o socket no porto " + portNumber);
                    System.exit(4);
                }
                executor.execute(new ServerThread(clientSocket, GuessNumber, PATH,loginFile,max,min,Lock1,LockLogin));

            }


        } catch (IOException e) {
            System.err.println("Main: Ocorreu um erro de I/O ao tentar criar o socket no porto " + portNumber);
            System.exit(5);
        }
    }
}
