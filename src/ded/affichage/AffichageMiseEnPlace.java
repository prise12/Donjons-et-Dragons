package ded.affichage;

import ded.donjon.Donjon;
import ded.donjon.DonjonParDefault;
import ded.entite.*;
import ded.entite.race.*;
import ded.entite.classe.*;
import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsable de l’affichage et de la mise en place
 * des éléments du jeu tels que les personnages, le donjon,
 * les monstres, les obstacles et les équipements.
 */
public class AffichageMiseEnPlace {

    // ===================== ATTRIBUTS =====================
    private static final Scanner m_scanner = new Scanner(System.in);
    private static final ArrayList<Personnage> lstPersonnage = new ArrayList<>();

    // ===================== MÉTHODES PUBLIQUES =====================

    /**
     * Permet à l’utilisateur de créer un ou plusieurs personnages.
     */
    public static void metreEnPlacePersonnage() {
        System.out.print("Nombre de personnages : ");
        int nbPersonnage = m_scanner.nextInt();
        m_scanner.nextLine();

        for (int i = 0; i < nbPersonnage; i++) {
            System.out.println("\nPersonnage " + (i + 1));
            System.out.print("Nom : ");
            String nom = m_scanner.nextLine();

            Race race = choisirRace();
            Classe classe = choisirClasse();
            m_scanner.nextLine();

            lstPersonnage.add(new Personnage(nom, null, classe, race));
        }
    }

    /**
     * Crée un donjon à partir de choix de l’utilisateur (prédéfini ou personnalisé).
     * @return le donjon créé avec les personnages placés
     */
    public static Donjon creerDonjon() {
        System.out.println("\nConfiguration du donjon :");
        System.out.println("1. Donjon par défaut 1");
        System.out.println("2. Donjon par défaut 2");
        System.out.println("3. Donjon par défaut 3");
        System.out.println("4. Donjon personnalisé");
        System.out.print("Choix : ");

        int choix = m_scanner.nextInt();
        switch (choix) {
            case 2: return placerPersonnages(DonjonParDefault.getDonjonParDefault2());
            case 3: return placerPersonnages(DonjonParDefault.getDonjonParDefault3());
            case 4: return placerPersonnages(metreEnPlaceDonjon());
            default: return placerPersonnages(DonjonParDefault.getDonjonParDefault1());
        }
    }

    /**
     * Crée un donjon personnalisé en demandant ses dimensions.
     * @return un donjon vide configuré
     */
    public static Donjon metreEnPlaceDonjon() {
        System.out.println("\nDimensions du donjon :");
        System.out.print("Largeur (X) : ");
        int x = m_scanner.nextInt();
        System.out.print("Hauteur (Y) : ");
        int y = m_scanner.nextInt();

        Donjon donjon = new Donjon();
        donjon.setDiemensionMap(new int[]{x, y});

        ArrayList<Espece> lstEspece = new ArrayList<>();
        lstEspece.add(new Espece("Gobelin"));

        ajouterElements(donjon, lstEspece);
        return donjon;
    }

    /**
     * Rafraîchit l'affichage du donjon dans le terminal.
     * @param donjon le donjon à afficher
     */
    public static void clearTerminalDonjon(Donjon donjon) {
        donjon.genererDonjon();
        System.out.println(donjon.getDonjon());
    }

    // ===================== MÉTHODES PRIVÉES =====================

    /**
     * Permet de choisir une race pour un personnage.
     * @return la race choisie
     */
    private static Race choisirRace() {
        System.out.println("Races disponibles :");
        System.out.println("1. Humain\n2. Elfe\n3. Halfelin\n4. Nain");
        System.out.print("Choix : ");

        switch (m_scanner.nextInt()) {
            case 2: return new Elfe();
            case 3: return new Halfelin();
            case 4: return new Nain();
            default: return new Humain();
        }
    }

    /**
     * Permet de choisir une classe pour un personnage.
     * @return la classe choisie
     */
    private static Classe choisirClasse() {
        System.out.println("Classes disponibles :");
        System.out.println("1. Guerrier\n2. Mage\n3. Voleur\n4. Clerc");
        System.out.print("Choix : ");

        switch (m_scanner.nextInt()) {
            case 2: return new Magicien();
            case 3: return new Roublard();
            case 4: return new Clerc();
            default: return new Guerrier();
        }
    }

