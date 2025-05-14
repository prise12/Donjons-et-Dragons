package DED.Creatures;
import java.util.ArrayList;
import java.util.Random;

public class Humain extends Race{

    public Humain(String nom) {
        Random jetD = new Random();
        ArrayList<Integer> carac = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int val = 5;

            for (int j = 0; j < 4; j++) {
                val += (jetD.nextInt(4) + 1);
            }
            carac.add(val);
        }

        super(
                nom,
                "Humain",
                carac.get(0),
                carac.get(1),
                carac.get(2),
                carac.get(3),
                carac.get(4)
                //nom, "Humain", vie, force, dexterite, vitesse, initiative
        );
    }
}
