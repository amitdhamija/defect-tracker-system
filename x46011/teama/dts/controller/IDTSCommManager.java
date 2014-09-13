package x46011.teama.dts.controller;

import java.util.ArrayList;
import java.util.List;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.DefectStatus;
import x46011.teama.dts.model.Person;

/**
 * IDTSCommManager provides an interface to teh DTSCommManager to enable modularity.
 * 
 * @author Kevin Alexander
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Kevin Alexander: Removed unneccesary functions
 * @revision 1.2	Kevin Alexander: Renamed class to match its new limited role
 * @revision 1.3	Kevin Alexander: Added emailUsers signature
 * @revision 1.4	Amit Dhamija: Added getPriorities and getStatuses signature
 */
public interface IDTSCommManager {
	
	public List<Defect> getDefects();
	
	public List<Person> getUsers();
	
	public List<DefectPriority> getPriorities();
	
	public List<DefectStatus> getStatuses();
	
	public void createDefect(Defect defect);
	
	public void saveDefect( Defect defect );
		
	public void emailUsers( Defect defect, ArrayList<String> emailAddresses );
	
	
}
