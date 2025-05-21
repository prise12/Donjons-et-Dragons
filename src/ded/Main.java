package ded;

import ded.entite.Entite;
import ded.entite.Personnage;
import ded.objet.Equipement;

import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        LinkedHashMap<Entite, int[]> dictEntite = new LinkedHashMap<>();
        LinkedHashMap<Equipement, int[]> dictEquipement = new LinkedHashMap<>();
        int[][] tabObstacles = new int[0][0];
        int largeurMap = 30;

        Entite bryan = new Personnage("Bryan");
        Entite fab = new Personnage("Fab");

        dictEntite.put(bryan,new int[]{5,5});
        dictEntite.put(fab,new int[]{10,12});



        Donjon donjon1 = new Donjon(dictEntite,dictEquipement,tabObstacles, largeurMap );

        donjon1.genererDonjon();
        donjon1.afficherDonjon();




    }

}
