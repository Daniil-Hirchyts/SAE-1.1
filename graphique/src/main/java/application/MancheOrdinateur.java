package application;

public class MancheOrdinateur {

    private Code code;
    private int[] codeSecret;
    private int lgCode;
    private int nbCouleurs;
    private int nbEssais;
    private int[] reponse;
    private int[][] cod;
    private int[][] rep;
    private int[] cod1;
    private boolean bonRep;
    private int nbCoups;
    private int nbEssaisCourant;

    public MancheOrdinateur(int lgCode, int nbCouleurs, int nbEssais) {
        this.lgCode = lgCode;
        this.nbCouleurs = nbCouleurs;
        this.nbEssais = nbEssais;
        this.cod = new int[nbEssais][lgCode];
        this.rep = new int[nbEssais][2];
        this.cod1 = new int[lgCode];
        this.bonRep = false;

        nbEssaisCourant = 0;
        nbCoups = 0;
    }

    public void newCode() {
        if (reponse[0] == lgCode) return;
        cod[nbCoups] = UtMM.copieTab(cod1);
        rep[nbCoups] = reponse;
        nbCoups++;
        passeCodeSuivantLexicoCompat(cod1, cod, rep, nbCoups, nbCouleurs);
    }

    public int[] getCodeSecret() {
        return cod1;
    }

    public void setReponse(int[] reponse) {
        this.reponse = reponse;
        System.out.println(this.reponse[0] + " " + this.reponse[1]);
    }

    public boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean bool = false;
        while (!bool) {
            bool = passeCodeSuivantLexico(cod1, nbCouleurs);
            if (bool) bool = estCompat(cod1, cod, rep, nbCoups, nbCouleurs);
            else return false;
        }
        return bool;
    }

    public boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
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

    public boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {
        boolean bool = true;
        int[] nbBienMal = new int[2];
        for (int i = 0; i < nbCoups; i++) {
            nbBienMal = Code.nbBienMalPlaces(cod[i], cod1, nbCouleurs);
            if (nbBienMal[0] != rep[i][0] || nbBienMal[1] != rep[i][1]) bool = false;
        }
        return bool;
    }
}
