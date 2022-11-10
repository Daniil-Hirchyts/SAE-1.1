import java.util.Scanner;

public class MasterMindBase {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................

    // fonctions classiques sur les tableaux

    //______________________________________________
    /**
     *
     * @param nb nombre d'éléments du tableau
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param lgCode : longueur du code
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
     *
     * @param codMot code à deviner
     * @param lgCode longueur du code
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
     *
     * @param codMot code à deviner
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
     *
     * @param lgCode longueur du code
     * @param tabCouleurs tableau de caractères contenant les couleurs
     * @param nbCoups nombre de coups joués
     * @return le code saisi sous forme de tableau d'entiers
     * @Pré-requis: lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0
     */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
        Scanner scanner = new Scanner(System.in);
        String codMot = "";
        do {
            System.out.println("Proposition N°" + (nbCoups + 1) + " : ");
            codMot = scanner.nextLine();
        } while (!codeCorrect(codMot, lgCode, tabCouleurs));
        return motVersEntiers(codMot, tabCouleurs);
    }

    //____________________________________________________________
    /**
     *
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
     *
     * @param cod code à deviner
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
     *
     * @param cod1 code à deviner
     * @param cod2 code proposé
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
     *
     * @param cod1 code à deviner
     * @param cod2 code proposé
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
     *
     * @param numManche numéro de la manche
     * @param nbEssaisMax nombre maximum d'essais
     * @param lgCode longueur du code
     * @param tabCouleurs tableau de caractères contenant les couleurs
     * @return le nombre de codes proposés par le joueur humain
     * @Pré-requis: numManche >= 1, nbEssaisMax > 0, lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0
     */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int[] cod = codeAleat(lgCode, tabCouleurs.length);
        int nbEssais = 0;
        int[] prop;
        int[] res;
        do {
            prop = propositionCodeHumain(nbEssais, lgCode, tabCouleurs);
            res = nbBienMalPlaces(cod, prop, tabCouleurs.length);
            System.out.println("Résultat : " + res[0] + " bien placé(s) et " + res[1] + " mal placé(s)");
            nbEssais++;
        } while (res[0] != lgCode && nbEssais < nbEssaisMax);
        if (res[0] == lgCode) return nbEssais;
        else return nbEssaisMax + nbEssais;
    }

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    //____________________________________________________________
    /**
     *
     * @param cod code à transformer
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
     *
     * @param rep tableau de 2 entiers
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
    //TODO : reponseHumain

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir les nombres de bien et mal placés,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : les réponses du joueur humain dans un tableau à 2 entiers
     */
    public static int[] reponseHumain(int lgCode) {
        return null;
    }

    //___________________________________________________________________
    //TODO : passeCodeSuivantLexico

    /**
     * pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     * action : met dans cod1 le code qui le suit selon l'ordre lexicographique dans l'ensemble
     * des codes de longueur cod1.length à valeurs de 0 à nbCouleurs-1, si ce code existe
     * résultat : vrai ssi l'action a pu être effectuée
     */
    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
        return false;
    }

    //___________________________________________________________________
    //TODO : estCompat

    /**
     * pré-requis : cod est une matrice, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length
     * et  nbCoups < rep.length
     * résultat : vrai ssi cod[nbCoups] est compatible avec les nbCoups premières lignes de cod et de rep,
     * c'est-à-dire que si cod[nbCoups] était le code secret, les réponses aux nbCoups premières
     * propositions de cod seraient les nbCoups premières réponses de rep
     */
    public static boolean estCompat(int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        return false;
    }

    //___________________________________________________________________
    //TODO : passePropSuivante

    /**
     * pré-requis : cod est une matrice, rep est une matrice à 2 colonnes, 0 < nbCoups < cod.length
     * et nbCoups < rep.length
     * action : met dans cod[nbCoups] le plus petit code (selon l'ordre lexicographique dans l'ensemble
     * des codes de longueur cod[0].length à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
     * cod[nbCoups-1] selon cet ordre et compatible avec les nbCoups premières lignes de cod et de rep,
     * si ce code existe
     * résultat : vrai ssi l'action a pu être effectuée
     */
    public static boolean passePropSuivante(int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        return false;
    }

    //___________________________________________________________________

    // manche Ordinateur
    //TODO : mancheOrdinateur

    /**
     * pré-requis : numManche >= 2
     * action : effectue la (numManche)ème  manche où l'humain est le codeur et l'ordinateur le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * résultat :
     * - 0 si le programme détecte une erreur dans les réponses du joueur humain
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par l'ordinateur
     */
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        return 0;
    }

    //___________________________________________________________________

    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................

    //TODO : saisirEntierPositif

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir un entier strictement positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : l'entier strictement positif saisi
     */
    public static int saisirEntierPositif() {
        return 0;
    }

    //___________________________________________________________________
    //TODO : saisirEntierPairPositif

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir un entier pair strictement positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : l'entier pair strictement positif saisi
     */
    public static int saisirEntierPairPositif() {
        return 0;
    }

    //___________________________________________________________________
    //TODO : saisirCouleurs

    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir le nombre de couleurs (strictement positif),
     * puis les noms de couleurs aux initiales différentes,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * résultat : le tableau des initiales des noms de couleurs saisis
     */
    public static char[] saisirCouleurs() {
        return null;
    }

    //___________________________________________________________________

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................

    /**
     * action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs,
     * nbManches, nbEssaisMax),
     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     * La longueur d'un code et le nombre de couleurs doivent être strictement positifs.
     * Le nombre de manches doit être un nombre pair strictement positif.
     * Les initiales des noms de couleurs doivent être différentes.
     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    public static void main(String[] args) {

//        //Test initTab (int, int): int[]
//        int[] tab = initTab(5, 2);
//        for (int j : tab) System.out.print(j + " ");

//        //Test copieTab (int[]): int []
//        int[] tab = {1, 2, 3, 4, 5};
//        int[] tab2 = copieTab(tab);
//        for (int j : tab2) System.out.print(j + " ");

//        //Test listElem (char [I): String
//        char[] tab = {'a', 'b', 'c', 'd'};
//        System.out.println(listElem(tab));

//        //Test plusGrandIndice (char [], char): int
//        char[] tab = {'a', 'b', 'c', 'd'};
//        System.out.println(plusGrandIndice(tab, 'b'));

//        //Test estPresent (char [], char): boolean
//        char[] tab = {'a', 'b', 'c', 'd'};
//        System.out.println(estPresent(tab, 'b'));

//        //Test elemDiff (char []): boolean
//        char[] tab = {'a', 'a', 'a', 'd'};
//        System.out.println(elemDiff(tab));

//        //Test sontEgaux(int[], int[]): boolean
//        int[] tab = {1, 2, 3, 4};
//        int[] tab2 = {1, 2, 3, 4};
//        System.out.println(sontEgaux(tab, tab2));

//        //Test codeAleat(int, int): int[]
//        int[] tab = codeAleat(5, 4);
//        for (int j : tab) System.out.print(j + " ");

//        //Test codeCorrect (String, int, char[]): boolean
//        char[] tab = {'a', 'b', 'c', 'd'};
//        System.out.println(codeCorrect("abcd", 4, tab));

//        //Test motVersEntiers(String, char[]): int[]
//        char[] tab = {'a', 'b', 'c', 'd'};
//        int[] tab2 = motVersEntiers("abcd", tab);
//        for (int j : tab2) System.out.print(j + " ");

//        //Test propositionCodeHumain(int, int, char[]): int[]
//        char[] tab = {'a', 'b', 'c', 'd'};
//        int[] tab2 = propositionCodeHumain(4, 4, tab);
//        for (int j : tab2) System.out.print(j + " ");

//        //Test nbBienPlaces(int [], int [I): int
//        int[] tab = {1, 2, 3, 4};
//        int[] tab2 = {1, 2, 3, 4};
//        System.out.println(nbBienPlaces(tab, tab2));

        //Test tabFrequence(int[], int): int[]
        int[] tab = {1, 2, 3, 4};
        int[] tab2 = tabFrequence(tab, 4);
        for (int j : tab2) System.out.print(j + " ");















    }
    // fin main
    //___________________________________________________________________
} // fin MasterMindBase
