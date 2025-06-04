package ded;

import ded.donjon.Donjon;
import ded.donjon.DonjonParDefault;
import ded.entite.*;
import ded.entite.race.*;
import ded.entite.classe.*;
import ded.objet.Arme;
import ded.objet.Armure;

import java.util.ArrayList;
import java.util.Scanner;

public class Affichage{


    private static Scanner m_scanner = new Scanner(System.in);
    private static ArrayList<Personnage> m_lstPersonnage = new ArrayList<>();

    public ArrayList<Personnage> MetreEnPlacePeronnage() {
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

        /*//metre en place mosntre
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
        }*/

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
                    case "1" : this.metreEnPlaceArme(donjon);
                    case "2" : this.metreEnPlaceArmure(donjon);
                    case "3" : flag3 = false;
                }
            }
        }

        //obstacles
        if(flag){
            this.clearTerminal(donjon);
            System.out.println("Ajouter obstacles:");
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
        System.out.println("Choisir une arme parmis celle-ci:\n"+
                "1.bâton, dégât: 1d6, portée: 1 case\n" +
                "2.masse d'armes, dégât: 1d6, portée: 1 case\n" +
                "3.épée longue, dégât: 1d8, portée: 1 case\n" +
                "4.rapière, dégât: 1d8, portée: 1 case\n" +
                "5.arbalète légère, dégât: 1d8, portée 16 cases\n" +
                "6.fronde, dégât 1d4, portée 6 cases\n" +
                "7.arc court, dégât 1d6, portée 16 cases");
        String  choix = (String) m_scanner.nextLine();
        Arme arme;
        switch (choix) {
            case "1" : arme = new Arme("Baton",null, Arme.Type.COURANTE, new int[]{1,6},1 );
            case "2" : arme = new Arme("Masse d'armes",null, Arme.Type.COURANTE, new int[]{1,6},1 );
            case "3" : arme = new Arme("Epee longue",null, Arme.Type.GUERRE, new int[]{1,8},1 );
            case "4" : arme = new Arme("Rapière",null, Arme.Type.GUERRE, new int[]{1,8},1 );
            case "5" : arme = new Arme("Arbalete legere",null, Arme.Type.DISTANCE, new int[]{1,8},16 );
            case "6" : arme = new Arme("Fronde",null, Arme.Type.DISTANCE, new int[]{1,4},6 );
            case "7" : arme = new Arme("Arc court",null, Arme.Type.DISTANCE, new int[]{1,6},16 );
            default : {
                System.out.println("Aucune option choisis, Baton choisis.");
                arme = new Arme("Baton",null, Arme.Type.COURANTE, new int[]{1,6},1 );
            }
        }
        System.out.println("Choisir la coordonée x de l'arme.");
        int  choix1 = m_scanner.nextInt();
        System.out.println("Choisir la coordonée y de l'arme.");
        int  choix2 = m_scanner.nextInt();
        arme.setCoo(new int[]{choix1,choix2} );
        donjon.addEquipement(arme);
    }
    public void metreEnPlaceArmure(Donjon donjon){

        this.clearTerminal(donjon);
        System.out.println("Choisir une armure parmis celle-ci:\n"+
                "1.armure d'écailles, classe d'armure: 9\n" +
                "2.demi-plate,        classe d'armure: 10\n" +
                "3.cotte de mailles,  classe d'armure: 11\n" +
                "4.harnois: classe d'armure: 12\n" );
        String choix = (String) m_scanner.nextLine();
        Armure armure;
        switch (choix) {
            case "1" : armure = new Armure("Cotte de mailles",null, Armure.Type.LOURDE,11);
            case "2" : armure = new Armure("Demi-plate",null, Armure.Type.LEGERE,9);
            case "4" : armure = new Armure("Harnois",null, Armure.Type.LOURDE,12);
            case "3" : armure = new Armure("Cotte de mailles",null, Armure.Type.LOURDE,11);
            default : {
                System.out.println("Aucune option choisis, Cotte de mailles choisis.");
                armure = new Armure("Cotte de mailles",null, Armure.Type.LOURDE,11);
            }
        }
        System.out.println("Choisir la coordonée x de l'armure.");
        int  choix1 = m_scanner.nextInt();
        System.out.println("Choisir la coordonée y de l'armure.");
        int  choix2 = m_scanner.nextInt();
        armure.setCoo(new int[]{choix1,choix2} );
        donjon.addEquipement(armure);
    }






    // Pas oublier de fermer : scanner.close()
    //scanner.close();
}
