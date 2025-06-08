package ded.affichage;

import ded.Des;
import ded.donjon.Donjon;
import ded.entite.Entite;
import ded.entite.Monstre;
import ded.entite.Personnage;
import ded.entite.classe.Clerc;
import ded.entite.classe.Magicien;
import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Classe gérant l'affichage et la logique d'un tour de jeu dans le donjon.
 * Permet aux entités d'effectuer des actions : déplacement, attaque, sorts, etc.
 */
public class AffichageTour {

    // ===================== ATTRIBUTS =====================
    private static final Scanner m_scanner = new Scanner(System.in);

    // ===================== MÉTHODE PRINCIPALE =====================

    /**
     * Lance et gère le déroulement du donjon en boucle jusqu’à sa fin.
     * @param donjon le donjon à explorer
     * @param nDonjon numéro du donjon
     */
    public static void afficherDonjon(Donjon donjon, int nDonjon) {
        System.out.println("Bienvenue dans le Donjon n°"+ nDonjon +" !");

        while (!donjon.isFinDonjon()) {
            System.out.println("\n--- Nouveau tour ---");

            for (Entite entite : donjon.getOrdreEntite()) {
                if (entite.getVie() <= 0) continue;

                boolean estMagicien = entite instanceof Personnage p && p.getClasse() instanceof Magicien;
                boolean estClerc = entite instanceof Personnage p && p.getClasse() instanceof Clerc;

                clearTerminalDonjon(donjon);
                System.out.println("----------C'est au tour de " + entite.getNom()+"----------");
                String commentaireActionPrecedente = "";
                affichageStats(entite);

                for (int action = 1; action <= 3 && !donjon.isFinDonjon(); action++) {
                    System.out.println("----" + entite.getNom() + "----");
                    System.out.println("\nAction " + action + "/3");

                    afficherAction(entite);


                    int choix = obtenirChoixUtilisateur(0, estMagicien ? 8 : estClerc ? 6 : entite instanceof Personnage ? 5 : 3);
                    switch (choix) {
                        case 0 -> {}
                        case 1 -> afficherAcionPrecedente(commentaireActionPrecedente);
                        case 2 -> commentaireActionPrecedente = deplacer(donjon, entite);
                        case 3 -> commentaireActionPrecedente = attaquer(donjon, entite);
                        case 4 -> {
                            if (entite instanceof Personnage personnage)
                                commentaireActionPrecedente = equiper(personnage);
                        }
                        case 5 -> {
                            if (entite instanceof Personnage personnage)
                                commentaireActionPrecedente = recuperer(donjon, personnage);
                        }
                        case 6 -> {
                            if (estClerc || estMagicien)
                                commentaireActionPrecedente = guerison(donjon, entite);
                        }
                        case 7 -> {
                            if (estMagicien)
                                commentaireActionPrecedente = boogieWoogie(donjon);
                        }
                        case 8 -> {
                            if (estMagicien)
                                commentaireActionPrecedente = armeMagique(donjon, entite);
                        }
                    }

                    System.out.println("----------C'est au tour du MJ----------");
                    actionMj(donjon, entite);
                    if (donjon.isFinDonjon()) break;
                }

                donjon.supprimerMonstre();
                if (donjon.isFinDonjon()) break;
            }
        }

        System.out.println("\nLe donjon est terminé !");
    }

    // ===================== MÉTHODES D’ACTION POUR LES JOUEURS =====================

    /**
     * Affiche la dernière action effectuée.
     */
    public static void afficherAcionPrecedente(String commentaireActionPrecedente) {
        System.out.println("Voici le commentaire de l'action précédente : << "+ commentaireActionPrecedente +" >>");
    }

    /**
     * Affiche les options d'action disponibles pour une entité.
     */
    public static void afficherAction(Entite entite) {
        String actions = "Choisissez parmi ces actions:\n0. Passer action\n1. Commentaire de l'action précédente\n2. Se déplacer\n3. Attaquer";

        if (entite instanceof Personnage personnage) {
            affichageInventaire(personnage);
            actions += "\n4. Equiper équipement\n5. Recuperer équipement";
            if (personnage.getClasse() instanceof Magicien) {
                actions += "\n6. Guérison\n7. Boogie Woogie\n8. Arme Magique";
            } else if (personnage.getClasse() instanceof Clerc) {
                actions += "\n6. Guérison";
            }
        }
        System.out.println(actions);
    }

