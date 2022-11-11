import java.util.Arrays;
import java.util.Scanner;

public class MasterMindBase {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................

    // fonctions classiques sur les tableaux

    //______________________________________________
    /**
     * @return scanne un entier
     */
    public static int lireInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

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
    /**
     *
     * @param lgCode longueur du code
     * @return le tableau de 2 entiers contenant le nombre de codes bien et mal placés
     * @Pré-requis: lgCode > 0
     */
    public static int[] reponseHumain(int lgCode) {
        int[] rep = new int[2];
        do {
            System.out.print("Nombre de codes bien placés : ");
            rep[0] = lireInt();
            System.out.print("Nombre de codes mal placés : ");
            rep[1] = lireInt();
        } while (!repCorrecte(rep, lgCode));
        return rep;
    }

    //___________________________________________________________________
    /**
     *
     * @param cod1 code à transformer
     * @param nbCouleurs nombre de couleurs
     * @return vrai ssi l'action a pu être effectuée
     * @Pré-requis: cod1.length > 0, les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     */
    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
        int i = cod1.length - 1;
        while (i >= 0 && cod1[i] == nbCouleurs - 1) i--;
        if (i < 0) return false;
        cod1[i]++;
        for (int j = i + 1; j < cod1.length; j++) cod1[j] = 0;
        return true;
    }

    //___________________________________________________________________
    /**
     *
     * @param cod tableau d'entiers de deux dimensions contenant les codes à tester et les résultats associés (cod[i][0] = code, cod[i][1] = nb bien placés, cod[i][2] = nb mal placés)
     * @param rep le tableau de 2 entiers contenant le nombre de codes bien et mal placés de cod
     * @param nbCoups nombre de coups joués
     * @return vrai ssi cod[nbCoups] est compatible avec les nbCoups premières lignes de cod et de rep,
     * c'est-à-dire que si cod[nbCoups] était le code secret, les réponses aux nbCoups premières
     * propositions de cod seraient les nbCoups premières réponses de rep
     * @Pré-requis: cod est une matrice, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length
     * et  nbCoups < rep.length
     */
    public static boolean estCompat(int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        int[] res;
        for (int i = 0; i < nbCoups; i++) {
            res = nbBienMalPlaces(cod[i], cod[nbCoups], nbCouleurs);
            if (res[0] != rep[i][0] || res[1] != rep[i][1]) return false;
        }
        return true;
    }

    //___________________________________________________________________
    /**
     *
     * @param cod tableau d'entiers de deux dimensions contenant les codes à tester et les résultats associés (cod[i][0] = code, cod[i][1] = nb bien placés, cod[i][2] = nb mal placés)
     * @param rep le tableau de 2 entiers contenant le nombre de codes bien et mal placés de cod
     * @param nbCoups nombre de coups joués
     * @param nbCouleurs nombre de couleurs
     * @return vrai ssi l'action a pu être effectuée
     * @Pré-requis: cod est une matrice, rep est une matrice à 2 colonnes, 0 < nbCoups < cod.length
     * et nbCoups < rep.length
     */
    public static boolean passePropSuivante(int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        if (nbCoups == 0) {
            for (int i = 0; i < cod[0].length; i++) cod[nbCoups][i] = 0;
            return true;
        }
        if (!passeCodeSuivantLexico(cod[nbCoups - 1], nbCouleurs)) return false;
        while (!estCompat(cod, rep, nbCoups, nbCouleurs)) {
            if (!passeCodeSuivantLexico(cod[nbCoups - 1], nbCouleurs)) return false;
        }
        for (int i = 0; i < cod[0].length; i++) cod[nbCoups][i] = cod[nbCoups - 1][i];
        return true;
    }

    // manche Ordinateur
    //___________________________________________________________________
    /**
     * @param lgCode longueur du code
     * @param tabCouleurs tableau de couleurs
     * @param numManche numéro de la manche
     * @param nbEssaisMax nombre d'essais maximum
     * @Pré-requis: numManche >= 2
     * @Action: effectue la (numManche)ème manche où l'humain est le codeur et l'ordinateur le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * @return 0 si le programme détecte une erreur dans les réponses du joueur humain, un nombre supérieur à nbEssaisMax,
     * calculé à partir du dernier essai de l'ordinateur (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais,
     * sinon le nombre de codes proposés par l'ordinateur
     */
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int[][] cod = new int[nbEssaisMax][lgCode];
        int[][] rep = new int[nbEssaisMax][2];
        int nbCoups = 0;
        int[] res;
        System.out.println("Manche " + numManche + " : l'ordinateur doit trouver le code secret");
        System.out.println("Le code secret est composé de " + lgCode + " couleurs parmi : " + tabCouleurs);
        System.out.println("L'ordinateur a " + nbEssaisMax + " essais pour trouver le code secret");
        System.out.println("L'ordinateur propose un code");
        for (int i = 0; i < cod[0].length; i++) cod[nbCoups][i] = 0;
        do {
            afficheCode(cod[nbCoups], tabCouleurs);
            rep[nbCoups] = reponseHumain(lgCode);
            if (rep[nbCoups][0] == lgCode) {
                System.out.println("L'ordinateur a trouvé le code secret en " + (nbCoups + 1) + " essais");
                return nbCoups + 1;
            }
            nbCoups++;
            if (nbCoups < nbEssaisMax) {
                System.out.println("L'ordinateur propose un code");
                if (!passePropSuivante(cod, rep, nbCoups, tabCouleurs.length)) {
                    System.out.println("L'ordinateur n'a pas trouvé le code secret");
                    return nbEssaisMax + 1;
                }
            }
        } while (nbCoups < nbEssaisMax);
        System.out.println("L'ordinateur n'a pas trouvé le code secret");
        return nbEssaisMax + 1;
    }

    //___________________________________________________________________
    private static void afficheCode(int[] cod, char[] tabCouleurs) {
        for (int j : cod) {
            System.out.print(tabCouleurs[j]);
        }
        System.out.println();
    }


    //FIXME: manche, codeSecret, evaluerProposition, saisirProposition, saisirCouleur
    private static int manche(int i, int lgCode, char[] tabCouleurs, int nbEssaisMax) {
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("Manche " + i);
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
        int[] codeSecret = codeAleat(lgCode, tabCouleurs.length);
        int nbEssais = 0;
        int[] proposition = new int[lgCode];
        int[] resultat = new int[2];
        while (nbEssais < nbEssaisMax && resultat[0] != lgCode) {
            System.out.println("Essai " + (nbEssais + 1));
            proposition = saisirProposition(lgCode, tabCouleurs);
            resultat = evaluerProposition(proposition, codeSecret);
            System.out.println("Résultat : " + resultat[0] + " couleurs bien placées, " + resultat[1] + " couleurs mal placées");
            nbEssais++;
        }
        if (resultat[0] == lgCode) {
            System.out.println("Bravo, vous avez trouvé le code secret !");
            return 1;
        } else {
            System.out.println("Vous avez perdu, le code secret était : " + Arrays.toString(codeSecret(codeSecret, tabCouleurs)));
            return 0;
        }
    }

    //Déchiffrement de int[] codeSecret en char[]
    private static char[] codeSecret(int[] codeSecret, char[] tabCouleurs) {
        char[] codeSecretChar = new char[codeSecret.length];
        for (int i = 0; i < codeSecret.length; i++) {
            codeSecretChar[i] = tabCouleurs[codeSecret[i]];
        }
        return codeSecretChar;
    }

    private static int[] evaluerProposition(int[] proposition, int[] codeSecret) {
        int[] resultat = new int[2];
        int[] codeSecretCopie = codeSecret.clone();
        for (int i = 0; i < proposition.length; i++) {
            if (proposition[i] == codeSecretCopie[i]) {
                resultat[0]++;
                codeSecretCopie[i] = -1;
                proposition[i] = -2;
            }
        }
        for (int i = 0; i < proposition.length; i++) {
            for (int j = 0; j < codeSecretCopie.length; j++) {
                if (proposition[i] == codeSecretCopie[j]) {
                    resultat[1]++;
                    codeSecretCopie[j] = -1;
                    proposition[i] = -2;
                }
            }
        }
        return resultat;
    }

    private static int[] saisirProposition(int lgCode, char[] tabCouleurs) {
        int[] proposition = new int[lgCode];
        for (int i = 0; i < lgCode; i++) {
            System.out.println("Saisissez la couleur " + (i + 1) + " de votre proposition");
            proposition[i] = saisirCouleur(tabCouleurs);
        }
        return proposition;
    }

    private static int saisirCouleur(char[] tabCouleurs) {
        int couleur = -1;
        while (couleur == -1) {
            Scanner sc = new Scanner(System.in);
            String saisie = sc.nextLine();
            for (int i = 0; i < tabCouleurs.length; i++) {
                if (saisie.charAt(0) == tabCouleurs[i]) {
                    couleur = i;
                }
            }
            if (couleur == -1) {
                System.out.println("Couleur invalide, veuillez saisir une couleur parmi : " + Arrays.toString(tabCouleurs));
            }
        }
        return couleur;
    }

    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................

    //___________________________________________________________________
    /**
     * @Action: demande au joueur humain de saisir un entier strictement positif, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * @return l'entier strictement positif saisi
     */
    public static int saisirEntierPositif() {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            System.out.print("Saisir un entier strictement positif : ");
            n = sc.nextInt();
        } while (n <= 0);
        return n;
    }

    //___________________________________________________________________
    /**
     * @Action: demande au joueur humain de saisir un entier pair strictement positif, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * @return l'entier pair strictement positif saisi
     */
    public static int saisirEntierPairPositif() {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            System.out.print("Saisir un entier pair strictement positif : ");
            n = sc.nextInt();
        } while (n <= 0 || n % 2 != 0);
        return n;
    }

    //___________________________________________________________________
    /**
     * @Action: demande au joueur humain de saisir le nombre de couleurs (strictement positif),
     * puis les noms de couleurs aux initiales différentes,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * @return le tableau des initiales des noms de couleurs saisis
     */
    public static char[] saisirCouleurs() {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            System.out.print("Saisir le nombre de couleurs (strictement positif) : ");
            n = sc.nextInt();
        } while (n <= 0);
        char[] tabCouleurs = new char[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Saisir l'initiale du nom de la couleur n°" + (i + 1) + " : ");
            tabCouleurs[i] = sc.next().charAt(0);
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

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................

    //___________________________________________________________________
    /**
     *
     * @Action: demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs, nbManches, nbEssaisMax),
     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     * @Pré-requis: la longueur d'un code et le nombre de couleurs doivent être strictement positifs.
     * Le nombre de manches doit être un nombre pair strictement positif.
     * Les initiales des noms de couleurs doivent être différentes.
     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    public static void main(String[] args) {

        //Titre du programme
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════ \n");
        System.out.println("███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗     ███╗   ███╗██╗███╗   ██╗██████╗ \n" + "████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗    ████╗ ████║██║████╗  ██║██╔══██╗\n" + "██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝    ██╔████╔██║██║██╔██╗ ██║██║  ██║\n" + "██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗    ██║╚██╔╝██║██║██║╚██╗██║██║  ██║\n" + "██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║    ██║ ╚═╝ ██║██║██║ ╚████║██████╔╝\n" + "╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝ \n" + "                                                                                        ");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("                                        Réalisé par : Daniil HIRCHYTS & Youssera OULMEKKI");
        System.out.println("                                 IUT Montpellier-Sète, Département Informatique 2022-2023");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        //Saisie des paramètres de la partie
        System.out.println("Bienvenue dans le jeu du Mastermind !\n");
        System.out.println("Le but du jeu est de trouver le code secret de l'ordinateur en un nombre limité d'essais.");
        System.out.println("Le code secret est composé de couleurs parmi : Rouge, Bleu, Jaune, Vert, Orange et Noir.\n");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
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
        System.out.println("La partie commence !");
        int[] score = new int[2];
        for (int i = 0; i < nbManches; i++) {
            if (i % 2 == 0) {
                score[0] += manche(i + 1, lgCode, tabCouleurs, nbEssaisMax);
            } else {
                score[1] += manche(i + 1, lgCode, tabCouleurs, nbEssaisMax);
            }
        }
        if (score[0] < score[1]) {
            System.out.println("L'ordinateur a gagné la partie");
        } else if (score[0] > score[1]) {
            System.out.println("Le joueur humain a gagné la partie");
        } else {
            System.out.println("La partie est nulle");
        }
    }
} // fin de la classe Mastermind
