package umu.tds.persistencia;

import umu.tds.modelo.Interprete;

public interface IAdaptadorInterpreteDAO {
	public void registrarInterprete(Interprete interprete);
	public Interprete recuperarInterprete(int codigo);
}
