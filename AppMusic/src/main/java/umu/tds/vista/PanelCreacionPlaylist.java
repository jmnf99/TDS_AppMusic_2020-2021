package umu.tds.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import umu.tds.controlador.AppMusic;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.CatalogoEstilos;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCreacionPlaylist extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textInterprete;
	private JTextField txtTitulo;
	private CatalogoEstilos catalogoEstilos;

	private int selectedRowSearch = -1;
	private int selectedRowPlaylist = -1;

	public void mostrarPanel() {
		setVisible(true);
	}

	public void esconderPanel() {
		setVisible(false);
	}

	/**
	 * Create the panel.
	 * 
	 * @param ventanaPrincipal
	 */
	public PanelCreacionPlaylist(VentanaPrincipal ventanaPrincipal) {
		catalogoEstilos = CatalogoEstilos.getUnicaInstancia();
		setVisible(false);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 7, 60, 60, 60, 5, 5, 50, 50, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 62, 32, 32, 58, 37, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
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

		String[] arrayEstilos = catalogoEstilos.getNombreEstilos();

		JComboBox<String> comboBoxEstilos = new JComboBox<String>();
		comboBoxEstilos.setModel(new DefaultComboBoxModel<String>(arrayEstilos));
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

		TablaModelo tablaBusqueda = new TablaModelo();
		JTable tablaMusica = new JTable(tablaBusqueda);
		JScrollPane scrollPaneMusica = new JScrollPane(tablaMusica);
		GridBagConstraints gbc_scrollPaneMusica = new GridBagConstraints();
		gbc_scrollPaneMusica.gridheight = 4;
		gbc_scrollPaneMusica.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneMusica.gridwidth = 3;
		gbc_scrollPaneMusica.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneMusica.gridx = 1;
		gbc_scrollPaneMusica.gridy = 2;
		add(scrollPaneMusica, gbc_scrollPaneMusica);

		tablaMusica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel selectionModel = tablaMusica.getSelectionModel();

		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedRowSearch = tablaMusica.getSelectedRow();
			}
		});

		List<Cancion> lista = CatalogoCanciones.getUnicaInstancia().getCanciones();

		for (Cancion cancion : lista) {
			tablaBusqueda.añadirFila(cancion);
		}

		JButton btnDerecha = new JButton(">>");
		GridBagConstraints gbc_btnDerecha = new GridBagConstraints();
		gbc_btnDerecha.gridwidth = 2;
		gbc_btnDerecha.insets = new Insets(0, 0, 5, 5);
		gbc_btnDerecha.gridx = 4;
		gbc_btnDerecha.gridy = 3;
		add(btnDerecha, gbc_btnDerecha);

		TablaModelo tablaPlaylist = new TablaModelo();
		JTable tablePlaylist = new JTable(tablaPlaylist);
		JScrollPane scrollPanePlaylist = new JScrollPane(tablePlaylist);
		scrollPanePlaylist
				.setBorder(new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 51, 255)),
						"Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_scrollPanePlayList = new GridBagConstraints();
		gbc_scrollPanePlayList.gridheight = 4;
		gbc_scrollPanePlayList.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanePlayList.gridwidth = 3;
		gbc_scrollPanePlayList.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePlayList.gridx = 6;
		gbc_scrollPanePlayList.gridy = 2;
		add(scrollPanePlaylist, gbc_scrollPanePlayList);

		tablePlaylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel selectionModel1 = tablePlaylist.getSelectionModel();

		selectionModel1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedRowPlaylist = tablePlaylist.getSelectedRow();
			}
		});

		JButton btnIzquierda = new JButton("<<");
		GridBagConstraints gbc_btnIzquierda = new GridBagConstraints();
		gbc_btnIzquierda.gridwidth = 2;
		gbc_btnIzquierda.insets = new Insets(0, 0, 5, 5);
		gbc_btnIzquierda.gridx = 4;
		gbc_btnIzquierda.gridy = 4;
		add(btnIzquierda, gbc_btnIzquierda);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(btnAceptar, "Playlist creada con éxito", "Creación playlist",
						JOptionPane.INFORMATION_MESSAGE);
				AppMusic.getInstancia().confirmarListaCanciones(tablaPlaylist.getCanciones());
				setVisible(false);
				ventanaPrincipal.reiniciarPanel();
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.gridwidth = 2;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 6;
		add(btnAceptar, gbc_btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Debe mostrarse como si se volviera a clickar en NuevaPlaylist
				setVisible(false);
				ventanaPrincipal.reiniciarPanel();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 6;
		add(btnCancelar, gbc_btnCancelar);

		btnDerecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedRowSearch != -1) {
					Cancion c = tablaBusqueda.getCancionFila(selectedRowSearch);
					tablaPlaylist.añadirFila(c);
				}
			}
		});

		btnIzquierda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedRowPlaylist != -1)
					tablaPlaylist.eliminarFila(selectedRowPlaylist);
			}
		});

	}
}
