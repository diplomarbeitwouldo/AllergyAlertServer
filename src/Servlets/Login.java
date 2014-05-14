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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserManager um;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		um = new UserManager();
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
				&& request.getParameter("password") != null) {
			if (um.checkCredentials(new Credentials(request
					.getParameter("username"), request.getParameter("password")))) {
				try {
					Models.Patient p = um.getPatient(request
							.getParameter("username"));
					HashMap<String, Models.Patient> err = new HashMap<String, Models.Patient>();
					err.put("patient", p);
					JSONObject obj = new JSONObject(err);
					response.getWriter().write(obj.toString());
				} catch (SQLException e) {
					HashMap<String, String> err = new HashMap<String, String>();
					err.put("exception", "Something went wroang!");
					JSONObject obj = new JSONObject(err);
					response.getWriter().write(obj.toString());
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

}
