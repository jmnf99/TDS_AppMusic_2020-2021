package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.EstiloMusical;

public interface IAdaptadorEstiloMusicalDAO {
	public void registrarEstiloMusical(EstiloMusical estiloMusical);
	public EstiloMusical recuperarEstiloMusical(int codigo);
	public  List<EstiloMusical> recuperarTodosEstilosMusicales();
}