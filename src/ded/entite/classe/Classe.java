package ded.entite.classe;

public abstract class Classe {

<<<<<<< HEAD
    private Integer m_vie;
    private String m_nom;

    public Classe(String nom, Integer vie){

        this.m_nom = nom;
=======
public abstract class Classe extends Personnage {

    protected int m_vie;

    public Classe(String nom, int vie){
        super(nom);
>>>>>>> 0478158 (modif personnage et int)
        this.m_vie = vie;
    }


}
