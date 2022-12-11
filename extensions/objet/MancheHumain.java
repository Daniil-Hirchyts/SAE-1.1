public class MancheHumain {
    private Plateau p;
    private int score;

    public MancheHumain(Plateau p) {
        this.p = p;
        this.score = 0;
    }

    public void joue() {
        int nbEssais = 0;
        Code codeAChercher = new Code(Code.codeAleat(Code.getLgCode(), Couleur.getNbCouleurs()));
        for (int i = 0; i < Plateau.getNbEssaisMax(); i++) {
            int[] codePropose = UtMM.propositionCodeHumain(p.getNbCoups(), Code.getLgCode(), Couleur.getTabCouleurs());
            int[] reponse = UtMM.nbBienMalPlaces(codeAChercher.getCode(), codePropose, Code.getLgCode());
            p.addCode(new Code(codePropose));
            p.addReponse(reponse);
            System.out.println("Bien placés : " + reponse[0] + " Mal placés : " + reponse[1]);
            if (reponse[0] == Code.getLgCode()) {
                System.out.println("Bravo, vous avez gagné en " + (p.getNbCoups() + 1) + " coups !");
                int[] t = UtMM.nbBienMalPlaces(codeAChercher.getCode(), codePropose, Couleur.getTabCouleurs().length);
                nbEssais = Plateau.getNbEssaisMax() + t[1] + 2 * (Code.getLgCode() - (t[0] + t[1]));
                score = nbEssais;
                return;
            }
        }
    }

    public int getScore() {
        return score;
    }
}