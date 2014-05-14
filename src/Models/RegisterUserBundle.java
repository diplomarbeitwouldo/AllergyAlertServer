package Models;

public class RegisterUserBundle {
	public String username;
	public String email;
	public String password;
	public String firstname;
	public String lastname;
	public String street;
	public String city;

	public RegisterUserBundle(String username, String email, String password,
			String firstname, String lastname, String street, String city) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.city = city;
	}
}
