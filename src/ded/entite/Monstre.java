package ded.entite;

import ded.entite.race.Race;

public class Monstre extends Entite{

    private Race m_race;
    private int m_classeArmure;
    public Monstre(String nom, Race race){
        this.m_nom = nom;
        this.m_race = race;
    }

    public int getclasseArmure(){
        return this.m_classeArmure;
    }

}
