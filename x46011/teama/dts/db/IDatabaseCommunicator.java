package x46011.teama.dts.db;

import java.util.List;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.Person;
import x46011.teama.dts.model.Status;


/**
 * IDTSCommManager provides an interface for DTSCommManager to enable modularity.
 * 
 * @author Travis Cribbet
 * @version 1.0
 * @revision 1.1	Kevin Alexander: Removed unneccesary functions
 */
public interface IDatabaseCommunicator {
       
        public List<Defect> getDefectList();   
        public void addDefect( Defect defect );
        public Defect getDefect( Integer defectId );
        public void saveDefect( Defect defect );
        
        public void addUser(Person p);
        public void deleteUser(Person p);
        public Person getUser(int id);
        
        public Status getStatus(int id);
        public void addStatus(Status status);
        public void deleteStatus(Status status);
        
        public List<Status> getStatusList();      
        public List<Person> getUsersList(); 
        
}
