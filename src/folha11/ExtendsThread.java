package folha11;

public class ExtendsThread extends Thread {
    private int counter = 0;

    @Override
    public void run() {
        this.counter++;
        System.out.println("ExtendsThread: " + this.counter);
    }
}
