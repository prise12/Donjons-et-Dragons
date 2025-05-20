package ded.entite;
import ded.Des;


public class Humain extends Race{

    public Humain(String nom) {

        super(
                nom,
                "Humain",
                14,
                Des.lancerBonus(4, 4, 5),
                Des.lancerBonus(4, 4, 5),
                Des.lancerBonus(4, 4, 5),
                Des.lancerBonus(4, 4, 5)
        );
    }
}
