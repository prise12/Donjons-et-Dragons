package ded.entite.classe;

import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

public class Guerrier extends Classe {
    public Guerrier() {
        super(20);
        this.m_nom  ="Guerrier";
        this.m_tabEquipement = new Equipement[]{
                new Armure("Cotte de mailles",null, Armure.Type.LEGERE,11),
                new Arme("Epee longue",null, Arme.Type.GUERRE, new int[]{1,8},1 ),
                new Arme("Arbalete legere",null, Arme.Type.DISTANCE, new int[]{1,8},16 )
        };
    }
}

