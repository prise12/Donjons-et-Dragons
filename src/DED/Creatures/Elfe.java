package DED.Creatures;

import DED.Des;

public class Elfe extends Race{

    public Elfe(String nom){
        super(
                nom,
                "Elfe",
                12,
                Des.lancerBonus(4, 4, 3),
                Des.lancerBonus(4, 4, 9),
                Des.lancerBonus(4, 4, 3),
                Des.lancerBonus(4, 4, 3)
        );
    }
}
