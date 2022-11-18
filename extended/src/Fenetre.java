import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    private static boolean thereIsInstance = false;
    private static Fenetre THEFENETRE = null;
    private static Panneau pan = null;

    private Fenetre(int w, int h, String titre) {
        this.setTitle(titre);
        this.setSize(w, h);
        this.setLocationRelativeTo(null);
        Fenetre.pan = new Panneau();
        this.setContentPane(Fenetre.pan);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        thereIsInstance = true;
        this.getContentPane().setBackground(new Color(162, 201, 231));
    }

    public static void createFenetre(int w, int h, String titre) {
        if (!thereIsInstance)
            THEFENETRE = new Fenetre(w, h, titre);
    }

    public static Fenetre getInstance() { // permet d'acceder a l'unique instance de la fenetre
        return Fenetre.THEFENETRE;
    }

    public void actualiserAffichage() { // actualise l'affichage
        pan.repaint();
    }
}
