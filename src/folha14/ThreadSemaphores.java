package folha14;

import java.util.concurrent.Semaphore;

public class ThreadSemaphores implements Runnable {

    private Semaphore sem;

    public ThreadSemaphores(Semaphore semaphore) {
        this.sem = semaphore;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <  Shared.max; i++) {
                this.sem.acquire();

                if (Shared.counter < Shared.max) {
                    Thread.sleep(100);

                    Shared.counter++;

                    System.out.println("A thread " + Thread.currentThread().getName() + " incrementou o contador para " + Shared.counter);
                    this.sem.release();

                } else {
                    this.sem.release();
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro de interrupção");
        }
    }
}
