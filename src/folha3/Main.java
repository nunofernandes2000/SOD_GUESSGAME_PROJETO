package folha3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> students = new ArrayList<>();

        students.add("Daniel Carrilho");
        students.add("Belissandro Cabral");
        students.add("João Gama");
        students.add("Carlos Raimundo");
        students.add("João Magarreiro");
        students.add("Pedro Gaspar");
        students.add("Dénis Furtado");
        students.add("Mamadu Embaló");
        students.add("Álvaro Melo");
        students.add("Nuno Fernandes");
        students.add("Pedro Andrade");
        students.add("Afonso Botelho");
        students.add("Fausto Serodio");
        students.add("Rafael Carvalho");
        students.add("André Silva");
        students.add("Diogo Constantino");

        double rand = Math.random();

        int choice = (int)(rand * students.size());

        if (choice == students.size()) {
            choice--;
        }

        System.out.println("\n" + choice + " : " + students.get(choice)
                + "\t(rand = " + rand + " ; double choice = " + (rand * students.size()) + ")");
    }
}
