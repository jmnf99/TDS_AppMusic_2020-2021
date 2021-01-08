package umu.tds.modelo;

import java.util.Comparator;
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
	
	public static List<Cancion> getCancionesMasEscuchadas(){
		List<Cancion> lista = catalogoCanciones.getCanciones();
		int numeroCanciones = lista.size();
		return lista.stream()
					.sorted(Comparator.comparing(Cancion::getNumReproducciones))
					.skip(numeroCanciones-10)
					.filter(c->c.getNumReproducciones()>0)
					.collect(Collectors.toList());
	}
}
