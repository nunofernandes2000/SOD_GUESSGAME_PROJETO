package folha12;

import utils.Array;
import utils.InputValidation;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] randomArrayOriginal = Array.newDoubleRandomArray(sc);
        double[] randomArrayCopy = Arrays.copyOf(randomArrayOriginal, randomArrayOriginal.length);

        int mult = InputValidation.validateIntGT0(sc, "Por favor, introduza a pot�ncia a aplicar a cada elemento do array: ");

        int nThreads = InputValidation.validateIntGT0(sc, "Por favor, introduza o n�mero de threads a utilizar: ");

        int nSubArray = InputValidation.validateIntGT0(sc, "Por favor, introduza o n�mero de partes em que o array deve ser dividido para poss�vel c�lculo paralelo: ");

        sc.close();

        ExecutorThread thread = new ExecutorThread(randomArrayOriginal, mult, 0, randomArrayOriginal.length);

        long startTime = System.nanoTime();

        thread.start();

        try {
            thread.join();

        } catch (InterruptedException e) {
            System.out.println("Erro de interrup��o");
        }

        long estimatedTime = System.nanoTime() - startTime;

        double sum = 0;
        for (int i = 0; i < randomArrayOriginal.length; i++) {
            sum += randomArrayOriginal[i];
        }

        System.out.println("\nResultado final: " + sum);
        System.out.println("Tempo de execu��o com 1 thread: " + (double)(estimatedTime / 1E9) + "s");

        // n�mero de threads a executar
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        int subElem = randomArrayCopy.length / nSubArray;

        startTime = System.nanoTime();

        // n�mero de tarefas a serem executadas pelas threads
        for (int i = 0; i < nSubArray; i++) {
            ExecutorThread worker = new ExecutorThread(randomArrayCopy, mult, i * subElem, (i + 1) * subElem);
            executor.execute(worker);
        }

        // n�o bloqueia � espera que as threads termina, mas faz com que deixem de aceitar novas tarefas
        executor.shutdown();

        try {
            // espera 100s at� que cada thread termine, se a mesma n�o terminar ou for interrompida antes
            executor.awaitTermination(100, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        estimatedTime = System.nanoTime() - startTime;

        sum = 0;
        for (int i = 0; i < randomArrayCopy.length; i++) {
            sum += randomArrayCopy[i];
        }

        System.out.println("Resultado final: " + sum);

        System.out.printf("Tempo de execu��o: " + (double)(estimatedTime / 1E9) + "s");
    }
}
