package folha1.ex1e;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Por favor, introduza o texto sobre o qual quer contar o número de palavras:");
        String str = sc.nextLine();

        sc.close();

        System.out.println("O texto introduzido tem " + wordCount(str) + " palavras.");
    }

    public static int wordCount(String str) {

        int count = 0;

        // por omissão conta o número de tokens (palavras neste caso) entre os delimitadores " \t\n\r\f" na frase
        StringTokenizer tk = new StringTokenizer(str);

        return tk.countTokens();
    }
}