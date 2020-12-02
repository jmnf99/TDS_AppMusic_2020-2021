package umu.tds.persistencia;

import umu.tds.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {
	public void registrarUsuario(Usuario usuario);
	public Usuario recuperarUsuario(int codigo);

}
