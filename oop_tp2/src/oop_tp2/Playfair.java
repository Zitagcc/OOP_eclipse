package oop_tp2;
import java.util.HashMap;
import java.util.Map;

public class Playfair extends Cryptage {
    private char[][] matriceDeCryptage;

    public Playfair(String clef) {
        super(clef);
        this.matriceDeCryptage = generateMatriceDeCryptage(clef);
    }

    @Override
    public String toString() {
        StringBuilder matrixString = new StringBuilder();
        for (int i = 0; i < matriceDeCryptage.length; i++) {
            for (int j = 0; j < matriceDeCryptage[i].length; j++) {
                matrixString.append(matriceDeCryptage[i][j]);
            }
            matrixString.append(" ");
        }

        return "Cryptage Playfair\nMot Clef: " + clef + "\nMatrice de cryptage : " + matrixString.toString();
    }

    @Override
    public String cryptage(String s) {
        StringBuilder encryptedText = new StringBuilder();
        String preparedText = prepareText(s);

        for (int i = 0; i < preparedText.length(); i += 2) {
            char c1 = preparedText.charAt(i);
            char c2 = preparedText.charAt(i + 1);
            int[] indices1 = findIndices(c1);
            int[] indices2 = findIndices(c2);

            if (indices1[0] == indices2[0]) {
                // Même ligne
                encryptedText.append(matriceDeCryptage[indices1[0]][(indices1[1] + 1) % 6]);
                encryptedText.append(matriceDeCryptage[indices2[0]][(indices2[1] + 1) % 6]);
            } else if (indices1[1] == indices2[1]) {
                // Même colonne
                encryptedText.append(matriceDeCryptage[(indices1[0] + 1) % 6][indices1[1]]);
                encryptedText.append(matriceDeCryptage[(indices2[0] + 1) % 6][indices2[1]]);
            } else {
                // Ni même ligne ni même colonne
                encryptedText.append(matriceDeCryptage[indices1[0]][indices2[1]]);
                encryptedText.append(matriceDeCryptage[indices2[0]][indices1[1]]);
            }
        }

        return encryptedText.toString();
    }

    @Override
    public String deCryptage(String s) {
        StringBuilder decryptedText = new StringBuilder();
        String preparedText = prepareText(s);

        for (int i = 0; i < preparedText.length(); i += 2) {
            char c1 = preparedText.charAt(i);
            char c2 = preparedText.charAt(i + 1);
            int[] indices1 = findIndices(c1);
            int[] indices2 = findIndices(c2);

            if (indices1[0] == indices2[0]) {
                // Même ligne
                decryptedText.append(matriceDeCryptage[indices1[0]][(indices1[1] + 5) % 6]);
                decryptedText.append(matriceDeCryptage[indices2[0]][(indices2[1] + 5) % 6]);
            } else if (indices1[1] == indices2[1]) {
                // Même colonne
                decryptedText.append(matriceDeCryptage[(indices1[0] + 5) % 6][indices1[1]]);
                decryptedText.append(matriceDeCryptage[(indices2[0] + 5) % 6][indices2[1]]);
            } else {
                // Ni même ligne ni même colonne
                decryptedText.append(matriceDeCryptage[indices1[0]][indices2[1]]);
                decryptedText.append(matriceDeCryptage[indices2[0]][indices1[1]]);
            }
        }

        return decryptedText.toString();
    }

    private String prepareText(String text) {
        // Supprime les espaces et convertit en lettres minuscules
        text = text.replaceAll(" ", "").toLowerCase();

        // Remplace 'j' par 'i' dans le texte
        text = text.replace("j", "i");

        // Assure une longueur pair en ajoutant un 'x' à la fin si nécessaire
        if (text.length() % 2 != 0) {
            text += "x";
        }

        return text;
    }

    private int[] findIndices(char c) {
        for (int i = 0; i < matriceDeCryptage.length; i++) {
            for (int j = 0; j < matriceDeCryptage[i].length; j++) {
                if (matriceDeCryptage[i][j] == c) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    private char[][] generateMatriceDeCryptage(String clef) {
        char[][] matrice = new char[6][6];
        String alphabetDeCryptage = clef.toLowerCase();

        // Remplit la matrice avec le mot-clef
        int row = 0;
        int col = 0;

        for (char c : alphabetDeCryptage.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                if (row == 6) {
                    break;
                }
                matrice[row][col] = c;
                col++;
                if (col == 6) {
                    col = 0;
                    row++;
                }
            }
        }

        // Remplit la matrice avec le reste de l'alphabet
        for (char c = 'a'; c <= 'z'; c++) {
            if (alphabetDeCryptage.indexOf(c) == -1) {
                if (row == 6) {
                    break;
                }
                matrice[row][col] = c;
                col++;
                if (col == 6) {
                    col = 0;
                    row++;
                }
            }
        }

        return matrice;
    }
}
