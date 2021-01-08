package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class Cancion {
	private int codigo;
	private String titulo, rutaFichero;
	private List<Interprete> interpretes;
	private EstiloMusical estilo;
	private int numReproducciones;

	public Cancion(String titulo, EstiloMusical estilo, String rutaFichero, List<Interprete> interpretes) {
		this.codigo = 0;
		this.titulo = titulo;
		this.estilo = estilo;
		this.rutaFichero = rutaFichero;
		this.interpretes = interpretes;
		this.numReproducciones = 0;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<Interprete> getInterpretes() {
		return new LinkedList<Interprete>(interpretes);
	}

	public EstiloMusical getEstilo() {
		return estilo;
	}

	public String getRutaFichero() {
		return rutaFichero;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones = numReproducciones;
	}
	
	public void escuchada() {
		this.numReproducciones++;
	}
	
	@Override
	public String toString() {
		String res = titulo + ": " + interpretes + " - " + estilo + " _ " + rutaFichero;
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
//		boolean interprete_igual = ((Cancion) obj).getInterpretes().equals(this.getInterpretes());
//		return interprete_igual;
		return true;
	}
}
