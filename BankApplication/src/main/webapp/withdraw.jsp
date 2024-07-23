<%@ page language="java" import="java.sql.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WITHDRAW AMOUNT</title>
</head>
<body>

<% String message = request.getParameter("message"); %>

        <h2>Withdraw Money</h2>
        <form action="withdraw" method="post">
        
            <label for="accountNumber">Account Number:</label>
            <input type="text" id="accountNumber" name="accountNumber"/>
            
            <label for="amount">Amount:</label>
            <input type="number" id="amount" name="amount"/>
            
            <button type="submit">Withdraw</button>
        </form>
        
    <% if (message != null && !message.isEmpty()) { %>
    <p style="color: green;"><%= message %></p>
<% } %>
<button class="User Home" onclick="window.location.href='loginsuccess.jsp'">Go Back</button>
</body>
</html>