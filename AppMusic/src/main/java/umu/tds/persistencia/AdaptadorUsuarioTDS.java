package umu.tds.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;

	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}
	private AdaptadorUsuarioTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	}
}
