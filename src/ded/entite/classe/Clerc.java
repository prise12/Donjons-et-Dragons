package ded.entite.classe;


import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

public class Clerc extends Classe {
    public Clerc(){
        super(16);
        this.m_nom = "Clerc";
        this.m_tabEquipement = new Equipement[]{
                new Armure("Armure d'ecaille",null, Armure.Type.LEGERE,9),
                new Arme("Masse d'armes",null, Arme.Type.COURANTE, new int[]{1,6},1 ),
                new Arme("Arbalete legere",null, Arme.Type.DISTANCE, new int[]{1,8},16 )
        };
    }
}
