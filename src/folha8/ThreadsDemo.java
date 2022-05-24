package folha8;

import java.util.Scanner;

public class ThreadsDemo {

    public static void main(String[] args) {

        System.out.println("O processo main foi iniciado");

        Scanner sc = new Scanner(System.in);

        System.out.println("Por favor, introduza o nome da primeira thread:");
        String t1Name = sc.nextLine();

        System.out.println("Por favor, introduza o nome da segunda thread:");
        String t2Name = sc.nextLine();

        Threads t1 = new Threads(t1Name);
        Threads t2 = new Threads(t2Name);

        t1.start();
        t2.start();

        // se não colocar este código a thread nain termina antes das 2 threads
        /*try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Erro ao esperar que as threads terminassem");
        }*/

        System.out.println("Por favor, pressione qualquer tecla para terminar");
        sc.nextLine();

        sc.close();

        System.out.println("O processo main foi terminado");
    }
}
