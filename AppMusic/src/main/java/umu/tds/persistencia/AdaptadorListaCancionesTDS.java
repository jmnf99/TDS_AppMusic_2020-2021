package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.ListaCanciones;

public class AdaptadorListaCancionesTDS implements IAdaptadorListaCancionesDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaCancionesTDS unicaInstancia = null;

	public static AdaptadorListaCancionesTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorListaCancionesTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorListaCancionesTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// cuando se registra una lista canciones se le asigna un identificador único
	public void registrarListaCanciones(ListaCanciones listaCanciones) {
		Entidad eListaCanciones;
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		try {
			eListaCanciones = servPersistencia.recuperarEntidad(listaCanciones.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		// Se registran sus objetos agregados
		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion c : listaCanciones.getCanciones())
			adaptadorCancion.registrarCancion(c);

		// la lista ya existe
		// Se crea la entidad eListaCanciones
		eListaCanciones = new Entidad();
		eListaCanciones.setNombre("listaCanciones");

		// Se le anaden las propiedades a la entidad creada
		eListaCanciones.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombrePlaylist", listaCanciones.getNombrePlaylist()),
						new Propiedad("canciones", obtenerCodigosCanciones(listaCanciones.getCanciones())))));

		// Se registra la entidad eListaCanciones asignandole un identificador unico
		eListaCanciones = servPersistencia.registrarEntidad(eListaCanciones);
		listaCanciones.setCodigo(eListaCanciones.getId());
	}

	public ListaCanciones recuperarListaCanciones(int codigo) {
		Entidad eListaCanciones;
		String nombrePlaylist;
		List<Cancion> canciones;

		// recuperar entidad
		eListaCanciones = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nombrePlaylist = servPersistencia.recuperarPropiedadEntidad(eListaCanciones, "nombrePlaylist");

		// creamos el objeto a recuperar
		ListaCanciones listaCanciones = new ListaCanciones(nombrePlaylist);
		listaCanciones.setCodigo(codigo);

		// recuperar propiedades que son objetos llamando a adaptadores
		canciones = obtenerCancionesDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eListaCanciones, "canciones"));
		for (Cancion c : canciones) {
			listaCanciones.addCancion(c);
		}
		return listaCanciones;
	}

	public ListaCanciones recuperarListaCanciones(String nombre) {
		List<ListaCanciones> listas = recuperarTodasListasCanciones();
		for (ListaCanciones l : listas) {
			if (l.getNombrePlaylist().equals(nombre)) {
				return l;
			}
		}
		return null;
	}

	public List<ListaCanciones> recuperarTodasListasCanciones() {
		List<ListaCanciones> listaCanciones = new LinkedList<ListaCanciones>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("listas");
		for (Entidad eCancion : entidades) {
			listaCanciones.add(recuperarListaCanciones(eCancion.getId()));
		}
		return listaCanciones;
	}

	public void borrarListaCanciones(ListaCanciones listaCanciones) {
		Entidad eListaCanciones = servPersistencia.recuperarEntidad(listaCanciones.getCodigo());
		servPersistencia.borrarEntidad(eListaCanciones);
	}

	// -------------------Funciones auxiliares-----------------------------
	// Obtener ids a partir de listas de objetos al registrar
	private String obtenerCodigosCanciones(List<Cancion> canciones) {
		String aux = "";
		for (Cancion cancion : canciones)
			aux += cancion.getCodigo() + " ";
		return aux.trim();
	}

	// Obtener una lista de objetos a partir de un string con una lista de ids al
	// recuperar
	private List<Cancion> obtenerCancionesDesdeCodigos(String canciones) {
		List<Cancion> aux = new LinkedList<Cancion>();
		StringTokenizer tokens = new StringTokenizer(canciones, " ");
		AdaptadorCancionTDS adaptadorCancionTDS = AdaptadorCancionTDS.getUnicaInstancia();
		while (tokens.hasMoreTokens()) {
			aux.add(adaptadorCancionTDS.recuperarCancion(Integer.valueOf((String) tokens.nextToken())));
		}
		return aux;
	}
}
