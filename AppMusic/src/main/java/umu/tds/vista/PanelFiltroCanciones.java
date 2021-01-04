package umu.tds.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import umu.tds.controlador.AppMusic;
import umu.tds.modelo.Cancion;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelFiltroCanciones extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int selectedRow = -1;

	AppMusic controlador = AppMusic.getInstancia();

	private TablaModelo tabla;

	public void mostrarPanel() {
		setVisible(true);
	}

	public void esconderPanel() {
		setVisible(false);
	}
	
	public void añadirCancion(Cancion c) {
		tabla.añadirFila(c);
	}
	
	public void actualizarTabla() {
		tabla.limpiarDatos();
		for (Cancion c : AppMusic.getInstancia().getCancionesRecientes()) {
			tabla.añadirFila(c);
		}
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

		tabla = new TablaModelo();

		final JTable table = new JTable(tabla);
		JScrollPane scrollPane = new JScrollPane(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		// ELIMINAR ESTO DE AQUI CUANDO SE CREEN LA BUSQUEDA
//		List<Cancion> lista = CatalogoCanciones.getUnicaInstancia().getCanciones();
//
//		for (Cancion cancion : lista) {
//			tabla.añadirFila(cancion);
//		}

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel selectionModel = table.getSelectionModel();

		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedRow = table.getSelectedRow();
			}
		});

		JButton btnPlay = new JButton("");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedRow != -1) {
					controlador.reproducirCancion(tabla.getCancionFila(selectedRow));
				}
			}
		});

		btnPlay.setIcon(new ImageIcon(PanelFiltroCanciones.class
				.getResource("/umu/tds/imagenes/music-play-pause-control-go-arrow_80458.png")));
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 3;
		gbc_btnPlay.gridy = 2;
		add(btnPlay, gbc_btnPlay);

		JButton btnRetroceder = new JButton("");
		btnRetroceder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedRow > 0)
					controlador.reproducirCancion(tabla.getCancionFila(--selectedRow));
			}
		});
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

		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.pausarCancion();
			}
		});

		JButton btnAvanzar = new JButton("");
		btnAvanzar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedRow < tabla.getRowCount() - 1) {
					controlador.reproducirCancion(tabla.getCancionFila(++selectedRow));
				}

			}
		});
		btnAvanzar.setIcon(new ImageIcon(
				PanelFiltroCanciones.class.getResource("/umu/tds/imagenes/forward-arrows-arrow-front-go.png")));
		GridBagConstraints gbc_btnAvanzar = new GridBagConstraints();
		gbc_btnAvanzar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAvanzar.gridx = 4;
		gbc_btnAvanzar.gridy = 3;
		add(btnAvanzar, gbc_btnAvanzar);

	}
}
