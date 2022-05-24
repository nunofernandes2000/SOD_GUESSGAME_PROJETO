package folha9.ex1;

public class Threads extends Thread {

    int number;

    public Threads (String name, int number) {
        this.number = number;
        this.setName(name);
    }

    @Override
    public void run() {

        int choice = 101;

        while(choice != number) {
            choice = (int)(Math.random() * 101);

            System.out.println(" A thread " + this.getName() + " tentou o número: " + choice);
        }

        System.out.println(" A thread " + this.getName() + " acertou no número: " + number);
    }
}
