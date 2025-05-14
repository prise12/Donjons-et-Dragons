import DED.Entite.Personnage;

import java.io;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Donjon {

    LinkedHashMap<Creature, int[] > m_dictEntitee;
    LinkedHashMap<Objet, int[] > m_dictObjet;
    int[][] m_tabObstacles;

    string[][] m_donjon;
    int m_largeurMap;


    public void afficherDonjon(int nMap){
        writer.println(" A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W\n");
        writer.println("*-----------------------------------------------------------------------*\n");
        for(int i = 0; i<  m_largeurMap; i++ ){

            Console.print(i+" ");
            for(int j = 0; j<  m_largeurMap; j++ ) {
                Console.print(m_donjon[i][j]);

            }
            Console.print("\n");
        }
    }

    }

    public void genererDonjon(){

        for(int i = 0; i< m_largeurMap; i++ ){
            for(int j = 0; j <  m_largeurMap; j++ ){;
                m_donjon[i][j] = ".";
            }
        }

        //On genere les creatures, objets, obstacles dans la map
        for(Map.Entry<Creature, Integer[]> entry : m_dictEntitee.entrySet()) {
            m_donjon[entry.getvalue()[0]][entry.getvalue()[1]] = entry.getValue().getNom().substring(0,2);
        }
        for(Map.Entry<Creature, Integer[]> entry : m_dictObjet.entrySet()) {
            m_donjon[entry.getvalue()[0]][entry.getvalue()[1]] = entry.getValue().getNom().substring(0,2);
        }
        for(int[] coo : m_tabObstacles) {
            m_donjon[coo[0]][coo[1]] = "[]";
        }



        for(int i = 0; i< m_largeurMap; i++ ){
            writer.print(i+" ");
            for(int j = 0; j<  m_largeurMap; j++ ){;
                writer.println(m_donjon[i][j]);
            }
        }

        writer.close();

    }




}
