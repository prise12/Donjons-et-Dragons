package ded;

import ded.donjon.Donjon;
import ded.entite.Entite;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Donjon d1 = new Donjon();
        ArrayList<Entite> lstPersonnage = new ArrayList<Entite>();
        AffichageMiseEnPlace.MetreEnPlacePersonnage(lstPersonnage);
        d1.setLstEntite(lstPersonnage);
        AffichageMiseEnPlace.MetreEnPlaceDonjon(d1);
        AffichageTour.jeuDonjon(d1);

    }
}
