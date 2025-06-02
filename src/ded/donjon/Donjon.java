package ded.donjon;

import ded.entite.Entite;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Donjon {

    private ArrayList<Entite> m_lstEntite;
    private ArrayList<Equipement> m_lstEquipement;
    private ArrayList<int[]> m_lstObstacle;
    private String[][] m_donjon;
    private int[] m_dimensionMap;
    
    public Donjon(ArrayList<Entite> lstEntite, ArrayList<Equipement> lstEquipement, ArrayList<int[]> lstObstacles, int[] dimensionMap) {
        
        this.m_lstEntite= lstEntite;
        this.m_lstEquipement = lstEquipement;
        this.m_lstObstacle = lstObstacles;
        this.m_dimensionMap = dimensionMap;
        //si les dimensions de map sont hors des limites autorisé la map est mise a dimension (25,25)
        if(dimensionMap[0] > 25 || dimensionMap[1] > 25){
            this.m_dimensionMap = new int[]{25,25};
        }
        this.m_donjon = new String[dimensionMap[0]][dimensionMap[1]];
    }


    public String getDonjon(){

        String map = "";
        map += "     ";
        for(int i = 0; i<  m_dimensionMap[1]; i++ ){
            char c = (char) (65 + i);
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
        for(int[] coo : m_lstObstacle) {
            this.m_donjon[coo[0]][coo[1]] = " []";
        }

    }

    public boolean addEntitee(Entite entite) {
        //on verfifie que les coordonée peuvent etre contenue dans la map et qu'il n'y ai pas autre chose à cette endrois
        if(entite.getCoo()[0] > this.m_dimensionMap[0] || entite.getCoo()[1] > this.m_dimensionMap[1] || ! this.verfifierCoo(entite.getCoo())) {
            return false;
        }
        int[] coo = entite.getCoo();
        if(coo[0] < this.m_dimensionMap[0] && coo[1] < this.m_dimensionMap[1] ) {

            this.m_lstEntite.add(entite);
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }
        return true;
    }



    public boolean addEquipement(Equipement equipement) {

        if(equipement.getCoo()[0] > this.m_dimensionMap[0] || equipement.getCoo()[1] > this.m_dimensionMap[1] || ! this.verfifierCoo(equipement.getCoo())) {
            return false;
        }
        int[] coo = equipement.getCoo();
        if(coo[0] < this.m_dimensionMap[0] && coo[1] < this.m_dimensionMap[1] ) {
            this.m_lstEquipement.add(equipement);
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }
        return true;
    }

    public boolean addObstacles(int[] coo) {
        if(coo[0] > this.m_dimensionMap[0] || coo[1] > this.m_dimensionMap[1] || ! this.verfifierCoo(coo)){
            return false;
        }
        if(coo[0] < this.m_dimensionMap[0] && coo[1] < this.m_dimensionMap[1] ) {
            this.m_donjon[coo[0]][coo[1]] = "[]";
        }
        else {
            throw new IllegalArgumentException("Les coordonées doivent êtres contenue dans la map.");
        }
        return true;
    }

    public boolean verfifierCoo(int[] coo){
        for(int[] coo2 : m_lstObstacle) {
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

        return true;
    }

    public void arrangerObstacles(int nbEquipement, int nbObstacles) {

        for(int i = 0; i<nbEquipement; i++){
            if(! this.m_lstEquipement.contains(i)){}
        }
    }

    public int[] getDimensionMap() {
        return this.m_dimensionMap;
    }
}
