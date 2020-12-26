package umu.tds.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class VentanaPagoPremium extends JDialog {

	private JFrame frame;
	
	/**
	 * Create the application.
	 */
	public VentanaPagoPremium(JFrame parent, boolean modal) {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
