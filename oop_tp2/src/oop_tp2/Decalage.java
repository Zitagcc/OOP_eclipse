package oop_tp2;

public class Decalage extends Cryptage {

    private int decalage;

    public Decalage(String clef) {
        this.decalage = Integer.parseInt(clef);
        this.alphabetDeCryptage = ALPHABET;
    }

    @Override
    public String cryptage(String s) {

    	StringBuilder result = new StringBuilder();
		
		char[] c = s.toCharArray();
		int idx =0, decalage=1;
		for(int i=0;i<s.length();i++) {
			if (c[i]!=' ' ) {
				
				idx = (int)(s.charAt(i)) + decalage;
				result.append((char)idx);
			}
			else {
				result.append(' ');
			}
			
		}

        return result.toString();
    }

    @Override
    public String deCryptage(String s) {
StringBuilder result = new StringBuilder();
		
		char[] c = s.toCharArray();
		int idx =0, decalage=1;
		for(int i=0;i<s.length();i++) {
			if (c[i]!=' ' ) {
				
				idx = (int)(s.charAt(i)) - decalage;
				result.append((char)idx);
			}
			else {
				result.append(' ');
			}
			
		}


        return result.toString();
    }

	
	
	  public static void main(String[] args) { Cryptage cryptageCaesar = new
	  Decalage("10");
	  
	  String texte = "hello world"; System.out.println("Texte original: " + texte);
	  
	  String texteCrypte = cryptageCaesar.cryptage(texte);
	  System.out.println("Texte crypté: " + texteCrypte);
	  
	  String texteDecrypte = cryptageCaesar.deCryptage(texteCrypte);
	  System.out.println("Texte décrypté: " + texteDecrypte); }
	 
	 
}
