<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h2>Remove User</h2>
    <form action="removeUser" method="post">
        <label for="accountNo">Account Number:</label><br>
        <input type="text" id="accountNo" name="accountNo" required><br><br>

        <input type="submit" value="Remove User">
    </form>
    <% String message = request.getParameter("message"); %>
    <%  if(message!=null && !message.isEmpty()) {%>
    <%=message %>
    <% }%>
    
    <button  onclick="window.location.href='adminsuccess.jsp'">Go Back</button>
</body>
</html>