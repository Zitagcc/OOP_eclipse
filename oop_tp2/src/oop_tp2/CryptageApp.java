package oop_tp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CryptageApp extends JFrame implements ActionListener {
	private JButton cryptageButton, playfairButton;
    private JRadioButton cryptageRadio, decryptageRadio;
    private ButtonGroup radioGroup;
    private JTextField clefField, inputText, outputText;

    public CryptageApp() {
        setTitle("Cryptage Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        add(panel);

        cryptageRadio = new JRadioButton("Cryptage", true);
        decryptageRadio = new JRadioButton("Décryptage");
        radioGroup = new ButtonGroup();
        radioGroup.add(cryptageRadio);
        radioGroup.add(decryptageRadio);

        panel.add(cryptageRadio);
        panel.add(decryptageRadio);

        JLabel clefLabel = new JLabel("Clé de cryptage:");
        clefField = new JTextField();
        panel.add(clefLabel);
        panel.add(clefField);

        JLabel inputLabel = new JLabel("Texte d'entrée:");
        inputText = new JTextField();
        panel.add(inputLabel);
        panel.add(inputText);

        JLabel outputLabel = new JLabel("Texte de sortie:");
        outputText = new JTextField();
        outputText.setEditable(false);
        panel.add(outputLabel);
        panel.add(outputText);

        cryptageButton = new JButton("Cryptage de déplacement");
        cryptageButton.addActionListener(this);
        panel.add(cryptageButton);

        playfairButton = new JButton("Cryptage Playfair");
        playfairButton.addActionListener(this);
        panel.add(playfairButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cryptageButton) {
            if (cryptageRadio.isSelected()) {
                String clef = clefField.getText();
                String texte = inputText.getText();
                Cryptage cryptage = new Decalage(clef);
                String resultat = cryptage.cryptage(texte);
                outputText.setText(resultat);
            } else {
                String clef = clefField.getText();
                String texte = inputText.getText();
                Cryptage cryptage = new Decalage(clef);
                String resultat = cryptage.deCryptage(texte);
                outputText.setText(resultat);
            }
        } else if (e.getSource() == playfairButton) {
            if (cryptageRadio.isSelected()) {
                String clef = clefField.getText();
                String texte = inputText.getText();
                Cryptage cryptage = new Playfair(clef);
                String resultat = cryptage.cryptage(texte);
                outputText.setText(resultat);
            } else {
                String clef = clefField.getText();
                String texte = inputText.getText();
                Cryptage cryptage = new Playfair(clef);
                String resultat = cryptage.deCryptage(texte);
                outputText.setText(resultat);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CryptageApp app = new CryptageApp();
                app.setVisible(true);
            }
        });
    }
}

