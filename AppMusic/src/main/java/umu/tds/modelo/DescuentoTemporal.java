package umu.tds.modelo;
//Descuento temporal del 20%

public class DescuentoTemporal implements Descuento {

	@Override
	public double calcDescuento(double precioBase) {
		return precioBase*0.8;
	}
}
