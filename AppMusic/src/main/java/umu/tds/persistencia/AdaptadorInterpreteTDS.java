package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Interprete;

public class AdaptadorInterpreteTDS implements IAdaptadorInterpreteDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorInterpreteTDS unicaInstancia = null;

	public static AdaptadorInterpreteTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorInterpreteTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorInterpreteTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// cuando se registra un interprete se le asigna un identificador único
	public void registrarInterprete(Interprete interprete) {
		Entidad eInterprete;
		boolean existe = true;
		// Si la entidad está registrada no la registra de nuevo
		try {
			eInterprete = servPersistencia.recuperarEntidad(interprete.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		// el interprete ya existe
		// Se crea la entidad eInterprete
		eInterprete = new Entidad();
		eInterprete.setNombre("interprete");

		// Se le anaden las propiedades a la entidad creada
		eInterprete.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", interprete.getNombre()))));
	}

	public Interprete recuperarInterprete(int codigo) {
		Entidad eInterprete;
		String nombre;

		eInterprete = servPersistencia.recuperarEntidad(codigo);
		nombre = servPersistencia.recuperarPropiedadEntidad(eInterprete, "nombre");

		Interprete interprete = new Interprete(nombre);
		interprete.setCodigo(codigo);

		return interprete;
	}

	public List<Interprete> recuperarTodosInterpretes() {
		List<Interprete> interpretes = new LinkedList<Interprete>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("interprete");

		for (Entidad eInterprete : entidades) {
			interpretes.add(recuperarInterprete(eInterprete.getId()));
		}
		return interpretes;
	}
}
