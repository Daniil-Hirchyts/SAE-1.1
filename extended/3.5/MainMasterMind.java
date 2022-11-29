//import java.util.Arrays;
//
//public class MainMasterMind {
//
//    //___________________________________________________________________
//
//    /**
//     * @return l'entier strictement positif saisi
//     * @Action: demande au joueur humain de saisir un entier strictement positif, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
//     */
//    public static int saisirEntierPositif() {
//        int n;
//        do {
//            System.out.print("Saisir un entier strictement positif : ");
//            n = lireInt();
//        } while (n <= 0);
//        return n;
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * @return l'entier pair strictement positif saisi
//     * @Action: demande au joueur humain de saisir un entier pair strictement positif, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
//     */
//    public static int saisirEntierPairPositif() {
//        int n;
//        do {
//            System.out.print("Saisir un entier pair strictement positif : ");
//            n = lireInt();
//        } while (n <= 0 || n % 2 != 0);
//        return n;
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * @return le tableau des initiales des noms de couleurs saisis
//     * @Action: demande au joueur humain de saisir le nombre de couleurs (strictement positif),
//     * puis les noms de couleurs aux initiales différentes,
//     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
//     */
//    public static char[] saisirCouleurs() {
//        int n;
//        do {
//            System.out.print("Saisir le nombre de couleurs strictement positif entre 4 et 6 : ");
//            n = lireInt();
//        } while (n <= 0);
//        char[] tabCouleurs = new char[n];
//        for (int i = 0; i < n; i++) {
//            System.out.print("Saisir le nom de la couleur n°" + (i + 1) + " : ");
//            tabCouleurs[i] = lireString().charAt(0);
//            for (int j = 0; j < i; j++) {
//                if (tabCouleurs[i] == tabCouleurs[j]) {
//                    System.out.println("Erreur : l'initiale du nom de la couleur n°" + (i + 1) + " est déjà utilisée");
//                    i--;
//                    break;
//                }
//            }
//        }
//        return tabCouleurs;
//    }
//
//    //___________________________________________________________________
//
//    /**
//     * @Action: demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs, nbManches, nbEssaisMax),
//     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
//     * @Pré-requis: la longueur d'un code et le nombre de couleurs doivent être strictement positifs.
//     * Le nombre de manches doit être un nombre pair strictement positif.
//     * Les initiales des noms de couleurs doivent être différentes.
//     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
//     */
//    public static void main(String[] args) {
//
//        //Titre du programme
//        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════ \n");
//        System.out.println(
//                "███╗   ███╗ █████╗ ███████╗████████╗███████╗██████╗     ███╗   ███╗██╗███╗   ██╗██████╗ \n" +
//                        "████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗    ████╗ ████║██║████╗  ██║██╔══██╗\n" +
//                        "██╔████╔██║███████║███████╗   ██║   █████╗  ██████╔╝    ██╔████╔██║██║██╔██╗ ██║██║  ██║\n" +
//                        "██║╚██╔╝██║██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗    ██║╚██╔╝██║██║██║╚██╗██║██║  ██║\n" +
//                        "██║ ╚═╝ ██║██║  ██║███████║   ██║   ███████╗██║  ██║    ██║ ╚═╝ ██║██║██║ ╚████║██████╔╝\n" +
//                        "╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝ \n\n");
//        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
//        System.out.println("                                        Réalisé par : Daniil HIRCHYTS & Youssera OULMEKKI");
//        System.out.println("                                 IUT Montpellier-Sète, Département Informatique 2022-2023");
//        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
//
//        //Saisie des paramètres de la partie
//        System.out.println("Bienvenue dans le jeu du Mastermind !\n");
//        System.out.println("Le but du jeu est de trouver le code secret de l'ordinateur en un nombre limité d'essais.");
//        System.out.println("Le code secret est composé de couleurs parmi : Rouge, Bleu, Jaune, Vert, Orange et Noir.\n");
//        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
//        System.out.println("Pour commencer, veuillez saisir les paramètres de la partie");
//        System.out.println("⚙️Longueur du code secret");
//        int lgCode = saisirEntierPositif();
//        System.out.println("⚙️Couleurs");
//        char[] tabCouleurs = saisirCouleurs();
//        System.out.println("⚙️Nombre de manches");
//        int nbManches = saisirEntierPairPositif();
//        System.out.println("⚙️Nombre d'essais maximum par manche");
//        int nbEssaisMax = saisirEntierPositif();
//        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
//
//        //Les paramètres de la partie finalement choisis
//        System.out.println("Les paramètres de la partie sont :");
//        System.out.println("Longueur du code secret : " + lgCode);
//        System.out.println("Couleurs : " + Arrays.toString(tabCouleurs));
//        System.out.println("Nombre de manches : " + nbManches);
//        System.out.println("Nombre d'essais maximum par manche : " + nbEssaisMax);
//        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
//
//        //Début de la partie
//        System.out.println("La partie commence 🚩!");
//        int[] score = new int[2];
//        for (int i = 0; i < nbManches; i++) {
//            System.out.println("═════════════════════════════════════════════════════════════════════════════════════════");
//            System.out.println("Manche " + (i + 1) + " :");
//            if (i % 2 == 0) {
//                score[0] += mancheOrdinateur(lgCode, tabCouleurs, i + 1, nbEssaisMax);
//            } else {
//                score[1] += mancheHumain(lgCode, tabCouleurs, i + 1, nbEssaisMax);
//            }
//        }
//        if (score[0] < score[1]) {
//            System.out.println("L'ordinateur a gagné la partie avec un score de " + score[0] + " points ⭐️!");
//        } else if (score[0] > score[1]) {
//            System.out.println("Le joueur humain a gagné la partie avec un score de " + score[1] + " points ⭐️!");
//        } else {
//            System.out.println("La partie est nulle 🚫");
//        }
//    }
//} // fin de la classe Mastermind
