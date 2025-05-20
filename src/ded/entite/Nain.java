package ded.entite;

import ded.Des;

public class Nain extends Race {

    public Nain(String nom){
        super(
                nom,
                "Nain",
                12,
                Des.lancerBonus(4, 4, 9),
                Des.lancerBonus(4, 4, 3),
                Des.lancerBonus(4, 4, 3),
                Des.lancerBonus(4, 4, 3)
        );
    }
}
