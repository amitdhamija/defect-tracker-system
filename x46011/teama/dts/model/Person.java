package x46011.teama.dts.model;

/**
 * The Defect model object
 * 
 * @author Travis Cribbet
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Amit Dhamija: Added spaces and renamed variables using standard naming convention
 * @revision 1.2	Amit Dhamija: Added additional getters and setters
 * 	
 */
public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	public Person(int id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}