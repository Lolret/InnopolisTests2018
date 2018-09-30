package reflection.inClass.reflection;

public class Capucin extends Monkey {

    public  int charming;
    protected int harmfull;
    private int footsize;
    private final int talLength = 40;

    Capucin(int charming, int harmfull, int footsize) {
        this.charming = charming;
        this.harmfull = harmfull;
        this.footsize = footsize;
            }

    public Capucin(int charming) {
        this(charming, 38, 1);
    }
    protected  void washFace() {
        System.out.println("face is cleaned");
    }

    private void washEars() {
        System.out.println("Ears washed!");
    }

    public void eatBananas(int count) {
        System.out.println("Ate " + count + " bananas");
    }
}