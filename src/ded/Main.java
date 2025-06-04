package ded;

import ded.donjon.Donjon;
import ded.donjon.DonjonParDefault;
import ded.entite.Entite;
import ded.entite.Personnage;
import ded.objet.Arme;
import ded.objet.Equipement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {
    public static void main(String[] args) throws IOException {
        Donjon d1 = new Donjon();
        ArrayList<Personnage> lstPersonnage = new ArrayList<>();
        //Affichage.MetreEnPlacePersonnage(lstPersonnage);
        Affichage.MetreEnPlaceDonjon(d1);

    }
}
