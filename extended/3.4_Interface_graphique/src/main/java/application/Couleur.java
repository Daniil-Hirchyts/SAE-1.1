package application;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Couleur {

    private static char[] tabCouleurs;
    private int nbCouleurs;
    private String couleursJeu;
    private List<Color> COULEURS;
    private List<Color> CHECK_COULEURS;
    private Map<Character, Color> codeColorMap;
    private Map<Character, Color> codeCheckColorMap;

    public Couleur(int nbCouleurs, String couleursJeu) {
        this.nbCouleurs = nbCouleurs;
        this.couleursJeu = couleursJeu;

        COULEURS = new ArrayList<>();
        setCOULEURS(nbCouleurs, couleursJeu);

        codeColorMap = new HashMap<>();
        for (int i = 0; i < nbCouleurs; i++) codeColorMap.put((char) (i + 1), COULEURS.get(i + 1));
    }

    public Couleur() {
        CHECK_COULEURS = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CHECK_COULEURS.add(Color.BLACK);
            CHECK_COULEURS.add(Color.WHITE);
            CHECK_COULEURS.add(Color.rgb(180, 180, 180));
        }


        codeCheckColorMap = new HashMap<>();
        for (int i = 0; i < 3; i++) codeCheckColorMap.put((char) (i + 1), CHECK_COULEURS.get(i + 1));
    }

    private void setCOULEURS(int nbCouleurs, String couleursJeu) {
        for (int i = 0; i < nbCouleurs; i++) {
            for (int j = 0; j < couleursJeu.length(); j++) {
                switch (couleursJeu.charAt(j)) {
                    case 'r' -> COULEURS.add(Color.rgb(216, 52, 52));
                    case 'b' -> COULEURS.add(Color.rgb(52, 134, 240));
                    case 'v' -> COULEURS.add(Color.rgb(114, 206, 55));
                    case 'j' -> COULEURS.add(Color.rgb(240, 209, 52));
                    case 'o' -> COULEURS.add(Color.rgb(240, 130, 42));
                    case 'n' -> COULEURS.add(Color.rgb(32, 32, 32));
                    default -> {
                    }
                }
            }
        }
    }

    public List<Color> getCOULEURS() {
        return COULEURS;
    }

    public Map<Character, Color> getCodeColorMap() {
        return codeColorMap;
    }

    public List<Color> getCHECK_COULEURS() {
        return CHECK_COULEURS;
    }

    public Map<Character, Color> getCodeCheckColorMap() {
        return codeCheckColorMap;
    }
}
