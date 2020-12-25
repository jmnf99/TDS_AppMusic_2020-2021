package umu.tds.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PanelFiltroCanciones extends JPanel {
	
	public void mostrarPanel() {
		setVisible(true);
	}
	
	public void esconderPanel() {
		setVisible(false);
	}
	
	
	/**
	 * Create the panel.
	 */
	public PanelFiltroCanciones() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 7, 130, 0, 130, 0, 130, 15 };
		gridBagLayout.rowHeights = new int[] { 5, 0, 0, 0, 10 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		JTable table = new JTable(new TablaModelo());
		JScrollPane scrollPane = new JScrollPane(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		JButton btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(PanelFiltroCanciones.class
				.getResource("/umu/tds/imagenes/music-play-pause-control-go-arrow_80458.png")));
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 3;
		gbc_btnPlay.gridy = 2;
		add(btnPlay, gbc_btnPlay);
		
				JButton btnRetroceder = new JButton("");
				btnRetroceder.setIcon(new ImageIcon(
						PanelFiltroCanciones.class.getResource("/umu/tds/imagenes/back-backwards-repeat-arrows-arrow.png")));
				GridBagConstraints gbc_btnRetroceder = new GridBagConstraints();
				gbc_btnRetroceder.insets = new Insets(0, 0, 5, 5);
				gbc_btnRetroceder.gridx = 2;
				gbc_btnRetroceder.gridy = 3;
				add(btnRetroceder, gbc_btnRetroceder);

		JButton btnPause = new JButton("");
		btnPause.setIcon(new ImageIcon(
				PanelFiltroCanciones.class.getResource("/umu/tds/imagenes/music-pause-stop-control-play.png")));
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.insets = new Insets(0, 0, 5, 5);
		gbc_btnPause.gridx = 3;
		gbc_btnPause.gridy = 3;
		add(btnPause, gbc_btnPause);
		
				JButton btnAvanzar = new JButton("");
				btnAvanzar.setIcon(new ImageIcon(
						PanelFiltroCanciones.class.getResource("/umu/tds/imagenes/forward-arrows-arrow-front-go.png")));
				GridBagConstraints gbc_btnAvanzar = new GridBagConstraints();
				gbc_btnAvanzar.insets = new Insets(0, 0, 5, 5);
				gbc_btnAvanzar.gridx = 4;
				gbc_btnAvanzar.gridy = 3;
				add(btnAvanzar, gbc_btnAvanzar);

	}
}
