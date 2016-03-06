package com.apichallenge;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * @author Nick Mckloski
 *
 */
public class StatsPage extends JFrame {
	
	private static final long serialVersionUID = 4431161786253693293L;

	public static double[] statData = new double[18];
	
	public static String[] statStrings = new String[3];
	
	public StatsPage(final String nameString) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel picture = new JLabel();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./data/icons/"+nameString+"Square.png");
        picture.setIcon(new ImageIcon(img));
        JLabel name = new JLabel(nameString);
        JLabel title = new JLabel(statStrings[1]);
        JLabel role = new JLabel(statStrings[2]);
        JButton button = new JButton("LoLWiki Page");
        
        picture.setBounds(10, 15, 120, 120);
        panel.add(picture);
        name.setBounds(10, 140, 200, 20);
        panel.add(name);
        title.setBounds(10, 160, 200, 20);
        panel.add(title);
        role.setBounds(10, 180, 200, 20);
        panel.add(role);
        button.setBounds(10, 228, 150, 30);
        panel.add(button);
        button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String page = "http://leagueoflegends.wikia.com/wiki/"+nameString;
				OpenPage.OpenWebPage(page.replace(" ", ""));
			}
		});
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(170, 10, 310, 250);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder border = BorderFactory.createTitledBorder(blackline, "Stats");
        panel2.setBorder(border);       
        
        final JLabel hp = new JLabel("Health: "+(int)statData[0]+" (+"+(int)statData[1]+")");
        final JLabel hpAt18 = new JLabel("Level 18: "+(int)(statData[0]+(statData[1]*18)));
        final JLabel hpRegen = new JLabel("Hp Regen: "+statData[2]+" (+"+statData[3]+")");
        final JLabel hpRegenAt18 = new JLabel("Level 18: "+(statData[2]+(statData[3]*18)));
        final JLabel mana = new JLabel("Mana: "+(int)statData[4]+" (+"+(int)statData[5]+")");
        final JLabel manaAt18 = new JLabel("Level 18: "+(int)(statData[4]+(statData[5]*18)));
        final JLabel manaRegen = new JLabel("Mana Regen: "+statData[6]+" (+"+statData[7]+")");
        final JLabel manaRegenAt18 = new JLabel("Level 18: "+(statData[6]+(statData[7]*18)));
        final JLabel range = new JLabel("Range: "+(int)statData[8]);
        final JLabel rangeAt18 = new JLabel("Level 18: "+(int)statData[8]);
        final JLabel ad = new JLabel("Attack Damage: "+statData[9]+" (+"+statData[10]+")");
        final JLabel adAt18 = new JLabel("Level 18: "+(statData[9]+(statData[10]*18)));
        final JLabel as = new JLabel("Attack Speed: "+statData[11]+" (+"+statData[12]+"%)");
        final JLabel asOffset = new JLabel("Level 18: "+(statData[11]+((statData[12]/100)*18)));
        final JLabel ar = new JLabel("Armor: "+statData[13]+" (+"+statData[14]+")");
        final JLabel arAt18 = new JLabel("Level 18: "+(statData[13]+(statData[14]*18)));
        final JLabel magRes = new JLabel("Magic Resist: "+statData[15]+" (+"+statData[16]+")");
        final JLabel magResAt18 = new JLabel("Level 18: "+(statData[15]+(statData[16]*18)));
        final JLabel movSpd = new JLabel("Mov. Speed: "+(int)statData[17]);
        final JLabel movSpdAt18 = new JLabel("Level 18: "+(int)statData[17]);
        JLabel lvl0 = new JLabel("Base Stats(Level 0):");
        
        lvl0.setBounds(10, 15, 200, 20);
        panel2.add(lvl0);
        hp.setBounds(10, 35, 200, 20);
        panel2.add(hp);
        hpAt18.setBounds(200, 35, 100, 20);
        panel2.add(hpAt18);
        hpRegen.setBounds(10, 55, 200, 20);
        panel2.add(hpRegen);
        hpRegenAt18.setBounds(200, 55, 100, 20);
        panel2.add(hpRegenAt18);
        mana.setBounds(10, 75, 200, 20);
        panel2.add(mana);
        manaAt18.setBounds(200, 75, 100, 20);
        panel2.add(manaAt18);
        manaRegen.setBounds(10, 95, 200, 20);
        panel2.add(manaRegen);
        manaRegenAt18.setBounds(200, 95, 100, 20);
        panel2.add(manaRegenAt18);
        range.setBounds(10, 115, 200, 20);
        panel2.add(range);
        rangeAt18.setBounds(200, 115, 100, 20);
        panel2.add(rangeAt18);
        ad.setBounds(10, 135, 200, 20);
        panel2.add(ad);
        adAt18.setBounds(200, 135, 100, 20);
        panel2.add(adAt18);
        as.setBounds(10, 155, 200, 20);
        panel2.add(as);
        asOffset.setBounds(200, 155, 100, 20);
        panel2.add(asOffset);
        ar.setBounds(10, 175, 200, 20);
        panel2.add(ar);
        arAt18.setBounds(200, 175, 100, 20);
        panel2.add(arAt18);
        magRes.setBounds(10, 195, 200, 20);
        panel2.add(magRes);
        magResAt18.setBounds(200, 195, 100, 20);
        panel2.add(magResAt18);
        movSpd.setBounds(10, 215, 200, 20);
        panel2.add(movSpd);
        movSpdAt18.setBounds(200, 215, 100, 20);
        panel2.add(movSpdAt18);
        
        String[] levels = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18" };
	    JComboBox<Object> levelSelect = new JComboBox<Object>(levels);
	    levelSelect.setSelectedIndex(17);
	    final JLabel levelSelectLabel = new JLabel("At Level");
	    levelSelectLabel.setLabelFor(levelSelect);
	    levelSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		        String selectedLevel = (String)((JComboBox<?>) e.getSource()).getSelectedItem();
		        hpAt18.setText("Level "+selectedLevel+": "+(int)(statData[0]+(statData[1]*Integer.parseInt(selectedLevel))));
		        hpRegenAt18.setText("Level "+selectedLevel+": "+(statData[2]+(statData[3]*Integer.parseInt(selectedLevel))));
		        manaAt18.setText("Level "+selectedLevel+": "+(int)(statData[4]+(statData[5]*Integer.parseInt(selectedLevel))));
		        manaRegenAt18.setText("Level "+selectedLevel+": "+(statData[6]+(statData[7]*Integer.parseInt(selectedLevel))));
		        rangeAt18.setText("Level "+selectedLevel+": "+(int)statData[8]);
		        adAt18.setText("Level "+selectedLevel+": "+(statData[9]+(statData[10]*Integer.parseInt(selectedLevel))));
		        
		        asOffset.setText("Level "+selectedLevel+": "+((1/1.6)*(1+statData[11])+((statData[12]/100)*Integer.parseInt(selectedLevel))));
		        arAt18.setText("Level "+selectedLevel+": "+(statData[13]+(statData[14]*Integer.parseInt(selectedLevel))));
		        magResAt18.setText("Level "+selectedLevel+": "+(statData[15]+(statData[16]*Integer.parseInt(selectedLevel))));
		        movSpdAt18.setText("Level "+selectedLevel+": "+(int)statData[17]);
			}
		});
	    levelSelect.setBounds(250, 15, 40, 20);
	    levelSelectLabel.setBounds(200, 15, 100, 20);
	    panel2.add(levelSelect);
	    panel2.add(levelSelectLabel);
        
        panel.add(panel2);
        
        getContentPane().add(panel);
        
		
	}
	
	public ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("unable to find: " + path);
            return null;
        }
	}
	
	/*private static void addButtonListeners() {1
    	button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				OpenPage.OpenWebPage("http://www.lolking.net/champions/"+statStrings[0]);
			}
		});
    }*/
	
	public static void createStatsPage(String name) {
		StatsPage frame = new StatsPage(name.replace(" ", ""));
		frame.setTitle(statStrings[0]);
		frame.setSize(500, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setVisible(true);
    	//addButtonListeners();
	}
	
}
