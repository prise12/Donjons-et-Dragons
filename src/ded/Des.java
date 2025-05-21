package ded;

import java.util.Random;

public class Des {
    private static final Random jetD = new Random();

    // Lance un dé à X faces
    public static int lancer(int faces) {
        return jetD.nextInt(faces) + 1;
    }

    // Lance plusieurs dés et additionne le résultat
    public static int lancerMulti(int nbDes, int faces) {
        int jet = 0;
        for (int i = 0; i < nbDes; i++) {
            jet += lancer(faces);
            return jet;
        }
    }


    public static int lancerBonus(int nbDes, int faces, int bonus) {
        return lancerMulti(nbDes, faces) + bonus;
    }
}