package ded.objet;

public abstract class Equipement {

    private String m_nom;
    int m_ralentissement = 0;

    public Equipement(String nom){
        this.m_nom = nom;
    }
    public String getNom(){
        return this.m_nom;
    }

    public int get_ralentissement(){ return this.m_ralentissement;}
}
