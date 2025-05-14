import DED.Entite.Personnage;

import java.io;
import java.util.HashMap;
import java.util.LinkedHashMap;
public class Donjon {

    LinkedHashMap<Entitee, Pair > m_dictEntitee;
    LinkedHashMap<Objet, Pair > m_dictObjet;
    int[] m_tabLargeurDonjon ;


    public void afficherDonjon(int nMap){
        String fileName = "donjon"+ nMap +".txt";


        Scanner obj = new Scanner(fileName);
        while (obj.hasNextLine()){

            Console.println(obj.nextLine());
        }

    }

    public void genererDonjon(Entitee[] tabEntitee, Objet[] tabObjet, int nMap){
        String fileName = "donjon"+ nMap +".txt";
        PrintWriter writer = new PrintWriter(fileName, encoding);
        writer.println(" A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W\n");
        writer.println("*-----------------------------------------------------------------------* \n");

        for(int i = 0; i< m_tabLargeurDonjon[nMap]; i++ ){
            writer.print(i+" ");
            for(int j = 0; j< m_tabLargeurDonjon[nMap]; j++ ){
                for(Map.entry<Entitee, Couple> entryEntitee : map.entrySet()){
                    if(entryEntitee.getValue() == new Pair<>(i, j) ){
                        writer.print(i+" "+ entryEntitee.getKey().getNom +" ");
                    }
                }

            }
        }

        writer.close();

    }




}
