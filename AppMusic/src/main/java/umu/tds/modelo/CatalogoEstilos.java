package umu.tds.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorEstiloMusicalDAO;

public class CatalogoEstilos {

	private Map<Integer, EstiloMusical> estilos;
	private static CatalogoEstilos unicaInstancia = new CatalogoEstilos();

	private FactoriaDAO dao;
	private IAdaptadorEstiloMusicalDAO adaptadorEstilos;

	private CatalogoEstilos() {
		try {
			dao = FactoriaDAO.getInstancia();
			adaptadorEstilos = dao.getEstiloDAO();
			estilos = new HashMap<Integer, EstiloMusical>();
			this.cargarCatalogo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CatalogoEstilos getUnicaInstancia() {
		return unicaInstancia;
	}

	public List<EstiloMusical> getEstilos() {
		ArrayList<EstiloMusical> lista = new ArrayList<EstiloMusical>();
		for (EstiloMusical estiloMusical : estilos.values()) {
			lista.add(estiloMusical);
		}
		return lista;
	}
	
	public String[] getNombreEstilos() {
		String[] nombreEstilos = new String[this.estilos.values().size() + 1];
		int i = 0;
		for (EstiloMusical e : this.estilos.values()) {
			nombreEstilos[i++] = e.getNombre();
		}
		nombreEstilos[i] = "Todos";
		return nombreEstilos;
	}

	public EstiloMusical getEstiloMusical(int codigo) {
		return estilos.get(codigo);
	}
	
	public EstiloMusical getEstiloMusical(String nombre) {
		for (EstiloMusical e : estilos.values()) {
			if (e.getNombre().equalsIgnoreCase(nombre))
				return e;
		}
		return null;
	}

	public boolean existeEstilo(int codigo) {
		return estilos.containsKey(codigo);
	}

	public boolean existeEstilo(String nombre) {
		for (EstiloMusical e : estilos.values()) {
			if (e.getNombre().equalsIgnoreCase(nombre))
				return true;
		}
		return false;
	}

	public void addEstilo(EstiloMusical estilo) {
		estilos.put(estilo.getCodigo(), estilo);
	}

	public void removeEstilo(int codigo) {
		estilos.remove(codigo);
	}

	private void cargarCatalogo() {
		List<EstiloMusical> estilosBD = adaptadorEstilos.recuperarTodosEstilosMusicales();
		for (EstiloMusical e : estilosBD)
			estilos.put(e.getCodigo(), e);
	}
}
