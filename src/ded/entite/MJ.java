package ded.entite;

import ded.Donjon;

import java.util.Map;

public class MJ {

    private String m_nom;

    public MJ(String nom){}

    public void ajouterObtacles(Donjon donjon, int[] coo){
        donjon.addObstacles(new int[]{coo[0],coo[1]});
    }

    public void deplacerEntite(Donjon donjon, Entite entite, int[] coo){
        for( Map.Entry<Entite, int[]> entry : donjon.getEntitee().entrySet()){
            if (entite.equals(entry.getKey())){
                entry.setValue(coo);
            }
        }
    }

    public void attaquer(Entite entite, int attaque){
        entite.defense(attaque);
    }
}
