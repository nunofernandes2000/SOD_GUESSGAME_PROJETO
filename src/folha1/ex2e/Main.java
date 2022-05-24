package folha1.ex2e;

import utils.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int[] newArray = Array.newRandomArray(sc);

        sc.close();

        System.out.println(Arrays.toString(newArray));

        Array.invertArrayVoid(newArray);

        System.out.println(Arrays.toString(newArray));
    }
}
