package x46011.teama.dts.controller;

import java.util.ArrayList;
import java.util.List;

import x46011.teama.dts.db.IDatabaseCommunications;
import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.Person;

/**
 * DTSCommManager handles interactions between the UI and the DB Communicator
 * 
 * @author Kevin Alexander
 * @version 1.0
 * @revision 1.1	Kevin Alexander: Removed unneccesary functions
 * @revision 1.2	Kevin Alexander: Renamed class to match its new limited role
 */
public class DTSCommManager implements IDTSCommManager {

	IDatabaseCommunications dbCommunicator;
	Person currentUser;
	
	/**
	 * Void constructor for DefectTrackingSystemManager class
	 */
	public DTSCommManager()
	{}	
	
	public List<Person> getUsers() {
		List<Person> allUsers = dbCommunicator.getUsersList();
		return allUsers;
	}
	
	public List<Defect> getAllDefects() {
		List<Defect> allDefects = dbCommunicator.getDefectList();
		return allDefects;
	}

	public void saveDefect(Defect defect) {
		dbCommunicator.saveDefect(defect);
	}

	@Override
	public void createDefect(Defect defect) {
		dbCommunicator.addDefect(defect);
	}

	public void emailUsers(ArrayList<String> emailAddresses) {

	}

}
