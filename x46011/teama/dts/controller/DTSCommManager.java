package x46011.teama.dts.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import x46011.teama.dts.db.DatabaseCommunicator;
import x46011.teama.dts.db.IDatabaseCommunications;
import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.Person;
import x46011.teama.dts.notification.DTSRawTextFormatter;

/**
 * DTSCommManager handles interactions between the UI and the communications layer
 * 
 * @author Kevin Alexander
 * @version 1.0
 * @revision 1.1	Kevin Alexander: Removed unneccesary functions
 * @revision 1.2	Kevin Alexander: Renamed class to match its new limited role
 * @revision 1.3	Kevin Alexander: Added instantiation of DatabaseCommunicator which went missing after a refactoring pass
 * @revision 1.4	Kevin Alexander: Implemented two versions of emailUsers()
 */
public class DTSCommManager implements IDTSCommManager {

	IDatabaseCommunications dbCommunicator;
	Person currentUser;
	
	/**
	 * Void constructor for DefectTrackingSystemManager class
	 */
	public DTSCommManager()
	{
		dbCommunicator = new DatabaseCommunicator();
	}	
	
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

	public void emailUsers(Defect defect, ArrayList<String> emailAddresses) {
		String formattedMessage = (new DTSRawTextFormatter()).formatEmail(defect);
		DTSEmailer_GMail mailer = new DTSEmailer_GMail();
		Set<String> setOfAddresses = new HashSet<String>(emailAddresses);
		
		mailer.sendMail(formattedMessage, setOfAddresses);
	}
	
	public void emailUsers(Defect oldDefect, Defect newDefect, ArrayList<String> emailAddresses) {
		String formattedMessage = (new DTSRawTextFormatter()).formatEmail(oldDefect,newDefect);
		DTSEmailer_GMail mailer = new DTSEmailer_GMail();
		Set<String> setOfAddresses = new HashSet<String>(emailAddresses);
		
		mailer.sendMail(formattedMessage, setOfAddresses);
	}

}
