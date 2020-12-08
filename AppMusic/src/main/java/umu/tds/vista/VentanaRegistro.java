package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.AppMusic;
import umu.tds.modelo.Usuario;

import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaRegistro {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textUsuario;
	private JPasswordField textPassword;
	private JPasswordField textPassword2;
	private JTextField textMail;
	private JDateChooser textFecha;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JLabel lblRepetirClave;
	private JLabel lblMail;
	private JLabel lblFecha;
	private JButton btnCancelar;
	private JLabel lblApellidos;
	private JLabel lblNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//VentanaRegistro frame = new VentanaRegistro();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostrarVentana() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 * @wbp.parser.entryPoint
	 */
	public VentanaRegistro() {
		
		frame = new JFrame();
			
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100,Constantes.x_size,Constantes.y_size);
		frame.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Registro", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 20, 0 };
		gbl_panel.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		panel.add(lblNombre, gbc_lblNombre);

		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 3;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 1;
		panel.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 2;
		panel.add(lblApellidos, gbc_lblApellidos);

		textApellidos = new JTextField();
		GridBagConstraints gbc_textApellidos = new GridBagConstraints();
		gbc_textApellidos.gridwidth = 3;
		gbc_textApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellidos.gridx = 2;
		gbc_textApellidos.gridy = 2;
		panel.add(textApellidos, gbc_textApellidos);
		textApellidos.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 3;
		panel.add(lblUsuario, gbc_lblUsuario);

		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 3;
		panel.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(10);

		lblClave = new JLabel("Clave");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.anchor = GridBagConstraints.EAST;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 4;
		panel.add(lblClave, gbc_lblClave);

		textPassword = new JPasswordField();
		textPassword.setColumns(10);
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.gridx = 2;
		gbc_textPassword.gridy = 4;
		panel.add(textPassword, gbc_textPassword);

		lblRepetirClave = new JLabel("Repetir");
		GridBagConstraints gbc_lblRepetirClave = new GridBagConstraints();
		gbc_lblRepetirClave.anchor = GridBagConstraints.EAST;
		gbc_lblRepetirClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepetirClave.gridx = 3;
		gbc_lblRepetirClave.gridy = 4;
		panel.add(lblRepetirClave, gbc_lblRepetirClave);

		textPassword2 = new JPasswordField();
		GridBagConstraints gbc_textPassword2 = new GridBagConstraints();
		gbc_textPassword2.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPassword2.gridx = 4;
		gbc_textPassword2.gridy = 4;
		panel.add(textPassword2, gbc_textPassword2);
		textPassword2.setColumns(10);

		lblMail = new JLabel("Mail");
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.EAST;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 1;
		gbc_lblMail.gridy = 5;
		panel.add(lblMail, gbc_lblMail);

		textMail = new JTextField();
		GridBagConstraints gbc_textMail = new GridBagConstraints();
		gbc_textMail.gridwidth = 3;
		gbc_textMail.insets = new Insets(0, 0, 5, 5);
		gbc_textMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMail.gridx = 2;
		gbc_textMail.gridy = 5;
		panel.add(textMail, gbc_textMail);
		textMail.setColumns(10);

		lblFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 1;
		gbc_lblFecha.gridy = 6;
		panel.add(lblFecha, gbc_lblFecha);
		
		textFecha = new JDateChooser();
		GridBagConstraints gbc_textFecha = new GridBagConstraints();
		gbc_textFecha.insets = new Insets(0, 0, 5, 5);
		gbc_textFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFecha.gridx = 2;
		gbc_textFecha.gridy = 6;
		panel.add(textFecha, gbc_textFecha);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VentanaLogin login = new VentanaLogin();
				login.mostrarVentana();
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 8;
		panel.add(btnCancelar, gbc_btnCancelar);

		final JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//obtener los campos con calendar.get(mes, dia, año)
				Calendar calendario = textFecha.getCalendar();
				int dia = calendario.get(Calendar.DAY_OF_MONTH);
				int mes = calendario.get(Calendar.MONTH);
				int anyo = calendario.get(Calendar.YEAR);
				LocalDate fechaNacim = LocalDate.of(anyo, mes, dia);

				if (!fieldsOK()) {
					JOptionPane.showMessageDialog(btnRegistrar, "Error de datos de registro", "Fallo Registro",
							JOptionPane.ERROR_MESSAGE, null);
				} else {
					// Registramos al usuario
					Usuario usu = AppMusic.getInstancia().registrarUsuario(textUsuario.getText(),
							new String(textPassword.getPassword()), textNombre.getText(), textApellidos.getText(),
							textMail.getText(), fechaNacim);
					if (usu == null) {
						JOptionPane.showMessageDialog(btnRegistrar, "Error, el nombre de usuario no esta disponible",
								"Fallo Registro", JOptionPane.ERROR_MESSAGE, null);
					} else {
						AppMusic.getInstancia().setUsuarioActual(usu);
					}
				}
			}
		});
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.anchor = GridBagConstraints.EAST;
		gbc_btnRegistrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegistrar.gridx = 4;
		gbc_btnRegistrar.gridy = 8;
		panel.add(btnRegistrar, gbc_btnRegistrar);
	}
	
	private boolean fieldsOK() {
		boolean ok = true;
		ocultarErrores();
		if(textNombre.getText().trim().isEmpty()) {
			lblNombre.setForeground(Color.RED);
			textNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok=false;
		}
		if(textApellidos.getText().trim().isEmpty()) {
			lblApellidos.setForeground(Color.RED);
			textApellidos.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok=false;
		}
		if(textMail.getText().trim().isEmpty()) {
			lblMail.setForeground(Color.RED);
			textMail.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok=false;
		}
		
		//añadir comprobacion de si ya esta registrado el nombre
		if(textUsuario.getText().trim().isEmpty()) {
			lblUsuario.setForeground(Color.RED);
			textUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok=false;
		}
		
		String passwd = new String(textPassword.getPassword());
		String passwd2 = new String(textPassword2.getPassword());
		
		if(passwd.isEmpty()) {
			lblClave.setForeground(Color.RED);
			textPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok=false;
		}
		
		if(passwd2.isEmpty()) {
			lblRepetirClave.setForeground(Color.RED);
			textPassword2.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok=false;
		}
		
		if(!passwd.equals(passwd2)) {
			lblClave.setForeground(Color.RED);
			lblRepetirClave.setForeground(Color.RED);
			textPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
			textPassword2.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok=false;
		}
		
		if(textFecha.getDate() == null) {
			textFecha.setForeground(Color.RED);
			textFecha.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok=false;
		}
		
		return ok;
	}
	
	private void ocultarErrores() {
		Border border = new JTextField().getBorder();
		textNombre.setBorder(border);
		textApellidos.setBorder(border);
		textUsuario.setBorder(border);
		textMail.setBorder(border);
		textPassword.setBorder(border);
		textPassword2.setBorder(border);
		textFecha.setBorder(border);
		
		textNombre.setForeground(Color.BLACK);
		textApellidos.setForeground(Color.BLACK);
		textUsuario.setForeground(Color.BLACK);
		textMail.setForeground(Color.BLACK);
		textPassword.setForeground(Color.BLACK);
		textPassword2.setForeground(Color.BLACK);
		textFecha.setForeground(Color.BLACK);
	}

}
