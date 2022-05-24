package folha1.ex1a;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int year = 0;

        boolean correctInput = false;
        while (!correctInput) {
            try {
                System.out.print("Por favor, introduza o ano a verificar se é bissexto:");
                year = sc.nextInt();

                if (year > 0) {
                    correctInput = true;

                } else {
                    System.out.println("Por favor introduza um número inteiro positivo (ano).");
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um número inteiro (ano).");
                sc.nextLine(); // para gastar o enter que o nextInt() não apanha, senão fica em ciclo infinito
            }
        }

        sc.close();

        if(isLeapYear(year)) {
            System.out.println("O ano " + year + " é bissexto.");
        }else {
            System.out.println("O ano " + year + " não é bissexto.");
        }
    }

    /**
     * Um ano é bissexto se:
     * - for divisível por 4 e não por 100;
     * - for divisível por 4 e por 100, desde que também seja divisível por 400. Neste caso, como ao ser divisível
     *   por 400 é também divisível por 100 e por 4, basta só procurar se é divisível por 400.
     */
    public static boolean isLeapYear(int year) {
        if((year%4==0 && year%100!=0) || year%400==0) {
            return true;
        } else {
            return false;
        }
    }
}