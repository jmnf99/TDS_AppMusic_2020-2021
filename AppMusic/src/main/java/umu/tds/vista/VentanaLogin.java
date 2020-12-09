package umu.tds.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.tds.controlador.AppMusic;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class VentanaLogin {

	private JFrame frmAppmusic;
	private JTextField textUsuario;
	private JPasswordField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void mostrarVentana() {
		frmAppmusic.setLocationRelativeTo(null);
		frmAppmusic.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		frmAppmusic = new JFrame();
		frmAppmusic.setTitle(Constantes.titulo);
		frmAppmusic.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource(Constantes.icono)));
		frmAppmusic.setBounds(100, 100,Constantes.x_size,Constantes.y_size);
		frmAppmusic.setResizable(false);
		frmAppmusic.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAppmusic.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelFormulario = new JPanel();
		panelFormulario.setPreferredSize(new Dimension(Constantes.x_size, Constantes.y_size - Constantes.barra));
		frmAppmusic.getContentPane().add(panelFormulario);
		GridBagLayout gbl_panelFormulario = new GridBagLayout();
		gbl_panelFormulario.columnWidths = new int[] { 10, 10, 0, 301, 10, 10, 0 };
		gbl_panelFormulario.rowHeights = new int[] { 0, 0, 20, 10, 20, 0, 0, 0, 0, 0 };
		gbl_panelFormulario.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelFormulario.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelFormulario.setLayout(gbl_panelFormulario);

		JLabel lblTitulo = new JLabel("Bienvenido a AppMusic");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 3;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 2;
		gbc_lblTitulo.gridy = 1;
		panelFormulario.add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblAppMusicLogo = new JLabel("");
		lblAppMusicLogo.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/imagenes/AppMusic(1).png")));
		GridBagConstraints gbc_lblAppMusicLogo = new GridBagConstraints();
		gbc_lblAppMusicLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppMusicLogo.gridx = 3;
		gbc_lblAppMusicLogo.gridy = 3;
		panelFormulario.add(lblAppMusicLogo, gbc_lblAppMusicLogo);
		
				JLabel lblUsuario = new JLabel("Usuario");
				GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
				gbc_lblUsuario.anchor = GridBagConstraints.EAST;
				gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
				gbc_lblUsuario.gridx = 1;
				gbc_lblUsuario.gridy = 5;
				panelFormulario.add(lblUsuario, gbc_lblUsuario);

		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.gridwidth = 3;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 5;
		panelFormulario.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(10);
		
				JLabel lblClave = new JLabel("Clave");
				GridBagConstraints gbc_lblClave = new GridBagConstraints();
				gbc_lblClave.anchor = GridBagConstraints.EAST;
				gbc_lblClave.insets = new Insets(0, 0, 5, 5);
				gbc_lblClave.gridx = 1;
				gbc_lblClave.gridy = 6;
				panelFormulario.add(lblClave, gbc_lblClave);

		textPassword = new JPasswordField();
		textPassword.setColumns(10);
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.gridwidth = 3;
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPassword.gridx = 2;
		gbc_textPassword.gridy = 6;
		panelFormulario.add(textPassword, gbc_textPassword);

		JButton btnRegistro = new JButton("Registro");
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaRegistro vRegistro = new VentanaRegistro();
				vRegistro.mostrarVentana();
				frmAppmusic.dispose();
			}
		});
		
				final JButton btnLogin = new JButton("Login");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// leer datos de la pantalla
						String usuario = textUsuario.getText();
						String clave = new String(textPassword.getPassword()); // devuelve un array de char, por eso el new

						// con los datos, invocar a los metodos del controlador: invocar logica de
						// negocio
						boolean logueado = AppMusic.getInstancia().login(usuario, clave);

						// si el controlador retorna datos, presentarlos en pantalla
						if (!logueado) {
							JOptionPane.showMessageDialog(btnLogin, "Usuario '" + usuario + "' o contraseña incorrectos",
									"Fallo login", JOptionPane.ERROR_MESSAGE, null);

						} else {
							// AppMusic.getInstancia().setUsuarioActual(usuarioActual);
							// DONE crear ventanaMain y hacerla visible (ocultar la de login)
							VentanaPrincipal vPrincipal = new VentanaPrincipal();
							vPrincipal.mostrarVentana();
							frmAppmusic.dispose();
						}
					}
				});
				GridBagConstraints gbc_btnLogin = new GridBagConstraints();
				gbc_btnLogin.anchor = GridBagConstraints.WEST;
				gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
				gbc_btnLogin.gridx = 2;
				gbc_btnLogin.gridy = 7;
				panelFormulario.add(btnLogin, gbc_btnLogin);
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.anchor = GridBagConstraints.EAST;
		gbc_btnRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistro.gridx = 4;
		gbc_btnRegistro.gridy = 7;
		panelFormulario.add(btnRegistro, gbc_btnRegistro);
	}

}
