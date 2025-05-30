package ded.entite.classe;

import ded.entite.Personnage;
import ded.objet.Equipement;

public abstract class Classe {

    String m_nom;
    private int m_vie;
    Equipement[] m_tabEquipement;


    public Classe(int vie){
        this.m_vie = vie;
    }

    public int getVie(){
        return this.m_vie;
    }
    public Equipement[] getEquipement(){return this.m_tabEquipement ;}

}
