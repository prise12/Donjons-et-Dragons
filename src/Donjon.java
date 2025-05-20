import DED.Creatures.Entite;
import DED.Creatures.Personnage;
import DED.Entite.Personnage;

import java.io;
import java.io.Console;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Donjon {

    private LinkedHashMap<Entitee, int[] > m_dictEntitee;
    private LinkedHashMap<Equipement, int[] > m_dictEquipement;
    private int[][] m_tabObstacles;
    private String[][] m_donjon;
    private int m_largeurMap;
    
    public Donjon(LinkedHashMap<Entitee, int[] > dictEntitee, LinkedHashMap<Equipement, int[] > dictEquipement, int[][] tabObstacles, int largeurMap) {
        
        this.m_dictEntitee = dictEntitee;
        this.m_dictEquipement = dictEquipement;
        this.m_tabObstacles = tabObstacles;
        this.m_tabObstacles = tabObstacles;
        this.m_largeurMap = largeurMap;
        

    }


    public void afficherDonjon(int nMap){

        Console.println(" A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W\n");
        Console.("*-----------------------------------------------------------------------*\n");
        for(int i = 0; i<  m_largeurMap; i++ ){

            Console.print(i+" ");
            for(int j = 0; j<  m_largeurMap; j++ ) {
                Console.print(m_donjon[i][j]);

            }
            Console.print("\n");
        }
    }

    public void genererDonjon(){

        for(int i = 0; i< m_largeurMap; i++ ){
            for(int j = 0; j <  m_largeurMap; j++ ){;
                this.m_donjon[i][j] = ".";
            }
        }

        //On genere les creatures, objets, obstacles dans la map
        for(Map.Entry<Entitee, Integer[]> entry : m_dictEntitee.entrySet()) {
            this.m_donjon[entry.getvalue()[0]][entry.getvalue()[1]] = entry.getValue().getNom().substring(0,2);
        }
        for(Map.Entry<Entitee, Integer[]> entry : m_dictObjet.entrySet()) {
            this.m_donjon[entry.getvalue()[0]][entry.getvalue()[1]] = entry.getValue().getNom().substring(0,2);
        }
        for(int[] coo : m_tabObstacles) {
            this.m_donjon[coo[0]][coo[1]] = "[]";
        }
        
        for(int i = 0; i< m_largeurMap; i++ ){
            writer.print(i+" ");
            for(int j = 0; j<  m_largeurMap; j++ ){;
                writer.println(m_donjon[i][j]);
            }
        }

        writer.close();

    }

    public void addEntitee(Entitee creature, int[] coordonées) {
        if(coordonées[0] < this.m_largeurMap && coordonées[1] < this.m_largeurMap ) {

            this.m_dictEntitee.Add(creature, coordonées)
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }

    }

    public void addEquipement(Equipement equipement, int[] coordonées) {
        if(coordonées[0] < this.m_largeurMap && coordonées[1] < this.m_largeurMap ) {
            this.m_dictEquipement.Add(equipement, coordonées);
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }
    }

    public void addObstacles(int[] coordonées) {
        if(coordonées[0] < this.m_largeurMap && coordonées[1] < this.m_largeurMap ) {
            this.m_donjon[coordonées[0]][coordonées[1]] = "[]";
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }
    }







}
