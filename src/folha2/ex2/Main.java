package folha2.ex2;

import utils.InputValidation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int raio;
        String cor;

        Scanner sc = new Scanner(System.in);

        raio = InputValidation.validateIntGT0(sc, "Por favor, introduza o raio do primeiro círculo:");

        Circulo circulo1 = new Circulo(raio);

        do {
            System.out.println("Por favor, introduza a cor do primeiro círculo (azul, verde, vermelho ou preto):");
            cor = sc.nextLine().toLowerCase();;

        } while(!cor.equals("azul") && !cor.equals("verde") && !cor.equals("vermelho") && !cor.equals("preto"));

        circulo1.setCor(cor);

        raio = InputValidation.validateIntGT0(sc, "Por favor, introduza o raio do segundo círculo:");

        do {
            System.out.println("Por favor, introduza a cor do segundo círculo (azul, verde, vermelho ou preto):");
            cor = sc.nextLine().toLowerCase();;

        } while(!cor.equals("azul") && !cor.equals("verde") && !cor.equals("vermelho") && !cor.equals("preto"));

        sc.close();

        Circulo circulo2 = new Circulo(raio, cor);

        new DrawCircles(circulo1, circulo2);
    }
}
