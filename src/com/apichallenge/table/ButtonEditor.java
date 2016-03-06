package com.apichallenge.table;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.apichallenge.StatsPage;
import com.apichallenge.api.ChampionData;

/**
 * @author Nick Mckloski
 *
 */
public class ButtonEditor extends DefaultCellEditor {
	
	private static final long serialVersionUID = -4083677955586516020L;
	protected JButton button;
	private String label;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {		
		String name = (value == null) ? "" : value.toString();
		label = name;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./data/icons/"+name+"Square.png");
		Image newimg = img.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(newimg));
		button.setText(label);
		button.setToolTipText(name);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorderPainted(false);
		button.setBackground(null);
		button.setOpaque(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			int champId = 0;
			for(int i = 0; i < ChampionData.champData.length; i++) {
				if(label.contains((String)ChampionData.champData[i][0])) {
					champId = i;
				}
			}
			for(int i = 0; i < 3; i++)
				StatsPage.statStrings[i] = (String) ChampionData.champData[champId][i];
			int i = 0;
			for(int i2 = 3; i2 < 21; i2++) {
				StatsPage.statData[i] = ChampionData.champData[champId][i2].equals("N/A") ? 0 : Double.parseDouble((String) ChampionData.champData[champId][i2]);i++;
			}
			StatsPage.createStatsPage((String) ChampionData.champData[champId][0]);
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}