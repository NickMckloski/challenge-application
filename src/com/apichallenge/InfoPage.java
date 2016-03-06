package com.apichallenge;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * @author Nick Mckloski
 *
 */
public class InfoPage extends JFrame {
	
	private static final long serialVersionUID = 8311443867217713525L;

	public InfoPage() {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        getContentPane().add(container, BorderLayout.CENTER);
        
        JLabel info1 = new JLabel("Version 1.0 - Last Updated 4/14/2015");
        JLabel info2 = new JLabel("This application was made by Nick Mckloski.");
        JLabel info3 = new JLabel("Contact Mckloski.Nick@Gmail.com for buisness inquiries.");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        info1.setHorizontalAlignment(JLabel.CENTER);
        info2.setHorizontalAlignment(JLabel.CENTER);
        info3.setHorizontalAlignment(JLabel.CENTER);
        
        panel.add(info1, BorderLayout.NORTH);
        panel.add(info2, BorderLayout.CENTER);
        panel.add(info3, BorderLayout.SOUTH);
        
        container.add(panel, BorderLayout.CENTER);
	}
	
	public static void createInfoPage() {
		InfoPage frame = new InfoPage();
		frame.setTitle("Information");
		frame.setSize(300, 100);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
