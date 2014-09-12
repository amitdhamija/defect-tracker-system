package x46011.teama.dts.db;

import java.util.List;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.DefectStatus;
import x46011.teama.dts.model.Person;


/**
 * IDTSCommManager provides an interface for DTSCommManager to enable modularity.
 * 
 * @author Travis Cribbet
 * @author Amit Dhamija
 * @author Kevin Alexander
 * @version 1.0
 * @revision 1.1	Kevin Alexander: Removed unneccesary functions'
 * @revision 1.2	Amit Dhamija: Removed unnecessary methods
 */
public interface IDatabaseCommunicator {
       
        public List<Defect> getDefectList();
        public List<Person> getUserList();
        public List<DefectPriority> getPriorityList();
        public List<DefectStatus> getStatusList();
        
        public void addUser(Person p);
        public void addDefect( Defect defect );
        public void saveDefect( Defect defect );
}
