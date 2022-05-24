package folha2.ex1c;

import utils.Array;
import utils.InputValidation;

import java.util.Scanner;

import static utils.Array.print2DArray;

public class Main {

    public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int[][] new2DArray = Array.newRandom2DArray(sc);

        sc.close();

        print2DArray(new2DArray);

        int[][] transposed2DArray = new int[new2DArray[0].length][new2DArray.length];

        for (int i = 0; i < transposed2DArray.length; i++) {

            for (int j = 0; j < transposed2DArray[0].length; j++) {
                transposed2DArray[i][j] = new2DArray[j][i];
            }
        }

        print2DArray(transposed2DArray);
    }
}
