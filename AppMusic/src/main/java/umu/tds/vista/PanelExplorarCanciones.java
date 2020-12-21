package umu.tds.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import umu.tds.modelo.CatalogoEstilos;
import umu.tds.modelo.EstiloMusical;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.DropMode;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PanelExplorarCanciones extends JPanel {
	private JTextField textInterprete;
	private JTextField textTitulo;
	private CatalogoEstilos catalogoEstilos;
	/**
	 * Create the panel.
	 * 
	 */
	public PanelExplorarCanciones(final PanelFiltroCanciones filtro) {
		
		catalogoEstilos = CatalogoEstilos.getUnicaInstancia();
		
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
		
		List<EstiloMusical> listaEstilos = catalogoEstilos.getEstilos();
		
		String[] arrayEstilos = new String[listaEstilos.size()];
		
		int i = 0;
		for (EstiloMusical e: listaEstilos) {
			arrayEstilos[i] = e.getNombre();
			i++;
		}
		
		JComboBox comboBoxEstilo = new JComboBox();
		comboBoxEstilo.setToolTipText("Estilo");
		comboBoxEstilo.setModel(new DefaultComboBoxModel(arrayEstilos));
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
