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


    public Personnage(String nom, int[] coo,Classe classe, Race race){
        super(nom, coo, new int[]{race.getVie() + classe.getVie(), race.getVitesse(), race.getForce(), race.getDexterite(), race.getInitiative() });
        this.m_classe = classe;
        this.m_race = race;


    }

    public bool attaque(Entite entite, Des des){
        return true;
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

    public int getVitesse(){
        return this.m_vitesse + this.m_armureEquipe.get_ralentissement() + this.m_armeEquipe.get_ralentissement() + this.m_race.getVitesse();
    }





}
