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

	public String getTitulo() {
		return titulo;
	}

	public String getInteprete() {
		return inteprete;
	}

	public String getEstilo() {
		return estilo;
	}

	public String getRutaFichero() {
		return rutaFichero;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}
}
