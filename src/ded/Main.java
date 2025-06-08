package ded;

import ded.affichage.AffichageMiseEnPlace;
import ded.affichage.AffichageTour;
import ded.donjon.Donjon;

import java.io.IOException;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        for(int i = 1; i<=3; i++) {
            AffichageMiseEnPlace.metreEnPlacePersonnage();
            Donjon d1 = AffichageMiseEnPlace.creerDonjon();
            AffichageTour.afficherDonjon(d1,i);
        }
    }
}
