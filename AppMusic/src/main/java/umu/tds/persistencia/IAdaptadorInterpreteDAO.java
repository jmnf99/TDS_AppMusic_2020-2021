package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Interprete;

public interface IAdaptadorInterpreteDAO {
	public Interprete registrarInterprete(Interprete interprete);
	public Interprete recuperarInterprete(int codigo);
	public List<Interprete> recuperarTodosInterpretes();
	public void borrarInterprete(Interprete interprete);
}
