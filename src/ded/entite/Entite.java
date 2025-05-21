package ded.entite;

public abstract class Entite {
    private String m_nom;

    public Entite(String nom){
        this.m_nom = nom;

    }

    public String getNom(){
        return this.m_nom;

    }
}
