package umu.tds.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.DropMode;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelExplorarCanciones extends JPanel {
	private JTextField textInterprete;
	private JTextField textTitulo;
	/**
	 * Create the panel.
	 * 
	 */
	public PanelExplorarCanciones(final PanelFiltroCanciones filtro) {
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textInterprete = new JTextField();
		textInterprete.setText("Intérprete");
		panelNorte.add(textInterprete);
		textInterprete.setColumns(10);
		
		textTitulo = new JTextField();
		textTitulo.setText("Título");
		panelNorte.add(textTitulo);
		textTitulo.setColumns(10);
		
		JComboBox comboBoxEstilo = new JComboBox();
		comboBoxEstilo.setToolTipText("Estilo");
		comboBoxEstilo.setModel(new DefaultComboBoxModel(new String[] {"Bolero", "Cantautor", "Clasica", "Flamenco", "Jazz", "Opera", "Pop", "Rock", "Romantica"}));
		panelNorte.add(comboBoxEstilo);
		
		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				filtro.mostrarPanel();
			}
		});
		panelCentral.add(btnBuscar);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelCentral.add(btnCancelar);

	}

}
