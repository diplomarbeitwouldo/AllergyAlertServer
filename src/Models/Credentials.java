package Models;

import org.apache.commons.codec.digest.DigestUtils;

public class Credentials {

	private static String PASSWORD_SALT = "2q3eg0adi8gbp3ri";
	
	private String username;
	private String password;

	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEncryptedPassword() {
		return DigestUtils.sha1Hex(password + PASSWORD_SALT);
	}
}
