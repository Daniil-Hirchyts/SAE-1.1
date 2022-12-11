import java.util.Arrays;
import java.util.Scanner;

public class MasterMindExtended {

    public static int lireInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String lireString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static int[] initTab(int nb, int val) {
        int[] tab = new int[nb];
        for (int i = 0; i < nb; i++) tab[i] = val;
        return tab;
    }
    public static int[] copieTab(int[] tab) {
        int[] c = new int[tab.length];
        for (int i = 0; i < tab.length; i++) c[i] = tab[i];
        return c;
    }
    public static String listElem(char[] t) {
        String l = "(";
        for (int i = 0; i < t.length; i++) {
            l += t[i];
            if (i < t.length - 1) l += ",";
        }
        return l + ")";
    }
    public static int plusGrandIndice(char[] t, char c) {
        int i = t.length - 1;
        while (i >= 0 && t[i] != c) i--;
        return i;
    }

    public static boolean estPresent(char[] t, char c) {
        return plusGrandIndice(t, c) != -1;
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
    public static boolean sontEgaux(int[] t1, int[] t2) {
        for (int i = 0; i < t1.length; i++) if (t1[i] != t2[i]) return false;
        return true;
    }

    public static int[] codeAleat(int lgCode, int nbCouleurs) {
        int[] t = new int[lgCode];
        for (int i = 0; i < lgCode; i++) t[i] = (int) (Math.random() * nbCouleurs);
        return t;
    }

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
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
        int[] t = new int[codMot.length()];
        for (int i = 0; i < codMot.length(); i++)
            for (int j = 0; j < tabCouleurs.length; j++) if (codMot.charAt(i) == tabCouleurs[j]) t[i] = j;
        return t;
    }
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
        String codMot = "";// code saisi par l'utilisateur
        do {
            System.out.println("Proposition N°" + (nbCoups + 1) + " : ");
            codMot = lireString();
        } while (!codeCorrect(codMot, lgCode, tabCouleurs));
        return motVersEntiers(codMot, tabCouleurs);
    }
    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        int nb = 0;
        for (int i = 0; i < cod1.length; i++) if (cod1[i] == cod2[i]) nb++;
        return nb;
    }
    public static int[] tabFrequence(int[] cod, int nbCouleurs) {
        int[] t = new int[nbCouleurs];
        for (int j : cod) t[j]++;
        return t;
    }
    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] t1 = tabFrequence(cod1, nbCouleurs);
        int[] t2 = tabFrequence(cod2, nbCouleurs);
        int nb = 0;
        for (int i = 0; i < nbCouleurs; i++) nb += Math.min(t1[i], t2[i]);
        return nb;
    }
    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] t = new int[2];
        t[0] = nbBienPlaces(cod1, cod2);
        t[1] = nbCommuns(cod1, cod2, nbCouleurs) - t[0];
        return t;
    }
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int nbCoups = 0;
        int[] cod1 = codeAleat(lgCode, tabCouleurs.length);
        int[] cod2 = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
        while (nbCoups < (nbEssaisMax - 1) && !Arrays.equals(cod1, cod2)) {
            int[] t = nbBienMalPlaces(cod1, cod2, tabCouleurs.length);
            System.out.println("Bien placés : " + t[0] + " Mal placés : " + t[1]);
            nbCoups++;
            cod2 = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
        }
        if (nbCoups == nbEssaisMax - 1) System.out.println("Perdu ! Le code était : " + entiersVersMot(cod1, tabCouleurs));
        else System.out.println("Gagné en " + (nbCoups + 1) + " coups !");
        return nbCoups + 1;
    }
    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
        String s = "";
        for (int i : cod) s += tabCouleurs[i];
        return s;
    }
    public static boolean repCorrecte(int[] rep, int lgCode) {
        if (rep[0] < 0 || rep[1] < 0 || rep[0] + rep[1] > lgCode) {
            System.out.println("Réponse incorrecte : " + rep[0] + " bien placé(s) et " + rep[1] + " mal placé(s)");
            return false;
        }
        return true;
    }
    public static int[] reponseHumain(int lgCode) {
        int[] rep = new int[2];
        do {
            System.out.print("Nombre de codes bien placés : ");
            rep[0] = lireInt(); // nombre de codes bien placés
            System.out.print("Nombre de codes mal placés : ");
            rep[1] = lireInt(); // nombre de codes mal placés
        } while (!repCorrecte(rep, lgCode));
        return rep;
    }
    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
        int n = cod1.length;
        int i = n - 1;
        while (i >= 0 && cod1[i] == nbCouleurs - 1) {
            cod1[i] = 0;
            i--;
        }
        if (i >= 0) {
            cod1[i]++;
            return true;
        } else {
            for (int j = 0; j < n; j++) cod1[j] = 0;
            return false;
        }
    }
    public static boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean bool = true;
        int[] nbBienMal = new int[2];
        for (int i = 0; i < nbCoups; i++) {
            nbBienMal = nbBienMalPlaces(cod[i], cod1, nbCouleurs);
            if (nbBienMal[0] != rep[i][0] || nbBienMal[1] != rep[i][1]) bool = false;
        }
        return bool;
    }
    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean bool = false;
        while (!bool) {
            bool = passeCodeSuivantLexico(cod1, nbCouleurs);
            if (bool) bool = estCompat(cod1, cod, rep, nbCoups, nbCouleurs);
        }
        return bool;

    }
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int nbCouleurs = tabCouleurs.length;
        int[][] cod = new int[nbEssaisMax][lgCode];
        int[][] rep = new int[nbEssaisMax][2];
        int nbCoups = 0;
        int[] cod1 = new int[lgCode];
        int[] reponse = new int[2];
        int nbEssais = 0;
        boolean bool = true;
        while (nbCoups < nbEssaisMax && bool) {
            System.out.println("Essai n°" + (nbCoups + 1) + " de la manche n°" + numManche);
            System.out.println(entiersVersMot(cod1, tabCouleurs));
            reponse = reponseHumain(lgCode);
            if (reponse[0] == lgCode) bool = false;
            if (bool) {
                cod[nbCoups] = copieTab(cod1);
                rep[nbCoups] = reponse;
                nbCoups++;
                bool = passeCodeSuivantLexicoCompat(cod1, cod, rep, nbCoups, nbCouleurs);
            }
        }
        if (nbCoups == nbEssaisMax && bool) nbEssais = nbEssaisMax + 1;
        else nbEssais = nbCoups + 1;
        return nbEssais;
    }
    public static int saisirEntierPositif() {
        int n;
        do {
            System.out.print("Saisir un entier strictement positif : ");
            n = lireInt();
        } while (n <= 0);
        return n;
    }
    public static int saisirEntierPairPositif() {
        int n;
        do {
            System.out.print("Saisir un entier pair strictement positif : ");
            n = lireInt();
        } while (n <= 0 || n % 2 != 0);
        return n;
    }
    public static char[] saisirCouleurs() {
        int n;
        do {
            System.out.print("Saisir le nombre de couleurs strictement positif entre 4 et 6 : ");
            n = lireInt();
        } while (n <= 0);
        char[] tabCouleurs = new char[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Saisir le nom de la couleur n°" + (i + 1) + " : ");
            tabCouleurs[i] = lireString().charAt(0);
            for (int j = 0; j < i; j++) {
                if (tabCouleurs[i] == tabCouleurs[j]) {
                    System.out.println("Erreur : l'initiale du nom de la couleur n°" + (i + 1) + " est déjà utilisée");
                    i--;
                    break;
                }
            }
        }
        return tabCouleurs;
    }
    public static void main(String[] args) {
        System.out.println("Pour commencer, veuillez saisir les paramètres de la partie");
        System.out.println("⚙️Longueur du code secret");
        int lgCode = saisirEntierPositif();
        System.out.println("⚙️Couleurs");
        char[] tabCouleurs = saisirCouleurs();
        System.out.println("⚙️Nombre de manches");
        int nbManches = saisirEntierPairPositif();
        System.out.println("⚙️Nombre d'essais maximum par manche");
        int nbEssaisMax = saisirEntierPositif();
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        //Les paramètres de la partie finalement choisis
        System.out.println("Les paramètres de la partie sont :");
        System.out.println("Longueur du code secret : " + lgCode);
        System.out.println("Couleurs : " + Arrays.toString(tabCouleurs));
        System.out.println("Nombre de manches : " + nbManches);
        System.out.println("Nombre d'essais maximum par manche : " + nbEssaisMax);
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        //Début de la partie
        System.out.println("La partie commence 🚩!");
        int[] score = new int[2];
        for (int i = 0; i < nbManches; i++) {
            System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
            System.out.println("Manche " + (i + 1) + " :");
            if (i % 2 == 0) score[0] += mancheOrdinateur(lgCode, tabCouleurs, i + 1, nbEssaisMax);
            else score[1] += mancheHumain(lgCode, tabCouleurs, i + 1, nbEssaisMax);
        }
        if (score[0] < score[1]) System.out.println("L'ordinateur a gagné la partie avec un score de " + score[0] + " points ⭐️!");
        else if (score[0] > score[1]) System.out.println("Le joueur humain a gagné la partie avec un score de " + score[1] + " points ⭐️!");
        else if ((score[0] == score[1])) System.out.println("La partie est nulle 🚫");
    }
} // fin de la classe Mastermind
