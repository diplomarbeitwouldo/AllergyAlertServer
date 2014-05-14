package Models;

import java.util.List;

public class Patient {
	public Patient(int id, String username, String password, String firstname,
			String lastname, String street, String city, List<Allergy> allergies) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.city = city;
		this.allergies = allergies;
	}

	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String street;
	private String city;
	private List<Allergy> allergies;

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public List<Allergy> getAllergies() {
		return this.allergies;
	}

}
