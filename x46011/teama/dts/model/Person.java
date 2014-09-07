package x46011.teama.dts.model;

public class Person {
	private int ID;
	private String FirstName;
	private String LastName;
	private String Email;
	public Person(int Id, String firstName, String lastName, String email)
	{
		setId(Id);
		setName(firstName, lastName);
		setEmail(email);
	}
	public Person(String firstName, String lastName, String email)
	{
		setName(firstName, lastName);
		setEmail(email);
	}
	public String getFirstName() {
		return FirstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setName(String firstName, String lastName) {
		FirstName = firstName;
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getId() {
		return ID;
	}
	public void setId(int Id) {
		ID = Id;
	}
}
