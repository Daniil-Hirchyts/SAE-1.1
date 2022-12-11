package application;

public class MancheHumain {

    private Code code;
    private int[] codeSecret;
    private int lgCode;
    private int nbCouleurs;

    public MancheHumain(int lgCode, int nbCouleurs) {
        this.lgCode = lgCode;
        this.nbCouleurs = nbCouleurs;
        newCode();
    }

    public int[] getCodeSecret() {
        return codeSecret;
    }


    public void newCode() {
        code = new Code(lgCode, nbCouleurs);
        codeSecret = code.getCode();
    }

    public int[] getScore(String userInput) {
        int[] userInputArray = new int[lgCode];
        for (int i = 0; i < userInput.length(); i++)
            userInputArray[i] = Integer.parseInt(String.valueOf(userInput.charAt(i)));
        return Code.nbBienMalPlaces(codeSecret, userInputArray, nbCouleurs);
    }
}
