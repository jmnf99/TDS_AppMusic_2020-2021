package umu.tds.modelo;

public class EstiloMusical {
	private int codigo;
	private String nombre;
	
	public EstiloMusical(String nombre) {
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
	
	@Override
	public String toString() {
		return nombre;
	}
}
