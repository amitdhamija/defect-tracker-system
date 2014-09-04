package x46011.teama.dts.manager;

import java.util.ArrayList;

import x46011.teama.dts.defect.Defect;
import x46011.teama.dts.entities.Person;


public interface IDefectTrackerSystemManager {

	public void initializeDefectTrackerSystem( Person user );
	
	public ArrayList<Defect> getAllDefects();
	
	public void createDefect();
	
	public void saveDefect( Defect defect );
	
	public void editDefect( Defect defect );
	
	public void emailUsers( ArrayList<String> emailAddresses );
	
	public void cancel();
}
