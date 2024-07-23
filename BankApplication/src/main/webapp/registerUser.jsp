<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Register Page</h2>
    <form action="registerUser" method="post">
        <label for="fullname">Full Name:</label><br>
        <input type="text" id="fullname" name="fullname" required><br><br>

        <label for="address">Address:</label><br>
        <textarea id="address" name="address" rows="4" cols="50" required></textarea><br><br>

        <label for="mobile">Mobile Number:</label><br>
        <input type="tel" id="mobile" name="mobile" pattern="[0-9]{10}" required><br><br>

        <label for="email">Email ID:</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="account_type">Type of Account:</label><br>
        <select id="account_type" name="account_type" required>
            <option value="">Select Account Type</option>
            <option value="Saving">Saving</option>
            <option value="Current">Current</option>
        </select><br><br>

        <label for="initial_balance">Initial Balance:</label><br>
        <input type="number" id="initial_balance" name="initial_balance" step="0.01" required><br><br>

        <label for="dob">Date of Birth:</label><br>
        <input type="date" id="dob" name="dob" required><br><br>

        <label for="id_proof">ID Proof:</label><br>
        <input type="text" id="id_proof" name="id_proof" required><br><br>

        <input type="submit" value="Register">
    </form>
    
    <% String message = request.getParameter("message"); %>
    <%  if(message!=null && !message.isEmpty()) {%>
    <%=message %>
    <% }%>
    
    <button  onclick="window.location.href='adminsuccess.jsp'">Go Back</button>
    
</body>
</html>