package DED.Entite;

public class Race extends Personnage{
    protected String m_race;
    protected Integer m_vie;
    protected Integer m_force;
    protected Integer m_dexterite;
    protected Integer m_vitesse;
    protected Integer m_initiative;

    public Race(String nom, String race, Integer vie, Integer force, Integer dexterite, Integer vitesse, Integer initiative)
    {
        super(nom);
        this.m_race = race;
        this.m_vie = vie;
        this.m_force = force;
        this.m_dexterite = dexterite;
        this.m_vitesse = vitesse;
        this.m_initiative = initiative;
    }



}
