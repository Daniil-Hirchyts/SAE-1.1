public class Code {

    private int[] code;
    private int lgCode;

    public Code(int lgCode, int[] code) {
        this.lgCode = lgCode;
        this.code = code;
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
            if (!UtMM.estPresent(tabCouleurs, codMot.charAt(i))) {
                System.out.println("Le code ne doit contenir que des éléments : " + UtMM.listElem(tabCouleurs));
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
            codMot = UtMM.lireString();
        } while (!codeCorrect(codMot, lgCode, tabCouleurs));
        return motVersEntiers(codMot, tabCouleurs);
    }

    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        int nb = 0;
        for (int i = 0; i < cod1.length; i++) if (cod1[i] == cod2[i]) nb++;
        return nb;
    }

    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] t = new int[2];
        t[0] = nbBienPlaces(cod1, cod2);
        t[1] = nbCommuns(cod1, cod2, nbCouleurs) - t[0];
        return t;
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
}
