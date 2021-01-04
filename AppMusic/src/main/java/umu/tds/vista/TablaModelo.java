package umu.tds.vista;

import java.util.List;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import umu.tds.modelo.Cancion;

public class TablaModelo extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Título", "Intérprete" };
	List<Cancion> data;
	
	public TablaModelo() {
		data = new LinkedList<>();
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	      Cancion cancion = data.get(rowIndex);

	      switch(columnIndex){
	        case 0: return cancion.getTitulo();
	        case 1: return cancion.getInterpretes();
	        default : return null;
	      }
	}
	
	public void añadirFila(Cancion c) {
		data.add(0, c);
		fireTableDataChanged();
	}
	
	public void eliminarFila(int c) {
		data.remove(c);
		fireTableDataChanged();
	}
	
	public void limpiarDatos() {
		data.clear();
		fireTableDataChanged();
	}
	
	public Cancion getCancionFila(int row) {
		return data.get(row);
	}
	
	public List<Cancion> getCanciones() {
		return new LinkedList<>(data);
	}
	
}
