package ded.objet;

/**
 * Classe représentant une arme dans le jeu.
 * Une arme est un équipement qui possède des dégâts, une portée,
 * un type (courante, guerre, distance), et peut avoir un bonus magique.
 */
public class Arme extends Equipement {

    // ===================== ATTRIBUTS =====================
    private int[] m_degat;
    private int m_portee;
    private int m_force = 0;
    private int m_bonusMagique;
    private Type m_type;

    /**
     * Enumération des types d'armes :
     * - COURANTE : arme simple, peu de contraintes
     * - GUERRE : arme plus puissante, exigeante
     * - DISTANCE : arme à longue portée
     */
    public enum Type {
        COURANTE,
        GUERRE,
        DISTANCE;
    }

    // ===================== CONSTRUCTEUR =====================

    /**
     * Crée une arme avec nom, position, type, dégâts et portée.
     * Applique les propriétés spécifiques au type (force, ralentissement).
     * @param nom nom de l’arme
     * @param coo coordonnées de l’arme
     * @param type type de l’arme (COURANTE, GUERRE, DISTANCE)
     * @param degat tableau [nombre de dés, faces] pour les dégâts
     * @param portee portée de l’arme
     */
    public Arme(String nom, int[] coo, Type type, int[] degat, int portee) {
        super(nom, coo);

        switch (type) {
            case COURANTE:
                this.m_type = Type.COURANTE;
                break;
            case GUERRE:
                this.m_force = 4;
                this.m_type = Type.GUERRE;
                this.m_ralentissement = 2;
                break;
            case DISTANCE:
                this.m_type = Type.DISTANCE;
                break;
        }

        this.m_degat = degat;
        this.m_portee = portee;
    }

    // ===================== ACCESSEURS =====================

    /**
     * @return tableau de dégâts [nb de dés, faces]
     */
    public int[] getDegat() {
        return this.m_degat;
    }

    /**
     * @return portée de l’arme
     */
    public int getPortee() {
        return this.m_portee;
    }

    /**
     * @return type de l’arme (courante, guerre, distance)
     */
    public Type getType() {
        return this.m_type;
    }

    /**
     * @return bonus magique appliqué à l’arme
     */
    public int getBonusMagique() {
        return this.m_bonusMagique;
    }

    /**
     * Incrémente le bonus magique de l’arme.
     */
    public void addBonusMagique() {
        this.m_bonusMagique++;
    }
}
