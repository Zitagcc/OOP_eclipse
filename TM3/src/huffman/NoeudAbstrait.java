package huffman;

import java.util.Map;

public abstract class NoeudAbstrait implements Comparable<NoeudAbstrait> {
	private int poids;

	public NoeudAbstrait(int poids) {
		this.poids = poids;
	}
	
	public NoeudAbstrait() {
		this(1);
	}
	
	public int getPoids() {
		return poids;
	}
	
	public int compareTo(NoeudAbstrait n) {
		return poids - n.poids;
	}
	
	public abstract void fournitCodes(Map<Character, String> m, String prefixe);
	
	public abstract Character getNextChar(String s) throws FinDeTexteInattendueException;
	
	public abstract int hauteur();

}
