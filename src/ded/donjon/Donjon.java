package ded.donjon;

import ded.entite.Entite;
import ded.entite.Monstre;
import ded.entite.Personnage;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Donjon {

    private ArrayList<Entite> m_lstEntite;
    private ArrayList<Equipement> m_lstEquipement ;
    private ArrayList<int[]> m_lstObstacle;
    private String[][] m_donjon;
    private int[] m_dimensionMap;

    public Donjon() {
        this.m_lstEntite = new ArrayList<Entite>();
        this.m_lstEquipement = new ArrayList<Equipement>();
        this.m_lstObstacle = new ArrayList<int[]>();

    }
    public Donjon(int[] dimensionMap) {
        this.m_dimensionMap = dimensionMap;
        this.m_lstEntite = new ArrayList<Entite>();
        this.m_lstEquipement = new ArrayList<Equipement>();
        this.m_lstObstacle = new ArrayList<int[]>();
    }
    public boolean m_finDonjon = false;
    public Donjon(ArrayList<Entite> lstEntite, ArrayList<Equipement> lstEquipement, ArrayList<int[]> lstObstacles, int[] dimensionMap) {
        
        this.m_lstEntite = lstEntite;
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
        this.m_donjon = new String[m_dimensionMap[0]][m_dimensionMap[1]];
        for(int i = 0; i<this.m_dimensionMap[0]; i++){
            for(int j = 0; j<this.m_dimensionMap[1]; j++){
                this.m_donjon[i][j] = " . ";
            }
        }
        //On genere les creatures, objets, obstacles dans la map
        if(! m_lstEntite.isEmpty()) {
            for (Entite entite : m_lstEntite) {
                String nom = entite.getNom();
                String nomCourt = nom.length() >= 3 ? nom.substring(0, 3) : (" " + nom + " ");
                this.m_donjon[entite.getCoo()[0]][entite.getCoo()[1]] = nomCourt;
            }
        }
        if(! m_lstEquipement.isEmpty()) {
            for (Equipement equipement : m_lstEquipement) {
                this.m_donjon[equipement.getCoo()[0]][equipement.getCoo()[1]] = " * ";
            }
        }
        if(! m_lstObstacle.isEmpty()) {
            for(int[] coo : m_lstObstacle) {
                this.m_donjon[coo[0]][coo[1]] = " []";
            }
        }

    }

    public void addEntitee(Entite entite) {
        //on verfifie que les coordonée peuvent etre contenue dans la map et qu'il n'y ai pas autre chose à cette endrois
        this.m_lstEntite.add(entite);
    }



    public void addEquipement(Equipement equipement) {
                this.m_lstEquipement.add(equipement);
    }

    public void addObstacles(int[] coo) {
        this.m_lstObstacle.add(coo);
    }

    public boolean verfifierCoo(int[] coo) {
        if(coo[0] > m_dimensionMap[0]-1 || coo[1] > m_dimensionMap[1]-1 || coo[0] < 0 || coo[1] < 0) {
            return false;
        }

        for(int[] coo2 : this.m_lstObstacle) {
            if(coo2[0] == coo[0] && coo2[1] == coo[1]) {
                return false;
            }
        }

        for(Entite entite: this.m_lstEntite) {
            if(entite.getCoo()[0] == coo[0] && entite.getCoo()[1] == coo[1]) {
                return false;
            }
        }

        for(Equipement equipement : this.m_lstEquipement) {
            if(equipement.getCoo()[0] == coo[0] && equipement.getCoo()[1] == coo[1]) {
                return false;
            }
        }

        return true;
    }


    public int[] getDimensionMap() {
        return this.m_dimensionMap;
    }

    public void setDiemensionMap(int[] dimensionMap) {
        this.m_dimensionMap = dimensionMap;
        if(this.m_dimensionMap[0] > 25){
            this.m_dimensionMap[0] = 25;
        }
        if(this.m_dimensionMap[1] > 25){
            this.m_dimensionMap[1] = 25;
        }
    }
    public ArrayList<Entite> getLstEntite() {return this.m_lstEntite;}
    public void setLstEntite(ArrayList<Entite> lstEntite) {this.m_lstEntite = lstEntite;}

    public ArrayList<Entite> getOrdreEntite(){
        //trie des entite en fonction de leurs initative
        ArrayList<Entite> lstOrdreEntite = new ArrayList<>(this.m_lstEntite);
        lstOrdreEntite.sort((e1, e2) -> Integer.compare(e2.getInitiative(), e1.getInitiative()));
        return lstOrdreEntite;

    }

    public Entite getCase(int[] coo) {
        if(!m_lstEntite.isEmpty()) {
            for (Entite entite : m_lstEntite) {
                if(entite.getCoo()[0] == coo[0] && entite.getCoo()[1] == coo[1]) {
                    return entite;
                }
            }
        }
        return null;
    }

    public boolean touche(Entite entite1, Entite entite2, int portee){
        if(Math.abs(entite1.getCoo()[0]-entite2.getCoo()[0]) <= portee && Math.abs(entite1.getCoo()[1]-entite2.getCoo()[1]) <= portee ){
            return true;
        }
        return false;
    }

    public boolean isFinDonjon() {
        boolean toutPersonnage = true;
        boolean monstreVivant = false;

        for (Entite entite : this.m_lstEntite) {
            if (entite instanceof Personnage && entite.getVie() <= 0) {
                toutPersonnage = false;
            } else if (entite instanceof Monstre && entite.getVie() > 0) {
                monstreVivant = true;
            }
        }
        return !toutPersonnage || !monstreVivant;
    }

}
