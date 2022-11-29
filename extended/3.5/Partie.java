import java.util.Arrays;

public class Partie {

    private int nbManches;
    private int numManche;
    private int scoreJoueur;
    private int scoreOrdi;


    public void demarrerPartie() {
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════ \n");
        System.out.println(
                "███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗     ███╗   ███╗██╗███╗   ██╗██████╗ \n" +
                        "████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗    ████╗ ████║██║████╗  ██║██╔══██╗\n" +
                        "██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝    ██╔████╔██║██║██╔██╗ ██║██║  ██║\n" +
                        "██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗    ██║╚██╔╝██║██║██║╚██╗██║██║  ██║\n" +
                        "██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║    ██║ ╚═╝ ██║██║██║ ╚████║██████╔╝\n" +
                        "╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝ \n\n");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("                                        Réalisé par : Daniil HIRCHYTS & Youssera OULMEKKI");
        System.out.println("                                 IUT Montpellier-Sète, Département Informatique 2022-2023");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        //Saisie des paramètres de la partie
        System.out.println("Bienvenue dans le jeu du Mastermind !\n");
        System.out.println("Le but du jeu est de trouver le code secret de l'ordinateur en un nombre limité d'essais.");
        System.out.println("Le code secret est composé de couleurs parmi : Rouge, Bleu, Jaune, Vert, Orange et Noir.\n");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("Pour commencer, veuillez saisir les paramètres de la partie");
        System.out.println("⚙️Longueur du code secret");
        int lgCode = UtMM.saisirEntierPositif();
        System.out.println("⚙️Couleurs");
        char[] tabCouleurs = Couleur.saisirCouleurs();
        System.out.println("⚙️Nombre de manches");
        int nbManches = UtMM.saisirEntierPairPositif();
        System.out.println("⚙️Nombre d'essais maximum par manche");
        int nbEssaisMax = UtMM.saisirEntierPositif();
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        //Les paramètres de la partie finalement choisis
        System.out.println("Les paramètres de la partie sont :");
        System.out.println("Longueur du code secret : " + lgCode);
        System.out.println("Couleurs : " + Arrays.toString(tabCouleurs));
        System.out.println("Nombre de manches : " + nbManches);
        System.out.println("Nombre d'essais maximum par manche : " + nbEssaisMax);
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        //Début de la partie
        System.out.println("La partie commence 🚩!");
        int[] score = new int[2];
        for (int i = 0; i < nbManches; i++) {
            System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
            System.out.println("Manche " + (i + 1) + " :");
            if (i % 2 == 0) score[0] += MancheOrdinateur.mancheOrdinateur(lgCode, tabCouleurs, i + 1, nbEssaisMax);
            else score[1] += MancheHumain.mancheHumain(lgCode, tabCouleurs, i + 1, nbEssaisMax);
        }
        if (score[0] < score[1]) System.out.println("L'ordinateur a gagné la partie avec un score de " + score[0] + " points ⭐️!");
        else if (score[0] > score[1]) System.out.println("Le joueur humain a gagné la partie avec un score de " + score[1] + " points ⭐️!");
        else if ((score[0] == score[1])) System.out.println("La partie est nulle 🚫");
    }

}
