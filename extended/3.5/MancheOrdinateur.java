public class MancheOrdinateur {

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
            rep[0] = UtMM.lireInt(); // nombre de codes bien placés
            System.out.print("Nombre de codes mal placés : ");
            rep[1] = UtMM.lireInt(); // nombre de codes mal placés
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
            nbBienMal = Code.nbBienMalPlaces(cod[i], cod1, nbCouleurs);
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
            System.out.println(UtMM.entiersVersMot(cod1, tabCouleurs));
            reponse = reponseHumain(lgCode);
            if (reponse[0] == lgCode) bool = false;
            if (bool) {
                cod[nbCoups] = UtMM.copieTab(cod1);
                rep[nbCoups] = reponse;
                nbCoups++;
                bool = passeCodeSuivantLexicoCompat(cod1, cod, rep, nbCoups, nbCouleurs);
            }
        }
        if (nbCoups == nbEssaisMax && bool) nbEssais = nbEssaisMax + 1;
        else nbEssais = nbCoups + 1;
        return nbEssais;
    }


}
