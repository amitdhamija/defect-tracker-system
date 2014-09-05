package x46011.teama.dts.controller;

import java.util.ArrayList;

import x46011.teama.dts.db.IDatabaseCommunications;
import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.Person;
import x46011.teama.dts.controller.IDefectTrackerScreen;



public class DefectTrackerSystemManager implements
		IDefectTrackerSystemManager {

	DefectTrackerSystemHost host;
	IDefectTrackerScreen screen;
	IDatabaseCommunications dbCommunicator;
	Defect currentDefect;
	Person currentUser;
	
	/**
	 * Void constructor for DefectTrackingSystemManager class
	 * @param DefectTrackerSystemHost provides reference to host so manager can return control upon logout
	 * @return None
	 */
	public DefectTrackerSystemManager(DefectTrackerSystemHost host)
	{
		this.host = host;
	}
	
	/***
	 * host app should call this function when user is logged in
	 */
	public void initializeDefectTrackerSystem( Person user ) {
		disposeScreen();

		currentUser = user;
		
		displayDefectList();
	}
	
	
/***
 * functions to be called by screens
 */
	@Override
	public ArrayList<Defect> getAllDefects() {
		ArrayList<Defect> allDefects = dbCommunicator.getAllDefects();
		return allDefects;
	}
	
	/***
	 * called by DefectListScreen when user selects to create a new defect
	 */
	@Override
	public void createDefect() {
		disposeScreen();
		
		Defect defect = new Defect(currentUser);
		currentDefect = defect;
		
		displayCurrentDefectForEditing();
	}

	/***
	 * called by DefectListScreen when user selects to edit a current defect
	 */
	@Override
	public void editDefect(Defect defect) {
		disposeScreen();

		currentDefect = defect;
		
		displayCurrentDefectForEditing();
	}

	/***
	 * called by DefectEditScreen when user selects to save an edited defect
	 */
	@Override
	public void saveDefect(Defect defect) {
		disposeScreen();

		currentDefect = defect;
		saveCurrentDefect();
		
		displayEmailScreen();
	}

	/***
	 * called by EmailScreen when user selects to email defect update to users
	 */
	@Override
	public void emailUsers(ArrayList<String> emailAddresses) {

	}

	/***
	 * called by any IDefectTrackerScreen when user cancels the action
	 *  (would be called from list screen when user selects to exit the system)
	 */
	@Override
	public void cancel() {
		IDefectTrackerScreen cancelledScreen = screen;
		
		if (cancelledScreen instanceof DefectListScreen)
			;// logoutUser();
		else if (cancelledScreen instanceof DefectEditScreen)
			;// return to DefectListScreen
		else if (cancelledScreen instanceof EmailScreen)
			;// return to DefectEditScreen
		else
			;// probably log an error then return to DefectListScreen
	}
/***
 * END functions to be called by screens
 */
	
	
	private void disposeScreen() {
		if (screen!=null)
		{
			screen.dispose();
			screen = null;
		}
	}
	
	private Integer saveCurrentDefect() {
		Integer defectId = dbCommunicator.saveDefect(currentDefect);
		return defectId;
	}

	private void displayDefectList() {
		screen = new DefectListScreen();
		screen.display(this);
	}

	private void displayCurrentDefectForEditing() {
		screen = new DefectEditScreen( currentDefect );
		screen.display(this);
	}
	
	private void displayEmailScreen() {
		screen = new EmailScreen( currentDefect );
		screen.display(this);
	}
}
