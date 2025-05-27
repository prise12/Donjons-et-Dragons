package ded;

import ded.entite.Entite;
import ded.entite.Personnage;
import ded.objet.Arme;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        LinkedHashMap<Entite, int[]> dictEntite = new LinkedHashMap<>();
        LinkedHashMap<Equipement, int[]> dictEquipement = new LinkedHashMap<>();
        ArrayList<int[]> lstObstacles = new ArrayList<int[]>();
        int largeurMap = 30;

        Entite bryan = new Personnage("Bryan");
        Entite fab = new Personnage("Fab");
        Equipement sabre = new Arme(Arme.Type.COURANTE,new int[]{8,5},8, "sabre");

        dictEntite.put(bryan,new int[]{5,5});
        dictEntite.put(fab,new int[]{10,12});

        dictEquipement.put(sabre, new int[]{10,4});

        lstObstacles.add(new int[]{2,3});

        Donjon donjon1 = new Donjon(dictEntite,dictEquipement,lstObstacles, largeurMap );

        donjon1.genererDonjon();
        System.out.print(donjon1.getDonjon());




    }

}
