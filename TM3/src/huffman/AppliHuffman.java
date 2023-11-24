package huffman;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class AppliHuffman {

	private JFrame frmTmCodage;
	private Huffman codage;
	private JTextArea textAreaClair;
	private JTextArea textAreaCode;
	private JLabel lblMessage;
	private DessinHuffman panelDessinArbre;
	private JTextArea textAreaDico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppliHuffman window = new AppliHuffman();
					window.frmTmCodage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppliHuffman() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTmCodage = new JFrame();
		frmTmCodage.setTitle("TM3 - Codage de Huffman");
		frmTmCodage.setBounds(100, 100, 750, 600);
		frmTmCodage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelDico = new JPanel();
		panelDico.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmTmCodage.getContentPane().add(panelDico, BorderLayout.EAST);
		panelDico.setLayout(new BorderLayout(0, 0));

		JLabel lblDico = new JLabel("Dictionnaire");
		lblDico.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDico.setHorizontalAlignment(SwingConstants.CENTER);
		panelDico.add(lblDico, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelDico.add(scrollPane, BorderLayout.CENTER);

		textAreaDico = new JTextArea();
		scrollPane.setViewportView(textAreaDico);

		lblMessage = new JLabel(" ");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		frmTmCodage.getContentPane().add(lblMessage, BorderLayout.SOUTH);

		JPanel panelCentre = new JPanel();
		frmTmCodage.getContentPane().add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelArbre = new JPanel();
		panelArbre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentre.add(panelArbre);
		panelArbre.setLayout(new BorderLayout(0, 0));

		JLabel lblArbre = new JLabel("Arbre");
		lblArbre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblArbre.setHorizontalAlignment(SwingConstants.CENTER);
		panelArbre.add(lblArbre, BorderLayout.NORTH);

		JScrollPane scrollPane_1 = new JScrollPane();
		panelArbre.add(scrollPane_1, BorderLayout.CENTER);

		panelDessinArbre = new DessinHuffman();
		scrollPane_1.setViewportView(panelDessinArbre);

		JPanel panelTextes = new JPanel();
		panelTextes.setBorder(null);
		panelCentre.add(panelTextes);
		panelTextes.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelClair = new JPanel();
		panelClair.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTextes.add(panelClair);
		panelClair.setLayout(new BorderLayout(0, 0));

		JLabel lblClair = new JLabel("Texte clair");
		lblClair.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelClair.add(lblClair, BorderLayout.NORTH);

		JPanel panelBoutonsClair = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBoutonsClair.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelClair.add(panelBoutonsClair, BorderLayout.SOUTH);

		JButton btnCreer = new JButton("Cr\u00E9er");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					codage = new Huffman(textAreaClair.getText());
					panelDessinArbre.setCodage(codage);
					StringBuffer sDico = new StringBuffer();
					Map<Character, String> dico = codage.getDictionnaire();
					for (Character c : dico.keySet())
						sDico.append(c).append(" : ").append(dico.get(c)).append('\n');
					textAreaDico.setText(sDico.toString());
					message("Codage cré (" + dico.size() + " caractéres)", Color.BLACK);
				} catch (IllegalArgumentException iae) {
					message("Impossible de créer le codage : " + iae.getMessage(), Color.RED);
				}
				// -----
			}
		});
		panelBoutonsClair.add(btnCreer);

		JButton btnCoder = new JButton("Coder");
		btnCoder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String res = codage.code(textAreaClair.getText());
					textAreaCode.setText(res);
					statut();
				} catch (CaractereInconnuException e1) {
					message("Impossible de coder : au moins un caractére est absent du codage.", Color.RED);
				}
				// -----
			}
		});
		panelBoutonsClair.add(btnCoder);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelClair.add(scrollPane_2, BorderLayout.CENTER);

		textAreaClair = new JTextArea();
		scrollPane_2.setViewportView(textAreaClair);

		JPanel panelCode = new JPanel();
		panelCode.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTextes.add(panelCode);
		panelCode.setLayout(new BorderLayout(0, 0));

		JLabel lblTexteCod = new JLabel("Texte cod\u00E9");
		lblTexteCod.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelCode.add(lblTexteCod, BorderLayout.NORTH);

		JPanel panelBoutonsCode = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelBoutonsCode.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelCode.add(panelBoutonsCode, BorderLayout.SOUTH);

		JButton btnDcoder = new JButton("D\u00E9coder");
		btnDcoder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifCodage();
				try {
					String res = codage.decode(textAreaCode.getText());
					textAreaClair.setText(res);
					statut();
				} catch (FinDeTexteInattendueException e1) {
					message("Impossible de décoder : fin de texte inattendue.", Color.RED);
				}
				// -----
			}
		});
		panelBoutonsCode.add(btnDcoder);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCode.add(scrollPane_3, BorderLayout.CENTER);

		textAreaCode = new JTextArea();
		scrollPane_3.setViewportView(textAreaCode);
	}

	private void message(String texte, Color col) {
		lblMessage.setForeground(col);
		lblMessage.setText(texte);
	}

	private void statut() {
		int lgClair = textAreaClair.getText().length();
		String stat = "Texte clair : " + lgClair + "caractéres = " + lgClair * 16 + " bits / texte codé : ";
		stat += textAreaCode.getText().length() + " bits.";
		message(stat, Color.BLACK);
	}

	private void verifCodage() {
		if (codage == null)
			message("Aucun codage n'a été crée !", Color.RED);

	}
}
