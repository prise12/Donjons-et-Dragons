package ded.entite.classe;

import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;

public class Guerrier extends Classe {
    public Guerrier() {
        super(20);
        this.m_nom  ="Guerrier";
        m_lstEquipement = new ArrayList<Equipement>();
        this.m_lstEquipement.add(new Armure("Cotte de mailles", null, Armure.Type.LEGERE, 11));
        this.m_lstEquipement.add(new Arme("Épée longue", null, Arme.Type.GUERRE, new int[]{1, 8}, 1));
        this.m_lstEquipement.add(new Arme("Arbalète légère", null, Arme.Type.DISTANCE, new int[]{1, 8}, 16));
    }
}

