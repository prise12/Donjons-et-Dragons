package ded.objet;

public class Arme extends Equipement{

    private int[] m_degat;
    private int m_portee;
    private int m_force = 0;
    private int m_bonusMagique;

    private Type m_type;
    public enum Type {
        COURANTE,
        GUERRE,
        DISTANCE;
        }

    public Arme(String nom,int[] coo, Type type, int[] degat, int portee){
        super(nom,coo);
        switch(type) {
            case COURANTE:
                this.m_type = Type.COURANTE;
            case GUERRE:
                this.m_force = 4;
                this.m_type = Type.GUERRE;
                this.m_ralentissement = 2;
            case DISTANCE:
                this.m_type = Type.DISTANCE;
        }

        this.m_degat = degat;
        this.m_portee = portee;

    }

    public int[] getDegat(){ return this.m_degat ;}
    public int getPortee(){ return this.m_portee ;}
    public Type getType(){ return this.m_type ;}
    public int getBonusMagique(){return this.m_bonusMagique ;}
    public void addBonusMagique(){this.m_bonusMagique++}

}
