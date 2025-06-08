package ded.donjon;

import ded.entite.Entite;
import ded.entite.Monstre;
import ded.entite.Personnage;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Classe représentant un donjon dans le jeu
 */
public class Donjon {
    // ===================== ATTRIBUTS =====================
    private ArrayList<Entite> m_lstEntite;
    private ArrayList<Equipement> m_lstEquipement;
    private ArrayList<int[]> m_lstObstacle;
    private String[][] m_donjon;
    private int[] m_dimensionMap;
    public boolean m_finDonjon = false;

    // ===================== CONSTRUCTEURS =====================
    /**
     * Crée un donjon vide avec des dimensions par défaut
     */
    public Donjon() {
        this.m_lstEntite = new ArrayList<Entite>();
        this.m_lstEquipement = new ArrayList<Equipement>();
        this.m_lstObstacle = new ArrayList<int[]>();
    }

    /**
     * Crée un donjon vide avec les dimensions spécifiées
     * @param dimensionMap dimensions du donjon [largeur, hauteur] (max 25x25)
     */
    public Donjon(int[] dimensionMap) {
        if(dimensionMap[0] > 25 || dimensionMap[1] > 25) {
            this.m_dimensionMap = new int[]{25,25};
        }
        this.m_lstEntite = new ArrayList<Entite>();
        this.m_lstEquipement = new ArrayList<Equipement>();
        this.m_lstObstacle = new ArrayList<int[]>();
    }

    /**
     * Crée un donjon avec des entités, équipements et obstacles spécifiés
     * @param lstEntite liste des entités présentes dans le donjon
     * @param lstEquipement liste des équipements présents dans le donjon
     * @param lstObstacles liste des obstacles présents dans le donjon
     * @param dimensionMap dimensions du donjon [largeur, hauteur] (max 25x25)
     */
    public Donjon(ArrayList<Entite> lstEntite, ArrayList<Equipement> lstEquipement,
                  ArrayList<int[]> lstObstacles, int[] dimensionMap) {
        this.m_lstEntite = lstEntite;
        this.m_lstEquipement = lstEquipement;
        this.m_lstObstacle = lstObstacles;
        this.m_dimensionMap = dimensionMap;
        if(dimensionMap[0] > 25 || dimensionMap[1] > 25) {
            this.m_dimensionMap = new int[]{25,25};
        }
        this.m_donjon = new String[dimensionMap[0]][dimensionMap[1]];
    }

    // ===================== METHODES PUBLIQUES =====================
    /**
     * Génère la représentation textuelle du donjon avec coordonnées et éléments
     * @return chaîne représentant l'état actuel du donjon
     */
    public String getDonjon() {
        StringBuilder map = new StringBuilder();
        map.append("     ");
        for (int i = 0; i < m_dimensionMap[0]; i++) {
            map.append(String.format("%-3s", i));
        }
        map.append("\n");

        for (int i = 0; i < m_dimensionMap[1]; i++) {
            map.append(String.format("%-3d ", i));
            for (int j = 0; j < m_dimensionMap[0]; j++) {
                map.append(String.format("%-3s", m_donjon[j][i]));
            }
            map.append("\n");
        }
        return map.toString();
    }

    /**
     * Génère la carte du donjon avec tous ses éléments
     */
    public void genererDonjon() {
        this.m_donjon = new String[m_dimensionMap[0]][m_dimensionMap[1]];
        for(int i = 0; i<this.m_dimensionMap[0]; i++) {
            for(int j = 0; j<this.m_dimensionMap[1]; j++) {
                this.m_donjon[i][j] = " . ";
            }
        }

        if(!m_lstEntite.isEmpty()) {
            for (Entite entite : m_lstEntite) {
                String nom = entite.getNom();
                String nomCourt = nom.length() >= 3 ? nom.substring(0, 3) : (" " + nom + " ");
                this.m_donjon[entite.getCoo()[0]][entite.getCoo()[1]] = nomCourt;
            }
        }

        if(!m_lstEquipement.isEmpty()) {
            for (Equipement equipement : m_lstEquipement) {
                this.m_donjon[equipement.getCoo()[0]][equipement.getCoo()[1]] = " * ";
            }
        }

        if(!m_lstObstacle.isEmpty()) {
            for(int[] coo : m_lstObstacle) {
                this.m_donjon[coo[0]][coo[1]] = " []";
            }
        }
    }

    // ===================== METHODES D'AJOUT =====================
    /**
     * Ajoute une entité au donjon
     * @param entite entité à ajouter
     */
    public void addEntitee(Entite entite) {
        this.m_lstEntite.add(entite);
    }

    /**
     * Ajoute un équipement au donjon
     * @param equipement équipement à ajouter
     */
    public void addEquipement(Equipement equipement) {
        this.m_lstEquipement.add(equipement);
    }

    /**
     * Ajoute un obstacle au donjon
     * @param coo coordonnées de l'obstacle [x,y]
     */
    public void addObstacles(int[] coo) {
        this.m_lstObstacle.add(coo);
    }

