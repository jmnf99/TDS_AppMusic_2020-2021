package umu.tds.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import javax.swing.SwingConstants;
import pulsador.Luz;
import umu.tds.controlador.AppMusic;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal {

	private JFrame frame;
	private JPanel panelPrincipal;
	private PanelExplorarCanciones panelExplorarCanciones;
	private PanelFiltroCanciones panelFiltroCanciones;
	private PanelNuevaPlaylist panelNuevaPlaylist;
	private PanelCreacionPlaylist panelCreacionPlaylist;
	private PanelFiltroCanciones panelMisListasDetalladas;
	private PanelFiltroCanciones panelRecientes;
	private PanelFiltroCanciones panelExitos;
	private List<String> listas;
	private ListaModelo modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		panelFiltroCanciones = new PanelFiltroCanciones();
		panelExplorarCanciones = new PanelExplorarCanciones(panelFiltroCanciones);
		panelCreacionPlaylist = new PanelCreacionPlaylist(this);
		panelNuevaPlaylist = new PanelNuevaPlaylist(panelCreacionPlaylist, this);
		panelMisListasDetalladas = new PanelFiltroCanciones();
		panelRecientes = new PanelFiltroCanciones();
		panelExitos = new PanelFiltroCanciones();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource(Constantes.icono)));
		frame.setBounds(100, 100, Constantes.x_size, Constantes.y_size);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle(Constantes.titulo);

		final JPanel panelFuncionalidad = new JPanel();
		panelFuncionalidad.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panelFuncionalidad, BorderLayout.WEST);
		GridBagLayout gbl_panelFuncionalidad = new GridBagLayout();
		gbl_panelFuncionalidad.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelFuncionalidad.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelFuncionalidad.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelFuncionalidad.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelFuncionalidad.setLayout(gbl_panelFuncionalidad);

		listas = AppMusic.getInstancia().getUsuarioActual().getNombreListas();
		modelo = new ListaModelo(listas);
		JList<String> listMisListas = new JList<String>(modelo);

		listMisListas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listMisListas.setSelectedIndex(0);
		listMisListas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listMisListas = new GridBagConstraints();
		gbc_listMisListas.fill = GridBagConstraints.BOTH;
		gbc_listMisListas.gridx = 1;
		gbc_listMisListas.gridy = 6;
		panelFuncionalidad.add(listMisListas, gbc_listMisListas);
		listMisListas.setVisible(false);

		final JButton btnTop10 = new JButton("Éxitos AppMusic");
		btnTop10.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panelExitos, BorderLayout.CENTER);
				// TODO actualizar tabla de panelExitos con las 10 canciones mas escuchadas
				listMisListas.setVisible(false);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
				frame.validate();
			}
		});
		if (AppMusic.getInstancia().getUsuarioActual().isPremium()) {
			btnTop10.setVisible(true);
		} else {
			btnTop10.setVisible(false);
		}
		btnTop10.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/temporizador.png")));
		GridBagConstraints gbc_btnTop10 = new GridBagConstraints();
		gbc_btnTop10.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTop10.insets = new Insets(0, 0, 5, 0);
		gbc_btnTop10.gridx = 1;
		gbc_btnTop10.gridy = 4;
		panelFuncionalidad.add(btnTop10, gbc_btnTop10);

		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panelExplorarCanciones, BorderLayout.NORTH);
				panelPrincipal.add(panelFiltroCanciones, BorderLayout.CENTER);

				List<Cancion> lista = CatalogoCanciones.getUnicaInstancia().getCanciones();

				for (Cancion cancion : lista) {
					panelFiltroCanciones.añadirCancion(cancion);
				}
				listMisListas.setVisible(false);
				panelFiltroCanciones.esconderPanel();
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
				frame.validate();
			}
		});

		btnExplorar.setHorizontalAlignment(SwingConstants.LEFT);
		btnExplorar.setIcon(new ImageIcon(
				VentanaPrincipal.class.getResource("/umu/tds/imagenes/magnifier-1_icon-icons.com_56924.png")));
		GridBagConstraints gbc_btnExplorar = new GridBagConstraints();
		gbc_btnExplorar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExplorar.insets = new Insets(0, 0, 5, 0);
		gbc_btnExplorar.gridx = 1;
		gbc_btnExplorar.gridy = 1;
		panelFuncionalidad.add(btnExplorar, gbc_btnExplorar);

		JButton btnNuevaPlaylist = new JButton("Nueva Playlist");
		btnNuevaPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarPanel();
				panelPrincipal.removeAll();
				panelPrincipal.add(panelNuevaPlaylist, BorderLayout.NORTH);
				panelPrincipal.add(panelCreacionPlaylist, BorderLayout.CENTER);
				listMisListas.setVisible(false);
				panelNuevaPlaylist.activarBotonCrear();
				panelCreacionPlaylist.esconderPanel();
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
				frame.validate();
			}
		});
		btnNuevaPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnNuevaPlaylist.setIcon(
				new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/-playlist-add_90050.png")));
		GridBagConstraints gbc_btnNuevaPlaylist = new GridBagConstraints();
		gbc_btnNuevaPlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNuevaPlaylist.insets = new Insets(0, 0, 5, 0);
		gbc_btnNuevaPlaylist.gridx = 1;
		gbc_btnNuevaPlaylist.gridy = 2;
		panelFuncionalidad.add(btnNuevaPlaylist, gbc_btnNuevaPlaylist);

		JButton btnReciente = new JButton("Reciente");
		btnReciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.removeAll();
				panelPrincipal.add(panelRecientes, BorderLayout.CENTER);
				panelRecientes.actualizarTablaRecientes();
				listMisListas.setVisible(false);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
				frame.validate();
			}
		});
		btnReciente.setHorizontalAlignment(SwingConstants.LEFT);
		btnReciente.setIcon(new ImageIcon(VentanaPrincipal.class
				.getResource("/umu/tds/imagenes/1491254405-recenttimesearchreloadtime_82966.png")));
		GridBagConstraints gbc_btnReciente = new GridBagConstraints();
		gbc_btnReciente.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReciente.insets = new Insets(0, 0, 5, 0);
		gbc_btnReciente.gridx = 1;
		gbc_btnReciente.gridy = 3;
		panelFuncionalidad.add(btnReciente, gbc_btnReciente);

		final JButton btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnPdf, "Pdf generado con éxito", "Generación de pdf",
						JOptionPane.INFORMATION_MESSAGE);
					AppMusic.getInstancia().generarPdf();
			}
		});
		btnPdf.setVisible(false);
		btnPdf.setIcon(new ImageIcon(PanelFiltroCanciones.class.getResource("/umu/tds/imagenes/pdf-32.png")));
		GridBagConstraints gbc_btnPdf = new GridBagConstraints();
		gbc_btnPdf.insets = new Insets(0, 0, 5, 5);
		gbc_btnPdf.gridx = 5;
		gbc_btnPdf.gridy = 3;
		panelMisListasDetalladas.add(btnPdf, gbc_btnPdf);

		JButton btnMisPlaylists = new JButton("Mis Playlists");
		btnMisPlaylists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelPrincipal.removeAll();

				if (AppMusic.getInstancia().getUsuarioActual().isPremium()) {
					btnPdf.setVisible(true);
				}

				panelPrincipal.add(panelMisListasDetalladas, BorderLayout.CENTER);
				listMisListas.setVisible(true);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
				frame.validate();
				// Anadir al panel funcionalidad la lista de playlists
			}
		});
		btnMisPlaylists.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisPlaylists
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/playlist_121110.png")));
		GridBagConstraints gbc_btnMisPlaylists = new GridBagConstraints();
		gbc_btnMisPlaylists.insets = new Insets(0, 0, 5, 0);
		gbc_btnMisPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMisPlaylists.gridx = 1;
		gbc_btnMisPlaylists.gridy = 5;
		panelFuncionalidad.add(btnMisPlaylists, gbc_btnMisPlaylists);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 374, 0, 53, 111, 10, 0 };
		gbl_panel.rowHeights = new int[] { 10, 21, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblBienvenida = new JLabel("Bienvenid@ " + AppMusic.getInstancia().getUsuarioActual().getNombre() + " "
				+ AppMusic.getInstancia().getUsuarioActual().getApellidos());
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblBienvenida = new GridBagConstraints();
		gbc_lblBienvenida.anchor = GridBagConstraints.EAST;
		gbc_lblBienvenida.insets = new Insets(0, 0, 0, 5);
		gbc_lblBienvenida.gridx = 2;
		gbc_lblBienvenida.gridy = 1;
		panel.add(lblBienvenida, gbc_lblBienvenida);

		Luz luz = new Luz();
		GridBagConstraints gbc_luz = new GridBagConstraints();
		luz.setColor(Color.CYAN);
		gbc_luz.insets = new Insets(0, 0, 0, 5);
		gbc_luz.gridx = 3;
		gbc_luz.gridy = 1;
		panel.add(luz, gbc_luz);

		final JButton btnPremium = new JButton("Mejora tu cuenta");
		if (AppMusic.getInstancia().getUsuarioActual().isPremium()) {
			btnPremium.setVisible(false);
		} else {
			btnPremium.setVisible(true);
		}
		btnPremium.setToolTipText("Sube a Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Comprobar que descuentos son aplicables
				AppMusic.getInstancia().seleccionarDescuento(LocalDate.now());

				VentanaPagoPremium ventanaPagoPremium = new VentanaPagoPremium(frame, true);
				ventanaPagoPremium.mostrarVentana();

				if (AppMusic.getInstancia().getUsuarioActual().isPremium()) {
					btnTop10.setVisible(true);
					btnPdf.setVisible(true);
					btnPremium.setVisible(false);

					panelFuncionalidad.revalidate();
					panelFuncionalidad.repaint();
					frame.validate();
				}
			}
		});
		btnPremium.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/Crown-icon.png")));
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnPremium.anchor = GridBagConstraints.EAST;
		gbc_btnPremium.insets = new Insets(0, 0, 0, 5);
		gbc_btnPremium.gridx = 4;
		gbc_btnPremium.gridy = 1;
		panel.add(btnPremium, gbc_btnPremium);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMusic.getInstancia().logout();
				VentanaLogin vLogin = new VentanaLogin();
				vLogin.mostrarVentana();
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.anchor = GridBagConstraints.WEST;
		gbc_btnLogout.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogout.gridx = 5;
		gbc_btnLogout.gridy = 1;
		panel.add(btnLogout, gbc_btnLogout);

		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
	}

	public void mostrarVentana() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void reiniciarPanel() {
		panelNuevaPlaylist.limpiarNombrePlaylist();
		panelNuevaPlaylist.activarBotonCrear();
		panelNuevaPlaylist.ocultarBotonBorrar();
	}

	public void anadirElemento(String lista) {
		modelo.anadirElemento(lista);
	}

	public void eliminarElemento(String lista) {
		modelo.eliminarElemento(lista);
	}

}
