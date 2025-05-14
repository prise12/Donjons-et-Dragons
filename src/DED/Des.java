package DED;

import java.util.Random;

public class De {
    private static final Random jetD = new Random();

    // Lance un dé à X faces
    public static int lancer(int faces) {
        return jetD.nextInt(faces) + 1;
    }

    // Lance plusieurs dés et additionne le résultat
    public static int lancerMultiple(int nbDes, int faces) {
        int total = 0;
        for (int i = 0; i < nbDes; i++) {
            total += lancer(faces);
        }
        return total;
    }


    public static int lancerAvecBonus(int nbDes, int faces, int bonus) {
        return lancerMultiple(nbDes, faces) + bonus;
    }
}