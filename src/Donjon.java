import DED.Entite.Personnage;

import java.io;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Donjon {

    LinkedHashMap<Entitee, Pair > m_dictEntitee;
    LinkedHashMap<Objet, Pair > m_dictObjet;

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
        }
    }

    }

    public void genererDonjon(){

        for(int i = 0; i< m_largeurMap; i++ ){
            for(int j = 0; j <  m_largeurMap; j++ ){;
                m_donjon[i][j] = ".";
            }
        }
        for(Map.Entry<Integer, Integer> entry : m_dictEntitee.entrySet()) {

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
