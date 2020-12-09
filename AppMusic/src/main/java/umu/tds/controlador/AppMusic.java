package umu.tds.controlador;

import java.io.File;
import java.time.LocalDate;

import com.jtattoo.plaf.aluminium.AluminiumSplitPaneDivider;

import umu.tds.modelo.CatalogoEstilos;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorEstiloMusicalDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

public class AppMusic {
	private final double precioPremium = 10;
	private static AppMusic unicaInstancia = null;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorEstiloMusicalDAO adaptadorEstilo;
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoEstilos catalogoEstilos;

	private AppMusic() {
		//inicializarAdaptadores();
		//inicializarCatalogos();
		obtenerEstilosMusicales();
	}
	
	public static void main(String[] args) {
		AppMusic app = AppMusic.getInstancia();
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
		adaptadorEstilo = factoria.getEstiloDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoEstilos = CatalogoEstilos.getUnicaInstancia();
	}
	
	//TODO terminad esto
	private void obtenerEstilosMusicales() {
		File carpeta = new File("./src/main/java/umu.tds.catalogo.canciones");
		String[] listado = carpeta.list();
	    for (int i=0; i< listado.length; i++) {
	        System.out.println(listado[i].substring(0, 1) + listado[i].substring(1).toLowerCase());
	    }
				
	}
}
