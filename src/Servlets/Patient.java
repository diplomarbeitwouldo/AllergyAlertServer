package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Database.UserManager;
import Models.Credentials;
import Models.PasswordUserBundle;
import Models.UpdateUserBundle;

@WebServlet("/Patient")
public class Patient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager um = new UserManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Patient() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null) {
			if (um.checkCredentials(new Credentials(request
					.getParameter("username"), request.getParameter("password")))) {
				if (request.getParameter("do") != null) {
					if (request.getParameter("do").equals("addAllergy")) {
						try {
							Models.Patient p = um.getPatient(request
									.getParameter("username"));
							um.addAllergy(p.getId(),
									request.getParameter("allergy"));
							p = um.getPatient(request.getParameter("username"));
							HashMap<String, Models.Patient> err = new HashMap<String, Models.Patient>();
							err.put("patient", p);
							JSONObject obj = new JSONObject(err);
							response.getWriter().write(obj.toString());
						} catch (SQLException e) {
							HashMap<String, String> err = new HashMap<String, String>();
							err.put("exception", "Something went wrong!");
							JSONObject obj = new JSONObject(err);
							response.getWriter().write(obj.toString());
						}
					} else if (request.getParameter("do").equals(
							"changePassword")) {
						try {
							um.changePassword(
									request.getParameter("username"),
									new PasswordUserBundle(request
											.getParameter("password"), request
											.getParameter("repeat")));
							Models.Patient p = um.getPatient("username");
							HashMap<String, Models.Patient> err = new HashMap<String, Models.Patient>();
							err.put("patient", p);
							JSONObject obj = new JSONObject(err);
							response.getWriter().write(obj.toString());
						} catch (Exception e) {
							HashMap<String, String> err = new HashMap<String, String>();
							err.put("exception", "Something went wrong!");
							JSONObject obj = new JSONObject(err);
							response.getWriter().write(obj.toString());
						}
					}
				} else {
					try {
						Models.Patient p = um.updatePatient(
								request.getParameter("username"),
								new UpdateUserBundle(request
										.getParameter("firstname"), request
										.getParameter("lastname"), request
										.getParameter("street"), request
										.getParameter("city")));
						HashMap<String, Models.Patient> err = new HashMap<String, Models.Patient>();
						err.put("patient", p);
						JSONObject obj = new JSONObject(err);
						response.getWriter().write(obj.toString());
					} catch (SQLException e) {
						HashMap<String, String> err = new HashMap<String, String>();
						err.put("exception", "Someathing went wrong!");
						JSONObject obj = new JSONObject(err);
						response.getWriter().write(obj.toString());
					}
				}
			} else {
				HashMap<String, String> err = new HashMap<String, String>();
				err.put("exception", "Wrong credentials!");
				JSONObject obj = new JSONObject(err);
				response.getWriter().write(obj.toString());
			}
		} else {
			HashMap<String, String> err = new HashMap<String, String>();
			err.put("exception", "Missing Parameter!");
			JSONObject obj = new JSONObject(err);
			response.getWriter().write(obj.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
