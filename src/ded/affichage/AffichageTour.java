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

public class AffichageTour {
    private static final Scanner m_scanner = new Scanner(System.in);

    public static void jeuDonjon(Donjon donjon) {
        System.out.println("Le jeu peut commencer !");

        while (!donjon.isFinDonjon()) {
            System.out.println("\n--- Nouveau tour ---");

            for (Entite entite : donjon.getOrdreEntite()) {
                if (entite.getVie() <= 0) {
                    System.out.println(entite.getNom() + " est mort et ne joue pas");
                    continue;
                }

                AffichageMiseEnPlace.clearTerminalDonjon(donjon);
                affichageStats(entite);

                String actions = "Choisissez parmi ces actions:\n1. Se déplacer\n2. Attaquer";
                boolean estMagicien = false;
                boolean estClerc = false;

                if (entite instanceof Personnage personnage) {
                    affichageInventaire(personnage);

                    if (personnage.getClasse() instanceof Magicien) {
                        estMagicien = true;
                        actions += "\n3. Guérison\n4. Boogie Woogie\n5. Arme Magique";
                    } else if (personnage.getClasse() instanceof Clerc) {
                        estClerc = true;
                        actions += "\n3. Guérison";
                    }
                }

                for (int action = 1; action <= 3 && !donjon.isFinDonjon(); action++) {
                    System.out.println("\nAction " + action + "/3");
                    System.out.println(actions);

                    int choix = obtenirChoixUtilisateur(1, estMagicien ? 5 : estClerc ? 3 : 2);

                    switch (choix) {
                        case 1:
                            deplacer(donjon, entite);
                            break;
                        case 2:
                            attaquer(donjon, entite);
                            break;
                        case 3:
                            if (estClerc || estMagicien) {
                                guerison(donjon, entite);
                            }
                            break;
                        case 4:
                            if (estMagicien) {
                                boogieWoogie(donjon);
                            }
                            break;
                        case 5:
                            if (estMagicien) {
                                armeMagique(donjon, entite);
                            }
                            break;
                    }

                    if (donjon.isFinDonjon()) break;
                }

                if (donjon.isFinDonjon()) break;
            }
        }
        System.out.println("\nLe donjon est terminé !");
    }

