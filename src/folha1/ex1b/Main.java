package folha1.ex1b;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int min = 0;
        int max = 0;
        int n = 0;

       boolean correctInput = false;
        while (!correctInput) {
            try {
                System.out.print("Por favor, introduza o valor mínimo a introduzir no array:");
                min = sc.nextInt();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

        correctInput = false;
        while (!correctInput) {
            try {
                System.out.print("Por favor, introduza o valor máximo a introduzir no array:");
                max = sc.nextInt();

                if (max <= min) {
                    System.out.println("O número máximo tem de ser superior ao número mínimo.");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

        correctInput = false;
        while (!correctInput) {
            try {
                System.out.print("Por favor, introduza o número de elementos para introduzir no array:");
                n = sc.nextInt();

                if (n <= 0) {
                    System.out.println("O array tem de ter elementos.");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

        sc.close();

        int[] newArray = newRandomArray(min, max, n);

        /*System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(newArray[i]);
            if (i < n - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");*/

        /*System.out.print("[");
        for (int value : newArray) {
            System.out.print(value + ",");
        }
        System.out.println("]");*/

        System.out.println(Arrays.toString(newArray));
    }

    public static int[] newRandomArray(int min, int max, int n) {

        int[] randomArray = new int[n];

        //Random r = new Random();

        for (int i = 0; i < n; i++) {

            // +1 para incluir o max
            // +min para começar no min
            // [0.0;1.0[
            randomArray[i] = (int) ((Math.random() * (max + 1 - min)) + min);

            // [0;x[
            //randomArray[i] = r.nextInt(max + 1 - min) + min;
        }

        return randomArray;
    }
}
