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
        // on initalise les caractéristiques avec les bonus des classes et des races
        super(nom, coo, new int[]{race.getVie() + classe.getVie(), race.getVitesse(), race.getForce(), race.getDexterite(), race.getInitiative() });
        this.m_classe = classe;
        this.m_race = race;
    }

    //retourne un int correspondant au point d'attaque total en comptant tous les bonus
    public int getAttaque(Entite entite){
        if (m_armeEquipe.get_type() == Arme.Type.COURANTE || m_armeEquipe.get_type() == Arme.Type.GUERRE ){
            return Des.lancerBonus(0,20, this.m_force);
        } else if (m_armeEquipe.get_type() == Arme.Type.COURANTE) {
            return Des.lancerBonus(0,20, this.m_dexterite);
        }
        return 0;
    }

    //retourne un int correspondant au point de degat
    public int getDegat(){
        return Des.lancerMulti(m_armeEquipe.get_degat()[0], m_armeEquipe.get_degat()[1]);
    }

    public void recupererEquipement(Equipement equipement){
        m_lstInventaire.add(equipement);
    }

    public void equiperEquipement(Equipement equipement){
        if(equipement.getClass() == Arme.class){
            this.m_armeEquipe = (Arme) equipement;
        }
        else if(equipement.getClass() == Armure.class){
            this.m_armureEquipe = (Armure) equipement;
        }
    }

    public int getClasseArmureEquipe(){
        return this.m_armureEquipe.get_classe();
    }
    public int getPorteeArmeEquipe(){return this.m_armeEquipe.get_portee();}

    public int getVitesse(){
        return this.m_vitesse + this.m_armureEquipe.get_ralentissement() + this.m_armeEquipe.get_ralentissement();
    }

}
