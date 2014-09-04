package x46011.teama.dts.dbComm;

import java.util.ArrayList;
import x46011.teama.dts.defect.Defect;


public interface IDatabaseCommunications {
	
	public ArrayList<Defect> getAllDefects();
	
	public Defect getDefect( Integer defectId );
	
	public void saveDefect( Defect defect );
}
