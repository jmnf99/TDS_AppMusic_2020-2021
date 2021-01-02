package umu.tds.persistencia;

import umu.tds.modelo.ListaCanciones;

public interface IAdaptadorListaCancionesDAO {
	public void registrarListaCanciones(ListaCanciones listaCanciones);
	public ListaCanciones recuperarListaCanciones(int codigo);
	public ListaCanciones recuperarListaCanciones(String nombre);
	public void borrarListaCanciones(ListaCanciones listaCanciones);
}
