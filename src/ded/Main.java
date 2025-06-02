package ded;

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
        Affichage.MetreEnPlacePeronnage();

        
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        BindingReader reader = new BindingReader(terminal.reader());
        KeyMap<String> keyMap = new KeyMap<>();

        // Mappage des touches
        keyMap.bind("up", "\033[A");    // Flèche haut
        keyMap.bind("down", "\033[B");  // Flèche bas
        keyMap.bind("enter", "\r");     // Entrée

        String[] options = {"Option 1", "Option 2", "Option 3"};
        int index = 0;

        while (true) {
            // Efface l'écran
            terminal.puts(org.jline.utils.InfoCmp.Capability.clear_screen);
            terminal.flush();

            // Affiche les options
            for (int i = 0; i < options.length; i++) {
                if (i == index) {
                    System.out.println("> " + options[i]);
                } else {
                    System.out.println("  " + options[i]);
                }
            }

            // Lit la touche pressée
            String key = reader.readBinding(keyMap);

            if ("up".equals(key)) {
                index = (index - 1 + options.length) % options.length;
            } else if ("down".equals(key)) {
                index = (index + 1) % options.length;
            } else if ("enter".equals(key)) {
                System.out.println("Tu as choisi : " + options[index]);
                break;
            }
        }


    }
}
