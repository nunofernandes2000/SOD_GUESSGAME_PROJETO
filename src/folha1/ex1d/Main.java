package folha1.ex1d;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Por favor, introduza o texto sobre o qual quer contar o número de vogais:");
        String str = sc.nextLine();

        sc.close();

        System.out.println("O texto introduzido tem " + vowelCount(str) + " vogais.");
    }

    public static int vowelCount(String str) {

        int count = 0;

        String strLowerCase = str.toLowerCase();

        for (int i = 0 ; i < str.length(); i++){

            char ch = strLowerCase.charAt(i);
            if(ch == 'a'|| ch == 'e'|| ch == 'i' ||ch == 'o' ||ch == 'u'){
                count ++;
            }
        }
        return count;
    }
}
