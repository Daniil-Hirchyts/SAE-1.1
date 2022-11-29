//import java.util.Arrays;
//
//public class MancheHumain {
//
//
//    /**
//     * @param cod1       code à deviner
//     * @param cod2       code proposé
//     * @param nbCouleurs nombre de couleurs
//     * @return un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
//     * se trouvant  (resp. ne se trouvant pas) au même indice
//     * @Pré-requis: cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
//     */
//    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
//        int[] t = new int[2];
//        t[0] = nbBienPlaces(cod1, cod2);
//        t[1] = nbCommuns(cod1, cod2, nbCouleurs) - t[0];
//        return t;
//    }
//
//    //____________________________________________________________
//
//    /**
//     * @param numManche   numéro de la manche
//     * @param nbEssaisMax nombre maximum d'essais
//     * @param lgCode      longueur du code
//     * @param tabCouleurs tableau de caractères contenant les couleurs
//     * @return le nombre de codes proposés par le joueur humain
//     * @Pré-requis: numManche >= 1, nbEssaisMax > 0, lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0
//     */
//    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
//        int nbCoups = 0;
//        int[] cod1 = codeAleat(lgCode, tabCouleurs.length);
//        int[] cod2 = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
//        while (nbCoups < nbEssaisMax && !Arrays.equals(cod1, cod2)) {
//            int[] t = nbBienMalPlaces(cod1, cod2, tabCouleurs.length);
//            System.out.println("Bien placés : " + t[0] + " Mal placés : " + t[1]);
//            nbCoups++;
//            cod2 = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
//        }
//        if (nbCoups == nbEssaisMax) System.out.println("Perdu ! Le code était : " + entiersVersMot(cod1, tabCouleurs));
//        else System.out.println("Gagné en " + (nbCoups + 1) + " coups !");
//        return nbCoups + 1;
//    }
//}
