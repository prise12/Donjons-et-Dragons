package ded;

import ded.donjon.Donjon;
import ded.entite.Entite;
import ded.entite.Monstre;
import ded.entite.Personnage;
import ded.entite.classe.Clerc;
import ded.entite.classe.Magicien;
import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Tour {
    private static final Scanner m_scanner = new Scanner(System.in);


    public static void jeuDonjon(Donjon donjon) {
        System.out.println("Le jeu peut commencer !");
        boolean finDonjon = false;
        while (!finDonjon) {
            System.out.println("Bienvenue dans le Donjon");
            for (Entite entite : donjon.getOrdreEntite()) {
                Affichage.clearTerminalDonjon(donjon);
                System.out.println("C'est au tour de " + entite.getNom());
                System.out.println("Initative : " + entite.getInitiative());
                System.out.println("Vie : " + entite.getVie());
                System.out.println("Force : " + entite.getForce());
                System.out.println("Dexterite : " + entite.getDexterite());
                System.out.println("Vitesse : " + entite.getVitesse());

                String actions = "Choisisez Parmis ces actions\n1. se deplacer\n2. attaquer";
                boolean estMagicien = false;
                boolean estClerc = false;

                if (entite instanceof Personnage personnage) {
                    Armure armure = personnage.getArmureEquipe();
                    Arme arme = personnage.getArmeEquipe();
                    System.out.println("Armure : " + armure.getNom() + "(Classe d'armure: " + armure.getClasse() + ")");
                    System.out.println("Arme : " + arme.getNom() + "(Degat: " + arme.getDegat()[0] + "d" + arme.getDegat()[1] + ", Portee: " + arme.getPortee() + ")");
                    System.out.print("Inventaire : ");
                    int i = 0;
                    for (Equipement equipement : personnage.getInventaire()) {
                        System.out.print(" [" + i + "] " + equipement.getNom());
                    }
                    System.out.println();

                    if (personnage.getClasse() instanceof Magicien magicien) {
                        estMagicien = true;
                        actions += "\n3. Guerison\n4. Boogie Woogie\n5. Arme Magique";
                    } else if (personnage.getClasse() instanceof Clerc clerc) {
                        estClerc = true;
                        actions += "\n3. Guerison";
                    }
                }
                for (int i = 1; i <= 3; i++) {
                    System.out.println("Il vous reste"  + (3 - (i-1)) +" action." );

                    System.out.println(actions);
                    int choix = Tour.m_scanner.nextInt();
                    switch (choix) {
                        case 2:
                            Tour.attaquer(donjon, entite);
                            break;
                        case 1:
                            Tour.deplacer(donjon, entite);
                            break;
                        case 3:
                            if (estClerc) {
                                Tour.guerison(donjon, entite);
                            } else if (estMagicien) {
                                Tour.guerison(donjon, entite);
                            }
                        case 4:
                            if (estMagicien) {
                                Tour.boogieWoogie(donjon);
                                break;
                            } else {
                                System.out.println("Aucune attaque selectioner, action sauter.");
                            }
                        case 5:
                            if (estMagicien) {
                                Tour.armeMagique(donjon, entite);
                                break;
                            } else {
                                System.out.println("Aucune attaque selectioner, action sauter.");
                            }
                        default:
                            System.out.println("Aucune attaque selectioner, action sauter.");
                    }
                    finDonjon = donjon.isFinDonjon();
                }
            }
        }

    }
    public static void attaquer(Donjon donjon, Entite entite) {
        System.out.println("Choisir une case à attaquer.");
        System.out.println("X :");
        int coo1 = Tour.m_scanner.nextInt();
        System.out.println("Y :");
        int coo2 = Tour.m_scanner.nextInt();
        Tour.m_scanner.nextLine();
        int attaque = 0;
        int degat = 0;
        Entite entite2 = donjon.getCase(new int[]{coo1,coo2});

        if(entite2 == null) {
            System.out.println("Les coordonées n'appartienent à aucune entité.");
            attaquer(donjon, entite);
        } else if (donjon.touche(entite, entite2, entite.getPortee())) {
            if (entite instanceof Personnage personnage) {
                attaque = personnage.getAttaque();
                degat = personnage.getDegat();

            } else if (entite instanceof Monstre monstre) {
                boolean flag = true;
                int portee = 0;
                while (flag){
                    System.out.println("Attaque au coprs à corps ? (o/n)");
                    String cac = Tour.m_scanner.nextLine();
                    if(Objects.equals(cac, "o")){
                        flag = false;
                        System.out.println("Choisir portee.");
                        portee = Tour.m_scanner.nextInt();
                    } else if (Objects.equals(cac, "n")) {
                        flag = false;
                        portee = 1;
                    }
                }

                System.out.println("Choisir degat.");
                System.out.println("Nombre de des");
                int des = Tour.m_scanner.nextInt();
                System.out.println("Nombre de faces:");
                int faces = Tour.m_scanner.nextInt();
                attaque = monstre.getAttaque(portee,new int[]{des,faces});
                degat = monstre.getDegat(new int[]{des,faces});
            }
            System.out.println(entite.getNom() + " inflige " + attaque + " de points d'attaque à " + entite2.getNom());
            if(entite2.setAttaque(attaque)) {
                System.out.println(entite.getNom() + " pourfend l'armure (" + entite2.getClasseArmure() + ") de" + entite2.getNom() +" !");

                entite2.setDegat(degat) ;
                System.out.println(entite.getNom() + " inflige " + degat + " de points de degats à " + entite2.getNom());
            }
            else {
                System.out.println(entite.getNom() + " n'atteint pas" + entite2.getNom() +" !");
            }

        } else{
            System.out.println(entite.getNom() + " n'as soit pas assez de portee pour son attaque ou soit une attaque visant un de ses alie.");
            attaquer(donjon, entite);
        }
    }

    public static void armeMagique(Donjon donjon,Entite entite) {
        System.out.println("Choisissez la case du Personnage possedant l'arme que vous voulez rendre magique.");
        System.out.println("X :");
        int coo1 = Tour.m_scanner.nextInt();
        System.out.println("Y :");
        int coo2 = Tour.m_scanner.nextInt();
        Tour.m_scanner.nextLine();
        Entite entite2 = donjon.getCase(new int[]{coo1,coo2});
        if(entite2 != null && entite2 instanceof Personnage personnage) {
            System.out.println("Choisissez parmis ses armes.");
            int i = 0;
            boolean equipeArme = false;
            if(personnage.getArmeEquipe() != null){
                i = 1;
                System.out.println("0: " + personnage.getArmeEquipe().getNom());
                equipeArme = true;
            }
            for(Equipement equipement : personnage.getInventaire()){
                if(equipement instanceof Arme arme) {
                    System.out.println(i + ": " + personnage.getArmeEquipe().getNom());
                    i++;
                }
            }
            int index = Tour.m_scanner.nextInt();
            if(equipeArme){
                if(index == 0){
                    personnage.getArmeEquipe().addBonusMagique();
                }
                else{
                    int j = 0;
                    for(Equipement equipement : personnage.getInventaire() ){
                        if(equipement instanceof Arme arme) {
                            if(j == index -1){
                                personnage.getArmeEquipe().addBonusMagique();
                            }
                            j++;
                        }
                    }
                }
            }
            else{
                int j = 0;
                for(Equipement equipement : personnage.getInventaire() ){
                    if(equipement instanceof Arme arme) {
                        if(j == index ){
                            personnage.getArmeEquipe().addBonusMagique();
                        }
                        j++;
                    }
                }
            }
        }
        else {
            System.out.println("Aucun personnage sur cette case.");
        }

    }

    public static void boogieWoogie(Donjon donjon) {
        System.out.println("Choisissez la case de la première entite que vous voulez echangez.");
        System.out.println("X :");
        int coo11 = Tour.m_scanner.nextInt();
        System.out.println("Y :");
        int coo12 = Tour.m_scanner.nextInt();
        Tour.m_scanner.nextLine();
        Entite entite1 = donjon.getCase(new int[]{coo11,coo12});
        if(entite1 == null) {
            System.out.println("La case ne contient pas d'entite");
            boogieWoogie(donjon);
        }
        System.out.println("Choisissez la case de la deuxième entite que vous voulez echangez.");
        System.out.println("X :");
        int coo21 = Tour.m_scanner.nextInt();
        System.out.println("Y :");
        int coo22 = Tour.m_scanner.nextInt();
        Tour.m_scanner.nextLine();
        Entite entite2 = donjon.getCase(new int[]{coo11,coo12});
        if(entite2 == null) {
            System.out.println("La case ne contient pas d'entite");
            boogieWoogie(donjon);
        }
        System.out.println(entite1.getNom() + " et " + entite2.getNom() + " on echangez de place!");
        entite1.setCoo(new int[]{coo21,coo22});
        entite2.setCoo(new int[]{coo11,coo12});

    }

    public static void guerison(Donjon donjon, Entite entite) {
        System.out.println("Choisissez la case du personnage que vous voulez guerir.");
        System.out.println("X :");
        int coo11 = Tour.m_scanner.nextInt();
        System.out.println("Y :");
        int coo12 = Tour.m_scanner.nextInt();
        Tour.m_scanner.nextLine();
        Entite entite1 = donjon.getCase(new int[]{coo11,coo12});
        if(entite1 != null && entite1 instanceof Personnage personnage) {
            int pv = Des.lancer(10);
            personnage.guerison(pv);
            System.out.println("Vous guerisez " + personnage.getNom() + "de " + pv +" points de vie." );
        }
        else{
            System.out.println("La case ne contient pas de personnage.");
            guerison(donjon, entite);
        }
    }

    public static void deplacer(Donjon donjon, Entite entite) {
        System.out.println("Choisiser la distance de deplacement: ");
        int distance = Tour.m_scanner.nextInt();
        if(distance < 0){
            System.out.println("Distance positive !");
            deplacer(donjon, entite);
        }

        System.out.println("Choisiser la direction de deplacement: ");
        System.out.println("1. haut :");
        System.out.println("2. bas :");
        System.out.println("3. gauche :");
        System.out.println("4. droite :");
        int[] direction = null;
        switch (Tour.m_scanner.nextInt()) {
            case 1: direction = new int[]{0,1}; break;
            case 2: direction = new int[]{1,0}; break;
            case 3: direction = new int[]{0,-1}; break;
            case 4: direction = new int[]{-1,0}; break;
        }
        if(entite.deplacerDirection(direction, distance)){
            System.out.println(entite.getNom() +  " c'est deplacer à [" + entite.getCoo()[0] + "," +entite.getCoo()[1] + "]");
        }
        else{
            System.out.println(entite.getNom() + " n'as pas assez de vitesse !");
            deplacer(donjon, entite);
        }
    }

}

        /*
        Au début de chaque donjon, le maître du jeu

        crée la carte d'un donjon: un rectangle dont les dimensions sont comprises entre 15 et 25 cases
        positionne des obstacles sur la carte: cases où les joueurs et les monstres ne pourront pas se déplacer
        crée les monstres et choisit leurs caractéristiques
        positionne les joueurs et les monstres sur la carte (en-dehors des obstacles)
        positionne des équipements sur la carte que les joueurs pourront récupérer
        présente en quelques phrases le contexte aux joueurs

        Afin de faciliter la tâche au maître du jeu, un choix de mise en place du donjon par défaut devra être proposé pour chaque donjon.
        Chaque joueur et chaque monstre lance 1d20 et ajoute leur caractéristique d'initiative. Ils joueront par ordre décroissant du résultat d'initiative.
        Avant de débuter le donjon, chaque joueur peut équiper l'armure et l'arme de son choix.
                */


