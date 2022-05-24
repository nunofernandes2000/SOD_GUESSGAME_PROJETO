package folha10;

public class Multiplier extends Thread {

    private double[] array;
    private int pot;
    private int init;
    private int end;

    public Multiplier(double[] array, int pot, int init, int end) {
        this.array = array;
        this.pot = pot;
        this.init = init;
        this.end = end;
    }

    @Override
    public void run() {

        for (int i = init; i < end; i++) {

            array[i] = Math.cos(array[i]);

            for (int j = 0; j < pot; j++) {
                array[i] *= Math.cos(array[i]);
            }
        }
    }
}
