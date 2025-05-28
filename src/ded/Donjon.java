package ded;

import ded.entite.Entite;;
import ded.objet.Equipement;

import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Donjon {

    private ArrayList<Entite> m_lstEntite;
    private ArrayList<Equipement> m_lstEquipement;
    private ArrayList<int[]> m_lstObstacles;
    private String[][] m_donjon;
    private int m_largeurMap;
    
    public Donjon(ArrayList<Entite> lstEntite, ArrayList<Equipement> lstEquipement, ArrayList<int[]> lstObstacles, int largeurMap) {
        
        this.m_lstEntite= lstEntite;
        this.m_lstEquipement = lstEquipement;
        this.m_lstObstacles = lstObstacles;
        this.m_largeurMap = largeurMap;
        this.m_donjon = new String[m_largeurMap][m_largeurMap];

    }


    public String getDonjon(){

        String map = "";
        map += "     ";
        for(int i = 0; i<  m_largeurMap; i++ ){

            char c = (char) (65 + i%26);

            map += c ;
            map += "  ";

        }
        map += "\n";


        for(int i = 0; i<  m_largeurMap; i++ ){

            map += i+" ";
            int nbChiffres = String.valueOf(Math.abs(i)).length();
            for(int j =0; j < 3 - nbChiffres; j++){
                map += " ";
            }

            for(int j = 0; j<  m_largeurMap; j++ ) {
                map += m_donjon[i][j];
            }
            map += "\n";
        }

        return map;
    }

    public void genererDonjon(){

        for(int i = 0; i<this.m_largeurMap; i++){
            for(int j = 0; j<this.m_largeurMap; j++){
                this.m_donjon[i][j] = " . ";
            }
        }
        //On genere les creatures, objets, obstacles dans la map
        for(Map.Entry<Entite, int[]> entry : m_dictEntite.entrySet()) {
            this.m_donjon[(Entite) entry.getValue()][entry.getValue()[1]] = entry.getKey().getNom().substring(0,3);
        }
        for(Map.Entry<Equipement, int[]> entry : m_dictEquipement.entrySet()) {
            this.m_donjon[entry.getValue()[0]][entry.getValue()[1]] = " * ";
        }
        for(int[] coo : m_lstObstacles) {
            this.m_donjon[coo[0]][coo[1]] = " []";
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

    public LinkedHashMap<Entite, int[]> getEntitee(){
        return m_dictEntite;
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

    public boolean verfifierDeplacement(int[] coo, int vitesse, int distance){
        for(Map.Entry<Entite, int[]> entry : m_dictEntite.entrySet()) {
            if(entry.getValue() == coo){
                return false;
            }
        }
        for(Map.Entry<Equipement, int[]> entry : m_dictEquipement.entrySet()) {
            if(entry.getValue() == coo){
                return false;
            }
        }
        for(int[] coo2 : m_lstObstacles) {
            if(coo2 == coo){
                return false;
            }
        }
        if(vitesse<distance){
            return false;
        }

        return true;
    }
}
