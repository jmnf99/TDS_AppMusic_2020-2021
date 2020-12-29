package umu.tds.modelo;

public interface Descuento {
	public static final int NAVIDAD = 0;
	public static final int ESTUDIANTE = 1;
	public static final int MAYORES = 2;
	
	
	public double calcDescuento(double precioBase);

}
