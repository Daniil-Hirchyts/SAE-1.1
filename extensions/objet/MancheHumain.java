import java.util.Arrays;

public class MancheHumain {
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int nbCoups = 0;
        int[] cod1 = Code.codeAleat(lgCode, tabCouleurs.length);
        int[] cod2 = Code.propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
        while (nbCoups < (nbEssaisMax - 1) && !Arrays.equals(cod1, cod2)) {
            int[] t = Code.nbBienMalPlaces(cod1, cod2, tabCouleurs.length);
            System.out.println("Bien placés : " + t[0] + " Mal placés : " + t[1]);
            nbCoups++;
            cod2 = Code.propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
        }
        if (nbCoups == nbEssaisMax - 1) System.out.println("Perdu ! Le code était : " + UtMM.entiersVersMot(cod1, tabCouleurs));
        else System.out.println("Gagné en " + (nbCoups + 1) + " coups !");
        return nbCoups + 1;
    }
}
