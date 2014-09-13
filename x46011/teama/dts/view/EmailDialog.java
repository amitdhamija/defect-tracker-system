package x46011.teama.dts.view;
 
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import x46011.teama.dts.controller.DTSCommManager;
import x46011.teama.dts.model.Constants;
import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.Person;

/**
 * The EmailDialog class shows the Dialog to email Defect status to the recipient.
 * 
 * @author Amit Dhamija
 * @author Kevin Alexander
 * @version 1.0
 * @revision 1.1	Amit Dhamija: Made changes to the placement of code for UI
 * @revision 1.2	Amit Dhamija: Structured code similar to Defect class
 * 					Amit Dhamija: Made changes to use single instance of the manager and use users array to get the email based on selectedIndex
 */
class EmailDialog extends JDialog implements PropertyChangeListener {
    
	private static final long serialVersionUID = 1L;
	
	private DTSCommManager manager;
	private Defect defect;
	private ArrayList<Person> recipients = new ArrayList<Person>();
	private JOptionPane optionPane;
    private JComboBox comboBoxRecipient;
    
    private String btnSendString = Constants.SEND_EMAIL;
    private String btnCancelString = Constants.CANCEL;
    private String email = "";
    
    public EmailDialog(Frame frame, Defect defect) {
        super(frame, true);
        
        this.defect = defect;
        fetchUIData();
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
        setPreferredSize(new Dimension(Constants.EMAIL_DIALOG_SIZE_WIDTH, Constants.EMAIL_DIALOG_SIZE_HEIGHT));
        setResizable(false);
        setLocationRelativeTo(frame);
        setVisible(true);
    }
    
    private void fetchUIData() {
    	manager = DefectTrackerSystem.getManager();
        recipients.clear();
        recipients.addAll(manager.getUsers());
    }
    
    private void createAndInitializeUI() {
    	setTitle(Constants.EMAIL_STATUS);
    	JLabel labelDefectId = new JLabel(Constants.LABEL_DEFECT_ID);
        JLabel labelDefectSummary = new JLabel(Constants.LABEL_DEFECT_SUMMARY);
        JLabel labelDefectStatus = new JLabel(Constants.LABEL_DEFECT_STATUS);
        JLabel labelRecipient = new JLabel(Constants.LABEL_RECIPIENT);
        JLabel labelDefectIdValue = new JLabel(String.valueOf(defect.getId()));
        JLabel labelDefectSummaryValue = new JLabel(defect.getSummary());
        JLabel labelDefectStatusValue = new JLabel(defect.getStatus().name());
        
    	int size = recipients.size();
		String[] names = new String[size];
		
		for(int i = 0; i < size; i++) {
			Person recipient = recipients.get(i);
			names[i] = recipient.getName();
		}
		
		comboBoxRecipient = new JComboBox(names);
    	String assigneeName = defect.getAssignee().getName();
    	comboBoxRecipient.setSelectedItem(assigneeName);
        
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        
        panel.setLayout(layout);
        
        // Create an array of the components to be displayed.
        Object[] array = {panel};
        // Create an array specifying the number of dialog buttons and their text.
        Object[] options = {btnCancelString, btnSendString};
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
        		layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup()
        				.addComponent(labelDefectId)
        				.addComponent(labelDefectSummary)
        				.addComponent(labelDefectStatus)
        				.addComponent(labelRecipient))
        				.addGroup(layout.createParallelGroup()
        						.addComponent(labelDefectIdValue)
        						.addComponent(labelDefectSummaryValue)
        						.addComponent(labelDefectStatusValue)
        						.addComponent(comboBoxRecipient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup()
        				.addComponent(labelDefectId)
        				.addComponent(labelDefectIdValue))
        				.addGroup(layout.createParallelGroup()
        						.addComponent(labelDefectSummary)
        						.addComponent(labelDefectSummaryValue))
        				.addGroup(layout.createParallelGroup()
        						.addComponent(labelDefectStatus)
        						.addComponent(labelDefectStatusValue))
        						.addGroup(layout.createParallelGroup()
                						.addComponent(labelRecipient)
                						.addComponent(comboBoxRecipient)));
        
        // Create the JOptionPane.
        optionPane = new JOptionPane(array,
                                    JOptionPane.PLAIN_MESSAGE,
                                    JOptionPane.OK_CANCEL_OPTION,
                                    null,
                                    options,
                                    options[1]);
        
        // Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        
        // Make this dialog display it.
        setContentPane(optionPane);
    }
    
    /** This method clears the dialog and clearAndHides it. */
    private void clearAndHide() {
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
 
            if (btnSendString.equals(value)) {
            	email = recipients.get(comboBoxRecipient.getSelectedIndex()).getEmail();
            	sendEmail();
            	clearAndHide();
            } else { // user closed dialog or clicked cancel
                clearAndHide();
            }
        }
    }
    
    private void sendEmail()
    {
    	ArrayList<String> emailAddresses = new ArrayList<String>();
    	emailAddresses.add(email);
    	manager.emailUsers(defect, emailAddresses);
    }
}