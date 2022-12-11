import java.util.Arrays;

public class MancheOrdinateur {
    private Plateau p;
    private int score;

    public MancheOrdinateur(Plateau p) {
        this.p = p;
        this.score = 0;
    }

    public static int[] reponseHumain(int lgCode) {
        int[] rep = new int[2];
        do {
            System.out.print("Nombre de codes bien placés : ");
            rep[0] = UtMM.saisirEntier();
            System.out.print("Nombre de codes mal placés : ");
            rep[1] = UtMM.saisirEntier();
        } while (!UtMM.repCorrecte(rep, lgCode));
        return rep;
    }

    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, Code[] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean bool = false;
        while (!bool) {
            bool = passeCodeSuivantLexico(cod1, nbCouleurs);
            if (bool) bool = estCompat(cod1, cod, rep, nbCoups, nbCouleurs);
        }
        return bool;
    }

    public static boolean estCompat(int[] cod1, Code[] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean bool = true;
        int[] nbBienMal = new int[2];
        for (int i = 0; i < nbCoups; i++) {
            nbBienMal = UtMM.nbBienMalPlaces(cod[i].getCode(), cod1, nbCouleurs);
            if (nbBienMal[0] != rep[i][0] || nbBienMal[1] != rep[i][1]) bool = false;
        }
        return bool;
    }

    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
        for (int i = cod1.length - 1; i >= 0; i--) {
            if (cod1[i] != nbCouleurs - 1) {
                cod1[i]++;
                return true;
            }
            Arrays.fill(cod1, i, i + 1, 0);
        }
        return false;
    }

    public void joue() {
        boolean bool = true;
        int[] cod1 = new int[Code.getLgCode()];
        int[] reponse = new int[2];
        int nbEssais = 0;
        while (p.getNbCoups() < Plateau.getNbEssaisMax() && bool) {
            System.out.println("Essai n°" + (p.getNbCoups() + 1));
            System.out.println(UtMM.entiersVersMot(cod1, Couleur.tabCouleurs));
            reponse = reponseHumain(Code.getLgCode());
            if (reponse[0] == Code.getLgCode()) bool = false;
            if (bool) {
                p.addCode(new Code(UtMM.copieTab(cod1)));
                p.getRep()[p.getNbCoups()] = reponse;
                p.addNbCoups();
                bool = passeCodeSuivantLexicoCompat(cod1, p.getCod(), p.getRep(), p.getNbCoups(), Couleur.getNbCouleurs());
            }
        }
        while (p.getNbCoups() < Plateau.getNbEssaisMax() && bool) ;
        if (p.getNbCoups() == Plateau.getNbEssaisMax() && bool)
            nbEssais = Plateau.getNbEssaisMax() + reponse[1] + 2 * (Code.getLgCode() - (reponse[0] + reponse[1]));
        else nbEssais = p.getNbCoups() + 1;
        System.out.println("Le joueur humain a gagné " + nbEssais + " points dans cette manche!");
        score = nbEssais;
    }

    public int getScore() {
        return score;
    }
}
