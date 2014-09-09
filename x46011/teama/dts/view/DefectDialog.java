package x46011.teama.dts.view;
 
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import x46011.teama.dts.model.Constants;
import x46011.teama.dts.model.Defect;
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
 */
class DefectDialog extends JDialog implements ActionListener, PropertyChangeListener, ItemListener {
    
	private static final long serialVersionUID = 1L;
	
	private Defect defect;
	private JOptionPane optionPane;
    
    private JTextField textFieldDefectId = new JTextField();
    private JTextField textFieldDefectDate = new JTextField();
    private JTextField textFieldDefectSummary = new JTextField();
    private JComboBox comboBoxDefectPriority = new JComboBox();
    private JComboBox comboBoxDefectStatus = new JComboBox();
    private JComboBox comboBoxDefectSubmitter = new JComboBox();
    private JComboBox comboBoxDefectOwner = new JComboBox();
    private JTextField textFieldDefectDescription = new JTextField();
    
    private String btnSaveString = Constants.SAVE;
    private String btnCancelString = Constants.CANCEL;
    private String email = "";
    
    public DefectDialog(Frame frame, Defect defect, int action) {
        super(frame, true);
        
        this.defect = defect;
        
        // TODO: Remove test data; use getUsers() from manager
		
		if(action == Constants.ACTION_ADD_DEFECT) {
			setTitle(Constants.ADD_DEFECT);
		}
		else if(action == Constants.ACTION_MODIFY_ASSIGN_DEFECT) {
			setTitle(Constants.MODIFY_ASSIGN_DEFECT);
		}
		
        setPreferredSize(new Dimension(Constants.DEFECT_DIALOG_SIZE_WIDTH, Constants.DEFECT_DIALOG_SIZE_HEIGHT));
        setResizable(false);
		createDialogUI();
        
        // Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                	// Instead of directly closing the window, change the JOptionPane's value property.
                    optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
            }
        });
        
        comboBoxDefectPriority.addItemListener(this);
        comboBoxDefectStatus.addItemListener(this);
        comboBoxDefectSubmitter.addItemListener(this);
        comboBoxDefectOwner.addItemListener(this);
        textFieldDefectDescription.addActionListener(this);
        
        optionPane.addPropertyChangeListener(this);
        
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }
    
    public void createDialogUI() {
        JLabel labelDefectId = new JLabel(Constants.LABEL_DEFECT_ID);
        JLabel labelDefectDate = new JLabel(Constants.LABEL_DEFECT_DATE);
        JLabel labelDefectSummary = new JLabel(Constants.LABEL_DEFECT_SUMMARY);
        JLabel labelDefectPriority = new JLabel(Constants.LABEL_DEFECT_PRIORITY);
        JLabel labelDefectStatus = new JLabel(Constants.LABEL_DEFECT_STATUS);
        JLabel labelDefectSubmitter = new JLabel(Constants.LABEL_DEFECT_SUBMITTER);
        JLabel labelDefectAssignee = new JLabel(Constants.LABEL_DEFECT_ASSIGNEE);
        JLabel labelDefectDescription = new JLabel(Constants.LABEL_DEFECT_DESCRIPTION);
        
        //comboBox, textField initialize, setText, size code goes here; 
        
        
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
        				.addComponent(labelDefectId)
        				.addComponent(labelDefectSummary)
        				.addComponent(labelDefectPriority)
        				.addComponent(labelDefectSubmitter)
        				.addComponent(labelDefectDescription))
        				.addGroup(layout.createParallelGroup()
        						.addComponent(textFieldDefectId)
        						.addComponent(textFieldDefectSummary)
        						.addComponent(comboBoxDefectPriority)
        						.addComponent(comboBoxDefectSubmitter)
        						.addComponent(textFieldDefectDescription)));
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup()
        				.addComponent(labelDefectId)
        				.addComponent(textFieldDefectId))
        				.addGroup(layout.createParallelGroup()
        						.addComponent(labelDefectSummary)
        						.addComponent(textFieldDefectSummary))
        				.addGroup(layout.createParallelGroup()
        						.addComponent(labelDefectPriority)
        						.addComponent(comboBoxDefectPriority))
        						.addGroup(layout.createParallelGroup()
                						.addComponent(labelDefectSubmitter)
                						.addComponent(comboBoxDefectSubmitter))
                						.addGroup(layout.createParallelGroup()
                								.addComponent(labelDefectDescription)
                								.addComponent(textFieldDefectDescription)));
        
        // Create the JOptionPane.
        optionPane = new JOptionPane(array,
                                    JOptionPane.PLAIN_MESSAGE,
                                    JOptionPane.OK_CANCEL_OPTION,
                                    null,
                                    options,
                                    options[1]);
        
        // Make this dialog display it.
        setContentPane(optionPane);
    }
    
    /** This method clears the dialog and clearAndHides it. */
    public void clearAndHide() {
    	setVisible(false);
    	dispose();
    }
    
	@Override
	public void itemStateChanged(ItemEvent e) {
		String name = e.getItem().toString();
		//email = emails.get(name);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnSaveString);
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
            	// TODO: send email
            	//System.out.println("Email: " + email);
            	clearAndHide();
            } else { // user closed dialog or clicked cancel
                clearAndHide();
            }
        }
    }
}