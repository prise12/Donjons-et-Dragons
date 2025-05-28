package ded;

import ded.entite.*;
import ded.entite.race.*;
import ded.entite.classe.*;
import ded.objet.*;
import ded.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Mise_en_place {

    Scanner scanner = new Scanner(System.in);

    Race race = null;
    Classe classe = null;
    ArrayList<Personnage> personnages = new ArrayList<>();

    for(int i = 0; i < 4; i++)
    {
        System.out.println("Choisir le nom du personnage : ");
        String nom = scanner.nextLine();

        // Choix de la race
        System.out.println("Choisir une race :\n1. Humain\n2. Elfe\n3. Halfelin\n4. Nain");
        int choixRace = Integer.parseInt(scanner.nextLine());
        switch (choixRace) {
            case 1 -> race = new Humain();
            case 2 -> race = new Elfe();
            case 3 -> race = new Halfelin();
            case 4 -> race = new Nain();
            default -> {
                System.out.println("Race invalide.");
                return;
            }
        }

        // Choix de la classe
        System.out.println("Choisir une classe :\n1. Guerrier\n2. Mage\n3. Voleur\n4. Clerc");
        int choixClasse = Integer.parseInt(scanner.nextLine());
        switch (choixClasse) {
            case 1 -> classe = new Guerrier();
            case 2 -> classe = new Magicien();
            case 3 -> classe = new Roublard();
            case 4 -> classe = new Clerc();
            default -> {
                System.out.println("Classe invalide.");
                return;
            }
        }

        Personnage p = new Personnage(nom, classe, race);
        personnages.add(p);

    }

    // Pas oublier de fermer : scanner.close()
    //scanner.close();
}
