package umu.tds.modelo;

import javax.swing.table.AbstractTableModel;

public class TablaModelo extends AbstractTableModel {
	private String[] columnNames = { "Título", "Intérprete" };

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
}
