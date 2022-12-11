import java.util.Arrays;
import java.util.Scanner;

public class MasterMindBase {

    private static final Scanner scanner = new Scanner(System.in);

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................

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
        System.arraycopy(tab, 0, c, 0, tab.length);
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

    /*
     * Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
     * lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents fonctions sur les codes pour la manche Humain
     */

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

    //____________________________________________________________

    /**
     * @param lgCode     : longueur du code
     * @param nbCouleurs : nombre de couleurs
     * @return un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
     * @Pré-requis: lgCode > 0, nbCouleurs > 0
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs) {
        int[] t = new int[lgCode];
        for (int i = 0; i < lgCode; i++) t[i] = (int) (Math.random() * nbCouleurs);
        return t;
    }

    //____________________________________________________________

    /**
     * @param codMot      code à deviner
     * @param lgCode      longueur du code
     * @param tabCouleurs tableau de caractères contenant les couleurs
     * @return vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
     * @Pré-requis: lgCode > 0, tabCouleurs.length > 0
     */
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

    //____________________________________________________________

    /**
     * @param codMot      code à deviner
     * @param tabCouleurs tableau de caractères contenant les couleurs
     * @return le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     * @Pré-requis: les caractères de codMot sont des éléments de tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
        int[] t = new int[codMot.length()];
        for (int i = 0; i < codMot.length(); i++)
            for (int j = 0; j < tabCouleurs.length; j++) if (codMot.charAt(i) == tabCouleurs[j]) t[i] = j;
        return t;
    }

    //____________________________________________________________

    /**
     * @param lgCode      longueur du code
     * @param tabCouleurs tableau de caractères contenant les couleurs
     * @param nbCoups     nombre de coups joués
     * @return le code saisi sous forme de tableau d'entiers
     * @Pré-requis: lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0
     */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
        String codMot = "";
        do {
            System.out.println("Proposition N°" + (nbCoups + 1) + " : ");
            codMot = scanner.nextLine();
        } while (!codeCorrect(codMot, lgCode, tabCouleurs));
        return motVersEntiers(codMot, tabCouleurs);
    }

    //____________________________________________________________

    /**
     * @param cod1 code à deviner
     * @param cod2 code proposé
     * @return le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     * @Pré-requis: cod1.length = cod2.length
     */
    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        int nb = 0;
        for (int i = 0; i < cod1.length; i++) if (cod1[i] == cod2[i]) nb++;
        return nb;
    }

    //____________________________________________________________

    /**
     * @param cod        code à deviner
     * @param nbCouleurs nombre de couleurs
     * @return un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
     * @Pré-requis: les éléments de cod sont des entiers de 0 à nbCouleurs-1
     */
    public static int[] tabFrequence(int[] cod, int nbCouleurs) {
        int[] t = new int[nbCouleurs];
        for (int j : cod) t[j]++;
        return t;
    }

    //____________________________________________________________

    /**
     * @param cod1       code à deviner
     * @param cod2       code proposé
     * @param nbCouleurs nombre de couleurs
     * @return le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
     * @Pré-requis: les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     */
    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] t1 = tabFrequence(cod1, nbCouleurs);
        int[] t2 = tabFrequence(cod2, nbCouleurs);
        int nb = 0;
        for (int i = 0; i < nbCouleurs; i++) nb += Math.min(t1[i], t2[i]);
        return nb;
    }

    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................

    //____________________________________________________________

    /**
     * @param cod1       code à deviner
     * @param cod2       code proposé
     * @param nbCouleurs nombre de couleurs
     * @return un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
     * se trouvant  (resp. ne se trouvant pas) au même indice
     * @Pré-requis: cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     */
    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] t = new int[2];
        t[0] = nbBienPlaces(cod1, cod2);
        t[1] = nbCommuns(cod1, cod2, nbCouleurs) - t[0];
        return t;
    }

    //____________________________________________________________

    /**
     * @param numManche   numéro de la manche
     * @param nbEssaisMax nombre maximum d'essais
     * @param lgCode      longueur du code
     * @param tabCouleurs tableau de caractères contenant les couleurs
     * @return le nombre de codes proposés par le joueur humain
     * @Pré-requis: numManche >= 1, nbEssaisMax > 0, lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0
     */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int nbCoups = 0;
        int nbEssais = 0;
        int[] cod1 = codeAleat(lgCode, tabCouleurs.length);
        int[] cod2 = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
        do {
            int[] t = nbBienMalPlaces(cod1, cod2, tabCouleurs.length);
            System.out.println("Bien placés : " + t[0] + " Mal placés : " + t[1]);
            nbCoups++;
            cod2 = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
        }
        while (nbCoups < (nbEssaisMax - 1) && !sontEgaux(cod1, cod2));
        if (nbCoups == nbEssaisMax - 1) {
            System.out.println("Le joueur humain a perdu, le code était : " + entiersVersMot(cod1, tabCouleurs));
            int[] t = nbBienMalPlaces(cod1, cod2, tabCouleurs.length);
            nbEssais = nbEssaisMax + t[1] + 2 * (lgCode - (t[0] + t[1]));
        } else {
            nbEssais = nbCoups + 1;
            System.out.println("Le joueur humain a gagné cette mache en " + nbEssais + " essais");
        }
        System.out.println("L'ordinateur a gagné " + nbEssais + " points dans cette manche!");
        return nbEssais;
    }

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    //____________________________________________________________

    /**
     * @param cod         code à transformer
     * @param tabCouleurs tableau de caractères contenant les couleurs
     * @return le code cod sous forme de mot d'après le tableau tabCouleurs
     * @Pré-requis: cod.length > 0, les éléments de cod sont des entiers de 0 à tabCouleurs.length-1, tabCouleurs.length > 0
     */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
        String s = "";
        for (int i : cod) s += tabCouleurs[i];
        return s;
    }

    //___________________________________________________________________

    /**
     * @param rep    tableau de 2 entiers
     * @param lgCode longueur du code
     * @return vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
     * @Pré-requis: rep.length = 2
     */
    public static boolean repCorrecte(int[] rep, int lgCode) {
        if (rep[0] < 0 || rep[1] < 0 || rep[0] + rep[1] > lgCode) {
            System.out.println("Réponse incorrecte : " + rep[0] + " bien placé(s) et " + rep[1] + " mal placé(s)");
            return false;
        }
        return true;
    }

    //___________________________________________________________________

    /**
     * @param lgCode longueur du code
     * @return le tableau de 2 entiers contenant le nombre de codes bien et mal placés
     * @Pré-requis: lgCode > 0
     */
    public static int[] reponseHumain(int lgCode) {
        int[] rep = new int[2];
        do {
            System.out.print("Nombre de codes bien placés : ");
            rep[0] = scanner.nextInt();
            System.out.print("Nombre de codes mal placés : ");
            rep[1] = scanner.nextInt();
        } while (!repCorrecte(rep, lgCode));
        return rep;
    }

    //___________________________________________________________________

    /**
     * CHANGE : action si le code suivant n'existe pas
     * pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     * action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
     * des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
        for (int i = cod1.length - 1; i >= 0; i--) {
            if (cod1[i] != nbCouleurs - 1) {
                cod1[i]++;
                return true;
            }
            Arrays.fill(cod1, i, i+1, 0);
        }
        return false;
    }

    //___________________________________________________________________

    /**
     * CHANGE : ajout du paramètre cod1 et modification des spécifications
     * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     * résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
     * c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
     * propositions de cod seraient les nbCoups premières réponses de rep resp.
     */
    public static boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean bool = true;
        int[] nbBienMal = new int[2];
        for (int i = 0; i < nbCoups; i++) {
            nbBienMal = nbBienMalPlaces(cod[i], cod1, nbCouleurs);
            if (nbBienMal[0] != rep[i][0] || nbBienMal[1] != rep[i][1]) bool = false;
        }
        return bool;
    }

    //___________________________________________________________________

    /**
     * CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat,
     * ajout du paramètre cod1 et modification des spécifications
     * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     * action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
     * des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
     * cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean bool = false;
        while (!bool) {
            bool = passeCodeSuivantLexico(cod1, nbCouleurs);
            if (bool) bool = estCompat(cod1, cod, rep, nbCoups, nbCouleurs);
        }
        return bool;
    }

    //___________________________________________________________________

    /**
     * @param lgCode      longueur du code
     * @param tabCouleurs tableau de couleurs
     * @param numManche   numéro de la manche
     * @param nbEssaisMax nombre d'essais maximum
     * @return 0 si le programme détecte une erreur dans les réponses du joueur humain, un nombre supérieur à nbEssaisMax,
     * calculé à partir du dernier essai de l'ordinateur (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais,
     * sinon le nombre de codes proposés par l'ordinateur
     * @Pré-requis: numManche >= 2
     * @Action: effectue la (numManche)ème manche où l'humain est le codeur et l'ordinateur le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     */
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int nbCouleurs = tabCouleurs.length;
        int[][] cod = new int[nbEssaisMax][lgCode];
        int[][] rep = new int[nbEssaisMax][2];
        int nbCoups = 0;
        int[] cod1 = new int[lgCode];
        int[] reponse = new int[2];
        int nbEssais = 0;
        boolean bool = true;
        do {
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
        while (nbCoups < nbEssaisMax && bool);
        if (nbCoups == nbEssaisMax && bool) nbEssais = nbEssaisMax + reponse[1] + 2 * (lgCode - (reponse[0] + reponse[1]));
        else nbEssais = nbCoups + 1;
        System.out.println("Le joueur humain a gagné " + nbEssais + " points dans cette manche!");
        return nbEssais;
    }

    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................

    //___________________________________________________________________

    /**
     * @return l'entier strictement positif saisi
     * @Action: demande au joueur humain de saisir un entier strictement positif, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     */
    public static int saisirEntierPositif() {
        int n;
        do {
            System.out.print("Saisir un entier strictement positif : ");
            n = scanner.nextInt();
        } while (n <= 0);
        return n;
    }

    //___________________________________________________________________

    /**
     * @return l'entier pair strictement positif saisi
     * @Action: demande au joueur humain de saisir un entier pair strictement positif, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     */
    public static int saisirEntierPairPositif() {
        int n;
        do {
            System.out.print("Saisir un entier pair strictement positif : ");
            n = scanner.nextInt();
        } while (n <= 0 || n % 2 != 0);
        return n;
    }

    //___________________________________________________________________

    /**
     * @return le tableau des initiales des noms de couleurs saisis
     * @Action: demande au joueur humain de saisir le nombre de couleurs (strictement positif),
     * puis les noms de couleurs aux initiales différentes,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     */
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

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................

    //___________________________________________________________________

    /**
     * @Action: demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs, nbManches, nbEssaisMax),
     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     * @Pré-requis: la longueur d'un code et le nombre de couleurs doivent être strictement positifs.
     * Le nombre de manches doit être un nombre pair strictement positif.
     * Les initiales des noms de couleurs doivent être différentes.
     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    public static void main(String[] args) {
        System.out.print("═════════════════════════════════════════════════════════════════════════════════════════ \n");
        System.out.print(
                """
                        ███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗     ███╗   ███╗██╗███╗   ██╗██████╗
                        ████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗    ████╗ ████║██║████╗  ██║██╔══██╗
                        ██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝    ██╔████╔██║██║██╔██╗ ██║██║  ██║
                        ██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗    ██║╚██╔╝██║██║██║╚██╗██║██║  ██║
                        ██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║    ██║ ╚═╝ ██║██║██║ ╚████║██████╔╝
                        ╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝\s
                        """);
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("                                        Réalisé par : Daniil HIRCHYTS & Youssera OULMEKKI");
        System.out.println("                                 IUT Montpellier-Sète, Département Informatique 2022-2023");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        System.out.println("Pour commencer, veuillez saisir les paramètres de la partie");
        System.out.print("Longueur du code secret : ");
        int lgCode = saisirEntierPositif();
        System.out.print("\n" + "Couleurs : ");
        char[] tabCouleurs = saisirCouleurs();
        System.out.print("\n" + "Nombre de manches : ");
        int nbManches = saisirEntierPairPositif();
        System.out.print("\n" + "Nombre d'essais maximum par manche : ");
        int nbEssaisMax = saisirEntierPositif();
        System.out.println("\n" + "═════════════════════════════════════════════════════════════════════════════════════════");

        System.out.println("Les paramètres de la partie sont :");
        System.out.println("Longueur du code secret : " + lgCode);
        System.out.println("Couleurs : " + Arrays.toString(tabCouleurs));
        System.out.println("Nombre de manches : " + nbManches);
        System.out.println("Nombre d'essais maximum par manche : " + nbEssaisMax);
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        System.out.println("La partie commence!");
        int[] score = new int[2];
        for (int i = 0; i < nbManches; i++) {
            System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
            System.out.println("Manche " + (i + 1) + " :");
            if (i % 2 == 1) score[1] += mancheOrdinateur(lgCode, tabCouleurs, i + 1, nbEssaisMax); //score[1] = score du joueur humain
            else score[0] += mancheHumain(lgCode, tabCouleurs, i + 1, nbEssaisMax); //score[0] = score de l'ordinateur
        }

        if (score[0] < score[1]) System.out.println("Le joueur humain a gagné la partie avec un score de " + score[1] + " points!");
        else if (score[0] > score[1]) System.out.println("L'ordinateur a gagné la partie avec un score de " + score[0] + " points!");
        else System.out.println("La partie est nulle!");
    }
}
