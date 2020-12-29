package umu.tds.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorInterpreteDAO;

public class CatalogoInterpretes {

	private Map<Integer, Interprete> interpretes;
	private static CatalogoInterpretes unicaInstancia = new CatalogoInterpretes();

	private FactoriaDAO dao;
	private IAdaptadorInterpreteDAO adaptadorInterpretes;

	private CatalogoInterpretes() {
		try {
			dao = FactoriaDAO.getInstancia();
			adaptadorInterpretes = dao.getInterpreteDAO();
			interpretes = new HashMap<>();
			this.cargarCatalogo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static CatalogoInterpretes getUnicaInstancia() {
		return unicaInstancia;
	}

	public List<Interprete> getInterpretes() {
		ArrayList<Interprete> lista = new ArrayList<>();
		for (Interprete i : interpretes.values())
			lista.add(i);
		return lista;
	}

	public Interprete getInterprete(int codigo) {
		return interpretes.get(codigo);
	}
	
	public Interprete getInterprete(String nombre) {
		for (Interprete i : interpretes.values()) {
			if (i.getNombre().equalsIgnoreCase(nombre))
				return i;
		}
		return null;
	}

	public boolean existeInterprete(int codigo) {
		return interpretes.containsKey(codigo);
	}
	
	public boolean existeInterprete(String nombre) {
		for (Interprete i : interpretes.values()) {
			if (i.getNombre().equalsIgnoreCase(nombre))
				return true;
		}
		return false;
	}
	
	public void addInterprete(Interprete interprete) {
		interpretes.put(interprete.getCodigo(), interprete);
	}
	
	public void removeInterprete(int codigo) {
		interpretes.remove(codigo);
	}

	private void cargarCatalogo() {
		List<Interprete> interpretesBD = adaptadorInterpretes.recuperarTodosInterpretes();
		for (Interprete i : interpretesBD)
			interpretes.put(i.getCodigo(), i);
	}
}
