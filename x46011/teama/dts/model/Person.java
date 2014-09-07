package x46011.teama.dts.model;

/**
 * The Defect model object
 * 
 * @author Travis Cribbet
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Added spaces and renamed variables using standard naming convention
 * 	
 */
public class Person {
	private String name;
	private String email;
	
	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}