package ded;

import ded.entite.Entite;
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
    private int[] m_dimensionMap;
    
    public Donjon(ArrayList<Entite> lstEntite, ArrayList<Equipement> lstEquipement, ArrayList<int[]> lstObstacles, int[] dimensionMap) {
        
        this.m_lstEntite= lstEntite;
        this.m_lstEquipement = lstEquipement;
        this.m_lstObstacles = lstObstacles;
        this.m_dimensionMap = dimensionMap;
        this.m_donjon = new String[m_dimensionMap[0]][m_dimensionMap[1]];

    }


    public String getDonjon(int tour){

        String map = "";
        map += "     ";
        for(int i = 0; i<  m_dimensionMap[1]; i++ ){

            char c = (char) (65 + i%26);

            map += c ;
            map += "  ";

        }
        map += "\n";


        for(int i = 0; i<  m_dimensionMap[0]; i++ ){

            map += i+" ";
            int nbChiffres = String.valueOf(Math.abs(i)).length();
            for(int j =0; j < 3 - nbChiffres; j++){
                map += " ";
            }

            for(int j = 0; j<  m_dimensionMap[1]; j++ ) {
                map += m_donjon[i][j];
            }
            map += "\n";
        }

        return map;
    }

    public void genererDonjon(){

        for(int i = 0; i<this.m_dimensionMap[0]; i++){
            for(int j = 0; j<this.m_dimensionMap[1]; j++){
                this.m_donjon[i][j] = " . ";
            }
        }
        //On genere les creatures, objets, obstacles dans la map
        for(Entite entite : m_lstEntite) {
            this.m_donjon[entite.getCoo()[0]][entite.getCoo()[1]] = entite.getNom().substring(0,3);
        }
        for(Equipement equipement : m_lstEquipement) {
            this.m_donjon[equipement.getCoo()[0]][equipement.getCoo()[1]] = " * ";
        }
        for(int[] coo : m_lstObstacles) {
            this.m_donjon[coo[0]][coo[1]] = " []";
        }

    }

    public void addEntitee(Entite entite) {
       int[] coo = entite.getCoo();
        if(coo[0] < this.m_dimensionMap[0] && coo[1] < this.m_dimensionMap[1] ) {

            this.m_lstEntite.add(entite);
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }

    }



    public void addEquipement(Equipement equipement) {

        int[] coo = equipement.getCoo();
        if(coo[0] < this.m_dimensionMap[0] && coo[1] < this.m_dimensionMap[1] ) {
            this.m_lstEquipement.add(equipement);
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }
    }

    public void addObstacles(int[] coordonées) {
        if(coordonées[0] < this.m_dimensionMap[0] && coordonées[1] < this.m_dimensionMap[1] ) {
            this.m_donjon[coordonées[0]][coordonées[1]] = "[]";
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }
    }

    public boolean verfifierDeplacement(int[] coo, int vitesse, int distance){
        for(int[] coo2 : m_lstObstacles) {
            if(coo2 == coo){
                return false;
            }
        }
        for(Entite entite: m_lstEntite) {
            if(entite.getCoo() == coo){
                return false;
            }
        }
        for(Equipement equipement : m_lstEquipement) {
            if(equipement.getCoo() == coo){
                return false;
            }
        }
        if(vitesse<distance){
            return false;
        }

        return true;
    }
}
