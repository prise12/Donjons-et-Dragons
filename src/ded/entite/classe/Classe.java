package ded.entite.classe;

import ded.entite.Personnage;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.List;

public abstract class Classe {

    String m_nom;
    private int m_vie;
    ArrayList<Equipement> m_lstEquipement;


    public Classe(int vie){
        this.m_vie = vie;
    }

    public int getVie(){
        return this.m_vie;
    }
    public ArrayList<Equipement> getEquipement(){return this.m_lstEquipement ;}

}
