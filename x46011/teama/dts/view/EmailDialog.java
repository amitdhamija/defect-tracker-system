package x46011.teama.dts.view;
 
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JPanel;

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

class EmailDialog extends JDialog implements ActionListener, PropertyChangeListener, ItemListener {
    
	private static final long serialVersionUID = 1L;
	
	private Defect defect;
	private ArrayList<Person> recipients = new ArrayList<Person>();
	private Hashtable<String, String> emails = new Hashtable<String, String>();
	private JOptionPane optionPane;
    private JComboBox<String> recipientComboBox;
    
    private String btnSendString = Constants.SEND_EMAIL;
    private String btnCancelString = Constants.CANCEL;
    private String email = "";
    
    public EmailDialog(Frame frame, Defect defect) {
        super(frame, true);
        
        this.defect = defect;
        
        // TODO: Remove test data; use getUsers() from manager
        Person person1 = new Person("Amit Dhamija", "amit@gmail.com");
		Person person2 = new Person("Tavis Cribbet", "travis@gmail.com");
		Person person3 = new Person("Kevin Alexander", "kevin@gmail.com");
		Person person4 = new Person("Thomas Hargrove", "thomas@gmail.com");
		Person person5 = new Person("Kesha Smith", "kesha@yahoo.com");
		recipients.add(person1);
		recipients.add(person2);
		recipients.add(person3);
		recipients.add(person4);
		recipients.add(person5);
        //
		
		int size = recipients.size();
		String[] names = new String[size];
		
		for(int i = 0; i < size; i++) {
			Person recipient = recipients.get(i);
			names[i] = recipient.getName();
			emails.put(recipient.getName(), recipient.getEmail());
		}

		recipientComboBox = new JComboBox<>(names);
		email = emails.get(names[0]);
		
		display();
        
        // Handle window closing correctly.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                	// Instead of directly closing the window, change the JOptionPane's value property.
                    optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
            }
        });
        
        recipientComboBox.addItemListener(this);
        
        // Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }
    
    public void display() {
        JLabel labelDefectId = new JLabel(Constants.LABEL_DEFECT_ID);
        JLabel labelDefectSummary = new JLabel(Constants.LABEL_DEFECT_SUMMARY);
        JLabel labelDefectStatus = new JLabel(Constants.LABEL_DEFECT_STATUS);
        JLabel labelDefectIdValue = new JLabel(String.valueOf(defect.getId()));
        JLabel labelDefectSummaryValue = new JLabel(defect.getSummary());
        JLabel labelDefectStatusValue = new JLabel(defect.getStatus().toString());
        JLabel labelRecipient = new JLabel(Constants.LABEL_RECIPIENT);
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        
        // Create an array of the components to be displayed.
        Object[] array = {panel};
        // Create an array specifying the number of dialog buttons and their text.
        Object[] options = {btnCancelString, btnSendString};
        
        setTitle(Constants.EMAIL_STATUS);
        setPreferredSize(new Dimension(Constants.DTS_DIALOG_SIZE_WIDTH, Constants.DTS_DIALOG_SIZE_HEIGHT));
        
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
        						.addComponent(recipientComboBox)));
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
                						.addComponent(recipientComboBox)));
        
        panel.setLayout(layout);
        
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
		email = emails.get(name);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnSendString);
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
            	// TODO: send email
            	System.out.println("Email: " + email);
            	clearAndHide();
            } else { // user closed dialog or clicked cancel
                clearAndHide();
            }
        }
    }
}