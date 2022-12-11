import java.util.Arrays;

public class MainMasterMind {
    public static void main(String[] args) {
        System.out.print("═════════════════════════════════════════════════════════════════════════════════════════ \n");
        System.out.print(
                """
                        ███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗     ███╗   ███╗██╗███╗   ██╗██████╗
                        ████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗    ████╗ ████║██║████╗  ██║██╔══██╗
                        ██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝    ██╔████╔██║██║██╔██╗ ██║██║  ██║
                        ██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗    ██║╚██╔╝██║██║██║╚██╗██║██║  ██║
                        ██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║    ██║ ╚═╝ ██║██║██║ ╚████║██████╔╝
                        ╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝\s
                        """);
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("                                        Réalisé par : Daniil HIRCHYTS & Youssera OULMEKKI");
        System.out.println("                                 IUT Montpellier-Sète, Département Informatique 2022-2023");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        System.out.println("Pour commencer, veuillez saisir les paramètres de la partie");
        System.out.print("Longueur du code secret : ");
        int lgCode = UtMM.saisirEntierPositif();
        System.out.print("\n" + "Couleurs : ");
        char[] tabCouleurs = UtMM.saisirCouleurs();
        System.out.print("\n" + "Nombre de manches : ");
        int nbManches = UtMM.saisirEntierPairPositif();
        System.out.print("\n" + "Nombre d'essais maximum par manche : ");
        int nbEssaisMax = UtMM.saisirEntierPositif();
        System.out.println("\n" + "═════════════════════════════════════════════════════════════════════════════════════════");

        System.out.println("Les paramètres de la partie sont :");
        System.out.println("Longueur du code secret : " + lgCode);
        System.out.println("Couleurs : " + Arrays.toString(tabCouleurs));
        System.out.println("Nombre de manches : " + nbManches);
        System.out.println("Nombre d'essais maximum par manche : " + nbEssaisMax);
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");

        System.out.println("La partie commence!");
        Couleur.setTabCouleurs(tabCouleurs);
        Code.setLgCode(lgCode);
        Plateau.setNbEssaisMax(nbEssaisMax);

        Partie p = new Partie(nbManches);
        p.joue();
    }
}
