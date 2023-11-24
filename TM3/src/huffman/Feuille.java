package huffman;

import java.util.Map;

public class Feuille extends NoeudAbstrait {
	private Character caractere;

	public Feuille(Character c, int poids) {
		super(poids);
		if (c == null)
			throw new IllegalArgumentException("null interdit");
		caractere = c;
	}

	@Override
	public void fournitCodes(Map<Character, String> m, String prefixe) {
		m.put(caractere, prefixe);
	}

	@Override
	public Character getNextChar(String s) throws FinDeTexteInattendueException {
		return caractere;
	}

	@Override
	public int hauteur() {
		return 1;
	}
	
	public Character getCaractere() {
		return caractere;
	}

	public String toString() {
		return "Feuille(" + caractere + ", " + getPoids() + ")";
	}
	
}
