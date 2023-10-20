package oop_tp2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CryptageGUI extends JFrame {

    private static final long serialVersionUID = 1L;
	private JTextField inputText;
    private JTextArea outputText;
    private JButton cryptButton;

    public CryptageGUI() {
        // Configurer la fenêtre
        setTitle("Cryptage GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer les composants
        inputText = new JTextField(20);
        outputText = new JTextArea(10, 20);
        outputText.setEditable(false);
        cryptButton = new JButton("Crypter");

        // Ajouter un ActionListener pour le bouton de cryptage
        cryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crypterTexte();
            }
        });

        // Créer le conteneur pour les composants
        JPanel panel = new JPanel();
        panel.add(new JLabel("Texte à crypter:"));
        panel.add(inputText);
        panel.add(cryptButton);
        panel.add(new JLabel("Texte crypté:"));
        panel.add(new JScrollPane(outputText));

        // Ajouter le panel à la fenêtre
        add(panel);

        // Afficher la fenêtre
        setVisible(true);
    }

    private void crypterTexte() {
        // Récupérer le texte de l'utilisateur
        String texte = inputText.getText();

        // Remplacer avec votre logique de cryptage
        // Ici, nous utilisons un cryptage de César avec un décalage de 3
        Cryptage cryptage = new Decalage("3");
        String texteCrypte = cryptage.cryptage(texte);

        // Afficher le texte crypté
        outputText.setText(texteCrypte);
    }

    public static void main(String[] args) {
        // Créer une instance de l'interface graphique
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CryptageGUI();
            }
        });
    }
}