    /**
     * Permet à une entité d’attaquer une cible.
     */
    public static String attaquer(Donjon donjon, Entite entite) {
        while (true) {
            System.out.println("\nChoisir une case à attaquer (X Y):");
            int[] coo = obtenirCoordonnees();
            Entite cible = donjon.getCaseEntite(coo);
            String commentaireAction = "";

            if (cible == null) {
                afficherErreur("Aucune entité à ces coordonnées.");
                return commentaireAction;
            }

            if (!donjon.touche(entite.getPortee(), entite.getCoo(), cible.getCoo())){
                System.out.println("La cible est hors de portée.");
                return commentaireAction;
            }
            if (entite.getClass() == cible.getClass()){
                System.out.println("La cible est un alié.");
                return commentaireAction;
            }

            int attaque = 0;
            int degats = 0;

            if (entite instanceof Personnage personnage) {
                attaque = personnage.getAttaque();
                degats = personnage.getDegat();
            } else if (entite instanceof Monstre monstre) {
                int portee = demanderPorteeAttaque();
                int[] des = demanderDesDegats();
                attaque = monstre.getAttaque(portee, des);
                degats = monstre.getDegat(des);
            }

            commentaireAction += entite.getNom() + " attaque " + cible.getNom() + " avec " + attaque + " points d'attaque.";

            if (cible.setAttaque(attaque)) {
                cible.setDegat(degats);
                commentaireAction += "Attaque réussie! " + degats + " points de dégâts infligés.";
                commentaireAction += cible.getNom() + " a maintenant " + cible.getVie() + " PV.";
            } else {
                commentaireAction += "Attaque échouée! L'armure a résisté.";
            }

            System.out.println(commentaireAction);
            return commentaireAction;
        }
    }

    /**
     * Permet de déplacer une entité dans une direction donnée.
     */
    public static String deplacer(Donjon donjon, Entite entite) {
        String commentaireAction = "";
        while (true) {
            System.out.println("Direction:");
            System.out.println("1. Haut\n2. Bas\n3. Gauche\n4. Droite");
            int direction = obtenirChoixUtilisateur(1, 4);

            int[] vecteur = switch (direction) {
                case 1 -> new int[]{0, -1};
                case 2 -> new int[]{0, 1};
                case 3 -> new int[]{-1, 0};
                case 4 -> new int[]{1, 0};
                default -> new int[]{0, 0};
            };

            System.out.print("Distance (0-" + entite.getVitesse() + "): \n");
            int distance = obtenirChoixUtilisateur(0, entite.getVitesse());

            if (donjon.deplacerDirection(entite, vecteur, distance)) {
                commentaireAction = entite.getNom() + " s'est déplacé à [" +
                        entite.getCoo()[0] + "," + entite.getCoo()[1] + "]";
                System.out.println(commentaireAction);
                return commentaireAction;
            } else {
                afficherErreur("Déplacement impossible, soit la vitesse est insuffisante, soit la case est bloquée.");
            }
        }
    }

    // ===================== MÉTHODES LIÉES À L’INVENTAIRE =====================

    public static String equiper(Personnage personnage) {
        System.out.println("\nChoisissez l'equipement à equiper :");
        int index = 0;
        for (Equipement equipement : personnage.getInventaire()) {
            System.out.println(index++ + ": " + equipement.getNom());
        }
        int choix = obtenirChoixUtilisateur(0, index);
        Equipement equipement = personnage.getInventaire().get(index - 1);
        personnage.equiperEquipement(index - 1);
        String commentaireAction = personnage.getNom() + " est maintenant equipé de " + equipement.getNom();
        System.out.println(commentaireAction);
        return commentaireAction;
    }

