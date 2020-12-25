package umu.tds.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class PanelCreacionPlaylist extends JPanel {
	private JTextField textInterprete;
	private JTextField txtTitulo;

	public void mostrarPanel() {
		setVisible(true);
	}
	
	public void esconderPanel() {
		setVisible(false);
	}
	
	/**
	 * Create the panel.
	 */
	public PanelCreacionPlaylist() {
		setVisible(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{7, 60, 60, 60, 5, 5, 50, 50, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 62, 32, 32, 58, 37, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textInterprete = new JTextField();
		textInterprete.setText("Intérprete");
		GridBagConstraints gbc_textInterprete = new GridBagConstraints();
		gbc_textInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_textInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_textInterprete.gridx = 2;
		gbc_textInterprete.gridy = 1;
		add(textInterprete, gbc_textInterprete);
		textInterprete.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("Título");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.gridwidth = 3;
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 3;
		gbc_txtTitulo.gridy = 1;
		add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		JComboBox comboBoxEstilos = new JComboBox();
		comboBoxEstilos.setModel(new DefaultComboBoxModel(new String[] {"Bolero", "Cantautor", "Clasica", "Flamenco", "Jazz", "Opera", "Pop", "Rock", "Romantica"}));
		GridBagConstraints gbc_comboBoxEstilos = new GridBagConstraints();
		gbc_comboBoxEstilos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstilos.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstilos.gridx = 6;
		gbc_comboBoxEstilos.gridy = 1;
		add(comboBoxEstilos, gbc_comboBoxEstilos);
		
		JButton btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 7;
		gbc_btnBuscar.gridy = 1;
		add(btnBuscar, gbc_btnBuscar);
		
		JTable tablaMusica = new JTable(new TablaModelo());
		JScrollPane scrollPaneMusica = new JScrollPane(tablaMusica);
		GridBagConstraints gbc_scrollPaneMusica = new GridBagConstraints();
		gbc_scrollPaneMusica.gridheight = 4;
		gbc_scrollPaneMusica.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneMusica.gridwidth = 3;
		gbc_scrollPaneMusica.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneMusica.gridx = 1;
		gbc_scrollPaneMusica.gridy = 2;
		add(scrollPaneMusica, gbc_scrollPaneMusica);
		
		JButton btnDerecha = new JButton(">>");
		GridBagConstraints gbc_btnDerecha = new GridBagConstraints();
		gbc_btnDerecha.gridwidth = 2;
		gbc_btnDerecha.insets = new Insets(0, 0, 5, 5);
		gbc_btnDerecha.gridx = 4;
		gbc_btnDerecha.gridy = 3;
		add(btnDerecha, gbc_btnDerecha);
		
		JTable tablaPlaylist = new JTable(new TablaModelo());
		JScrollPane scrollPanePlaylist = new JScrollPane(tablaPlaylist);
		scrollPanePlaylist.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 51, 255)), "Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_scrollPanePlayList = new GridBagConstraints();
		gbc_scrollPanePlayList.gridheight = 4;
		gbc_scrollPanePlayList.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanePlayList.gridwidth = 3;
		gbc_scrollPanePlayList.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePlayList.gridx = 6;
		gbc_scrollPanePlayList.gridy = 2;
		add(scrollPanePlaylist, gbc_scrollPanePlayList);
		
		JButton btnIzquierda = new JButton("<<");
		GridBagConstraints gbc_btnIzquierda = new GridBagConstraints();
		gbc_btnIzquierda.gridwidth = 2;
		gbc_btnIzquierda.insets = new Insets(0, 0, 5, 5);
		gbc_btnIzquierda.gridx = 4;
		gbc_btnIzquierda.gridy = 4;
		add(btnIzquierda, gbc_btnIzquierda);
		
		JButton btnAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.gridwidth = 2;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 6;
		add(btnAceptar, gbc_btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 6;
		add(btnCancelar, gbc_btnCancelar);

	}

}