    /**
     * Permet à l’utilisateur d’ajouter des entités au donjon (monstre, équipement, obstacle).
     */
    private static void ajouterElements(Donjon donjon, ArrayList<Espece> lstEspece) {
        int choix;
        do {
            System.out.println("\nAjouter des éléments :");
            System.out.println("1. Monstre\n2. Equipement\n3. Obstacle\n4. Terminer");
            System.out.print("Choix : ");
            choix = m_scanner.nextInt();

            switch (choix) {
                case 1: donjon.addEntitee(mettreEnPlaceMonstre(donjon, lstEspece)); break;
                case 2: ajouterEquipement(donjon); break;
                case 3: donjon.addObstacles(mettreEnPlaceObstacle(donjon)); break;
            }
            clearTerminalDonjon(donjon);
        } while (choix != 4);
    }

    /**
     * Ajoute une arme ou une armure dans le donjon.
     */
    private static void ajouterEquipement(Donjon donjon) {
        System.out.println("\nType d'équipement :");
        System.out.println("1. Arme\n2. Armure");
        System.out.print("Choix : ");

        Equipement equip = (m_scanner.nextInt() == 1) ?
                mettreEnPlaceArme(donjon) : mettreEnPlaceArmure(donjon);

        if (equip != null) {
            donjon.addEquipement(equip);
        }
    }

    /**
     * Place les personnages dans le donjon à des positions valides.
     */
    private static Donjon placerPersonnages(Donjon donjon) {
        for (Personnage personnage : lstPersonnage) {
            boolean place = false;
            while (!place) {
                System.out.println("\nPlacer " + personnage.getNom());
                int[] coo = demanderCoordonnees();

                if (donjon.verfifierCoo(coo)) {
                    personnage.setCoo(coo);
                    donjon.addEntitee(personnage);
                    place = true;
                } else {
                    System.out.println("Emplacement invalide !");
                }
            }
        }
        return donjon;
    }

    /**
     * Demande des coordonnées à l’utilisateur.
     * @return tableau contenant X et Y
     */
    private static int[] demanderCoordonnees() {
        System.out.print("X : ");
        int x = m_scanner.nextInt();
        System.out.print("Y : ");
        int y = m_scanner.nextInt();
        return new int[]{x, y};
    }

    /**
     * Crée un nouvel obstacle et vérifie la validité de sa position.
     */
    public static int[] mettreEnPlaceObstacle(Donjon donjon) {
        System.out.println("\nNouvel obstacle");
        System.out.println("\nPosition de l'obstacle : ");
        int[] coo = demanderCoordonnees();
        return donjon.verfifierCoo(coo) ? coo : mettreEnPlaceObstacle(donjon);
    }

    /**
     * Crée une arme à placer dans le donjon selon un choix utilisateur.
     */
    public static Equipement mettreEnPlaceArme(Donjon donjon) {
        System.out.println("\nTypes d'armes :");
        System.out.println("1. Bâton (1d6, 1 case)");
        System.out.println("2. Masse d'armes (1d6, 1 case)");
        System.out.println("3. Épée longue (1d8, 1 case)");
        System.out.println("4. Rapière (1d8, 1 case)");
        System.out.println("5. Arbalète légère (1d8, 16 cases)");
        System.out.println("6. Fronde (1d4, 6 cases)");
        System.out.println("7. Arc court (1d6, 16 cases)");
        System.out.print("Choix : ");

        Arme arme = switch (m_scanner.nextInt()) {
            case 2 -> new Arme("Masse d'armes", null, Arme.Type.COURANTE, new int[]{1,6}, 1);
            case 3 -> new Arme("Épée longue", null, Arme.Type.GUERRE, new int[]{1,8}, 1);
            case 4 -> new Arme("Rapière", null, Arme.Type.GUERRE, new int[]{1,8}, 1);
            case 5 -> new Arme("Arbalète légère", null, Arme.Type.DISTANCE, new int[]{1,8}, 16);
            case 6 -> new Arme("Fronde", null, Arme.Type.DISTANCE, new int[]{1,4}, 6);
            case 7 -> new Arme("Arc court", null, Arme.Type.DISTANCE, new int[]{1,6}, 16);
            default -> new Arme("Bâton", null, Arme.Type.COURANTE, new int[]{1,6}, 1);
        };

        System.out.println("Position de l'arme :");
        int[] coo = demanderCoordonnees();

        if (donjon.verfifierCoo(coo)) {
            arme.setCoo(coo);
            return arme;
        }
        return null;
    }

