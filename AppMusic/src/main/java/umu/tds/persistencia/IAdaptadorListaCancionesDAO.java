package umu.tds.persistencia;

import umu.tds.modelo.ListaCanciones;

public interface IAdaptadorListaCancionesDAO {
	public void registrarListaCanciones(ListaCanciones listaCanciones);
	public ListaCanciones recuperarListaCanciones(int codigo);
}
