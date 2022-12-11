public class Plateau {
  private static int nbEssaisMax;
  private Code[] cod;
  private final int[][] rep;
  private int nbCoups;

  public Plateau() {
    this.cod = new Code[nbEssaisMax];
    this.rep = new int[nbEssaisMax][2];
    this.nbCoups = 0;
  }

  public static int getNbEssaisMax() {
    return nbEssaisMax;
  }

  public Code[] getCod() {
    return this.cod;
  }

  public int[][] getRep() {
    return this.rep;
  }

  public int getNbCoups() {
    return this.nbCoups;
  }

  public void addNbCoups() {
    this.nbCoups ++;
  }



  public void addCode(Code code) {
    cod[nbCoups] = code;
  }

  public static void setNbEssaisMax(int nbEssaisMax) {
    Plateau.nbEssaisMax = nbEssaisMax;
  }

  public void addReponse(int[] reponse) {
    rep[nbCoups] = reponse;
    nbCoups++;
  }
}
