package ded.objet;

import java.util.Optional;

/**
 * Classe abstraite représentant un équipement générique dans le jeu.
 * Un équipement possède un nom, des coordonnées, et un éventuel ralentissement.
 * Elle est héritée par les classes concrètes comme {@link Arme} ou {@link Armure}.
 */
public abstract class Equipement {

    // ===================== ATTRIBUTS =====================
    private String m_nom;
    int m_ralentissement = 0;
    private Optional<int[]> m_coo;

    // ===================== CONSTRUCTEUR =====================

    /**
     * Crée un équipement avec un nom et des coordonnées optionnelles.
     * @param nom nom de l’équipement
     * @param coo coordonnées dans le donjon (peut être null)
     */
    public Equipement(String nom, int[] coo) {
        this.m_nom = nom;
        this.m_coo = Optional.ofNullable(coo);
    }

    // ===================== ACCESSEURS =====================

    /**
     * @return nom de l’équipement
     */
    public String getNom() {
        return this.m_nom;
    }

    /**
     * @return valeur de ralentissement appliquée par l’équipement (par défaut : 0)
     */
    public int getRalentissement() {
        return this.m_ralentissement;
    }

    /**
     * Modifie les coordonnées de l’équipement.
     * @param coo coordonnées [x, y]
     */
    public void setCoo(int[] coo) {
        this.m_coo = Optional.of(coo);
    }

    /**
     * @return coordonnées actuelles de l’équipement
     */
    public int[] getCoo() {
        return this.m_coo.get();
    }
}
