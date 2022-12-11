import java.util.Arrays;

public class MainMasterMind {

    private Partie partie;

    public MainMasterMind() {
        partie = new Partie();
    }

    public static void main(String[] args) {
        MainMasterMind m = new MainMasterMind();
        m.partie.demarrerPartie();
    }
} // fin de la classe Mastermind
