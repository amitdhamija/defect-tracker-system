package x46011.teama.dts.controller;

import java.util.ArrayList;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.Person;


public interface IDefectTrackerSystemManager {

	public void initializeDefectTrackerSystem( Person user );
	
	public ArrayList<Defect> getAllDefects();
	
	public void createDefect();
	
	public void saveDefect( Defect defect );
	
	public void editDefect( Defect defect );
	
	public void emailUsers( ArrayList<String> emailAddresses );
	
	public void cancel();
}
