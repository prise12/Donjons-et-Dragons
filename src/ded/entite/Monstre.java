package ded.entite;

import ded.Des;
import ded.entite.race.Race;
import ded.objet.Arme;

import java.util.Optional;

public class Monstre extends Entite{

    private Espece m_espece;
    public Monstre(String nom, int[] coo, Espece espece, int classeA, int[] stats){
        super(nom,coo,stats);
        this.m_espece = espece;
        this.m_classeArmure = classeA;
        this.m_portee = 0;
    }

    public int getClasseArmure(){return this.m_classeArmure;}
    public Espece getEspece(){return this.m_espece;}

    //retourne un int correspondant au point d'attaque le mj peut choisir en paramètre les différentes caratèritique de l'attaque,
    // la portee, les degat et si l'attaque est a distance ou pas
    public int getAttaque( int portee, int[] degat){
        this.m_portee = portee;
        return Des.lancer(20) + this.m_force;
    }

    //retourne un int correspondant au point de degat
    public int getDegat(int[] degat){
        return Des.lancerMulti(degat[0], degat[1]);
    }




}
