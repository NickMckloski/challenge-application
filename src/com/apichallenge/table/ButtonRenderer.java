package com.apichallenge.table;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import com.apichallenge.api.ChampionData;

/**
 * @author Nick Mckloski
 *
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
	
	private static final long serialVersionUID = 5506030057945728529L;
	public static JLabel[] champIcons = new JLabel[ChampionData.champData.length];
	
	public ButtonRenderer() {
		setOpaque(true);
	}

	public void prerenderChampIcons(JTable table) {
		for(int i = 0; i < ChampionData.champData.length; i++) {
			Object value = (String) ChampionData.champData[i][0];
			getTableCellRendererComponent(table, value, false, false, i, 0);
		}
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		if(column == 0) {
			JLabel iconBeingRendered = new JLabel();
			
			String name = (value == null) ? "" : value.toString();
			if(champIcons[(row)] == null) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image img = toolkit.getImage("./data/icons/"+name+"Square.png");
				Image newimg = img.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				iconBeingRendered.setIcon(new ImageIcon(newimg));
				iconBeingRendered.setToolTipText(name);
				iconBeingRendered.setText(name);
				iconBeingRendered.setHorizontalAlignment(SwingConstants.LEFT);
				champIcons[row] = iconBeingRendered;
				return iconBeingRendered;
			} else {
				return champIcons[row];
			}
		} else if(column != 6) {
			System.out.println(column+"  "+value);
			setHorizontalAlignment(JLabel.CENTER);
            setText(NumberFormat.getNumberInstance(Locale.US).format(value));
		}
		return this;
	}
}