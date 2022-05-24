package folha16;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable{

    private Semaphore livres;
    private Semaphore ocups;
    private Lock mutex;

    public Producer(Semaphore livres, Semaphore ocups, Lock mutex) {
        this.livres = livres;
        this.ocups = ocups;
        this.mutex = mutex;
    }

    private int produzirItem() {

        int item = (int)(Math.random() * (Buffer.max - Buffer.min + 1) + Buffer.min);

        System.out.println("A thread " + Thread.currentThread().getName() + " produziu o item " + item);

        return item;
    }

    private void depositarItem(int item) {

        for (int i = 0; i < Buffer.bufferSize; i++) {

            if (Buffer.isOccupied[i] == false) {

                Buffer.buffer[i] = item;
                Buffer.isOccupied[i] = true;

                System.out.println("A thread " + Thread.currentThread().getName() + " depositou o item " +
                item + " na posição " + i + " do buffer");

                return;
            }
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Produtora_" + Thread.currentThread().getId());

        while(!Thread.currentThread().isInterrupted()) {

            int item = produzirItem();

            try {
                this.livres.acquire();

            } catch (InterruptedException e) {
                System.out.println("A thread " + Thread.currentThread().getName() + " recebeu o sinal de interrupção");
                break;
            }

            this.mutex.lock();

            depositarItem(item);

            this.mutex.unlock();

            this.ocups.release();

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
