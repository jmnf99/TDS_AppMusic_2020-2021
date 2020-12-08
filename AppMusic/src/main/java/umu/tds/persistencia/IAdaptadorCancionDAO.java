package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Cancion;

public interface IAdaptadorCancionDAO {
	public void registrarCancion(Cancion cancion);
	public Cancion recuperarCancion(int codigo);
	public List<Cancion> recuperarTodasCanciones();
}
