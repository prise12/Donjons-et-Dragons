package ded.entite;

public abstract class Entite {
    protected String m_nom;
    protected int[] m_coordonnees;

    public class (String nom, int[] coordonnees){
        this.m_nom = nom;
        this.m_coordonnees[0] = coordonnees[0];
        this.m_coordonnees[1] = coordonnees[1];
    }

    public abstract void attaquer(Entite entite);

    public abstract void defense(Integer attaque);

    public abstract int[] getCoordonnees();

    public abstract void setCoordonnees(int[] coordonnees);

    public abstract String getNom();



}
