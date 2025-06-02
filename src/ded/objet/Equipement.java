package ded.objet;

import java.util.Optional;

public abstract class Equipement {

    private String m_nom;
    int m_ralentissement = 0;
    private Optional<int[]> m_coo;

    public Equipement(String nom, int[] coo){
        this.m_nom = nom;
        this.m_coo = Optional.ofNullable(coo);
    }
    public String getNom(){
        return this.m_nom;
    }

    public int getRalentissement(){ return this.m_ralentissement;}

    public void setCoo(int[] coo){
        this.m_coo = Optional.of(coo);
    }

    public int[] getCoo(){
        return this.m_coo.get();
    }
}
