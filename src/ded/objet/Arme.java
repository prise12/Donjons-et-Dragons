package ded.objet;

public class Arme extends Equipement{

    private int m_degat;
    private int m_portee;

    private int m_force;
    private int m_ralentissement;

    public enum Type {
        COURANTECAC,
        GUERRECAC,
        DISTANCE;


        }

    public Arme(Type type, int degat, int portee){
        switch(type) {
            case COURANTECAC:
            case GUERRECAC:
                this.m_force = 4;
                this.m_ralentissement = 2;
            case DISTANCE:
                this.m_ralentissement = 4;
        }

        this.m_degat = degat;
        this.m_portee = portee;

    }





}
