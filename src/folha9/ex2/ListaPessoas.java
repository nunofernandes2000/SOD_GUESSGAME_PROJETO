package folha9.ex2;

import utils.InputValidation;

import java.util.Scanner;

public class ListaPessoas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String nome1, nome2, nome3;
        int idade1, idade2, idade3;

        System.out.println("Por favor, introduza o nome da primeira pessoa");
        nome1 = sc.nextLine();
        idade1 = InputValidation.validateIntGE0(sc,"Por favor, introduza a idade da primeira pessoa");

        System.out.println("Por favor, introduza o nome da segunda pessoa");
        nome2 = sc.nextLine();
        idade2 = InputValidation.validateIntGE0(sc,"Por favor, introduza a idade da segunda pessoa");

        System.out.println("Por favor, introduza o nome da terceira pessoa");
        nome3 = sc.nextLine();
        idade3 = InputValidation.validateIntGE0(sc,"Por favor, introduza a idade da terceira pessoa");

        sc.close();

        ThreadPessoa t1Pessoa = new ThreadPessoa(nome1, idade1);
        ThreadPessoa t2Pessoa = new ThreadPessoa(nome2, idade2);
        ThreadPessoa t3Pessoa = new ThreadPessoa(nome3, idade3);

        t1Pessoa.add1ToIdade();
        t2Pessoa.add1ToIdade();
        t3Pessoa.add1ToIdade();

        t1Pessoa.start();
        t2Pessoa.start();
        t3Pessoa.start();
    }
}
