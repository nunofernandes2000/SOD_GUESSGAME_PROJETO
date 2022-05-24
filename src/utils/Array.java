package utils;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Array {

    public static int[] newRandomArray(Scanner sc) {

        int min = 0, max = 0, n = 0;

        boolean correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o valor mínimo para o array:");
                min = sc.nextInt();

                correctInput = true;

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o valor máximo para o array:");
                max = sc.nextInt();

                if (max <= min) {
                    System.out.println("O valor máximo tem de ser maior que o valor mínimo");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o número de elementos a introduzir no array:");
                n = sc.nextInt();

                if (n <= 0) {
                    System.out.println("O número de elementos a introduzir no array tem de ser maior que 0");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        int[] randomArray = new int[n];

        for (int i = 0; i < n; i++) {
            randomArray[i] = (int)((Math.random() * (max + 1 - min)) + min);
        }

        return randomArray;
    }

    public static double[] newDoubleRandomArray(Scanner sc) {

        double min = 0, max = 0;
        int n = 0;

        boolean correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o valor mínimo para o array:");
                min = sc.nextDouble();

                correctInput = true;

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número double.");
                sc.nextLine();
            }
        } while (correctInput == false);

        correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o valor máximo para o array:");
                max = sc.nextDouble();

                if (max <= min) {
                    System.out.println("O valor máximo tem de ser maior que o valor mínimo");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número double.");
                sc.nextLine();
            }
        } while (correctInput == false);

        correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o número de elementos a introduzir no array:");
                n = sc.nextInt();

                if (n <= 0) {
                    System.out.println("O número de elementos a introduzir no array tem de ser maior que 0");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        double[] randomArray = new double[n];

        for (int i = 0; i < n; i++) {
            randomArray[i] = (Math.random() * (max + 1 - min)) + min;
        }

        return randomArray;
    }

    /**
     *
     * @param oldArray array de onde remover o valor valueToRemove
     * @param valueToRemove valor a retirar do oldArray
     * @return
     */
    public static int[] deleteFromArray(int[] oldArray, int valueToRemove) {

        int count = 0;
        for (int element: oldArray) {
            if (element == valueToRemove) {
                count++;
            }
        }

        int[] newArray = new int[oldArray.length - count];

        int j = 0;
        for (int i = 0; i < oldArray.length; i++) {

            if (oldArray[i] != valueToRemove) {
                newArray[j++] = oldArray[i];
            }
        }
        return newArray;
    }

    public static int[] invertArray(int[] oldArray) {

        int[] invertedArray = new int[oldArray.length];

        /*int j = 0;
        for (int i = oldArray.length; i >= 0 ; i--) {
            invertedArray[j++] = oldArray[i];
        }*/

        for (int i = 0; i < oldArray.length ; i++) {
            invertedArray[oldArray.length - i - 1] = oldArray[i];
        }

        return invertedArray;
    }

    public static void invertArrayVoid(int[] oldArray) {

        int temp;
        for (int i = 0; i < oldArray.length / 2 ; i++) {
            temp = oldArray[oldArray.length - i - 1];
            oldArray[oldArray.length - i - 1] = oldArray[i];
            oldArray[i] = temp;
        }
    }

    public static int[][] newRandom2DArray(Scanner sc) {

        int min = 0, max = 0, l = 0, c = 0;

        boolean correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o valor mínimo para o array:");
                min = sc.nextInt();

                correctInput = true;

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o valor máximo para o array:");
                max = sc.nextInt();

                if (max <= min) {
                    System.out.println("O valor máximo tem de ser maior que o valor mínimo");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o número de linhas a introduzir no array:");
                l = sc.nextInt();

                if (l <= 0) {
                    System.out.println("O número de linhas a introduzir no array tem de ser maior que 0");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza o número de colunas a introduzir no array:");
                c = sc.nextInt();

                if (c <= 0) {
                    System.out.println("O número de colunas a introduzir no array tem de ser maior que 0");

                } else {
                    correctInput = true;
                }

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        int[][] randomArray = new int[l][c];

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {

                randomArray[i][j] = (int)((Math.random() * (max + 1 - min)) + min);
            }
        }

        return randomArray;
    }

    public static void print2DArray(int[][] array2D) {

        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[0].length; j++) {

                System.out.print(array2D[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }
}





