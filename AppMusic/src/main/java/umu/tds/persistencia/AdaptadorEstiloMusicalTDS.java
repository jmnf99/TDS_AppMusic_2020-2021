package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.EstiloMusical;

public class AdaptadorEstiloMusicalTDS implements IAdaptadorEstiloMusicalDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorEstiloMusicalTDS unicaInstancia = null;

	public static AdaptadorEstiloMusicalTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorEstiloMusicalTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorEstiloMusicalTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// cuando se registra un estilo musical se le asigna un identificador único
	public EstiloMusical registrarEstiloMusical(EstiloMusical estiloMusical) {
		Entidad eEstiloMusical;
		boolean existe = true;
		// Si la entidad está registrada no la registra de nuevo
		try {
			eEstiloMusical = servPersistencia.recuperarEntidad(estiloMusical.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return estiloMusical;

		// el estilo musical ya existe
		// Se crea la entidad eEstiloMusical
		eEstiloMusical = new Entidad();
		eEstiloMusical.setNombre("estiloMusical");

		// Se le anaden las propiedades a la entidad creada
		eEstiloMusical.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", estiloMusical.getNombre()))));
		
		// Se registra la entidad eEstiloMusical asignandole un identificador unico
		eEstiloMusical = servPersistencia.registrarEntidad(eEstiloMusical);
		estiloMusical.setCodigo(eEstiloMusical.getId());
		return estiloMusical;
	}

	public EstiloMusical recuperarEstiloMusical(int codigo) {
		Entidad eEstiloMusical;
		String nombre;

		eEstiloMusical = servPersistencia.recuperarEntidad(codigo);
		nombre = servPersistencia.recuperarPropiedadEntidad(eEstiloMusical, "nombre");

		EstiloMusical estiloMusical = new EstiloMusical(nombre);
		estiloMusical.setCodigo(codigo);

		return estiloMusical;
	}

	public List<EstiloMusical> recuperarTodosEstilosMusicales() {
		List<EstiloMusical> estilosMusicales = new LinkedList<EstiloMusical>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("estiloMusical");

		for (Entidad eEstiloMusical : entidades) {
			estilosMusicales.add(recuperarEstiloMusical(eEstiloMusical.getId()));
		}
		return estilosMusicales;
	}
	
	public void borrarEstiloMusical(EstiloMusical estiloMusical) {
		Entidad eEstiloMusical;
		eEstiloMusical = servPersistencia.recuperarEntidad(estiloMusical.getCodigo());
		servPersistencia.borrarEntidad(eEstiloMusical);
	}

}
