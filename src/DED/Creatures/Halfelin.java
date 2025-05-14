package DED.Creatures;

import DED.Des;

public class Halfelin extends Race{
    public Halfelin(String nom){
        super(
                nom,
                "Halfelin",
                12,
                Des.lancerBonus(4, 4, 3),
                Des.lancerBonus(4, 4, 7),
                Des.lancerBonus(4, 4, 5),
                Des.lancerBonus(4, 4, 3)
        );
    }
}
