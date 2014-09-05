package x46011.teama.dts.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import x46011.teama.dts.model.Constants;

/**
 * The DefectTrackerSystem class runs the main thread and allows the user to navigate the application.
 * 
 * @author Amit Dhamija
 * @version 1.0
 */
public class DefectTrackerSystem {

    private JScrollPane scrollPaneDefectList;
    private JScrollPane scrollPaneDescription;
    private JTable tableDefectList;
    private JTextArea textAreaDescription;
    private JButton buttonAddDefect;
    private JButton buttonModifyAssignDefect;
    private JButton buttonEmailStatus;
    private JLabel labelDescription;
    
	public DefectTrackerSystem() {
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	DefectTrackerSystem app = new DefectTrackerSystem();
				app.display();
		    }
		});
	}

	public void display() {
		JFrame frame = new JFrame();
		frame.setTitle(Constants.DTS_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(850, 550);
		frame.setResizable(false);
		
		Container pane = frame.getContentPane();
		
		scrollPaneDefectList = new JScrollPane();
        tableDefectList = new JTable();
        labelDescription = new JLabel();
        scrollPaneDescription = new JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        buttonAddDefect = new JButton();
        buttonModifyAssignDefect = new JButton();
        buttonEmailStatus = new JButton();
		
        tableDefectList.setModel(new DefaultTableModel(
	            new Object [][] {
	                {"0001", "Defect Summary", "8/20/2014", "HIGH", "OPEN", "Thomas", "Amit"},
	                {"0002", "Defect Summary 2", "8/20/2014", "MEDIUM", "RESOLVED", "Amit", "Kevin"},
	                {"0003", "Defect Summary 3", "8/20/2014", "MEDIUM", "SUBMITTED", "Kevin", "<UNASSIGNED>"},
	                {"0004", "Defect Summary 4", "8/20/2014", "LOW", "OPEN", "Travis", "Travis"},
	                {"0005", "Defect Summary 5", "8/20/2014", "HIGH", "CLOSED", "Kevin", "Thomas"}
	            },
	            new String [] {
	                Constants.DEFECT_ID, Constants.DEFECT_DATE, Constants.DEFECT_REPORTER, Constants.DEFECT_ASSIGNEE, Constants.DEFECT_PRIORITY, Constants.DEFECT_STATUS, Constants.DEFECT_SUMMARY
	            }
	        ) {
	            Class[] types = new Class [] {
	                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }
	        });
	        scrollPaneDefectList.setViewportView(tableDefectList);

	        labelDescription.setText(Constants.LABEL_DEFECT_DESCRIPTION);
	        textAreaDescription.setColumns(20);
	        textAreaDescription.setRows(5);
	        textAreaDescription.setText("Description 1");
	        scrollPaneDescription.setViewportView(textAreaDescription);

	        buttonAddDefect.setText(Constants.ADD_DEFECT);
	        buttonAddDefect.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //buttonAddDefectActionPerformed(evt);
	            }
	        });

	        buttonModifyAssignDefect.setText(Constants.MODIFY_ASSIGN_DEFECT);
	        buttonModifyAssignDefect.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //buttonModifyAssignDefectActionPerformed(evt);
	            }
	        });

	        buttonEmailStatus.setText(Constants.EMAIL_STATUS);
	        buttonEmailStatus.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //buttonEmailStatusActionPerformed(evt);
	            }
	        });

	        GroupLayout layout = new GroupLayout(pane);
	        pane.setLayout(layout);
	        layout.setAutoCreateGaps(true);
	        layout.setAutoCreateContainerGaps(true);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup()
	            .addComponent(scrollPaneDefectList)
	            .addGroup(layout.createParallelGroup()
	            		.addComponent(labelDescription))
	            		.addComponent(scrollPaneDescription)
	            		.addGroup(layout.createSequentialGroup()
	            				.addComponent(buttonAddDefect)
	            				.addComponent(buttonModifyAssignDefect)
	            				.addComponent(buttonEmailStatus))
	        );
	        layout.setVerticalGroup(
	            layout.createSequentialGroup()
	            .addComponent(scrollPaneDefectList)
	            .addComponent(labelDescription)
	            .addComponent(scrollPaneDescription)
	            .addGroup(layout.createParallelGroup()
	            		.addComponent(buttonAddDefect)
	            		.addComponent(buttonEmailStatus)
	            		.addComponent(buttonModifyAssignDefect))
	        );
		
		frame.setVisible(true);
	}
}