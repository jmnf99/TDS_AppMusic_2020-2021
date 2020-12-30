package umu.tds.controlador;

import java.time.LocalDate;

import umu.tds.modelo.Cancion;
import umu.tds.modelo.CargadorCancionesDisco;
import umu.tds.modelo.CatalogoEstilos;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Descuento;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Reproductor;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.AdaptadorUsuarioTDS;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

public class AppMusic {
	private final double precioPremium = 10;
	private Usuario usuarioActual;
	private ListaCanciones listaActual;
	private static AppMusic unicaInstancia = null;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoEstilos catalogoEstilos;
	private CargadorCancionesDisco cargadorCancionesDisco = CargadorCancionesDisco.getInstancia();
	Reproductor reproductor = Reproductor.getUnicaInstancia();

	private AppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
		cargadorCancionesDisco.cargarEstilosMusicales();
//		cargadorCancionesDisco.comprobarEstilosMusicales();
		cargadorCancionesDisco.cargarCanciones();
	}

	public static AppMusic getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

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

	public void logout() {
		setUsuarioActual(null);
	}

	public Usuario registrarUsuario(String usuario, String clave, String nombre, String apellidos, String mail,
			LocalDate fechaNacim) {
		// Comprobar que el nombre del usuario no existe ya en la aplicacion
		if (catalogoUsuarios.getUsuario(usuario) != null)
			return null;
		// Creamos al usuario
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

	public void crearListaCanciones(String nombrePlaylist) {
		listaActual = new ListaCanciones(nombrePlaylist);
	}

	public void seleccionarDescuento(LocalDate now) {
		if ((now.getMonthValue() == 1 && now.getDayOfMonth() <= 6)
				|| (now.getMonthValue() == 12 && now.getDayOfMonth() >= 25)) {
			this.usuarioActual.setDescuento(Descuento.NAVIDAD);
		} else if (this.usuarioActual.isEstudianteUMU()) {
			this.usuarioActual.setDescuento(Descuento.ESTUDIANTE);
		} else if (this.usuarioActual.isMayor(now.getYear())) {
			this.usuarioActual.setDescuento(Descuento.MAYORES);
		}
	}

	public double calcularDescuento() {
		return usuarioActual.getDescuento().calcDescuento(this.precioPremium);
	}
	
	public void reproducirCancion(Cancion c) {
		reproductor.reproducirCancion(c.getRutaFichero());
	}
	
	public void pausarCancion() {
		reproductor.pausarCancion();
	}
	
	public String[] getNombresEstilos() {
		return catalogoEstilos.getNombreEstilos();
	}

	public void modificarUsuario() {
		AdaptadorUsuarioTDS.getUnicaInstancia().modificarUsuario(this.usuarioActual);

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
		catalogoEstilos = CatalogoEstilos.getUnicaInstancia();
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
}
