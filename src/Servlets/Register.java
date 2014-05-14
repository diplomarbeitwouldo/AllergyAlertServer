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
import Models.RegisterUserBundle;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager um = new UserManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		if (request.getParameter("username") != null
				&& request.getParameter("password") != null
				&& request.getParameter("repeat") != null
				&& request.getParameter("email") != null
				&& request.getParameter("firstname") != null
				&& request.getParameter("lastname") != null
				&& request.getParameter("street") != null
				&& request.getParameter("city") != null) {
			if (request.getParameter("password").equals(
					request.getParameter("repeat"))) {
				try {
					um.registerUser(new RegisterUserBundle(request
							.getParameter("username"), request
							.getParameter("email"), request
							.getParameter("password"), request
							.getParameter("firstname"), request
							.getParameter("lastname"), request
							.getParameter("street"), request
							.getParameter("city")));
					HashMap<String, Models.Patient> err = new HashMap<String, Models.Patient>();
					err.put("user",
							um.getPatient(request.getParameter("username")));
					JSONObject obj = new JSONObject(err);
					response.getWriter().write(obj.toString());
				} catch (SQLException e) {
					HashMap<String, String> err = new HashMap<String, String>();
					err.put("exception", e.getMessage());
					JSONObject obj = new JSONObject(err);
					response.getWriter().write(obj.toString());
				}
			} else {
				HashMap<String, String> err = new HashMap<String, String>();
				err.put("exception", "Passwords doesn't match!");
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
}
