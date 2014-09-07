package x46011.teama.dts.db;

import java.util.List;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.Person;
import x46011.teama.dts.model.Status;


public interface IDatabaseCommunications {
       
        public List<Defect> getDefectList();   
      //need to add this...  public Defect getDefect( Integer defectId );    
        public void addDefect( Defect defect );
        
        public void addPerson(Person p);
        public void deletePerson(Person p);
        public Person getPerson(int id);
        
        public Status getStatus(int id);
        public void addStatus(Status status);
        public void deleteStatus(Status status);
        public List<Status> getStatusList();      
        public List<Person> getPersonList();       
}
