public class Partie {

    private int nbManches;
    private int numManche;
    private int[] score = new int[2];

    public Partie(int nbManches) {
        this.nbManches = nbManches;
        this.numManche = 0;
    }

    public void joue() {
        while (numManche < nbManches) {
            if (numManche % 2 == 1) {
                MancheHumain mancheHumain = new MancheHumain(new Plateau());
                mancheHumain.joue();
                score[0] += mancheHumain.getScore();
            } else {
                MancheOrdinateur mancheOrdinateur = new MancheOrdinateur(new Plateau());
                mancheOrdinateur.joue();
                score[1] += mancheOrdinateur.getScore();
            }
            numManche++;
        }
        if (score[0] < score[1])
            System.out.println("L'ordinateur a gagné la partie avec un score de " + score[1] + " points!");
        else if (score[0] > score[1])
            System.out.println("Le joueur humain a gagné la partie avec un score de " + score[0] + " points!");
        else System.out.println("La partie est nulle!");
    }
}
