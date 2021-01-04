package umu.tds.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import umu.tds.controlador.AppMusic;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelNuevaPlaylist extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textNombrePlaylist;
	private JButton btnCrearPlaylist;
	private JButton btnBorrar;

	/**
	 * Create the panel.
	 * @param ventanaPrincipal 
	 */
	public PanelNuevaPlaylist(final PanelCreacionPlaylist playlist, VentanaPrincipal ventanaPrincipal) {
		setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.columnWidths = new int[] { 145, 96, 0, 120, 0 };
		gbl_panelNorte.rowHeights = new int[] { 10, 21, 0 };
		gbl_panelNorte.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelNorte.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelNorte.setLayout(gbl_panelNorte);

		textNombrePlaylist = new JTextField();
		GridBagConstraints gbc_textNombrePlaylist = new GridBagConstraints();
		gbc_textNombrePlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombrePlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_textNombrePlaylist.gridx = 1;
		gbc_textNombrePlaylist.gridy = 1;
		panelNorte.add(textNombrePlaylist, gbc_textNombrePlaylist);
		textNombrePlaylist.setColumns(10);

		btnCrearPlaylist = new JButton("Crear");
		btnCrearPlaylist.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (btnCrearPlaylist.isEnabled()) {
					String nombrePlaylist = textNombrePlaylist.getText();
					if (nombrePlaylist.isEmpty()) {
						JOptionPane.showMessageDialog(btnCrearPlaylist, "No se puede crear una playlist sin nombre.",
								"Error en la creación", JOptionPane.ERROR_MESSAGE);

					} else if (AppMusic.getInstancia().existePlaylistUsuario(nombrePlaylist)) {

						JOptionPane.showMessageDialog(btnCrearPlaylist, "Ya existe una playlist con ese nombre.",
								"Playlist existente", JOptionPane.WARNING_MESSAGE);
						
						AppMusic.getInstancia().setListaActual(nombrePlaylist);
						mostrarBotonBorrar();
						playlist.mostrarPanel();
						desactivarBotonCrear();
						// TODO Cargar la playlist existente

					} else {
						Object[] options = { "Si", "No" };
						int opcion = JOptionPane.showOptionDialog(btnCrearPlaylist, "¿Deseas crear una nueva playlist?",
								"Crear nueva playlist", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, options[0]);
						if (opcion == JOptionPane.YES_OPTION) {
							AppMusic.getInstancia().crearListaCanciones(nombrePlaylist);
							playlist.mostrarPanel();
							desactivarBotonCrear();
						}
					}
				}
			}
		});
		GridBagConstraints gbc_btnCrearPlaylist = new GridBagConstraints();
		gbc_btnCrearPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrearPlaylist.gridx = 2;
		gbc_btnCrearPlaylist.gridy = 1;
		panelNorte.add(btnCrearPlaylist, gbc_btnCrearPlaylist);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(btnBorrar,
						"Lista '" + AppMusic.getInstancia().getListaActual() + "' borrada con éxito.",
						"Borrado realizado", JOptionPane.INFORMATION_MESSAGE);
				ventanaPrincipal.eliminarElemento(AppMusic.getInstancia().getListaActual());
				AppMusic.getInstancia().eliminarUsuarioLista();
				ocultarBotonBorrar();
				playlist.esconderPanel();
				limpiarNombrePlaylist();
				activarBotonCrear();
			}
		});
		btnBorrar.setVisible(false);
		GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
		gbc_btnBorrar.anchor = GridBagConstraints.WEST;
		gbc_btnBorrar.gridx = 3;
		gbc_btnBorrar.gridy = 1;
		panelNorte.add(btnBorrar, gbc_btnBorrar);

	}

	public void activarBotonCrear() {
		this.btnCrearPlaylist.setEnabled(true);
	}

	public void desactivarBotonCrear() {
		this.btnCrearPlaylist.setEnabled(false);
	}

	public void mostrarBotonBorrar() {
		this.btnBorrar.setVisible(true);
	}

	public void ocultarBotonBorrar() {
		this.btnBorrar.setVisible(false);
	}

	public void limpiarNombrePlaylist() {
		this.textNombrePlaylist.setText("");
	}
}
