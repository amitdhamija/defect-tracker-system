
public class Person {
	private String Name;
	private String Email;
	public Person(String name, String email)
	{
		setName(name);
		setEmail(email);
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
}
