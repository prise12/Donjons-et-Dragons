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


    private static final Scanner m_scanner = new Scanner(System.in);

    public static void MetreEnPlacePersonnage(ArrayList<Personnage> lstPersonnage) {
        for(int i = 0; i < 4; i++)
        {
            Race race = null;
            Classe classe = null;
            System.out.println("Choisir le nom du personnage n °" + (i+1) + ":");
            String nom = m_scanner.nextLine();

            // Choix de la race
            System.out.println("Choisir une race :\n1. Humain\n2. Elfe\n3. Halfelin\n4. Nain");
            int  choixRace =  m_scanner.nextInt();
            switch (choixRace) {
                case 1 : race = new Humain();
                case 2 : race = new Elfe();
                case 3 : race = new Halfelin();
                case 4 : race = new Nain();
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
            lstPersonnage.add(p);

        }
    }

    public static void MetreEnPlaceDonjon(Donjon donjon) {
        System.out.println("Metre en place le donjon" );

        Boolean flag = false;

        //metre en place donjon choisis
        System.out.println("Choisir un donjon :\n1. Donjon par default 1\n2. Donjon par default 2\n3. Donjon par default 3\n4. Mettre en place le donjon");
        int  choix = m_scanner.nextInt();
        switch (choix) {
            case 1 : donjon = DonjonParDefault.getDonjonParDefault1(); break;
            case 2 : donjon = DonjonParDefault.getDonjonParDefault2(); break;
            case 3 : donjon = DonjonParDefault.getDonjonParDefault3(); break;
            case 4 : flag = true; break;
            default : {
                System.out.println("Aucune option choisis, donjon par default 1 choisis.");
                donjon = DonjonParDefault.getDonjonParDefault1();
            }
        }

        if(flag){
            //Dimension de la map
            System.out.println("Choisir dimension map :");
            System.out.println("X :");
            choix = m_scanner.nextInt();
            System.out.println("Y :");
            int choix2 = m_scanner.nextInt();
            donjon.setDiemensionMap(new int[]{choix,choix2});

            Affichage.clearTerminalDonjon(donjon);
            //ajout des monstres
            ArrayList<Espece> lstEspece = new ArrayList<Espece>();
            lstEspece.add(new Espece("Gobelin"));
            System.out.println("Ajouter equipement:");
            boolean flag2 = true;
            while (flag2){
                System.out.println("1. Ajouter Monstre\n2. Continuer la mise en place du donjon");
                choix = m_scanner.nextInt();
                switch (choix) {
                    case 1 : Affichage.mettreEnPlaceMonstre(donjon, lstEspece);break;
                    case 3 : flag2 = false;
                }
            }

            //ajout des equipements
            System.out.println("Ajouter equipement:");
            boolean flag3 = true;
            while (flag3){
                System.out.println("1. Ajouter Arme\n2. Ajouter Armure \n3. Continuer la mise en place du donjon");
                choix = m_scanner.nextInt();
                switch (choix) {
                    case 1 : Affichage.mettreEnPlaceArme(donjon);break;
                    case 2 : Affichage.mettreEnPlaceArmure(donjon); break;
                    case 3 : flag3 = false;
                }
            }
            System.out.println("Ajouter obstacles:");
            boolean flag4 = true;
            while (flag4){
                System.out.println("1. Ajouter Obstacle\n2. Continuer mise en place donjon");
                System.out.println("");
                choix = m_scanner.nextInt();
                switch (choix) {
                    case 1 : Affichage.mettreEnPlaceObstacle(donjon);break;
                    case 2 : flag4 = false;
                }
            }
        }
        Affichage.clearTerminalDonjon(donjon);
    }

    public static void clearTerminalDonjon(Donjon donjon){
        System.out.flush();
        donjon.genererDonjon();
        System.out.println( donjon.getDonjon());
    }

    public static void mettreEnPlaceObstacle(Donjon donjon){
        System.out.println("Choisir les coordonées de l'obstacle:");
        System.out.println("X :");
        int  choix1 = m_scanner.nextInt();
        System.out.println("Y :");
        int  choix2 = m_scanner.nextInt();
        donjon.addObstacles(new int[]{choix1, choix2});
        if(! donjon.addObstacles(new int[]{choix1, choix2})){
            System.out.println("Les coordonées appartienent deja à quelque chose sur la map ou sont hors des dimensions.");
            mettreEnPlaceObstacle(donjon);
        }
    }

    public static void mettreEnPlaceArme(Donjon donjon){
        System.out.println("Choisir une arme parmis celle-ci:\n"+
                "1.bâton, dégât: 1d6, portée: 1 case\n" +
                "2.masse d'armes, dégât: 1d6, portée: 1 case\n" +
                "3.épée longue, dégât: 1d8, portée: 1 case\n" +
                "4.rapière, dégât: 1d8, portée: 1 case\n" +
                "5.arbalète légère, dégât: 1d8, portée 16 cases\n" +
                "6.fronde, dégât 1d4, portée 6 cases\n" +
                "7.arc court, dégât 1d6, portée 16 cases");
        int choix =  m_scanner.nextInt();
        Arme arme;
        switch (choix) {
            case 1 : arme = new Arme("Baton",null, Arme.Type.COURANTE, new int[]{1,6},1 );
            case 2 : arme = new Arme("Masse d'armes",null, Arme.Type.COURANTE, new int[]{1,6},1 );
            case 3 : arme = new Arme("Epee longue",null, Arme.Type.GUERRE, new int[]{1,8},1 );
            case 4 : arme = new Arme("Rapière",null, Arme.Type.GUERRE, new int[]{1,8},1 );
            case 5 : arme = new Arme("Arbalete legere",null, Arme.Type.DISTANCE, new int[]{1,8},16 );
            case 6 : arme = new Arme("Fronde",null, Arme.Type.DISTANCE, new int[]{1,4},6 );
            case 7 : arme = new Arme("Arc court",null, Arme.Type.DISTANCE, new int[]{1,6},16 );
            default : {
                System.out.println("Aucune option choisis, Baton choisis.");
                arme = new Arme("Baton",null, Arme.Type.COURANTE, new int[]{1,6},1 );
            }
        }
        System.out.println("Choisir les coordonées de l'Armure:");
        System.out.println("X :");
        int  choix1 = m_scanner.nextInt();
        System.out.println("Y :");
        int  choix2 = m_scanner.nextInt();
        arme.setCoo(new int[]{choix1,choix2} );
        if(! donjon.addEquipement(arme)){
            System.out.println("Les coordonées appartienent deja à quelque chose sur la map ou sont hors des dimensions.");
            mettreEnPlaceArme(donjon);
        }
    }
    public static void mettreEnPlaceArmure(Donjon donjon){

        System.out.println("Choisir une armure parmis celle-ci:\n"+
                "1.armure d'écailles, classe d'armure: 9\n" +
                "2.demi-plate,        classe d'armure: 10\n" +
                "3.cotte de mailles,  classe d'armure: 11\n" +
                "4.harnois: classe d'armure: 12\n" );
        int choix = m_scanner.nextInt();
        Armure armure;
        switch (choix) {
            case 1 : armure = new Armure("Cotte de mailles",null, Armure.Type.LOURDE,11);
            case 2 : armure = new Armure("Demi-plate",null, Armure.Type.LEGERE,9);
            case 4 : armure = new Armure("Harnois",null, Armure.Type.LOURDE,12);
            case 3 : armure = new Armure("Cotte de mailles",null, Armure.Type.LOURDE,11);
            default : {
                System.out.println("Aucune option choisis, Cotte de mailles choisis.");
                armure = new Armure("Cotte de mailles",null, Armure.Type.LOURDE,11);
            }
        }
        System.out.println("Choisir les coordonées de l'armure:");
        System.out.println("X :");
        int  choix1 = m_scanner.nextInt();
        System.out.println("Y :");
        int  choix2 = m_scanner.nextInt();
        armure.setCoo(new int[]{choix1,choix2});

        if(! donjon.addEquipement(armure)){
            System.out.println("Les coordonées appartienent deja à quelque chose sur la map ou sont hors des dimensions.");
            mettreEnPlaceArmure(donjon);
        }
    }

    public static void mettreEnPlaceMonstre(Donjon donjon, ArrayList<Espece> lstEspece){
        Entite monstre;
        Espece espece;
        int[] stats = new int[5];

        //nom du monstre
        System.out.println("Choisir le nom du personnage n :");
        String nom = m_scanner.nextLine();

        //espece du monstre
        System.out.println("Choisir une espece :\n1. Choisir d'une espece existante :\n2. Créer une nouvelle espece :");
        int choix = m_scanner.nextInt();

        switch (choix) {
            case 1 : espece = Affichage.mettreEnPlaceEspece(lstEspece);
            case 2 : espece = Affichage.choisirParmisEspece(lstEspece);
            default : {
                System.out.println("Aucune option choisis,"+ lstEspece.get(0).getNom() +"choisis.");
                espece = lstEspece.get(0);
            }
        }

        //Les caracteristique du monstre
        System.out.println("Classe d'armure :");
        int classeArmure = m_scanner.nextInt();
        System.out.println("Vie :");
        stats[0] = m_scanner.nextInt();
        System.out.println("Vitesse :");
        stats[1] = m_scanner.nextInt();
        System.out.println("Force :");
        stats[2] = m_scanner.nextInt();
        System.out.println("Dexterité :");
        stats[3] = m_scanner.nextInt();
        System.out.println("Initiative :");
        stats[4] = m_scanner.nextInt();

        //Coordonées du monstre
        System.out.println("Choisir les coordonées du Monstre:");
        System.out.println("X :");
        int  choix1 = m_scanner.nextInt();
        System.out.println("Y :");
        int  choix2 = m_scanner.nextInt();
        int[] coo = new int[]{choix1,choix2};

        monstre = new Monstre("oui", coo, espece, classeArmure, stats);
        donjon.addEntitee(monstre);
    }
    public static Espece mettreEnPlaceEspece(ArrayList<Espece> lstEspece){
        System.out.println("Choisir nom Espece:");
        String choix = m_scanner.nextLine();
        Espece e = new Espece(choix);
        lstEspece.add(e);
        return e;
    }
    public static Espece choisirParmisEspece(ArrayList<Espece> lstEspece){
        System.out.println("Choisir Parmis les especes:");
        int i = 0;
        for(Espece e : lstEspece){
            System.out.println(i + ". " + e.getNom());
        }
        int choix = m_scanner.nextInt();
        if(0 < choix  && choix< lstEspece.size()){
            return lstEspece.get(choix);
        }
        else{
            System.out.println("Aucune option choisis,"+ lstEspece.get(0).getNom() +"choisis.");
            return lstEspece.get(0);
        }
    }

    // Pas oublier de fermer : scanner.close()
    //scanner.close();
}
