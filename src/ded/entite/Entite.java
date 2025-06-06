package ded.entite;

import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Armure;

import java.util.Optional;

public abstract class Entite {
    private String m_nom;
    private int[] m_coo = null;
    int m_vie;
    int  m_vitesse;
    int m_force;
    int m_dexterite ;
    int m_initiative;
    int m_classeArmure;
    int m_portee;

    public Entite(String nom, int[] coo, int[] stats){
        this.m_nom = nom;
        this.m_coo = coo;
        this.m_vie = stats[0];
        this.m_vitesse = stats[1];
        this.m_force = stats[2];
        this.m_dexterite = stats[3];
        this.m_initiative = stats[4];
    }

    //Infliges les degat au personnge et renvoie un booleen indiquant si le personnage et à 0 pv ou moins
    public void setDegat(int degat){
        this.m_vie -= degat;
    }

    public boolean setAttaque(int degat){
        if(this.m_classeArmure < degat){
            return true;
        }
        return false;
    }


    public int getDexterite(){return this.m_dexterite;}
    public int getForce(){return this.m_force;}
    public int getVitesse(){return this.m_vitesse;}
    public int getVie(){return this.m_vie;}
    public int getClasseArmure(){return this.m_classeArmure;}
    public int getPortee(){return this.m_portee;}

    public String getNom(){
        return this.m_nom;
    }


    public void setCoo(int[] coo){
        this.m_coo = coo;
    }

    public int[] getCoo(){
        return this.m_coo;
    }
    public int getInitiative(){return this.m_initiative;}

    public boolean deplacerDirection(int[] direction, int distance){
        if( 0 > distance || distance > this.m_vitesse){
            return false;
        }
        this.m_coo[0] = this.m_coo[0] + (direction[0] * distance);
        this.m_coo[1] = this.m_coo[1] + (direction[1] * distance);
        return true;
    }


}
