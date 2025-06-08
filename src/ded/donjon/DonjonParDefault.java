package ded.donjon;

import ded.entite.Entite;
import ded.entite.Espece;
import ded.entite.Monstre;
import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe fournissant des donjons prédéfinis que le MJ peut choisir
 */
public class DonjonParDefault {

    // ===================== METHODES PUBLIQUES =====================

    /**
     * Crée et retourne le premier donjon prédéfini
     * @return Donjon avec gobelins et orcs, équipements variés et obstacles linéaires
     */
    public static Donjon getDonjonParDefault1() {
        // Configuration de base
        int[] dimension = new int[]{20,20};

        // Création des espèces
        Espece orc = new Espece("orc");
        Espece gobelin = new Espece("gobelin");

        // Création des monstres
        Entite gobelin1 = new Monstre("gobelin1", new int[]{10,12}, gobelin, 7, new int[]{10, 6, 6, 3, 4});
        Entite gobelin2 = new Monstre("gobelin2", new int[]{11,12}, gobelin, 7, new int[]{9, 4, 5, 2, 5});
        Entite orc1 = new Monstre("orc1", new int[]{15,18}, orc, 9, new int[]{12, 12, 4, 6, 6});
        Entite orc2 = new Monstre("orc2", new int[]{15,17}, orc, 9, new int[]{11, 11, 5, 7, 5});

        // Liste des monstres
        ArrayList<Entite> lstMonstre = new ArrayList<>(Arrays.asList(gobelin1, gobelin2, orc1, orc2));

        // Configuration des obstacles
        ArrayList<int[]> lstObstacle = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            lstObstacle.add(new int[]{i,5});
        }

        // Configuration des équipements
        Armure armure1 = new Armure("Armure d'ecaille", new int[]{14,14}, Armure.Type.LEGERE, 9);
        Armure armure2 = new Armure("Cotte de mailles", new int[]{10,10}, Armure.Type.LOURDE, 11);
        Arme arme1 = new Arme("Masse d'armes", new int[]{8,8}, Arme.Type.COURANTE, new int[]{1,6}, 1);
        Arme arme2 = new Arme("Arbalete legere", new int[]{8,7}, Arme.Type.DISTANCE, new int[]{1,8}, 16);
        Arme arme3 = new Arme("Rapière", new int[]{7,6}, Arme.Type.GUERRE, new int[]{1,8}, 1);
        Arme arme4 = new Arme("Arc court", new int[]{7,7}, Arme.Type.DISTANCE, new int[]{1,6}, 16);

        ArrayList<Equipement> lstEquipement = new ArrayList<>(Arrays.asList(arme1, arme2, arme3, arme4, armure1, armure2));

        return new Donjon(lstMonstre, lstEquipement, lstObstacle, dimension);
    }

    /**
     * Crée et retourne le deuxième donjon prédéfini
     * @return Donjon avec gobelins, orcs et troll, équipements variés et obstacles verticaux
     */
    public static Donjon getDonjonParDefault2() {
        // Configuration de base
        int[] dimension = new int[]{25,25};

        // Création des espèces
        Espece orc = new Espece("orc");
        Espece gobelin = new Espece("gobelin");
        Espece troll = new Espece("troll");

        // Création des monstres
        Entite gobelin1 = new Monstre("gobelin1", new int[]{10,12}, gobelin, 7, new int[]{10, 6, 6, 3, 4});
        Entite orc1 = new Monstre("orc1", new int[]{15,18}, orc, 9, new int[]{12, 12, 4, 6, 6});
        Entite orc2 = new Monstre("orc2", new int[]{15,17}, orc, 9, new int[]{11, 11, 5, 7, 5});
        Entite troll1 = new Monstre("troll1", new int[]{24,24}, troll, 9, new int[]{15, 15, 8, 10, 2});

        // Liste des monstres
        ArrayList<Entite> lstMonstre = new ArrayList<>(Arrays.asList(gobelin1, orc1, orc2, troll1));

        // Configuration des obstacles
        ArrayList<int[]> lstObstacle = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            lstObstacle.add(new int[]{4,i});
        }

        // Configuration des équipements
        Armure armure1 = new Armure("Armure d'ecaille", new int[]{14,14}, Armure.Type.LEGERE, 9);
        Armure armure2 = new Armure("Cotte de mailles", new int[]{10,10}, Armure.Type.LOURDE, 11);
        Arme arme1 = new Arme("Baton", new int[]{12,6}, Arme.Type.COURANTE, new int[]{1,6}, 1);
        Arme arme2 = new Arme("Fronde", new int[]{10,6}, Arme.Type.DISTANCE, new int[]{1,4}, 6);
        Arme arme3 = new Arme("Rapière", new int[]{7,6}, Arme.Type.GUERRE, new int[]{1,8}, 1);
        Arme arme4 = new Arme("Arc court", new int[]{7,7}, Arme.Type.DISTANCE, new int[]{1,6}, 16);

        ArrayList<Equipement> lstEquipement = new ArrayList<>(Arrays.asList(arme1, arme2, arme3, arme4, armure1, armure2));

        return new Donjon(lstMonstre, lstEquipement, lstObstacle, dimension);
    }

    /**
     * Crée et retourne le troisième donjon prédéfini
     * @return Donjon avec orcs et trolls, équipements principalement des armes et obstacles verticaux
     */
    public static Donjon getDonjonParDefault3() {
        // Configuration de base
        int[] dimension = new int[]{25,25};

        // Création des espèces
        Espece orc = new Espece("orc");
        Espece troll = new Espece("troll");

        // Création des monstres
        Entite orc1 = new Monstre("orc1", new int[]{15,18}, orc, 9, new int[]{12, 12, 4, 6, 6});
        Entite orc2 = new Monstre("orc2", new int[]{15,17}, orc, 9, new int[]{11, 11, 5, 7, 5});
        Entite troll1 = new Monstre("troll1", new int[]{20,20}, troll, 9, new int[]{15, 15, 8, 10, 2});
        Entite troll2 = new Monstre("troll2", new int[]{22,22}, troll, 9, new int[]{16, 14, 7, 9, 3});

        // Liste des monstres
        ArrayList<Entite> lstMonstre = new ArrayList<>(Arrays.asList(orc1, orc2, troll1, troll2));

        // Configuration des obstacles
        ArrayList<int[]> lstObstacle = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            lstObstacle.add(new int[]{4,i});
        }

        // Configuration des équipements
        Arme arme1 = new Arme("Baton", new int[]{12,8}, Arme.Type.COURANTE, new int[]{1,6}, 1);
        Arme arme2 = new Arme("Fronde", new int[]{12,6}, Arme.Type.DISTANCE, new int[]{1,4}, 6);
        Arme arme3 = new Arme("Rapière", new int[]{9,14}, Arme.Type.GUERRE, new int[]{1,8}, 1);
        Arme arme4 = new Arme("Arc court", new int[]{7,7}, Arme.Type.DISTANCE, new int[]{1,6}, 16);
        Arme arme5 = new Arme("Arbalete legere", new int[]{7,8}, Arme.Type.DISTANCE, new int[]{1,8}, 16);

        ArrayList<Equipement> lstEquipement = new ArrayList<>(Arrays.asList(arme1, arme2, arme3, arme4, arme5));

        return new Donjon(lstMonstre, lstEquipement, lstObstacle, dimension);
    }
}