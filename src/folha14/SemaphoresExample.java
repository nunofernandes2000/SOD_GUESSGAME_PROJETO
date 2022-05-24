package folha14;

import java.util.concurrent.Semaphore;

public class SemaphoresExample {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(1);

        int nThreads = 10;
        Thread[] threads = new Thread[nThreads];

        ThreadSemaphores ts = new ThreadSemaphores(sem);
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(ts, "t" + i);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < nThreads; i++) {
            try {
                threads[i].join();

            } catch (InterruptedException e) {
                System.out.println("Erro de interrupção no join");
            }
        }

        System.out.println("Counter : " + Shared.counter);
    }
}
