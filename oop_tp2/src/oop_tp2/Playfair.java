package oop_tp2;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Playfair extends Cryptage {
    private char[][] matrix;

    public Playfair(String clef) {
        super(clef);
        this.matrix = generatematrix(clef);
    }
    
    private char[][] generatematrix(String motClef) {
        char[][] matrice = new char[6][6];
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        
        int motClefLength = motClef.length();
        
        int currentIndex = 0;
        boolean[] used = new boolean[36];

        for (int i = 0; i < motClefLength; i++) {
            char c = Character.toLowerCase(motClef.charAt(i));    
            
            if (c >= 'a' && c <= 'z' && !used[c - 'a']) {
                matrice[currentIndex / 6][currentIndex % 6] = c;
                used[c - 'a'] = true;
                currentIndex++;
            }
            else if( c < 'a' && c > 'z' && !used[c+25 - 48]) {
            	matrice[currentIndex / 6][currentIndex % 6] = c;
                used[c +25 - 48] = true;
                currentIndex++;  	
            }
        }

		
		  for (int i = 0; i < alphabet.length(); i++) { 
			  char c = alphabet.charAt(i); 
			  //System.out.println(c);
			  if(i <26) { 
				  if (!used[c - 'a']) {
				  matrice[currentIndex / 6][currentIndex % 6] =c; 
				  used[c - 'a'] = true; currentIndex++; 
				  } 
			  }
			  else{
				  
				  matrice[currentIndex / 6][currentIndex % 6] =c; 
				  used[c +25 - 48] = true; currentIndex++; 
				  
			  }
			  
		  }
		 
		  System.out.println("Playfair Cipher Key Matrix:");
			
			for (int i = 0; i < 6; i++)
				System.out.println(Arrays.toString(matrice[i]));

        return matrice;
    }
    
    private String pretraiterTexte(String texte) {
        // Supprimer les espaces et convertir en minuscules
        // texte = texte.replaceAll(" ", "").toLowerCase();
        texte = texte.toLowerCase();
        // Supprimer les caractères non autorisés
        // texte = texte.replaceAll("[^a-z1-9]", "");
        // // Remplacer 'j' par 'i'
        // texte = texte.replace("j", "i");
        return texte;
    }
    
    
    
	@Override
	public String cryptage(String texte) {
	        texte = pretraiterTexte(texte);
	        
            // case 0
            // si l’un des caractères ne se trouve pas dans l’alphabet, les deux caractères sont laissés tel quel.
            // si le premier caractère est le dernier de la chaîne, il est laissé tel quel.

	        if (texte.length()==1) {
	        	return texte;
	        }
	        
	        StringBuilder texteCrypte = new StringBuilder();
	        for (int i = 0; i < texte.length(); i += 2) {
	            char c1 = texte.charAt(i);
	            char c2 = (i + 1 < texte.length()) ? texte.charAt(i + 1) : ' ';

	            int[] pos1 = findPosition(c1);
	            int[] pos2 = findPosition(c2);
	            
	            char newC1 = matrix[0][0], newC2 = matrix[0][0];
	            
	            // case 1
	            //Si les deux caractères sont sur la même ligne de la matrice, ils sont remplacés 
	            //par les deux caractères suivants dans la ligne. Le caractère suivant le dernier caractère de la ligne est le
	            //premier caractère de la même ligne.
	                   
	            if (pos1[0] == pos2[0] && pos1[0]!=100) {
	            	newC1 = matrix[pos1[0]][(pos1[1] + 1) % 6];
	                newC2 = matrix[pos2[0]][(pos2[1] + 1) % 6];
	            }
	            // case 2
	            //Si les deux caractères sont sur la même colonne de la matrice, ils sont remplacés par les deux
	            //caractères suivants dans la colonne. Le caractère qui suit le dernier caractère de la colonne est
	            //le premier caractère de la même colonne.
	            else if (pos1[1] == pos2[1] && pos1[1]!=100) {
	            	 newC1 = matrix[(pos1[0] + 1) % 6][pos1[1]];
	            	 newC2 = matrix[(pos2[0] + 1) % 6][pos2[1]];
	            }
	            // case 3
	            //Si les deux caractères ne sont ni sur la même ligne, ni sur la même colonne, on remplace le
	            //premier caractère par le caractère qui est sur la même ligne que le premier caractère et 
	            //la même colonne que le second, et on remplace le deuxième caractère par le caractère qui est 
	            //sur la même ligne que le deuxième caractère et la même colonne que le premier.
	            else if (pos1[0] != pos2[0] && pos1[1] != pos2[1]) {
	            	if (pos1[0] == 100 || pos2[0] == 100) {
	            		newC1 = c1;
		                newC2 = c2;
	            	}
	            	else {           		
	            		newC1 = (char)(matrix[pos1[0]][pos2[1]]);
		                newC2 = matrix[pos2[0]][pos1[1]];
	            	}
	            }
	            texteCrypte.append(newC1).append(newC2);
	        }
	        return texteCrypte.toString();
	    }

	@Override
	public String deCryptage(String texte) {
		if (texte.length()==1) {
        	return texte;
        }
        
        StringBuilder texteDecrypte = new StringBuilder();
        for (int i = 0; i < texte.length(); i += 2) {
            char c1 = texte.charAt(i);
            char c2 = (i + 1 < texte.length()) ? texte.charAt(i + 1) : ' ';

            int[] pos1 = findPosition(c1);
            int[] pos2 = findPosition(c2);
            
            char newC1 = matrix[0][0], newC2 = matrix[0][0];
            
            
            // case 1
            //Si les deux caractères sont sur la même ligne de la matrice, ils sont remplacés 
            //par les deux caractères suivants dans la ligne. Le caractère suivant le dernier caractère de la ligne est le
            //premier caractère de la même ligne.
                   
            if (pos1[0] == pos2[0] && pos1[0]!=100) {
            	newC1 = matrix[pos1[0]][(pos1[1] + 5) % 6];
                newC2 = matrix[pos2[0]][(pos2[1] + 5) % 6];
            }
            // case 2
            //Si les deux caractères sont sur la même colonne de la matrice, ils sont remplacés par les deux
            //caractères suivants dans la colonne. Le caractère qui suit le dernier caractère de la colonne est
            //le premier caractère de la même colonne.
            else if (pos1[1] == pos2[1] && pos1[1]!=100) {
            	 newC1 = matrix[(pos1[0] + 5) % 6][pos1[1]];
            	 newC2 = matrix[(pos2[0] + 5) % 6][pos2[1]];
            }
            // case 3
            //Si les deux caractères ne sont ni sur la même ligne, ni sur la même colonne, on remplace le
            //premier caractère par le caractère qui est sur la même ligne que le premier caractère et 
            //la même colonne que le second, et on remplace le deuxième caractère par le caractère qui est 
            //sur la même ligne que le deuxième caractère et la même colonne que le premier.
            else if (pos1[0] != pos2[0] && pos1[1] != pos2[1]) {
            	if (pos1[0] == 100 || pos2[0] == 100) {
            		newC1 = c1;
	                newC2 = c2;
            	}
            	else {           		
            		newC1 = matrix[pos1[0]][pos2[1]];
	                newC2 = matrix[pos2[0]][pos1[1]];
            	}
            }
            texteDecrypte.append(newC1).append(newC2);
        }
        return texteDecrypte.toString();
	    }
	
	private int[] findPosition(char c) {
        int[] pos = new int[2];
        pos[0] = 100;
        pos[1] = 100;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (matrix[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }
	

	public static void main(String[] args) { 
        Cryptage cryptagePlayfair = new Playfair("Playfair");
	  
		String texte = "le langage java"; 
		System.out.println("Texte original: " + texte);
	    
		
		  String texteCrypte = cryptagePlayfair.cryptage(texte);
		  System.out.println("Texte crypté: " + texteCrypte);
		  
		  String texteDecrypte = cryptagePlayfair.deCryptage(texteCrypte);
		  System.out.println("Texte décrypté: " + texteDecrypte);
		 
			 
	}


	 
}
