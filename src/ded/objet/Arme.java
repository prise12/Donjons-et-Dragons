package ded.objet;

public class Arme extends Equipement{

    private int[] m_degat;
    private int m_portee;
    private int m_force = 0;

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

    public int[] get_degat(){ return this.m_degat ;}
    public int get_portee(){ return this.m_portee ;}
    public Type get_type(){ return this.m_type ;}

}
