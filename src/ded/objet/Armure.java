package ded.objet;

public class Armure extends Equipement{

    private int m_classe;
    private Type m_type;


    public enum Type {
        LEGERE,
        LOURDE,
    }
    public Armure(String nom, int[] coo, Type type, int classe ){
        super(nom,coo);

        switch(type) {
            case LEGERE:
                this.m_type = Type.LEGERE;
                this.m_classe = 9;
            case LOURDE:
                this.m_ralentissement = 4;
                this.m_type = Type.LOURDE;
        }
    }
    public int getClasse(){ return this.m_classe;}
    public Type getType(){ return this.m_type ;}
}
