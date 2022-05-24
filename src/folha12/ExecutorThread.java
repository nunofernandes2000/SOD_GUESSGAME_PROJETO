package folha12;

public class ExecutorThread extends Thread{
    private double[] array;
    private int mult;
    private int init;
    private int end;


    public ExecutorThread(double[] array, int mult, int init, int end) {
        this.array = array;
        this.mult = mult;
        this.init = init;
        this.end = end;
    }

    @Override
    public void run() {

        for (int i = this.init; i < this.end; i++) {

            this.array[i] = Math.cos(this.array[i]);

            for (int j = 0; j < this.mult; j++) {

                this.array[i] *= Math.cos(this.array[i]);
            }
        }
    }
}
