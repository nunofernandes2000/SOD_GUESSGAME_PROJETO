package folha13;

import java.time.LocalTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterQueue {
    private Lock queueLock = new ReentrantLock();
    public int id;

    public PrinterQueue(int id) {
        this.id = id;
    }

    public void printDoc() {

        queueLock.lock();

        int duration = (int)(Math.random() * 10001);

        System.out.println(LocalTime.now() + " -> A PrintQueue " + this.id + " vai imprimir durante "
            + duration / 1000 + "s para a thread " + Thread.currentThread().getName());

        try {
            Thread.sleep(duration);

            System.out.println(LocalTime.now() + " -> A PrintQueue " + this.id + " terminou de imprimir o documento para a thread "
                    + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            System.out.println("Erro de interrupção");

        } finally {
            queueLock.unlock();
        }
    }
}
