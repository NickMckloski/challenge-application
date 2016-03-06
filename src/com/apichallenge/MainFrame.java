package com.apichallenge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import com.apichallenge.api.ChampionData;
import com.apichallenge.table.ButtonEditor;
import com.apichallenge.table.ButtonRenderer;
import com.apichallenge.table.CellRenderer;
import com.apichallenge.table.TableModel;

/**
 * @author Nick Mckloski
 *
 */
public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -5224889059809983735L;
	public static String[] columnName = {"Champion", "Total Damage", "Magic Damage", "Physical Damage", "Minions Killed", "Times Picked", "Role", "PentaKills"};
	public static Object[][] data = new Object[124][8];
	
	private static JMenuItem exit;
	private static JMenuItem information;
	private static JMenuBar mb;
	private static JRadioButton assassinOption;
	private static JRadioButton fighterOption;
	private static JRadioButton mageOption;
	private static JRadioButton marksmanOption;
	private static JRadioButton supportOption;
	private static JRadioButton tankOption;
	private static JRadioButton all;
	
	private static JTextField filterField = new JTextField("");
	
	private static TableModel model = new TableModel();
	private static TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);	

	
	public MainFrame() {
		super();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		JPanel container = new JPanel();
		
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		top.setLayout(new BorderLayout());
		bottom.setLayout(new BorderLayout());
		
		JPanel infoPanel = new JPanel();
		JLabel infoLabel = new JLabel("These are stats from 50,221 URF matches on the NA server. Click a champion for their in-game base stats.");
		infoLabel.setFont(new Font("Ariel", Font.BOLD, 14));
		infoPanel.add(infoLabel);
		top.add(infoPanel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		
		//filter text
		JLabel filterLabel = new JLabel("Search:");
		filterLabel.setLabelFor(filterField);
		//tags text
		JLabel tagsLabel = new JLabel("Tags:");
		//search field and filter options
		filterField.setPreferredSize(new Dimension(200, 24));
		filterField.setToolTipText("Filter by champion name");
		assassinOption = new JRadioButton("Assassin");
		fighterOption = new JRadioButton("Fighter");
		mageOption = new JRadioButton("Mage");
		marksmanOption = new JRadioButton("Marksman");
		supportOption = new JRadioButton("Support");
		tankOption = new JRadioButton("Tank");
		all = new JRadioButton("All");
		
		//group buttons together and add to panel
		groupButtons();
		
		//add other search elements to panel
		panel.add(filterLabel);
		panel.add(filterField);
		panel.add(tagsLabel);
		panel.add(assassinOption);
		panel.add(fighterOption);
		panel.add(mageOption);
		panel.add(marksmanOption);
		panel.add(supportOption);
		panel.add(tankOption);
		panel.add(all);
		
		top.add(panel, BorderLayout.CENTER);
		
		JTable table = new JTable(data, columnName);
		table.setRowSorter(sorter);
		table.setModel(new TableModel());
		customizeTable(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		bottom.add(scrollPane, BorderLayout.CENTER);
		
		container.setLayout(new BorderLayout());
		container.add(top, BorderLayout.NORTH);
		container.add(bottom, BorderLayout.CENTER);
		getContentPane().add(container);
		
        mb = new JMenuBar();
		JMenu file = new JMenu("File");
		mb.add(file);
		exit = new JMenuItem("Exit");
		file.add(exit);
		
		JMenu info = new JMenu("Info");
		mb.add(info);
		information = new JMenuItem("Information");
		info.add(information);
		
		setTitle("URF Stats");
	    setSize(900, 565);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void customizeTable(final JTable table) {
		ButtonRenderer br = new ButtonRenderer();
		CellRenderer cr = new CellRenderer();
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setMinWidth(160);
		table.getColumnModel().getColumn(0).setMaxWidth(160);
		table.getColumnModel().getColumn(0).setWidth(160);
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setWidth(0);
		table.setRowHeight(70);
		table.setDefaultRenderer(Long.class, cr);
		table.getColumn("Champion").setCellRenderer(br);
		table.getColumn("Champion").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.setFillsViewportHeight(true);
		br.prerenderChampIcons(table);
		table.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	for(int i = 0; i < ChampionData.champData.length; i++) {
		        	ButtonRenderer.champIcons[i] = null;
		        }
		    }
		});
	}
	private void groupButtons() {
		ButtonGroup bg = new ButtonGroup();
		bg.add(assassinOption);
		bg.add(mageOption);
		bg.add(supportOption);
		bg.add(marksmanOption);
		bg.add(fighterOption);
		bg.add(tankOption);
		bg.add(all);
		all.setSelected(true);
	}
	
	private static void addDocumentListeners() {
		filterField.getDocument().addDocumentListener(new DocumentListener() {
	        public void changedUpdate(DocumentEvent e) {
	        	newFilter();
	        }
	        public void insertUpdate(DocumentEvent e) {
	        	newFilter();
	        }
	        public void removeUpdate(DocumentEvent e) {
	        	newFilter();
	        }
        });
    }
	
	private static void addMenuListeners() {
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		information.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				InfoPage.createInfoPage();
			}
		});
	}
	
	private static void addButtonListeners() {
		assassinOption.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
				try {
					filters.add(RowFilter.regexFilter("(?i)"+assassinOption.getText(), 6));
					filters.add(RowFilter.regexFilter("(?i)"+filterField.getText(), 0));
				 	RowFilter<Object,Object> comboFilter = RowFilter.andFilter(filters);
					sorter.setRowFilter(comboFilter);
			        for(int i = 0; i < ChampionData.champData.length; i++) {
			        	ButtonRenderer.champIcons[i] = null;
			        }
				} catch (java.util.regex.PatternSyntaxException e1) {
					return;
				}
		    }
		});
		marksmanOption.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
				try {
					filters.add(RowFilter.regexFilter("(?i)"+marksmanOption.getText(), 6));
					filters.add(RowFilter.regexFilter("(?i)"+filterField.getText(), 0));
				 	RowFilter<Object,Object> comboFilter = RowFilter.andFilter(filters);
					sorter.setRowFilter(comboFilter);
			        for(int i = 0; i < ChampionData.champData.length; i++) {
			        	ButtonRenderer.champIcons[i] = null;
			        }
				} catch (java.util.regex.PatternSyntaxException e1) {
					return;
				}
		    }
		});
		supportOption.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
				try {
					filters.add(RowFilter.regexFilter("(?i)"+supportOption.getText(), 6));
					filters.add(RowFilter.regexFilter("(?i)"+filterField.getText(), 0));
				 	RowFilter<Object,Object> comboFilter = RowFilter.andFilter(filters);
					sorter.setRowFilter(comboFilter);
			        for(int i = 0; i < ChampionData.champData.length; i++) {
			        	ButtonRenderer.champIcons[i] = null;
			        }
				} catch (java.util.regex.PatternSyntaxException e1) {
					return;
				}
		    }
		});
		mageOption.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
				try {
					filters.add(RowFilter.regexFilter("(?i)"+mageOption.getText(), 6));
					filters.add(RowFilter.regexFilter("(?i)"+filterField.getText(), 0));
				 	RowFilter<Object,Object> comboFilter = RowFilter.andFilter(filters);
					sorter.setRowFilter(comboFilter);
			        for(int i = 0; i < ChampionData.champData.length; i++) {
			        	ButtonRenderer.champIcons[i] = null;
			        }
				} catch (java.util.regex.PatternSyntaxException e1) {
					return;
				}
		    }
		});
		fighterOption.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
				try {
					filters.add(RowFilter.regexFilter("(?i)"+fighterOption.getText(), 6));
					filters.add(RowFilter.regexFilter("(?i)"+filterField.getText(), 0));
				 	RowFilter<Object,Object> comboFilter = RowFilter.andFilter(filters);
					sorter.setRowFilter(comboFilter);
			        for(int i = 0; i < ChampionData.champData.length; i++) {
			        	ButtonRenderer.champIcons[i] = null;
			        }
				} catch (java.util.regex.PatternSyntaxException e1) {
					return;
				}
		    }
		});
		tankOption.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
				try {
					filters.add(RowFilter.regexFilter("(?i)"+tankOption.getText(), 6));
					filters.add(RowFilter.regexFilter("(?i)"+filterField.getText(), 0));
				 	RowFilter<Object,Object> comboFilter = RowFilter.andFilter(filters);
					sorter.setRowFilter(comboFilter);
			        for(int i = 0; i < ChampionData.champData.length; i++) {
			        	ButtonRenderer.champIcons[i] = null;
			        }
				} catch (java.util.regex.PatternSyntaxException e1) {
					return;
				}
		    }
		});
		all.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
				try {
					filters.add(RowFilter.regexFilter("(?i)"+filterField.getText(), 0));
					RowFilter<Object,Object> comboFilter = RowFilter.andFilter(filters);
					sorter.setRowFilter(comboFilter);
			        for(int i = 0; i < ChampionData.champData.length; i++) {
			        	ButtonRenderer.champIcons[i] = null;
			        }
				} catch (java.util.regex.PatternSyntaxException e1) {
					return;
				}
		    }
		});
    }
	
	private static void newFilter() {
	    List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
	    try {
	        filters.add(RowFilter.regexFilter("(?i)"+filterField.getText(), 0));
	        
	        if(fighterOption.isSelected()) {
	        	filters.add(RowFilter.regexFilter("(?i)"+fighterOption.getText(), 6));
	        } else if(assassinOption.isSelected()) {
	        	filters.add(RowFilter.regexFilter("(?i)"+assassinOption.getText(), 6));
	        } else if(supportOption.isSelected()) {
	        	filters.add(RowFilter.regexFilter("(?i)"+supportOption.getText(), 6));
	        } else if(marksmanOption.isSelected()) {
	        	filters.add(RowFilter.regexFilter("(?i)"+marksmanOption.getText(), 6));
	        } else if(mageOption.isSelected()) {
	        	filters.add(RowFilter.regexFilter("(?i)"+mageOption.getText(), 6));
	        }else if(tankOption.isSelected()) {
	        	filters.add(RowFilter.regexFilter("(?i)"+tankOption.getText(), 6));
	        }
	        
	        RowFilter<Object,Object> comboFilter = RowFilter.andFilter(filters);
	        
	        sorter.setRowFilter(comboFilter);
	        for(int i = 0; i < ChampionData.champData.length; i++) {
	        	ButtonRenderer.champIcons[i] = null;
	        }
	    } catch (java.util.regex.PatternSyntaxException e) {
	        return;
	    }
	}
	
	private static void buildTableData() {
		ChampionData.parseChampData();
		
		for(int i = 0; i < 124; i++) {
			data[i][0] = ChampionData.champData[i][0];//name
			data[i][1] = (long)ChampionData.champData[i][22];//total dmg
			data[i][2] = (long)ChampionData.champData[i][23];//magic dmg
			data[i][3] = (long)ChampionData.champData[i][24];//physical dmg
			data[i][4] = (long)ChampionData.champData[i][25];//minions
			data[i][5] = (long)ChampionData.champData[i][26];//picked
			data[i][6] = ChampionData.champData[i][2];//invisible column role
			data[i][7] = (long)ChampionData.champData[i][27];//pentas
		}
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {				
            	buildTableData();
            	MainFrame frame = new MainFrame();
                frame.setVisible(true);
        		frame.setJMenuBar(mb);
        		addDocumentListeners();
        		addMenuListeners();
        		addButtonListeners();
            }
        });
    }
	
}