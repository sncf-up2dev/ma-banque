package fr.sncf.banque;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CompteBancaire monCompte;
        new CompteBancaire("FR001");
        new CompteBancaire("FR002");
        new CompteBancaire("FR003");
        monCompte = new CompteBancaire("FR000");

        System.out.println("Bienvenu à l'application Vivement Virement !");

        monCompte.crediter(500);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Veuillez entrer le code iban de destinataire, ou \"exit\" pour sortir.");
            String iban = scanner.nextLine(); // Lecture code iban
            if (iban.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Veuillez entrer un montant.");
            String montantStr = scanner.nextLine();
            try {
                double montant = Double.parseDouble(montantStr);
                monCompte.faireVirement(iban, montant);
            } catch (NumberFormatException e) {
                System.err.println("Le montant doit être une valeur réelle !");
            }
            CompteBancaire.printAll();
        }
    }
}
