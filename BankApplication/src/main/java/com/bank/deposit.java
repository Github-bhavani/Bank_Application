package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deposit
 */
@WebServlet("/deposit")
public class deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   		String accountNo = request.getParameter("accountNumber");
		double amount = Double.parseDouble(request.getParameter("amount"));
		response.sendRedirect("deposit.jsp?message=Deposit successful");
		
		try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","Yahya@2003");
		String sql = "UPDATE customer SET initial_balance=initial_balance+? WHERE account_No = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setDouble(1,amount);
		statement.setString(2,accountNo);
		statement.executeUpdate();
		
		String type="Deposit";
		statement = con.prepareStatement("INSERT INTO transactions (account_no,type,amount) VALUES (?,?,?)");
		statement.setString(1, accountNo);
		statement.setString(2, type);
		statement.setDouble(3, amount);
		statement.executeUpdate();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
