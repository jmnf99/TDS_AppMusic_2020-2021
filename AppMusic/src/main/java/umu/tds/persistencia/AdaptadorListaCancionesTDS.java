package umu.tds.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorListaCancionesTDS implements IAdaptadorListaCancionesDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaCancionesTDS unicaInstancia = null;

	public static AdaptadorListaCancionesTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorListaCancionesTDS();
		else
			return unicaInstancia;
	}
	private AdaptadorListaCancionesTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	}
}
