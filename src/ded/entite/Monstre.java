package ded.entite;

import ded.Des;

/**
 * Classe représentant un monstre dans le donjon.
 * Un monstre est une entité dotée d’une espèce, de statistiques spécifiques,
 * et de capacités d’attaque configurables par le maître du jeu.
 */
public class Monstre extends Entite {

    // ===================== ATTRIBUTS =====================
    private Espece m_espece;

    // ===================== CONSTRUCTEUR =====================

    /**
     * Crée un monstre avec un nom, une position, une espèce, une classe d’armure et des statistiques.
     * @param nom nom du monstre
     * @param coo coordonnées du monstre
     * @param espece espèce du monstre
     * @param classeA classe d’armure
     * @param stats tableau des caractéristiques : [vie, vitesse, force, dextérité, initiative]
     */
    public Monstre(String nom, int[] coo, Espece espece, int classeA, int[] stats) {
        super(nom, coo, stats);
        this.m_espece = espece;
        this.m_classeArmure = classeA;
        this.m_portee = 0;
    }

    // ===================== ACCESSEURS =====================

    /**
     * @return classe d’armure actuelle du monstre
     */
    public int getClasseArmure() {
        return this.m_classeArmure;
    }

    /**
     * @return espèce du monstre
     */
    public Espece getEspece() {
        return this.m_espece;
    }

    // ===================== COMBAT =====================

    /**
     * Calcule et retourne le score d’attaque du monstre.
     * Le MJ peut spécifier la portée et les dégâts pour personnaliser l'attaque.
     * @param portee portée de l’attaque
     * @param degat tableau contenant [nombre de dés, nombre de faces]
     * @return score d’attaque (1d20 + force)
     */
    public int getAttaque(int portee, int[] degat) {
        this.m_portee = portee;
        return Des.lancer(20) + this.m_force;
    }

    /**
     * Calcule et retourne les dégâts infligés.
     * @param degat tableau contenant [nombre de dés, nombre de faces]
     * @return total des dégâts infligés
     */
    public int getDegat(int[] degat) {
        return Des.lancerMulti(degat[0], degat[1]);
    }
}
