package ded;
import ded.entite.*;
import ded.entite.race.*;
import ded.entite.classe.*;
import ded.objet.*;
import ded.*;
import ded.Mise_en_place;
import jdk.internal.jrtfs.JrtFileAttributeView;

import java.util.ArrayList;
import java.util.Scanner;

public class Tour {
    Scanner scanner = new Scanner(System.in);
    System.out.println("");

    for(int i = 0; i < 3; i++)
    {
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
        int init = 0;
        for(int j = 0; j < personnages.size; j++){
            init = personnages.get(j).getinitiative;
            init += Des.lancer(20);


        }




    }






}
