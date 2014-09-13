package x46011.teama.dts.model;

/**
 * The Constants class holds all the constants used by the application.
 * 
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Amit Dhamija: Added additional constants
 * @revision 1.2	Amit Dhamija: Added more constants
 */
public final class Constants {

	public static final int DTS_FRAME_SIZE_WIDTH = 850;
	public static final int DTS_FRAME_SIZE_HEIGHT = 550;
	
	public static final int EMAIL_DIALOG_SIZE_WIDTH = 450;
	public static final int EMAIL_DIALOG_SIZE_HEIGHT = 200;
	
	public static final int DEFECT_DIALOG_SIZE_WIDTH = 450;
	public static final int DEFECT_DIALOG_SIZE_HEIGHT = 350;
	
	public static final int TEXTFIELD_COLUMNS = 10;
	public static final int TEXTAREA_COLUMNS = 20;
	public static final int TEXTAREA_ROWS = 5;
	
	public static final int ACTION_ADD_DEFECT = 1;
	public static final int ACTION_MODIFY_ASSIGN_DEFECT = 2;
	
	public static final String COLON = ": ";
	public static final String DTS_TITLE = "Defect Tracker System";
	
	public static final String DEFECT_ID = "Defect ID";
	public static final String DEFECT_DATE = "Date";
	public static final String DEFECT_SUMMARY = "Summary";
	public static final String DEFECT_PRIORITY = "Priority";
	public static final String DEFECT_STATUS = "Status";
	public static final String DEFECT_SUBMITTER = "Submitter";
	public static final String DEFECT_ASSIGNEE = "Assignee";
	public static final String DEFECT_DESCRIPTION = "Description";
	
	public static final String ADD_DEFECT = "Add Defect";
	public static final String MODIFY_ASSIGN_DEFECT = "Modify/Assign Defect";
	public static final String DEFECT = "Defect";
	public static final String LABEL_DEFECT_ID = DEFECT_ID + COLON;
	public static final String LABEL_DEFECT_DATE = DEFECT_DATE + COLON;
	public static final String LABEL_DEFECT_SUMMARY = DEFECT_SUMMARY + COLON;
	public static final String LABEL_DEFECT_PRIORITY = DEFECT_PRIORITY + COLON;
	public static final String LABEL_DEFECT_STATUS = DEFECT_STATUS + COLON;
	public static final String LABEL_DEFECT_SUBMITTER = DEFECT_SUBMITTER + COLON;
	public static final String LABEL_DEFECT_ASSIGNEE = DEFECT_ASSIGNEE + COLON;
	public static final String LABEL_DEFECT_DESCRIPTION = DEFECT_DESCRIPTION + COLON;
	public static final String SAVE = "Save";
	
	public static final String ADD_USER = "Add User";
	public static final String LABEL_FIRST_NAME = "First Name" + COLON;
	public static final String LABEL_LAST_NAME = "Last Name" + COLON;
	public static final String LABEL_EMAIL_ADDRESS = "Email Address" + COLON;
	public static final String ADD = "Add";
	
	public static final String EMAIL_STATUS = "Email Status";
	public static final String LABEL_RECIPIENT = "Recipient" + COLON;
	public static final String SEND_EMAIL = "Send";
	
	public static final String CANCEL = "Cancel";
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/";
	public static final String DB_NAME = "defecttracker";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "";
}