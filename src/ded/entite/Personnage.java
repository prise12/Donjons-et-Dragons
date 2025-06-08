package ded.entite;

import ded.Des;
import ded.entite.classe.Classe;
import ded.entite.race.Race;
import ded.objet.Arme;
import ded.objet.Armure;
import ded.objet.Equipement;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Classe représentant un personnage jouable dans le jeu.
 * Hérite d’Entite et possède des équipements, une classe, une race,
 * ainsi qu’un inventaire d’objets.
 */
public class Personnage extends Entite {

    // ===================== ATTRIBUTS =====================
    private Optional<Arme> m_armeEquipe;
    private Optional<Armure> m_armureEquipe;
    private ArrayList<Equipement> m_inventaire;
    private Classe m_classe;
    private Race m_race;

    // ===================== CONSTRUCTEUR =====================

    /**
     * Crée un personnage avec un nom, des coordonnées, une classe et une race.
     * Applique les bonus de classe/race et initialise l’inventaire avec l’équipement de la classe.
     * Les caractéristiques sont augmentées par un 4d4 + 3.
     */
    public Personnage(String nom, int[] coo, Classe classe, Race race) {
        // on initalise les caractéristiques avec les bonus des classes et des races
        super(nom, coo, new int[]{race.getVie() + classe.getVie(), race.getVitesse(), race.getForce(), race.getDexterite(), race.getInitiative()});
        this.m_classe = classe;
        this.m_race = race;

        // on ajoute le 4d4 et les 3 points par défaut
        int desCara = Des.lancerMulti(4, 4);
        this.m_vitesse += desCara + 3;
        this.m_force += desCara + 3;
        this.m_dexterite += desCara + 3;
        this.m_initiative += desCara + 3;
        this.m_inventaire = m_classe.getEquipement();
        this.m_classeArmure = 0;
    }

    // ===================== MÉTHODES DE COMBAT =====================

    /**
     * Calcule le jet d’attaque total selon le type d’arme équipée (force ou dextérité).
     * @return score d'attaque
     */
    public int getAttaque() {
        if (m_armeEquipe.get().getType() == Arme.Type.COURANTE || m_armeEquipe.get().getType() == Arme.Type.GUERRE) {
            return Des.lancerBonus(0, 20, this.m_force + this.m_armeEquipe.get().getBonusMagique());
        } else if (m_armeEquipe.get().getType() == Arme.Type.COURANTE) {
            return Des.lancerBonus(0, 20, this.m_dexterite);
        }
        return 0;
    }

    /**
     * Calcule les dégâts infligés par l’arme équipée.
     * @return score de dégâts
     */
    public int getDegat() {
        return Des.lancerBonus(m_armeEquipe.get().getDegat()[0], m_armeEquipe.get().getDegat()[1], this.m_armeEquipe.get().getBonusMagique());
    }

    // ===================== MÉTHODES D’INVENTAIRE =====================

    /**
     * Ajoute un équipement à l’inventaire du personnage.
     */
    public void recupererEquipement(Equipement equipement) {
        m_inventaire.add(equipement);
    }

    /**
     * Équipe un objet de l’inventaire (arme ou armure).
     * L’équipement actuel est renvoyé dans l’inventaire.
     */
    public void equiperEquipement(int index) {
        if (this.m_inventaire.get(index) instanceof Arme arme) {
            this.m_inventaire.add(this.m_armeEquipe.get());
            this.m_armeEquipe = Optional.of(arme);
        } else if (this.m_inventaire.get(index) instanceof Armure armure) {
            this.m_inventaire.add(this.m_armureEquipe.get());
            this.m_armureEquipe = Optional.of(armure);
            this.m_classeArmure = armure.getClasse();
        }
    }

    // ===================== ACCESSEURS =====================

    /**
     * @return la classe du personnage
     */
    public Classe getClasse() {
        return this.m_classe;
    }

    /**
     * @return classe d’armure actuelle (0 si aucune armure équipée)
     */
    public int getClasseArmureEquipe() {
        return this.m_armureEquipe.isPresent() ? this.m_armureEquipe.get().getClasse() : 0;
    }

    /**
     * @return portée de l’arme équipée (0 si aucune)
     */
    public int getPorteeArmeEquipe() {
        return this.m_armeEquipe.isPresent() ? this.m_armeEquipe.get().getPortee() : 0;
    }

    /**
     * @return arme actuellement équipée (null si aucune)
     */
    public Arme getArmeEquipe() {
        return !(this.m_armeEquipe == null) ? this.m_armeEquipe.get() : null;
    }

    /**
     * @return armure actuellement équipée (null si aucune)
     */
    public Armure getArmureEquipe() {
        return !(this.m_armureEquipe == null) ? this.m_armureEquipe.get() : null;
    }

    /**
     * @return inventaire du personnage
     */
    public ArrayList<Equipement> getInventaire() {
        return this.m_inventaire;
    }

    /**
     * @return vitesse effective (réduite par le poids de l’armure/arme)
     */
    @Override
    public int getVitesse() {
        int vitesseBase = this.m_vitesse;
        int ralentissementArmure = (this.m_armureEquipe != null && this.m_armureEquipe.isPresent()) ? this.m_armureEquipe.get().getRalentissement() : 0;
        int ralentissementArme = (this.m_armeEquipe != null && this.m_armeEquipe.isPresent()) ? this.m_armeEquipe.get().getRalentissement() : 0;

        int vitesseFinale = vitesseBase - ralentissementArmure - ralentissementArme;
        return Math.max(vitesseFinale, 0);
    }

    // ===================== MÉTHODES DIVERS =====================

    /**
     * Soigne le personnage d’un certain nombre de points de vie.
     * @param pv nombre de PV à récupérer
     */
    public void guerison(int pv) {
        this.m_vie += pv;
    }
}
