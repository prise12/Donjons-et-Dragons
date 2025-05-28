package ded.entite;

import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;

public class Personnage extends Entite{

    private String m_nom;
    private Arme m_armeEquipe;
    private ArrayList<Equipement>  m_lstInventaire;
    private Classe m_classe;
    private Race m_race;


    public Personnage(String nom,Classe classe, Race race){
        super(nom);
        this.m_classe = classe;
        this.m_race = race;
    }

    public void defense (){

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





}
