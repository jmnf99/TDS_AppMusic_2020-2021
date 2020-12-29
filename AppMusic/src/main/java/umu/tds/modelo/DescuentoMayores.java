package umu.tds.modelo;

//Descuento Mayores de 65 del 50%
public class DescuentoMayores implements Descuento {

	public static final int EDAD = 65;

	@Override
	public double calcDescuento(double precioBase) {
		return precioBase * 0.5;
	}

}
