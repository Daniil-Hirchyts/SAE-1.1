//public class Code {
//    //____________________________________________________________
//
//    /**
//     * @param lgCode     : longueur du code
//     * @param nbCouleurs : nombre de couleurs
//     * @return un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
//     * @Pré-requis: lgCode > 0, nbCouleurs > 0
//     */
//    public static int[] codeAleat(int lgCode, int nbCouleurs) {
//        int[] t = new int[lgCode];
//        for (int i = 0; i < lgCode; i++) t[i] = (int) (Math.random() * nbCouleurs);
//        return t;
//    }
//
//    //____________________________________________________________
//
//    /**
//     * @param codMot      code à deviner
//     * @param lgCode      longueur du code
//     * @param tabCouleurs tableau de caractères contenant les couleurs
//     * @return vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
//     * @Pré-requis: lgCode > 0, tabCouleurs.length > 0
//     */
//    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {
//        if (codMot.length() != lgCode) {
//            System.out.println("La longueur du code doit être : " + lgCode);
//            return false;
//        }
//        for (int i = 0; i < codMot.length(); i++) {
//            if (!estPresent(tabCouleurs, codMot.charAt(i))) {
//                System.out.println("Le code ne doit contenir que des éléments : " + listElem(tabCouleurs));
//                return false;
//            }
//        }
//        return true;
//    }
//
//    //____________________________________________________________
//
//    /**
//     * @param codMot      code à deviner
//     * @param tabCouleurs tableau de caractères contenant les couleurs
//     * @return le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
//     * @Pré-requis: les caractères de codMot sont des éléments de tabCouleurs
//     */
//    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
//        int[] t = new int[codMot.length()];
//        for (int i = 0; i < codMot.length(); i++)
//            for (int j = 0; j < tabCouleurs.length; j++) if (codMot.charAt(i) == tabCouleurs[j]) t[i] = j;
//        return t;
//    }
//
//    //____________________________________________________________
//
//    /**
//     * @param lgCode      longueur du code
//     * @param tabCouleurs tableau de caractères contenant les couleurs
//     * @param nbCoups     nombre de coups joués
//     * @return le code saisi sous forme de tableau d'entiers
//     * @Pré-requis: lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0
//     */
//    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
//        String codMot = "";// code saisi par l'utilisateur
//        do {
//            System.out.println("Proposition N°" + (nbCoups + 1) + " : ");
//            codMot = lireString();
//        } while (!codeCorrect(codMot, lgCode, tabCouleurs));
//        return motVersEntiers(codMot, tabCouleurs);
//    }
//
//    //____________________________________________________________
//
//    /**
//     * @param cod1 code à deviner
//     * @param cod2 code proposé
//     * @return le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
//     * @Pré-requis: cod1.length = cod2.length
//     */
//    public static int nbBienPlaces(int[] cod1, int[] cod2) {
//        int nb = 0;
//        for (int i = 0; i < cod1.length; i++) if (cod1[i] == cod2[i]) nb++;
//        return nb;
//    }
//
//    //____________________________________________________________
//
//    /**
//     * @param cod        code à deviner
//     * @param nbCouleurs nombre de couleurs
//     * @return un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
//     * @Pré-requis: les éléments de cod sont des entiers de 0 à nbCouleurs-1
//     */
//    public static int[] tabFrequence(int[] cod, int nbCouleurs) {
//        int[] t = new int[nbCouleurs];
//        for (int j : cod) t[j]++;
//        return t;
//    }
//
//    //____________________________________________________________
//
//    /**
//     * @param cod1       code à deviner
//     * @param cod2       code proposé
//     * @param nbCouleurs nombre de couleurs
//     * @return le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
//     * @Pré-requis: les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
//     */
//    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
//        int[] t1 = tabFrequence(cod1, nbCouleurs);
//        int[] t2 = tabFrequence(cod2, nbCouleurs);
//        int nb = 0;
//        for (int i = 0; i < nbCouleurs; i++) nb += Math.min(t1[i], t2[i]);
//        return nb;
//    }
//}
