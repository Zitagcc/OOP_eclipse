package oop_tp2;

public abstract class Cryptage {
    final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    protected String alphabetDeCryptage;
    protected String clef;

    public Cryptage() {
    }

    public Cryptage(String clef) {
        this.clef = clef;
    }

    public String toString() {
        return "Cryptage " + this.getClass().getSimpleName() + " Mot Clef : " + this.clef;
    }

    public abstract String cryptage(String s);

    public abstract String deCryptage(String s);
}

