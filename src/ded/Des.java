package ded;

import java.util.Random;

/**
 * Classe utilitaire pour simuler des jets de dés.
 * Fournit des méthodes pour lancer un dé simple, plusieurs dés,
 * ou appliquer un bonus aux résultats.
 */
public class Des {

    // ===================== ATTRIBUTS =====================
    private static final Random jetD = new Random();

    // ===================== MÉTHODES DE LANCERS =====================

    /**
     * Lance un dé à X faces.
     * @param faces nombre de faces du dé (ex : 6 pour un d6)
     * @return résultat du lancer (entre 1 et faces inclus)
     */
    public static int lancer(int faces) {
        return jetD.nextInt(faces) + 1;
    }

    /**
     * Lance plusieurs dés et additionne les résultats.
     * @param nbDes nombre de dés à lancer
     * @param faces nombre de faces de chaque dé
     * @return somme des résultats des dés
     */
    public static int lancerMulti(int nbDes, int faces) {
        int total = 0;
        for (int i = 0; i < nbDes; i++) {
            total += lancer(faces);
        }
        return total;
    }

    /**
     * Lance plusieurs dés et ajoute un bonus fixe au total.
     * @param nbDes nombre de dés
     * @param faces nombre de faces
     * @param bonus valeur à ajouter au résultat total
     * @return total + bonus
     */
    public static int lancerBonus(int nbDes, int faces, int bonus) {
        return lancerMulti(nbDes, faces) + bonus;
    }
}
