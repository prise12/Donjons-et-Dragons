package ded.objet;

public class Armure extends Equipement{

    private int m_classe;
    private Type m_type;
    public enum Type {
        LEGERE,
        LOURDE,
    }

    public Armure(Type type, int degat, int portee, String nom){
        super(nom);

        switch(type) {
            case LEGERE:
                this.m_type = Type.LEGERE;
            case LOURDE:
                this.m_ralentissement = 4;
                this.m_type = Type.LOURDE;
        }
    }
    public int get_classe(){ return this.m_ralentissement;}
    public Type get_type(){ return this.m_type ;}
}
