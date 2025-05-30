package ded.entite.classe;

import ded.objet.Arme;
import ded.objet.Equipement;

public class Magicien extends Classe {
    public Magicien(String nom)
    {
        super(12);
        this.m_nom  ="Magicien";
        this.m_tabEquipement = new Equipement[]{
                new Arme("Baton",null, Arme.Type.COURANTE, new int[]{1,6},1 ),
                new Arme("Fronde",null, Arme.Type.DISTANCE, new int[]{1,4},6 )
        };
    }
}
