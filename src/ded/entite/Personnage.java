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

        //on ajoute le 4d4 et les 3 points par default
        int desCara = Des.lancerMulti(4,4);
        this.m_vitesse += desCara + 3;
        this.m_force += desCara + 3;
        this.m_dexterite += desCara + 3;
        this.m_initiative += desCara + 3;
    }

    //retourne un int correspondant au point d'attaque total en comptant tous les bonus
    public int getAttaque(Entite entite){
        if (m_armeEquipe.getType() == Arme.Type.COURANTE || m_armeEquipe.getType() == Arme.Type.GUERRE ){
            return Des.lancerBonus(0,20, this.m_force + this.m_armeEquipe.getBonusMagique());
        } else if (m_armeEquipe.getType() == Arme.Type.COURANTE) {
            return Des.lancerBonus(0,20, this.m_dexterite);
        }
        return 0;
    }

    //retourne un int correspondant au point de degat
    public int getDegat(){
        return Des.lancerBonus(m_armeEquipe.getDegat()[0], m_armeEquipe.getDegat()[1], this.m_armeEquipe.getBonusMagique());
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

    public Classe getClasse(){return this.m_classe;}

    public int getClasseArmureEquipe(){
        return this.m_armureEquipe.getClasse();
    }
    public int getPorteeArmeEquipe(){return this.m_armeEquipe.getPortee();}

    public Arme getArmeEquipe(){return this.m_armeEquipe;}
    public Armure getArmureEquipe(){return this.m_armureEquipe;}
    public ArrayList<Equipement> getInventaire(){return this.m_lstInventaire;}

    @Override
    public int getVitesse(){
        return this.m_vitesse - this.m_armureEquipe.getRalentissement() - this.m_armeEquipe.getRalentissement();
    }

    public void guerison(int pv){
        this.m_vie += pv;
    }


}
