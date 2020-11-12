package umu.tds.controlador;

import java.time.LocalDate;

import umu.tds.modelo.Usuario;

public class AppMusic {
	private final double precioPremium  = 10;
	private static AppMusic unicaInstancia = null;
	private AppMusic() {}
	
	public static AppMusic getInstancia() {
		if(unicaInstancia == null) unicaInstancia = new AppMusic();
		return unicaInstancia;
	}
	
	private Usuario usuarioActual;
	
	public boolean login(String usuario, String clave) {
		//if usuario exite en Catalogo o BBDD
		// 	if clave es igual a clave de usuario: usuario.login(clave)
		//		return true;
		//return false;
		return false;

	}

	public Usuario registrarUsuario(String usuario, String clave, String nombre, String apellidos, String mail,
			LocalDate fechaNacim) {
		Usuario usu = new Usuario(usuario, clave, mail, nombre, apellidos, fechaNacim);
		
		//registrar usuario en BBDD
		//meter usuario registrado en CatalogoUsuarios
		return usu;

	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public double getPrecioPremium() {
		return precioPremium;
	}
}
