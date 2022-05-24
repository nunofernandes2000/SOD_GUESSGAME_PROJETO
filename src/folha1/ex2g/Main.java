package folha1.ex2g;

import utils.Array;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int[] newArray = Array.newRandomArray(sc);

        sc.close();

        System.out.println(Arrays.toString(newArray));

        Arrays.sort(newArray);
        System.out.println(Arrays.toString(newArray));

        int minValue = newArray[0];
        for (int i = 1; i < newArray.length; i++) {
            if (newArray[i] > minValue) {
                System.out.println("Segundo menor valor: " + newArray[i]);
                return;
            }
        }
    }
}
