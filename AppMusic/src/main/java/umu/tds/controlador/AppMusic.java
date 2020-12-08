package umu.tds.controlador;

import java.time.LocalDate;

import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

public class AppMusic {
	private final double precioPremium = 10;
	private static AppMusic unicaInstancia = null;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private CatalogoUsuarios catalogoUsuarios;

	private AppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
	}

	public static AppMusic getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

	private Usuario usuarioActual;

	public boolean login(String usuario, String clave) {
		// Comprobamos que el usuario exite en Catalogo
		Usuario oUsuario = catalogoUsuarios.getUsuario(usuario);
		if (oUsuario == null)
			return false;
		// Comprobamos que su clave es la correcta
		if (oUsuario.login(clave)) {
			setUsuarioActual(oUsuario);
			return true;
		}
		return false;
	}

	public Usuario registrarUsuario(String usuario, String clave, String nombre, String apellidos, String mail,
			LocalDate fechaNacim) {
		// Comprobar que el nombre del usuario no existe ya en la aplicacion
		if (catalogoUsuarios.getUsuario(usuario) != null)
			return null;
		// Creamos al ususario
		Usuario usu = new Usuario(usuario, clave, mail, nombre, apellidos, fechaNacim);
		// Lo registramos en BBDD
		adaptadorUsuario.registrarUsuario(usu);
		// Lo registramos tambien CatalogoUsuarios
		catalogoUsuarios.addUsuario(usu);
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

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
}
