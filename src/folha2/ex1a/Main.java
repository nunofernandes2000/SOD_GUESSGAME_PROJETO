package folha2.ex1a;

import utils.Array;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static utils.Array.print2DArray;

public class Main {

    public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int[][] new2DArray = Array.newRandom2DArray(sc);

        print2DArray(new2DArray);

        int mult = 0;

        boolean correctInput = false;
        while (!correctInput) {
            try {
                System.out.print("\nPor favor, introduza o valor pelo qual vamos multiplicar os valores do array bidimensional:");
                mult = sc.nextInt();
                sc.nextLine();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

        sc.close();

        for (int l = 0; l < new2DArray.length; l++) {

            for (int c = 0; c < new2DArray[0].length; c++) {
                new2DArray[l][c] *= mult;
            }
        }

        print2DArray(new2DArray);
        //System.out.println(Arrays.deepToString(new2DArray));
    }
}
