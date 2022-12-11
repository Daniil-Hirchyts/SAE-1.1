public class Couleur {
    public static char[] tabCouleurs;

    public static void initTabCouleurs(char[] tabCouleurs) {
        Couleur.tabCouleurs = tabCouleurs;
    }

    public static int getNbCouleurs() {
        return tabCouleurs.length;
    }

    public static char entierVersCouleur(int i) {
        return tabCouleurs[i];
    }

    public static char[] getTabCouleurs() {
        return tabCouleurs;
    }

    public static void setTabCouleurs(char[] tabCouleurs) {
        Couleur.tabCouleurs = tabCouleurs;
    }

    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
        int[] t = new int[codMot.length()];
        for (int i = 0; i < codMot.length(); i++)
            for (int j = 0; j < tabCouleurs.length; j++) if (codMot.charAt(i) == tabCouleurs[j]) t[i] = j;
        return t;
    }
}
