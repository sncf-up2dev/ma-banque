package fr.sncf.banque;

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
        try {
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
                } catch (CompteNonTrouvableException e) {
                    System.err.println("Compte non trouvé !");
                } catch (SoldeNonSuffisanteException e) {
                    System.err.println("Solde non suffisante !");
                }
                CompteBancaire.printAll();
            }
        } finally {
            scanner.close();
        }
    }
}
