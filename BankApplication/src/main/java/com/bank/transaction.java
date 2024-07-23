package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class transaction
 */
@WebServlet("/transaction")
public class transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 		String userId = request.getParameter("account_no");

        String jdbcURL = "jdbc:mysql://localhost:3306/banking"; // Change to your DB URL
        String dbUser = "root"; // Change to your DB user
        String dbPassword = "Yahya@2003"; // Change to your DB password

            try {
            	Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                PreparedStatement ps = conn.prepareStatement("SELECT date,type,amount FROM transactions WHERE account_no=?");
    			ps.setString(1, userId);
    			ResultSet rs = ps.executeQuery();
    			
    			List<Object[]> transactionList = new ArrayList<>();
    			
    			while (rs.next()) {
                    Timestamp date = rs.getTimestamp("date");
                    String description = rs.getString("type");
                    double amount = rs.getDouble("amount");
                    Object[] transaction = {date, description, amount};
                    transactionList.add(transaction);
    			}
    			
    			request.setAttribute("transactions", transactionList);
    	        request.getRequestDispatcher("transaction.jsp").forward(request, response);
    			
    		}
    		catch(SQLException e) {
    			e.printStackTrace();
    		}
 	}}




