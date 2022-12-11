public class Plateau {

    private static int nbEssaisMax;
    private Code[] cod;
    private int[][] rep;
    private int nbCoups;

    public Plateau(int nbEssaisMax, int nbCases, int nbCouleurs) {
        Plateau.nbEssaisMax = nbEssaisMax;
        cod = new Code[nbEssaisMax];
        rep = new int[nbEssaisMax][nbCases];
        nbCoups = 0;
    }
}
