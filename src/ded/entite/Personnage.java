package ded.entite;

<<<<<<< HEAD
import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Arme;
import ded.objet.Armure;

public class Personnage extends Entite{
=======
import ded.Des;
import ded.objet.Arme;

public class Personnage {
    protected String m_nom;
<<<<<<< HEAD
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
=======
    protected String m_race;
    protected int m_vie;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;

    public Personnage(String nom, String race, int vie, int force, int dexterite, int vitesse, int initiative)
    {
        super(nom);
        this.m_race = race;
        this.m_vie = vie;
        this.m_force = force;
        this.m_dexterite = dexterite;
        this.m_vitesse = vitesse;
        this.m_initiative = initiative;
    }

    public void attaquer (Entite entite, Arme.Type types)
    {
        int attaque = 0;
        switch(types){
            case COURANTE :
                attaque = Des.lancerBonus(1,20, this.m_force);
            case DISTANCE:
                attaque = Des.lancerBonus(1, 20, m_dexterite);
            case GUERRE:
                attaque = Des.lancerBonus(1, 20, this.m_force + 4);
        }
        entite.m_vie -= attaque;
    }

    public void defense (){
<<<<<<< HEAD
>>>>>>> 2300749 (modif personnage)

<<<<<<< HEAD
=======
>>>>>>> 8bbe180 (modif personnage)
    }

<<<<<<< HEAD
    public void attaquer (Entite entite)
    {
        //Attaque
        if()
    }
=======
>>>>>>> 0478158 (modif personnage et int)

    public void defense (){
=======
    public void getNom(){
>>>>>>> 2300749 (modif personnage)

    }


}
