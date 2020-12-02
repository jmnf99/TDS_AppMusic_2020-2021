package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class ListaCanciones {
	private int codigo;
	private String nombrePlaylist;
	List<Cancion> canciones;

	public ListaCanciones(String nombrePlaylist) {
		this.codigo = 0;
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
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	//Getters
	public int getCodigo() {
		return codigo;
	}
	public String getNombrePlaylist() {
		return nombrePlaylist;
	}

	public List<Cancion> getCanciones() {
		return new LinkedList<Cancion>(canciones);
	}
}
