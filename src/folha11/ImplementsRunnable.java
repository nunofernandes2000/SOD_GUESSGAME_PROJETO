package folha11;

public class ImplementsRunnable implements Runnable {
    private int counter = 0;

    @Override
    public void run() {
        this.counter++;
        System.out.println("ImplementsRunnable: " + this.counter);
    }
}
