package fr.sncf.banque;

import java.util.HashMap;
import java.util.Map;

public class CompteBancaire {

    private static final Map<String, CompteBancaire> comptes = new HashMap<>();

    double sold;
    private final String iban;

    public CompteBancaire(String iban) {
        this.iban = iban;
        CompteBancaire.comptes.put(iban, this);
    }

    public double getSold() {
        return sold;
    }

    public String getIban() {
        return iban;
    }

    public void faireVirement(String iban, double montant) {
        CompteBancaire destinataire = CompteBancaire.trouverParIban(iban);
        if (this.debiter(montant)) {
            destinataire.crediter(montant);
        }
    }

    public synchronized void crediter(double montant) {
        this.sold += montant;
    }

    public synchronized boolean debiter(double montant) {
        if (this.sold < montant) {
            return false;
        }
        this.sold -= montant;
        return true;
    }

    public static void printAll() {
        for (CompteBancaire compte: CompteBancaire.comptes.values()) {
            System.out.printf("%s: \t%#7.2f\n", compte.iban, compte.sold);
        }
    }
    private static CompteBancaire trouverParIban(String iban) {
        return CompteBancaire.comptes.get(iban);
    }
}
