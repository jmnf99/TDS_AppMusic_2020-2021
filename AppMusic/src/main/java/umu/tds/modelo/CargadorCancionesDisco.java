package umu.tds.modelo;

import java.io.File;
import java.util.List;
import umu.tds.persistencia.IAdaptadorEstiloMusicalDAO;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorCancionDAO;

public class CargadorCancionesDisco {
	private final static String rutaCancionesDisco = "./src/main/java/umu.tds.catalogo.canciones";

	private CatalogoEstilos catalogoEstilos;
	private CatalogoCanciones catalogoCanciones;
	private IAdaptadorEstiloMusicalDAO adaptadorEstilo;
	private IAdaptadorCancionDAO adaptadorCanciones;
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
	}

	private void inicializarCatalogos() {
		catalogoEstilos = CatalogoEstilos.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	}

	public String[] obtenerEstilosMusicalesDisco() {
		File carpeta = new File(rutaCancionesDisco);
		return carpeta.list();
	}

	public void comprobarEstilosMusicales() {
		String[] listado = obtenerEstilosMusicalesDisco();

		List<EstiloMusical> lista = adaptadorEstilo.recuperarTodosEstilosMusicales();
		for (EstiloMusical e : lista) {
			boolean existe = false;
			for (int i = 0; i < listado.length; i++) {
				if (e.getNombre().equalsIgnoreCase(listado[i])) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				catalogoEstilos.removeEstilo(e.getCodigo());
			}
		}
	}

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
			File archivos = new File(rutaCancionesDisco + "/" + listado[i]);
			String[] canciones = archivos.list();
			for (int j = 0; j < canciones.length; j++) {
				//System.out.println(canciones[j]);
				mostrarCancion(canciones[j]);
			}
		}
	}
	
	public void mostrarCancion(String cancion) {
		String[] campos = cancion.split("-");
		for(int i = 0; i<campos.length; i ++) {
//			campos[0] = campos[0].substring(0, 1) + campos[0].substring(1).toLowerCase();
//			campos[1] = campos[1].substring(0, 1) + campos[0].substring(1).toLowerCase();
//			new Cancion(campos[0], campos, estilo, ruta);
			System.out.println(campos[i].strip());
		}
		System.out.println();
	}
}
