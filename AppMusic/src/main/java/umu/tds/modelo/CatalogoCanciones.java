package umu.tds.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorCancionDAO;

public class CatalogoCanciones {

	private Map<Integer, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia = new CatalogoCanciones();
	
	private FactoriaDAO dao;
	private IAdaptadorCancionDAO adaptadorCanciones;
	
	private CatalogoCanciones() {
		try {
			dao = FactoriaDAO.getInstancia();
			adaptadorCanciones = dao.getCancionDAO();
			canciones = new HashMap<Integer, Cancion>();
			this.cargarCatalogo();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static CatalogoCanciones getUnicaInstancia() {
		return unicaInstancia;
	}
	
	public List<Cancion> getCanciones() {
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		for(Cancion c: canciones.values())
			lista.add(c);
		return lista;
	}
	
	public Cancion getCancion(int codigo) {
		return canciones.get(codigo);
	}
	
	public void addCancion(Cancion cancion) {
		canciones.put(cancion.getCodigo(), cancion);
	}
	
	public void removeCancion(int codigo) {
		canciones.remove(codigo);
	}
	
	private void cargarCatalogo() {
		List<Cancion> cancionesBD = adaptadorCanciones.recuperarTodasCanciones();
		for(Cancion c : cancionesBD)
			canciones.put(c.getCodigo(), c);
	}
}
