package ded.objet;

/**
 * Classe représentant une armure dans le jeu.
 * L’armure détermine la classe d’armure (CA) d'une entité
 * et peut être de type légère ou lourde avec des effets de ralentissement.
 */
public class Armure extends Equipement {

    // ===================== ATTRIBUTS =====================
    private int m_classe;
    private Type m_type;

    /**
     * Enumération des types d’armure :
     * - LEGERE : peu ou pas de ralentissement
     * - LOURDE : ralentit fortement les déplacements
     */
    public enum Type {
        LEGERE,
        LOURDE,
    }

    // ===================== CONSTRUCTEUR =====================

    /**
     * Crée une armure avec nom, coordonnées, type et classe d’armure.
     * Applique automatiquement les ralentissements et valeurs spécifiques.
     * @param nom nom de l’armure
     * @param coo coordonnées dans le donjon
     * @param type type de l’armure (légère ou lourde)
     * @param classe valeur de CA attribuée (écrasée si type LEGERE)
     */
    public Armure(String nom, int[] coo, Type type, int classe) {
        super(nom, coo);

        switch (type) {
            case LEGERE:
                this.m_type = Type.LEGERE;
                this.m_classe = 9; // valeur fixée, ignore paramètre `classe`
                break;
            case LOURDE:
                this.m_ralentissement = 4;
                this.m_type = Type.LOURDE;
                break;
        }
    }

    // ===================== ACCESSEURS =====================

    /**
     * @return classe d’armure (CA)
     */
    public int getClasse() {
        return this.m_classe;
    }

    /**
     * @return type de l’armure (LEGERE ou LOURDE)
     */
    public Type getType() {
        return this.m_type;
    }
}
