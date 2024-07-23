package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class removeUser
 */
@WebServlet("/removeUser")
public class removeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jdbcUrl = "jdbc:mysql://localhost:3306/banking";
        String dbUsername = "root";
        String dbPassword = "Yahya@2003";

        // Retrieve account_no parameter from request
        String accountNo = request.getParameter("accountNo");

        // Database connection and SQL query
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM customer WHERE account_no = ?";

        try {
            // Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            // Prepare the SQL statement
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNo);

            // Execute the statement
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
            	RequestDispatcher dispatcher = request.getRequestDispatcher("removeUser.jsp?message=Removed Successful!");
                dispatcher.forward(request, response);
            } else {
            	RequestDispatcher dispatcher = request.getRequestDispatcher("removeUser.jsp?message=Remove Failed!");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
