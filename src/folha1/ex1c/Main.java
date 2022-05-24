package folha1.ex1c;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Por favor, introduza os três valores, sobre os quais quer saber o mínimo.");

        int a = 0;
        int b = 0;
        int c = 0;

        boolean correctInput = false;
        while (!correctInput) {
            try {
                a = sc.nextInt();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

       correctInput = false;
        while (!correctInput) {
            try {
                b = sc.nextInt();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

        correctInput = false;
        while (!correctInput) {
            try {
                c = sc.nextInt();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro.");
                sc.nextLine(); // para gastar o enter senão fica em ciclo infinito
            }
        }

        sc.close();

        System.out.println("O valor mínimo é " + minimumValue(a, b, c));
    }

    public static int minimumValue(int a, int b, int c) {

        int minimum = a;

        if (b < minimum) {
            minimum = b;
        }
        if (c < minimum) {
            minimum = c;
        }
        return minimum;

        // várias formas de resolver o problema:

        /*int minimum = 0;

        if (a < b && a < c) {
            return a;
        }
        if (b < a && b < c) {
            return b;
        }
        return c;*/

        /*int[] newArray = new int[3];
        newArray[0] = a;
        newArray[1] = b;
        newArray[2] = c;
        // ou
        //int[] newArray = {a,b,c};*/
        /*minimum = newArray[0];
        for (int i = 1; i < 3; i++) {
            if (newArray[i] < minimum) {
                minimum = newArray[i];
            }
        }
        return minimum;*/

        //return Math.min(Math.min(a, b),c);

        /*
        // como ordenar um array e reordená-lo em sentido oposto para outro array
        // não está relacionado com este exercício
        int n = 100;
        Arrays.sort(newArray);
        int[] array2 = new int[100];
        for (int i = 0; i < n; i++) {
            array2[i] = newArray[100 - 1 - i];
        }*/
    }
}
