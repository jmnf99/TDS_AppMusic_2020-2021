package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Cancion;

public interface IAdaptadorCancionDAO {
	public Cancion registrarCancion(Cancion cancion);
	public Cancion recuperarCancion(int codigo);
	public List<Cancion> recuperarTodasCanciones();
	public void borrarCancion(Cancion cancion);
}
