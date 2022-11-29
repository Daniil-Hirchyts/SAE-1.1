import java.util.Scanner;

public class UtMM {
    //______________________________________________

    /**
     * @return scanne un entier
     */
    public static int lireInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String lireString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    //______________________________________________

    /**
     * @param nb  nombre d'éléments du tableau
     * @param val valeur à affecter à chaque élément
     * @return tableau de nb cases contenant la valeur val
     * @Pré-requis: nb >= 0
     */
    public static int[] initTab(int nb, int val) {
        int[] tab = new int[nb];
        for (int i = 0; i < nb; i++) tab[i] = val;
        return tab;
    }

    //______________________________________________

    /**
     * @param tab tableau d'entiers
     * @return copie de tab
     * @Pré-requis: tab != null
     */
    public static int[] copieTab(int[] tab) {
        int[] c = new int[tab.length];
        for (int i = 0; i < tab.length; i++) c[i] = tab[i];
        return c;
    }

    //______________________________________________

    /**
     * @param t tableau d'entiers
     * @return la liste des éléments de t entre parenthèses et séparés par des virgules
     * @Pré-requis: t != null
     */
    public static String listElem(char[] t) {
        String l = "(";
        for (int i = 0; i < t.length; i++) {
            l += t[i];
            if (i < t.length - 1) l += ",";
        }
        return l + ")";
    }

    //______________________________________________

    /**
     * @param t tableau de caractères
     * @param c charactère à compter
     * @return le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
     * @Pré-requis: t != null
     */
    public static int plusGrandIndice(char[] t, char c) {
        int i = t.length - 1;
        while (i >= 0 && t[i] != c) i--;
        return i;
    }

    //______________________________________________

    /**
     * @param t tableau de caractères
     * @param c : caractère
     * @return vrai ssi c est un élément de t
     * @Pré-requis: t != null
     */
    public static boolean estPresent(char[] t, char c) {
        return plusGrandIndice(t, c) != -1;
    }

    //______________________________________________

    /**
     * @param t tableau de caractères
     * @return vrai ssi les éléments de t sont différents
     * @Pré-requis: t != null
     */
    public static boolean elemDiff(char[] t) {
        for (int i = 0; i < t.length; i++) {
            if (estPresent(t, t[i]) && plusGrandIndice(t, t[i]) != i) {
                System.out.println("Doublon : " + t[i] + ". Les indices : " + i + " et " + plusGrandIndice(t, t[i]));
                return false;
            }
        }
        return true;
    }

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents
    // fonctions sur les codes pour la manche Humain

    //______________________________________________

    /**
     * @param t1 tableau d'entiers
     * @param t2 tableau d'entiers
     * @return vrai ssi t1 et t2 contiennent la même suite d'entiers
     * @Pré-requis: t1.length == t2.length
     */
    public static boolean sontEgaux(int[] t1, int[] t2) {
        for (int i = 0; i < t1.length; i++) if (t1[i] != t2[i]) return false;
        return true;
    }
}
