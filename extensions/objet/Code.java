public class Code {

    public static int lgCode;
    private int[] code;

    public Code(int[] code) {
        this.code = code;
    }

    public int[] getCode() {
        return code;
    }

    public void setCode(int[] code) {
        this.code = code;
    }

    public static void setLgCode(int lgCode) {
        Code.lgCode = lgCode;
    }

    public static int getLgCode() {
        return Code.lgCode;
    }

    public static int[] codeAleat(int lgCode, int nbCouleurs) {
        int[] t = new int[lgCode];
        for (int i = 0; i < lgCode; i++) t[i] = (int) (Math.random() * nbCouleurs);
        return t;
    }

    public int[] getTab() {
        return code;
    }
}
