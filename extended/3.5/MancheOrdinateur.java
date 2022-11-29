//public class MancheOrdinateur {
//
//    /**
//     * @param cod         code à transformer
//     * @param tabCouleurs tableau de caractères contenant les couleurs
//     * @return le code cod sous forme de mot d'après le tableau tabCouleurs
//     * @Pré-requis: cod.length > 0, les éléments de cod sont des entiers de 0 à tabCouleurs.length-1, tabCouleurs.length > 0
//     */
//    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
//        String s = "";
//        for (int i : cod) s += tabCouleurs[i];
//        return s;
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * @param rep    tableau de 2 entiers
//     * @param lgCode longueur du code
//     * @return vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
//     * @Pré-requis: rep.length = 2
//     */
//    public static boolean repCorrecte(int[] rep, int lgCode) {
//        if (rep[0] < 0 || rep[1] < 0 || rep[0] + rep[1] > lgCode) {
//            System.out.println("Réponse incorrecte : " + rep[0] + " bien placé(s) et " + rep[1] + " mal placé(s)");
//            return false;
//        }
//        return true;
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * @param lgCode longueur du code
//     * @return le tableau de 2 entiers contenant le nombre de codes bien et mal placés
//     * @Pré-requis: lgCode > 0
//     */
//    public static int[] reponseHumain(int lgCode) {
//        int[] rep = new int[2];
//        do {
//            System.out.print("Nombre de codes bien placés : ");
//            rep[0] = lireInt(); // nombre de codes bien placés
//            System.out.print("Nombre de codes mal placés : ");
//            rep[1] = lireInt(); // nombre de codes mal placés
//        } while (!repCorrecte(rep, lgCode));
//        return rep;
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * CHANGE : action si le code suivant n'existe pas
//     * ************************************************
//     * pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
//     * action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
//     * des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
//     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
//     */
//    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
//        int n = cod1.length;
//        int i = n - 1;
//        while (i >= 0 && cod1[i] == nbCouleurs - 1) {
//            cod1[i] = 0;
//            i--;
//        }
//        if (i >= 0) {
//            cod1[i]++;
//            return true;
//        } else {
//            for (int j = 0; j < n; j++) cod1[j] = 0;
//            return false;
//        }
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * CHANGE : ajout du paramètre cod1 et modification des spécifications
//     * ********************************************************************
//     * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
//     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
//     * résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
//     * c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
//     * propositions de cod seraient les nbCoups premières réponses de rep resp.
//     */
//    public static boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
//        boolean bool = true;
//        int[] nbBienMal = new int[2];
//        for (int i = 0; i < nbCoups; i++) {
//            nbBienMal = nbBienMalPlaces(cod[i], cod1, nbCouleurs);
//            if (nbBienMal[0] != rep[i][0] || nbBienMal[1] != rep[i][1]) bool = false;
//        }
//        return bool;
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat,
//     * ajout du paramètre cod1 et modification des spécifications
//     * *************************************************************************
//     * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
//     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
//     * action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
//     * des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
//     * cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
//     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
//     */
//    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
//        boolean bool = false;
//        while (!bool) {
//            bool = passeCodeSuivantLexico(cod1, nbCouleurs);
//            if (bool) bool = estCompat(cod1, cod, rep, nbCoups, nbCouleurs);
//        }
//        return bool;
//
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * @param lgCode      longueur du code
//     * @param tabCouleurs tableau de couleurs
//     * @param numManche   numéro de la manche
//     * @param nbEssaisMax nombre d'essais maximum
//     * @return 0 si le programme détecte une erreur dans les réponses du joueur humain, un nombre supérieur à nbEssaisMax,
//     * calculé à partir du dernier essai de l'ordinateur (cf. sujet),
//     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais,
//     * sinon le nombre de codes proposés par l'ordinateur
//     * @Pré-requis: numManche >= 2
//     * @Action: effectue la (numManche)ème manche où l'humain est le codeur et l'ordinateur le décodeur
//     * (le paramètre numManche ne sert que pour l'affichage)
//     */
//    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
//        int nbCouleurs = tabCouleurs.length;
//        int[][] cod = new int[nbEssaisMax][lgCode];
//        int[][] rep = new int[nbEssaisMax][2];
//        int nbCoups = 0;
//        int[] cod1 = new int[lgCode];
//        int[] reponse = new int[2];
//        int nbEssais = 0;
//        boolean bool = true;
//        while (nbCoups < nbEssaisMax && bool) {
//            System.out.println("Essai n°" + (nbCoups + 1) + " de la manche n°" + numManche);
//            System.out.println(entiersVersMot(cod1, tabCouleurs));
//            reponse = reponseHumain(lgCode);
//            if (reponse[0] == lgCode) bool = false;
//            if (bool) {
//                cod[nbCoups] = copieTab(cod1);
//                rep[nbCoups] = reponse;
//                nbCoups++;
//                bool = passeCodeSuivantLexicoCompat(cod1, cod, rep, nbCoups, nbCouleurs);
//            }
//        }
//        if (nbCoups == nbEssaisMax && bool) nbEssais = nbEssaisMax + 1;
//        else nbEssais = nbCoups + 1;
//        return nbEssais;
//    }
//
//    //___________________________________________________________________
//    private static void afficheCode(int[] cod, char[] tabCouleurs) {
//        for (int j : cod) System.out.print(tabCouleurs[j]);
//        System.out.println();
//    }
//}
