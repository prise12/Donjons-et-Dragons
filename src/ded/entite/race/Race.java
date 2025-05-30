package ded.entite.race;

import ded.entite.Entite;
import ded.entite.Personnage;

public abstract class Race {
    String m_nom;
    protected int m_vie;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;

    public Race(int vie,int force,int dexterite,int vitesse,int intitative)
    {
        this.m_vie = vie;
        this.m_force =  force;
        this.m_dexterite =  dexterite;
        this.m_vitesse =  vitesse;
        this.m_initiative = intitative;
    }


    public String getNom(){return this.m_nom ;}
    public int getVie(){return this.m_vie ;}
    public int getForce(){return this.m_force ;}
    public int getDexterite(){return this.m_force ;}
    public int getVitesse(){return this.m_force ;}
    public int getInitiative(){return this.m_force ;}






}
