package ded.entite;

import ded.Des;
import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.Optional;

public class Personnage extends Entite{

    private Optional<Arme> m_armeEquipe;
    private Optional<Armure> m_armureEquipe;
    private ArrayList<Equipement>  m_inventaire;
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
        this.m_inventaire = m_classe.getEquipement();
    }

    //retourne un int correspondant au point d'attaque total en comptant tous les bonus
    public int getAttaque(Entite entite){
        if (m_armeEquipe.get().getType() == Arme.Type.COURANTE || m_armeEquipe.get().getType() == Arme.Type.GUERRE ){
            return Des.lancerBonus(0,20, this.m_force + this.m_armeEquipe.get().getBonusMagique());
        } else if (m_armeEquipe.get().getType() == Arme.Type.COURANTE) {
            return Des.lancerBonus(0,20, this.m_dexterite);
        }
        return 0;
    }

    //retourne un int correspondant au point de degat
    public int getDegat(){
        return Des.lancerBonus(m_armeEquipe.get().getDegat()[0], m_armeEquipe.get().getDegat()[1], this.m_armeEquipe.get().getBonusMagique());
    }

    public void recupererEquipement(Equipement equipement){
        m_inventaire.add(equipement);
    }

    public void equiperEquipement(int index) {
        if(this.m_inventaire.get(index) instanceof Arme arme){
            this.m_inventaire.add( this.m_armeEquipe.get() );
            this.m_armeEquipe = Optional.of( arme);
        } else if (this.m_inventaire.get(index) instanceof Armure armure) {
            this.m_inventaire.add( this.m_armureEquipe.get() );
            this.m_armureEquipe = Optional.of( armure);
        }
    }

    public Classe getClasse(){return this.m_classe;}

    public int getClasseArmureEquipe(){
        return this.m_armureEquipe.get().getClasse();
    }
    public int getPorteeArmeEquipe(){return this.m_armeEquipe.get().getPortee();}

    public Arme getArmeEquipe(){return this.m_armeEquipe.get();}
    public Armure getArmureEquipe(){return this.m_armureEquipe.get();}
    public ArrayList<Equipement> getInventaire(){return this.m_inventaire;}

    @Override
    public int getVitesse(){
        return this.m_vitesse - this.m_armureEquipe.get().getRalentissement() - this.m_armeEquipe.get().getRalentissement();
    }

    public void guerison(int pv){
        this.m_vie += pv;
    }


}
