package ded.entite;

import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Armure;

import java.util.Optional;

public abstract class Entite {
    private String m_nom;
    private Optional<int[]>m_coo;
    int m_vie;
    int m_vitesse;
    int m_force;
    int m_dexterite ;
    int m_initiative;

    public Entite(String nom, int[] coo, int[] stats){
        this.m_nom = nom;
        this.m_coo = Optional.ofNullable(coo);
        this.m_vie = stats[0];
        this.m_vitesse = stats[1];
        this.m_force = stats[2];
        this.m_dexterite = stats[3];
        this.m_initiative = stats[4];
    }


    //Infliges les degat au personnge et renvoie un booleen indiquant si le personnage et à 0 pv ou moins
    public boolean setDegat(int degat){
        if(this.m_vie - degat <= 0){
            return false;
        }
        return true;
    }

    public int getDexterite(){return this.m_dexterite;}
    public int getForce(){return this.m_force;}
    public int getVitesse(){return this.m_vitesse;}
    public int getVie(){return this.m_vie;}

    public String getNom(){
        return this.m_nom;
    }


    public void setCoo(int[] coo){
        this.m_coo = Optional.of(coo);
    }

    public int[] getCoo(){
        return this.m_coo.get();
    }
    public int getInitiative(){return this.m_initiative;}


}
