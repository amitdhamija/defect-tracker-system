package x46011.teama.dts.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
import x46011.teama.dts.model.DefectTableModel;

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
 * @revision 1.5	Amit Dhamija: Added logic to update the UI table when the data is added/updated
 */
public class DefectTrackerSystem {

	private static DefectTrackerSystem dts;
	private DTSCommManager manager;
	private JFrame frame;
	private JTable tableDefectList;
	private JTextArea textAreaDescription;
    
    private ArrayList<Defect> defectList = new ArrayList<Defect>();
    
    /**
	 * Default constructor
	 */
	public DefectTrackerSystem() {
		
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
		
		tableDefectList = new JTable();
        
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
        //buttonModifyAssignDefect.setEnabled(false);
        buttonModifyAssignDefect.setText(Constants.MODIFY_ASSIGN_DEFECT);
        buttonModifyAssignDefect.addActionListener(new java.awt.event.ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
            	onModifyAssignButtonClicked(tableDefectList.getSelectedRow());
            }
        });

        JButton buttonEmailStatus = new JButton();
        //buttonEmailStatus.setEnabled(false);
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
        setDefectListTableModel();
        
        tableDefectList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() ) {
                    onRowSelected(tableDefectList.getSelectedRow());
                }
            }
        });
        
        tableDefectList.changeSelection(0, 0, false, false);
	}
	
	public void updateDefectList(int id) {
		setDefectListTableModel();
		if(defectList.size() > 0) {
			if (id == -1)
				tableDefectList.changeSelection(defectList.size() - 1, 0, false, false);
			else
				tableDefectList.changeSelection(id - 1, 0, false, false);
		}
	}
	
	private void setDefectListTableModel() {
		defectList.clear();
		defectList.addAll(manager.getDefects());
		DefectTableModel defectTableModel = new DefectTableModel(defectList);
        tableDefectList.setModel(defectTableModel);
        tableDefectList.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableDefectList.getColumnModel().getColumn(1).setPreferredWidth(75);
        tableDefectList.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableDefectList.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableDefectList.getColumnModel().getColumn(4).setPreferredWidth(50);
        tableDefectList.getColumnModel().getColumn(5).setPreferredWidth(50);
        tableDefectList.getColumnModel().getColumn(6).setPreferredWidth(250);
        tableDefectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void onRowSelected(int row) {
		if (row != -1)
			textAreaDescription.setText(defectList.get(row).getDescription());
	}
	
	private void onAddDefectButtonClicked() {
		DefectDialog defectDialog = new DefectDialog(frame, null);
	}
	
	private void onModifyAssignButtonClicked(int row) {
		DefectDialog defectDialog = new DefectDialog(frame, defectList.get(row));
	}

	private void onEmailStatusButtonClicked(int row) {
		EmailDialog emailDialog = new EmailDialog(frame, defectList.get(row));
	}
}