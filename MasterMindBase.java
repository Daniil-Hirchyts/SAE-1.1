public class MasterMindBase {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................

    // fonctions classiques sur les tableaux
    //______________________________________________
    //TODO : initTab
    /**
      pré-requis : nb >= 0
      résultat : un tableau de nb entiers égaux à val
     */
    public static int[] initTab(int nb, int val) {
        return null;
    }

    //______________________________________________
    //TODO : copieTab
    /**
      pré-requis : aucun
      résultat : une copie de tab
     */
    public static int[] copieTab(int[] tab) {
        return null;
    }

    //______________________________________________
    //TODO : listElem
    /**
      pré-requis : aucun
      résultat : la liste des éléments de t entre parenthèses et séparés par des virgules
     */
    public static String listElem(char[] t) {
        return null;
    }
    //______________________________________________
    //TODO : plusGrandIndice
    /**
      pré-requis : aucun
      résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
     */
    public static int plusGrandIndice(char[] t, char c) {
        return 0;
    }

    //______________________________________________
    //TODO : estPresent
    /**
      pré-requis : aucun
      résultat : vrai ssi c est un élément de t
      stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean estPresent(char[] t, char c) {
        return false;
    }

    //______________________________________________
    //TODO : elemDiff
    /**
     * pré-requis : aucun
     * action : affiche un doublon et 2 de ses indices dans t s'il en existe
     * résultat : vrai ssi les éléments de t sont différents
     * stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean elemDiff(char[] t) {
        return false;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain
    //TODO : sontEgaux
    /**
     * pré-requis : t1.length = t2.length
     * résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2) {
        return false;
    }

    //____________________________________________________________
    //TODO : codeAleat
    /**
     * pré-requis : aucun
     * résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs) {
        return null;
    }

    //____________________________________________________________
    //TODO : codeCorrect
    /**
     * pré-requis : aucun
     * action : si codMot n'est pas correct, affiche pourquoi
     * résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {
        return false;
    }

    //____________________________________________________________
    //TODO : motVersEntiers
    /**
     * pré-requis : les caractères de codMot sont des éléments de tabCouleurs
     * résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
        return null;
    }

    //____________________________________________________________
    //TODO : propositionCodeHumain
    /**
     * pré-requis : aucun
     * action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce
     * qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
     * résultat : le code saisi sous forme de tableau d'entiers
     */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
        return null;
    }

    //____________________________________________________________
    //TODO : nbBienPlaces
    /**
     * pré-requis : cod1.length = cod2.length
     * résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
     */
    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        return 0;
    }

    //____________________________________________________________
    //TODO : tabFrequence
    /**
     * pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
     * résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
     * Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
     */
    public static int[] tabFrequence(int[] cod, int nbCouleurs) {
        return null;
    }

    //____________________________________________________________
    //TODO : nbCommuns
    /**
     * pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     * résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
     */
    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        return 0;
    }

    //____________________________________________________________

    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................
    //TODO : nbBienMalPlaces
        /**
     * pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     * résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
     * se trouvant  (resp. ne se trouvant pas) au même indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 bien placé (le "0" à l'indice 3)
     * et 2 mal placés (1 "0" et 1 "1")
     */
    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        return null;
    }
    //TODO : mancheHumain
/**
     * pré-requis : numMache >= 1
     * action : effectue la (numManche)ème manche où l'ordinateur est le codeur et l'humain le décodeur
     * (le paramètre numManche ne sert que pour l'affichage)
     * résultat :
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par le joueur humain
     */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        return 0;
    }

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................
    //TODO : entiersVersMot
    /**
     * pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
     * résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
     */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
        return null;
    }

    //___________________________________________________________________
    //TODO : repCorrecte
    /**
     * pré-requis : rep.length = 2
     * action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont
     * les nombres de bien et mal placés resp.
     * résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
     */
    public static boolean repCorrecte(int[] rep, int lgCode) {
        return false;
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


    } // fin main

    //___________________________________________________________________

} // fin MasterMindBase
