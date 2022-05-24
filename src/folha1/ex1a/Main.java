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
                System.out.print("Por favor, introduza o ano a verificar se � bissexto:");
                year = sc.nextInt();

                if (year > 0) {
                    correctInput = true;

                } else {
                    System.out.println("Por favor introduza um n�mero inteiro positivo (ano).");
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor introduza um n�mero inteiro (ano).");
                sc.nextLine(); // para gastar o enter que o nextInt() n�o apanha, sen�o fica em ciclo infinito
            }
        }

        sc.close();

        if(isLeapYear(year)) {
            System.out.println("O ano " + year + " � bissexto.");
        }else {
            System.out.println("O ano " + year + " n�o � bissexto.");
        }
    }

    /**
     * Um ano � bissexto se:
     * - for divis�vel por 4 e n�o por 100;
     * - for divis�vel por 4 e por 100, desde que tamb�m seja divis�vel por 400. Neste caso, como ao ser divis�vel
     *   por 400 � tamb�m divis�vel por 100 e por 4, basta s� procurar se � divis�vel por 400.
     */
    public static boolean isLeapYear(int year) {
        if((year%4==0 && year%100!=0) || year%400==0) {
            return true;
        } else {
            return false;
        }
    }
}