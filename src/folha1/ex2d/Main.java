package folha1.ex2d;

import utils.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int[] newArray = Array.newRandomArray(sc);

        int value = 0;

        boolean correctInput = false;
        while (!correctInput) {
            try {
                System.out.print("Por favor, introduza o valor a eliminar do array:");
                value = sc.nextInt();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

        sc.close();

        int[] prunedArray = Array.deleteFromArray(newArray,value);

        System.out.println("Original: " + Arrays.toString(newArray));
        System.out.println("Final:    " + Arrays.toString(prunedArray));
    }
}
