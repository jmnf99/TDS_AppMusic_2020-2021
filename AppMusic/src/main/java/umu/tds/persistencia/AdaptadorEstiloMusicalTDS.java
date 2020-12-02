package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;

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
	public void registrarEstiloMusical(EstiloMusical estiloMusical) {
		Entidad eEstiloMusical;
		boolean existe = true;
		// Si la entidad está registrada no la registra de nuevo
		try {
			eEstiloMusical = servPersistencia.recuperarEntidad(estiloMusical.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe)
			return;

		// el estilo musical ya existe
		// Se crea la entidad eEstiloMusical
		eEstiloMusical = new Entidad();
		eEstiloMusical.setNombre("estiloMusical");

		// Se le anaden las propiedades a la entidad creada
		eEstiloMusical.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", estiloMusical.getNombre()))));
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
}
