package folha1.ex2f;

import utils.Array;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int[] newArray = Array.newRandomArray(sc);

        sc.close();

        System.out.println(Arrays.toString(newArray));

       /**int max = newArray[0];
        for (int i = 1; i < newArray.length; i++) {
            if (newArray[i] > max) {
                max = newArray[i];
            }
        }

        int max2 = newArray[0];
        for (int i = 1; i < newArray.length; i++) {
            if (newArray[i] < max && newArray[i] > max2) {
                max2 = newArray[i];
            }
        }**/

        Arrays.sort(newArray);
        System.out.println(Arrays.toString(newArray));

        int maxValue = newArray[newArray.length - 1];
        for (int i = newArray.length - 1; i >= 0; i--) {
            if (newArray[i] < maxValue) {
                System.out.println("Segundo maior valor: " + newArray[i]);
                return;
            }
        }
    }
}
