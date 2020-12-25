package umu.tds.modelo;

import java.io.File;
import java.util.List;
import umu.tds.persistencia.IAdaptadorEstiloMusicalDAO;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class CargadorCancionesDisco {
	private CatalogoEstilos catalogoEstilos;
	private IAdaptadorEstiloMusicalDAO adaptadorEstilo;
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
	}

	private void inicializarCatalogos() {
		catalogoEstilos = CatalogoEstilos.getUnicaInstancia();
	}

	public void obtenerEstilosMusicales() {
		File carpeta = new File("./src/main/java/umu.tds.catalogo.canciones");
		String[] listado = carpeta.list();
		for (int i = 0; i < listado.length; i++) {
			if (!catalogoEstilos.existeEstilo(listado[i])) {
				EstiloMusical estilo = adaptadorEstilo.registrarEstiloMusical(
						new EstiloMusical(listado[i].substring(0, 1) + listado[i].substring(1).toLowerCase()));
				catalogoEstilos.addEstilo(estilo);
			}
			
			//Para cada estilo
			File archivos = new File(listado[i]);
			String[] canciones = archivos.list();
			for(int j = 0; j < listado.length; j++) {
				
			}
			
		}

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
}
