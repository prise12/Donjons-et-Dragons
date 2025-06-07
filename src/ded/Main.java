package ded;

import ded.affichage.AffichageMiseEnPlace;
import ded.affichage.AffichageTour;
import ded.donjon.Donjon;

import java.io.IOException;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        String rouge = "\u001B[0m";
        System.out.println(rouge + "oui" + "\u001B[0m");
        AffichageMiseEnPlace.metreEnPlacePersonnage();
        Donjon d1 = AffichageMiseEnPlace.creerDonjon();
        AffichageTour.jeuDonjon(d1);
    }
}
