package folha1.ex1f;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int number = 0;

        boolean correctInput = false;
        do {
            try {
                System.out.println("Por favor, introduza um número inteiro para calcular a soma dos algarismos:");
                number = sc.nextInt();

                correctInput = true;

            } catch (InputMismatchException e) {

                System.out.println("Por favor, introduza um número inteiro.");
                sc.nextLine();
            }
        } while (correctInput == false);

        System.out.println("A soma dos números é " + intSum(number));

        sc.close();
    }

    public static int intSum(int number) {

        String textNumber = Integer.toString(number);
        String letter = "";
        int sum = 0;

        for (int i = 0; i < textNumber.length(); i++) {
            letter += textNumber.charAt(i);
            sum += Integer.parseInt(letter);
            letter = "";
        }
        return sum;
    }
}
