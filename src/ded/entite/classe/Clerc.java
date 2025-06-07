package ded.entite.classe;


import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;

public class Clerc extends Classe {
    public Clerc(){
        super(16);
        this.m_nom = "Clerc";
        this.m_lstEquipement = new ArrayList<Equipement>();
        this.m_lstEquipement.add(new Armure("Armure d'écaille", null, Armure.Type.LEGERE, 9));
        this.m_lstEquipement.add(new Arme("Masse d'armes", null, Arme.Type.COURANTE, new int[]{1, 6}, 1));
        this.m_lstEquipement.add(new Arme("Arbalète légère", null, Arme.Type.DISTANCE, new int[]{1, 8}, 16));
    }
}
