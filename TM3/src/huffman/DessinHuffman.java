package huffman;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class DessinHuffman extends JPanel {
	private Huffman codage;

	public DessinHuffman() {
	}

	public DessinHuffman(LayoutManager layout) {
		super(layout);
	}

	public DessinHuffman(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public DessinHuffman(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public void setCodage(Huffman codage) {
		if (codage != null) {
			this.codage = codage;
			NoeudAbstrait arbre = this.codage.getArbre();
			this.setSize(10 + largeurAffichage(this.getGraphics(), arbre), 10
					+ (arbre.hauteur() * 2 + 1)
					* this.getGraphics().getFontMetrics().getHeight());
		}
			this.getParent().invalidate();
			this.getParent().repaint();
	}

	private int dessineArbre(Graphics g, NoeudAbstrait racine, int x, int y) {
		int largeur = largeurAffichage(g, racine);
		if (racine instanceof Feuille) {
			Feuille f = (Feuille) racine;
			FontMetrics fm = g.getFontMetrics();
			g.drawRect(x, y, largeur, fm.getHeight());
			int lc = fm.stringWidth(String.valueOf(f.getCaractere()));
			g.drawLine(x + lc + 6, y, x + lc + 6, y + fm.getHeight());
			g.drawString(String.valueOf(f.getCaractere()), x + 3,
					y + fm.getAscent());
			g.drawString(String.valueOf(f.getPoids()), x + lc + 8,
					y + fm.getAscent());
			return x + largeur / 2;
		}
		Noeud n = (Noeud) racine;
		int lg = largeurAffichage(g, n.getGauche());
		FontMetrics fm = g.getFontMetrics();
		int lp = fm.stringWidth(String.valueOf(n.getPoids()));
		int yFils = y + 2 * (fm.getHeight());
		int xg = dessineArbre(g, n.getGauche(), x, yFils);
		int xd = dessineArbre(g, n.getDroit(), x + lg + 4, yFils);
		int xTexte = xg + (xd - xg + 2) / 2 - lp / 2;
		g.drawRoundRect(xTexte - 3, y, lp + 6, fm.getHeight(), 10, 10);
		g.drawString(String.valueOf(n.getPoids()), xTexte, y + fm.getAscent());
		g.drawLine(xTexte + lp / 2, y + fm.getHeight(), xg, yFils);
		g.drawLine(xTexte + lp / 2, y + fm.getHeight(), xd, yFils);
		return xTexte + lp / 2;
	}

	private int largeurAffichage(Graphics g, NoeudAbstrait racine) {
		FontMetrics fm = g.getFontMetrics();
		if (racine instanceof Feuille)
			return fm.stringWidth(String.valueOf(((Feuille) racine)
					.getCaractere()))
					+ fm.stringWidth(String.valueOf(racine.getPoids())) + 11;
		Noeud n = (Noeud) racine;
		return largeurAffichage(g, n.getGauche()) + 4
				+ largeurAffichage(g, n.getDroit());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (codage != null) {
			NoeudAbstrait arbre = codage.getArbre();
			FontMetrics fm = g.getFontMetrics();
			int hauteur = 10 + (arbre.hauteur() * 2 + 1) * (fm.getHeight());
			int largeur = 10 + largeurAffichage(g, arbre);
			Rectangle r = g.getClipBounds();

			int x = 5, y = 5;
			Dimension dim = new Dimension(r.width, r.height);
			// boolean tailleChangee = false;
			if (largeur <= r.width)
				x += (r.width - largeur) / 2;
			else {
				// tailleChangee = true;
				dim.setSize(largeur, r.height);
			}
			if (hauteur < r.height)
				y += (r.height - hauteur) / 2;
			else {
				// tailleChangee = true;
				dim.setSize(r.width, hauteur);
			}
			setPreferredSize(dim);
			dessineArbre(g, arbre, x, y);
		}
	}

}
