package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class ListaCanciones {
	private String nombrePlaylist;
	LinkedList<Cancion> canciones;

	public ListaCanciones(String nombrePlaylist) {
		this.nombrePlaylist = nombrePlaylist;
		this.canciones = new LinkedList<Cancion>();
	}
	
	//Metodos
	public boolean addCancion(Cancion c){
		if(canciones.contains(c)) {
			return false;
		}
		else
			canciones.add(c);
			return true;
	}
	
	//Getters
	public String getNombrePlaylist() {
		return nombrePlaylist;
	}

	public List<Cancion> getCanciones() {
		return new LinkedList<Cancion>(canciones);
	}
}
