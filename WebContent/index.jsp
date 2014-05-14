<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tests</title>
</head>
<body>
	<h1>AllergyAlert - Tests</h1>
	<h2>Register</h2>
	<form method="POST" action="Register">
		<table>
			<tr>
				<td><input type="text" placeholder="Username" name="username" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="email" name="email" /></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="Password"
					name="password" /></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="Password Repeat"
					name="repeat" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="First name"
					name="firstname" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="Last name" name="lastname" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="Street" name="street" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="City" name="city" /></td>
			</tr>
		</table>
		<input type="submit" />
	</form>
	<h2>DataRequest</h2>
	<form method="POST" action="Patient">
		<table>
			<tr>
				<td><input type="text" placeholder="Username" name="username" /></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="Password"
					name="password" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="Firstname" name="firstname" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="Lastname" name="lastname" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="Street" name="street" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="City" name="city"/></td>
			</tr>
		</table>
		<input type="submit" />
	</form>
	<h2>Login</h2>
	<form method="POST" action="Login">
		<table>
			<tr>
				<td><input type="text" placeholder="Username" name="username" /></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="Password"
					name="password" /></td>
			</tr>
		</table>
		<input type="submit" />
	</form>
	<h2>Add Allergy</h2>
	<form method="POST" action="Patient">
		<table>
			<tr>
				<td><input type="text" placeholder="Username" name="username" /></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="Password"
					name="password" /></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="Allergy" name="allergy" /></td>
			</tr>
		</table>
		<input type="hidden" name="do" value="addAllergy" /> <input
			type="submit" />
	</form>
</body>
</html>