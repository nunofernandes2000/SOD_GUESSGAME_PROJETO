package folha16;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

    private Semaphore livres;
    private Semaphore ocups;
    private Lock mutex;

    public Consumer(Semaphore livres, Semaphore ocups, Lock mutex) {
        this.livres = livres;
        this.ocups = ocups;
        this.mutex = mutex;
    }

    private int retirarItem() {

        for (int i = 0; i < Buffer.bufferSize; i++) {

            if (Buffer.isOccupied[i] == true) {

                int item = Buffer.buffer[i];
                Buffer.isOccupied[i] = false;

                System.out.println("A thread " + Thread.currentThread().getName() + " retirou o item " +
                        item + " da posição " + i + " do buffer");

                return item;
            }
        }
        return Buffer.min - 1;
    }

    public void consumirItem(int item) {
        System.out.println("A thread " + Thread.currentThread().getName() + " consumiu o item " + item);
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Consumidora_" + Thread.currentThread().getId());

        while(!Thread.currentThread().isInterrupted()) {

            try {
                this.ocups.acquire();

            } catch (InterruptedException e) {
                System.out.println("A thread " + Thread.currentThread().getName() + " recebeu o sinal de interrupção");
                break;
            }

            this.mutex.lock();

            int item = retirarItem();

            this.mutex.unlock();
            this.livres.release();

            consumirItem(item);

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println("A thread " + Thread.currentThread().getName() + " recebeu o sinal de interrupção");
                break;
            }
        }
        System.out.println("A thread " + Thread.currentThread().getName() + " terminou");
    }
}
