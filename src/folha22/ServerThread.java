package folha22;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class ServerThread extends Thread {

    private Socket clientSocket;
    private String itemsFilePath;
    private String raffleFilePath;
    private Lock itemsLock;
    private Lock raffleLock;

    private BufferedReader fileItemsBReader;
    private BufferedReader fileRaffleBReader;
    private BufferedWriter fileRaffleBWriter;

    private int itemNumberRaffle;

    public ServerThread(Socket clientSocket, String itemsFilePath, String raffleFilePath,
                        Lock itemsLock, Lock raffleLock) {

        this.clientSocket = clientSocket;
        this.itemsFilePath = itemsFilePath;
        this.raffleFilePath = raffleFilePath;
        this.itemsLock = itemsLock;
        this.raffleLock = raffleLock;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        )
        {
            doRaffle();

            String option = "";

            do {
                option = in.readLine();

                switch (option) {
                    case "1":
                        System.out.println("Thread " + this.getName() + ": A enviar a lista de itens");
                        sendItemsList(out);
                        break;

                    case "2":
                        if (Thread.currentThread().isInterrupted()) {
                            System.out.println("Thread " + this.getName() + ": A enviar os resultados do sorteio");
                            out.println(1);
                            sendResults(out);

                        } else {
                            System.out.println("Thread " + this.getName() + ": Resultados não enviados, pois o sorteio " +
                                    "ainda não acabou");
                            out.println(0);
                        }
                        break;

                    case "3":
                        System.out.println("Thread " + this.getName() + ": O cliente saíu");
                        break;
                }

            } while (!option.equals("3"));


        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de criação dos buffers do socket");
            System.exit(1);
        }
    }

    private void doRaffle(){

        List<String> raffleList = new ArrayList<>();

        String line = "";
        int counter = 0;

        this.raffleLock.lock();

        try {
            this.fileRaffleBReader = new BufferedReader(new FileReader(this.raffleFilePath));

            while (line != null) {

                line = this.fileRaffleBReader.readLine();

                if (line != null) {
                    raffleList.add(line);

                    if (line.equals("0")) {
                        counter++;
                    }
                }
            }

            this.fileRaffleBReader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Thread " + this.getName() + ": Ficheiro de sorteio não encontrado");
            this.raffleLock.unlock();
            System.exit(2);

        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de leitura ou fecho do ficheiro de sorteio");
            this.raffleLock.unlock();
            System.exit(3);
        }

        int number = (int)(Math.random() * counter + 1);

        int zeroCounter = 0;
        for (int i = 0; i < raffleList.size(); i++) {

            if (raffleList.get(i).equals("0")) {
                zeroCounter++;
            }

            if (zeroCounter == number) {
                raffleList.set(i,this.getName());

                itemNumberRaffle = i;

                break;
            }
        }

        try {
            this.fileRaffleBWriter = new BufferedWriter(new FileWriter(this.raffleFilePath));

            for (int i = 0; i < raffleList.size(); i++) {
                this.fileRaffleBWriter.write(raffleList.get(i) + "\n");
            }

            this.fileRaffleBWriter.close();

        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de escrita, abertura ou fecho do ficheiro de sorteio");
            this.raffleLock.unlock();
            System.exit(4);
        }

        this.raffleLock.unlock();
    }

    private void sendItemsList(PrintWriter out) {

        this.itemsLock.lock();

        String line = "";
        try {
            this.fileItemsBReader = new BufferedReader(new FileReader(this.itemsFilePath));

            while (line != null) {

                line = this.fileItemsBReader.readLine();

                if (line != null) {
                    out.println(line);
                }
            }
            out.println("####");

        } catch (FileNotFoundException e) {
            System.err.println("Thread " + this.getName() + ": Erro de leitura do ficheiro de itens");
            this.itemsLock.unlock();
            System.exit(5);

        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de leitura do ficheiro de itens");
            this.itemsLock.unlock();
            System.exit(6);
        }

        this.itemsLock.unlock();
    }

    private void sendResults(PrintWriter out) {

        String itemNameRaffle = null;

        this.itemsLock.lock();

        try {
            this.fileItemsBReader = new BufferedReader(new FileReader(this.itemsFilePath));

            itemNameRaffle = "";

            for (int i = 0; i <= this.itemNumberRaffle; i++) {
                itemNameRaffle = this.fileItemsBReader.readLine();
            }

            this.fileItemsBReader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Thread " + this.getName() + ": Ficheiro de itens não encontrado");
            this.itemsLock.unlock();
            System.exit(7);

        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de leitura ou de fecho do ficheiro de itens");
            this.itemsLock.unlock();
            System.exit(8);
        }
        this.itemsLock.unlock();

        out.println("\t\t\t### Resultados do sorteio ###");
        out.println("");
        out.println("\t Foi-lhe sorteado o item: " + itemNameRaffle);
        out.println("");

        this.itemsLock.lock();
        this.raffleLock.lock();

        try {
            this.fileItemsBReader = new BufferedReader(new FileReader(this.itemsFilePath));
            this.fileRaffleBReader = new BufferedReader(new FileReader(this.raffleFilePath));

            String itemName = "";
            String threadName = "";
            while (itemName != null) {

                itemName = this.fileItemsBReader.readLine();
                threadName = this.fileRaffleBReader.readLine();

                if (itemName != null) {
                    out.println(itemName + " - " + threadName);
                }
            }
            out.println("####");

            this.fileItemsBReader.close();
            this.fileRaffleBReader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Thread " + this.getName() + ": Ficheiro de itens ou de sorteio não encontrado");
            this.itemsLock.unlock();
            this.raffleLock.unlock();
            System.exit(9);

        } catch (IOException e) {
            System.err.println("Thread " + this.getName() + ": Erro de leitura ou fecho do ficheiro de itens " +
                    "ou do ficheiro de sorteio");
            this.itemsLock.unlock();
            this.raffleLock.unlock();
            System.exit(10);
        }
        this.itemsLock.unlock();
        this.raffleLock.unlock();
    }
}
