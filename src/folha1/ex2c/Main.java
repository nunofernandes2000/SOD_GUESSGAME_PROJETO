package folha1.ex2c;

import utils.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int[] newArray = Array.newRandomArray(sc);

        int min = 0;

        boolean correctInput = false;
        while (!correctInput) {
            try {
                System.out.print("Por favor, introduza o valor mínimo do array a listar:");
                min = sc.nextInt();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

        sc.close();

        System.out.print("Original: " + Arrays.toString(newArray));

        System.out.print("\nFinal   : [");
        boolean print = false;
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] >= min) {

                if (print) {
                    System.out.print(", ");
                }
                print = true;
                System.out.print(newArray[i]);
            }
        }
        System.out.print("]\n");
    }
}
