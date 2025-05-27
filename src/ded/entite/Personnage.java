package ded.entite;

import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Arme;
import ded.objet.Armure;

public class Personnage extends Entite{

    private String m_nom;
    private Arme arme;
    private Classe m_classe;
    private Race m_race;


    public Personnage(String nom,Classe classe, Race race){
        super(nom);
        this.m_classe = classe;
        this.m_race = race;
    }

    public void attaquer (Entite entite)
    {
        //Attaque
        if()
    }

    public void defense (){

    }


}
