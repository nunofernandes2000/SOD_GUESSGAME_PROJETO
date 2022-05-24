package folha1.ex2a;

import utils.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] newArray = Array.newRandomArray(sc);

        sc.close();

        System.out.println(Arrays.toString(newArray));

        int sum = 0;
        for (int value : newArray) {
            sum += value;
        }
        System.out.println("A soma de todos os elementos do array é " + sum);
    }
}
