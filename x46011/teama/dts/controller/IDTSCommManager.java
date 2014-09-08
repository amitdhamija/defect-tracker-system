package x46011.teama.dts.controller;

import java.util.ArrayList;
import java.util.List;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.Person;

/**
 * IDTSCommManager provides an interface to teh DTSCommManager to enable modularity.
 * 
 * @author Kevin Alexander
 * @version 1.0
 * @revision 1.1	Kevin Alexander: Removed unneccesary functions
 * @revision 1.2	Kevin Alexander: Renamed class to match its new limited role
 */
public interface IDTSCommManager {
	
	public List<Defect> getAllDefects();
	
	public List<Person> getUsers();
	
	public void createDefect(Defect defect);
	
	public void saveDefect( Defect defect );
		
	public void emailUsers( ArrayList<String> emailAddresses );
}
