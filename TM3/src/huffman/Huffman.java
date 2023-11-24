package huffman;

import java.util.Map;
import java.util.TreeMap;

public class Huffman {
	private Map<Character, String> dictionnaire;
	private NoeudAbstrait arbre;

	public Huffman(String texte) {
		if (texte == null)
			throw new IllegalArgumentException("null interdit");
		Map<Character, Integer> comptes = compteCaracteres(texte);
		if (comptes.size() < 2)
			throw new IllegalArgumentException("le texte de r�f�rence doit au moins contenir 2 caract�res diff�rents.");
		initArbre(comptes);
		initDictionnaire();
	}

	private Map<Character, Integer> compteCaracteres(String texte) {
		Map<Character, Integer> comptes = new TreeMap<Character, Integer>();
		for (int i = 0; i < texte.length(); i++) {
			char c = texte.charAt(i);
			if (comptes.containsKey(c))
				comptes.put(c, comptes.get(c) + 1);
			else
				comptes.put(c, 1);
		}
		return comptes;
	}

	private void initArbre(Map<Character, Integer> comptes) {
		ListeTriee l = new ListeTriee();
		for (Character c : comptes.keySet())
			l.add(new Feuille(c, comptes.get(c)));
		while (l.size() > 1) {
			NoeudAbstrait n1 = l.removeFirst();
			NoeudAbstrait n2 = l.removeFirst();
			l.add(new Noeud(n1.getPoids() + n2.getPoids(), n1, n2));
		}
		arbre = l.removeFirst();
	}

	private void initDictionnaire() {
		dictionnaire = new TreeMap<Character, String>();
		arbre.fournitCodes(dictionnaire, "");
	}
	
	public NoeudAbstrait getArbre() {
		return arbre;
	}
	
	public Map<Character, String> getDictionnaire() {
		return dictionnaire;
	}
	
	public String code(String texte) throws CaractereInconnuException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < texte.length(); i ++) {
			char c = texte.charAt(i);
			if (! dictionnaire.containsKey(c))
				throw new CaractereInconnuException("" + c);
			sb.append(dictionnaire.get(c));
		}
		return sb.toString();
	}

	public String decode(String texte) throws FinDeTexteInattendueException {
		StringBuffer sb = new StringBuffer();
		while (texte.length() > 0) {
			Character c = arbre.getNextChar(texte);
			sb.append(c);
			texte = texte.substring(dictionnaire.get(c).length());
		}
		return sb.toString();
	}
	
	public String toString() {
		return "Codage Huffman de dictionnaire : " + dictionnaire;
	}

	public static void main(String[] args) {
		Huffman h = new Huffman("abracadabra-baccara");
		System.out.println(h);
		try {
			System.out.println(h.code("abracadabra-baccara"));
			System.out.println(h.decode("0101111011001001010111101000101011011001110"));
		} catch (CaractereInconnuException e) {
			e.printStackTrace();
		} catch (FinDeTexteInattendueException e) {
			e.printStackTrace();
		}
		
	}

}
