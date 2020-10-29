package umu.tds.modelo;

public class Cancion {
	private String titulo, inteprete, estilo, rutaFichero;
	private int numReproducciones;
	
	public Cancion(String titulo, String interprete, String estilo, String rutaFichero) {
		this.titulo = titulo;
		this.inteprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutaFichero;
	}
}