    public static String recuperer(Donjon donjon, Personnage personnage) {
        System.out.println("\nChoisissez l'equipement à recuperer :");
        int[] coo = obtenirCoordonnees();
        Equipement equipement = donjon.getCaseEquipement(coo);
        String commentaireAction = "";

        if (equipement != null) {
            if (donjon.touche(1, personnage.getCoo(), equipement.getCoo())) {
                personnage.recupererEquipement(equipement);
                donjon.supprimerEquipement(equipement);
                commentaireAction = personnage.getNom() + " a récupéré " + equipement.getNom();
                System.out.println(commentaireAction);
            } else {
                afficherErreur("L'équipement est trop loin.");
            }
        } else {
            afficherErreur("Aucun équipement à cet emplacement.");
            return recuperer(donjon, personnage);
        }
        return commentaireAction;
    }

    // ===================== SORTS =====================

    public static String guerison(Donjon donjon, Entite entite) {
        System.out.println("\nChoisissez la case du personnage à guérir (X Y):");
        int[] coo = obtenirCoordonnees();
        Entite cible = donjon.getCaseEntite(coo);
        String commentaireAction = "";

        if (!(cible instanceof Personnage personnage)) {
            afficherErreur("Aucun personnage à ces coordonnées.");
            return commentaireAction;
        }

        int pv = Des.lancer(10);
        personnage.guerison(pv);
        commentaireAction = personnage.getNom() + " a récupéré " + pv + " PV.";
        commentaireAction += "PV actuels: " + personnage.getVie();
        System.out.println(commentaireAction);
        return commentaireAction;
    }

    public static String armeMagique(Donjon donjon, Entite entite) {
        System.out.println("\nChoisissez la case du personnage (X Y):");
        int[] coo = obtenirCoordonnees();
        Entite cible = donjon.getCaseEntite(coo);
        String commentaireAction = "";

        if (!(cible instanceof Personnage personnage)) {
            afficherErreur("Aucun personnage à ces coordonnées.");
            return commentaireAction;
        }

        List<Arme> armes = new ArrayList<>();
        if (personnage.getArmeEquipe() != null)
            armes.add(personnage.getArmeEquipe());

        for (Equipement equipement : personnage.getInventaire()) {
            if (equipement instanceof Arme arme)
                armes.add(arme);
        }

        if (armes.isEmpty()) {
            System.out.println("Ce personnage n'a aucune arme!");
            return commentaireAction;
        }

        System.out.println("Choisissez une arme à rendre magique:");
        for (int i = 0; i < armes.size(); i++) {
            System.out.println(i + ": " + armes.get(i).getNom());
        }

        int choix = obtenirChoixUtilisateur(0, armes.size() - 1);
        armes.get(choix).addBonusMagique();
        commentaireAction = armes.get(choix).getNom() + " est maintenant magique!";
        System.out.println(commentaireAction);
        return commentaireAction;
    }

    public static String boogieWoogie(Donjon donjon) {
        System.out.println("\nEchange de places entre deux entités");

        System.out.println("Première entité (X Y):");
        int[] coo1 = obtenirCoordonnees();
        Entite entite1 = donjon.getCaseEntite(coo1);

        System.out.println("Deuxième entité (X Y):");
        int[] coo2 = obtenirCoordonnees();
        Entite entite2 = donjon.getCaseEntite(coo2);

        String commentaireAction = "";
        if (entite1 == null || entite2 == null) {
            afficherErreur("Coordonnées invalides.");
            return commentaireAction;
        }

        int[] temp = entite1.getCoo();
        entite1.setCoo(entite2.getCoo());
        entite2.setCoo(temp);

        commentaireAction = entite1.getNom() + " et " + entite2.getNom() + " ont échangé leurs places!";
        System.out.println(commentaireAction);
        return commentaireAction;
    }

    // ===================== OUTILS MJ =====================

    public static void actionMj(Donjon donjon, Entite entite) {
        System.out.println("Actions MJ :\n1. Passer\n2. Déplacer\n3. Infliger dégâts\n4. Ajouter obstacle");
        int choix = obtenirChoixUtilisateur(1, 4);

        switch (choix) {
            case 2 -> mjDeplacerEntite(donjon, entite);
            case 3 -> mjInfligerDegat(entite);
            case 4 -> mjAjouterObstacle(donjon);
        }
    }

