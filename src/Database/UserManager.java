package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Allergy;
import Models.Credentials;
import Models.PasswordUserBundle;
import Models.Patient;
import Models.RegisterUserBundle;
import Models.UpdateUserBundle;

public class UserManager {
	private Connection conn;

	public UserManager() {
		conn = DBConnector.sharedConnector().getConnection();
	}

	/**
	 * Checks if the username already exists in the database
	 * 
	 * @param username
	 * @return true/false
	 * @throws SQLException
	 */
	public boolean userExists(String username) throws SQLException {
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM patient WHERE username=?");
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		boolean exists = rs.next();
		rs.close();
		stmt.close();
		return exists;
	}

	/**
	 * Checks the given credentials
	 * 
	 * @param identifier
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean checkCredentials(Credentials c) {
		Patient p;
		try {
			p = this.getPatient(c.getUsername());
			if (p != null) {
				return p.getPassword().equals(c.getEncryptedPassword());
			} else
				return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public Patient getPatientById(int id) throws SQLException {
		Patient p = null;
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM patient WHERE id=?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			List<Allergy> allergies = this.getAllergiesForPatient(id);
			p = new Patient(rs.getInt("id"), rs.getString("username"),
					rs.getString("password"), rs.getString("firstname"),
					rs.getString("lastname"), rs.getString("street"),
					rs.getString("city"), allergies);
		}
		rs.close();
		stmt.close();
		return p;
	}

	public List<Allergy> getAllergiesForPatient(int id) throws SQLException {
		List<Allergy> allergies = new ArrayList<Allergy>();
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM allergies WHERE patient_id=?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			allergies.add(new Allergy(rs.getInt("allergy_id"), rs
					.getString("allergy")));
		}
		rs.close();
		stmt.close();
		return allergies;
	}

	public Patient updatePatient(String username, UpdateUserBundle bundle)
			throws SQLException {
		Patient np = null;
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE user SET firstname=?, lastname=?, street=?, city=? WHERE username=?");
		stmt.setString(1, bundle.firstname);
		stmt.setString(2, bundle.lastname);
		stmt.setString(3, bundle.street);
		stmt.setString(4, bundle.city);
		stmt.setString(5, username);
		stmt.executeQuery();
		stmt.close();
		np = getPatient(username);
		return np;
	}

	public void changePassword(String username, PasswordUserBundle bundle)
			throws SQLException, PasswordNotEqualException {
		Patient op = getPatient(username);
		if (bundle.password.equals(bundle.repeat)) {
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE user SET password=? WHERE id=?");
			stmt.setString(1, bundle.password);
			stmt.setInt(2, op.getId());
		} else
			throw new PasswordNotEqualException();
	}

	public Patient getPatient(String identifier) throws SQLException {
		Patient p = null;
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM patient WHERE username=?");
		stmt.setString(1, identifier);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			p = this.getPatientById(rs.getInt("id"));
		}
		rs.close();
		stmt.close();
		if (p == null) {
			PreparedStatement estmt = conn
					.prepareStatement("SELECT * FROM patient WHERE email=?");
			estmt.setString(1, identifier);
			ResultSet ers = estmt.executeQuery();
			if (ers.next()) {
				p = this.getPatientById(ers.getInt("id"));
			}
			ers.close();
			estmt.close();
		}
		return p;
	}

	/**
	 * Registers a new User
	 * 
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void registerUser(RegisterUserBundle bundle)
			throws SQLException {
		Credentials c = new Credentials(bundle.username, bundle.password);
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO patient (username, email, password, firstname, lastname, street, city) VALUES(?,?,?,?,?,?,?)");
		stmt.setString(1, bundle.username);
		stmt.setString(2, bundle.email);
		stmt.setString(3, c.getEncryptedPassword());
		stmt.setString(4, bundle.firstname);
		stmt.setString(5, bundle.lastname);
		stmt.setString(6, bundle.street);
		stmt.setString(7, bundle.city);
		stmt.executeUpdate();
		stmt.close();
	}

	/**
	 * Adds new Allergy to the Database
	 * 
	 * @param allergy
	 *            : Name of the Allergy
	 * @throws SQLException
	 */
	public void addAllergy(int patient_id, String allergy) throws SQLException {
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO allergies (patient_id, allergy) VALUES(?,?)");
		stmt.setInt(1, patient_id);
		stmt.setString(2, allergy);
		stmt.executeUpdate();
		stmt.close();
	}

	public void removeAllergy(int id) throws SQLException {
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM allergies WHERE allergy_id=?");
		stmt.setInt(1, id);
		stmt.executeQuery();
	}

	class PasswordNotEqualException extends Exception {
		private static final long serialVersionUID = 1L;

		public PasswordNotEqualException() {
			super("The Passwords do not match!");
		}
	}
}
