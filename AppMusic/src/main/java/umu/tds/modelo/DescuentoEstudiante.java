package umu.tds.modelo;

//Descuento Estudiante del 40%
public class DescuentoEstudiante implements Descuento {

	@Override
	public double calcDescuento(double precioBase) {
		return precioBase*0.6;
	}

}
