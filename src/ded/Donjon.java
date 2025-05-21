package ded;

import ded.entite.Entite;;
import ded.objet.Equipement;

import java.io.Console;
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
        this.m_largeurMap = largeurMap;
        this.m_donjon = new String[m_largeurMap][m_largeurMap];

    }


    public void afficherDonjon(){
        System.out.print("     ");
        for(int i = 0; i<  m_largeurMap; i++ ){
            if(i<25){

            }
            char c = (char) (65 + i%26);
            System.out.print( c );
            if(i>25){
                char c1 = (char) (65 + i/26);
                System.out.print( c );
                System.out.print( " " );
            }
            else {

                System.out.print( "  " );
            }



        }
        System.out.print("\n");


        for(int i = 0; i<  m_largeurMap; i++ ){

            System.out.print(i+" ");
            int nbChiffres = String.valueOf(Math.abs(i)).length();
            for(int j =0; j < 3 - nbChiffres; j++){
                System.out.print(" ");
            }

            for(int j = 0; j<  m_largeurMap; j++ ) {
                System.out.print(m_donjon[i][j]);

            }
            System.out.print("\n");
        }
    }

    public void genererDonjon(){

        for(int i = 0; i<this.m_largeurMap; i++){
            for(int j = 0; j<this.m_largeurMap; j++){

                this.m_donjon[i][j] = " . ";
            }
        }
        //On genere les creatures, objets, obstacles dans la map
        for(Map.Entry<Entite, int[]> entry : m_dictEntite.entrySet()) {
            this.m_donjon[entry.getValue()[0]][entry.getValue()[1]] = entry.getKey().getNom().substring(0,3);
        }
        for(Map.Entry<Equipement, int[]> entry : m_dictEquipement.entrySet()) {
            this.m_donjon[entry.getValue()[0]][entry.getValue()[1]] = entry.getKey().getNom().substring(0,3);
        }
        for(int[] coo : m_tabObstacles) {
            this.m_donjon[coo[0]][coo[1]] = "[]";
        }
        


    }

    public void addEntitee(Entite entite, int[] coordonées) {
        if(coordonées[0] < this.m_largeurMap && coordonées[1] < this.m_largeurMap ) {

            this.m_dictEntite.put(entite, coordonées);
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
