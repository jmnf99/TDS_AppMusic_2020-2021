package umu.tds.modelo;

import java.util.List;
import java.util.stream.Collectors;

public class Filtros {
	private static CatalogoCanciones catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	
	private static boolean interpreteComparador(List<Interprete> lista, String interprete) {
		if(interprete.isEmpty()) return true;
		for (Interprete i : lista) {
			if(i.getNombre().toLowerCase().contains(interprete.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public static List<Cancion> getCancionesFiltro(String titulo, String interprete, String estilo) {
		
		List<Cancion> lista = catalogoCanciones.getCanciones();
		return lista.stream()
					.filter(c -> c.getEstilo().getNombre().toLowerCase().contains(estilo.toLowerCase()))
					.filter(c -> c.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
					.filter(c -> interpreteComparador(c.getInterpretes(), interprete))
					.collect(Collectors.toList());
	}
}
