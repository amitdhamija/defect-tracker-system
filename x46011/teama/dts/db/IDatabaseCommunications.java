package x46011.teama.dts.db;

import java.util.ArrayList;
import x46011.teama.dts.model.Defect;


public interface IDatabaseCommunications {
	
	public ArrayList<Defect> getAllDefects();
	
	public Defect getDefect( Integer defectId );
	
	public void saveDefect( Defect defect );
}
