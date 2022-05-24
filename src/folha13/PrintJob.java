package folha13;

import java.time.LocalTime;

class PrintJob implements Runnable {

    private PrinterQueue printerQueue;

    public PrintJob(PrinterQueue printerQueue) {
        this.printerQueue = printerQueue;
    }

    @Override
    public void run() {

        System.out.println(LocalTime.now() + " -> A thread " + Thread.currentThread().getName() + " vai imprimir na PrinterQueue " + this.printerQueue.id);

        this.printerQueue.printDoc();
    }
}
