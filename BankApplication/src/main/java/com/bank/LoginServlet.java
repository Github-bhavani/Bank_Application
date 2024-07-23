package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class userlogin
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String Un = request.getParameter("username");
	        String Upwd = request.getParameter("password");
	        String type = request.getParameter("loginType");

	   if(type.equals("user")) {
	        boolean isValid = validateUser(Un, Upwd);
	        if (isValid) {
	            // Login successful
	            response.sendRedirect("loginsuccess.jsp"); // Redirect to a success page
	        } else {
	            // Login failed
	            response.sendRedirect("loginfail.jsp"); // Redirect to the login page again
	        }
	    }
	   else {

			 boolean isValid = validateAdmin(Un, Upwd);
		
		     if (isValid) {
		         // Login successful
		         response.sendRedirect("adminsuccess.jsp"); // Redirect to a success page
		     } else {
		         // Login failed
		         response.sendRedirect("adminfail.jsp"); // Redirect to the login page again
		     }}
	   }
	
	
	    // Method to validate user against MySQL database
	    private boolean validateUser(String Un, String Upwd) {
	        try {
	            // Load MySQL JDBC Driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Connect to MySQL database bankCredentials
	            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "Yahya@2003");
	                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer WHERE full_name = ? AND password = ?")) {

	                // Set parameters for the query
	                statement.setString(1, Un);
	                statement.setString(2, Upwd);

	                // Execute the query
	                try (ResultSet result = statement.executeQuery()) {
	                    // Check if any row exists in the result set
	                    return result.next();
	                }
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	    
	    private boolean validateAdmin(String Un, String Upwd) {
	        try {
	            // Load MySQL JDBC Driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Connect to MySQL database bankCredentials
	            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "Yahya@2003");
	                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?")) {

	                // Set parameters for the query
	                statement.setString(1, Un);
	                statement.setString(2, Upwd);

	                // Execute the query
	                try (ResultSet result = statement.executeQuery()) {
	                    // Check if any row exists in the result set
	                    return result.next();
	                }
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
		}

}
