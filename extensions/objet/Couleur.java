public class Couleur {

    public static char[] saisirCouleurs() {
        int n;
        do {
            System.out.print("Saisir le nombre de couleurs strictement positif entre 4 et 6 : ");
            n = UtMM.lireInt();
        } while (n <= 0);
        char[] tabCouleurs = new char[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Saisir le nom de la couleur n°" + (i + 1) + " : ");
            tabCouleurs[i] = UtMM.lireString().charAt(0);
            for (int j = 0; j < i; j++) {
                if (tabCouleurs[i] == tabCouleurs[j]) {
                    System.out.println("Erreur : l'initiale du nom de la couleur n°" + (i + 1) + " est déjà utilisée");
                    i--;
                    break;
                }
            }
        }
        return tabCouleurs;
    }

}
