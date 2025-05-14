import java.io.*;
public class Donjon {

    Objet[] m_tabObjet;
    Entitee[] m_tabEntitee;

    public void afficherDonjon(int nMap){



    }

    public void genererDonjon(Entitee[] tabEntitee, Objet[] tabObjet){
        String fileName = "my-file.txt";
        String encoding = "UTF-8";

        PrintWriter writer = new PrintWriter(fileName, encoding);
        writer.println("The first line");
        writer.println("The second line");
        writer.close();

    }




}
