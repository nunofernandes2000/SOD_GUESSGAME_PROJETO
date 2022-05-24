package folha16;

import utils.InputValidation;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        Semaphore livre;
        Semaphore ocups = new Semaphore(0);
        Lock mutex = new ReentrantLock();

        Scanner sc = new Scanner(System.in);

        Buffer.bufferSize = InputValidation.validateIntGT0(sc,
                "Por favor, introduza o tamanho do buffer a criar: ");

        Buffer.buffer = new int[Buffer.bufferSize];
        Buffer.isOccupied = new boolean[Buffer.bufferSize];

        livre = new Semaphore(Buffer.bufferSize);

        boolean correctInput = false;
        while(correctInput == false) {
            try {
                System.out.println("Por favor, introduza o valor mínimo a inserir no buffer");
                Buffer.min = sc.nextInt();
                sc.nextLine();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        }

        correctInput = false;
        while(correctInput == false) {
            try {
                System.out.println("Por favor, introduza o valor máximo a inserir no buffer");
                Buffer.max = sc.nextInt();
                sc.nextLine();

                if (Buffer.max <= Buffer.min) {
                    System.out.println("O valor máximo tem de ser superior ao mínimo (" + Buffer.min + ")");

                } else {
                    correctInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        }

        int nProducerThreads = InputValidation.validateIntGT0(sc, "Por favor, introduza o número de threads " +
                "produtoras a criar: ");
        int nConsumerThreads = InputValidation.validateIntGT0(sc, "Por favor, introduza o número de threads " +
                "consumidoras a criar: ");

        ExecutorService executor = Executors.newFixedThreadPool(nProducerThreads + nConsumerThreads);

        for (int i = 0; i < nProducerThreads; i++) {
            Producer producer = new Producer(livre, ocups, mutex);
            executor.execute(producer);
        }

        for (int i = 0; i < nConsumerThreads; i++) {
            Consumer consumer = new Consumer(livre, ocups, mutex);
            executor.execute(consumer);
        }

        try {
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            System.out.println("Erro de interrupção");
        }

        executor.shutdownNow();
    }
}
