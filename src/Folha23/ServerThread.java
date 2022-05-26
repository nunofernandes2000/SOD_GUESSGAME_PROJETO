package Folha23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Lock;

import static Folha23.Server.EXTRACTED_NUMBER;
import static java.lang.Integer.parseInt;

public class ServerThread extends Thread {

    private final Socket clientSocket;
    private final Lock extractionLock;

    private final Phaser phaser;

    public ServerThread(Socket clientSocket, Lock extractionLock, Phaser phaser) {
        this.clientSocket = clientSocket;
        this.extractionLock = extractionLock;
        this.phaser = phaser;
        this.phaser.register();
    }

    @Override
    public void run() {
        {
            try (
                    PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            )
            {

                int value = Integer.parseInt(in.readLine());

                int currentChips = value / 5;

                System.out.println("Thread " +this.getName() + ": O cliente investiu " + value + " euros ( " + currentChips + " fichas)");

                out.println(currentChips);




                while (!Thread.currentThread().isInterrupted()) {

                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        out.println("Terminou o periodo para aceitar novos jogadores: Faça a sua aposta.");

                    }
                }


                /* if (Thread.currentThread().isInterrupted()) {
                    out.println("Terminou o periodo para aceitar novos jogadores: Faça a sua aposta.");
                }*/

                System.out.println("Thread " +this.getName() + ": vai ser informado que o jogo vai começar.");



                while(true) {


                    int number = Integer.parseInt(in.readLine()); //recebe o numero que o cliente apostou
                    System.out.println("Thread " + this.getName() + ": O cliente quer apostar no número" + number);


                    int chipsToBet = Integer.parseInt(in.readLine()); //recebe o numero de fichas que o cliente quer apostar
                    System.out.println("Thread " + this.getName() + ": O cliente quer apostar " + chipsToBet + " no número" + number);


                    currentChips -= chipsToBet; //currentChips = currentChips - chipsToBet;

                    extractionLock.lock(); //bloqueia a variavel EXTRACTED_NUMBER


                    Server.EXTRACTED_NUMBER = (int) (Math.random() * 36 + 1); //gera um numero aleatorio entre 1 e 36


                    extractionLock.unlock(); //desbloqueia a variavel EXTRACTED_NUMBER


                    phaser.arriveAndAwaitAdvance(); //espera que todas as threads cheguem aqui e depois avançam

                    System.out.println("Thread " + this.getName() + ": O número extraido foi " + Server.EXTRACTED_NUMBER);

                    if (Server.EXTRACTED_NUMBER == number) {
                        currentChips += (chipsToBet * 35);
                    }

                    out.println(Server.EXTRACTED_NUMBER); //envia o numero que foi sorteado
                    out.println(currentChips); //envia o numero de fichas que o cliente têm

                    String text = in.readLine();

                    if (text.equals("Sair")) {
                        System.out.println("Thread " + this.getName() + ": O cliente saiu do jogo");
                        break;
                    } else {
                        System.out.println("Thread " + this.getName() + ": O cliente vai continuar a jogar");
                    }

                }


            } catch (IOException e) {
                System.err.println("Thread " + this.getName() + ": Erro de criação dos buffers do socket");
                System.exit(1);
            }
        }
    }
}
