package umu.tds.controlador;

import java.time.LocalDate;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import umu.tds.componente.CancionXML;
import umu.tds.componente.Canciones;
import umu.tds.componente.CancionesEvent;
import umu.tds.componente.CancionesListener;
import umu.tds.componente.CargadorCanciones;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CargadorCancionesDisco;
import umu.tds.modelo.CatalogoEstilos;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Descuento;
import umu.tds.modelo.Filtros;
import umu.tds.modelo.GeneradorPdfs;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Reproductor;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorListaCancionesDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

public class AppMusic implements CancionesListener {
	private final double precioPremium = 10;
	private Usuario usuarioActual;
	private ListaCanciones listaActual;
	private static AppMusic unicaInstancia = null;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoEstilos catalogoEstilos;
	private IAdaptadorListaCancionesDAO adaptadorListaCanciones;
	private CargadorCancionesDisco cargadorCancionesDisco = CargadorCancionesDisco.getInstancia();
	private Queue<Cancion> recientes;
	private Reproductor reproductor = Reproductor.getUnicaInstancia();
	private CargadorCanciones cargadorXML = CargadorCanciones.getUnicaInstancia();

	private AppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();

//		cargadorCancionesDisco.cargarEstilosMusicales();
//		cargadorCancionesDisco.comprobarEstilosMusicales();
		cargadorCancionesDisco.cargarCanciones();
		recientes = new LinkedList<>();
		cargadorXML.addCancionesListener(this);
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
		// Comprobar que el nombre del usuario no existe ya en la aplicación
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

	public void modificarListaCanciones(List<Cancion> lista) {

		listaActual.eliminarCanciones();

		for (Cancion cancion : lista) {
			listaActual.addCancion(cancion);
		}

		adaptadorListaCanciones.modificarListaCanciones(this.listaActual);
		adaptadorUsuario.modificarListas(usuarioActual);
	}

	public void confirmarListaCanciones(List<Cancion> lista) {
		for (Cancion cancion : lista) {
			listaActual.addCancion(cancion);
		}
		usuarioActual.addListaCanciones(listaActual);
		adaptadorListaCanciones.registrarListaCanciones(listaActual);
		adaptadorUsuario.modificarListas(usuarioActual);
	}
	
	public void eliminarCacheCanciones() {
		reproductor.eliminarCache();
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
		if (recientes.size() == 10) {
			recientes.poll();
		}
		recientes.add(c);
		c.escuchada();
	}

	public List<Cancion> getCancionesRecientes() {
		return new LinkedList<>(recientes);
	}

	public void pausarCancion() {
		reproductor.pausarCancion();
	}

	public String[] getNombresEstilos() {
		return catalogoEstilos.getNombreEstilos();
	}

	public void modificarUsuarioPremium() {
		adaptadorUsuario.modificarPremium(this.usuarioActual);
	}

	public void modificarUsuarioListas() {
		adaptadorUsuario.modificarListas(this.usuarioActual);
	}

	public void eliminarUsuarioLista() {
		this.usuarioActual.eliminarLista(listaActual);
		adaptadorUsuario.eliminarLista(usuarioActual, listaActual);
		listaActual = null;
	}

	public boolean existePlaylistUsuario(String nombre) {
		return usuarioActual.existePlaylist(nombre);
	}

	public String getNombreListaActual() {
		return this.listaActual.getNombrePlaylist();
	}

	public void setListaActual(String nombre) {
		listaActual = usuarioActual.getLista(nombre);
	}
	
	public List<Cancion> getCancionesListaActual(){
		return listaActual.getCanciones();
	}
	
	public List<Cancion> getCancionesFiltro(String nombre, String interprete, String estilo){
		if(nombre.equals("Título")) nombre = "";
		if(interprete.equals("Intérprete")) interprete = "";
		if(estilo.equals("Todos")) estilo = "";
		return Filtros.getCancionesFiltro(nombre, interprete, estilo);
	}
	
	public List<Cancion> getCancionesMasReproducidas(){
		return Filtros.getCancionesMasEscuchadas();
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorListaCanciones = factoria.getListaCancionesDAO();
		System.out.println();
	}

	private void inicializarCatalogos() {
		catalogoEstilos = CatalogoEstilos.getUnicaInstancia();
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}

	public void generarPdf() {
		GeneradorPdfs.generarPdf(this.usuarioActual.getListas());
	}

	public void cargarCanciones(String xml) {
		cargadorXML.setArchivoCanciones(xml);
	}

	@Override
	public void enteradoNuevasCanciones(EventObject evento) {
		if(evento instanceof CancionesEvent) {
			Canciones nuevasCanciones = ((CancionesEvent) evento).getNuevasCanciones();
			for (CancionXML cxml : nuevasCanciones.getCancion()) {
				cargadorCancionesDisco.cargarCancionesXML(cxml.getTitulo(), cxml.getInterprete(), cxml.getURL(), cxml.getEstilo());
			}		
		}
	}
}