    // ===================== METHODES DE VERIFICATION =====================
    /**
     * Vérifie si des coordonnées sont valides dans le donjon
     * @param coo coordonnées à vérifier [x,y]
     * @return true si les coordonnées sont libres et valides, false sinon
     */
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

    /**
     * Vérifie si une entité peut toucher une autre entité en fonction de la portée
     * @param portee portée de l'attaque
     * @param coo1 coordonnées de l'attaquant [x,y]
     * @param coo2 coordonnées de la cible [x,y]
     * @return true si la cible est à portée, false sinon
     */
    public boolean touche(int portee, int[] coo1, int[] coo2) {
        int dx = coo1[0] - coo2[0];
        int dy = coo1[1] - coo2[1];
        return dx*dx + dy*dy <= portee*portee;
    }

    /**
     * Vérifie si le donjon est terminé (tous les personnages morts ou tous les monstres morts)
     * @return true si le donjon est terminé, false sinon
     */
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

    // ===================== METHODES DE SUPPRESSION =====================
    /**
     * Supprime un équipement du donjon
     * @param equipement équipement à supprimer
     */
    public void supprimerEquipement(Equipement equipement) {
        this.m_lstEquipement.remove(equipement);
    }

    /**
     * Supprime tous les monstres morts du donjon
     */
    public void supprimerMonstre() {
        for (Entite entite : this.m_lstEntite) {
            if (entite instanceof Monstre monstre && monstre.getVie() <= 0) {
                this.m_lstEntite.remove(monstre);
            }
        }
    }

    // ===================== METHODES DE DEPLACEMENT =====================
    /**
     * Déplace une entité dans une direction donnée
     * @param entite entité à déplacer
     * @param direction direction du déplacement [x,y]
     * @param distance distance du déplacement
     * @return true si le déplacement a réussi, false sinon
     */
    public boolean deplacerDirection(Entite entite, int[] direction, int distance) {
        if(0 > distance || distance > entite.getVitesse()) {
            return false;
        }
        int[] newCoo = new int[2];
        newCoo[0] = entite.getCoo()[0] + (direction[0] * distance);
        newCoo[1] = entite.getCoo()[1] + (direction[1] * distance);
        if(this.verfifierCoo(newCoo)) {
            entite.setCoo(newCoo);
            return true;
        }
        return false;
    }

    // ===================== GETTERS/SETTERS =====================
    /**
     * @return dimensions actuelles du donjon [largeur, hauteur]
     */
    public int[] getDimensionMap() {
        return this.m_dimensionMap;
    }

    /**
     * Modifie les dimensions du donjon
     * @param dimensionMap nouvelles dimensions [largeur, hauteur] (max 25x25)
     */
    public void setDiemensionMap(int[] dimensionMap) {
        this.m_dimensionMap = dimensionMap;
        if(this.m_dimensionMap[0] > 25) {
            this.m_dimensionMap[0] = 25;
        }
        if(this.m_dimensionMap[1] > 25) {
            this.m_dimensionMap[1] = 25;
        }
    }

    /**
     * @return liste des entités du donjon
     */
    public ArrayList<Entite> getLstEntite() {
        return this.m_lstEntite;
    }

    /**
     * Modifie la liste des entités du donjon
     * @param lstEntite nouvelle liste d'entités
     */
    public void setLstEntite(ArrayList<Entite> lstEntite) {
        this.m_lstEntite = lstEntite;
    }

    /**
     * @return liste des entités triées par initiative (décroissante)
     */
    public ArrayList<Entite> getOrdreEntite() {
        ArrayList<Entite> lstOrdreEntite = new ArrayList<>(this.m_lstEntite);
        lstOrdreEntite.sort((e1, e2) -> Integer.compare(e2.getInitiative(), e1.getInitiative()));
        return lstOrdreEntite;
    }

    /**
     * Récupère l'entité présente à des coordonnées spécifiques
     * @param coo coordonnées à vérifier [x,y]
     * @return l'entité trouvée ou null si aucune entité
     */
    public Entite getCaseEntite(int[] coo) {
        if(!m_lstEntite.isEmpty()) {
            for (Entite entite : m_lstEntite) {
                if(entite.getCoo()[0] == coo[0] && entite.getCoo()[1] == coo[1]) {
                    return entite;
                }
            }
        }
        return null;
    }

    /**
     * Récupère l'équipement présent à des coordonnées spécifiques
     * @param coo coordonnées à vérifier [x,y]
     * @return l'équipement trouvé ou null si aucun équipement
     */
    public Equipement getCaseEquipement(int[] coo) {
        if(!m_lstEquipement.isEmpty()) {
            for (Equipement equipement : m_lstEquipement) {
                if(equipement.getCoo()[0] == coo[0] && equipement.getCoo()[1] == coo[1]) {
                    return equipement;
                }
            }
        }
        return null;
    }
}