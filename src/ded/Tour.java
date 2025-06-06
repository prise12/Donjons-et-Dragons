package ded;

import ded.donjon.Donjon;
import ded.entite.Entite;
import ded.entite.Personnage;

import java.util.Scanner;
import java.util.ArrayList;

public class Tour {
    Scanner scanner = new Scanner(System.in);


    public static void jeu(Donjon donjon){
        System.out.println("Le jeu peut commencer !");
        for(int i = 1; i <=3 ; i++){
            System.out.println("Bienvenue dans le Donjon");
            for(Entite entite : donjon.ordreLstEntite()){
                Affichage.clearTerminalDonjon(donjon);
                System.out.println("C'est au tour de " + entite.getNom() + " !");
                if(entite.equals(Personnage.class)){




                }
                else{



                }
            }
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
        /*int init = 0;
        for(int j = 0; j < personnages.size; j++){
            init = personnages.get(j).getinitiative;
            init += Des.lancer(20);


