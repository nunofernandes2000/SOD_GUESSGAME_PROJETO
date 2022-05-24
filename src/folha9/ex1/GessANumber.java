package folha9.ex1;

import utils.InputValidation;

import java.util.Scanner;

public class GessANumber {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int number;

        number = InputValidation.validateIntBetween(sc, "Por favor, introduza um número inteiro entre 0 e 100", 0, 100);

        sc.close();

        Threads t1 = new Threads("t1", number);
        Threads t2 = new Threads("t2", number);
        Threads t3 = new Threads("t3", number);
        Threads t4 = new Threads("t4", number);
        Threads t5 = new Threads("t5", number);
        Threads t6 = new Threads("t6", number);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
