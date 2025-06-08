package ded.entite;

import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Armure;

import java.util.Optional;

/**
 * Classe abstraite représentant une entité dans le jeu (personnage ou monstre)
 */
public abstract class Entite {
    // ===================== ATTRIBUTS =====================
    private String m_nom;
    private int[] m_coo = null;
    int m_vie;
    int m_vitesse;
    int m_force;
    int m_dexterite;
    int m_initiative;
    int m_classeArmure;
    private int m_portee;

    // ===================== CONSTRUCTEUR =====================

    /**
     * Crée une nouvelle entité avec les caractéristiques spécifiées
     * @param nom Nom de l'entité
     * @param coo Coordonnées de l'entité [x,y]
     * @param stats Tableau des statistiques [vie, vitesse, force, dextérité, initiative]
     */
    public Entite(String nom, int[] coo, int[] stats) {
        this.m_nom = nom;
        this.m_coo = coo;
        this.m_vie = stats[0];
        this.m_vitesse = stats[1];
        this.m_force = stats[2];
        this.m_dexterite = stats[3];
        this.m_initiative = stats[4];
    }

    // ===================== METHODES PUBLIQUES =====================

    /**
     * Inflige des dégâts à l'entité
     * @param degat Montant des dégâts à infliger
     */
    public void setDegat(int degat) {
        this.m_vie -= degat;
    }

    /**
     * Vérifie si une attaque réussit contre cette entité
     * @param degat Valeur de l'attaque à comparer avec la classe d'armure
     * @return true si l'attaque réussit (dégâts > CA), false sinon
     */
    public boolean setAttaque(int degat) {
        return this.m_classeArmure < degat;
    }

    // ===================== GETTERS/SETTERS =====================

    /**
     * @return Dextérité de l'entité
     */
    public int getDexterite() {
        return this.m_dexterite;
    }

    /**
     * @return Force de l'entité
     */
    public int getForce() {
        return this.m_force;
    }

    /**
     * @return Vitesse de déplacement de l'entité
     */
    public int getVitesse() {
        return this.m_vitesse;
    }

    /**
     * @return Points de vie actuels de l'entité
     */
    public int getVie() {
        return this.m_vie;
    }

    /**
     * @return Classe d'armure de l'entité
     */
    public int getClasseArmure() {
        return this.m_classeArmure;
    }

    /**
     * @return Portée d'attaque de l'entité
     */
    public int getPortee() {
        return this.m_portee;
    }

    /**
     * @return Nom de l'entité
     */
    public String getNom() {
        return this.m_nom;
    }

    /**
     * Modifie les coordonnées de l'entité
     * @param coo Nouvelles coordonnées [x,y]
     */
    public void setCoo(int[] coo) {
        this.m_coo = coo;
    }

    /**
     * @return Coordonnées actuelles de l'entité [x,y]
     */
    public int[] getCoo() {
        return this.m_coo;
    }

    /**
     * @return Valeur d'initiative de l'entité
     */
    public int getInitiative() {
        return this.m_initiative;
    }
}