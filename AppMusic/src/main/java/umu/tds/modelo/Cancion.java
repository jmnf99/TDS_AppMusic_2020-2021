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
}
