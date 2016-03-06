package com.apichallenge.table;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.apichallenge.api.ChampionData;

/**
 * @author Nick Mckloski
 *
 */
public class CellRenderer implements TableCellRenderer {
	
	public static JLabel[] champIcons = new JLabel[ChampionData.champData.length];
	
	public CellRenderer() {
	}

	public void prerenderChampIcons(JTable table) {
		for(int i = 0; i < ChampionData.champData.length; i++) {
			Object value = (String) ChampionData.champData[i][0];
			getTableCellRendererComponent(table, value, false, false, i, 0);
		}
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		JLabel label = new JLabel();
		if(column != 6) {
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setText(NumberFormat.getNumberInstance(Locale.US).format(value));
		}
		return label;
	}
}