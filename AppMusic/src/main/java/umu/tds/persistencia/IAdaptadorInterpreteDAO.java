package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Interprete;

public interface IAdaptadorInterpreteDAO {
	public void registrarInterprete(Interprete interprete);
	public Interprete recuperarInterprete(int codigo);
	public List<Interprete> recuperarTodosInterpretes();
}
