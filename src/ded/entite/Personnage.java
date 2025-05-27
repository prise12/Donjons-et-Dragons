package ded.entite;

<<<<<<< HEAD
import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Arme;
import ded.objet.Armure;

public class Personnage extends Entite{
=======
import ded.Des;

public class Personnage {

    protected String m_nom;
>>>>>>> 0478158 (modif personnage et int)

    private String m_nom;
    private Arme arme;
    private Classe m_classe;
    private Race m_race;

<<<<<<< HEAD

    public Personnage(String nom,Classe classe, Race race){
        super(nom);
        this.m_classe = classe;
        this.m_race = race;
=======
    public void attaquer (Entite entite)
    {
>>>>>>> 0478158 (modif personnage et int)
    }

    public bool degat(Entite entite)
    {
        defense()

<<<<<<< HEAD
    }

    public void attaquer (Entite entite)
    {
        //Attaque
        if()
    }
=======
>>>>>>> 0478158 (modif personnage et int)

    public void defense (){

    }


}
