package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {
	public Usuario registrarUsuario(Usuario usuario);
	public Usuario recuperarUsuario(int codigo);
	public List<Usuario> recuperarTodosUsuarios();
	public void modificarPremium(Usuario usuario);
	public void modificarListas(Usuario usuario);
}