    private static int obtenirChoixUtilisateur(int min, int max) {
        while (true) {
            try {
                System.out.print("Votre choix: ");
                int choix = m_scanner.nextInt();
                m_scanner.nextLine(); // Vider le buffer

                if (choix >= min && choix <= max) {
                    return choix;
                }
                System.out.println("Choix invalide. Veuillez entrer un nombre entre " + min + " et " + max);
            } catch (Exception e) {
                m_scanner.nextLine(); // En cas d'erreur de saisie
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }

    public static void affichageStats(Entite entite) {
        System.out.println("C'est au tour de " + entite.getNom());
        System.out.println("Position: [" + entite.getCoo()[0] + "," + entite.getCoo()[1] + "]");
        System.out.println("PV: " + entite.getVie() + ", Initiative: " + entite.getInitiative());
        System.out.println("Force: " + entite.getForce() + ", Dexterite: " + entite.getDexterite());
        System.out.println("Vitesse: " + entite.getVitesse() + ", Portée: " + entite.getPortee());
    }

    public static void affichageInventaire(Personnage personnage) {
        System.out.println("\n--- Equipement ---");
        Armure armure = personnage.getArmureEquipe();
        Arme arme = personnage.getArmeEquipe();

        System.out.println("Armure: " + armure.getNom() + " (CA: " + armure.getClasse() + ")");
        System.out.println("Arme: " + arme.getNom() + " (Dégâts: " + arme.getDegat()[0] + "d" +
                arme.getDegat()[1] + ", Portée: " + arme.getPortee() + ")");

        System.out.println("\nInventaire:");
        int index = 0;
        for (Equipement equipement : personnage.getInventaire()) {
            System.out.println(index++ + ": " + equipement.getNom());
        }
    }

    public static void attaquer(Donjon donjon, Entite entite) {
        while (true) {
            System.out.println("\nChoisir une case à attaquer (X Y):");
            int[] coo = obtenirCoordonnees();
            Entite cible = donjon.getCase(coo);

            if (cible == null) {
                System.out.println("Aucune entité à ces coordonnées.");
                if (!demanderContinuer()) return;
                continue;
            }

            if (!donjon.touche(entite, cible, entite.getPortee())) {
                System.out.println("La cible est hors de portée.");
                if (!demanderContinuer()) return;
                continue;
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

            System.out.println(entite.getNom() + " attaque " + cible.getNom() + " avec " + attaque + " points d'attaque.");

            if (cible.setAttaque(attaque)) {
                cible.setDegat(degats);
                System.out.println("Attaque réussie! " + degats + " points de dégâts infligés.");
                System.out.println(cible.getNom() + " a maintenant " + cible.getVie() + " PV.");
            } else {
                System.out.println("Attaque échouée! L'armure a résisté.");
            }

            break;
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

    private static int demanderNombre(String message) {
        while (true) {
            try {
                System.out.print(message);
                int nombre = m_scanner.nextInt();
                m_scanner.nextLine();
                return nombre;
            } catch (Exception e) {
                m_scanner.nextLine();
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }

    private static boolean demanderContinuer() {
        System.out.println("Voulez-vous réessayer ? (o/n)");
        String reponse = m_scanner.nextLine().toLowerCase();
        return reponse.equals("o");
    }

    public static void armeMagique(Donjon donjon, Entite entite) {
        System.out.println("\nChoisissez la case du personnage (X Y):");
        int[] coo = obtenirCoordonnees();
        Entite cible = donjon.getCase(coo);

        if (!(cible instanceof Personnage personnage)) {
            System.out.println("Aucun personnage à ces coordonnées.");
            return;
        }

        List<Arme> armes = new ArrayList<>();
        if (personnage.getArmeEquipe() != null) {
            armes.add(personnage.getArmeEquipe());
        }

        for (Equipement equipement : personnage.getInventaire()) {
            if (equipement instanceof Arme arme) {
                armes.add(arme);
            }
        }

        if (armes.isEmpty()) {
            System.out.println("Ce personnage n'a aucune arme!");
            return;
        }

        System.out.println("Choisissez une arme à rendre magique:");
        for (int i = 0; i < armes.size(); i++) {
            System.out.println(i + ": " + armes.get(i).getNom());
        }

        int choix = obtenirChoixUtilisateur(0, armes.size() - 1);
        armes.get(choix).addBonusMagique();
        System.out.println(armes.get(choix).getNom() + " est maintenant magique!");
    }

    public static void boogieWoogie(Donjon donjon) {
        System.out.println("\nEchange de places entre deux entités");

        System.out.println("Première entité (X Y):");
        int[] coo1 = obtenirCoordonnees();
        Entite entite1 = donjon.getCase(coo1);

        if (entite1 == null) {
            System.out.println("Aucune entité à ces coordonnées.");
            return;
        }

        System.out.println("Deuxième entité (X Y):");
        int[] coo2 = obtenirCoordonnees();
        Entite entite2 = donjon.getCase(coo2);

        if (entite2 == null) {
            System.out.println("Aucune entité à ces coordonnées.");
            return;
        }

        int[] temp = entite1.getCoo();
        entite1.setCoo(entite2.getCoo());
        entite2.setCoo(temp);

        System.out.println(entite1.getNom() + " et " + entite2.getNom() + " ont échangé leurs places!");
    }

    public static void guerison(Donjon donjon, Entite entite) {
        System.out.println("\nChoisissez la case du personnage à guérir (X Y):");
        int[] coo = obtenirCoordonnees();
        Entite cible = donjon.getCase(coo);

        if (!(cible instanceof Personnage personnage)) {
            System.out.println("Aucun personnage à ces coordonnées.");
            return;
        }

        int pv = Des.lancer(10);
        personnage.guerison(pv);
        System.out.println(personnage.getNom() + " a récupéré " + pv + " PV.");
        System.out.println("PV actuels: " + personnage.getVie());
    }

    public static void deplacer(Donjon donjon, Entite entite) {
        while (true) {
            System.out.println("\nDéplacement de " + entite.getNom());
            System.out.print("Distance (0-" + entite.getVitesse() + "): ");
            int distance = obtenirChoixUtilisateur(0, entite.getVitesse());

            System.out.println("Direction:");
            System.out.println("1. Haut");
            System.out.println("2. Bas");
            System.out.println("3. Gauche");
            System.out.println("4. Droite");

            int direction = obtenirChoixUtilisateur(1, 4);
            int[] vecteur = switch (direction) {
                case 1 -> new int[]{0, 1};
                case 2 -> new int[]{0, -1};
                case 3 -> new int[]{-1, 0};
                case 4 -> new int[]{1, 0};
                default -> new int[]{0, 0};
            };

            if (entite.deplacerDirection(vecteur, distance)) {
                System.out.println(entite.getNom() + " s'est déplacé à [" +
                        entite.getCoo()[0] + "," + entite.getCoo()[1] + "]");
                break;
            } else {
                System.out.println("Déplacement impossible!");
                if (!demanderContinuer()) break;
            }
        }
    }

    private static int[] obtenirCoordonnees() {
        int x = demanderNombre("X: ");
        int y = demanderNombre("Y: ");
        return new int[]{x, y};
    }
}