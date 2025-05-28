package ded.entite;

import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Armure;

public abstract class Entite {
    private String m_nom;
    private int[] m_coo;
    private int m_vie;
    private int m_vitesse;
    private int m_force;
    private int m_dexterite ;
    private int m_initiative;

    public Entite(String nom,int vie, int[] coo){
        this.m_nom = nom;
        this.m_vie = vie;
        this.m_coo = coo;
    }

    public int defenseDegat(int degat){
        this.m_vie -= degat;
        return m_vie;
    }
    public abstract int getClasseArmure();
    public abstract void degat(int attaque);
    public abstract void attaque(int attaque);


    //l'adversaire attaque et
    public abstract void defense(Integer attaque);

    public String getNom(){
        return this.m_nom;
    }

    public void setCoo(int[] coo){
        this.m_coo = coo;
    }

    public int[] getCoo(){
        return this.m_coo;
    }


}
