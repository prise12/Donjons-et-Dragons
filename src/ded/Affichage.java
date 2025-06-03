package ded;

import ded.donjon.Donjon;
import ded.donjon.DonjonParDefault;
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
                case "1" : race = new Humain();
                case "2" : race = new Elfe();
                case "3" : race = new Halfelin();
                case "4" : race = new Nain();
                default : {
                    System.out.println("Aucune race choisi, humain assigné par default.");
                    race = new Humain();
                }
            }

            // Choix de la classe
            System.out.println("Choisir une classe :\n1. Guerrier\n2. Mage\n3. Voleur\n4. Clerc");
            String choixClasse = (String) m_scanner.nextLine();
            switch (choixClasse) {
                case "1" : classe = new Guerrier();
                case "2" : classe = new Magicien();
                case "3" : classe = new Roublard();
                case "4" : classe = new Clerc();
                default : {
                    System.out.println("Aucune classe choisi, Guerrier assigné par default.");
                    classe = new Guerrier();
                }
            }
            Personnage p = new Personnage(nom,null,classe, race);
            m_lstPersonnage.add(p);

        }
        return m_lstPersonnage;
    }





    public Donjon MetreEnPlaceDonjon(int nDonjon) {
        Race race = null;
        Classe classe = null;
        System.out.println();
        System.out.println("Choisir le nom du personnage n °" + nDonjon + ":");
        String nom = m_scanner.nextLine();

        Donjon donjon;
        Boolean flag = false;

        //metre en place donjon choisis
        System.out.println("Choisir un donjon :\n1. Donjon par default 1\n2. Donjon par default 2\n3. Donjon par default 3\n4. Metre en place le donjon");
        String  choixRace = (String) m_scanner.nextLine();
        switch (choixRace) {
            case "1" : donjon = DonjonParDefault.getDonjonParDefault1();
            case "2" : donjon = DonjonParDefault.getDonjonParDefault1();
            case "3" : donjon = DonjonParDefault.getDonjonParDefault1();
            case "4" : flag = true;
            default : {
                System.out.println("Aucune option choisis, donjon par default 1 choisis.");
                donjon = DonjonParDefault.getDonjonParDefault1();
            }
        }

        //metre en place mosntre
        if(flag){
            this.clearTerminal(donjon);
            System.out.println("Ajouter monstre:");
            System.out.println("1. Ajouter monstre\n2. continuer mise en place donjon");
            boolean flag2 = true;
            while (flag2){
                Monstre monstre;
                System.out.println("");
                String  choix = (String) m_scanner.nextLine();
                switch (choix) {
                    case "1" : this.metreEnPlaceMonstre(donjon);
                    case "2" : flag2 = false;
                }
            }
        }

        //equipements
        if(flag){
            this.clearTerminal(donjon);
            System.out.println("Ajouter equipement:");
            System.out.println("1. Ajouter Arme\n2.Ajouter Armure, \n2. continuer mise en place donjon");
            boolean flag3 = true;
            while (flag3){
                Monstre monstre;
                System.out.println("");
                String  choix = (String) m_scanner.nextLine();
                switch (choix) {
                    case "1" : this.metreEnPlaceEquipement(donjon);
                    case "2" : flag3 = false;
                }
            }
        }

        //onbstacles
        if(flag){
            this.clearTerminal(donjon);
            System.out.println("Ajouter equipement:");
            System.out.println("1. Obstacles\n2. continuer mise en place donjon");
            boolean flag4 = true;
            while (flag4){
                Monstre monstre;
                System.out.println("");
                String  choix = (String) m_scanner.nextLine();
                switch (choix) {
                    case "1" : this.metreEnPlaceObstacle(donjon);
                    case "2" : flag4 = false;
                }
            }
        }

        return donjon;


    }

    public void clearTerminal(Donjon donjon){
        System.out.flush();
        donjon.genererDonjon();
        System.out.println(donjon.getDonjon());
    }

    public void metreEnPlaceObstacle(Donjon donjon){
        this.clearTerminal(donjon);
        System.out.println("Choisir la coordonée x de obstacle.");
        int  choix1 = m_scanner.nextInt();
        System.out.println("Choisir la coordonée y de obstacle.");
        int  choix2 = m_scanner.nextInt();
        donjon.addObstacles(new int[]{choix1, choix2});
    }

    public void metreEnPlaceArme(Donjon donjon){
        this.clearTerminal(donjon);
        System.out.println("Choisir la coordonée x de obstacle.");
        int  choix1 = m_scanner.nextInt();
        System.out.println("Choisir la coordonée y de obstacle.");
        int  choix2 = m_scanner.nextInt();
        donjon.addObstacles(new int[]{choix1, choix2});
    }




    // Pas oublier de fermer : scanner.close()
    //scanner.close();
}
