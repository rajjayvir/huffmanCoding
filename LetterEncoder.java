public class LetterEncoder {
    private Character alphabet;
    private int alphaCode;

    public LetterEncoder(Character alphabet, int alphaCode) {
        this.alphabet = alphabet;
        this.alphaCode = alphaCode;
    }

    public Character getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Character alphabet) {
        this.alphabet = alphabet;
    }

    public int getAlphaCode() {
        return alphaCode;
    }

    public void setAlphaCode(int alphaCode) {
        this.alphaCode = alphaCode;
    }

    @Override
    public String toString() {
        return "LetterEncoder{" +
                "alphabet=" + alphabet +
                ", alphaCode=" + alphaCode +
                '}';
    }

}
