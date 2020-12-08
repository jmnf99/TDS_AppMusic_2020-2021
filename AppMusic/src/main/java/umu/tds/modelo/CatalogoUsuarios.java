package umu.tds.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {

	private Map<String, Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	
	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuario = dao.getUsuarioDAO();
			usuarios = new HashMap<String, Usuario>();
			this.cargarCatalogo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}
	
	public List<Usuario> getUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<>();
		for(Usuario u : usuarios.values())
			lista.add(u);
		return lista;
	}
	
	public Usuario getUsuario(String nUsuario) {
		return usuarios.get(nUsuario);
	}
	
	public void addUsuario(Usuario u) {
		usuarios.put(u.getNusuario(), u);
	}
	
	public void removeUsuario(String nombreUsuario) {
		usuarios.remove(nombreUsuario);
	}
	
	private void cargarCatalogo() {
		List<Usuario> usuariosDB = adaptadorUsuario.recuperarTodosUsuarios();
		for(Usuario u : usuariosDB) 
			usuarios.put(u.getNusuario(), u);
		
	}
	
}
