package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		adaptadorInterprete.registrarInterprete(cancion.getInterprete());

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
				new Propiedad("interprete", Integer.toString(cancion.getInterprete().getCodigo())),
				new Propiedad("estilo", Integer.toString(cancion.getEstilo().getCodigo())))));
		// Se registra la entidad eCancion asignandole un identificador unico
		eCancion = servPersistencia.registrarEntidad(eCancion);
		cancion.setCodigo(eCancion.getId());
		return cancion;
	}

	public Cancion recuperarCancion(int codigo) {
		Entidad eCancion;
		String titulo, rutaFichero;
		Interprete interprete;
		EstiloMusical estilo;
		int numReproducciones;

		// recuperar entidad
		eCancion = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
		rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, "rutaFichero");
		numReproducciones = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "numReproducciones"));

		// recuperar propiedades que son objetos llamando a adaptadores
		AdaptadorInterpreteTDS adaptadorInterpreteTDS = AdaptadorInterpreteTDS.getUnicaInstancia();
		AdaptadorEstiloMusicalTDS adaptadorEstiloMusicalTDS = AdaptadorEstiloMusicalTDS.getUnicaInstancia();

		interprete = adaptadorInterpreteTDS.recuperarInterprete(
				Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "interprete")));
		estilo = adaptadorEstiloMusicalTDS.recuperarEstiloMusical(
				Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "estilo")));

		// creamos el objeto a recuperar
		Cancion cancion = new Cancion(titulo, interprete, estilo, rutaFichero);
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
}
