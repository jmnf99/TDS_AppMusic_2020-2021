package umu.tds.modelo;

public class DescuentoNormal implements Descuento {

	@Override
	public double calcDescuento(double precioBase) {
		return precioBase;
	}

}
