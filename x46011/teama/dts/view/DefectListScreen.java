package x46011.teama.dts.view;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DefectListScreen {

	public static final String CURRENCY_CONVERTER = "Currency Converter App";
	public static final String EURO = "EUR";
	public static final String BRITISH_POUND = "GBP";
	public static final String INDIAN_RUPEE = "INR";
	
	private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
	
	private JLabel labelCurrency = new JLabel(CURRENCY_CONVERTER);
	private JTextField textFieldCurrencyValue = new JTextField("1.00");
	
	public DefectListScreen() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	DefectListScreen dls = new DefectListScreen();
				dls.display();
		    }
		});
	}

	public void display() {
		JFrame frame = new JFrame();
		
		JPanel pageStartPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel pageEndPanel = new JPanel();
		
		JLabel labelUSD = new JLabel("USD $");
		
		JButton buttonConvert = new JButton("Convert");
		
		textFieldCurrencyValue.setPreferredSize(new Dimension(90,20));
		labelCurrency.setForeground(Color.BLUE);
		
		pageStartPanel.add(labelCurrency);
		centerPanel.setBackground(Color.LIGHT_GRAY);
		centerPanel.add(labelUSD);
		centerPanel.add(textFieldCurrencyValue);
		centerPanel.add(buttonConvert);
		pageEndPanel.add(buttonConvert);
		
		Container pane = frame.getContentPane();
		pane.add(pageStartPanel, BorderLayout.PAGE_START);
		pane.add(centerPanel, BorderLayout.CENTER);
		pane.add(pageEndPanel, BorderLayout.PAGE_END);
		
		//buttonConvert.addActionListener(new CustomButtonListener());
		
		frame.setTitle(CURRENCY_CONVERTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 120);
		frame.setResizable(false);
		frame.setVisible(true);
		
	    

	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();
	        jLabel1 = new javax.swing.JLabel();
	        jScrollPane3 = new javax.swing.JScrollPane();
	        jTextArea1 = new javax.swing.JTextArea();
	        jButton1 = new javax.swing.JButton();
	        jButton2 = new javax.swing.JButton();
	        jButton4 = new javax.swing.JButton();
	        menuBar = new javax.swing.JMenuBar();
	        fileMenu = new javax.swing.JMenu();
	        openMenuItem = new javax.swing.JMenuItem();
	        saveMenuItem = new javax.swing.JMenuItem();
	        saveAsMenuItem = new javax.swing.JMenuItem();
	        exitMenuItem = new javax.swing.JMenuItem();
	        editMenu = new javax.swing.JMenu();
	        cutMenuItem = new javax.swing.JMenuItem();
	        copyMenuItem = new javax.swing.JMenuItem();
	        pasteMenuItem = new javax.swing.JMenuItem();
	        deleteMenuItem = new javax.swing.JMenuItem();
	        helpMenu = new javax.swing.JMenu();
	        contentsMenuItem = new javax.swing.JMenuItem();
	        aboutMenuItem = new javax.swing.JMenuItem();

	        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {"0001", "Defect Summary", "8/20/2014", "HIGH", "OPEN", "Thomas", "Amit"},
	                {"0002", "Defect Summary 2", "8/20/2014", "MEDIUM", "RESOLVED", "Amit", "Kevin"},
	                {"0003", "Defect Summary 3", "8/20/2014", "MEDIUM", "SUBMITTED", "Kevin", "<UNASSIGNED>"},
	                {"0004", "Defect Summary 4", "8/20/2014", "LOW", "OPEN", "Travis", "Travis"},
	                {"0005", "Defect Summary 5", "8/20/2014", "HIGH", "CLOSED", "Kevin", "Thomas"}
	            },
	            new String [] {
	                "ID", "Title/Summary", "Date", "Priority", "Status", "Submitted By", "Owner"
	            }
	        ) {
	            Class[] types = new Class [] {
	                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }
	        });
	        jScrollPane1.setViewportView(jTable1);

	        jLabel1.setText("Description:");

	        jTextArea1.setColumns(20);
	        jTextArea1.setRows(5);
	        jTextArea1.setText("Description 1");
	        jScrollPane3.setViewportView(jTextArea1);

	        jButton1.setText("New Defect");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //jButton1ActionPerformed(evt);
	            }
	        });

	        jButton2.setText("Modify/Assign");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //jButton2ActionPerformed(evt);
	            }
	        });

	        jButton4.setText("Email");


	        

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
	        frame.getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(jLabel1)
	                .addGap(0, 0, Short.MAX_VALUE))
	            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jButton1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(jButton2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jButton4))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jButton1)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jButton4)
	                        .addComponent(jButton2)))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	    
	}
}
