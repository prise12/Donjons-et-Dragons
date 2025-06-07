package ded;

import ded.affichage.AffichageMiseEnPlace;
import ded.affichage.AffichageTour;
import ded.donjon.Donjon;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AffichageMiseEnPlace.metreEnPlacePersonnage();
        Donjon d1 = AffichageMiseEnPlace.creerDonjon();
        AffichageTour.jeuDonjon(d1);
    }
}
