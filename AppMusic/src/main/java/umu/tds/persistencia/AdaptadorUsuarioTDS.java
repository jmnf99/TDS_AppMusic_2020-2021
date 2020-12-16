package umu.tds.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;

	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// cuando se registra un usuario se le asigna un identificador único
	public Usuario registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return usuario;

		// Se registran sus objetos agregados
		AdaptadorListaCancionesTDS adaptadorListaCanciones = AdaptadorListaCancionesTDS.getUnicaInstancia();
		for (ListaCanciones l : usuario.getListas())
			adaptadorListaCanciones.registrarListaCanciones(l);

		// el usuario ya existe
		// Se crea la entidad eUsuario
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");

		// Se le anaden las propiedades a la entidad creada
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nUsuario", usuario.getNusuario()),
				new Propiedad("clave", usuario.getClave()), new Propiedad("mail", usuario.getMail()),
				new Propiedad("nombre", usuario.getNombre()), new Propiedad("apellidos", usuario.getApellidos()),
				new Propiedad("fechaNacim", usuario.getFechaNacim().toString()),
				new Propiedad("premium", Boolean.toString(usuario.isPremium())),
				new Propiedad("listas", obtenerCodigosListasCanciones(usuario.getListas())))));

		// Se registra la entidad eUsuario asignandole un identificador unico
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setCodigo(eUsuario.getId());
		return usuario;
	}

	public Usuario recuperarUsuario(int codigo) {
		Entidad eUsuario;
		String nUsuario, clave, mail, nombre, apellidos;
		LocalDate fechaNacim;
		List<ListaCanciones> listas = new LinkedList<ListaCanciones>();
		boolean premium;

		// recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nUsuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nUsuario");
		clave = servPersistencia.recuperarPropiedadEntidad(eUsuario, "clave");
		mail = servPersistencia.recuperarPropiedadEntidad(eUsuario, "mail");
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		fechaNacim = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacim"));
		premium = Boolean.parseBoolean(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));

		// creamos el objeto a recuperar
		Usuario usuario = new Usuario(nUsuario, clave, mail, nombre, apellidos, fechaNacim);
		usuario.setPremium(premium);
		usuario.setCodigo(codigo);

		// recuperar propiedades que son objetos llamando a adaptadores
		listas = obtenerListasCancionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "listas"));
		for (ListaCanciones l : listas) {
			usuario.addListaCanciones(l);
		}
		return usuario;
	}

	public List<Usuario> recuperarTodosUsuarios() {
		List<Usuario> usuarios = new LinkedList<Usuario>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("usuario");
		for (Entidad eUsuario : entidades) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}

	// -------------------Funciones auxiliares-----------------------------

	// Obtener ids a partir de una lista de objetos al registrar
	private String obtenerCodigosListasCanciones(List<ListaCanciones> listas) {
		String aux = "";
		for (ListaCanciones lista : listas)
			aux += lista.getCodigo() + " ";
		return aux.trim();
	}

	// Obtener una lista de objetos a partir de un string con una lista de ids al
	// recuperar

	private List<ListaCanciones> obtenerListasCancionesDesdeCodigos(String listas) {
		List<ListaCanciones> aux = new LinkedList<ListaCanciones>();
		StringTokenizer tokens = new StringTokenizer(listas, " ");
		AdaptadorListaCancionesTDS adaptadorListaCanciones = AdaptadorListaCancionesTDS.getUnicaInstancia();
		while (tokens.hasMoreTokens()) {
			aux.add(adaptadorListaCanciones.recuperarListaCanciones(Integer.valueOf((String) tokens.nextElement())));
		}
		return aux;
	}

}
