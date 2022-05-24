package Folha23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class ServerThread extends Thread {

    private final Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
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
                    }
                };



                out.println("Terminou o periodo para aceitar novos jogadores: Faça a sua aposta.");
                System.out.println("Thread " +this.getName() + ": vai ser informado que o jogo vai começar.");







                if (Thread.currentThread().isInterrupted()) {
                    out.println("Terminou o periodo para aceitar novos jogadores: Faça a sua aposta.");
                }



            } catch (IOException e) {
                System.err.println("Thread " + this.getName() + ": Erro de criação dos buffers do socket");
                System.exit(1);
            }
        }
    }
}
