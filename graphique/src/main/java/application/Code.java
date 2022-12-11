package application;

public class Code {

    private int[] code;

    public Code(int lgCode, int nbCouleurs) {
        code = new int[lgCode];
        for (int i = 0; i < lgCode; i++) {
            code[i] = (int) (Math.random() * nbCouleurs);
        }
    }

    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        int nb = 0;
        for (int i = 0; i < cod1.length; i++) if (cod1[i] == cod2[i]) nb++;
        return nb;
    }

    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] t1 = tabFrequence(cod1, nbCouleurs);
        int[] t2 = tabFrequence(cod2, nbCouleurs);
        int nb = 0;
        for (int i = 0; i < nbCouleurs; i++) nb += Math.min(t1[i], t2[i]);
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

    public int[] getCode() {
        return this.code;
    }
}
