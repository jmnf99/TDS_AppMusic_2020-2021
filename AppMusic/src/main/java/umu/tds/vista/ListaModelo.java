package umu.tds.vista;

import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractListModel;

public class ListaModelo extends AbstractListModel<String> {
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
	
	public void anadirElemento(String lista) {
		values.add(lista);
		fireIntervalAdded(this, getSize(), getSize() + 1);
	}

	public void eliminarElemento(String lista) {
		Iterator<String> iterador = values.iterator();
		while(iterador.hasNext()) {
			String v = iterador.next();
			if(v.equals(lista))
				iterador.remove();
		}
		fireIntervalRemoved(this, getSize(), getSize() + 1);
	}
}
