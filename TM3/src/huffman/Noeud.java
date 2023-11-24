package huffman;

import java.util.Map;

public class Noeud extends NoeudAbstrait {
	private NoeudAbstrait gauche;
	private NoeudAbstrait droit;

	public Noeud(int poids, NoeudAbstrait gauche, NoeudAbstrait droit) {
		super(poids);
		if (gauche == null || droit == null)
			throw new IllegalArgumentException("null interdit");
		this.gauche = gauche;
		this.droit = droit;
	}

	@Override
	public void fournitCodes(Map<Character, String> m, String prefixe) {
		gauche.fournitCodes(m, prefixe + "0");
		droit.fournitCodes(m, prefixe + "1");
	}

	@Override
	public Character getNextChar(String s) throws FinDeTexteInattendueException {
		if (s.isEmpty())
			throw new FinDeTexteInattendueException();
		char c = s.charAt(0);
		if (c == '0')
			return gauche.getNextChar(s.substring(1));
		if (c == '1')
			return droit.getNextChar(s.substring(1));
		throw new IllegalArgumentException("Le texte � d�coder ne doit contenir que des '0' et des '1");
	}
	
	public NoeudAbstrait getGauche() {
		return gauche;
	}
	
	public NoeudAbstrait getDroit() {
		return droit;
	}

	@Override
	public int hauteur() {
		return 1 + Math.max(gauche.hauteur(), droit.hauteur());
	}

}
