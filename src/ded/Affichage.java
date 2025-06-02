package ded;

import ded.donjon.Donjon;
import ded.entite.*;
import ded.entite.race.*;
import ded.entite.classe.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Affichage{


    private static Scanner m_scanner = new Scanner(System.in);
    private static ArrayList<Personnage> m_lstPersonnage = new ArrayList<>();

    public static ArrayList<Personnage> MetreEnPlacePeronnage() {
        for(int i = 0; i < 4; i++)
        {
            Race race = null;
            Classe classe = null;
            System.out.println("Choisir le nom du personnage n °" + (i+1) + ":");
            String nom = m_scanner.nextLine();

            // Choix de la race
            System.out.println("Choisir une race :\n1. Humain\n2. Elfe\n3. Halfelin\n4. Nain");
            String  choixRace = (String) m_scanner.nextLine();
            switch (choixRace) {
                case "1" -> race = new Humain();
                case "2" -> race = new Elfe();
                case "3" -> race = new Halfelin();
                case "4" -> race = new Nain();
                default -> {
                    System.out.println("Aucune race choisi, humain assigné par default.");
                    race = new Humain();
                }
            }

            // Choix de la classe
            System.out.println("Choisir une classe :\n1. Guerrier\n2. Mage\n3. Voleur\n4. Clerc");
            String choixClasse = (String) m_scanner.nextLine();
            switch (choixClasse) {
                case "1" -> classe = new Guerrier();
                case "2" -> classe = new Magicien();
                case "3" -> classe = new Roublard();
                case "4" -> classe = new Clerc();
                default -> {
                    System.out.println("Aucune classe choisi, Guerrier assigné par default.");
                    classe = new Guerrier();
                }
            }
            Personnage p = new Personnage(nom,null,classe, race);
            m_lstPersonnage.add(p);

        }
        return m_lstPersonnage;
    }





    /*public static Donjon MetreEnPlaceDonjon() {




    }*/



    // Pas oublier de fermer : scanner.close()
    //scanner.close();
}
