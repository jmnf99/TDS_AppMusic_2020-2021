package umu.tds.modelo;

public class Interprete {
	private int codigo;
	private String nombre;
	
	public Interprete(String nombre) {
		this.codigo = 0;
		this.nombre = nombre;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
}
