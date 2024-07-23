package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registerUser
 */
@WebServlet("/registerUser")
public class registerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String jdbcUrl = "jdbc:mysql://localhost:3306/banking";
        String dbUsername = "root";
        String dbPassword = "Yahya@2003";

        // Retrieve form data
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String accountType = request.getParameter("account_type");
        double initialBalance = Double.parseDouble(request.getParameter("initial_balance"));
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("id_proof");

        // Database connection and SQL query
        Connection conn = null;
        PreparedStatement stmt = null;
        
        Random random = new Random();
        int Password = random.nextInt(1000, 9999);
        String sql = "INSERT INTO customer (full_name, address, mobile_no, email_id, account_type, initial_balance, dob, id_proof, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            // Prepare the SQL statement
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fullname);
            stmt.setString(2, address);
            stmt.setString(3, mobile);
            stmt.setString(4, email);
            stmt.setString(5, accountType);
            stmt.setDouble(6, initialBalance);
            stmt.setString(7, dob);
            stmt.setString(8, idProof);
            stmt.setInt(9, Password);

            // Execute the statement
            int rowsAffected = stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT account_no,password FROM customer WHERE mobile_no=?");
            stmt.setString(1, mobile);
            ResultSet rs = stmt.executeQuery();
            String accountNo = "";
            String password = "";
            if(rs.next()) {
            accountNo = rs.getString("account_no");
            password = rs.getString("password");
            if (rowsAffected > 0) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp?message=Registration Successful! \n<br>\n Your Account Number is "+accountNo+"\n<br>\n Your Temporary password is "+password);
                dispatcher.forward(request, response);
            } else {
            	RequestDispatcher dispatcher = request.getRequestDispatcher("registerUser.jsp?message=Registration Failed!");
                dispatcher.forward(request, response);
            }
        } }catch (ClassNotFoundException | SQLException e) {
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
