package umu.tds.vista;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;

public class ListaModelo extends AbstractListModel<String> implements ListModel<String> {
	private static final long serialVersionUID = 1L;
	private List<String> values;

	public ListaModelo(List<String> values) {
		this.values = values;
	}

	@Override
	public int getSize() {
		return values.size();
	}

	@Override
	public String getElementAt(int index) {
		return values.get(index);
	}

	public void eliminarElemento(int indice) {
		values.remove(indice);
		//fireIntervalRemoved(source, index0, index1);
	}

}
