<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Redirect Example</title>
</head>
<body>
<%
    // Get the value of the 'is_registered' parameter
    String isRegistered = request.getParameter("is_registered");

    // Check if 'is_registered' is true
    if ("true".equalsIgnoreCase(isRegistered)) {
        // Forward to login.jsp
%>
<jsp:forward page="loginPage.jsp"/>
<%
} else {
    // Forward to register.jsp
%>
<jsp:forward page="registerPage.jsp"/>
<%
    }
%>
</body>
</html>
