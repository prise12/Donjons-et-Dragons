package ded.entite.classe;


import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

public class Clerc extends Classe {
    public Clerc(){
        super(16);
        this.m_nom = "Clerc";

        m_lstEquipement.add(new Armure("Armure d'écaille", null, Armure.Type.LEGERE, 9));
        m_lstEquipement.add(new Arme("Masse d'armes", null, Arme.Type.COURANTE, new int[]{1, 6}, 1));
        m_lstEquipement.add(new Arme("Arbalète légère", null, Arme.Type.DISTANCE, new int[]{1, 8}, 16));
    }
}
