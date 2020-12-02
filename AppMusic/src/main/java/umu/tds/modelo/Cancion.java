package umu.tds.modelo;

public class Cancion {
	private int codigo;
	private String titulo, rutaFichero;
	private Interprete interprete;
	private EstiloMusical estilo;
	private int numReproducciones;

	public Cancion(String titulo, Interprete interprete, EstiloMusical estilo, String rutaFichero) {
		this.codigo = 0;
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutaFichero;
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

	public Interprete getInterprete() {
		return interprete;
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
