package umu.tds.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class VentanaPrincipal {

	private JFrame frame;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblBienvenida = new JLabel("Bienvenid@");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblBienvenida);
		
		JButton btnPremium = new JButton("Mejora tu cuenta");
		panel.add(btnPremium);
		
		JButton btnLogout = new JButton("Logout");
		panel.add(btnLogout);
		
		JPanel panelFuncionalidad = new JPanel();
		panelFuncionalidad.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(panelFuncionalidad, BorderLayout.WEST);
		GridBagLayout gbl_panelFuncionalidad = new GridBagLayout();
		gbl_panelFuncionalidad.columnWidths = new int[]{0, 0, 0};
		gbl_panelFuncionalidad.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelFuncionalidad.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelFuncionalidad.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelFuncionalidad.setLayout(gbl_panelFuncionalidad);
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.setHorizontalAlignment(SwingConstants.LEFT);
		btnExplorar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/magnifier-1_icon-icons.com_56924.png")));
		GridBagConstraints gbc_btnExplorar = new GridBagConstraints();
		gbc_btnExplorar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExplorar.insets = new Insets(0, 0, 5, 0);
		gbc_btnExplorar.gridx = 1;
		gbc_btnExplorar.gridy = 1;
		panelFuncionalidad.add(btnExplorar, gbc_btnExplorar);
		
		JButton btnNuevaPlaylist = new JButton("Nueva Playlist");
		btnNuevaPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnNuevaPlaylist.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/-playlist-add_90050.png")));
		GridBagConstraints gbc_btnNuevaPlaylist = new GridBagConstraints();
		gbc_btnNuevaPlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNuevaPlaylist.insets = new Insets(0, 0, 5, 0);
		gbc_btnNuevaPlaylist.gridx = 1;
		gbc_btnNuevaPlaylist.gridy = 2;
		panelFuncionalidad.add(btnNuevaPlaylist, gbc_btnNuevaPlaylist);
		
		JButton btnReciente = new JButton("Reciente");
		btnReciente.setHorizontalAlignment(SwingConstants.LEFT);
		btnReciente.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/1491254405-recenttimesearchreloadtime_82966.png")));
		GridBagConstraints gbc_btnReciente = new GridBagConstraints();
		gbc_btnReciente.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReciente.insets = new Insets(0, 0, 5, 0);
		gbc_btnReciente.gridx = 1;
		gbc_btnReciente.gridy = 3;
		panelFuncionalidad.add(btnReciente, gbc_btnReciente);
		
		JButton btnMisPlaylists = new JButton("Mis Playlists");
		btnMisPlaylists.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisPlaylists.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/imagenes/playlist_121110.png")));
		GridBagConstraints gbc_btnMisPlaylists = new GridBagConstraints();
		gbc_btnMisPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMisPlaylists.gridx = 1;
		gbc_btnMisPlaylists.gridy = 4;
		panelFuncionalidad.add(btnMisPlaylists, gbc_btnMisPlaylists);
	}

}
