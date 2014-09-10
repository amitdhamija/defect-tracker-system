package x46011.teama.dts.view;

import java.awt.Container;
import java.awt.Dimension;
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

import x46011.teama.dts.controller.DTSCommManager;
import x46011.teama.dts.model.Constants;
import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.DefectStatus;
import x46011.teama.dts.model.DefectTableModel;
import x46011.teama.dts.model.Person;
import x46011.teama.dts.model.Status;

/**
 * The DefectTrackerSystem class runs the main thread and allows the user to navigate the application.
 * 
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Amit Dhamija: Added method to get single instance of this class
 * 					Amit Dhamija: Updated class to use DefectTableModel and set properties on tableDefectList object
 * 					Amit Dhamija: Added event listeners for table selection and buttons
 * @revision 1.2	Amit Dhamija: Added code to get Defect data from selected table row and pass it to ModifyAssignDialog
 * 					Amit Dhamija: Added code to use Dimension object and get frame size from Constants class
 * @revision 1.3	Amit Dhamija: Made changes to the placement of code for UI
 * @revision 1.4	Amit Dhamija: Added instance of the manager class
 */
public class DefectTrackerSystem {

	private static DefectTrackerSystem dts;
	private Defect defect;
	private DTSCommManager manager;
	
	private JFrame frame;
	private JTable tableDefectList;
	private JTextArea textAreaDescription;
    
    private ArrayList<Defect> defectList = new ArrayList<Defect>();
    
    /**
	 * Default constructor
	 */
	public DefectTrackerSystem() {
		
		// TODO: Remove test data
		// TODO: Check for null Defect data; report back to user; exit app?
		Person person1 = new Person(1, "Amit", "Dhamija", "amit@gmail.com");
		Person person2 = new Person(2, "Tavis", "Cribbet", "travis@gmail.com");
		Person person3 = new Person(3, "Kevin", "Alexander", "kevin@gmail.com");
		Person person4 = new Person(4, "Thomas", "Hargrove", "thomas@gmail.com");
		Person person5 = new Person(5, "Kesha", "Smith", "kesha@yahoo.com");
		Status status1 = new Status(1, DefectStatus.OPEN);
		Status status2 = new Status(1, DefectStatus.CLOSED);
		Status status3 = new Status(1, DefectStatus.RESOLVED);
		Date date1 = new Date();
		Defect defect1 = new Defect(1, date1,person1, person4, DefectPriority.HIGH, status1, "Summary1", "Description1");
		Defect defect2 = new Defect(1, date1,person2, person5, DefectPriority.MEDIUM, status3, "Summary2", "Description2");
		Defect defect3 = new Defect(1, date1,person3, person2, DefectPriority.HIGH, status1, "Summary3", "Description3");
		Defect defect4 = new Defect(1, date1,person4, person3, DefectPriority.LOW, status2, "Summary4", "Description4");
		Defect defect5 = new Defect(1, date1,person5, person1, DefectPriority.LOW, status2, "Summary5", "Description5");
		
		//defectList.add(defect1);
		//defectList.add(defect2);
		//defectList.add(defect3);
		//defectList.add(defect4);
		//defectList.add(defect5);
		//
		
		manager = new DTSCommManager();
	}
		
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	getSingleInstance().createAndShowUI();
		    }
		});
	}
	
	/**
	 * Get the manager instance
	 * @return the manager
	 */
	public static DTSCommManager getManager() {
		return getSingleInstance().manager;
	}
	
	/**
	 * Get the single instance of the application
	 */
	protected static DefectTrackerSystem getSingleInstance() {
        if (dts == null)
            dts = new DefectTrackerSystem();
        return dts;
    }
	
	public void createAndShowUI() {
		frame = new JFrame();
		frame.setTitle(Constants.DTS_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(Constants.DTS_FRAME_SIZE_WIDTH, Constants.DTS_FRAME_SIZE_HEIGHT));
		
		DefectTableModel defectTableModel = new DefectTableModel(defectList);
		tableDefectList = new JTable();
        tableDefectList.setModel(defectTableModel);
		tableDefectList.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableDefectList.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableDefectList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableDefectList.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableDefectList.getColumnModel().getColumn(4).setPreferredWidth(50);
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
        textAreaDescription.setColumns(Constants.TEXTAREA_COLUMNS);
        textAreaDescription.setRows(Constants.TEXTAREA_ROWS);
        textAreaDescription.setEditable(false);
        
        JScrollPane scrollPaneDescription = new JScrollPane();
        scrollPaneDescription.setViewportView(textAreaDescription);

        JButton buttonAddDefect = new JButton();
        buttonAddDefect.setText(Constants.ADD_DEFECT);
        buttonAddDefect.addActionListener(new java.awt.event.ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
                onAddDefectButtonClicked();
            }
        });

        JButton buttonModifyAssignDefect = new JButton();
        buttonModifyAssignDefect.setEnabled(false);
        buttonModifyAssignDefect.setText(Constants.MODIFY_ASSIGN_DEFECT);
        buttonModifyAssignDefect.addActionListener(new java.awt.event.ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
            	onModifyAssignButtonClicked(tableDefectList.getSelectedRow());
            }
        });

        JButton buttonEmailStatus = new JButton();
        buttonEmailStatus.setEnabled(false);
        buttonEmailStatus.setText(Constants.EMAIL_STATUS);
        buttonEmailStatus.addActionListener(new java.awt.event.ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
        		onEmailStatusButtonClicked(tableDefectList.getSelectedRow());
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
            .addComponent(labelDescription)
            .addComponent(scrollPaneDescription)
            .addGroup(layout.createSequentialGroup()
            		.addComponent(buttonAddDefect)
            		.addComponent(buttonModifyAssignDefect)
            		.addComponent(buttonEmailStatus)));
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(scrollPaneDefectList)
            .addComponent(labelDescription)
            .addComponent(scrollPaneDescription)
            .addGroup(layout.createParallelGroup()
            		.addComponent(buttonAddDefect)
            		.addComponent(buttonEmailStatus)
            		.addComponent(buttonModifyAssignDefect)));
        
        frame.setVisible(true);
        
        // TODO: Worker thread?
        // TODO: Move this in a post-data received method; also check if table contains any data
        defectList.clear();
        //defectList.addAll(manager.getAllDefects());
        
        if(defectList.size() > 0) {
        	tableDefectList.changeSelection(0, 0, false, false);
            buttonModifyAssignDefect.setEnabled(true);
            buttonEmailStatus.setEnabled(true);
        }
	}
	
	private void onRowSelected(int row) {
		defect = defectList.get(row);
		textAreaDescription.setText(defect.getDescription());
	}
	
	private void onAddDefectButtonClicked() {
		DefectDialog defectDialog = new DefectDialog(frame, null, Constants.ACTION_ADD_DEFECT);
	}
	
	private void onModifyAssignButtonClicked(int row) {
		defect = defectList.get(row);
		DefectDialog defectDialog = new DefectDialog(frame, defect, Constants.ACTION_MODIFY_ASSIGN_DEFECT);
	}

	private void onEmailStatusButtonClicked(int row) {
		defect = defectList.get(row);
		EmailDialog emailDialog = new EmailDialog(frame, defect);
	}
}