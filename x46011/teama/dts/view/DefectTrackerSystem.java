package x46011.teama.dts.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import x46011.teama.dts.model.Constants;
import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.DefectStatus;
import x46011.teama.dts.model.DefectTableModel;
import x46011.teama.dts.model.Person;

/**
 * The DefectTrackerSystem class runs the main thread and allows the user to navigate the application.
 * 
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Added method to get single instance of this class
 * 					Updated class to use DefectTableModel and set properties on tableDefectList object
 * 					Added event listeners for table selection and buttons
 */
public class DefectTrackerSystem {

	private static DefectTrackerSystem dts;
	private JTable tableDefectList;
	private JTextArea textAreaDescription; 
    
    private ArrayList<Defect> defectList = new ArrayList<Defect>();
    
    /**
	 * Default constructor
	 */
	public DefectTrackerSystem() {
		
		// TODO: Remove test data
		Person person1 = new Person("Amit Dhamija", "amit@gmail.com");
		Person person2 = new Person("Tavis Cribbet", "travis@gmail.com");
		Person person3 = new Person("Kevin Alexander", "kevin@gmail.com");
		Person person4 = new Person("Thomas Hargrove", "thomas@gmail.com");
		Person person5 = new Person("Kesha Smith", "kesha@yahoo.com");
		
		Date date1 = new Date();
		Defect defect1 = new Defect(1, date1,person1, person4, DefectPriority.HIGH, DefectStatus.OPEN, "Summary1", "Description1");
		Defect defect2 = new Defect(1, date1,person2, person5, DefectPriority.MEDIUM, DefectStatus.RESOLVED, "Summary2", "Description2");
		Defect defect3 = new Defect(1, date1,person3, person2, DefectPriority.HIGH, DefectStatus.OPEN, "Summary3", "Description3");
		Defect defect4 = new Defect(1, date1,person4, person3, DefectPriority.LOW, DefectStatus.CLOSED, "Summary4", "Description4");
		Defect defect5 = new Defect(1, date1,person5, person1, DefectPriority.LOW, DefectStatus.CLOSED, "Summary5", "Description5");
		
		defectList.add(defect1);
		defectList.add(defect2);
		defectList.add(defect3);
		defectList.add(defect4);
		defectList.add(defect5);
		//
	}
		
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	getSingleInstance().display();
		    }
		});
	}
	
	/**
	 * Get the single instance of the application
	 */
	protected static DefectTrackerSystem getSingleInstance() {
        if (dts == null)
            dts = new DefectTrackerSystem();
        return dts;
    }
	
	public void display() {
		JFrame frame = new JFrame();
		frame.setTitle(Constants.DTS_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 550);
		
		DefectTableModel defectTableModel = new DefectTableModel(defectList);
		tableDefectList = new JTable();
        tableDefectList.setModel(defectTableModel);
        tableDefectList.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableDefectList.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableDefectList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableDefectList.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableDefectList.getColumnModel().getColumn(4).setPreferredWidth(25);
        tableDefectList.getColumnModel().getColumn(5).setPreferredWidth(50);
        tableDefectList.getColumnModel().getColumn(6).setPreferredWidth(225);
        tableDefectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableDefectList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() ) {
                    onRowSelected(tableDefectList.getSelectedRow());
                }
            }
        });
        
        JScrollPane scrollPaneDefectList = new JScrollPane();
        scrollPaneDefectList.setViewportView(tableDefectList);
        
        JLabel labelDescription = new JLabel();
        labelDescription.setText(Constants.LABEL_DEFECT_DESCRIPTION);
        
        textAreaDescription = new JTextArea();
        textAreaDescription.setColumns(20);
        textAreaDescription.setRows(5);
        
        JScrollPane scrollPaneDescription = new JScrollPane();
        scrollPaneDescription.setViewportView(textAreaDescription);

        JButton buttonAddDefect = new JButton();
        buttonAddDefect.setText(Constants.ADD_DEFECT);
        buttonAddDefect.addActionListener(new java.awt.event.ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
                onAddDefectButtonClicked(event);
            }
        });

        JButton buttonModifyAssignDefect = new JButton();
        buttonModifyAssignDefect.setText(Constants.MODIFY_ASSIGN_DEFECT);
        buttonModifyAssignDefect.addActionListener(new java.awt.event.ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
            	onModifyAssignButtonClicked(event);
            }
        });

        JButton buttonEmailStatus = new JButton();
        buttonEmailStatus.setText(Constants.EMAIL_STATUS);
        buttonEmailStatus.addActionListener(new java.awt.event.ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
        		onEmailStatusButtonClicked(event);
            }
        });

        Container pane = frame.getContentPane();
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
        
        // TODO: Worker thread?
        // TODO: Move this for post-data received; also check if table contains any data
        tableDefectList.changeSelection(0, 0, false, false);
        buttonModifyAssignDefect.setEnabled(true);
        buttonEmailStatus.setEnabled(true);
	}
	
	private void onRowSelected(int row) {
		textAreaDescription.setText(defectList.get(row).getDescription());
	}
	
	private void onAddDefectButtonClicked(ActionEvent event) {
		
	}
	
	private void onModifyAssignButtonClicked(ActionEvent event) {
		
	}

	private void onEmailStatusButtonClicked(ActionEvent event) {
	
	}
}