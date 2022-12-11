import java.util.Scanner;

public class UtMM {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {
        if (codMot.length() != lgCode) {
            System.out.println("La longueur du code doit être : " + lgCode);
            return false;
        }
        for (int i = 0; i < codMot.length(); i++) {
            if (!estPresent(tabCouleurs, codMot.charAt(i))) {
                System.out.println("Le code ne doit contenir que des éléments : " + listElem(tabCouleurs));
                return false;
            }
        }
        return true;
    }


    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
        String codMot = "";
        do {
            System.out.println("Proposition N°" + (nbCoups + 1) + " : ");
            codMot = scanner.nextLine();
        } while (!codeCorrect(codMot, lgCode, tabCouleurs));
        return Couleur.motVersEntiers(codMot, tabCouleurs);
    }

    public static boolean estPresent(char[] t, char c) {
        return plusGrandIndice(t, c) != -1;
    }

    public static int plusGrandIndice(char[] t, char c) {
        int i = t.length - 1;
        while (i >= 0 && t[i] != c) i--;
        return i;
    }

    public static String listElem(char[] t) {
        String l = "(";
        for (int i = 0; i < t.length; i++) {
            l += t[i];
            if (i < t.length - 1) l += ",";
        }
        return l + ")";
    }

    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] t = new int[2];
        t[0] = nbBienPlaces(cod1, cod2);
        t[1] = nbCommuns(cod1, cod2, nbCouleurs) - t[0];
        return t;
    }

    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] t1 = tabFrequence(cod1, nbCouleurs);
        int[] t2 = tabFrequence(cod2, nbCouleurs);
        int nb = 0;
        for (int i = 0; i < nbCouleurs; i++) nb += Math.min(t1[i], t2[i]);
        return nb;
    }

    public static int[] tabFrequence(int[] cod, int nbCouleurs) {
        int[] t = new int[nbCouleurs];
        for (int j : cod) t[j]++;
        return t;
    }

    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        int nb = 0;
        for (int i = 0; i < cod1.length; i++) if (cod1[i] == cod2[i]) nb++;
        return nb;
    }

    public static String codeVersMot(int[] code, char[] tabCouleurs) {
        String codMot = "";
        for (int i : code) codMot += tabCouleurs[i];
        return codMot;
    }

    public static int saisirEntier() {
        return scanner.nextInt();
    }

    public static boolean repCorrecte(int[] rep, int lgCode) {
        if (rep[0] < 0 || rep[1] < 0 || rep[0] + rep[1] > lgCode) {
            System.out.println("Réponse incorrecte : " + rep[0] + " bien placé(s) et " + rep[1] + " mal placé(s)");
            return false;
        }
        return true;
    }

    public static int saisirEntierPositif() {
        int n;
        do {
            System.out.print("Saisir un entier strictement positif : ");
            n = scanner.nextInt();
        } while (n <= 0);
        return n;
    }

    public static int saisirEntierPairPositif() {
        int n;
        do {
            System.out.print("Saisir un entier pair strictement positif : ");
            n = scanner.nextInt();
        } while (n <= 0 || n % 2 != 0);
        return n;
    }

    public static char[] saisirCouleurs() {
        boolean egaux = true;
        int n;
        do {
            System.out.print("Saisir le nombre de couleurs strictement positif entre 4 et 6 : ");
            n = scanner.nextInt();
        } while (n <= 0);
        char[] tabCouleurs = new char[n];
        do {
            for (int i = 0; i < n; i++) {
                System.out.print("Saisir le nom de la couleur n°" + (i + 1) + " : ");
                tabCouleurs[i] = scanner.next().charAt(0);
            }
            if (elemDiff(tabCouleurs)) egaux = false;
            else System.out.println("Les premières lettres des noms de couleurs doivent être différentes");
        } while (egaux);
        return tabCouleurs;
    }

    public static boolean elemDiff(char[] t) {
        for (int i = 0; i < t.length; i++) {
            if (estPresent(t, t[i]) && plusGrandIndice(t, t[i]) != i) {
                System.out.println("Doublon : " + t[i] + ". Les indices : " + i + " et " + plusGrandIndice(t, t[i]));
                return false;
            }
        }
        return true;
    }

    public static int[] copieTab(int[] tab) {
        int[] c = new int[tab.length];
        System.arraycopy(tab, 0, c, 0, tab.length);
        return c;
    }

    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
        String s = "";
        for (int i : cod) s += tabCouleurs[i];
        return s;
    }
}
