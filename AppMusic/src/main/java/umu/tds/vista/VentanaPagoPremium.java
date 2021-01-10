package umu.tds.vista;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;

import umu.tds.controlador.AppMusic;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPagoPremium extends JDialog {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 */
	public VentanaPagoPremium(JFrame parent, boolean modal) {
		super(parent, "Metodo de pago", modal);
		setResizable(false);

		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(new Color(230, 230, 250));
		panelInfo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panelInfo, BorderLayout.NORTH);
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[] { 40, 40, 40, 40, 0 };
		gbl_panelInfo.rowHeights = new int[] { 13, 0, 0, 0 };
		gbl_panelInfo.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInfo.setLayout(gbl_panelInfo);

		JLabel lblTitulo = new JLabel("¿Por qué pasarte a Premium?");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.gridwidth = 4;
		gbc_lblTitulo.anchor = GridBagConstraints.NORTH;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		panelInfo.add(lblTitulo, gbc_lblTitulo);

		JLabel lblVentaja1 = new JLabel("Genera PDFs de tus playlists");
		lblVentaja1.setIcon(new ImageIcon(VentanaPagoPremium.class.getResource("/umu/tds/imagenes/pdf-16.png")));
		lblVentaja1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GridBagConstraints gbc_lblVentaja1 = new GridBagConstraints();
		gbc_lblVentaja1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblVentaja1.insets = new Insets(0, 0, 5, 5);
		gbc_lblVentaja1.gridx = 0;
		gbc_lblVentaja1.gridy = 1;
		panelInfo.add(lblVentaja1, gbc_lblVentaja1);

		JLabel lblVentaja2 = new JLabel("Reproduce las 10 canciones más escuchadas de AppMusic");
		lblVentaja2
				.setIcon(new ImageIcon(VentanaPagoPremium.class.getResource("/umu/tds/imagenes/temporizador (1).png")));
		lblVentaja2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GridBagConstraints gbc_lblVentaja2 = new GridBagConstraints();
		gbc_lblVentaja2.insets = new Insets(0, 0, 0, 5);
		gbc_lblVentaja2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblVentaja2.gridwidth = 3;
		gbc_lblVentaja2.gridx = 0;
		gbc_lblVentaja2.gridy = 2;
		panelInfo.add(lblVentaja2, gbc_lblVentaja2);

		JPanel panelDescuentos = new JPanel();
		getContentPane().add(panelDescuentos, BorderLayout.CENTER);
		GridBagLayout gbl_panelDescuentos = new GridBagLayout();
		gbl_panelDescuentos.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelDescuentos.rowHeights = new int[] { 30, 0, 0, 0, 0, 0 };
		gbl_panelDescuentos.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelDescuentos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelDescuentos.setLayout(gbl_panelDescuentos);

		JLabel lblPrecioBase = new JLabel("Precio base: " + AppMusic.getInstancia().getPrecioPremium() + " €");
		GridBagConstraints gbc_lblPrecioBase = new GridBagConstraints();
		gbc_lblPrecioBase.anchor = GridBagConstraints.WEST;
		gbc_lblPrecioBase.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecioBase.gridx = 1;
		gbc_lblPrecioBase.gridy = 1;
		panelDescuentos.add(lblPrecioBase, gbc_lblPrecioBase);

		JLabel lblDescuentos = new JLabel(
				"Descuento aplicable: " + AppMusic.getInstancia().getUsuarioActual().getTipoDescuento());
		GridBagConstraints gbc_lblDescuentos = new GridBagConstraints();
		gbc_lblDescuentos.anchor = GridBagConstraints.WEST;
		gbc_lblDescuentos.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuentos.gridx = 1;
		gbc_lblDescuentos.gridy = 2;
		panelDescuentos.add(lblDescuentos, gbc_lblDescuentos);

		JLabel lblPrecioFinal = new JLabel("Precio final: " + AppMusic.getInstancia().calcularDescuentoUsuario() + " €");
		GridBagConstraints gbc_lblPrecioFinal = new GridBagConstraints();
		gbc_lblPrecioFinal.anchor = GridBagConstraints.WEST;
		gbc_lblPrecioFinal.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecioFinal.gridx = 1;
		gbc_lblPrecioFinal.gridy = 3;
		panelDescuentos.add(lblPrecioFinal, gbc_lblPrecioFinal);

		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ya es premium el usuario
				AppMusic.getInstancia().getUsuarioActual().setPremium(true);
				// Se actualiza en la BBDD
				AppMusic.getInstancia().modificarUsuarioPremium();
				dispose();
			}
		});
		GridBagConstraints gbc_btnPagar = new GridBagConstraints();
		gbc_btnPagar.insets = new Insets(0, 0, 0, 5);
		gbc_btnPagar.gridx = 1;
		gbc_btnPagar.gridy = 4;
		panelDescuentos.add(btnPagar, gbc_btnPagar);
		initialize();
	}

	public void mostrarVentana() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
