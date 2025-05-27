package ded.entite;

public abstract class Entite {
    private String m_nom;

    public Entite(String nom){
        this.m_nom = nom;
    }

    /*
    public abstract void attaquer(Entite entite);

    public abstract void defense(Integer attaque);

    public abstract int[] getCoordonnees();

    public abstract void setCoordonnees(int[] coordonnees);

     */

    public String getNom(){
        return this.m_nom;
    }


}
