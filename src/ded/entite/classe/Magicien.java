package ded.entite.classe;

import ded.Des;
import ded.entite.Entite;
import ded.entite.Personnage;
import ded.objet.Arme;
import ded.objet.Equipement;

import java.util.ArrayList;

public class Magicien extends Classe {
    public Magicien()
    {
        super(12);
        this.m_nom  ="Magicien";
        this.m_lstEquipement = new ArrayList<Equipement>();
        this.m_lstEquipement.add(new Arme("Bâton", null, Arme.Type.COURANTE, new int[]{1, 6}, 1));
        this.m_lstEquipement.add(new Arme("Fronde", null, Arme.Type.DISTANCE, new int[]{1, 4}, 6));
    }



    public void boogieWoogie(Entite entite1, Entite entite2){
        int[] coo1 = entite1.getCoo();
        entite1.setCoo(entite2.getCoo());
        entite2.setCoo(coo1);
    }

    public void armeMagique(Arme arme){
        arme.addBonusMagique();
    }
}
