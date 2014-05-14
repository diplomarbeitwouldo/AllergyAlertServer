package Models;

public class UpdateUserBundle {
	public String firstname;
	public String lastname;
	public String street;
	public String city;
	
	public UpdateUserBundle(String firstname, String lastname, String street,
			String city) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.city = city;
	}
}
