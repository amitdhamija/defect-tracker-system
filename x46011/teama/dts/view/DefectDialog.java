package x46011.teama.dts.view;
 
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import x46011.teama.dts.controller.DTSCommManager;
import x46011.teama.dts.model.Constants;
import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.DefectStatus;
import x46011.teama.dts.model.Person;

import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The DefectDialog class shows the Dialog to modify and/or assign the Defect.
 * 
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Amit Dhamija: added the components to the layout
 */
class DefectDialog extends JDialog implements PropertyChangeListener {
    
	private static final long serialVersionUID = 1L;
	
	private Defect defect;
	private DefectTrackerSystem dts;
	private DTSCommManager manager;
	private ArrayList<Person> users = new ArrayList<Person>();
	private ArrayList<DefectPriority> priorities = new ArrayList<DefectPriority>();
	private ArrayList<DefectStatus> statuses = new ArrayList<DefectStatus>();
	private Hashtable<String, Person> usersByName = new Hashtable<String, Person>();
	private JOptionPane optionPane;
    
    private JTextField textFieldSummary = new JTextField(Constants.TEXTFIELD_COLUMNS);
    private JComboBox comboBoxPriority;
    private JComboBox comboBoxStatus;
    private JComboBox comboBoxSubmitter;
    private JComboBox comboBoxAssignee;
    private JTextArea textAreaDescription = new JTextArea();
    
    private String btnSaveString = Constants.SAVE;
    private String btnCancelString = Constants.CANCEL;
    private String email = "";
    
