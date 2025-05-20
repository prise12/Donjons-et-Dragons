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

    private LinkedHashMap<Entite, int[] > m_dictEntite;
    private LinkedHashMap<Equipement, int[] > m_dictEquipement;
    private int[][] m_tabObstacles;
    private String[][] m_donjon;
    private int m_largeurMap;
    
    public Donjon(LinkedHashMap<Entite, int[] > dictEntite, LinkedHashMap<Equipement, int[] > dictEquipement, int[][] tabObstacles, int largeurMap) {
        
        this.m_dictEntite = dictEntite;
        this.m_dictEquipement = dictEquipement;
        this.m_tabObstacles = tabObstacles;
        this.m_tabObstacles = tabObstacles;
        this.m_largeurMap = largeurMap;
        

    }


    public void afficherDonjon(int nMap){

        System.out.println(" A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W");
        System.out.println("*-----------------------------------------------------------------------*");
        for(int i = 0; i<  m_largeurMap; i++ ){

            System.out.print(i+" ");
            for(int j = 0; j<  m_largeurMap; j++ ) {
                System.out.print(m_donjon[i][j]);

            }
            System.out.print("\n");
        }
    }

    public void genererDonjon(){

        for(int i = 0; i< m_largeurMap; i++ ){
            for(int j = 0; j <  m_largeurMap; j++ ){;
                this.m_donjon[i][j] = ".";
            }
        }

        //On genere les creatures, objets, obstacles dans la map
        for(Map.Entry<Entite, int[]> entry : m_dictEntite.entrySet()) {
            this.m_donjon[entry.getValue()[0]][entry.getValue()[1]] = entry.getValue().getNom().substring(0,2);
        }
        for(Map.Entry<Equipement, Integer[]> entry : m_dictEquipement.entrySet()) {
            this.m_donjon[entry.getvalue()[0]][entry.getvalue()[1]] = entry.getValue().getNom().substring(0,2);
        }
        for(int[] coo : m_tabObstacles) {
            this.m_donjon[coo[0]][coo[1]] = "[]";
        }
        
        for(int i = 0; i< m_largeurMap; i++ ){
            System.out.print(i+" ");
            for(int j = 0; j<  m_largeurMap; j++ ){;
                System.out.print(m_donjon[i][j]);
            }
        }

        Console.close();

    }

    public void addEntitee(Entite entite, int[] coordonées) {
        if(coordonées[0] < this.m_largeurMap && coordonées[1] < this.m_largeurMap ) {

            this.m_dictEntite.Add(entite, coordonées);
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }

    }

    public void addEquipement(Equipement equipement, int[] coordonées) {
        if(coordonées[0] < this.m_largeurMap && coordonées[1] < this.m_largeurMap ) {
            this.m_dictEquipement.put(equipement, coordonées);
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