    /**
     * Crée une armure à placer dans le donjon selon un choix utilisateur.
     */
    public static Equipement mettreEnPlaceArmure(Donjon donjon) {
        System.out.println("\nTypes d'armures :");
        System.out.println("1. Armure d'écailles (CA 9)");
        System.out.println("2. Demi-plate (CA 10)");
        System.out.println("3. Cotte de mailles (CA 11)");
        System.out.println("4. Harnois (CA 12)");
        System.out.print("Choix : ");

        Armure armure = switch (m_scanner.nextInt()) {
            case 1 -> new Armure("Armure d'écailles", null, Armure.Type.LEGERE, 9);
            case 2 -> new Armure("Demi-plate", null, Armure.Type.LEGERE, 10);
            case 4 -> new Armure("Harnois", null, Armure.Type.LOURDE, 12);
            default -> new Armure("Cotte de mailles", null, Armure.Type.LOURDE, 11);
        };

        System.out.println("Position de l'armure :");
        int[] coo = demanderCoordonnees();

        if (donjon.verfifierCoo(coo)) {
            armure.setCoo(coo);
            return armure;
        }
        return null;
    }

    /**
     * Crée un monstre et demande ses caractéristiques à l’utilisateur.
     */
    public static Entite mettreEnPlaceMonstre(Donjon donjon, ArrayList<Espece> lstEspece) {
        m_scanner.nextLine();
        System.out.println("\nNouveau monstre");
        System.out.print("Nom : ");
        String nom = m_scanner.nextLine();

        Espece espece = choisirEspece(lstEspece);
        int[] stats = demanderStats();

        System.out.println("\nPosition du monstre : ");
        int[] coo = demanderCoordonnees();

        if (donjon.verfifierCoo(coo)) {
            return new Monstre(nom, coo, espece, stats[0], stats);
        }
        return null;
    }

    /**
     * Demande les statistiques d’un monstre à l’utilisateur.
     * @return tableau contenant toutes les caractéristiques
     */
    private static int[] demanderStats() {
        System.out.println("\nCaractéristiques :");
        System.out.print("Classe d'armure : ");
        int ca = m_scanner.nextInt();
        System.out.print("Vie : ");
        int vie = m_scanner.nextInt();
        System.out.print("Vitesse : ");
        int vitesse = m_scanner.nextInt();
        System.out.print("Force : ");
        int force = m_scanner.nextInt();
        System.out.print("Dextérité : ");
        int dex = m_scanner.nextInt();
        System.out.print("Initiative : ");
        int init = m_scanner.nextInt();

        return new int[]{vie, vitesse, force, dex, init, ca};
    }

    /**
     * Permet de créer une nouvelle espèce de monstre.
     * @return l’espèce créée
     */
    public static Espece mettreEnPlaceEspece(ArrayList<Espece> lstEspece) {
        m_scanner.nextLine();
        System.out.print("Nom de l'espèce : ");
        String nom = m_scanner.nextLine();
        Espece e = new Espece(nom);
        lstEspece.add(e);
        return e;
    }

    /**
     * Permet de choisir une espèce existante dans la liste.
     * @return espèce sélectionnée
     */
    public static Espece choisirParmisEspece(ArrayList<Espece> lstEspece) {
        System.out.println("\nEspèces disponibles :");
        for (int i = 0; i < lstEspece.size(); i++) {
            System.out.println((i + 1) + ". " + lstEspece.get(i).getNom());
        }
        System.out.print("Choix : ");
        int choix = m_scanner.nextInt() - 1;
        return lstEspece.get(choix >= 0 && choix < lstEspece.size() ? choix : 0);
    }

    /**
     * Menu de sélection ou création d’espèce de monstre.
     */
    private static Espece choisirEspece(ArrayList<Espece> lstEspece) {
        System.out.println("\nType de monstre :");
        System.out.println("1. Choisir existant\n2. Créer nouvelle espèce");
        System.out.print("Choix : ");

        return (m_scanner.nextInt() == 1) ?
                choisirParmisEspece(lstEspece) : mettreEnPlaceEspece(lstEspece);
    }
}