package ded.entite;

import ded.donjon.Donjon;

public class MJ {

    private String m_nom;

    public MJ(String nom){}

    public void ajouterObtacles(Donjon donjon, int[] coo){
        donjon.addObstacles(new int[]{coo[0],coo[1]});
    }

    public void deplacerEntite( Entite entite, int[] coo){
        entite.setCoo(coo);
    }

    public void attaquer(Entite entite, int attaque){
        entite.setDegat(attaque);
    }
}
