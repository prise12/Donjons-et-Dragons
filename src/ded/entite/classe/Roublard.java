package ded.entite.classe;

import ded.objet.Arme;
import ded.objet.Equipement;

public class Roublard extends Classe {
    public Roublard() {
        super(16);
        this.m_nom  ="Roublard";
        this.m_lstEquipement.add(new Arme("Rapière", null, Arme.Type.GUERRE, new int[]{1, 8}, 1));
        this.m_lstEquipement.add(new Arme("Arc court", null, Arme.Type.DISTANCE, new int[]{1, 6}, 16));
    }
}
