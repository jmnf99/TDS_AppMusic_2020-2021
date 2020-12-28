package umu.tds.modelo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import umu.tds.persistencia.IAdaptadorEstiloMusicalDAO;
import umu.tds.persistencia.IAdaptadorInterpreteDAO;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorCancionDAO;

public class CargadorCancionesDisco {
	private final static String rutaCancionesDisco = "./src/main/java/umu.tds.catalogo.canciones";

	private CatalogoEstilos catalogoEstilos;
	private CatalogoCanciones catalogoCanciones;
	private CatalogoInterpretes catalogoInterpretes;
	private IAdaptadorEstiloMusicalDAO adaptadorEstilo;
	private IAdaptadorCancionDAO adaptadorCanciones;
	private IAdaptadorInterpreteDAO adaptadorInterpretes;
	private static CargadorCancionesDisco unicaInstancia = null;

	private CargadorCancionesDisco() {
		inicializarAdaptadores();
		inicializarCatalogos();
	}

	public static CargadorCancionesDisco getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new CargadorCancionesDisco();
		return unicaInstancia;
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorEstilo = factoria.getEstiloDAO();
		adaptadorCanciones = factoria.getCancionDAO();
		adaptadorInterpretes = factoria.getInterpreteDAO();
	}

	private void inicializarCatalogos() {
		catalogoEstilos = CatalogoEstilos.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
		catalogoInterpretes = CatalogoInterpretes.getUnicaInstancia();
	}

	private String[] obtenerEstilosMusicalesDisco() {
		File carpeta = new File(rutaCancionesDisco);
		return carpeta.list();
	}

//	public void comprobarEstilosMusicales() {
//		String[] listado = obtenerEstilosMusicalesDisco();
//
//		List<EstiloMusical> lista = adaptadorEstilo.recuperarTodosEstilosMusicales();
//		for (EstiloMusical e : lista) {
//			boolean existe = false;
//			for (int i = 0; i < listado.length; i++) {
//				if (e.getNombre().equalsIgnoreCase(listado[i])) {
//					existe = true;
//					break;
//				}
//			}
//			if (!existe) {
//				catalogoEstilos.removeEstilo(e.getCodigo());
//			}
//		}
//	}

	public void cargarEstilosMusicales() {

		String[] listado = obtenerEstilosMusicalesDisco();

		for (int i = 0; i < listado.length; i++) {
			if (!catalogoEstilos.existeEstilo(listado[i])) {
				EstiloMusical estilo = adaptadorEstilo.registrarEstiloMusical(
						new EstiloMusical(listado[i].substring(0, 1) + listado[i].substring(1).toLowerCase()));
				catalogoEstilos.addEstilo(estilo);
			}
		}
	}

	public void cargarCanciones() {
		String[] listado = obtenerEstilosMusicalesDisco();

		for (int i = 0; i < listado.length; i++) {
			// Para cada estilo
			String estiloActual = listado[i];
			File archivos = new File(rutaCancionesDisco + "/" + estiloActual);

			String[] canciones = archivos.list();
			for (int j = 0; j < canciones.length; j++) {
				// System.out.println(canciones[j]);
				guardarCancion(canciones[j], rutaCancionesDisco + "/" + estiloActual + "/" + canciones[j],
						estiloActual);
			}
		}

		List<Cancion> canciones = catalogoCanciones.getCanciones();

		int i = 0;
		for (Cancion cancion : canciones) {
			System.out.println(cancion.getCodigo());

			System.out.println(cancion.getEstilo().getNombre());

			System.out.println(cancion.getTitulo());

			System.out.println(cancion.getInterpretes());

			System.out.println(cancion.getRutaFichero());

			System.out.println();
			i++;
		}
		System.out.println(i);

	}

	private void guardarCancion(String cancion, String ruta, String estilo) {

		String[] campos = cancion.split("-");

		List<Interprete> lista = this.cargarInterpretes(campos[0]);

		String titulo = parsearTitulo(campos[1]);

		EstiloMusical estiloMusical = catalogoEstilos.getEstiloMusical(estilo);

		Cancion cancionActual = new Cancion(titulo, estiloMusical, ruta, lista);
		if (!catalogoCanciones.existeCancion(ruta)) {
			cancionActual = adaptadorCanciones.registrarCancion(cancionActual);
			catalogoCanciones.addCancion(cancionActual);
		}

//		System.out.println(estilo);
//
//		System.out.println(titulo);
//
//		System.out.println(campos[0]);
//
//		System.out.println(ruta);
//
//		System.out.println();
	}

	private List<Interprete> cargarInterpretes(String nombres) {
		Interprete interprete = null;
		String[] interpretes = nombres.split("&");
		LinkedList<Interprete> lista = new LinkedList<>();
		for (int j = 0; j < interpretes.length; j++) {

			String interpreteActual = interpretes[j];

			if (!catalogoInterpretes.existeInterprete(interpreteActual)) {
				interprete = adaptadorInterpretes.registrarInterprete(new Interprete(interpreteActual));
				catalogoInterpretes.addInterprete(interprete);
			} else {
				interprete = catalogoInterpretes.getInterprete(interpreteActual);
			}

			lista.add(interprete);
		}
		return lista;
	}

	private String parsearTitulo(String nombre) {
		String titulo = nombre.strip();

		titulo = titulo.substring(0, 1) + titulo.substring(1).toLowerCase();

		int index = titulo.indexOf('.');
		titulo = titulo.substring(0, index);
		return titulo;
	}
}
