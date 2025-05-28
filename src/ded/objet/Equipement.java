package ded.objet;

public abstract class Equipement {

    private String m_nom;
    int m_ralentissement = 0;
    int[] m_coo;

    public Equipement(String nom, int[] coo){
        this.m_nom = nom;
    }
    public String getNom(){
        return this.m_nom;
    }

    public int get_ralentissement(){ return this.m_ralentissement;}

    public void setCoo(int[] coo){
        this.m_coo = coo;
    }

    public int[] getCoo(){
        return this.m_coo;
    }
}
