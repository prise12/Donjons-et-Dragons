package ded.entite;

import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Armure;

public abstract class Entite {
<<<<<<< HEAD
    private String m_nom;
    private int m_vie;

    private int m_vitesse;
    Armure m_armureEquipe;
    private Race m_race;
    private int m_dexterite ;
    private int m_vitesse;
    private int m_initiative;
=======
    protected String m_nom;
    protected int m_vie;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;

    public class Entite (String nom) {
        this.m_nom = nom;
    }
>>>>>>> 0478158 (modif personnage et int)


<<<<<<< HEAD
    public Entite(String nom){
        this.m_nom = nom;
    }


    //public abstract void attaquer(Entite entite);
=======
    public abstract void defense(int attaque);
>>>>>>> 0478158 (modif personnage et int)

<<<<<<< HEAD
    public void defense(Integer attaque){
        this.m_vie -= attaque;
    }
=======

>>>>>>> 65f6756 (modif personnage)

    public String getNom(){
        return this.m_nom;
    }


}
