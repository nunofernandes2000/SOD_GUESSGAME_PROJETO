package folha11;

public class Main {

    public static void main(String[] args) {

        try {

            ExtendsThread t1 = new ExtendsThread();
            t1.start();
            Thread.sleep(1000);

            ExtendsThread t2 = new ExtendsThread();
            t2.start();
            Thread.sleep(1000);

            ExtendsThread t3 = new ExtendsThread();
            t3.start();
            Thread.sleep(1000);

            ImplementsRunnable runnable = new ImplementsRunnable();
            Thread t4 = new Thread(runnable);
            t4.start();
            Thread.sleep(1000);

            Thread t5 = new Thread(runnable);
            t5.start();
            Thread.sleep(1000);

            Thread t6 = new Thread(runnable);
            t6.start();
            Thread.sleep(1000);

            /*
            Thread t4 = new Thread(new ImplementsRunnable());
            t4.start();
            Thread.sleep(1000);

            Thread t5 = new Thread(new ImplementsRunnable());
            t5.start();
            Thread.sleep(1000);

            Thread t6 = new Thread(new ImplementsRunnable());
            t6.start();
            Thread.sleep(1000);
            */

        } catch (InterruptedException e) {
            System.out.println("Erro de interrupção");
        }
    }
}
