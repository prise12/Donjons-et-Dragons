package ded.entite.race;

import ded.entite.Personnage;

public abstract class Race extends Personnage {
    protected String m_race;
    protected int m_vie;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;

    public Race(String nom, String race, int vie, int force, int dexterite, int vitesse, int initiative)
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
