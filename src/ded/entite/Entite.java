package ded.entite;

public abstract class Entite {
    private String m_nom;
    private int m_vie;


    public Entite(String nom){
        this.m_nom = nom;
    }


    //public abstract void attaquer(Entite entite);

    public void defense(Integer attaque){
        this.m_vie -= attaque;
    }

    public String getNom(){
        return this.m_nom;
    }


}
