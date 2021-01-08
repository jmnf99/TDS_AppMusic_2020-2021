package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.EstiloMusical;
import umu.tds.modelo.Interprete;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;

	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorCancionTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// cuando se registra un cancion se le asigna un identificador único
	public Cancion registrarCancion(Cancion cancion) {
		Entidad eCancion;
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return cancion;
		
		// Se registran sus objetos agregados
		AdaptadorInterpreteTDS adaptadorInterprete = AdaptadorInterpreteTDS.getUnicaInstancia();
		for (Interprete i : cancion.getInterpretes()) {
			adaptadorInterprete.registrarInterprete(i);
		}
		
		AdaptadorEstiloMusicalTDS adaptadorEstiloMusical = AdaptadorEstiloMusicalTDS.getUnicaInstancia();
		adaptadorEstiloMusical.registrarEstiloMusical(cancion.getEstilo());

		// la cancion ya existe
		// Se crea la entidad eCancion
		eCancion = new Entidad();
		eCancion.setNombre("cancion");

		// Se le anaden las propiedades a la entidad creada
		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("titulo", cancion.getTitulo()),
				new Propiedad("rutaFichero", cancion.getRutaFichero()),
				new Propiedad("numReproducciones", Integer.toString(cancion.getNumReproducciones())),
				new Propiedad("interpretes", obtenerCodigosInterpretes(cancion.getInterpretes())),
				new Propiedad("estilo", Integer.toString(cancion.getEstilo().getCodigo())))));
		// Se registra la entidad eCancion asignandole un identificador unico
		eCancion = servPersistencia.registrarEntidad(eCancion);
		cancion.setCodigo(eCancion.getId());
		return cancion;
	}

	public Cancion recuperarCancion(int codigo) {
		Entidad eCancion;
		String titulo, rutaFichero;
		List<Interprete> interpretes = new LinkedList<Interprete>();
		EstiloMusical estilo;
		int numReproducciones;

		// recuperar entidad
		eCancion = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
		rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, "rutaFichero");
		numReproducciones = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "numReproducciones"));

		// recuperar propiedades que son objetos llamando a adaptadores
		AdaptadorEstiloMusicalTDS adaptadorEstiloMusicalTDS = AdaptadorEstiloMusicalTDS.getUnicaInstancia();

		interpretes = obtenerInterpretesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eCancion, "interpretes")); 
		
		estilo = adaptadorEstiloMusicalTDS.recuperarEstiloMusical(
				Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "estilo")));

		// creamos el objeto a recuperar
		Cancion cancion = new Cancion(titulo, estilo, rutaFichero, interpretes);
		cancion.setNumReproducciones(numReproducciones);
		cancion.setCodigo(codigo);
		return cancion;
	}

	public List<Cancion> recuperarTodasCanciones() {
		List<Cancion> canciones = new LinkedList<Cancion>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("cancion");
		for (Entidad eCancion : entidades) {
			canciones.add(recuperarCancion(eCancion.getId()));
		}
		return canciones;
	}
	
	public void modificarNumReproducciones(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		
		servPersistencia.eliminarPropiedadEntidad(eCancion, "numReproducciones");
		servPersistencia.anadirPropiedadEntidad(eCancion, "numReproducciones", Integer.toString(cancion.getNumReproducciones()));
	}

	public void borrarCancion(Cancion cancion) {
		Entidad eCancion;
		eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		servPersistencia.borrarEntidad(eCancion);
	}
	
	// -------------------Funciones auxiliares-----------------------------

		// Obtener ids a partir de una lista de objetos al registrar
		private String obtenerCodigosInterpretes(List<Interprete> interpretes) {
			String aux = "";
			for (Interprete interprete : interpretes)
				aux += interprete.getCodigo() + " ";
			return aux.trim();
		}

		// Obtener una lista de objetos a partir de un string con una lista de ids al
		// recuperar

		private List<Interprete> obtenerInterpretesDesdeCodigos(String interpretes) {
			List<Interprete> aux = new LinkedList<Interprete>();
			StringTokenizer tokens = new StringTokenizer(interpretes, " ");
			AdaptadorInterpreteTDS adaptadorInterprete = AdaptadorInterpreteTDS.getUnicaInstancia();
			while (tokens.hasMoreTokens()) {
				aux.add(adaptadorInterprete.recuperarInterprete(Integer.valueOf((String) tokens.nextElement())));
			}
			return aux;
		}
}
