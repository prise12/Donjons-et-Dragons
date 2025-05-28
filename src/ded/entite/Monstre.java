package ded.entite;

import ded.entite.race.Race;

public class Monstre extends Entite{

    private Espece m_espece;
    private int m_vie;
    private int m_classeArmure;
    private int degat;
    public Monstre(String nom,int vie, int[] coo, Espece espece, int classeA){
        super(nom,coo,);
        this.m_vie = vie;
        this.m_espece = espece;
        this.m_classeArmure = classeA;
    }

    public int getclasseArmure(){
        return this.m_classeArmure;
    }

}
