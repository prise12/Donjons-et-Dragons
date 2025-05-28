package ded.entite;

import ded.Des;
import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;

public class Personnage extends Entite{

    private Arme m_armeEquipe;
    private Armure m_armureEquipe;
    private ArrayList<Equipement>  m_lstInventaire;
    private Classe m_classe;
    private Race m_race;


    public Personnage(String nom, int vie, int[] coo,Classe classe, Race race){
        super(nom, vie,coo);
        this.m_classe = classe;
        this.m_race = race;
    }

    public void attaque(Entite entite , Des des){


        if()
        des.lancer()
        entite.getClasseArmure();
    }

    public int degat(){

    }

    public void defenseAttaque(){

    }

    public void recuperer(Equipement equipement){
        m_lstInventaire.add(equipement);
    }

    public void equiper(Equipement equipement){
        if(equipement.getClass() == Arme.class){
            this.m_armeEquipe = (Arme) equipement;
        }
        else if(equipement.getClass() == Armure.class){
            this.m_armureEquipe = (Armure) equipement;
        }
    }

    public int getClasseArmure(){

        return this.m_armureEquipe.get_classe();
    }





}
