package huffman;

import java.util.Collection;
import java.util.LinkedList;

public class ListeTriee extends LinkedList<NoeudAbstrait> {
	private static final long serialVersionUID = 1L;
	
	public ListeTriee() {
		super();
	}
	
	public ListeTriee(Collection<? extends NoeudAbstrait> c) {
		super();
		addAll(c);
	}
	
	public boolean add(NoeudAbstrait n) {
		if (n == null)
			throw new IllegalArgumentException("null interdit");
		int i = 0;
		for (; i < size() && n.compareTo(get(i)) >= 0; i ++)
			;
		super.add(i, n);
		return true;
	}
	
	public void add(int i, NoeudAbstrait n) {
		throw new UnsupportedOperationException();
	}
	
	public boolean addAll(Collection<? extends NoeudAbstrait> c) {
		for (NoeudAbstrait n : c)
			add(n);
		return c.size() > 0;
	}
	

	public static void main(String[] args) {
		Feuille f0 = new Feuille('a', 1);
		Feuille f1 = new Feuille('b', 1);
		Feuille f2 = new Feuille('c', 8);
		Feuille f3 = new Feuille('d', 7);
		Feuille f4 = new Feuille('e', 1);
		Feuille f5 = new Feuille('f', 3);
		Feuille f6 = new Feuille('g', 6);
		Feuille f7 = new Feuille('h', 5);
		LinkedList<Feuille> lk = new LinkedList<Feuille>();
		lk.add(f4);
		lk.add(f5);
		lk.add(f6);
		lk.add(f7);
		lk.add(f6);
		lk.add(f7);
		ListeTriee l = new ListeTriee();
		l.add(f0);
		l.add(f1);
		l.add(f2);
		l.add(f3);
		l.add(f4);
		System.out.println(l);
		ListeTriee l2 = new ListeTriee(lk);
		System.out.println(l2);
		l.addAll(l2);
		System.out.println(l);
	}

}
