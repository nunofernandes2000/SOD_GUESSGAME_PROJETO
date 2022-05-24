package folha2.ex1b;

import utils.Array;
import utils.InputValidation;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

import static utils.Array.print2DArray;
import static utils.InputValidation.validateIntGE0;

public class Main {

    public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int[][] new2DArray = Array.newRandom2DArray(sc);

        print2DArray(new2DArray);

        int firstRow = 0;
        int lastRow = 0;
        int firstColumn = 0;
        int lastColumn = 0;

        while (true) {

            firstRow = validateIntGE0(sc, "Por favor, introduza a linha inicial a copiar (0...)");
            lastRow = validateIntGE0(sc, "Por favor, introduza a linha final a copiar (0...)");
            firstColumn = validateIntGE0(sc, "Por favor, introduza a coluna inicial a copiar (0...)");
            lastColumn = validateIntGE0(sc, "Por favor, introduza a coluna final a copiar (0...)");

            if (lastRow >= firstRow && lastColumn >= firstColumn && firstRow < new2DArray.length && lastRow < new2DArray.length
                    && firstColumn < new2DArray[0].length && lastColumn < new2DArray[0].length) {
                break;

            } else {
                System.out.println("As coordenadas introduzidas são inválidas");
            }
        }

        sc.close();

        int[][] new2DSubArray = new int[lastRow - firstRow + 1][lastColumn - firstColumn + 1];

        for (int i = firstRow; i <= lastRow; i++) {

            for (int j = firstColumn; j <= lastColumn; j++) {

                new2DSubArray[i - firstRow][j - firstColumn] = new2DArray[i][j];
            }
        }
        print2DArray(new2DArray);
        print2DArray(new2DSubArray);
    }
}