    public DefectDialog(Frame frame, Defect defect, int action) {
        super(frame, true);
        
        if(defect == null) {
        	this.defect = new Defect();
        	setTitle(Constants.ADD_DEFECT);
        }
        else {
        	this.defect = defect;
        	setTitle(Constants.MODIFY_ASSIGN_DEFECT);
        }
        
        if(action == Constants.ACTION_ADD_DEFECT) {
			setTitle(Constants.ADD_DEFECT);
		}
		else if(action == Constants.ACTION_MODIFY_ASSIGN_DEFECT) {
			setTitle(Constants.MODIFY_ASSIGN_DEFECT);
		}
		
        manager = DefectTrackerSystem.getManager();
        users.clear();
        users.addAll(manager.getUsers());
        priorities.clear();
        priorities.addAll(manager.getPriorities());
        statuses.clear();
        statuses.addAll(manager.getStatuses());
        
        createAndInitializeUI();
        
        // Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                	// Instead of directly closing the window, change the JOptionPane's value property.
                    optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
            }
        });
        
        pack();
        setPreferredSize(new Dimension(Constants.DEFECT_DIALOG_SIZE_WIDTH, Constants.DEFECT_DIALOG_SIZE_HEIGHT));
        setResizable(false);
        setLocationRelativeTo(frame);
        setVisible(true);
    }
    
    public void createAndInitializeUI() {
    	int size = users.size();
		String[] names = new String[size];
		for(int i = 0; i < size; i++) {
			Person user = users.get(i);
			names[i] = user.getName();
			usersByName.put(user.getName(), user);
		}
		
		comboBoxSubmitter = new JComboBox(names);
		comboBoxAssignee = new JComboBox(names);
		
		comboBoxPriority = new JComboBox(priorities.toArray());
		comboBoxStatus = new JComboBox(statuses.toArray());
		
        //JLabel labelId = new JLabel(Constants.LABEL_DEFECT_ID);
        //JLabel labelDate = new JLabel(Constants.LABEL_DEFECT_DATE);
        JLabel labelSummary = new JLabel(Constants.LABEL_DEFECT_SUMMARY);
        JLabel labelPriority = new JLabel(Constants.LABEL_DEFECT_PRIORITY);
        JLabel labelStatus = new JLabel(Constants.LABEL_DEFECT_STATUS);
        JLabel labelSubmitter = new JLabel(Constants.LABEL_DEFECT_SUBMITTER);
        JLabel labelAssignee = new JLabel(Constants.LABEL_DEFECT_ASSIGNEE);
        JLabel labelDescription = new JLabel(Constants.LABEL_DEFECT_DESCRIPTION);
        JScrollPane scrollPaneDescription = new JScrollPane();
        
        scrollPaneDescription.setViewportView(textAreaDescription);
        textAreaDescription.setColumns(Constants.TEXTAREA_COLUMNS);
        textAreaDescription.setRows(Constants.TEXTAREA_ROWS);
        
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        
        panel.setLayout(layout);
        
        // Create an array of the components to be displayed.
        Object[] array = {panel};
        // Create an array specifying the number of dialog buttons and their text.
        Object[] options = {btnCancelString, btnSaveString};
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
        		layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup()
        				//.addComponent(labelId)
        				//.addComponent(labelDate)
        				.addComponent(labelSummary)
        				.addComponent(labelPriority)
        				.addComponent(labelStatus)
        				.addComponent(labelSubmitter)
        				.addComponent(labelAssignee)
        				.addComponent(labelDescription))
        				.addGroup(layout.createParallelGroup()
        						//.addComponent(textFieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						//.addComponent(textFieldDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textFieldSummary)
        						.addComponent(comboBoxPriority, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(comboBoxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(comboBoxSubmitter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(comboBoxAssignee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(scrollPaneDescription)));
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup()
        				//.addComponent(labelId)
        				//.addComponent(textFieldId))
        				//.addGroup(layout.createParallelGroup()
        						//.addComponent(labelDate)
                				//.addComponent(textFieldDate))
                		//.addGroup(layout.createParallelGroup()
                				.addComponent(labelSummary)
        						.addComponent(textFieldSummary))
        				.addGroup(layout.createParallelGroup()
        						.addComponent(labelPriority)
        						.addComponent(comboBoxPriority))
        				.addGroup(layout.createParallelGroup()
        						.addComponent(labelStatus)
        						.addComponent(comboBoxStatus))
        				.addGroup(layout.createParallelGroup()
                				.addComponent(labelSubmitter)
                				.addComponent(comboBoxSubmitter))
                		.addGroup(layout.createParallelGroup()
                				.addComponent(labelAssignee)
                				.addComponent(comboBoxAssignee))
                		.addGroup(layout.createParallelGroup()
                				.addComponent(labelDescription)
                				.addComponent(scrollPaneDescription)));
        
        // Create the JOptionPane.
        optionPane = new JOptionPane(array,
                                    JOptionPane.PLAIN_MESSAGE,
                                    JOptionPane.OK_CANCEL_OPTION,
                                    null,
                                    options,
                                    options[1]);
        
        
        //textFieldSummary.addActionListener(this);
        optionPane.addPropertyChangeListener(this);
        
        // Make this dialog display it.
        setContentPane(optionPane);
    }
    
    /** This method clears the dialog and clearAndHides it. */
    public void clearAndHide() {
    	setVisible(false);
    	dispose();
    }
 
    @Override
    public void propertyChange(PropertyChangeEvent e) {
    	String prop = e.getPropertyName();
 
        if (isVisible()
         && (e.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();
 
            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                // ignore reset
                return;
            }
            
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);
 
            if (btnSaveString.equals(value)) {
            	defect.setSubmitter(users.get(comboBoxSubmitter.getSelectedIndex()));
            	defect.setAssignee(users.get(comboBoxAssignee.getSelectedIndex()));
            	defect.setPriority(priorities.get(comboBoxPriority.getSelectedIndex()));
            	defect.setStatus(statuses.get(comboBoxStatus.getSelectedIndex()));
            	defect.setSummary(textFieldSummary.getText());
            	defect.setDescription(textAreaDescription.getText());
            	manager.createDefect(defect);
            	dts.getSingleInstance().updateTable();
            	clearAndHide();
            } else { // user closed dialog or clicked cancel
                clearAndHide();
            }
        }
    }
}