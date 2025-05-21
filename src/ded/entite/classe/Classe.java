
package ded.entite.classe;

import ded.entite.Personnage;



public abstract class Classe extends Personnage {

    protected Integer m_vie;

    public Classe(String nom, Integer vie){
        super(nom);
        this.m_vie = vie;
    }


}
