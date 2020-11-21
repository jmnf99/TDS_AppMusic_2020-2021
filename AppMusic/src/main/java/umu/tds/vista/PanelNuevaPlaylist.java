package umu.tds.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class PanelNuevaPlaylist extends JPanel {
	private JTextField textNombrePlaylist;

	/**
	 * Create the panel.
	 */
	public PanelNuevaPlaylist() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.columnWidths = new int[]{145, 96, 59, 0, 0, 120, 0};
		gbl_panelNorte.rowHeights = new int[]{10, 21, 0};
		gbl_panelNorte.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelNorte.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelNorte.setLayout(gbl_panelNorte);
		
		textNombrePlaylist = new JTextField();
		GridBagConstraints gbc_textNombrePlaylist = new GridBagConstraints();
		gbc_textNombrePlaylist.gridwidth = 2;
		gbc_textNombrePlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombrePlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_textNombrePlaylist.gridx = 1;
		gbc_textNombrePlaylist.gridy = 1;
		panelNorte.add(textNombrePlaylist, gbc_textNombrePlaylist);
		textNombrePlaylist.setColumns(10);
		
		JButton btnCrearPlaylist = new JButton("Crear");
		GridBagConstraints gbc_btnCrearPlaylist = new GridBagConstraints();
		gbc_btnCrearPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrearPlaylist.gridx = 4;
		gbc_btnCrearPlaylist.gridy = 1;
		panelNorte.add(btnCrearPlaylist, gbc_btnCrearPlaylist);

	}

}
