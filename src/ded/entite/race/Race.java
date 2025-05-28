package ded.entite.race;

import ded.entite.Entite;
import ded.entite.Personnage;

public abstract class Race {
    protected String m_race;
    protected int m_vie;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;

    public Race( String race, int vie, int force, int dexterite, int vitesse, int initiative)
    {
        this.m_race = race;
        this.m_vie = vie;
        this.m_force = force;
        this.m_dexterite = dexterite;
        this.m_vitesse = vitesse;
        this.m_initiative = initiative;
    }






}
