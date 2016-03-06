package com.apichallenge.table;

import javax.swing.table.AbstractTableModel;

import com.apichallenge.MainFrame;

/**
 * @author Nick Mckloski
 *
 */
public class TableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -3567361742860717675L;

	public String getColumnName(int col) {
		return MainFrame.columnName[col].toString();
	}

	public int getRowCount() {
		return MainFrame.data.length;
	}

	public int getColumnCount() {
		return MainFrame.columnName.length;
	}

	public Object getValueAt(int row, int col) {
		return MainFrame.data[row][col];
	}
	
	@Override
	public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return Long.class;
    		case 2:
                return Long.class;
    		case 3:
                return Long.class;
    		case 4:
                return Long.class;
    		case 5:
                return Long.class;
    		case 6:
                return String.class;
    		case 7:
                return Long.class;
    		case 8:
                return Long.class;
    		default:
    			return Long.class;
        }
    }
	
	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 0:
			return true;
		case 1:
			return false;
		case 2:
			return false;
		case 3:
			return false;
		case 4:
			return false;
		case 5:
			return false;
		case 6:
			return false;
		case 7:
			return false;
		case 8:
			return false;
		}
		return false;
	}

	public void setValueAt(Object value, int row, int col) {
		MainFrame.data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

}
