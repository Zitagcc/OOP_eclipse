package oop_tp2;

public class Decalage extends Cryptage {

    private int decalage;

    public Decalage(String clef) {
        //super(clef);
        this.decalage = Integer.parseInt(clef);
        this.alphabetDeCryptage = ALPHABET;
    }

    @Override
    public String cryptage(String s) {
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            int index = (ALPHABET.indexOf(c) + decalage) % ALPHABET.length();
            result.append(ALPHABET.charAt(index));
        }

        return result.toString();
    }

    @Override
    public String deCryptage(String s) {
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            int index = (ALPHABET.indexOf(c) - decalage + ALPHABET.length()) % ALPHABET.length();
            result.append(ALPHABET.charAt(index));
        }

        return result.toString();
    }

	
	
	  public static void main(String[] args) { Cryptage cryptageCaesar = new
	  Decalage("10");
	  
	  String texte = "hello"; System.out.println("Texte original: " + texte);
	  
	  String texteCrypte = cryptageCaesar.cryptage(texte);
	  System.out.println("Texte crypté: " + texteCrypte);
	  
	  String texteDecrypte = cryptageCaesar.deCryptage(texteCrypte);
	  System.out.println("Texte décrypté: " + texteDecrypte); }
	 
	 
}