    public static void mjAjouterObstacle(Donjon donjon) {
        System.out.println("\nCase de l'obstacle (X Y):");
        int[] coo = obtenirCoordonnees();

        if (donjon.verfifierCoo(coo)) {
            donjon.addObstacles(coo);
            System.out.println("Obstacle ajouté en [" + coo[0] + "," + coo[1] + "]");
        } else {
            afficherErreur("Case déjà occupée.");
        }
    }

    public static void mjInfligerDegat(Entite entite) {
        System.out.println("Dégâts à infliger:");
        int[] degat = demanderDesDegats();
        int nbDegat = Des.lancerMulti(degat[0], degat[1]);
        entite.setDegat(nbDegat);
        System.out.println(entite.getNom() + " a subi " + nbDegat + " points de dégâts !");
    }

    public static void mjDeplacerEntite(Donjon donjon, Entite entite) {
        System.out.println("\nNouvelle position (X Y):");
        int[] coo = obtenirCoordonnees();

        if (donjon.verfifierCoo(coo)) {
            entite.setCoo(coo);
            System.out.println(entite.getNom() + " déplacé en [" + coo[0] + "," + coo[1] + "]");
        } else {
            afficherErreur("Case déjà occupée.");
        }
    }

    // ===================== UTILITAIRES =====================

    private static int obtenirChoixUtilisateur(int min, int max) {
        while (true) {
            try {
                System.out.print("Votre choix: ");
                int choix = m_scanner.nextInt();
                m_scanner.nextLine();
                if (choix >= min && choix <= max) return choix;
                System.out.println("Choix invalide.");
            } catch (Exception e) {
                m_scanner.nextLine();
                afficherErreur("Veuillez entrer un nombre valide.");
            }
        }
    }

    private static int[] obtenirCoordonnees() {
        int x = demanderNombre("X: ");
        int y = demanderNombre("Y: ");
        return new int[]{x, y};
    }

    private static int demanderNombre(String message) {
        while (true) {
            try {
                System.out.print(message);
                int n = m_scanner.nextInt();
                m_scanner.nextLine();
                return n;
            } catch (Exception e) {
                m_scanner.nextLine();
                afficherErreur("Veuillez entrer un nombre valide.");
            }
        }
    }

    private static int demanderPorteeAttaque() {
        System.out.println("Attaque au corps à corps ? (o/n)");
        String reponse = m_scanner.nextLine().toLowerCase();
        return reponse.equals("o") ? 1 : demanderNombre("Portée de l'attaque: ");
    }

    private static int[] demanderDesDegats() {
        int des = demanderNombre("Nombre de dés: ");
        int faces = demanderNombre("Nombre de faces: ");
        return new int[]{des, faces};
    }

    public static void affichageStats(Entite entite) {
        System.out.println("Position: [" + entite.getCoo()[0] + "," + entite.getCoo()[1] + "]");
        System.out.println("PV: " + entite.getVie() + ", Initiative: " + entite.getInitiative());
        System.out.println("Force: " + entite.getForce() + ", Dexterite: " + entite.getDexterite());
        System.out.println("Vitesse: " + entite.getVitesse() + ", Portée: " + entite.getPortee());
    }

    public static void affichageInventaire(Personnage personnage) {
        System.out.println("\n--- Equipement ---");
        Armure armure = personnage.getArmureEquipe();
        Arme arme = personnage.getArmeEquipe();

        System.out.println("Armure: " + (!(armure == null) ? armure.getNom() : " ") +
                " (CA: " + (!(armure == null) ? armure.getClasse() : " ") + ")");
        System.out.println("Arme: " + (!(arme == null) ? arme.getNom() : " ") +
                " (Dégâts: " + (!(arme == null) ? arme.getDegat()[0] : " ") + "d" +
                (!(arme == null) ? arme.getDegat()[1] : " ") +
                ", Portée: " + (!(arme == null) ? arme.getPortee() : " ") + ")");

        System.out.println("\nInventaire:");
        int index = 0;
        for (Equipement equipement : personnage.getInventaire()) {
            System.out.println(index++ + ": " + equipement.getNom());
        }
        System.out.println();
    }

    public static void afficherErreur(String message) {
        String rouge = "\u001B[0m";
        System.out.println(rouge + message);
    }

    public static void clearTerminalDonjon(Donjon donjon) {
        donjon.genererDonjon();
        System.out.println(donjon.getDonjon());
    }
}
