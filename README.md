Projet Donjon & Dragon : Application de Jeu de Rôle

Ce projet vise à développer une application pour un jeu de rôle de type Medieval Fantasy, inspiré de D&D. Les joueurs incarnent des personnages qui affrontent des monstres contrôlés par un Maître du Jeu (MJ) à travers plusieurs donjons.

Organisation

    Travail: En binôme.

    Dépôt: Fork du projet dans un groupe nommé kempf-giorgipanazzolo.

    Durée: 5 semaines.

    Phases: Phase 1 dévoilée le 5 mai, Phase 2 le 19 mai.

Calendrier

Événement	Date
Début du projet	Lundi 5 mai
Rendu Intermédiaire (Phase 1)	Dimanche 18 mai, minuit
Rendu Final	Dimanche 8 Juin, minuit
Soutenances	Du 11 au 13 juin
Rendus Hebdomadaires	Chaque dimanche, minuit

Le Jeu

Le jeu se déroule au tour par tour et utilise des jets de dés (<nombre de dés>d<nombre de faces>). L'objectif est d'éliminer tous les monstres de trois donjons successifs. La partie est perdue si un seul personnage est éliminé.

Personnages

Chaque personnage est défini par :

    Identité : Nom, Race (Humain, Nain, Elfe, Halfelin), Classe (Guerrier, Clerc, Magicien, Roublard).

    Caractéristiques : Points de Vie (PV), Force, Dextérité, Vitesse, Initiative.

    Équipements : Inventaire illimité, un seul emplacement pour une Arme et une Armure portées.

Impact des Caractéristiques :

    PV : Dégâts maximums supportés.

    Force : Bonus aux attaques au corps-à-corps.

    Dextérité : Bonus aux attaques à distance.

    Vitesse : Distance de déplacement maximale par action.

    Initiative : Bonus pour déterminer l'ordre de jeu.

Les Races confèrent des bonus fixes aux caractéristiques de base. Le port d'armes de guerre ou d'armures lourdes modifie la Vitesse et/ou la Force.

Monstres

Les monstres sont contrôlés par le MJ et possèdent :

    Identité : Espèce, Numéro.

    Attaque : Portée et Dégâts (jet de dés).

    Caractéristiques : PV, Vitesse, Force (ou Dextérité), Classe d'Armure (CA), Initiative.

    Ils ne possèdent pas d'équipement.

Déroulement d'un Donjon

    Mise en Place : Le MJ crée la carte (15x15 à 25x25 cases) avec obstacles et équipements, puis positionne joueurs et monstres. L'ordre de jeu est déterminé par le jet d'initiative (1d20+Initiative).

    Tour de Jeu : Chaque participant effectue jusqu'à 3 actions par tour, dans l'ordre d'initiative.

        S'équiper (Perso) : Changer l'arme ou l'armure portée.

        Se Déplacer : Mouvement sur une distance ≤ Vitesse / 3 cases.

        Attaquer : Jet d'attaque (1d20+Force/Dexteˊriteˊ). Si le résultat est supérieur à la CA de la cible, les dégâts de l'arme sont infligés.

        Ramasser (Perso) : Récupérer un équipement présent sur la case.

    Fin de Donjon : Victoire si tous les monstres sont éliminés (les PV sont restaurés), défaite si un joueur est tué.

Exigences de Rendu

Les rendus hebdomadaires doivent être effectués sur une branche nommée rendu<numéro-rendu>.

Structure du Dépôt

```text
.
├── Phase_2.md
├── README.md
├── src
│   └── ded
│       ├── affichage
│       │   ├── AffichageMiseEnPlace.java
│       │   └── AffichageTour.java
│       ├── Des.java
│       ├── donjon
│       │   ├── Donjon.java
│       │   └── DonjonParDefault.java
│       ├── entite
│       │   ├── classe
│       │   │   ├── Classe.java
│       │   │   ├── Clerc.java
│       │   │   ├── Guerrier.java
│       │   │   ├── Magicien.java
│       │   │   └── Roublard.java
│       │   ├── Entite.java
│       │   ├── Espece.java
│       │   ├── Monstre.java
│       │   ├── Personnage.java
│       │   └── race
│       │       ├── Elfe.java
│       │       ├── Halfelin.java
│       │       ├── Humain.java
│       │       ├── Nain.java
│       │       └── Race.java
│       ├── Main.java
│       └── objet
│           ├── Arme.java
│           ├── Armure.java
│           └── Equipement.java
├── tests
└── uml
    └── semaine3.puml

