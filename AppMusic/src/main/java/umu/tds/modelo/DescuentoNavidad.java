package umu.tds.modelo;
//Descuento Navidad del 20%

public class DescuentoNavidad implements Descuento {

	@Override
	public double calcDescuento(double precioBase) {
		return precioBase*0.8;
	}
}
